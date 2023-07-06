package com.stickpoint.ddmusic.page.controller;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fntp
 * @since 2023/6/20
 */
public class MusicOptionsController {
    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(MusicOptionsController.class);
    /**
     * 播放
     */
    @FXML
    public Region play;
    /**
     * 收藏
     */
    @FXML
    public Region favorite;
    /**
     * 添加到我的歌单
     */
    @FXML
    public Region addToList;
    /**
     * 下载
     */
    @FXML
    public Region download;
    /**
     * 操作面板
     */
    @FXML
    public AnchorPane optionPad;

}
