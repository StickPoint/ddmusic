package com.stickpoint.ddmusic.common.utils;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author fntp
 */
@SuppressWarnings("unused")
@UtilityClass
public class HttpUtils {
    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 系统Http请求工具类实例
     */
    private static HttpUtils instance;
    private static final Integer WRITE_TIME_OUT = 10000;
    private static final Integer READ_TIME_OUT = 10000;

    public static String doAbsoluteGet(String requestUrl){
        Map<String,String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
        headerMap.put("Accept", "*/*");
        headerMap.put("Host","sinsy.xyz:5000");
        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("Connection", "keep-alive");
        try {
            return doGet(requestUrl,headerMap);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 携带参数请求音乐接口
     * @param requestUrl 接口请求地址
     * @param paramMap 接口请求参数 map形式传入，遍历后拼接成最终请求地址
     * @return 返回一个最终请求的结果
     */
    public static String doGetWithParams(String requestUrl, Map<String,Object> paramMap) {
        StringBuilder builder = new StringBuilder(requestUrl);
        if (paramMap.size() > 0) {
            builder.append("?");
            Iterator<Entry<String, Object>> iterator = paramMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                Object value = entry.getValue();
                if (value instanceof String){
                    value = URLEncoder.encode((String) entry.getValue(), StandardCharsets.UTF_8);
                }
                builder.append(entry.getKey()).append("=").append(value);
                if (iterator.hasNext()) {
                    builder.append("&");
                }
            }
            String finalRequestUrl = builder.toString();
            return doAbsoluteGet(finalRequestUrl);
        }
        return doAbsoluteGet(requestUrl);
    }

    /**
     * 使用基于Java原生的HTTP请求来完成请求操作，因为okhttp会有请求重定向的问题，无法使用okhttp3因为不支持模块化
     * @param requestUrl 请求地址
     * @return 返回一个请求的响应结果json
     */
    public static String doMsWeatherInfoGet(String requestUrl){
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Host", "assets.msn.cn");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Type", "text/plain");
            int responseCode = connection.getResponseCode();
            log.info("请求状态码 {}",responseCode);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = inputReader.readLine()) != null) {
                responseBuilder.append(line);
            }
            inputReader.close();
            return responseBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new DdmusicException(DdMusicExceptionEnums.FAILED);
    }



    /**
     * 发送 HTTP GET 请求
     * @param url 请求url地址
     * @return JSON响应
     * @throws IOException 抛出异常
     */
    public static String doGet(String url,Map<String,String> headerMap) throws IOException {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = initConnection(url, "GET", headerMap);
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            if (conn.getResponseCode() == InfoEnums.APP_NETWORK_STATUS_OK.getNumberInfo()) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } else {
                log.error("HTTP GET 请求错误：{}" ,conn.getResponseCode());
            }
        } finally {
            conn.disconnect();
        }
        return result.toString();
    }

    /**
     * 通用的方式启动网络引擎
     * @param requestUrl 请求地址
     */
    private static HttpURLConnection initConnection(String requestUrl, String requestMethod){
       return initConnection(requestUrl, requestMethod, Collections.emptyMap());
    }

    /**
     * 通用的方式启动网络引擎
     * @param requestUrl 请求地址
     */
    private static HttpURLConnection initConnection(String requestUrl, String requestMethod, Map<String,String> otherHeader){
        HttpURLConnection conn = null;
        try {
            URL realUrl = new URL(requestUrl);
            conn = (HttpURLConnection) realUrl.openConnection();
            otherHeader.forEach(conn::setRequestProperty);
            conn.setRequestMethod(requestMethod);
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setConnectTimeout(WRITE_TIME_OUT);
            conn.setReadTimeout(READ_TIME_OUT);
            conn.connect();
        } catch (IOException e) {
           log.error(e.getMessage());
        }
        return conn;
    }

    /**
     * 发送 HTTP POST 请求
     * @param url 地址
     * @param data 内容
     * @return 结果
     */
    @SuppressWarnings("unused")
    public String sendPost(String url, String data) throws IOException {
        HttpURLConnection conn = initConnection(url, "POST");
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            conn.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            if (conn.getResponseCode() == InfoEnums.APP_NETWORK_STATUS_OK.getNumberInfo()) {

                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } else {
                log.info("HTTP POST 请求错误： {}", conn.getResponseCode());
            }
        } finally {
           conn.disconnect();
        }
        return result.toString();
    }

    /**
     * 发送 HTTP PUT 请求
     * @param url 地址
     * @param data 内容
     * @return 结果
     */
    @SuppressWarnings("unused")
    public String sendPut(String url, String data) throws IOException {
        HttpURLConnection conn = initConnection(url,"PUT");
        StringBuilder result = new StringBuilder();
        try( BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            conn.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            if (conn.getResponseCode() == InfoEnums.APP_NETWORK_STATUS_OK.getNumberInfo()) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } else {
                log.info("HTTP PUT 请求错误：{}", conn.getResponseCode());
            }
        } finally {
            conn.disconnect();
        }
        return result.toString();
    }

    /**
     * 发送 HTTP DELETE 请求
     * @param url 地址
     * @return 结果
     */
    @SuppressWarnings("unused")
    public String sendDelete(String url) throws IOException {
        HttpURLConnection conn = initConnection(url,"DELETE");
        StringBuilder result = new StringBuilder();
        try( BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            if (conn.getResponseCode() == InfoEnums.APP_NETWORK_STATUS_OK.getNumberInfo()) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } else {
                log.info("HTTP DELETE 请求错误：{}", conn.getResponseCode());
            }
        } finally {
            conn.disconnect();
        }
        return result.toString();
    }

}
