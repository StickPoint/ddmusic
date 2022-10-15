package com.stickpoint.ddmusic.common.constriant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: io.github.ddmusic.common.constriant
 * @Author: fntp
 * @CreateTime: 2022-09-17  11:38
 * @Description: TODO 
 * @Version: 1.0
 */
public interface SystemCache {

    /**
     * BeCareful！ 这里是应用全局配置，是通过网络IO加载的数据
     * 系统全局应用配置数据缓存，
     * 当配置中心发生变化的时候进行全局配置刷新
     * （此功能需要启动全局自动刷新，如果禁用全局配置自动刷新需要通过手动刷新配置中心）
     */
    Map<String, Object> APP_PROPERTIES = new ConcurrentHashMap<>(80);
    
    /**
     * 系统内部加载的所有页面
     */
    Map<String,FXMLLoader> FXML_LOAD_MAP = new ConcurrentHashMap<>(40);
    /**
     * 全局唯一日志对象
     */
    Logger logger = Logger.getGlobal();
    /**
     * 应用内部全局配置，不由外部决定
     * 本配置仅在运行时生效
     * 本配置仅由内部程序修改，不暴露对外接口
     */
    Map<String,Object> SYS_INNER_PROPERTIES = new ConcurrentHashMap<>(80);
}
