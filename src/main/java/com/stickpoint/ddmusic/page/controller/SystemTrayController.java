package com.stickpoint.ddmusic.page.controller;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.AppEnums;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * description: SystemTrayController
 *
 * @ClassName : SystemTrayController
 * @Date 2022/11/14 18:22
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SystemTrayController {

    /**
     * 返回首页区域HBox
     */
    @FXML
    public HBox openOriginalPage;

    /**
     * 初始化方法
     */
    @FXML
    public void initialize(){
        initOpenOriginalPage();
    }

    /**
     * 初始化监听 -> 还原页面显示的按钮
     * 当点击了返回首页，执行返回首页任务
     * 从缓存中取出首页节点，执行首页出栈
     */
    private void initOpenOriginalPage() {
        openOriginalPage.setOnMouseClicked(event -> {
            Stage mainStage = (Stage) SystemCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STAGE.getInfoValue());
            mainStage.show();
        });
    }

}
