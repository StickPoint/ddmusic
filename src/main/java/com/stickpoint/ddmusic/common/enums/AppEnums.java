package com.stickpoint.ddmusic.common.enums;

/**
 * @Author SunChengXin_0303
 * @ClassName AppEnums.Class
 * @PackageName com.stickpoint.devtools.common.enums
 * @Date 2022年10月28日 13:13
 * @since 1.5
 */
@SuppressWarnings("unused")
public enum AppEnums {
    /**
     * 网络状态码200
     */
    APP_NETWORK_STATUS_OK(200),
    /**
     * 网络端口数量
     */
    APP_NETWORK_POSTS_NUMBER(65535),
    /**
     * 端口占用列表-key
     */
    APP_SYSTEM_INFO_PORT_ALREADY_IN_USED_LIST("alreadyInUsedPortList"),
    /**
     * 端口可用列表-key
     */
    APP_SYSTEM_INFO_PORT_CAN_BE_USED_LIST("canBeUsedPortList"),
    /**
     * 菜单面板子项菜单的样式css文件的Path
     */
    FUNCTION_CENTER_MENU_ITEM_CSS_URI("菜单面板子项菜单的样式css文件的Path", "/css/leftMenuCenter.css" ),
    /**
     * 菜单面板的menuTree的fx-id
     */
    FUNCTION_CENTER_MENU_PANE_FX_ID_INFO("菜单面板的menuTree的fx-id","menuTree" ),
    /**
     * 菜单栏小菜单背景css样式id
     */
    MENU_PANE_SELECTION_LITTLE_ID("菜单栏小菜单背景","menuPane-selection-little" ),
    /**
     * 五大菜单主面板id之一：（1）sysStatusPane
     */
    LEFT_PANE_MENU_ID_SYS_STATUS_PANE("五大菜单主面板id之一：（1）sysStatusPane","sysStatusPane" ),
    /**
     * 五大菜单主面板id之一：（2）devPane
     */
    LEFT_PANE_MENU_ID_DEV_PANE("五大菜单主面板id之一：（2）devPane","devPane" ),
    /**
     * 五大菜单主面板id之一：（3）opPane
     */
    LEFT_PANE_MENU_ID_OP_PANE("五大菜单主面板id之一：（3）opPane","opPane" ),
    /**
     * 五大菜单主面板id之一：（4）webPane
     */
    LEFT_PANE_MENU_ID_WEB_PANE("五大菜单主面板id之一：（4）webPane","webPane" ),
    /**
     * 五大菜单主面板id之一：（5）sysPane
     */
    LEFT_PANE_MENU_ID_SYS_PANE("五大菜单主面板id之一：（5）sysPane","sysPane" ),
    /**
     * 当前ip地址
     */
    INFO_CURRENT_IP("当前ip地址","当前IP："),
    /**
     * 配置文件地址头
     */
    FILE_PATH_HEADER("http"),
    /**
     * 下标为0
     */
    INDEX_ZERO(0),
    /**
     * 通用数字 256
     */
    COMMON_NUMBER_256(256),
    /**
     * 普通级别Toast
     */
    TOAST_INFO(0),
    /**
     * 警告级别Toast
     */
    TOAST_WARNING(1),
    /**
     * 错误级别Toast
     */
    TOAST_ERROR(2),
    /**
     * 通用数字 3
     */
    COMMON_NUMBER_THREE(3),
    /**
     * 通用数字 1
     */
    COMMON_NUMBER_ONE(1),
    /**
     * 通用数字 2
     */
    COMMON_NUMBER_TWO(2),
    /**
     * 天气数据长度 固定长度 7
     */
    COMMON_NUMBER_SEVEN(7),
    /**
     * 应用主场景Stage
     */
    APPLICATION_MAIN_STAGE("mainStage"),
    /**
     * 应用stackPane
     */
    APPLICATION_MAIN_STACK_PANE("stackPane"),
    /**
     * 应用系统托盘
     */
    APPLICATION_TRAY("tray"),
    /**
     * 应用名称
     */
    APPLICATION_NAME("顶点音乐"),
    /**
     * 系统远程Yaml文件配置
     */
    APP_REMOTE_YAML_PATH_KEY("systemPropertiesFileUrl"),
    /**
     * 系统版本id
     */
    SYSTEM_VERSION_ID("system.version.id"),
    /**
     * 菜单栏文字id
     */
    LEFT_PANE_MENU_TEXT_ID("菜单栏文字id","menuText" );
    /**
     * fx应用内部infoName-key
     */
    private final String infoName;
    /**
     * fx应用内部infoValue-值
     */
    private final String infoValue;
    /**
     * 数字枚举数值
     */
    private final Integer numberInfo;

    /**
     * 带描述枚举
     * @param infoName 枚举描述
     * @param infoValue 枚举值
     */
    AppEnums(String infoName, String infoValue){
        this.infoName = infoName;
        this.infoValue=infoValue;
        numberInfo = null;
    }

    /**
     * 单独字符串枚举不带描述
     * @param infoValue 字符串枚举数据
     */
    AppEnums(String infoValue) {
        this.infoName = null;
        this.infoValue=infoValue;
        this.numberInfo=null;
    }

    /**
     * 单独数字枚举不带描述
     * @param numberInfo 数字枚举数值
     */
    AppEnums(Integer numberInfo){
        this.numberInfo = numberInfo;
        this.infoValue = null;
        this.infoName= null;
    }

    /**
     *
     * @title: getRouterId 方法名：getRouterId
     * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
     * @return String 返回的是一个字符串，也就是枚举对象的路由Id
     */
    public String getInfoName() {
        return infoName;
    }

    /**
     *
     * @title: getRouterId 方法名：getRouterId
     * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
     * @return String 返回的是一个字符串，也就是枚举对象的路由Id
     */
    public String getInfoValue() {
        return infoValue;
    }

    public Integer getNumberInfo(){ return numberInfo; }
}
