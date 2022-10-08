package com.stickpoint.ddmusic.page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.File;

/**
 * @author puye(0303)
 */
public class RecentlyPlayListController {

    @FXML
    public Label lastThreeDaysPlayTotal;

    @FXML
    public ListView<File> recentlyPlayList;

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
