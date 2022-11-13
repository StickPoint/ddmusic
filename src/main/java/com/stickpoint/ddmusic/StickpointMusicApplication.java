package com.stickpoint.ddmusic;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.utils.ThreadUtil;
import com.stickpoint.ddmusic.page.stage.HomePageStage;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * @author fntp
 * @version v0.0.3
 * @date 2022/9/11
 * @description 欢迎页
 */
public class StickpointMusicApplication extends Application {
	/**
	 * 构建日志工具
	 */
    private static final Logger log = LoggerFactory.getLogger(StickpointMusicApplication.class);

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
        MediaView mediaView = new MediaView(player);
        loadingMessage = new Label();
        loadingMessage.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(loadingMessage, 10.0);
        AnchorPane.setBottomAnchor(loadingMessage, 10.0);
        AnchorPane page = new AnchorPane();
        page.getChildren().addAll(mediaView , loadingMessage);
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
            mediaView.setFitWidth(primaryStage.getWidth());
            mediaView.setFitHeight(primaryStage.getHeight());
        });
        ExecutorService pool = ThreadUtil.getPool();
        pool.submit(() -> {
            initApplication();
            Platform.runLater(() -> {
                try {
                    HomePageStage home = new HomePageStage();
                    primaryStage.hide();
                    log.info("界面已由欢迎页面跳转至主页面~");
                    home.show();
                } catch (Exception e) {
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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
     * 整个项目的fxml文件
     * 初始化，直接在这里进行 然后后续都使用缓存
     *
     */
    @Override
    public void init() {
        // 系统内部配置优先装载 （1）播放器状态初始化
        SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(), InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent());
        // 装载FXML文件: （1）首页
        FXMLLoader homePageLoader = new FXMLLoader(PageEnums.HOMEPAGE.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.HOMEPAGE.getRouterId(),  homePageLoader);
        log.info("首页装载成功！");
        //（2）播放页面
        FXMLLoader playerComponentLoader = new FXMLLoader(PageEnums.PLAYER_COMPONENT.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.PLAYER_COMPONENT.getRouterId(), playerComponentLoader);
        log.info("播放组件页面装载成功！");
        //（3）累计统计情况额外页面
        FXMLLoader accumulatePaneLoader = new FXMLLoader(PageEnums.ACCUMULATE_PANE.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.ACCUMULATE_PANE.getRouterId(), accumulatePaneLoader);
        log.info("累计统计情况额外组件页面装载成功！");
        //（4）播放控制页面
        FXMLLoader musicControlLoader = new FXMLLoader(PageEnums.MUSIC_CONTROL.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.MUSIC_CONTROL.getRouterId(), musicControlLoader);
        log.info("播放控制组件页面装载成功！");
        //（5）歌曲播放详情界面
        FXMLLoader playDetailPage = new FXMLLoader(PageEnums.PLAY_DETAIL_PAGE.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.PLAY_DETAIL_PAGE.getRouterId(), playDetailPage);
        //（6）近期播放列表页面--最近播放小页面
        FXMLLoader recentlyPlayListLoader = new FXMLLoader(PageEnums.RECENTLY_PLAY_LIST.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.RECENTLY_PLAY_LIST.getRouterId(),recentlyPlayListLoader);
        //（7）系统播放器额外组件-音量控制组件
        FXMLLoader soundControlLoader = new FXMLLoader(getClass().getResource("/fxml/soundControl.fxml"));
        SystemCache.FXML_LOAD_MAP.put(PageEnums.SOUND_CONTROL.getRouterId(),soundControlLoader);
        // 装载完毕所有页面之后 将逐步进行页面的初始化操作
        FXMLLoader findMusicLoader = new FXMLLoader(PageEnums.FIND_MUSIC.getPageSource());
        SystemCache.FXML_LOAD_MAP.put(PageEnums.FIND_MUSIC.getRouterId(), findMusicLoader);
        // 需要在中间区域显示的菜单页面需要在初始化的时候进行加载
        try {
            homePageLoader.load();
            findMusicLoader.load();
            playDetailPage.load();
            accumulatePaneLoader.load();
            musicControlLoader.load();
            recentlyPlayListLoader.load();
            playerComponentLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 等到所有的子页面都加载完毕了然后再统一添加到StackPane中，避免出现多层View在同一层显示的Bug
        StackPane centerView = (StackPane) SystemCache.CACHE_NODE.get(InfoEnums.HOME_PAGE_CENTER_VIEW_FX_ID.getInfoContent());
        centerView.getChildren().addAll(SystemCache.CENTER_VIEW_PAGE_LIST);
        Node findMusicPage = centerView.getChildren().filtered(node -> InfoEnums.FIND_MUSIC_SCROLL_PANE_CSS_ID.getInfoContent().equals(node.getId()))
                .get(InfoEnums.INDEX_ZERO.getNumberInfo());
        // 初始化的时候让他在最前面
        findMusicPage.toFront();
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
