package com.stickpoint.ddmusic.page.controller;

import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Objects;

/**
 * @author fntp
 */
public class RecentlyPlayListController {

    @FXML
    public Label lastThreeDaysPlayTotal;

    @FXML
    public ListView<File> recentlyPlayList;

    private ContextMenu recentlyPlayListPopup;

    @FXML
    public void initialize(){

    }

    /**
     * 获得最近播放的菜单组件
     * @return 返回一个最近播放
     */
    public ContextMenu getRecentlyPlayListPopup(){
        // 因为这个contextMenu是一直存在的 只能在空的状态下去进行设置
        if (Objects.isNull(recentlyPlayListPopup)) {
            recentlyPlayListPopup = new ContextMenu(new SeparatorMenuItem());
            FXMLLoader recentlyPlayListLoader = SystemCache.PAGE_MAP.get(PageEnums.RECENTLY_PLAY_LIST.getRouterId());
            Parent recentlyPlayListPopupNode = recentlyPlayListLoader.getRoot();
            recentlyPlayListPopup.getScene().setRoot(recentlyPlayListPopupNode);
        }
        return recentlyPlayListPopup;
    }

    /**
     * 一键清除所有最近播放
     * @param mouseEvent 鼠标事件
     */
    public void clearRecentlyAll(MouseEvent mouseEvent) {

    }

    /**
     * 一键收藏所有最近播放
     * @param mouseEvent 鼠标事件
     */
    public void favoriteRecentlyAll(MouseEvent mouseEvent) {

    }
}
