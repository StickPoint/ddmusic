package com.stickpoint.ddmusic.page.controller;
import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.page.component.ScrollPaneComponent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URISyntaxException;
import java.util.Objects;

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
    public void initialize() throws URISyntaxException {
        ObservableList<RXCarouselPane> paneList = sceneryCarousel.getPaneList();
        RXCarouselPane rxCarouselPane1 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/1.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane2 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/2.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane3 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/1.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane4 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/2.png")).toURI().toString())));
        rxCarouselPane1.setTranslateY(-25);
        rxCarouselPane2.setTranslateY(-25);
        rxCarouselPane3.setTranslateY(-25);
        rxCarouselPane4.setTranslateY(-25);
        paneList.add(rxCarouselPane1);
        paneList.add(rxCarouselPane2);
        paneList.add(rxCarouselPane3);
        paneList.add(rxCarouselPane4);
        AnimAround animAround = new AnimAround(true);
        sceneryCarousel.setCarouselAnimation(animAround);
        // 初始化推荐歌单的列表样式
        initDdMusicRecommendListStyle(hotMusicList);
        initDdMusicRecommendListStyle(musicWaveList);
        initDdMusicRecommendListStyle(dailyRecommendList);
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
     * @param hBox 一个box列表
     */
    private void initDdMusicRecommendListStyle(HBox hBox) {
        ObservableList<Node> children = hBox.getChildren();
        children.forEach(item->{
            // 每一个item都是一个VBox，每一个VBox都包含两个孩子节点，一个是图片，一个是歌单名称
            VBox cacheItem = (VBox) item;
            ObservableList<Node> cacheItemChildren = cacheItem.getChildren();
            // 每一个音乐播放列表封面
            ImageView imageView = (ImageView) cacheItemChildren.get(0);
            // 变型封面
            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imageView.setClip(clip);
            log.info("初始化推荐歌单成功~");
        });
    }
}
