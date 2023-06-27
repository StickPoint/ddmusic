package com.stickpoint.ddmusic.common.enums;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
@SuppressWarnings("unused")
public enum DdMusicExceptionEnums {

    /**
     * 请求正常
     */
    ALI_SUCCESS(200,"success"),
    // 429 接口请求过于频繁
    ALI_BLOCK(429,"so frequent for a while"),
    // 成功
    SUCCESS(20000, "success"),
    // 错误
    FAILED(500, "服务器内部错误"),
    // 401xx 未授权
    ERROR_LOGIN_UNAUTHORIZED(40101, "操作失败，未授权或授权已过期"),
    ERROR_LOGIN_ERROR(40102, "用户登录失败"),
    ERROR_EXCEED_LIMIT(40103,"系统繁忙，请稍后再试!"),
    ERROR_NO_AUTH(40104,"操作失败，当前用户无访问权限!"),
    ERROR_NO_OPERATE_AUTH(40105,"当前用户无此操作权限"),
    // 200xx 通用错误
    ERROR_DATA_TRANS_EXCEPTION(20001, "数据转换错误"),
    ERROR_JSON_PARSER(20003, "json结果解析错误"),
    ERROR_JSON_PARAM(20004, "json参数解析错误"),
    ERROR_OPERATION_FAILED(20005, "操作失败"),
    ERROR_FILE_PARSER(20006, "文件解析错误"),
    ERROR_FILE_NOT_EXSIT(20007, "文件不存在"),
    ERROR_PARAM(20008, "参数错误"),
    ERROR_HTTP(20009, "HTTP通信异常"),
    ERROR_FTP(20010, "FTP异常"),
    ERROR_SFTP(20011, "SFTP异常"),
    ERROR_CODE_TRANS(20012, "编码转换异常"),
    ERROR_GET_TOKEN(20013, "请求异常，获取TOKEN异常"),
    ERROR_REQUEST(20014, "请求异常，请检查方法及参数是否符合要求!"),
    ERROR_PARAM_NULL(20015, "参数[%s]不能为空"),
    ERROR_PARAM_ERROR(20016, "请求参数错误[%s]"),
    ERROR_FILE_UPLOAD(20017, "文件上传失败,无法获取文件上传URL链接"),
    ERROR_FILE_UPLOAD_FILE_NAME_ERROR(20018, "文件上传成功,但文件名获取失败~"),
    ERROR_FAILED_TO_GET_LYRIC(20019,"获取歌词失败"),
    ERROR_MUSIC_FILE_PATH_NOT_EXIST(20020,"音乐文件不存在"),
    ERROR_MUSIC_OBJECT_NOT_FOUND(20021,"待播放的音乐对象数据不正确！"),
    ERROR_MUSIC_PLAY_DETAIL_NOT_FOUND(20022,"点击音乐封面的时播放详情界面丢失不见"),

    // 201xx 用户管理
    ERROR_TOKEN_INVALID(20101, "token异常"),
    ERROR_JWT_TOKEN_CHECK(20102, "JWT格式验证失败"),
    ERROR_LOGIN_PASSWORD_FAIL(20103, "用户名或密码错误"),

    // 202xx
    ERROR_ZIP_FILE_FAILED(20203, "文件打包失败"),
    ERROR_OSS_PARAM_ERROR(20207, "请求参数[%s]错误"),
    ERROR_PHONE_NULL(20208, "手机号为空，请输入手机号"),
    ERROR_PHONE_FAILED(20209, "手机号格式错误，请输入正确的手机号"),
    ERROR_RESPONSE_EXCEPTION(20505, "HTTP请求失败"),
    ERROR_RESPONSE_ISNULL(20506, "HTTP请求失败返回为空"),

    // 关于SQL异常错误
    EXCEPTION_SQL_SELECT(20701,"SQL操作异常"),

    // 204xx 特殊
    ERROR_AUTHENTICATION(20401, "没有权限"),
    ERROR_IS_NOT_JSON(20017, "response结果不是json"),
    ERROR_ACCESS_TOKEN_IS_NULL(20018,"accessToken为空"),
    ERROR_FXML_NULL(20019,"fxml文件解析失败"),
    ERROR_DATA_ACCESS(20020,"数据解析失败"),
    ERROR_MOVED_BACKWARD(20021,"Clock moved backwards.  Refusing to generate id"),
    ERROR_PROP_URL_ERROR(20022,"远程配置文件出错，请检查"),
    ERROR_PROPERTIES_CENTER_FILE_PATH_IS_NULL(20023,"远程服务地址不存在"),
    ;
    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    /**
     * 构造方法
     * @param code    状态码
     * @param message 信息
     */
    DdMusicExceptionEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误代码
     * @return 错误代码
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取描述
     * @return 描述信息
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 根据状态码获取信息
     * @param code 状态码
     * @return 信息
     */
    public static String getMessageByCode(int code) {
        for (DdMusicExceptionEnums codeEnum : DdMusicExceptionEnums.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum.getMessage();
            }
        }
        return "未知异常";
    }
}
