package com.stickpoint.ddmusic.common.utils;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.Serial;
import java.util.Objects;
import java.util.Properties;

/**
 * description: SystemPropertiesUtil
 *
 * @ClassName : SystemPropertiesUtil
 * @Date 2022/9/23 9:13
 * @Author fntp
 * @PackageName com.stickpoint.ddmusic.common.utils
 */
public class SystemPropertiesUtil extends Properties {
    
	
    @Serial
    private static final long serialVersionUID = -8648895737014160099L;

    /**
     * 构建日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(SystemPropertiesUtil.class);

    /**
     * 初始化系统配置
     */
    public void loadProperties(){
        try {
            this.load(this.getClass().getClassLoader().getResourceAsStream("sys.properties"));
            log.info("数据加载成功");
            SystemCache.APP_PROPERTIES.putAll(new YamlUtil(this.getProperty("applicationProperties")));
        } catch (IOException e) {
            log.info(e.getMessage(),e);
        }
    }

    /**
     * 刷新系统内部缓存的配置
     * 传入一个PropertyKey来获得系统配置
     */
    public void flushCacheProperties(){
        SystemCache.APP_PROPERTIES.clear();
    }

    /**
     * 刷新系统配置
     */
    public void updateSystemProperties(){
        try {
            this.load(this.getClass().getClassLoader().getResourceAsStream("sys.properties"));
            log.info("数据加载成功");
            YamlUtil applicationProperties = new YamlUtil(this.getProperty("applicationProperties"));
            // 数据加载完毕后，将系统配置保留在系统内存中
            if (Objects.nonNull(applicationProperties.get(InfoEnums.APP_ENV_VERSION.getInfoContent()))
                    &&Objects.nonNull(SystemCache.APP_PROPERTIES.get(InfoEnums.APP_ENV_VERSION.getInfoContent()))) {
                // 检查系统环境版本
                String systemEnvironmentVersion = applicationProperties.get(InfoEnums.APP_ENV_VERSION.getInfoContent());
                // 检查当前环境版本
                String currentEnvironmentVersion = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.APP_ENV_VERSION.getInfoContent());
                // 如果不一致，直接刷新系统配置
                if (systemEnvironmentVersion.equals(currentEnvironmentVersion)){
                    SystemCache.APP_PROPERTIES.putAll(applicationProperties);
                    log.info("当前系统环境版本一致，无需进行版本更新！");
                }else {
                    log.info("版本环境更新成功！已经升级到最新环境！");
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage(),e);
        }
    }

}
