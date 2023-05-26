package com.stickpoint.ddmusic.common.config;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.config
 * @Author: fntp
 * @CreateTime: 2022-11-19  22:24
 * @Description: TODO
 * @Version: 1.0
 */
public class DdMusicHttpConfig implements DdmusicSpiMonitor {

    /**
     * 在这里对 HTTP.Builder 做一些自定义的配置
     * 这里的自定义配置都是加载来自于云端的数据
     * 通过Gitee进行自动化的修改的，可以通过轮训的方式方法进行自动刷新系统配置
     * 或者是通过手动的方式进行刷新系统配置
     */
    @Override
    public void loadRemoteProperties() {
        // 首先读取系统加载的远程配置数据
        int requestServerStatus = (int) SystemCache.APP_PROPERTIES
                .get(InfoEnums.API_DDMUSIC_WP_MUSIC_STATUS.getInfoContent());
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
            String requestUrl = hosts[0].concat(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent())
                    .concat(port).concat(prefix);
            // 设置系统缓存的默认请求路径
        }
    }
}
