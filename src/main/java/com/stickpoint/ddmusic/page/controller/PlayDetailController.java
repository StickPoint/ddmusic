package com.stickpoint.ddmusic.page.controller;

import com.leewyatt.rxcontrols.controls.RXAudioSpectrum;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * description: PlayDetailController
 *
 * @ClassName : PlayDetailController
 * @Date 2022/10/11 17:59
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.page
 */
public class PlayDetailController {
    /**
     * 歌词组件
     */
    @FXML
    public RXLrcView lrcView;
    /**
     * 频谱组件
     */
    @FXML
    public RXAudioSpectrum audioSpectrum;
    /**
     * 频谱初始普段
     */
    private static final float[] EMPTY_ARRAY = new float[128];
    /**
     * 播放详情页面的根节点
     */
    public AnchorPane playDetailRootNode;

    @FXML
    public void initialize(){
        lrcView.setLrcDoc(null);
        lrcView.currentTimeProperty().unbind();
        lrcView.setCurrentTime(Duration.ZERO);
        audioSpectrum.setMagnitudes(EMPTY_ARRAY);
        initPlayDetailPage();
    }

    /**
     * 初始化加载的时候将播放详情页面放置入系统的显示区域的StackPane中
     * 需要的时候在进行切换toFront或者是toBackhand
     */
    private void initPlayDetailPage() {
        SystemCache.CENTER_VIEW_PAGE_LIST.add(playDetailRootNode);
    }

    /**
     * 初始化的时候绑定一下子组件节点与父组件节点之间的X坐标位置关系
     * 初始化歌词组件binding
     */
    @SuppressWarnings("unused")
    public  void initPlayLrcLayoutBindings() {
        DoubleBinding layoutBinding = Bindings.createDoubleBinding(() -> {
            double parentWidth = playDetailRootNode.getWidth();
            // 计算子节点的布局X坐标
            double childX = lrcView.getLayoutX();
            // 获取子节点的宽度
            double childWidth = lrcView.getWidth();
            return childX + (parentWidth - childWidth);
        }, playDetailRootNode.widthProperty());
        lrcView.layoutXProperty().bind(layoutBinding);
    }

    /**
     * 初始化的时候绑定一下子组件节点与父组件节点之间的X坐标位置关系
     * 初始化频谱组件binding
     */
    @SuppressWarnings("unused")
    public void initPlaySpectrumLayoutBindings() {
        DoubleBinding layoutBinding = Bindings.createDoubleBinding(() -> {
            double parentWidth = playDetailRootNode.getWidth();
            // 计算子节点的布局X坐标
            double childX = audioSpectrum.getLayoutX();
            // 获取子节点的宽度
            double childWidth = audioSpectrum.getWidth();
            return childX + (parentWidth - childWidth);
        }, playDetailRootNode.widthProperty());
        audioSpectrum.layoutXProperty().bind(layoutBinding);
    }

}
