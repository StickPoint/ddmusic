package com.stickpoint.ddmusic.router;

import com.stickpoint.ddmusic.page.AccumulatePaneController;
import com.stickpoint.ddmusic.page.HomePageStage;
import com.stickpoint.ddmusic.page.MusicControlController;
import com.stickpoint.ddmusic.page.PlayerComponentController;

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
    ACCUMULATE_PANE(AccumulatePaneController.class,AccumulatePaneController.class.getResource("/fxml/AccumulatePane.fxml"),"accumulatePane");

    /**
     * 页面名称
     */
    private final Class<?> pageType;
    /**
     * 页面路由
     */
    private final URL pageSource;
    /**
     * 页面全名称
     */
    private final String routerId;
    
	/**
	 * 
	 * 构建构造方法
	 * @param pageType 页面类型，以页面的class来区分
	 * @param pageSource 页面数据源URL，classpath下的文件
	 * @param routerId 路由id，字符串，作为组件路由的关键key
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
	 * @throws
	 */
    public Class<?> getPageType() {
        return pageType;
    }
    
	/**
	 * 
	 * @title: getPageSource 
	 * @description:
	 * @return URL
	 * @throws
	 */
    public URL getPageSource() {
        return pageSource;
    }
	/**
	 * 
	 * @title: getRouterId 方法名：getRouterId
	 * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
	 * @return String 返回的是一个字符串，也就是枚举对象的路由Id
	 * @throws 不需要抛出任何异常信息
	 */
    public String getRouterId() {
        return routerId;
    }
}
