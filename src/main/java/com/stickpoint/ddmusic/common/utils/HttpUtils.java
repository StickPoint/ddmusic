package com.stickpoint.ddmusic.common.utils;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class HttpUtils {
    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 系统Http请求工具类实例
     */
    private static HttpUtils instance;
    /**
     * 写超时时间
     */
    private static final Integer WRITE_TIME_OUT = 10000;
    /**
     * 读超时时间
     */
    private static final Integer READ_TIME_OUT = 10000;
    /**
     * 字节长度
     */
    private static final Integer BYTE_SIZE = 1024;
    /**
     * url编码中%23表示 #
     */
    private static final String SIGN_JIN = "%23";
    /**
     * Http文件上传响应编码201
     */
    private static final Integer HTTP_CODE_201 = 201;
    /**
     * Http文件上传响应编码202
     */
    private static final Integer HTTP_CODE_202 = 202;

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

    /**
     * 文件上传服务
     * @param file 传入一个文件
     * @param url 文件上传地址
     * @param tdnum 文件上传的时间戳
     * @param filename 文件名称
     * @throws Exception 抛出一个潜在的异常
     */
    //public static void binupfile(File file, String url, int tdnum, String filename) throws Exception {
    //    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
    //    conn.setChunkedStreamingMode(0);
    //    conn.setRequestProperty("Content-Length",String.valueOf(file.length()));
    //    conn.setRequestProperty("Content-Type", "application/octet-stream");
    //    conn.setRequestProperty("Origin", "https://img.mediy.cn");
    //    conn.setRequestProperty("Host", "mediycn-my.sharepoint.com");
    //    conn.setRequestProperty("Referer", "https://img.mediy.cn/");
    //    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
    //    conn.setRequestMethod("PUT");
    //    conn.connect();
    //    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
    //        InputStream is = conn.getInputStream();
    //        BufferedReader br = new BufferedReader(new InputStreamReader(is));
    //        StringBuilder response = new StringBuilder();
    //        String line;
    //        while ((line = br.readLine()) != null) {
    //            response.append(line);
    //        }
    //        br.close();
    //        is.close();
    //        conn.disconnect();
    //        long newstartsize = 0;
    //        long asize = 0;
    //        long totalSize = file.length();
    //        String html = response.toString();
    //        String[] ranges = html.split("-");
    //        if (ranges.length > 0) {
    //            newstartsize = Long.parseLong(ranges[0]);
    //        }
    //        long startTime = System.currentTimeMillis();
    //        if (newstartsize == 0) {
    //            log.info("开始于：{} " , startTime);
    //        } else {
    //            log.info("上次上传 {} 本次开始于：{}" ,sizeFormat(newstartsize), startTime);
    //        }
    //        // chunk size, max 60M. 每小块上传大小，最大60M，微软建议10M
    //        long chunksize = 5L * BYTE_SIZE * BYTE_SIZE;
    //        if (totalSize > (long) BYTE_SIZE * BYTE_SIZE * BYTE_SIZE) {
    //            chunksize = 10L * BYTE_SIZE * BYTE_SIZE;
    //        }
    //        byte[] buffer = new byte[(int) chunksize];
    //        FileInputStream fis = new FileInputStream(file);
    //        long skip = fis.skip(newstartsize);
    //        log.warn("文件上传已跳过：{}",skip);
    //        // 跳过已上传的部分
    //        String result;
    //        while ((asize + chunksize) < totalSize) {
    //            fis.read(buffer);
    //            asize += chunksize;
    //            HttpURLConnection conn2 = (HttpURLConnection) new URL(url).openConnection();
    //            conn2.setRequestMethod("PUT");
    //            conn2.setRequestProperty("Content-Length",String.valueOf(file.length()));
    //            conn2.setRequestProperty("Content-Type", "application/octet-stream");
    //            conn2.setRequestProperty("Content-Range", "bytes " + (newstartsize + asize - chunksize) + "-" +
    //                    (newstartsize + asize - 1) + "/" + totalSize);
    //            conn2.setDoOutput(true);
    //            conn2.getOutputStream().write(buffer);
    //            conn2.connect();
    //            if (conn2.getResponseCode() == 201 || conn2.getResponseCode() == 202) {
    //                conn2.disconnect();
    //                long endTime = System.currentTimeMillis();
    //                log.info("结束于：{}" , endTime);
    //                if (newstartsize == 0) {
    //                   log.info("平均速度：{} /s" , sizeFormat(totalSize * 1000 / (endTime - startTime)));
    //                } else {
    //                    log.info("本次平均速度：{} /s" ,sizeFormat((totalSize - newstartsize) * 1000 / (endTime - startTime)));
    //                }
    //                break;
    //            } else {
    //                conn2.disconnect();
    //                fis.skip(-1 * chunksize);
    //            }
    //        }
    //
    //        if (asize + chunksize >= totalSize) {
    //            fis.read(buffer, 0, (int) (totalSize - asize));
    //            asize = totalSize;
    //            HttpURLConnection conn2 = (HttpURLConnection) new URL(url).openConnection();
    //            conn2.setRequestMethod("PUT");
    //            conn2.setRequestProperty("Content-Type", "application/octet-stream");
    //            conn2.setRequestProperty("Content-Range", "bytes " + (newstartsize + asize - chunksize) + "-" +
    //                    (newstartsize + asize - 1) + "/" + totalSize);
    //            conn2.setDoOutput(true);
    //            conn2.getOutputStream().write(buffer, 0, (int) (totalSize - asize));
    //            conn2.connect();
    //            if (conn2.getResponseCode() == HTTP_CODE_201 || conn2.getResponseCode() == HTTP_CODE_202) {
    //                conn2.disconnect();
    //                long endTime = System.currentTimeMillis();
    //                log.info("结束于：{}" ,endTime);
    //                if (newstartsize == 0) {
    //                    log.info("平均速度：{} /s" , sizeFormat(totalSize * 1000 / (endTime - startTime)));
    //                } else {
    //                    log.info("平均速度：{} /s" , sizeFormat((totalSize - newstartsize) * 1000 / (endTime - startTime)));
    //                }
    //            } else {
    //                conn2.disconnect();
    //            }
    //        }
    //        fis.close();
    //    } else {
    //        if (filename.contains(SIGN_JIN)) {
    //            log.info("目录或文件名含有#，上传失败。");
    //        } else {
    //            InputStream is = conn.getErrorStream();
    //            BufferedReader br = new BufferedReader(new InputStreamReader(is));
    //            StringBuilder response = new StringBuilder();
    //            String line;
    //            while ((line = br.readLine()) != null) {
    //                response.append(line);
    //            }
    //            br.close();
    //            is.close();
    //            conn.disconnect();
    //            log.info(response.toString());
    //        }
    //    }
    //}

    public static void chunkedUploadFile(File file, String url, int tdnum, String filename) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        //conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setRequestProperty("Origin", "https://img.mediy.cn");
        conn.setRequestProperty("Host", "mediycn-my.sharepoint.com");
        conn.setRequestProperty("Referer", "https://img.mediy.cn/");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        conn.setRequestMethod("PUT");
        conn.setChunkedStreamingMode(0);
        conn.setDoOutput(true);
        conn.connect();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            is.close();
            conn.disconnect();
            long newStartSize = 0;
            long aSize = 0;
            long totalSize = file.length();
            String html = response.toString();
            String[] ranges = html.split("-");
            if (ranges.length > 0) {
                newStartSize = Long.parseLong(ranges[0]);
            }
            long startTime = System.currentTimeMillis();
            if (newStartSize == 0) {
                log.info("开始于：{} ", startTime);
            } else {
                log.info("上次上传 {} 本次开始于：{}", sizeFormat(newStartSize), startTime);
            }

            // chunk size, max 60M. 每小块上传大小，最大60M，微软建议10M
            long chunkSize = 5L * BYTE_SIZE * BYTE_SIZE;
            if (totalSize > (long) BYTE_SIZE * BYTE_SIZE * BYTE_SIZE) {
                chunkSize = 10L * BYTE_SIZE * BYTE_SIZE;
            }
            byte[] buffer = new byte[(int) chunkSize];
            FileInputStream fis = new FileInputStream(file);
            long skip = fis.skip(newStartSize);
            log.warn("文件上传已跳过：{}", skip);

            // 跳过已上传的部分
            String result;
            while ((aSize + chunkSize) < totalSize) {
                fis.read(buffer);
                aSize += chunkSize;
                HttpURLConnection conn2 = (HttpURLConnection) new URL(url).openConnection();
                conn2.setRequestMethod("PUT");
                conn2.setRequestProperty("Content-Type", "application/octet-stream");
                conn2.setRequestProperty("Content-Range", "bytes " + (newStartSize + aSize - chunkSize) + "-" +
                        (newStartSize + aSize - 1) + "/" + totalSize);
                conn2.setDoOutput(true);
                conn2.getOutputStream().write(buffer);
                conn2.connect();
                if (conn2.getResponseCode() == 201 || conn2.getResponseCode() == 202) {
                    conn2.disconnect();
                    long endTime = System.currentTimeMillis();
                    log.info("结束于：{}", endTime);
                    if (newStartSize == 0) {
                        log.info("平均速度：{} /s", sizeFormat(totalSize * 1000 / (endTime - startTime)));
                    } else {
                        log.info("本次平均速度：{} /s", sizeFormat((totalSize - newStartSize) * 1000 / (endTime - startTime)));
                    }
                    break;
                } else {
                    conn2.disconnect();
                    fis.skip(-1 * chunkSize);
                }
            }

            if (aSize + chunkSize >= totalSize) {
                fis.read(buffer, 0, (int) (totalSize - aSize));
                aSize = totalSize;
                HttpURLConnection conn2 = (HttpURLConnection) new URL(url).openConnection();
                conn2.setRequestMethod("PUT");
                conn2.setRequestProperty("Content-Type", "application/octet-stream");
                conn2.setRequestProperty("Content-Range", "bytes " + (newStartSize + aSize - chunkSize) + "-" +
                        (newStartSize + aSize - 1) + "/" + totalSize);
                conn2.setDoOutput(true);
                conn2.getOutputStream().write(buffer, 0, (int) (totalSize - aSize));
                conn2.connect();
                if (conn2.getResponseCode() == HTTP_CODE_201 || conn2.getResponseCode() == HTTP_CODE_202) {
                    conn2.disconnect();
                    long endTime = System.currentTimeMillis();
                    log.info("结束于：{}", endTime);
                    if (newStartSize == 0) {
                        log.info("平均速度：{} /s", sizeFormat(totalSize * 1000 / (endTime - startTime)));
                    } else {
                        log.info("平均速度：{} /s", sizeFormat((totalSize - newStartSize) * 1000 / (endTime - startTime)));
                    }
                } else {
                    conn2.disconnect();
                }
            }
            fis.close();
        } else {
            if (filename.contains(SIGN_JIN)) {
                log.info("目录或文件名含有#，上传失败。");
            } else {
                InputStream is = conn.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                is.close();
                conn.disconnect();
                log.info(response.toString());
            }
        }
    }




    /**
     * 长度格式化
     * @param size 传入一个fileLength格式化文件长度描述
     * @return 返回一个文件长度格式化后的结果
     */
    private static String sizeFormat(long size) {
        if (size < BYTE_SIZE) {
            return size + "B";
        } else if (size < (long) BYTE_SIZE * BYTE_SIZE) {
            return (size / BYTE_SIZE) + "KB";
        } else if (size < (long) BYTE_SIZE * BYTE_SIZE * BYTE_SIZE) {
            return (size / BYTE_SIZE / BYTE_SIZE) + "MB";
        } else {
            return (size / BYTE_SIZE / BYTE_SIZE / BYTE_SIZE) + "GB";
        }
    }

}
