package com.stickpoint.ddmusic.common.cache;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: io.github.ddmusic.common.constriant
 * @Author: fntp
 * @CreateTime: 2022-09-17  11:38
 * @Description: TODO
 * @Version: 1.0
 */
public interface SystemCache {

    /**
     * BeCareful！ 这里是应用全局配置，是通过网络IO加载的数据
     * 系统全局应用配置数据缓存，
     * 当配置中心发生变化的时候进行全局配置刷新
     * （此功能需要启动全局自动刷新，如果禁用全局配置自动刷新需要通过手动刷新配置中心）
     */
    Map<String, Object> APP_PROPERTIES = new ConcurrentHashMap<>(30);

    /**
     * 系统内部加载的所有页面
     */
    Map<String,FXMLLoader> PAGE_MAP = new ConcurrentHashMap<>(30);

    /**
     * 应用内部全局配置，不由外部决定
     * 本配置仅在运行时生效
     * 本配置仅由内部程序修改，不暴露对外接口
     */
    Map<String,Object> SYS_INNER_PROPERTIES = new ConcurrentHashMap<>(20);

    /**
     * 菜单map
     */
    List<Node> CENTER_VIEW_PAGE_LIST = new ArrayList<>();

    /**
     * 里面装载的是全局的中间内容展示区域的StackPane-CenterView
     * 因为在不同的页面的控制器Controller中点击按钮的时候
     * 会触发这个界面的切换效果，需要使用主界面的stackPane来达到这个效果
     * 因此需要将他存在内存中，不进行二次创建，并且避免被系统回收内存
     */
    Map<String, Node> CACHE_NODE = new ConcurrentHashMap<>(1);

    /**
     * 系统托盘缓存
     * 将系统托盘与系统主页之间来回切换
     */
    Map<String,Object> NODE_MAP = new ConcurrentHashMap<>(1);

    /**
     * 内置音乐播放器缓存
     * （1）歌词组件
     */
    Map<String, MediaPlayer> INNER_PLAYER_CACHE = new ConcurrentHashMap<>(1);
    /**
     * 内置音乐播放器缓存
     * （2）歌词组件
     */
    Map<String, RXLrcView> INNER_LRC_CACHE = new ConcurrentHashMap<>(1);
    /**
     * 内置音乐播放器缓存
     * （3）进度条组件
     */
    Map<String, RXMediaProgressBar> INNER_MP_CACHE = new ConcurrentHashMap<>(1);

    /**
     * 上一首，当前播放，下一首
     * 原本打算使用链表来进行存储关系，上一首下一首但是发现链表不具备持久化的特点，会干扰反射，所以直接就用map吧
     */
    Map<String, AbstractDdMusicEntity> INNER_PLAY_MUSIC = new ConcurrentHashMap<>(3);
}
