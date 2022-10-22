package com.stickpoint.ddmusic.page;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.leewyatt.rxcontrols.controls.RXAudioSpectrum;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import com.leewyatt.rxcontrols.pojo.LrcDoc;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.CodeEnum;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.utils.EncodingDetectUtil;
import com.stickpoint.ddmusic.router.PageEnums;
import animatefx.animation.FadeIn;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    @SuppressWarnings("unused")
    private MediaPlayer player;
    /**
     * 播放时间字符串显示格式格式化
     */
    private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    /**
     * 音乐播放器-控制组件
     */
    @SuppressWarnings("unused")
    private HBox musicControl;
    /**
     * 音量调节组件
     */
    @SuppressWarnings("unused")
    private ContextMenu soundPopup;
    /**
     * 音量滑动条
     */
    @SuppressWarnings("unused")
    private Slider soundSlider;

    private RecentlyPlayListController recentlyPlayListController;

    private MusicControlController musicControlController;

    public PlayerComponentController(){
        this.recentlyPlayListController = new RecentlyPlayListController();
        this.musicControlController = new MusicControlController();
    }

    /**
     * 额外组件加载的位置
     * 在页面初始化之后，伴随着controller的产生进行初始化
     * 出去本页面之外的组件进行初始化
     * @title: initialize 初始化方法
     * @description: void 无需要返回任何数据
     */
    @FXML
    void initialize() {
        initPlayerComponent();
        initProgressBar();
        initSoundPopup();
        initMusicList();
    }

    /**
     * 进度条的拖动 或者 点击 进行处理
     * @title: initProgressBar 初始化进度条方法
     * @description: void 不需要返回任何数据
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
     * 初始化音量调节context区域
     * 首先这是一个音量调节组件，定位在组件
     * 组件与页面一样，同样拥有页面的加载器。
     * 与页面不同的是，组件的消息冒泡属性是直接在父组件中进行处理的，而不是在组件自身内部进行的。
     * 详细解释请参考顶点音乐官方文档。
     */
    private void initSoundPopup() {
        soundPopup = new ContextMenu(new SeparatorMenuItem());
        Parent soundRoot = null;
        try {
            // 系统初始化的时候已经装载了这个数据，因此现在拿来用就可以了
            FXMLLoader soundControlLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.SOUND_CONTROL.getRouterId());
            // 初始化根节点
            soundRoot = soundControlLoader.load();
            // 通过fx:id获得内部node 注意这里不是通过css-id的lookup方法获得的node节点
            ObservableMap<String, Object> soundControlNamespace = soundControlLoader.getNamespace();
            soundSlider = (Slider) soundControlNamespace.get("soundSlider");
            Label soundNumLabel = (Label) soundControlNamespace.get("soundNum");
            soundNumLabel.textProperty().bind(soundSlider.valueProperty().asString("%.0f%%"));
            //声音滑块改变时,改变player的音量
            soundSlider.valueProperty().addListener((ob, ov, nv) -> {
                if (player != null) {
                    player.setVolume(nv.doubleValue() / 100);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        soundPopup.getScene().setRoot(soundRoot);
    }

    /**
     * 初始化音乐播放列表
     */
    private void initMusicList() {
         // 第一步 初始化音乐播放列表
        if (player != null) {
            disposeMediaPlayer();
        }
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/media/jar-of-love.mp3")).toString()));
        CacheNode.INNER_PLAYER_CACHE.put("player",player);
        player.setVolume(soundSlider.getValue() / 100);
        //设置歌词
        FXMLLoader playerDetailLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.PLAY_DETAIL_PAGE.getRouterId());
        ObservableMap<String, Object> playerDetailLoaderNamespace = playerDetailLoader.getNamespace();
        RXLrcView lrcView = (RXLrcView) playerDetailLoaderNamespace.get(InfoEnums.MUSIC_DETAIL_LRC_VIEW.getInfoContent());
        String lrcPath = "D:\\developData\\codeData\\desktopApp\\ddmusic\\src\\main\\resources\\media\\jar-of-love.lrc";
        File lrcFile = new File(lrcPath);
        boolean exists = lrcFile.exists();
        if (exists) {
            try {
                byte[] bytes = Files.readAllBytes(lrcFile.toPath());
                //解析歌词
                lrcView.setLrcDoc(LrcDoc.parseLrcDoc(new String(bytes, EncodingDetectUtil.detect(bytes))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置歌词进度
        lrcView.currentTimeProperty().bind(player.currentTimeProperty());
        //设置频谱可视化
        player.setAudioSpectrumListener(audioSpectrumListener);
        //设置进度条的总时长
        playerProgressBar.durationProperty().bind(player.getMedia().durationProperty());
        //播放器的进度修改监听器
        player.currentTimeProperty().addListener(durationChangeListener);
        //如果播放完当前歌曲, 我们这里暂停播放 (1)播放器暂停 （2）ui显示
        player.setOnEndOfMedia(() -> musicControlController.startOrPausePlay(CacheNode.INNER_PLAYER_CACHE.get("player")));
        //player.setOnEndOfMedia(this::playNextMusic);
        //player.play();
    }

    /**
     * 频谱数据发生改变的时候,修改频谱可视化组件的数据
     */
    private final AudioSpectrumListener audioSpectrumListener = (timestamp, duration, magnitudes, phases) -> {
        FXMLLoader pageDetailLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.PLAY_DETAIL_PAGE.getRouterId());
        ObservableMap<String, Object> playerDetailLoaderNamespace = pageDetailLoader.getNamespace();
        RXAudioSpectrum audioSpectrum = (RXAudioSpectrum) playerDetailLoaderNamespace.get(InfoEnums.MUSIC_DETAIL_AUDIO_SPECTRUM.getInfoContent());
        audioSpectrum.setMagnitudes(magnitudes);
    };

    /**
     * 当播放时间改变的时候. 修改时间的显示
     * 将音乐播放时间进行动态刷新显示
     */
    private void changeTimeLabel(Duration duration) {
        String currentTime = sdf.format(duration.toMillis());
        String bufferedTimer = sdf.format(player.getBufferProgressTime().toMillis());
        playerTimeLabel.setText(currentTime+ " / "+bufferedTimer);
    }

    /**
     * 播放进度发生改变的时候..修改进度条的播放进度, 修改时间的显示
     */
	private final ChangeListener<Duration> durationChangeListener = (ob1, ov1, nv1) -> {
        playerProgressBar.setCurrentTime(nv1);
        changeTimeLabel(nv1);
    };

    private void disposeMediaPlayer() {
        player.stop();
        player.setAudioSpectrumListener(null);
        playerProgressBar.durationProperty().unbind();
        playerProgressBar.setCurrentTime(Duration.ZERO);
        player.currentTimeProperty().removeListener(durationChangeListener);
        // 事件标签变化
        playerTimeLabel.setText("00:00 / 00:00");
        player.setOnEndOfMedia(null);
        player.dispose();
        player = null;
    }


    /**
     * 监听鼠标移入歌曲播放简况信息内部
     * 然后执行动画效果展示音乐播放控制组件
     */
    @FXML
    void showMusicControl() {
    	infoSliderArea.getChildren().clear();
    	// TODO 使用timeLine动画效果实现这个切换过程    	
    	if (Objects.isNull(musicControl)) {
    		// 如果说 musicControl是一个空的对象，从系统Cache中取
			LOGGER.log(Level.WARNING,CodeEnum.EXCEPTION_NULL_POINTER.getMessage());
	    	// 初始化加载音乐播放组件
            FXMLLoader musicControlLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.MUSIC_CONTROL.getRouterId());
            // 获得初始化的音乐播放控制组件节点
            ObservableMap<String, Object> musicControlNamespace = musicControlLoader.getNamespace();
            musicControl = (HBox) musicControlNamespace.get(InfoEnums.MUSIC_CONTROL_FX_ID.getInfoContent());
		}else {
			LOGGER.log(Level.INFO,"当前对象存在！");
        }
        infoSliderArea.getChildren().add(musicControl);
        FadeIn fadeIn = new FadeIn(infoSliderArea);
    	fadeIn.play();
    }
    
    /**
     * 监听鼠标移出音乐播放控制组件内部
     * 然后执行动画效果展示歌曲播放简况信息
     */
    @FXML
    void showMusicGeneralInfo() {
    	infoSliderArea.getChildren().clear();
    	infoSliderArea.getChildren().add(musicGeneralInfo);
    	FadeIn fadeIn = new FadeIn(infoSliderArea);
    	fadeIn.play();
    }


    /**
     * 由于默认的组件位置是空白的一个AnchorPane 什么都没有
     * 现在通过初始化组件的形式加载其他额外的FXML文件
     * 初始化一个播放组件：
     * （1）由于在软件刚初始化的时候，所有的FXML文件都被装载到了系统内部，所以可以直接读取
     * （2）通过CSS-ID来选择器来选择组件
     */
    private void initPlayerComponent(){
        FXMLLoader homePageLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.HOMEPAGE.getRouterId());
        ObservableMap<String, Object> homePageNamespace = homePageLoader.getNamespace();
        AnchorPane homePagePlayer = (AnchorPane) homePageNamespace.get(InfoEnums.HOME_PAGE_PLAYER_FX_ID.getInfoContent());
        FXMLLoader playerComponentLoader = SystemCache.FXML_LOAD_MAP.get(PageEnums.PLAYER_COMPONENT.getRouterId());
        ObservableMap<String, Object> playerComponentLoaderNamespace = playerComponentLoader.getNamespace();
        homePagePlayer.getChildren().addAll((Node) playerComponentLoaderNamespace.get(InfoEnums.PLAY_COMPONENT_FX_ID.getInfoContent()));
    }

    /**
     * 当监听到点击了音量调节按钮之后的操作
     */
    @FXML
    public void onSoundPopupAction() {
        Bounds bounds = playerVolume.localToScreen(playerVolume.getBoundsInLocal());
        Stage hBoxStage = (Stage) playerComponent.getScene().getWindow();
        soundPopup.show(hBoxStage, bounds.getMinX() - 20, bounds.getMinY() - 165);
    }

    /**
     * 显示最近播放
     */
    public void showRecentlyPlayList() {
        Bounds bounds = playerRecentlyList.localToScreen(playerRecentlyList.getBoundsInLocal());
        Stage hBoxStage = (Stage) playerComponent.getScene().getWindow();
        ContextMenu playListPopup = recentlyPlayListController.getRecentlyPlayListPopup();
        playListPopup.show(hBoxStage, bounds.getMinX() - 292, bounds.getMinY() - 376);
    }
}
