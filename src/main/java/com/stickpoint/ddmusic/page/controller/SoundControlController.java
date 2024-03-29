package com.stickpoint.ddmusic.page.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

/**
 * description: SoundControlController
 *
 * @ClassName : SoundControlController
 * @Date 2022/10/11 17:08
 * @Author fntp
 * @PackageName com.stickpoint.ddmusic.page
 */
public class SoundControlController {

    @FXML
    public Slider soundSlider;
    @FXML
    public Label soundNum;
    public AnchorPane soundControl;
}
