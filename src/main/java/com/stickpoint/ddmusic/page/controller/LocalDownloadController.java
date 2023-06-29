package com.stickpoint.ddmusic.page.controller;

import com.stickpoint.ddmusic.common.cache.SystemCache;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * 本地下载控制器
 * @author puye(0303)
 * @since 2023/6/28
 */
public class LocalDownloadController {
    /**
     * 下载完成按钮
     */
    @FXML
    public HBox downloadFinished;

    /**
     * 下载中按钮
     */
    @FXML
    public HBox downloadingHBox;

    /**
     * 本地下载的rootPane
     */
    @FXML
    public AnchorPane localDownloadPane;

    @FXML
    public void initialize(){
        initLocalDownloadPageIntoChildList();
    }

    /**
     * 初始化的时候将节点塞入到centerView将会现实的视图中去
     */
    private void initLocalDownloadPageIntoChildList(){
        SystemCache.CENTER_VIEW_PAGE_LIST.add(localDownloadPane);
    }
}
