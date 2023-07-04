package com.stickpoint.ddmusic.common.enums;


/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public enum InfoEnums {
    /**
     * search任务线程池名称
     */
    THREAD_POLL_NAME_SEARCH("searchThreadPoll"),
    /**
     * download任务线程池名称
     */
    THREAD_POLL_NAME_DOWNLOAD("downloadThreadPoll"),
    /**
     * other任务线程池名称
     */
    THREAD_POLL_NAME_OTHER("otherThreadPoll"),
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
     * 文件上传地址
     */
    FILE_UPLOAD_URL("uploadUrl"),
    /**
     * Http请求前缀
     */
    HTTP_REQUEST_PROTOCOL("http://"),
    /**
     * app系统配置版本
     */
    APP_ENV_VERSION("app.env.version"),
    /**
     * 音乐播放控制组件的播放与暂停按钮的fx:id
     */
    MUSIC_CONTROL_PLAY_OR_PAUSE_FX_ID("playerPauseOrGoon"),
    /**
     * 歌词fx:id
     */
    MUSIC_DETAIL_LRC_VIEW("lrcView"),
    /**
     * 频谱fx:id
     */
    MUSIC_DETAIL_AUDIO_SPECTRUM("audioSpectrum"),
    /**
     * 主页播放器控件CssId
     */
    HOME_PAGE_PLAYER_FX_ID("homePagePlayer"),
    /**
     * 播放器的CssId
     */
    PLAY_COMPONENT_FX_ID("playerComponent"),
    /**
     * 音乐播放组件-CssId
     */
    MUSIC_CONTROL_FX_ID("musicControl"),
    /**
     * HomePage主页：centerView 中心展示区域 JavaFX-ID
     */
    HOME_PAGE_CENTER_VIEW_FX_ID("centerView"),
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
     * 最近播放音乐列表子项-操作按钮面板-分享音乐的region内容
     */
    RECENTLY_PLAY_LIST_ITEM_SHARE_MUSIC_REGION_VALUE("-fx-background-color: gray; -fx-shape: \"M853.333333 533.333333a32 32 0 0 1 64 0v266.666667c0 64.8-52.533333 117.333333-117.333333 117.333333H224c-64.8 0-117.333333-52.533333-117.333333-117.333333V256c0-64.8 52.533333-117.333333 117.333333-117.333333h277.333333a32 32 0 0 1 0 64H224a53.333333 53.333333 0 0 0-53.333333 53.333333v544a53.333333 53.333333 0 0 0 53.333333 53.333333h576a53.333333 53.333333 0 0 0 53.333333-53.333333V533.333333z m-42.058666-277.333333l-89.792-95.402667a32 32 0 0 1 46.613333-43.861333l140.544 149.333333C927.861333 286.485333 913.376 320 885.333333 320H724.704C643.029333 320 576 391.210667 576 480v192a32 32 0 1 1-64 0V480c0-123.296 94.784-224 212.704-224h86.570667z\""),
    /**
     * 最近播放音乐列表子项-操作按钮面板-删除音乐的region内容
     */
    RECENTLY_PLAY_LIST_ITEM_DELETE_MUSIC_REGION_VALUE("-fx-background-color: gray; -fx-shape: \"M840 288H688v-56c0-40-32-72-72-72h-208C368 160 336 192 336 232V288h-152c-12.8 0-24 11.2-24 24s11.2 24 24 24h656c12.8 0 24-11.2 24-24s-11.2-24-24-24zM384 288v-56c0-12.8 11.2-24 24-24h208c12.8 0 24 11.2 24 24V288H384zM758.4 384c-12.8 0-24 11.2-24 24v363.2c0 24-19.2 44.8-44.8 44.8H332.8c-24 0-44.8-19.2-44.8-44.8V408c0-12.8-11.2-24-24-24s-24 11.2-24 24v363.2c0 51.2 41.6 92.8 92.8 92.8h358.4c51.2 0 92.8-41.6 92.8-92.8V408c-1.6-12.8-12.8-24-25.6-24zM444.8 744v-336c0-12.8-11.2-24-24-24s-24 11.2-24 24v336c0 12.8 11.2 24 24 24s24-11.2 24-24zM627.2 744v-336c0-12.8-11.2-24-24-24s-24 11.2-24 24v336c0 12.8 11.2 24 24 24s24-11.2 24-24z\""),
    /**
     * 最近播放音乐列表子项-操作按钮面板-收藏音乐的region内容
     */
    RECENTLY_PLAY_LIST_ITEM_FAV_MUSIC_REGION_VALUE("-fx-background-color: gray; -fx-shape: \"M508.771 62.025c-248.377 0-449.727 201.353-449.727 449.73s201.349 449.729 449.727 449.729c248.379 0 449.729-201.351 449.729-449.729S757.151 62.025 508.771 62.025z m0 860.35c-226.781 0-410.622-183.841-410.622-410.62 0-226.78 183.841-410.622 410.622-410.622s410.622 183.841 410.622 410.622c0 226.779-183.841 410.62-410.622 410.62z m269.888-508.647H587.546l-59.128-181.671a19.536 19.536 0 0 0-18.589-13.498h-0.086a19.553 19.553 0 0 0-18.56 13.652l-57.372 181.516H243.767a19.552 19.552 0 0 0-18.636 13.635 19.552 19.552 0 0 0 7.37 21.902l147.71 104.167-54.802 179.799a19.55 19.55 0 0 0 7.121 21.46c6.703 4.949 15.774 5.1 22.61 0.383l155.92-106.512 151.406 106.38a19.52 19.52 0 0 0 11.237 3.553c4.104 0 8.209-1.3 11.668-3.86a19.56 19.56 0 0 0 6.893-21.843l-58.011-174.894 155.594-108.576a19.567 19.567 0 0 0 7.469-21.902 19.566 19.566 0 0 0-18.657-13.691zM599.911 534.163c-7.094 4.965-10.093 13.975-7.372 22.187l43.966 132.56-114.055-80.143a19.48 19.48 0 0 0-22.263-0.135L379.046 691.39l42.697-140.079a19.567 19.567 0 0 0-7.436-21.695l-108.881-76.78h142.716c8.527 0 16.079-5.538 18.646-13.652l43.319-137.046 44.654 137.198a19.536 19.536 0 0 0 18.589 13.5h143.117l-116.556 81.327z\""),
    /**
	 * 音乐继续播放状态: music:player:status:goon
	 */
	MUSIC_PLAY_STATUS_GOON_VALUE("music:player:status:goon"),
	/**
	 * 音乐暂停播放状态: music:player:status:pause
	 */
	MUSIC_PLAY_STATUS_PAUSE_VALUE("music:player:status:pause"),
    /**
     * 内置音乐播放器ID
     */
    MUSIC_INNER_PLAYER_ID("ddmusic:inner:component:player"),
    /**
     * 发现音乐主页
     * TODO 这里需要注意，如果修改了发现音乐页面的根节点的id，这里就是需要修改的
     */
    FIND_MUSIC_SCROLL_PANE_CSS_ID("findMusic"),
    /**
     * 根节点 播放详情
     */
    ROOT_NODE_PLAY_DETAIL_CSS_ID("playDetail"),
    /**
     * 播放详情页面根节点fxId
     */
    ROOT_NODE_PLAY_DETAIL_FX_ID("playDetailRootNode"),
    /**
     * 通用下标枚举 0
     */
    INDEX_ZERO(0),
    /**
     * 通用数字枚举 5
     */
    COMMON_NUMBER_5(5),
    /**
     * 通用数字枚举 10
     */
    COMMON_NUMBER_10(10),
    /**
     * 通用数字枚举 200
     */
    COMMON_NUMBER_200(200),
    /**
     * 音乐播放器远程服务状态
     */
    APP_NETWORK_STATUS_OK(200),
    /**
     * 应用软件通用的分隔符号
     */
    APP_PROPERTIES_COMMON_SYMBOL_SPLIT(","),
    /**
     * 网易云请求前缀
     */
    API_NET_EASY_URL_PREFIX("api.sourceList.wpMusicApi.platform.neteasy.prefix"),
    /**
     * 网易云歌曲歌词-基础地址
     */
    API_NET_EASY_URL_GET_LYRIC("api.sourceList.wpMusicApi.platform.neteasy.getLyric"),
    /**
     * 顶点音乐请求的一级baseUrl
     */
    API_DDMUSIC_SOURCE_LIST_WP_MUSIC_HOST("api.sourceList.wpMusicApi.host"),
    /**
     * 顶点音乐请求的二级baseUrl
     * api.sourceList.wpMusicApi.prefix
     */
    API_DDMUSIC_SOURCE_LIST_WP_MUSIC_PREFIX("api.sourceList.wpMusicApi.prefix"),
    /**
     * WP_Music基础服务请求-网易云音乐基础前缀地址
     * api.sourceList.wpMusicApi.platform.neteasy.prefix
     */
    NETEASY_PREFIX("api.sourceList.wpMusicApi.platform.neteasy.prefix"),
    /**
     * WP_Music基础服务请求-网易云搜索音乐接口地址
     * api.sourceList.wpMusicApi.platform.neteasy.getSearch
     */
    NETEASY_GET_SEARCH("api.sourceList.wpMusicApi.platform.neteasy.getSearch"),
    /**
     * WP_Music基础服务请求-网易云音乐获取播放地址接口
     * api.sourceList.wpMusicApi.platform.neteasy.song
     */
    NETEASY_GET_SONG("api.sourceList.wpMusicApi.platform.neteasy.getSong"),
    /**
     * WP_Music基础服务请求-网易云音乐获取播放地址接口
     * api.sourceList.wpMusicApi.platform.neteasy.getLyric
     */
    NETEASY_GET_LRC("api.sourceList.wpMusicApi.platform.neteasy.getLyric"),
    /**
     * WP_Music基础服务请求-网易云每日推荐音乐接口地址
     * api.sourceList.wpMusicApi.platform.neteasy.getRecommend
     */
    NETEASY_GET_RECOMMEND("api.sourceList.wpMusicApi.platform.neteasy.getRecommend"),
    /**
     * WP_Music基础服务请求端口
     * api.sourceList.wpMusicApi.por
     */
    API_DDMUSIC_BASE_WP_MUSIC_PORT("api.sourceList.wpMusicApi.port"),
    /**
     * WP_Music基础服务请求前缀
     */
    API_DDMUSIC_BASE_WP_MUSIC_PREFIX("api.sourceList.wpMusicApi.prefix"),
    /**
     * myFreeMusic服务状态
     */
    API_DDMUSIC_MY_FREE_MUSIC_STATUS("api.sourceList.myFreeMusic.status"),
    /**
     * WP_MUSIC服务状态
     */
    API_DDMUSIC_WP_MUSIC_STATUS("api.sourceList.wpMusicApi.status"),
    /**
     * ddmusic初始化spi机制加载之后默认的base地址，默认以这个为基准
     */
    API_FINAL_REQUEST_BASE_URL("app.finalRequestBaseUrl"),
    /**
     * ddmusic初始化推荐音乐歌单列表内容
     */
    APP_MUSIC_RECOMMEND_LIST_DAILY("app.music.recommendList.daily"),
    /**
     * ddmusic初始化热门歌单列表内容
     */
    APP_MUSIC_HOT_LIST_DAILY("app.music.hotList.daily"),
    /**
     * ddmusic初始化音浪前线歌单列表内容
     */
    APP_MUSIC_WAVE_LIST_DAILY("app.music.waveList.daily"),
    /**
     * ddmusic初始化轮播json
     */
    APP_MUSIC_BANNER_LIST_DAILY("app.music.banner.daily"),
    /**
     * 音乐状态 被比较的值 是一个常量枚举
     */
    APP_PROPERTIES_STATUS_OK_INT(200),
    /**
     * 音乐平台 网易云
     */
    APP_PLATFORM_NAME_NET_EASY("netEasy"),
    /**
     * 音乐平台 酷我
     */
    APP_PLATFORM_NAME_KU_WO("kuwo"),
    /**
     * 音乐平台 酷狗
     */
    APP_PLATFORM_NAME_KU_GOU("kugou"),
    /**
     * 音乐平台 咪咕
     */
    APP_PLATFORM_NAME_MI_GU("migu"),
    /**
     * 音乐平台 企鹅
     */
    APP_PLATFORM_NAME_QQ("qq"),
    /**
     * 网易云音乐资源篇平台前缀
     */
    API_DDMUSIC_BASE_WP_MUSIC_PLATFORM_NET_EASY("api.sourceList.wpMusicApi.platform.neteasy.prefix"),
    /**
     * 应用配置
     */
    APP_PROPERTIES_LOCAL_NAME("applicationProperties"),
    /**
     * 最近播放列表容器Pane的JavaFX-ID
     */
    RECENTLY_PLAYLIST_PANE_FX_ID("recentlyPlayListPane");

    /**
     * 提示信息
     */
    private final String infoContent;

    private final Integer infoNumber;

    /**
     * 构造方法
     * @param infoContent 提示信息内容
     */
    InfoEnums(String infoContent) {
        this.infoContent = infoContent;
        this.infoNumber = null;
    }

    InfoEnums(Integer infoNumber) {
        this.infoContent = null;
        this.infoNumber = infoNumber;
    }

	/**
	 *
	 * @title: getInfoContent 方法名getInfoContent
	 * @description: 返回枚举信息的message
	 * @return String 返回当前枚举的message信息
	 */
    public String getInfoContent() {
        return infoContent;
    }

    public Integer getNumberInfo(){ return infoNumber; }

}
