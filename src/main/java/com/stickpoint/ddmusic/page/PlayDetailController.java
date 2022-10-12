package com.stickpoint.ddmusic.page;

import com.leewyatt.rxcontrols.controls.RXAudioSpectrum;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import javafx.fxml.FXML;
import javafx.util.Duration;

/**
 * description: PlayDetailController
 *
 * @ClassName : PlayDetailController
 * @Date 2022/10/11 17:59
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.page
 */
public class PlayDetailController {
    /**
     * 歌词组件
     */
    @FXML
    public RXLrcView lrcView;
    /**
     * 频谱组件
     */
    @FXML
    public RXAudioSpectrum audioSpectrum;
    /**
     * 频谱初始普段
     */
    private static final float[] EMPTY_ARRAY = new float[128];

    @FXML
    public void initialize(){
        lrcView.setLrcDoc(null);
        lrcView.currentTimeProperty().unbind();
        lrcView.setCurrentTime(Duration.ZERO);
        audioSpectrum.setMagnitudes(EMPTY_ARRAY);
    }



}
