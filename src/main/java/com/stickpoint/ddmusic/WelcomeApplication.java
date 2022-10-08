package com.stickpoint.ddmusic;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.utils.ThreadUtil;
import com.stickpoint.ddmusic.page.HomePageStage;
import com.stickpoint.ddmusic.router.PageEnums;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description 欢迎页
 */
public class WelcomeApplication extends Application {
	/**
	 * 构建日志工具
	 */
    private static final Logger LOGGER = Logger.getGlobal();

    /**
     * 加载信息
     */
    private static Label loadingMessage;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     *  Be careful !!!
     *                     Use the MediaPlayer Media a =new Media("uri")
     *                     (1) file:///+absolutePath
     *                     (2) getClass().getResource("fileName")
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws URISyntaxException {
        Media welcomeVideo = new Media(Objects.requireNonNull(getClass().getResource("/media/ddmusic.mp4")).toURI().toString());
        MediaPlayer player = new MediaPlayer(welcomeVideo);
        MediaView imageView = new MediaView(player);
        loadingMessage = new Label();
        loadingMessage.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(loadingMessage, 10.0);
        AnchorPane.setBottomAnchor(loadingMessage, 10.0);
        AnchorPane page = new AnchorPane();
        page.getChildren().addAll(imageView , loadingMessage);
        primaryStage.setTitle("顶点音乐");
        primaryStage.setScene(new Scene(page));
        primaryStage.setWidth(700);
        primaryStage.setHeight(400);
        // 定义Stage具有纯白色背景且没有装饰的样式。
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("https://sinsy.oss-cn-beijing.aliyuncs.com/img/ddmusic-logo.png"));
        primaryStage.show();
        // 欢迎页开始展示了之后，开始剪裁欢迎页的视频尺寸大小
        player.play();
        player.setOnReady(() -> {
            imageView.setFitWidth(primaryStage.getWidth());
            imageView.setFitHeight(primaryStage.getHeight());
        });
        ExecutorService pool = ThreadUtil.getPool();
        pool.submit(() -> {
            initApplication();
            Platform.runLater(() -> {
                try {
                    HomePageStage home = new HomePageStage();
                    primaryStage.close();
                    LOGGER.info("当前界面正在执行关闭操作~");
                    home.show();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * 初始化软件基础设施
     * （1）初始化UI界面
     * （2）初始化数据库软件
     * （3）初始化云端连接
     * （4）初始化获得本地信息
     * （5）初始化本地页面装载
     */
    private static void initApplication(){
        try {
            // 其他操作
            showApplicationInitsInfo("初始化目录...");
            Thread.sleep(1500);
//            showApplicationInitsInfo("初始化系统配置...");
//            Thread.sleep(1500);
//            showApplicationInitsInfo("版本检测...");
//            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 显示软件初始化过程中的交互提示信息
     * @param info 提示信息
     */
    public static void showApplicationInitsInfo(String info) {
        Platform.runLater(() -> loadingMessage.setText(info));
    }

    /**
     * 首页关闭的时候，整个程序直接关闭
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed. An application may
     * override this method to perform initialization prior to the actual starting
     * of the application.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is not called on the JavaFX Application Thread. An
     * application must not construct a Scene or a Stage in this
     * method.
     * An application may construct other JavaFX objects in this method.
     * </p>
     *
     * 整个项目的初始化
     * （1）FXML文件初始化
     * （2）播放器状态设置初始化
     * （3）组件加载初始化
     * （4）网络初始化
     * （5）db初始化
     * （6）日志初始化
     *
     * 整个项目的fxml文件
     * 初始化，直接在这里进行 然后后续都使用缓存
     *
     * @throws Exception if something goes wrong
     */
    @Override
    public void init() throws Exception {
        // 装载FXML文件: （1）首页
        FXMLLoader homePageLoader = new FXMLLoader(PageEnums.HOMEPAGE.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.HOMEPAGE.getRouterId(),  homePageLoader.load());
        LOGGER.log(Level.INFO,"首页装载成功！");
        //（2）播放组件
        FXMLLoader playerComponentLoader = new FXMLLoader(PageEnums.PLAYER_COMPONENT.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.PLAYER_COMPONENT.getRouterId(), playerComponentLoader.load());
        LOGGER.log(Level.INFO,"播放组件页面装载成功！");
        //（3）累计统计情况额外组件
        FXMLLoader accumulatePaneLoader = new FXMLLoader(PageEnums.ACCUMULATE_PANE.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.ACCUMULATE_PANE.getRouterId(), accumulatePaneLoader.load());
        LOGGER.log(Level.INFO,"累计统计情况额外组件页面装载成功！");
        //（4）播放控制组件
        FXMLLoader musicControlLoader = new FXMLLoader(PageEnums.MUSIC_CONTROL.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.MUSIC_CONTROL.getRouterId(), musicControlLoader.load());
        LOGGER.log(Level.INFO,"播放控制组件页面装载成功！");
        
        // （1）播放器状态初始化
        SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(), 
    			InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent());
    }

    /**
     * 程序启动的入口
     * @param args 参数是随机参数 无实际意义
     */
    public static void main(String[] args) {
    	// 启动顶点音乐（Application的launch方法是建立在继承了Application的基础上的）
        Application.launch(args);
    }

}
