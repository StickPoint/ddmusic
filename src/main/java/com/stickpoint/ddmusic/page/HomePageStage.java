package com.stickpoint.ddmusic.page;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.router.PageEnums;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.logging.Logger;

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
     * 初始化一个日志对象
     */
    private static final Logger log = Logger.getGlobal();

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
        //去掉面板的标题栏
        this.initStyle(StageStyle.TRANSPARENT);
        Scene homePageScene = new Scene(SystemCache.FXML_LOAD_MAP.get(PageEnums.HOMEPAGE.getRouterId()), 950, 610);
        this.setScene(homePageScene);
        // 设置Stage的任务栏Logo
        this.getIcons().add(new Image("https://sinsy.oss-cn-beijing.aliyuncs.com/img/ddmusic-logo.png"));
        // 设置居中显示场景
        this.centerOnScreen();
        // 设置Stage监听
        this.mouseClickedToWindowsSlider(homePageScene);
        // 显示窗体拖拽轨迹
        this.xProperty().addListener((observable, oldValue, newValue) -> log.info(String.format("当前窗口--X--轴历史坐标：%s，现在坐标%s",oldValue,newValue)));
        this.yProperty().addListener((observable, oldValue, newValue) -> log.info(String.format("当前窗口--Y--轴历史坐标：%s，现在坐标%s",oldValue,newValue)));
        // 初始化播放器
        this.initPlayerComponent();
    }

    /**
     * 鼠标按下事件控制窗口移动方法
     * 当鼠标按下后，开始监听鼠标的移动操作，鼠标按下后
     * 主要实现了：鼠标移动，场景窗体跟随移动
     * @param scene 传入一个场景scene对象
     */
    private void mouseClickedToWindowsSlider(Scene scene){
        //  鼠标按下开始监听位置坐标
        scene.setOnMousePressed(event -> {
            oldStageX = this.getX();
            oldStageY = this.getY();
            oldScreenX = event.getScreenX();
            oldScreenY = event.getScreenY();
        });
        // 鼠标拖拽开始监听移动位置坐标
        scene.setOnMouseDragged(event -> {
            //新位置
            //拖拽前后的鼠标差值加上原始窗体坐标值
           this.setX(event.getScreenX() - oldScreenX + oldStageX);
           this.setY(event.getScreenY() - oldScreenY + oldStageY);
        });
    }

    /**
     * 由于默认的组件位置是空白的一个AnchorPane 什么都没有
     * 现在通过初始化组件的形式加载其他额外的FXML文件
     * 初始化一个播放组件：
     * （1）由于在软件刚初始化的时候，所有的FXML文件都被装载到了系统内部，所以可以直接读取
     * （2）通过CSS-ID来选择器来选择组件
     */
    private void initPlayerComponent(){
        Parent homePage = SystemCache.FXML_LOAD_MAP.get(PageEnums.HOMEPAGE.getRouterId());
        AnchorPane homePagePlayer = (AnchorPane) homePage.lookup(InfoEnums.HOME_PAGE_PLAYER_CSS_ID.getInfoContent());
        Parent playerComponentRootNode = SystemCache.FXML_LOAD_MAP.get(PageEnums.PLAYER_COMPONENT.getRouterId());
        homePagePlayer.getChildren().add(playerComponentRootNode);
    }

}