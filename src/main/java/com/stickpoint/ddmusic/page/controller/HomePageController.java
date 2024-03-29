package com.stickpoint.ddmusic.page.controller;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.common.thread.DdThreadPollCenter;
import com.stickpoint.ddmusic.page.component.DdMusicTray;
import com.stickpoint.ddmusic.common.enums.AppEnums;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import com.stickpoint.ddmusic.page.stage.HomePageStage;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 *
 * @author fntp
 */
public class HomePageController {
    /**
     * 左侧整个菜单Pane
     */
    @FXML
    public AnchorPane ddLeftPane;
    /**
     * 中间Stage区域内部含有一个StackPane容器
     */
    @FXML
    public AnchorPane ddCenterPane;
    /**
     * 顶部的Header-AnchorPane
     */
    @FXML
    public AnchorPane headerAnchorPane;
    /**
     * 最小化按纽
     */
    @FXML
    private Region minSize;

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
    /**
     * 中央显示容器
     */
    @FXML
    private StackPane centerView;
    /**
     * 我的音乐-VBox父容器
     */
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
    /**
     * 我的音乐-用户信息HBox
     */
	@FXML
    public HBox userInfoContainer;
    /**
     * 我的音乐-主界面底部容器 将会外联两个单独的播放页面
     */
	@FXML
    public AnchorPane homePagePlayer;
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

    private NetEasyMusicServiceImpl netEasyMusicService;

    @FXML
    public void initialize(){
        changeMyMusicMenuBackgroundStyle();
        initSystemMenuList();
        this.netEasyMusicService = NetEasyMusicServiceImpl.getInstance();
        // 初始化
        initInnerComponent();
        // 初始化监听
        initMinSizeAddListener();
    }

    /**
     * 首页初始化的时候将首页的StackPane装载进入系统缓存中
     * 供给系统后续页面切换使用
     */
    private void initSystemMenuList() {
        // 存储入缓存中
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
        // 前面都是一直在获取元素 获取到了 直接调用API 原子动作分割
        myMusicContainerChildren.forEach (node ->
                node.setOnMouseClicked (event ->
                        setMenuPaneSelectedBackgroundStyle (node,myMusicContainer.getChildren())));
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
            // 处理除了选中了的menuPane之外的Pane 这个Pane包含三个部分（1）小Pane最左侧部分；（2）大Pane整体按钮；（3）菜单文字；
            if (remove) {
                NODE_LIST.forEach (child->{
                    log.info(child.getId());
                    Pane menuItemPane = (Pane) child;
                    // 菜单Pane透明
                    menuItemPane.styleProperty().unbind();
                    // 处理 （2）大Pane整体按钮； 鼠标放上效果
                    changeBackgroundOnHoverUsingBinding(menuItemPane);
                    menuItemPane.getChildren().forEach(itemNode->{
                        if (Objects.nonNull(itemNode.getId())&&itemNode.getId().equals(InfoEnums.LEFT_TAB_MENU_ITEM_LITTLE_PANE.getInfoContent())) {
                            // （1）小Pane最左侧部分； 菜单左边Pane透明
                            itemNode.setStyle ("-fx-background-color: transparent");
                        }
                        if(Objects.nonNull(itemNode.getId())&&itemNode.getId().equals(InfoEnums.LEFT_TAB_MENU_ITEM_MENU_TEXT.getInfoContent())) {
                            // （3）菜单文字； 菜单文字高亮
                            itemNode.setStyle("-fx-text-fill: #7f7f7f");
                        }
                    });
                });
            }
            addOtherClickEventForMenuPane(node);
            NODE_LIST.clear ();
        }
    }

    /**
     * 更改Node的Hover时机的样式
     * @param node node节点，传入一个待修改样式的组件对象
     */
    private void changeBackgroundOnHoverUsingBinding(Node node) {
        // 对于非菜单按钮不显示大按钮样式
        if (Objects.nonNull(node.getId())){
            node.styleProperty()
                    .bind(Bindings.when(node.hoverProperty())
                            .then("-fx-background-color: rgba(243, 243, 243, 0.99);-fx-effect: dropshadow(three-pass-box, #D9D9D9, 5.0,0,0, 0);-fx-cursor: hand;")
                            .otherwise("-fx-background-color: transparent"));
        }
    }

    /**
     * 为特定的MenuPane设置额外的点击事件
     * 防止事件冒泡处理，只能在处理样式的同时去触发额外的点击事件
     * 这里JDK17中也无法使用 Switch-Enum
     * --------------------------------
     * （1）在线音乐-发现音乐-点击事件
     * （2）在线音乐-热门金曲点击事件
     * （3）在线音乐-共享中心-点击事件
     * （4）在线音乐-音浪磁场-点击事件
     * --------------------------------
     * （5）我的音乐-本地下载-点击事件
     * （6）我的音乐-我的收藏-点击事件
     * （7）我的音乐-播放历史-点击事件
     * --------------------------------
     * （8）我的歌单-外部歌单-点击事件
     * --------------------------------
     * 这么做有一个很大的好处，就是将页面加载耦合度降到最低
     * 不需要考虑子页面加载需要在父页面容器加载完成之前加载好
     * 让子页面与父页面同时加载，然后需要使用子页面的时候再添加显示，
     * 并且只添加一次，如果存在该节点将不会重复添加而是直接置顶显示子页面
     * @param node 传入一个带修添加事件的组件
     */
    private void addOtherClickEventForMenuPane(Node node) {
        if (AppEnums.FIND_MUSIC_PANE.getInfoValue().equals(node.getId())) {
            // 先去centerView中去取 按钮id与页面root节点的cssId保持一致就完事 如果根据按钮id 在centerView中找到了界面的rootNode节点id那么说明是存在这个子页面的
            Optional<Node> optionalNode = centerView.getChildren().stream().filter(itemNode -> itemNode.getId().equals(node.getId())).findFirst();
            // 如果页面存在
            if (optionalNode.isPresent()) {
                // 直接置顶页面
                log.info("当前页面：{} 已经存在，已执行置顶操作~",node.getId());
                optionalNode.get().toFront();
            }else {
                log.error("当前页面：{} 显示失败！",node.getId());
            }
        }
        log.info("点击了：{}",node.getId());
        // 如果点击的是【我的音乐-本地下载】centerView跳转dao下载本地
        if (AppEnums.MY_MUSIC_LOCAL_DOWNLOAD.getInfoValue().equals(node.getId())) {
            // 先去centerView中去取 按钮id与页面root节点的cssId保持一致就完事 如果根据按钮id 在centerView中找到了界面的rootNode节点id那么说明是存在这个子页面的
            Optional<Node> optionalNode = centerView.getChildren().stream().filter(itemNode -> itemNode.getId().equals(node.getId())).findFirst();
            // 如果页面存在
            if (optionalNode.isPresent()) {
                // 直接置顶页面
                log.info("当前页面：{} 已经存在，已执行置顶操作~",node.getId());
                optionalNode.get().toFront();
            }else {
                log.error("当前页面：{} 显示失败！",node.getId());
            }
        }

        if (AppEnums.MY_FAVORITE_PANE.getInfoValue().equals(node.getId())) {
            // TODO 执行我的收藏界面置顶显示

        }

        if (AppEnums.PLAY_HISTORY_PANE.getInfoValue().equals(node.getId())) {
            // TODO 执行播放历史界面置顶显示
        }
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
            FXMLLoader accumulateLoader = SystemCache.PAGE_MAP.get(PageEnums.ACCUMULATE_PANE.getRouterId());
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
        FXMLLoader searchResultLoader = SystemCache.PAGE_MAP.get(PageEnums.SEARCH_RESULT_PAGE.getRouterId());
        SearchMusicResultController searchMusicResultController = searchResultLoader.getController();
        // 使用Property属性绑定上数据
        if (!searchMusicResultController.searchedMusicName.textProperty().isBound()) {
            searchMusicResultController.searchedMusicName.textProperty().bindBidirectional(searchKey.textProperty());
        }
        // 初始化的时候加载过
        VBox rootNode = searchResultLoader.getRoot();
        // 看看当前最前面的是不是搜索结果
        Node frontNode = centerView.getChildren().get(centerView.getChildren().size() - 1);
        // 如果是centerView中最前面的view是搜索结果
        if (frontNode.getId().equals(rootNode.getId())){
            // 如果id一致，那么说明当前搜索结果页面是在最上面，只需要刷新Data就可以了
            log.info("当前页面{}未切换，刷新Data数据！",frontNode.getId());
            flushData(searchMusicResultController);
        }else {
            // 不一致就是表示当前搜索页面不在最上面 先看看有没有这个节点
           if (!centerView.getChildren().contains(rootNode)){
               // 没有的话先加进去
               centerView.getChildren().add(rootNode);
           }
           // 然后设置一下置顶
            rootNode.toFront();
            // 最后刷新数据
            flushData(searchMusicResultController);
        }
    }

        private void flushData(SearchMusicResultController searchMusicResultController){
        // 获取当前搜索的字符串
        if (Objects.nonNull(searchKey.getText())) {
            RequestBaseInfoVO requestInfo = new RequestBaseInfoVO();
            requestInfo.setSearchKey(searchKey.getText());
            // 执行搜索
            Callable<List<? extends AbstractDdMusicEntity>> searchResultList = () -> netEasyMusicService.searchMusicList(requestInfo);
            // 刷新UI
            DdThreadPollCenter.doDdMusicSearchTask(searchResultList, searchMusicResultController::initTableData);
        }
    }

    /**
     * 初始化系统托盘
     */
    private void initInnerComponent() {
        FXMLLoader sysTrayLoader = SystemCache.PAGE_MAP.get(PageEnums.SYSTEM_TRAY.getRouterId());
        Region region = sysTrayLoader.getRoot();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo-ddmusic.png"));
        Platform.runLater(() -> {
            DdMusicTray myTray = new DdMusicTray(image,AppEnums.APPLICATION_NAME.getInfoValue(),region);
            SystemCache.NODE_MAP.put(AppEnums.APPLICATION_TRAY.getInfoValue(), myTray);
            log.info("系统托盘初始化成功！");
        });
    }

    /**
     * 点击了最小化按钮的点击监听事件
     * TODO 注意 此处有大坑 Platform.setImplicitExit(false);
     *  方法的执行意义是告诉 JavaFX 平台，当用户关闭主窗口时，
     *  不要自动退出应用程序。这样做的目的是为了实现将主页面隐藏到系统托盘，
     *  并在系统托盘中显示应用图标和菜单的功能，因为如果应用程序在关闭主窗口时自动退出，
     *  那么就无法在系统托盘中继续显示应用程序的图标和菜单了。
     *  需要注意的是，当需要退出应用程序时，必须手动调用 Platform.exit() 方法来退出程序。
     */
    private void initMinSizeAddListener() {
        minSize.setOnMouseClicked(event -> {
            DdMusicTray myTray = (DdMusicTray) SystemCache.NODE_MAP.get(AppEnums.APPLICATION_TRAY.getInfoValue());
            try {
                SystemTray.getSystemTray().remove(myTray);
                SystemTray.getSystemTray().add(myTray);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            // 最后将主页面隐藏 Finally, hide the main page
            Stage mainStage = HomePageStage.getInstance();
            // 不退出主程序
            Platform.setImplicitExit(false);
            // 主界面先关闭
            mainStage.close();
        });
    }

    @FXML
    private void showDownloadLocalPage() {
        FXMLLoader downloadLocalLoader = SystemCache.PAGE_MAP.get(PageEnums.DOWNLOAD_LOCAL.getRouterId());
        AnchorPane pane = downloadLocalLoader.getRoot();
        pane.toFront();
    }

}
