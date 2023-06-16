package com.stickpoint.ddmusic.common.config;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.config
 * @Author: fntp
 * @CreateTime: 2022-11-19  22:24
 * @Version: 1.0
 */
public class DdMusicHttpConfig implements DdmusicSpiMonitor {

    /**
     * SPI加载初始化日志
     */
    private static final Logger log = LoggerFactory.getLogger(DdMusicHttpConfig.class);

    /**
     * 在这里对 HTTP.Builder 做一些自定义的配置
     * 这里的自定义配置都是加载来自于云端的数据
     * 通过Gitee进行自动化的修改的，可以通过轮训的方式方法进行自动刷新系统配置
     * 或者是通过手动的方式进行刷新系统配置
     */
    @Override
    public void loadRemoteProperties() {
        log.info("系统配置已启动加载");
        // 首先读取系统加载的远程配置数据
        int requestServerStatus =  Integer.parseInt(String.valueOf(SystemCache.APP_PROPERTIES
                .get(InfoEnums.API_DDMUSIC_WP_MUSIC_STATUS.getInfoContent())));
        String requestBaseHosts = null;
        String prefix = null;
        String port = null;
        // 如果说服务状态是ok的，也就是说当前服务请求可以使用wpMusic
        if (requestServerStatus==InfoEnums.APP_PROPERTIES_STATUS_OK_INT.getNumberInfo()) {
            // 获取当前基础服务的主机地址
            requestBaseHosts = (String) SystemCache.APP_PROPERTIES
                    .get(InfoEnums.API_DDMUSIC_SOURCE_LIST_WP_MUSIC_HOST.getInfoContent());
            // 获取当前基础服务的前缀
            prefix =(String) SystemCache.APP_PROPERTIES
                    .get(InfoEnums.API_DDMUSIC_SOURCE_LIST_WP_MUSIC_PREFIX.getInfoContent());
            // 获取当前基础服务的端口
            port =(String) SystemCache.APP_PROPERTIES
                    .get(InfoEnums.API_DDMUSIC_BASE_WP_MUSIC_PORT.getInfoContent());
        }else {
            requestServerStatus = (int) SystemCache.APP_PROPERTIES
                    .get(InfoEnums.API_DDMUSIC_MY_FREE_MUSIC_STATUS.getInfoContent());
            if(requestServerStatus == InfoEnums.APP_PROPERTIES_STATUS_OK_INT.getNumberInfo()) {
                requestBaseHosts = (String) SystemCache.APP_PROPERTIES.get("api.sourceList.myFreeMusic.host");
                prefix =(String) SystemCache.APP_PROPERTIES.get("api.sourceList.myFreeMusic.prefix");
                port =(String) SystemCache.APP_PROPERTIES.get("api.sourceList.myFreeMusic.port");
            }
        }
        if (Objects.nonNull(requestBaseHosts)) {
            String[] hosts = requestBaseHosts.split(InfoEnums.APP_PROPERTIES_COMMON_SYMBOL_SPLIT.getInfoContent());
            String requestUrl = hosts[0].concat(port).concat(prefix);
            // 设置系统全局缓存的默认请求路径
            SystemCache.APP_PROPERTIES.put(InfoEnums.API_FINAL_REQUEST_BASE_URL.getInfoContent(),requestUrl);
        }
        // TODO 当没有远程提供的音乐请求基础地址的时候，需要对用户进行告知，比如服务器维护中，诸如此类
        log.info("远程核心系统配置加载完毕");
    }
}
