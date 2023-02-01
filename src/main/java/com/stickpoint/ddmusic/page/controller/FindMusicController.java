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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
 * @Description: TODO
 * @Version: 1.0
 */
public class FindMusicController {
    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(FindMusicController.class);
    public RXCarousel sceneryCarousel;
    public RXLineButton recommend;
    public HBox indexMenuBar;
    public RXLineButton recommend1;
    public RXLineButton recommend2;
    public ScrollPane scrollPane;
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
        FXMLLoader findMusicLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.FIND_MUSIC.getRouterId());
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

    @FXML
    public void showSlider() {
        Node lookup = scrollPane.lookup(".scroll-bar:vertical");
        lookup.setStyle("");
    }

    @FXML
    public void hideSlider() {
        Node lookup = scrollPane.lookup(".scroll-bar:vertical");
        lookup.setStyle("-fx-opacity:0;");
    }

    /**
     * 初始化顶点音乐的顶点推荐列表的样式
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
//            SnapshotParameters parameters = new SnapshotParameters();
//            parameters.setFill(Color.TRANSPARENT);
//            WritableImage image = imageView.snapshot(parameters, null);
//            // remove the rounding clip so that our effect can show through.
//            imageView.setClip(null);
//            // apply a shadow effect.
//            imageView.setEffect(new DropShadow(20, Color.BLACK));
//            // store the rounded image in the imageView.
//            imageView.setImage(image);
        });
    }
}
