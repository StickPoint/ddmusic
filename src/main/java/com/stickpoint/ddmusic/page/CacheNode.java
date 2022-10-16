package com.stickpoint.ddmusic.page;

import javafx.scene.media.MediaPlayer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: CacheNode
 *
 * @ClassName : CacheNode
 * @Date 2022/10/11 22:59
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.page
 */
public interface CacheNode {

    /**
     * 内置音乐播放器缓存
     */
    Map<String, MediaPlayer> INNER_PLAYER_CACHE = new ConcurrentHashMap<>(1);

}
