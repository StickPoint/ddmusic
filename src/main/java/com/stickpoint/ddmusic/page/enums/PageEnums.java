package com.stickpoint.ddmusic.page.enums;

import com.stickpoint.ddmusic.page.controller.*;
import com.stickpoint.ddmusic.page.stage.HomePageStage;
import java.net.URL;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public enum PageEnums {
    /**
     * 发现音乐组件
     */
    FIND_MUSIC(FindMusicController.class,FindMusicController.class.getResource("/fxml/findMusic.fxml"),"findMusic"),
    /**
     * 播放音乐组件
     */
    PLAYER_COMPONENT(PlayerComponentController.class, PlayerComponentController.class.getResource("/fxml/playerComponent.fxml"),"playerComponent"),
    /**
     * 音乐播放控制组件：暂停开始、上一曲下一曲组件。
     */
    MUSIC_CONTROL(MusicControlController.class,MusicControlController.class.getResource("/fxml/musicControl.fxml"),"musicControl"),
    /**
     * Home首页-页面
     */
    HOMEPAGE(HomePageStage.class, HomePageStage.class.getResource("/fxml/homePage.fxml"),"homePage"),
    /**
     * 额外菜单面板--累计信息卡
     */
    ACCUMULATE_PANE(AccumulatePaneController.class,AccumulatePaneController.class.getResource("/fxml/accumulatePane.fxml"),"accumulatePane"),
    /**
     * 播放详情页面
     */
    PLAY_DETAIL_PAGE(PlayDetailController.class,PlayDetailController.class.getResource("/fxml/playDetailPage.fxml"),"playDetailPage"),
    /**
     * 最近播放页面--最近播放列表
     */
    RECENTLY_PLAY_LIST(RecentlyPlayListController.class,RecentlyPlayListController.class.getResource("/fxml/recentlyPlayList.fxml"),"recentlyPlayList"),
    /**
     * 搜索音乐结果
     */
    SEARCH_RESULT_PAGE(SearchMusicResultController.class,SearchMusicResultController.class.getResource("/fxml/searchMusicResult.fxml"),"searchMusicResult"),
    /**
     * 系统托盘
     */
    SYSTEM_TRAY(SystemTrayController.class, SystemTrayController.class.getResource("/fxml/systemTray.fxml"),"systemTray"),
    /**
     * 音乐搜索之后单曲操作按钮四个按钮
     */
    MUSIC_SEARCH_RESULT_OPTIONS(MusicOptionsController.class, MusicOptionsController.class.getResource("/fxml/musicOptions.fxml"),"musicOptions"),
    /**
     * 下载本地页面
     */
    DOWNLOAD_LOCAL(LocalDownloadController.class,LocalDownloadController.class.getResource("/fxml/localDownload.fxml"),"localDownload"),
    /**
     * 歌单详情页面
     */
    PLAY_LIST_DETAIL(PlayListDetailController.class,PlayListDetailController.class.getResource("/fxml/playListDetail.fxml"),"playListDetail"),
    /**
     * playDetailAddition 歌曲播放详情附加页面左侧弹窗
     */
    PLAY_DETAIL_ADDITION(PlayDetailAdditionPageController.class,PlayDetailAdditionPageController.class.getResource("/fxml/playDetailAdditionPage.fxml"),"playDetailAddition"),
    /**
     * 音量控制组件面板--控制音量
     */
    SOUND_CONTROL(SoundControlController.class,SoundControlController.class.getResource("/fxml/soundControl.fxml"),"soundControl");
    /**
     * 页面/组件 名称
     */
    private final Class<?> pageType;
    /**
     * 页面/组件 路由
     */
    private final URL pageSource;
    /**
     * 页面/组件 全名称
     */
    private final String routerId;

	/**
	 *
	 * 构建构造方法
	 * @param pageType 页面/组件 类型，以页面的class来区分
	 * @param pageSource 页面/组件 数据源URL，classpath下的文件
	 * @param routerId 页面/组件 路由id，字符串，作为组件路由的关键key
	 */
    PageEnums(Class<?> pageType, URL pageSource,String routerId){
        this.pageType = pageType;
        this.pageSource = pageSource;
        this.routerId = routerId;
    }

	/**
	 *
	 * @title: getPageType
	 * @description:
	 * @return Class<?>
     */
    @SuppressWarnings("unused")
    public Class<?> getPageType() {
        return pageType;
    }

	/**
	 *
	 * @title: getPageSource
	 * @description:
	 * @return URL
     */
    public URL getPageSource() {
        return pageSource;
    }
	/**
	 *
	 * @title: getRouterId 方法名：getRouterId
	 * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
	 * @return String 返回的是一个字符串，也就是枚举对象的路由Id
	 */
    public String getRouterId() {
        return routerId;
    }
}
