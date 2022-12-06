package com.stickpoint.ddmusic.common.enums;

/**
 * 状态码 枚举类
 * @author fntp
 * @since 2022/10/05
 */
public enum DDMusicExceptionEnum {

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
    ERROR_DATABASE_FAIL(20002, "数据库操作失败"),
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
    // 201xx 用户管理
    ERROR_TOKEN_INVALID(20101, "token异常"),
    ERROR_JWT_TOKEN_CHECK(20102, "JWT格式验证失败"),
    ERROR_LOGIN_PASSWORD_FAIL(20103, "用户名或密码错误"),
    // 202xx
    ERROR_OSS_BUCKET_NOT_FOUND(20201, "ossBucket不存在"),
    ERROR_READ_OSS_FILE_FAILED(20202, "读取OSS文件失败"),
    ERROR_ZIP_FILE_FAILED(20203, "文件打包失败"),
    ERROR_ZIP_FILE_VALUE_TYPE_FAILED(20204, "文件打包失败，内容类型不符合要求"),
    ERROR_UPLOAD_FILE_ERROR(20205, "上传文件失败，请检查OSS服务是否正常"),
    ERROR_UPLOAD_FILE_TYPE_ERROR(20206, "上传文件失败，仅支持扩展名为[%s]的文件，请检查文件类型"),
    ERROR_OSS_PARAM_ERROR(20207, "请求参数[%s]错误"),
    ERROR_PHONE_NULL(20208, "手机号为空，请输入手机号"),
    ERROR_PHONE_FAILED(20209, "手机号格式错误，请输入正确的手机号"),
    ERROR_TOKEN_FAILED(20210, "获取token失败"),
    ERROR_SEND_VERITY_CODE(20211, "发送验证码失败"),
    ERROR_VERITY_CODE_FAILED(20212, "行程核验信息失败"),
    ERROR_IDENTITY_CARD_NULL(20213, "身份证号为空，请输入身份证号"),
    ERROR_IDENTITY_FAILED(20214, "身份证号格式错误，请输入正确的身份证号"),
    ERROR_VERIFICATION_CODE_NULL(20215, "验证码为空，请输入验证码"),
    ERROR_NAME_NULL(20216, "姓名为空，请输入姓名"),
    ERROR_HISTORY_ROUTE_NULL(20217, "历史行程为空，请输入历史行程"),
    ERROR_AUTHORIZATION_TYPE_NULL(20218, "位置授权类型有误，请传入正确的位置授权类型"),
    ERROR_ROUTE_ID_NULL(20219, "行程码信息id为空，请传入行程码信息id"),
    ERROR_OTHER_PHONE_FAILED(20220, "其他手机号格式错误，请输入正确的手机号"),
    ERROR_ROUTE_CODE(20221, "未获取到正确的行程码状态"),
    ERROR_OSS_CONNECT_FAILED(20222, "OSS服务器连接超时"),
    ERROR_GET_PROMISE_INFO(20223, "获取承诺书内容异常"),
    ERROR_YIDA_FORM(20224,"获取流程表单数据异常"),
    ERROR_YIDA_USERID(20226,"获取宜搭用户ID异常"),
    ERROR_SEND_SMS_RETURN(20227, "发送验证码失败!"),
    ERROR_PROPERTIES_CENTER_FILE_PATH_IS_NULL(20228,"配置中心文件地址为空！"),
    ERROR_RESPONSE_EXCEPTION(20505, "HTTP请求失败"),
    ERROR_RESPONSE_ISNULL(20506, "HTTP请求失败返回为空"),
    // 关于SQL异常错误
    EXCEPTION_SQL_SELECT(20701,"SQL操作异常"),
    // 关于数据错误异常
    EXCEPTION_REDIS_DATA_GET_CAST(20800,"redis缓存数据异常！数据转换失败！"),
    EXCEPTION_GET_WATER_MARK(20801,"获取水印失败！"),
    EXCEPTION_GET_WATER_MARK_DATA_CAST(20802,"获取水印数据时，出现类型转换"),
    EXCEPTION_JSON_DATA_CAST_TO_OBJECT(20803,"json数据转换异常！"),
    EXCEPTION_JSON_NULL(20804,"JSON指定key为null"),
    // 关于免密操作
    EXCEPTION_WITHOUT_SECRET_USERINFO_GET(20901,"用户免密登录信息获取出错"),
    EXCEPTION_WITHOUT_SECRET_USERINFO_PRE_GET(20902,"未先获取用户信息！"),
    EXCEPTION_WITHOUT_SECRET_LOGIN(20903,"免密登录获得token失败！"),
    // 对象类
    EXCEPTION_NULL_POINTER(21001,"当前对象为空！"),
    EXCEPTION_NULL_POINTER_FOR_TWO(21002,"当前对象二次获取为空！"),
    // 204xx 特殊
    ERROR_AUTHENTICATION(20401, "没有权限"),
    // 203XX (文件相关)
    ERROR_CSS_TEMPLATE_FILE_MISS(20301, "css样式模板文件不存在，请检查"),
    ERROR_PDF_RENDER_ERROR(20302, "pdf文件渲染失败"),
    ERROR_PDF_ADD_WATER_MARK_ERROR(20303, "pdf文件添加水印失败"),
    ERROR_TEMPLATE_FILE_NOT_EXIST(20304, "模板文件[%s]不存在"),
    ERROR_TEMPLATE_PARSER(20305, "文件[%s]解析错误"),
    ERROR_PDF_DOWNLOAD(20306, "文件下载失败"),
    ERROR_PDF_PREVIEW(20307, "文件预览失败"),;
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
     *
     * @param code    状态码
     * @param message 信息
     */
    DDMusicExceptionEnum(int code, String message) {
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
        for (DDMusicExceptionEnum ignored : DDMusicExceptionEnum.values()) {
            if (ignored.getCode() == code) {
                return ignored.getMessage();
            }
        }
        return "未知异常";
    }
}
