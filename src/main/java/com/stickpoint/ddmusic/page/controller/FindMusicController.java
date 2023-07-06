package com.stickpoint.ddmusic.page.controller;
import com.google.gson.reflect.TypeToken;
import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.model.dd.DdRecommend;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.common.thread.DdThreadPollCenter;
import com.stickpoint.ddmusic.common.utils.JsonUtil;
import com.stickpoint.ddmusic.common.utils.SecurityUtil;
import com.stickpoint.ddmusic.page.component.ScrollPaneComponent;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.sinsy.ddmusic
 * @Author: fntp
 * @CreateTime: 2022-10-20  21:15
 * @Description:
 * @Version: 1.0
 */
public class FindMusicController {
    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(FindMusicController.class);
    /**
     * 发现音乐首页轮播图
     */
    public RXCarousel sceneryCarousel;
    /**
     * 滚动容器
     */
    public ScrollPane scrollPane;
    /**
     * 中央容器
     */
    public AnchorPane centerPane;
    /**
     * 发现音乐页面根节点
     */
    public AnchorPane findMusicRootNode;
    /**
     * 热门音乐
     */
    public HBox hotMusicList;
    /**
     * 音浪前线
     */
    public HBox musicWaveList;
    /**
     * 每日推荐
     */
    public HBox dailyRecommendList;

    @FXML
    public void initialize() {
        // 初始化轮播
        log.info("正在初始化轮播~");
        initCarousel();
        // 初始化推荐歌单的列表样式
        log.info("正在初始化歌单~");
        initDdMusicListStyle(hotMusicList);
        initDdMusicListStyle(musicWaveList);
        initDdMusicListStyle(dailyRecommendList);
        log.info("初始化歌单成功~");
        // 初始化的时候放在childList里面
        initFindMusicPageInToChildList();
    }

    /**
     * 初始化让发现音乐在最前面
     */
    private void initFindMusicPageInToChildList(){
        ScrollPane finaMusicMenu = ScrollPaneComponent.createCommonScrollPaneRoot(findMusicRootNode);
        // 只有发现音乐会在初始化的时候默认显示 其他的view都是直接添加到centerView
        finaMusicMenu.toFront();
        SystemCache.CENTER_VIEW_PAGE_LIST.add(finaMusicMenu);
    }

    /**
     * 原本设计想动态监听鼠标移入与移除动作同步更新UI动作，现在暂时先不用了
     * 后续如果样式不够美观，需要通过拖动条引导用户拖动就需要放开这个对拖动条的样式控制
     * 现在采用的方案是直接将拖动条全局隐藏了
     */
    @FXML
    @SuppressWarnings("unused")
    public void showSlider() {
        Node lookup = scrollPane.lookup(".scroll-bar:vertical");
        lookup.setStyle("");
    }

    /**
     * 原本设计想动态监听鼠标移入与移除动作同步更新UI动作，现在暂时先不用了
     * 后续如果样式不够美观，需要通过拖动条引导用户拖动就需要放开这个对拖动条的样式控制
     * 现在采用的方案是直接将拖动条全局隐藏了
     */
    @FXML
    @SuppressWarnings("unused")
    public void hideSlider() {
        Node lookup = scrollPane.lookup(".scroll-bar:vertical");
        lookup.setStyle("-fx-opacity:0;");
    }

    /**
     * 初始化Carousel轮播图
     */
    private void initCarousel() {
        String carouselJsonStr =  (String) SystemCache.APP_PROPERTIES.get(InfoEnums.APP_MUSIC_BANNER_LIST_DAILY.getInfoContent());
        List<DdRecommend> bannerList = null;
        if (Objects.nonNull(carouselJsonStr)) {
            String originalJsonStr = SecurityUtil.getOriginalStrFromBase64Str(carouselJsonStr);
            bannerList = JsonUtil.getGson().fromJson(originalJsonStr,new TypeToken<List<DdRecommend>>(){}.getType());
        }
        ObservableList<RXCarouselPane> paneList = sceneryCarousel.getPaneList();
        for (DdRecommend banner : bannerList) {
            ImageView imageView = new ImageView(new Image(banner.getPicUrl()));
            RXCarouselPane rxCarouselPane = new RXCarouselPane(new Pane(imageView));
            rxCarouselPane.setTranslateY(-25);
            paneList.add(rxCarouselPane);
        }
        AnimAround animAround = new AnimAround(true);
        sceneryCarousel.setCarouselAnimation(animAround);
    }

    /**
     * 初始化顶点音乐的顶点推荐列表的样式
     * 2023-06-15 忽略的代码：
     *             SnapshotParameters parameters = new SnapshotParameters();
     *             parameters.setFill(Color.TRANSPARENT);
     *             WritableImage image = imageView.snapshot(parameters, null);
     *             // remove the rounding clip so that our effect can show through.
     *             imageView.setClip(null);
     *             // apply a shadow effect.
     *             imageView.setEffect(new DropShadow(20, Color.BLACK));
     *             // store the rounded image in the imageView.
     *             imageView.setImage(image);
     *             【插眼】
     *             这段代码主要是为了修改推荐列表的显示样式，但是没有达到预期效果，暂时先不改动这里的样式，后续有机会在优化把
     * 初始化每日推荐音乐歌单列表页面样式
     * @param hBox 传入一个每日推荐的hBox对象
     */
    private void initDdMusicListStyle(HBox hBox) {
        ObservableList<Node> children = hBox.getChildren();
        String dailyMusicListJsonStr = switch (hBox.getId()) {
            case "musicWaveList" ->
                    (String) SystemCache.APP_PROPERTIES.get(InfoEnums.APP_MUSIC_WAVE_LIST_DAILY.getInfoContent());
            case "hotMusicList" ->
                    (String) SystemCache.APP_PROPERTIES.get(InfoEnums.APP_MUSIC_HOT_LIST_DAILY.getInfoContent());
            default ->
                    (String) SystemCache.APP_PROPERTIES.get(InfoEnums.APP_MUSIC_RECOMMEND_LIST_DAILY.getInfoContent());
        };
        List<DdRecommend> recommendList = null;
        if (Objects.nonNull(dailyMusicListJsonStr)) {
            String originalJsonStr = SecurityUtil.getOriginalStrFromBase64Str(dailyMusicListJsonStr);
            recommendList = JsonUtil.getGson().fromJson(originalJsonStr,new TypeToken<List<DdRecommend>>(){}.getType());
        }
        for (int i = 0; i < children.size(); i++) {
            // 每一个item都是一个VBox，每一个VBox都包含两个孩子节点，一个是图片，一个是歌单名称
            VBox cacheItem = (VBox) children.get(i);
            ObservableList<Node> cacheItemChildren = cacheItem.getChildren();
            // 每一个音乐播放列表歌曲封面
            ImageView imageView = (ImageView) cacheItemChildren.get(0);
            if (Objects.nonNull(recommendList)&&recommendList.size()==5) {
                imageView.imageProperty().set(new Image(recommendList.get(i).getPicUrl()));
                setOnMouseClickedListener(cacheItem,recommendList.get(i));
            }
            // 设置每一首音乐的歌名
            Label playListName = (Label) cacheItemChildren.get(1);
            playListName.setText(recommendList.get(i).getName());
            // 变型封面
            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imageView.setClip(clip);
        }
    }

    /**
     * 对每一个vBox进行监听
     * @param vBox 传入一个vBox
     * @param ddRecommend 传入一个顶点音乐对象
     */
    private void setOnMouseClickedListener(VBox vBox, DdRecommend ddRecommend) {
        // 当点击了vBox之后 跳转到歌单详情页面 设置监听范围
        vBox.setOnMouseClicked(event -> {
            // 根据传入的数据id请求歌单详情
            FXMLLoader playListDetailLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAY_LIST_DETAIL.getRouterId());
            PlayListDetailController detailController = playListDetailLoader.getController();
            VBox rootNode = playListDetailLoader.getRoot();
            // 看看当前最前面的是不是搜索结果
            StackPane centerView =  (StackPane) SystemCache.CACHE_NODE.get(InfoEnums.HOME_PAGE_CENTER_VIEW_FX_ID.getInfoContent());
            Node frontNode = centerView.getChildren().get(centerView.getChildren().size() - 1);
            // 暂存一下当前封面
            SystemCache.CURRENT_PLAY_LIST_COVER_URL.clear();
            SystemCache.CURRENT_PLAY_LIST_COVER_URL.add(ddRecommend.getPicUrl());
            // 如果是centerView中最前面的view是搜索结果
            if (frontNode.getId().equals(rootNode.getId())){
                // 如果id一致，那么说明当前搜索结果页面是在最上面，只需要刷新Data就可以了
                log.info("当前页面{}未切换，刷新Data数据！",frontNode.getId());
                flushData(ddRecommend.getId(), detailController);
            }else {
                // 不一致就是表示当前搜索页面不在最上面 先看看有没有这个节点
                if (!centerView.getChildren().contains(rootNode)){
                    // 没有的话先加进去
                    centerView.getChildren().add(rootNode);
                }
                // 然后设置一下置顶
                rootNode.toFront();
                // 最后刷新数据
                flushData(ddRecommend.getId(), detailController);
            }
        });
    }

    /**
     * 刷新数据
     * @param playListId 传入一个播放歌单id
     * @param playListDetailController 传入一个播放详情controller
     */
    private void flushData(String playListId, PlayListDetailController playListDetailController){
        // 获取当前搜索的字符串
        if (Objects.nonNull(playListId)) {
            // 执行搜索
            Callable<List<? extends AbstractDdMusicEntity>> searchResultList = () -> NetEasyMusicServiceImpl.getInstance().getPlayListInfoByPlayListId(playListId);
            // 刷新UI
            DdThreadPollCenter.doDdMusicSearchTask(searchResultList, playListDetailController::initTableData);
        }else {
            // TODO 弹窗 刷新数据出错： 歌单id为空
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
    }
}
