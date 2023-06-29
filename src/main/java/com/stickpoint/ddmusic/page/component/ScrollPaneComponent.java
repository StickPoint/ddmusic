package com.stickpoint.ddmusic.page.component;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.page.component
 * @Author: fntp
 * @CreateTime: 2022-11-13  16:25
 * @Description: 滑动窗口创造器
 * @Version: 1.0
 */
public class ScrollPaneComponent {

    private ScrollPaneComponent() throws IllegalAccessException {
        throw new IllegalAccessException("ScrollPaneComponent is not enable to be multiple");
    }

    private static final Logger log = LoggerFactory.getLogger(ScrollPaneComponent.class);

    /**
     * 基于根节点构建滑动容器
     * 原理就是根据传入的根节点创建一个可滑动的pane，这个scrollPane使用根节点的id作为唯一识别id
     * @param rootNode 根节点
     * @return 返回一个滑动容器
     */
    public static ScrollPane createCommonScrollPaneRoot(Parent rootNode){
        ScrollPane scrollPane = new ScrollPane(rootNode);
        scrollPane.setId(rootNode.getId());
        try {
            // 滑动pane容器设置样式
            scrollPane.getStylesheets().add(Objects.requireNonNull(ScrollPaneComponent.class.getResource("/css/scrollPane-common.css")).toURI().toString());
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            // 界面初始化默认仪表盘在最前显示
            scrollPane.toFront();
            // 监听鼠标移入移出
            scrollPaneMouseEventStyleChange(scrollPane);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return scrollPane;
    }

    /**
     * 更改scrollPane的鼠标移入移出事件样式
     * @param scrollPane 滚动面板
     */
    private static void scrollPaneMouseEventStyleChange(ScrollPane scrollPane){
        scrollPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            log.info("当前鼠标移动策略：{}" , scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
        scrollPane.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            log.info("当前鼠标移动策略：{}", scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
    }
}
