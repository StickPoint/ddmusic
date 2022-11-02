package com.stickpoint.ddmusic.page.controller;

import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.controls.RXLineButton;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
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

    public RXCarousel sceneryCarousel;
    public RXLineButton recommend;
    public HBox indexMenuBar;
    public RXLineButton recommend1;
    public RXLineButton recommend2;
    public ScrollPane scrollPane;
    public AnchorPane centerPane;

    @FXML
    public void initialize() throws URISyntaxException {
        ObservableList<RXCarouselPane> paneList = sceneryCarousel.getPaneList();
        RXCarouselPane rxCarouselPane1 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/1.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane2 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/2.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane3 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/1.png")).toURI().toString())));
        RXCarouselPane rxCarouselPane4 = new RXCarouselPane(new Pane(new ImageView(Objects.requireNonNull(getClass().getResource("/img/2.png")).toURI().toString())));
        paneList.add(rxCarouselPane1);
        paneList.add(rxCarouselPane2);
        paneList.add(rxCarouselPane3);
        paneList.add(rxCarouselPane4);
        AnimAround animAround = new AnimAround(true);
        sceneryCarousel.setCarouselAnimation(animAround);
        recommend.setStyle("-fx-font-size: 18px");
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
            node.setStyle("-fx-font-size: 14px");

        });
        // 首先获得当前被点击的node 然后根据当前被点击的node进行设置
        RXLineButton menuButton = (RXLineButton) mouseEvent.getSource();
        menuButton.setStyle("-fx-font-size: 18px;");
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
}
