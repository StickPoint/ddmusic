package com.stickpoint.ddmusic.page.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author fntp
 * @since 2023/9/2
 */
public class PlayDetailAdditionPageController {

    /**
     * 歌曲播放界面图片
     */
    public ImageView cover;
    /**
     * 根容器
     */
    public VBox rootPane;

    @FXML
    public void initialize() {
        initializeImageCover();
    }

    /**
     * 将附加页面的歌曲图像进行圆角化设置
     */
    private void initializeImageCover() {
        Rectangle clip = new Rectangle(cover.getFitWidth(), cover.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        cover.setClip(clip);
    }

}
