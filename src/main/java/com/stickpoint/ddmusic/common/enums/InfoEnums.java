package com.stickpoint.ddmusic.common.enums;


/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public enum InfoEnums {
    /**
     * 左边菜单栏-菜单-不可按-名字标签：leftTabMenu
     */
    LEFT_TAB_MENU("leftTabMenu"),
    /**
     * 左边菜单栏-菜单子项-最小Pane：menuPane-selection-little
     */
    LEFT_TAB_MENU_ITEM_LITTLE_PANE("menuPane-selection-little"),
    /**
     * 左边菜单栏-菜单子项-菜单文字：menuText
     */
    LEFT_TAB_MENU_ITEM_MENU_TEXT("menuText"),
    /**
     * 配置文件地址头
     */
    FILE_PATH_HEADER("http"),
    /**
     * app系统配置版本
     */
    APP_ENV_VERSION("app.env.version"),
    /**
     * 主页播放器控件CssId
     */
    HOME_PAGE_PLAYER_CSS_ID("#homePagePlayer"),
    /**
     * 播放器的CssId
     */
    PLAY_COMPONENT_CSS_ID("#playerComponent"),
    /**
     * 音乐播放组件-CssId
     */
    MUSIC_CONTROL_CSS_ID("#musicControl"),
    /**
     * 音乐播放组件-Id
     */
    MUSIC_CONTROL_ID("musicControl"),
    /**
     * 系统初始化语句
     */
    APP_PAGE_LOADER_SUCCESS("ApplicationPageLoader inits successfully !"),
	/**
	 * 音乐播放状态
	 */
	MUSIC_PLAY_STATUS("music:player:status:"),
	/**
	 * 音乐播放状态-继续-svg路径
	 */
	MUSIC_PLAY_STATUS_GOON_SVG_PATH("M512 1024C228.266667 1024 0 795.733333 0 512S228.266667 0 512 0s512 228.266667 512 512-228.266667 512-512 512z m0-42.666667c260.266667 0 469.333333-209.066667 469.333333-469.333333S772.266667 42.666667 512 42.666667 42.666667 251.733333 42.666667 512s209.066667 469.333333 469.333333 469.333333z m-106.666667-682.666666c12.8 0 21.333333 8.533333 21.333334 21.333333v384c0 12.8-8.533333 21.333333-21.333334 21.333333s-21.333333-8.533333-21.333333-21.333333V320c0-12.8 8.533333-21.333333 21.333333-21.333333z m213.333334 0c12.8 0 21.333333 8.533333 21.333333 21.333333v384c0 12.8-8.533333 21.333333-21.333333 21.333333s-21.333333-8.533333-21.333334-21.333333V320c0-12.8 8.533333-21.333333 21.333334-21.333333z"),
	/**
	 * 音乐播放状态-暂停-svg路径
	 */
	MUSIC_PLAY_STATUS_PAUSE_SVG_PATH("M692.224 495.616 692.224 495.616 692.224 495.616c-4.096-4.096-4.096-4.096-4.096-4.096l-258.048-147.456 0 0c-4.096-4.096-8.192-4.096-12.288-4.096-12.288 0-20.48 8.192-20.48 20.48l0 303.104c0 12.288 8.192 20.48 20.48 20.48 4.096 0 8.192 0 12.288-4.096l0 0 258.048-147.456c0 0 0 0 0 0l4.096 0 0 0c4.096-4.096 8.192-8.192 8.192-16.384S696.32 499.712 692.224 495.616zM438.272 630.784 438.272 393.216l208.896 118.784L438.272 630.784zM512 98.304C282.624 98.304 98.304 282.624 98.304 512s184.32 413.696 413.696 413.696c229.376 0 413.696-184.32 413.696-413.696S741.376 98.304 512 98.304zM512 888.832c-208.896 0-376.832-167.936-376.832-376.832 0-208.896 167.936-376.832 376.832-376.832 208.896 0 376.832 167.936 376.832 376.832C888.832 720.896 720.896 888.832 512 888.832z"),
	/**
	 * 音乐继续播放状态: music:player:status:goon
	 */
	MUSIC_PLAY_STATUS_GOON_VALUE("music:player:status:goon"),
	/**
	 * 音乐暂停播放状态: music:player:status:pause
	 */
	MUSIC_PLAY_STATUS_PAUSE_VALUE("music:player:status:pause");
	
    /**
     * 提示信息
     */
    private final String infoContent;

    /**
     * 构造方法
     * @param infoContent 提示信息内容
     */
    InfoEnums(String infoContent) {
        this.infoContent = infoContent;
    }
    
	/**
	 * 
	 * @title: getInfoContent 方法名getInfoContent
	 * @description: 返回枚举信息的message
	 * @return String 返回当前枚举的message信息
	 * @throws 不需要抛出任何异常
	 */
    public String getInfoContent() {
        return infoContent;
    }
}
