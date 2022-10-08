package com.stickpoint.ddmusic.page;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.CodeEnum;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.router.PageEnums;
import animatefx.animation.FadeIn;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.page
 * @Author: fntp
 * @CreateTime: 2022-09-24  11:28
 * @Description: TODO
 * @Version: 1.0
 */
public class PlayerComponentController {
	
	/**
	 * 歌曲概况信息与播放控制组件可滑动区域
	 */
    @FXML
    private AnchorPane infoSliderArea;
    /**
     * 音乐基础概况信息
     */
    @FXML
    private VBox musicGeneralInfo;
    /**
     * 音乐播放器-歌曲-封面
     */
    @SuppressWarnings("exports")
	public RXAvatar playerMusicCover;
    /**
     * 音乐播放器-歌曲-时间标签
     */
    @SuppressWarnings("exports")
	public Label playerTimeLabel;
    /**
     * 音乐播放器-歌曲-播放进度条
     */
    @SuppressWarnings("exports")
	public RXMediaProgressBar playerProgressBar;
    /**
     * 音乐播放器-歌曲-桌面歌词开关
     */
    @SuppressWarnings("exports")
	public Region playerDesktopLyrics;
    /**
     * 音乐播放器-歌曲-播放器循环策略
     */
    @SuppressWarnings("exports")
	public Region playerCycleStrategy;
    /**
     * 音乐播放器-歌曲-播放音量
     */
    @SuppressWarnings("exports")
	public Region playerVolume;
    /**
     * 音乐播放器-歌曲-最近播放列表
     */
    @SuppressWarnings("exports")
	public Region playerRecentlyList;
    /**
     * 音乐播放器-歌曲-歌曲演唱者名字
     */
    @SuppressWarnings("exports")
	public Label playerSinger;
    /**
     * 音乐播放器-歌曲-歌曲名称
     */
    @SuppressWarnings("exports")
	public Label playerSongName;
    /**
     * 初始化一个日志对象
     */
    private static final Logger LOGGER = Logger.getGlobal();
    /**
     * 音乐播放器的Root组件
     */
    @SuppressWarnings("exports")
	public HBox playerComponent;
    /**
     * JavaFX内置：音频播放器
     */
    private MediaPlayer player;
    /**
     * 播放时间字符串显示格式格式化
     */
    private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    /**
     * 音乐播放器-控制组件
     */
    private HBox musicControl;

    /**
     * 额外组件加载的位置
     * 在页面初始化之后，伴随着controller的产生进行初始化
     * 出去本页面之外的组件进行初始化
     * @title: initialize 初始化方法
     * @description: void 无需要返回任何数据
     * @throws 暂无需要抛出的异常
     */
    @FXML
    void initialize() {
        initProgressBar();
    }

    /**
     * 进度条的拖动 或者 点击 进行处理
     * @title: initProgressBar 初始化进度条方法
     * @description: void 不需要返回任何数据
     * @throws 不需要抛出任何异常
     */
    private void initProgressBar() {
        EventHandler<MouseEvent> progressBarHandler = event -> {
            if (player != null) {
                player.seek(playerProgressBar.getCurrentTime());
                changeTimeLabel(playerProgressBar.getCurrentTime());
            }
        };
        playerProgressBar.setOnMouseClicked(progressBarHandler);
        playerProgressBar.setOnMouseDragged(progressBarHandler);
    }

    /**
     * 当播放时间改变的时候. 修改时间的显示
     */
    private void changeTimeLabel(Duration nv1) {
        String currentTime = sdf.format(nv1.toMillis());
        String bufferedTimer = sdf.format(player.getBufferProgressTime().toMillis());
        playerTimeLabel.setText(currentTime+ " / "+bufferedTimer);
    }

    /**
     * 播放进度发生改变的时候..修改进度条的播放进度, 修改时间的显示
     */
    @SuppressWarnings("unused")
	private ChangeListener<Duration> durationChangeListener = (ob1, ov1, nv1) -> {
        playerProgressBar.setCurrentTime(nv1);
        changeTimeLabel(nv1);
    };
    
    /**
     * 监听鼠标移入歌曲播放简况信息内部
     * 然后执行动画效果展示音乐播放控制组件
     * @param event	
     */
    @FXML
    void showMusicControl(MouseEvent event) {
    	infoSliderArea.getChildren().clear();
    	// TODO 使用timeLine动画效果实现这个切换过程    	
    	if (Objects.isNull(musicControl)) {
    		// 如果说 musicControl是一个空的对象，从系统Cache中取
			LOGGER.log(Level.WARNING,CodeEnum.EXCEPTION_NULL_POINTER.getMessage());
	    	// 初始化加载音乐播放组件
	    	Parent musicControlParentNode = SystemCache.FXML_LOAD_MAP.get(PageEnums.MUSIC_CONTROL.getRouterId());
	    	// 获得初始化的音乐播放控制组件节点
	    	HBox currentMusicControlNode = (HBox) musicControlParentNode.lookup(InfoEnums.MUSIC_CONTROL_CSS_ID.getInfoContent());
	    	if (Objects.isNull(currentMusicControlNode)) {
	    		// 如果说从LookUp中加载的node节点是空的 那么从系统缓存中去取node节点对象
	    		LOGGER.log(Level.WARNING,CodeEnum.EXCEPTION_NULL_POINTER_FOR_TWO.getMessage());
	    		musicControl = (HBox) SystemCache.SYS_CACHE_NODE.get(InfoEnums.MUSIC_CONTROL_ID.getInfoContent());
	    		LOGGER.log(Level.INFO,"从系统中获取Node节点了");
	    		infoSliderArea.getChildren().add(musicControl);
	    	}else {
	    		// 如果说LookUp中加载的Node不是空的 那么直接赋值给musicControl
	    		LOGGER.log(Level.INFO,"从LookUp中获得了Node节点，存储到了系统缓存中");
	    		musicControl = currentMusicControlNode;
	    		// 然后存储起来，存在系统缓存里面,以供下次使用
	    		SystemCache.SYS_CACHE_NODE.put(InfoEnums.MUSIC_CONTROL_ID.getInfoContent(),musicControl);
	    		infoSliderArea.getChildren().add(musicControl);
	    	}
		}else {
			infoSliderArea.getChildren().add(musicControl);
			LOGGER.log(Level.INFO,"当前对象存在！");
		}
    	FadeIn fadeIn = new FadeIn(infoSliderArea);
    	fadeIn.play();
    }
    
    /**
     * 监听鼠标移出音乐播放控制组件内部
     * 然后执行动画效果展示歌曲播放简况信息
     * @param event	
     */
    @FXML
    void showMusicGeneralInfo(MouseEvent event) {
    	infoSliderArea.getChildren().clear();
    	infoSliderArea.getChildren().add(musicGeneralInfo);
    	FadeIn fadeIn = new FadeIn(infoSliderArea);
    	fadeIn.play();
    }
    
}
