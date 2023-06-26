package com.stickpoint.ddmusic.page.controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Objects;
import com.leewyatt.rxcontrols.controls.RXAudioSpectrum;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import com.leewyatt.rxcontrols.pojo.LrcDoc;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.utils.EncodingDetectUtil;
import com.stickpoint.ddmusic.page.enums.PageEnums;
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
     * 音乐播放器-控制组件
     */
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
        // 初始化上次关闭前最后一首音乐播放，或者是最近播放，或者是本地播放的音乐
        prepareLastPlayOrRecentlyLocalMusic();
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
            FXMLLoader soundControlLoader = SystemCache.PAGE_MAP.get(PageEnums.SOUND_CONTROL.getRouterId());
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
     *
     * 播放音乐的前置方法内部方法
     * TODO  如果没有歌词需要显示暂无歌词
     */
    public void prepareMusic(AbstractDdMusicEntity abstractDdMusicEntity, String musicUrl, String musicLrcContent) {
         // 第一步 初始化音乐播放列表
        if (player != null) {
            disposeMediaPlayer();
        }
        player = new MediaPlayer(new Media(musicUrl));
        SystemCache.INNER_PLAYER_CACHE.put("player",player);
        player.setVolume(soundSlider.getValue() / 100);
        //设置歌词
        FXMLLoader playerDetailLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAY_DETAIL_PAGE.getRouterId());
        PlayDetailController playDetailController = playerDetailLoader.getController();
        RXLrcView lrcView = playDetailController.lrcView;
        // 如果有歌词
        if (Objects.nonNull(musicLrcContent)) {
            if (musicLrcContent.isEmpty()||musicLrcContent.isBlank()) {
                musicLrcContent = "暂无歌词";
            }
            try {
                byte[] bytes = musicLrcContent.getBytes();
                //解析歌词
                lrcView.setLrcDoc(LrcDoc.parseLrcDoc(new String(bytes, EncodingDetectUtil.detect(bytes))));
                //设置歌词进度
                lrcView.currentTimeProperty().bind(player.currentTimeProperty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置频谱可视化
        player.setAudioSpectrumListener(audioSpectrumListener);
        //设置进度条的总时长
        playerProgressBar.durationProperty().bind(player.getMedia().durationProperty());
        //播放器的进度修改监听器
        player.currentTimeProperty().addListener(durationChangeListener);
        // 存入缓存
        changeMusicPlayPreNextAndCurrent(abstractDdMusicEntity);
    }

    /**
     * 初始化音乐播放列表
     * 当软件启动的时候，先去加载本地历史播放的音乐列表 选择一首音乐进行播放
     * 如果没有最近播放列表，那么就从本地音乐中找一首音乐进行播放
     * TODO 如果本地没有音乐 那么直接报错 现在为了测试，如果本地没有音乐 就读取项目里面的音乐，这个是写死的后面要改为报错
     */
    private void prepareLastPlayOrRecentlyLocalMusic() {
        // 第一步 初始化音乐播放列表
        if (player != null) {
            disposeMediaPlayer();
        }
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/media/jar-of-love.mp3")).toString()));
        SystemCache.INNER_PLAYER_CACHE.put("player",player);
        player.setVolume(soundSlider.getValue() / 100);
        //设置歌词
        FXMLLoader playerDetailLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAY_DETAIL_PAGE.getRouterId());
        ObservableMap<String, Object> playerDetailLoaderNamespace = playerDetailLoader.getNamespace();
        RXLrcView lrcView = (RXLrcView) playerDetailLoaderNamespace.get(InfoEnums.MUSIC_DETAIL_LRC_VIEW.getInfoContent());
        URL resource = PlayerComponentController.class.getResource("/media/jar-of-love.lrc");
        if (Objects.nonNull(resource)) {
            File lrcFile = new File(resource.getPath());
            boolean exists = lrcFile.exists();
            if (exists) {
                try {
                    byte[] bytes = Files.readAllBytes(lrcFile.toPath());
                    // 解析歌词
                    lrcView.setLrcDoc(LrcDoc.parseLrcDoc(new String(bytes, EncodingDetectUtil.detect(bytes))));
                    // 设置歌词进度
                    lrcView.currentTimeProperty().bind(player.currentTimeProperty());
                    // 存储节点
                    SystemCache.INNER_LRC_CACHE.put("playerLyric",lrcView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //设置频谱可视化
            player.setAudioSpectrumListener(audioSpectrumListener);
            //设置进度条的总时长
            playerProgressBar.durationProperty().bind(player.getMedia().durationProperty());
            SystemCache.INNER_MP_CACHE.put("playerProgress",playerProgressBar);
            //播放器的进度修改监听器
            player.currentTimeProperty().addListener(durationChangeListener);
        }else {
            throw new DdmusicException(DdMusicExceptionEnums.ERROR_MUSIC_FILE_PATH_NOT_EXIST);
        }
    }

    /**
     * 执行了PrepareMusic的时候，需要设置当前播放，下一首播放，上一首播放
     */
    private void changeMusicPlayPreNextAndCurrent(AbstractDdMusicEntity absolutelyCurrent) {
        if (Objects.nonNull(absolutelyCurrent)) {
            AbstractDdMusicEntity current = SystemCache.INNER_PLAY_MUSIC.get("current");
            if (Objects.nonNull(current)) {
                SystemCache.INNER_PLAY_MUSIC.put("pre",current);
            }
            // 当前音乐就是传入的将要播放的音乐
            SystemCache.INNER_PLAY_MUSIC.put("current",absolutelyCurrent);
            // TODO 这个功能将会在数据落库的时候去做这个数据的处理 下一首需要根据返回的列表来决定，暂时没有数据
        }else {
            throw new DdmusicException(DdMusicExceptionEnums.ERROR_MUSIC_OBJECT_NOT_FOUND);
        }
    }

    /**
     * 频谱数据发生改变的时候,修改频谱可视化组件的数据
     */
    private final AudioSpectrumListener audioSpectrumListener = (timestamp, duration, magnitudes, phases) -> {
        FXMLLoader pageDetailLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAY_DETAIL_PAGE.getRouterId());
        ObservableMap<String, Object> playerDetailLoaderNamespace = pageDetailLoader.getNamespace();
        RXAudioSpectrum audioSpectrum = (RXAudioSpectrum) playerDetailLoaderNamespace.get(InfoEnums.MUSIC_DETAIL_AUDIO_SPECTRUM.getInfoContent());
        audioSpectrum.setMagnitudes(magnitudes);
    };

    /**
     * 当播放时间改变的时候. 修改时间的显示
     * 将音乐播放时间进行动态刷新显示
     */
    private void changeTimeLabel(Duration duration) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
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
        // 初始化加载音乐播放组件
        FXMLLoader musicControlLoader = SystemCache.PAGE_MAP.get(PageEnums.MUSIC_CONTROL.getRouterId());
        // 获得初始化的音乐播放控制组件节点
        ObservableMap<String, Object> musicControlNamespace = musicControlLoader.getNamespace();
        musicControl = (HBox) musicControlNamespace.get(InfoEnums.MUSIC_CONTROL_FX_ID.getInfoContent());
        infoSliderArea.getChildren().clear();
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
        infoSliderArea.setCacheShape(true);
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
     * TODO 后续需要修改，因为加载完毕FXML之后，会有一个FXMLLoader对象这个对象可以直接获取Controller，根据controller直接获取对象
     */
    private void initPlayerComponent(){
        FXMLLoader homePageLoader = SystemCache.PAGE_MAP.get(PageEnums.HOMEPAGE.getRouterId());
        ObservableMap<String, Object> homePageNamespace = homePageLoader.getNamespace();
        AnchorPane homePagePlayer = (AnchorPane) homePageNamespace.get(InfoEnums.HOME_PAGE_PLAYER_FX_ID.getInfoContent());
        FXMLLoader playerComponentLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAYER_COMPONENT.getRouterId());
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
        FXMLLoader recentlyPlayListLoader = SystemCache.PAGE_MAP.get(PageEnums.RECENTLY_PLAY_LIST.getRouterId());
        RecentlyPlayListController recentlyPlayListController = recentlyPlayListLoader.getController();
        ContextMenu playListPopup = recentlyPlayListController.getRecentlyPlayListPopup();
        playListPopup.show(hBoxStage, bounds.getMinX() - 292, bounds.getMinY() - 376);
    }
}
