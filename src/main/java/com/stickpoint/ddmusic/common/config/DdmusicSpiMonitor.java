package com.stickpoint.ddmusic.common.config;

/**
 * @author fntp
 * @since 2023/5/26
 */
public interface DdmusicSpiMonitor {

    /**
     * 加载远程配置，远程配置控制是否启动应用程序
     */
    void loadRemoteProperties();

}
