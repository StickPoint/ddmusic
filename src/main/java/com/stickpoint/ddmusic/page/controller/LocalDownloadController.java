package com.stickpoint.ddmusic.page.controller;

import com.stickpoint.ddmusic.common.cache.SystemCache;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地下载控制器
 * @author puye(0303)
 * @since 2023/6/28
 */
public class LocalDownloadController {
    /**
     * 下载完成按钮
     */
    @FXML
    public HBox downloadFinished;
    /**
     * 下载中按钮
     */
    @FXML
    public HBox downloadinghBox;
    /**
     * 顶部HBox菜单按钮
     */
    @FXML
    public HBox headerBoxMenu;
    /**
     * 节点缓存集合
     */
    private static final List<Node> NODE_LIST = new ArrayList<>(3);
    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(LocalDownloadController.class);
    /**
     * 本地下载的rootPane
     */
    @FXML
    public AnchorPane localDownloadPane;

    @FXML
    public void initialize(){
        initLocalDownloadPageIntoChildList();
        changeStyleWithClicked();
    }

    /**
     * 初始化的时候将节点塞入到centerView将会现实的视图中去
     */
    private void initLocalDownloadPageIntoChildList(){
        SystemCache.CENTER_VIEW_PAGE_LIST.add(localDownloadPane);
    }

    @FXML
    public void changeStyleWithClicked() {
        ObservableList<Node> children = headerBoxMenu.getChildren();
        children.forEach(item-> item.setOnMouseClicked(event -> changeBackGroundColor(item,children)));
        log.info("size : {}",NODE_LIST.size());
    }

    /**
     * 绑定点击事件让HBox点击的时候背景颜色进行动态变化
     * 每次传入一个Item 然后分为两种情况
     * （1）处理点击了的按钮情况；
     * （2）处理未被点击的按钮的情况（也就是点击按钮之外的按钮）
     * 当点击了三个中的一个，被点击的节点需要显示样式
     * 其他两个需要隐藏样式
     */
    private void changeBackGroundColor(Node item,ObservableList<Node> childrenList) {
        NODE_LIST.addAll(childrenList);
        item.styleProperty().unbind();
        item.setStyle("-fx-background-color: rgb(236, 65, 65);-fx-background-radius: 25px; -fx-border-radius: 25px;");
        HBox cacheItem = (HBox) item;
        Node node = cacheItem.getChildren().get(0);
        node.styleProperty().unbind();
        node.setStyle("-fx-text-fill: rgb(246,246,246);");
        // 处理未被点击的按钮
        boolean removeResult = NODE_LIST.remove(item);
        if (removeResult) {
            // 删除成功，就剩下未被点击的HBox
            NODE_LIST.forEach(child->{
                child.styleProperty().unbind();
                child.setStyle("-fx-background-color: white;-fx-border-color: #989797;-fx-background-radius: 25px;-fx-border-radius: 25px;");
                HBox cachehBox = (HBox) child;
                Node cacheLabel = cachehBox.getChildren().get(0);
                cacheLabel.styleProperty().unbind();
                cacheLabel.setStyle("-fx-text-fill: #5e5c5c");
            });
        }
        NODE_LIST.clear ();
    }
}
