package com.stickpoint.ddmusic.page.controller;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.ddmusic.StickpointMusicApplication;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.IMusicService;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.common.thread.DdThreadCenter;
import com.stickpoint.ddmusic.page.component.ScrollPaneComponent;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 *
 * @author fntp
 */
public class HomePageController {

    @FXML
    public Pane findMusic;

    @FXML
    public Pane hotMusic;

    @FXML
    public Pane shareCenter;

    @FXML
    public Pane radioCenter;

    @FXML
    public VBox myMusicListContainer;
    /**
     * 搜索按钮
     */
    @FXML
    public Region search;
    /**
     * 搜索的关键字
     */
    @FXML
    public TextField searchKey;

    @FXML
    private StackPane centerView;

	@FXML
    public Pane myMusicPlayHistory;

	@FXML
    public Pane myMusicFavorite;

	@FXML
    public Pane myMusicLocalMusic;

	@FXML
    public VBox myMusicContainer;

    /**
     * 关闭主窗口按钮
     */
	@FXML
    public Region homePageClose;
    /**
     * 节点缓存集合
     */
    private static final List<Node> NODE_LIST = new ArrayList<>();

	@FXML
    public HBox userInfoContainer;

	@FXML
    public AnchorPane homePagePlayer;
    /**
     * 音乐播放组件
     */
    public PlayerComponentController playerComponentController;
	/**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(HomePageController.class);
    /**
     * 首页面板-mainPage
     */
	@FXML
    public BorderPane mainPage;
    /**
     * 额外菜单面板
     */

	public ContextMenu userInfoCardContext;
    /**
     * 用户图像
     */

	@FXML
    public RXAvatar userAvatar;
    /**
     * 音乐播放播放器
     */
    @SuppressWarnings("unused")
	private MediaPlayer player;

    private IMusicService musicService;

    private DdThreadCenter threadCenter;

    /**
     * 初始化的时候，初始化音乐播放组件
     */
    public HomePageController() {
        // 初始化音乐播放组件
        playerComponentController = new PlayerComponentController();
    }

    @FXML
    public void initialize(){
        changeMyMusicMenuBackgroundStyle();
        initSystemMenuList();
        this.musicService = new NetEasyMusicServiceImpl();
        this.threadCenter = new DdThreadCenter("顶点音乐");
    }

    /**
     * 首页初始化的时候将首页的StackPane装载进入系统缓存中
     * 供给系统后续页面切换使用
     */
    private void initSystemMenuList() {
        SystemCache.CACHE_NODE.put(InfoEnums.HOME_PAGE_CENTER_VIEW_FX_ID.getInfoContent(),centerView);
    }

    /**
     * 菜单切换Style效果
     * 按钮点击事件
     */
    @FXML
    public void changeMyMusicMenuBackgroundStyle() {
        ObservableList<Node> myMusicContainerChildren = myMusicContainer.getChildren ();
        // 第一个菜单不参与高亮显示
        myMusicContainerChildren.forEach (node -> node.setOnMouseClicked (event -> {
            // 前面都是一直在获取元素 获取到了 直接调用API 原子动作分割
            setMenuPaneSelectedBackgroundStyle (node,myMusicContainer.getChildren());
        }));
    }

    /**
     * 设置菜单选项选中时候的背景效果
     * 注意：这里的css效果不是通过css文件控制的点击后的效果以及hover之后的效果，而是通过bind与方法事件实现的
     * 注意：由于这里是通过css绑定bind技术实现的css-hover效果，所以需要在bind之前先unbind
     * @param node 节点
     */
    @FXML
    public void setMenuPaneSelectedBackgroundStyle(Node node,ObservableList<Node> childrenList){
        if (node.getId().equals(InfoEnums.LEFT_TAB_MENU.getInfoContent())){
            // 如果是菜单的话那就点击了按压了，hover也没有效果
            node.styleProperty().unbind();
            node.setStyle(null);
        }else{
            // 否则不是菜单按钮，那么直接点击就好了
            NODE_LIST.addAll (childrenList);
            // 外层节点 先解除绑定的css效果
            node.styleProperty().unbind();
            // 然后手动设置效果无法绑定，因为点击事件是针对特定的node的不是固定的所以手动绑定设置css样式
            node.setStyle ("-fx-background-color: rgba(243, 243, 243, 0.99);" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(169, 168, 168, 0.41), 1.0,0,0, 0);"+
                    "    -fx-cursor: hand;");
            Pane currentNode = (Pane) node;
            // 获取菜单子节点
            Node currentLittleNode = currentNode.getChildren ().get (2);
            // 设置左侧竖直item背景效果
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
                    menuItemPane.styleProperty().unbind();
                    changeBackgroundOnHoverUsingBinding(menuItemPane);
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

    private void changeBackgroundOnHoverUsingBinding(Node node) {
        node.styleProperty()
                .bind(Bindings.when(node.hoverProperty())
                .then("-fx-background-color: rgba(243, 243, 243, 0.99);-fx-effect: dropshadow(three-pass-box, #D9D9D9, 5.0,0,0, 0);-fx-cursor: hand;")
                        .otherwise("-fx-background-color: transparent"));
    }

    /**
	 * 关闭home主页
     */
    public void closeHomePage() {
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
           log.info(name);
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

    /**
     * 执行音乐搜索
     */
    @FXML
    public void doSearch(){
        // 首先看看当前页面是不是在最上面
        // 从系统缓存节点中取出所有界面共享区域stackPane
        StackPane centerView = (StackPane) SystemCache.CACHE_NODE.get(InfoEnums.HOME_PAGE_CENTER_VIEW_FX_ID.getInfoContent());
        // 取出搜索结果页面
        FXMLLoader searchResultLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.SEARCH_RESULT_PAGE.getRouterId());
        SearchMusicResultController searchMusicResultController = searchResultLoader.getController();
        // 初始化的时候加载过
        VBox rootNode = searchResultLoader.getRoot();
        // 看看当前最前面的是不是搜索结果
        Node frontNode = centerView.getChildren().get(centerView.getChildren().size() - 1);
        if (frontNode.getId().equals(rootNode.getId())){
            // 如果id一致，那么说明当前搜索结果页面是在最上面，只需要刷新Data就可以了
            log.info("当前页面{}未切换，刷新Data数据！",frontNode.getId());
            flushData(searchMusicResultController);
        }else {
            // 不一致就是表示当前搜索页面不在最上面 先切换页面 再刷新data
            ScrollPane searchMusicResultScrollPane = ScrollPaneComponent.createCommonScrollPaneRoot(rootNode);
            centerView.getChildren().add(searchMusicResultScrollPane);
            searchMusicResultScrollPane.toFront();
            // 刷新数据
            flushData(searchMusicResultController);
        }
    }

    private void flushData(SearchMusicResultController searchMusicResultController){
        // 获取当前搜索的字符串
        if (Objects.nonNull(searchKey.getText())) {
            RequestBaseInfoVO requestInfo = new RequestBaseInfoVO();
            requestInfo.setSearchKey(searchKey.getText());
            // 执行搜索
            Callable<List<? extends AbstractDdMusicEntity>> searchResultList = () -> musicService.searchMusicList(requestInfo);
            List<? extends AbstractDdMusicEntity> abstractDdMusicEntities = threadCenter.doDdMusicSearchTask(searchResultList);
            // 刷新UI
            Platform.runLater(()-> searchMusicResultController.initTableData(abstractDdMusicEntities));
        }
    }
}
