package com.stickpoint.ddmusic.page.component;
import com.stickpoint.ddmusic.common.enums.AppEnums;
import com.stickpoint.ddmusic.common.factory.SingletonFactory;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author fearless
 * @version v1.0
 * @description:
 * @date 2021/12/30 下午6:23
 */
public class DdMusicTray extends TrayIcon {

    public DdMusicTray(Image image, String tooltip, Region menu) {
        super(image, tooltip);
        Stage stage = SingletonFactory.getWeakInstace(Stage.class);
        StackPane pane = SingletonFactory.getWeakInstace(StackPane.class);
        stage.setScene(new Scene(pane));
        //去掉面板的标题栏
        stage.initStyle(StageStyle.TRANSPARENT);
        //监听面板焦点
        stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue)) {
                //如果失去焦点就隐藏面板
                stage.hide();
            }
        });
        //设置系统托盘图标为自适应
        this.setImageAutoSize(true);
        //添加组件到面板中
        pane.getChildren().add(menu);
        //设置面板的宽高
        stage.setWidth(menu.getPrefWidth());
        stage.setHeight(menu.getPrefHeight());
        //添加鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //getButton() 1左键 2中键 3右键
                if (e.getButton() == AppEnums.COMMON_NUMBER_THREE.getNumberInfo()) {
                    //在JavaFx的主线程中调用javaFx组件的方法
                    Platform.runLater(() -> {
                        //设置dialog的显示位置
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        double screenWidth = screenBounds.getWidth();
                        double screenHeight = screenBounds.getHeight();
                        // 家人们 这块位置有点不太对劲 TODO 需要大量测试window环境下菜单位置
                        stage.setX(screenWidth - stage.getWidth());
                        stage.setY(screenHeight - stage.getHeight());
                        //设置为顶层，否则在windows系统中会被底部任务栏遮挡
                        stage.setAlwaysOnTop(true);
                        //展示/隐藏
                        if (!stage.isShowing()) {
                            stage.show();
                        } else {
                            stage.hide();
                        }
                    });
                }
            }
        });
    }
}
