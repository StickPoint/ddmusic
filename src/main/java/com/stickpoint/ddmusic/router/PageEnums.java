package com.stickpoint.ddmusic.router;

import com.stickpoint.ddmusic.page.AccumulatePaneController;
import com.stickpoint.ddmusic.page.HomePageStage;
import com.stickpoint.ddmusic.page.MusicControlController;
import com.stickpoint.ddmusic.page.PlayDetailController;
import com.stickpoint.ddmusic.page.PlayerComponentController;
import com.stickpoint.ddmusic.page.RecentlyPlayListController;
import com.stickpoint.ddmusic.page.SoundControlController;

import java.net.URL;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public enum PageEnums {
    /**
     * 播放音乐组件
     */
    PLAYER_COMPONENT(PlayerComponentController.class, PlayerComponentController.class.getResource("/fxml/PlayerComponent.fxml"),"playerComponent"),
    /**
     * 音乐播放控制组件：暂停开始、上一曲下一曲组件。
     */
    MUSIC_CONTROL(MusicControlController.class,MusicControlController.class.getResource("/fxml/MusicControl.fxml"),"musicControl"),
    /**
     * Home首页-页面
     */
    HOMEPAGE(HomePageStage.class, HomePageStage.class.getResource("/fxml/HomePage.fxml"),"homePage"),
    /**
     * 额外菜单面板--累计信息卡
     */
    ACCUMULATE_PANE(AccumulatePaneController.class,AccumulatePaneController.class.getResource("/fxml/AccumulatePane.fxml"),"accumulatePane"),
    /**
     * 播放详情页面
     */
    PLAY_DETAIL_PAGE(PlayDetailController.class,PlayDetailController.class.getResource("/fxml/PlayDetailPage.fxml"),"playDetailPage"),
    /**
     * 最近播放页面--最近播放列表
     */
    RECENTLY_PLAY_LIST(RecentlyPlayListController.class,RecentlyPlayListController.class.getResource("/fxml/RecentLyPlayList.fxml"),"recentlyPlayList"),
    /**
     * 音量控制组件面板--控制音量
     */
    SOUND_CONTROL(SoundControlController.class,SoundControlController.class.getResource("/fxml/SoundControl.fxml"),"soundControl");

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
