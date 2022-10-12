package com.stickpoint.ddmusic.page;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.router.PageEnums;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fntp
 */
public class HomePageController {
    @FXML
    private StackPane centerView;
    @SuppressWarnings("exports")
	@FXML
    public Pane myMusicPlayHistory;
    @SuppressWarnings("exports")
	@FXML
    public Pane myMusicFavorite;
    @SuppressWarnings("exports")
	@FXML
    public Pane myMusicDownloadManager;
    @SuppressWarnings("exports")
	@FXML
    public Pane myMusicLocalMusic;
    @SuppressWarnings("exports")
	@FXML
    public VBox myMusicContainer;
    /**
     * 关闭主窗口按钮
     */
    @SuppressWarnings("exports")
	@FXML
    public Region homePageClose;
    /**
     * 节点缓存集合
     */
    private static final List<Node> NODE_LIST = new ArrayList<>();
    @SuppressWarnings("exports")
	@FXML
    public HBox userInfoContainer;
    @SuppressWarnings("exports")
	@FXML
    public AnchorPane homePagePlayer;
    /**
     * 音乐播放组件
     */
    public PlayerComponentController playerComponentController;
	/**
     * 日志工具
     */
    private static final Logger LOGGER = SystemCache.logger;
    /**
     * 首页面板-mainPage
     */
    @SuppressWarnings("exports")
	@FXML
    public BorderPane mainPage;
    /**
     * 额外菜单面板
     */
    @SuppressWarnings("exports")
	public ContextMenu userInfoCardContext;
    /**
     * 用户图像
     */
    @SuppressWarnings("exports")
	@FXML
    public RXAvatar userAvatar;
    /**
     * 音乐播放播放器
     */
    @SuppressWarnings("unused")
	private MediaPlayer player;

    /**
     * 初始化的时候，初始化音乐播放组件
     */
    public HomePageController() {
        // 初始化音乐播放组件
        playerComponentController = new PlayerComponentController();
    }
   
   

    /**
     * 菜单切换Style效果
     * 按钮点击事件
     */
    @FXML
    public void changeBackgroundStyle() {
        ObservableList<Node> myMusicContainerChildren = myMusicContainer.getChildren ();
        // 第一个菜单不参与高亮显示
        myMusicContainerChildren.forEach (node -> node.setOnMouseClicked (event -> {
            // 前面都是一直在获取元素 获取到了 直接调用API 原子动作分割
            setMenuPaneSelectedBackgroundStyle (node);
        }));
    }

    /**
     * 设置菜单选项选中时候的背景效果
     * @param node 节点
     */
    @FXML
    public void setMenuPaneSelectedBackgroundStyle(@SuppressWarnings("exports") Node node){
        if (node.getId().equals(InfoEnums.LEFT_TAB_MENU.getInfoContent())){
            // 如果是菜单的话那就点击了按压了，hover也没有效果
            node.setStyle(null);
        }else{
            // 否则不是菜单按钮，那么直接点击就好了
            NODE_LIST.addAll (myMusicContainer.getChildren ());
            // 外层节点
            node.setStyle ("-fx-background-color: rgba(243, 243, 243, 0.99);" +
                    "    -fx-effect: dropshadow(three-pass-box, #D9D9D9, 5.0,0,0, 0);" +
                    "    -fx-cursor: hand;");
            Pane currentNode = (Pane) node;
            // 获取菜单子节点
            Node currentLittleNode = currentNode.getChildren ().get (2);
            currentLittleNode.setStyle (" -fx-background-color: rgba(215, 0, 15);");
            Node selectedTextNode = currentNode.getChildren().get(1);
            // 设置菜单文本高亮
            if(Objects.nonNull(selectedTextNode.getId())&&selectedTextNode.getId().equals(InfoEnums.LEFT_TAB_MENU_ITEM_MENU_TEXT.getInfoContent())) {
                // 菜单文字高亮
                selectedTextNode.setStyle("-fx-text-fill: black");
            }
            boolean remove = NODE_LIST.remove (node);
            // 处理除了选中了的menuPane之外的Pane
            if (remove) {
                NODE_LIST.forEach (child->{
                    Pane menuItemPane = (Pane) child;
                    // 菜单Pane透明
                    menuItemPane.setStyle ("-fx-background-color: transparent");
                    menuItemPane.getChildren().forEach(itemNode->{
                        if (Objects.nonNull(itemNode.getId())&&itemNode.getId().equals(InfoEnums.LEFT_TAB_MENU_ITEM_LITTLE_PANE.getInfoContent())) {
                            // 菜单左边Pane透明
                            itemNode.setStyle ("-fx-background-color: transparent");
                        }
                        if(Objects.nonNull(itemNode.getId())&&itemNode.getId().equals(InfoEnums.LEFT_TAB_MENU_ITEM_MENU_TEXT.getInfoContent())) {
                            // 菜单文字高亮
                            itemNode.setStyle("-fx-text-fill: #7f7f7f");
                        }
                    });
                });
            }
            NODE_LIST.clear ();
        }
    }
    
	/**
	 * 关闭home主页
	 * @param mouseEvent 鼠标事件
	 */
    public void closeHomePage(MouseEvent mouseEvent) {
        //LOGGER.log(Level.INFO,"用户点击了关闭按钮，点击按钮的位置是：X-{},Y-{}",mouseEvent.getScreenX(),mouseEvent.getScreenY());
        // 系统关闭
        System.exit(0);
    }

    /**
     * 展示用户的历史听歌信息card
     * @param event 信息卡
     */
    @FXML
    void showUserInfoCard(MouseEvent event) {
        Bounds bounds = userAvatar.localToScreen(userAvatar.getBoundsInLocal());
        if (Objects.isNull(userInfoCardContext)){
           userInfoCardContext = new ContextMenu(new SeparatorMenuItem());
           String name = event.getEventType().getName();
           LOGGER.log(Level.INFO,name);
           // TODO 这里应该是将他封装好然后传递两个参数然后进行展示
            FXMLLoader accumulateLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.ACCUMULATE_PANE.getRouterId());
            Parent load = accumulateLoader.getRoot();
            userInfoCardContext.getScene().setRoot(load);
           userInfoCardContext.show(findStage(),bounds.getMaxX() - 135,bounds.getMaxY() + 10);
       }else {
           userInfoCardContext.show(findStage(),bounds.getMaxX() - 135,bounds.getMaxY() + 10);
       }
    }

    /**
     * 关闭用户信息卡
     */
    @FXML
    void closeUserInfoCard(){
        // 直接让这个context隐藏即可
        userInfoCardContext.hide();
    }

    /**
     * 通过当前容器获得当前Stage进而设置区域显示
     * @return 返回一个stage
     */
    private Stage findStage() {
        return (Stage) mainPage.getScene().getWindow();
    }
}