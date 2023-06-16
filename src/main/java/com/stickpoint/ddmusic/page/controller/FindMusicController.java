package com.stickpoint.ddmusic.page.controller;
import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.controls.RXLineButton;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.page.component.ScrollPaneComponent;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
 * @BelongsProject: mydemo
 * @BelongsPackage: com.sinsy.mydemo
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
     * 顶部线条按纽 后续可能会更换
     * 推荐一
     */
    public RXLineButton recommend;
    /**
     * HBox组件
     */
    public HBox indexMenuBar;
    /**
     * 顶部线条按纽 后续可能会更换
     * 推荐二
     */
    public RXLineButton recommend1;
    /**
     * 顶部线条按纽 后续可能会更换
     * 推荐三
     */
    public RXLineButton recommend2;
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

    public HBox ddmusicRecommend;

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
        recommend.setStyle("-fx-font-size: 18px");
        showFindMusic();
        // 初始化推荐歌单的列表样式
        initDdMusicRecommendListStyle(ddmusicRecommend);
    }

    /**
     * 初始化让发现音乐在最前面
     */
    private void showFindMusic(){
        ScrollPane finaMusicMenu = ScrollPaneComponent.createCommonScrollPaneRoot(findMusicRootNode);
        finaMusicMenu.toFront();
        SystemCache.CENTER_VIEW_PAGE_LIST.add(finaMusicMenu);
    }

    /**
     * 创建一个选择菜单的方法
     * @param mouseEvent 点击事件
     */
    @FXML
    public void selectMenu(MouseEvent mouseEvent) {
        FXMLLoader findMusicLoader = SystemCache.PAGE_MAP.get(PageEnums.FIND_MUSIC.getRouterId());
        ObservableMap<String, Object> namespace = findMusicLoader.getNamespace();
        if (Objects.isNull(indexMenuBar)) {
            indexMenuBar = (HBox) namespace.get("indexMenuBar");
        }
        ObservableList<Node> childrenList = indexMenuBar.getChildren();
        childrenList.forEach(item-> {
            Pane pane = (Pane) item;
            RXLineButton node = (RXLineButton) pane.getChildren().get(0);
            node.setStyle("-fx-font-size: 16px");

        });
        // 首先获得当前被点击的node 然后根据当前被点击的node进行设置
        RXLineButton menuButton = (RXLineButton) mouseEvent.getSource();
        menuButton.setStyle("-fx-font-size: 20px;");
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
