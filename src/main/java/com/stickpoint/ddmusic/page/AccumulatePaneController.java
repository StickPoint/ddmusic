package com.stickpoint.ddmusic.page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.page
 * @Author: fntp
 * @CreateTime: 2022-09-27  20:38
 * @Description: TODO
 * @Version: 1.0
 */
public class AccumulatePaneController {
    /**
     * 累计使用时间时长
     */
    @SuppressWarnings("exports")
	@FXML
    public Label accumulateMinute;
    /**
     * 累计使用天数时长
     */
    @SuppressWarnings("exports")
	@FXML
    public Label accumulateDate;
    /**
     * 累计情况统计额外面板根节点
     */
    @SuppressWarnings("exports")
	@FXML
    public Pane accumulatePane;
}
