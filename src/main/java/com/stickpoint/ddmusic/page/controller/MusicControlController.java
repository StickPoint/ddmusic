package com.stickpoint.ddmusic.page.controller;
import java.util.Objects;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.page
 * @Author: fntp
 * @CreateTime: 2022-09-25  19:46
 * @Description: TODO
 * @Version: 1.0
 */
public class MusicControlController {

    @FXML
	@SuppressWarnings("unused")
	private HBox musicControl;

    @FXML
	@SuppressWarnings("unused")
    private AnchorPane playControlPane;

    @FXML
	@SuppressWarnings("unused")
    private Region playerLeft;

    @FXML
    private Region playerPauseOrGoon;

    @FXML
	@SuppressWarnings("unused")
    private Region playerRight;

	/**
     * 日志工具
     */
	private static final Logger log = LoggerFactory.getLogger(MusicControlController.class);

    /**
	 * TODO 注意我这里没有考虑使用ToggleButton：
	 * 只负责两件事
	 * （1）切换播放按钮样式
	 * （2）播放或者暂停
	 * 播放的音乐获取，歌词获取，进度等等都是播放的前置操作，前置操作交由内部组件去执行
	 * 因为我需要直接获得按钮的实时状态等诸多属性，如果使用ToggleButton，二次封装获取属性会很复杂。
     * 鼠标点击事件：开始播放或者暂停播放音乐
     * 实现思想：首先获得当前的style，因为是region实现的图标
     * 所以我们通过region来修改style样式。
     * 然后修改点击后的样式，这里的修改需要借助SystemCache来完成，因为
     * 点击之后，我们需要先判断现在音乐的状态，
     * 注意事项：音乐的初始化状态是在全局初始化的时候完成加载的
     */
    @FXML
    void startOrPausePlay() {
		// 首先获得当前按钮的style样式，基于此然后进行修改
		String currentStyle = playerPauseOrGoon.getStyle();
		// 当前播放器播放按钮状态
		log.info(currentStyle);
		// 然后紧接着获得当前系统音乐播放按钮的播放状态
		String currentMusicPlayerStatus = (String) SystemCache.SYS_INNER_PROPERTIES.get(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent());
    	// 播放状态
    	if(currentMusicPlayerStatus.equals(InfoEnums.MUSIC_PLAY_STATUS_GOON_VALUE.getInfoContent())) {
    		// 如果是播放状态，那么就修改成暂停状态
        	SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(),
        			InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent());
        	// 获得当前按钮的style样式之后，修改SystemCache的值，然后修改按钮样式
        	currentStyle = currentStyle.replace(InfoEnums.MUSIC_PLAY_STATUS_GOON_SVG_PATH.getInfoContent(),
        			InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent());
        	playerPauseOrGoon.setStyle(currentStyle);
        	SystemCache.INNER_PLAYER_CACHE.get("player").pause();
    	}else if(currentMusicPlayerStatus.equals(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent())){
			// 那么就修改成播放状态
			SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(),
					InfoEnums.MUSIC_PLAY_STATUS_GOON_VALUE.getInfoContent());
			// 获得当前按钮的style样式之后，修改SystemCache的值，然后修改按钮样式
			if(currentStyle.contains(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent())) {
				currentStyle = currentStyle.replace(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent(),
						InfoEnums.MUSIC_PLAY_STATUS_GOON_SVG_PATH.getInfoContent());
				playerPauseOrGoon.setStyle(currentStyle);
			}
			SystemCache.INNER_PLAYER_CACHE.get("player").play();
    	}
    	// 切换后播放器播放按钮状态
    	playerPauseOrGoon.setStyle(currentStyle);
    	// 记录日志
		log.info("音乐播放组件的播放按钮点击后的最终样式是：{}",currentStyle);
    }

	/**
	 * 外部调用的方法
	 * @param player 传入一个内置音乐播放器
	 */
	@SuppressWarnings("unused")
	public void startOrPausePlay(MediaPlayer player){
		if (Objects.isNull(playerPauseOrGoon)){
			FXMLLoader musicControlLoader = SystemCache.PAGE_MAP.get(PageEnums.MUSIC_CONTROL.getRouterId());
			ObservableMap<String, Object> musicControlLoaderNamespace = musicControlLoader.getNamespace();
			playerPauseOrGoon = (Region) musicControlLoaderNamespace.get(InfoEnums.MUSIC_CONTROL_PLAY_OR_PAUSE_FX_ID.getInfoContent());
		}
		// 首先获得当前按钮的style样式，基于此然后进行修改
		String currentStyle = playerPauseOrGoon.getStyle();
		// 当前播放器播放按钮状态
		log.info(currentStyle);
		// 然后紧接着获得当前系统音乐播放按钮的播放状态
		String currentMusicPlayerStatus = (String) SystemCache.SYS_INNER_PROPERTIES.get(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent());
		log.info("当前系统音乐播放控制组件中，播放按钮的状态是：{}",currentMusicPlayerStatus);
		// 播放状态
		if(currentMusicPlayerStatus.equals(InfoEnums.MUSIC_PLAY_STATUS_GOON_VALUE.getInfoContent())) {
			// 如果是播放状态，那么就修改成暂停状态
			SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(),
					InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent());
			// 获得当前按钮的style样式之后，修改SystemCache的值，然后修改按钮样式
			currentStyle = currentStyle.replace(InfoEnums.MUSIC_PLAY_STATUS_GOON_SVG_PATH.getInfoContent(),
					InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent());
			playerPauseOrGoon.setStyle(currentStyle);
			player.pause();
		}else if(currentMusicPlayerStatus.equals(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_VALUE.getInfoContent())){
			// 如果是暂停状态，那么就修改成播放状态
			SystemCache.SYS_INNER_PROPERTIES.put(InfoEnums.MUSIC_PLAY_STATUS.getInfoContent(),
					InfoEnums.MUSIC_PLAY_STATUS_GOON_VALUE.getInfoContent());
			// 获得当前按钮的style样式之后，修改SystemCache的值，然后修改按钮样式
			if(currentStyle.contains(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent())) {
				currentStyle = currentStyle.replace(InfoEnums.MUSIC_PLAY_STATUS_PAUSE_SVG_PATH.getInfoContent(),
						InfoEnums.MUSIC_PLAY_STATUS_GOON_SVG_PATH.getInfoContent());
				playerPauseOrGoon.setStyle(currentStyle);
			}
			player.play();
		}
		// 切换后播放器播放按钮状态
		playerPauseOrGoon.setStyle(currentStyle);
		// 记录日志
		log.info("音乐播放组件的播放按钮点击后的最终样式是：{}",currentStyle);
	}

}
