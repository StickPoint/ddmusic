package com.stickpoint.ddmusic.page.stage;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.factory.SingletonFactory;
import com.stickpoint.ddmusic.page.controller.HomePageController;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author fntp
 */
public class HomePageStage extends Stage {

    /**
     * 初始化桌面坐标位置--X--
     */
    private double oldStageX;
    /**
     * 初始化桌面坐标位置--Y--
     */
    private double oldStageY;
    /**
     * 初始化场景坐标位置--X--
     */
    private double oldScreenX;
    /**
     * 初始化场景坐标位置--Y--
     */
    private double oldScreenY;
    /**
     * 构建日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(HomePageStage.class);

    /**
     * 增加内存屏障 确保单例正确
     */
    private static volatile HomePageStage instance = null;

    public static HomePageStage getInstance() {
        if (instance == null) {
            synchronized (HomePageStage.class) {
                if (instance==instance){
                    instance = SingletonFactory.getWeakInstace(HomePageStage.class);
                }
            }
        }
        return instance;
    }

    /**
     * Creates a new instance of decorated {@code Stage}.
     *
     * @throws IllegalStateException if this constructor is called on a thread
     *                               other than the JavaFX Application Thread.
     */
    public HomePageStage() {
        // 初始化场景位置坐标与舞台位置坐标
        oldScreenX = 0;
        oldScreenY = 0;
        oldStageY = 0;
        oldStageX = 0;
        Scene homePageScene;
        // 在初始化init方法中load过之后，我们可以直接拿node了
        FXMLLoader homepageLoader = SystemCache.PAGE_MAP.get(PageEnums.HOMEPAGE.getRouterId());
        HomePageController homepageController = homepageLoader.getController();
        Parent parentNode = homepageLoader.getRoot();
        homePageScene = new Scene(parentNode, 950, 610);
        this.setScene(homePageScene);
        // 设置Stage监听
        log.info("场景监听部署完毕~");
        // 显示窗体拖拽轨迹
        mouseClickedToWindowsSlider(homepageController.headerAnchorPane);
        this.setOnCloseRequest(event -> {
            if(this.isIconified()){
                this.setIconified(false);
            }
        });
   }

    /**
     * 鼠标按下事件控制窗口移动方法
     * 当鼠标按下后，开始监听鼠标的移动操作，鼠标按下后
     * 主要实现了：鼠标移动，场景窗体跟随移动
     * @param node 传入一个node对象
     */
    private void mouseClickedToWindowsSlider(Node node){
        //  鼠标按下开始监听位置坐标
        node.setOnMousePressed(event -> {
            oldStageX = this.getX();
            oldStageY = this.getY();
            oldScreenX = event.getScreenX();
            oldScreenY = event.getScreenY();
        });
        // 鼠标拖拽开始监听移动位置坐标
        node.setOnMouseDragged(event -> {
            //新位置
            //拖拽前后的鼠标差值加上原始窗体坐标值
           this.setX(event.getScreenX() - oldScreenX + oldStageX);
           this.setY(event.getScreenY() - oldScreenY + oldStageY);
        });
    }

    /**
     * 强制GC
     * @param stage 传入原始的stage强制gc
     */
    public void doShow(Stage stage){
        if (Objects.nonNull(stage)) {
            this.setTitle(stage.getTitle());
        }
        //去掉面板的标题栏
        this.initStyle(stage.getStyle());
        // 设置Stage的任务栏Logo
        this.getIcons().addAll(stage.getIcons());
        // 设置居中显示场景
        this.centerOnScreen();
        this.show();
    }

}
