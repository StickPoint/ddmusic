package com.stickpoint.ddmusic.common.utils;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpCall;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.OkHttps;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Objects;


/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-11-02  21:58
 * @Description: TODO
 * @Version: 1.0
 */
public class HttpUtils {
    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 系统Http请求工具类实例
     */
    private static HttpUtils INSTANCE;
    /**
     * 系统Http请求对象
     */
    private static final HTTP HTTP = OkHttps.getHttp();

    /**
     * 私有化构造，启用单例模式
     */
    private HttpUtils() { }

    /**
     * 获得系统HttpUtils对象实例
     * @return 返回一个HttpUtils请求对象
     */
    public static HttpUtils getInstance() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (HttpUtils.class) {
                log.info("初始化加载HttpIUtils工具类对象");
                INSTANCE = new HttpUtils();
            }
        }
        return INSTANCE;
    }

    /**
     * 进行基础的常见的Get请求
     * @param requestUrl 请求地址
     * @param requestParams 请求参数
     * @return 返回一个请求的最终json响应字符串
     */
    public String doNormalGet(String requestUrl, Map<String, String> requestParams){
        HttpCall httpCall = HTTP.async(requestUrl) .addUrlPara(requestParams).get();
        HttpResult httpResult = httpCall.getResult();
        return getResponse(httpResult);
    }

    public String doAbsoluteGet(String requestUrl) {
        HttpCall httpCall = HTTP.async(requestUrl).get();
        HttpResult result = httpCall.getResult();
        return getResponse(result);
    }

    /**
     * 封装内部的返回基本响应的方法
     * @param httpResult httpResult 请求结果对象
     * @return 返回一个相应的字符串内容
     */
    private String getResponse(HttpResult httpResult) {
        String responseStr = null;
        if (InfoEnums.APP_NETWORK_STATUS_OK.getNumberInfo() == httpResult.getStatus()) {
            responseStr = httpResult.getBody().toString();
        }
        if (Objects.nonNull(responseStr)) {
            // 说明Http请求成功存在响应
            return responseStr;
        }
        return null;
    }

}