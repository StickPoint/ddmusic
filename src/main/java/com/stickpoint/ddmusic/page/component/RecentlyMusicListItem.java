package com.stickpoint.ddmusic.page.component;

import com.stickpoint.ddmusic.common.enums.InfoEnums;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.page
 * @Author: fntp
 * @CreateTime: 2022-10-15  16:41
 * @Description: TODO
 * @Version: 1.0
 */
public class RecentlyMusicListItem {
    /**
     * 删除音乐按钮
     */
    public Region deleteMusic;
    /**
     * 喜欢音乐按钮
     */
    public Region favMusic;
    /**
     * 分享音乐按钮
     */
    public Region shareMusic;
    /**
     * 是否在播放中
     */
    public ImageView playing;
    /**
     * 操作面板
     */
    public Pane optionBtnPane;
    /**
     * 音乐时间
     */
    public Label musicTime;
    /**
     * 歌手
     */
    public Label singer;
    /**
     * 歌曲名
     */
    public Label musicName;
    /**
     * 签名
     */
    private String sign;

    @FXML
    public void initialize(){
        this.sign = musicName.getText().concat(singer.getText());
    }

    /**
     * 此方法当鼠标移入区域的时候触发 显示当前音乐操作按钮面板
     */
    @FXML
    public void showOptionBtn() {
        favMusic = new Region();
        shareMusic = new Region();
        deleteMusic = new Region();
        optionBtnPane.getChildren().clear();
        optionBtnPane.getChildren().add(favMusic);
        optionBtnPane.getChildren().add(shareMusic);
        optionBtnPane.getChildren().add(deleteMusic);
        // 设置内容，设置位置
        favMusic.setStyle(InfoEnums.RECENTLY_PLAY_LIST_ITEM_FAV_MUSIC_REGION_VALUE.getInfoContent());
        shareMusic.setStyle(InfoEnums.RECENTLY_PLAY_LIST_ITEM_SHARE_MUSIC_REGION_VALUE.getInfoContent());
        deleteMusic.setStyle(InfoEnums.RECENTLY_PLAY_LIST_ITEM_DELETE_MUSIC_REGION_VALUE.getInfoContent());
        // 设置位置
        favMusic.setLayoutX(50);
        favMusic.setLayoutY(13);
        favMusic.prefWidth(16);
        favMusic.prefHeight(16);
        shareMusic.setLayoutX(6);
        shareMusic.setLayoutY(13);
        shareMusic.setPrefWidth(15);
        shareMusic.setPrefHeight(15);
        deleteMusic.setLayoutX(29);
        deleteMusic.setLayoutY(13);
        deleteMusic.setPrefWidth(15);
        deleteMusic.setPrefHeight(15);
    }

    /**
     * 此方法当鼠标从目标区域移出的时候触发 显示歌曲播放时长
     */
    @FXML
    public void showMusicTime() {
        if (Objects.nonNull(musicTime)) {
            optionBtnPane.getChildren().clear();
            optionBtnPane.getChildren().add(musicTime);
        }
    }

    @Override
    public int hashCode() {
        return getSign().hashCode();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode()==this.hashCode() && obj.getClass() == this.getClass();
    }

}

