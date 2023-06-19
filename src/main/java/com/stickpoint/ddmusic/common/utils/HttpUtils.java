package com.stickpoint.ddmusic.common.utils;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


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
    private static final int WRITE_TIME_OUT = 10000;
    /**
     * 读超时时间
     */
    private static final int READ_TIME_OUT = 10000;
    /**
     * 字节长度
     */
    private static final int BYTE_SIZE = 1024;
    /**
     * url编码中%23表示 #
     */
    private static final String SIGN_JIN = "%23";
    /**
     * HTTP请求超时通用时间 5s
     */
    private static final int HTTP_TIMEOUT_5 = 5;
    /**
     * 文件服务前缀
     */
    private static final String FILE_SERVER_PREFIX = "https://img.mediy.cn/";
    /**
     * 文件上传的时候文件已存在
     */
    private static final String ALREADY_EXISTING  = "nameAlreadyExists";
    /**
     * 正则size
     */
    private static final String REGEX_SIZE = "size";
    /**
     * 静态内部对象 HTTP文件上传所使用的
     */
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();

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

    private static byte[] readChunk(Path file, long offset, int chunkSize) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file.toFile(), "r")) {
            raf.seek(offset);
            int remainingBytes = (int) (raf.length() - offset);
            int bytesRead;
            if (remainingBytes >= chunkSize) {
                byte[] chunk = new byte[chunkSize];
                bytesRead = raf.read(chunk);
                return bytesRead != -1 ? chunk : new byte[0];
            } else if (remainingBytes > 0) {
                byte[] chunk = new byte[remainingBytes];
                bytesRead = raf.read(chunk);
                return bytesRead != -1 ? chunk : new byte[0];
            } else {
                return new byte[0];
            }
        }
    }

    /**
     * 使用GZIPOutputStream压缩字节数组
     * @param data 传入待压缩的数据
     * @return 返回一个压缩完之后的二进制数据
     * @throws IOException 抛出IO异常
     */
    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
            gzipOutputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    /**
     * 暴露给外界的文件上传接口
     * @param filePath 待上传的文件地址
     * @return 返回一个文件上传完毕之后的url地址
     * 这里返回的url地址分为两种
     * （1）一种是直接文件上传之后，返回的文件下载地址；
     * （2）一种是文件上传之后，发现文件存在之后，返回的拼接的文件地址；
     */
    public static String uploadFile(String filePath){
        // 第一步先检测：检测待上传的文件是否存在：
        File targetFile = new File(filePath);
        if (targetFile.exists()) {
            // 如果文件存在 执行后续上传操作
            long totalSize = targetFile.length();
            String fileDownloadUrl = null;
            try {
                String checkResult = checkFileIfExist(filePath);
                log.info(checkResult);
                if (Objects.isNull(checkResult)){
                    throw new DdmusicException(DdMusicExceptionEnums.FAILED);
                }
                if (checkResult.startsWith(InfoEnums.FILE_PATH_HEADER.getInfoContent())) {
                   return checkResult;
                }else if (checkResult.contains(InfoEnums.FILE_UPLOAD_URL.getInfoContent())){
                    // 创建正则表达式模式
                    String pattern = "\"uploadUrl\":.{0,1}\"(.*?)\"";
                    // 创建Pattern对象
                    Pattern regex = Pattern.compile(pattern, Pattern.MULTILINE | Pattern.DOTALL);
                    Matcher matcher = regex.matcher(checkResult);
                    if (matcher.find()) {
                        String url = matcher.group(1);
                        log.info("将要上传的文件的上传地址是:{}",url);
                        String uploadRes = getFileUploadSessionUrl(url.replace("\\/", "/"));
                        log.info("获得的sessionUrl是：{}",uploadRes);
                        if (Objects.nonNull(uploadRes)) {
                            log.info("开始执行文件上传");
                            // chunk size, max 60M. 每小块上传大小，最大60M，微软建议10M
                            int chunkSize = InfoEnums.COMMON_NUMBER_5.getNumberInfo() * BYTE_SIZE * BYTE_SIZE;
                            if (totalSize > InfoEnums.COMMON_NUMBER_200.getNumberInfo() * BYTE_SIZE * BYTE_SIZE) {
                                chunkSize = InfoEnums.COMMON_NUMBER_10.getNumberInfo() * BYTE_SIZE * BYTE_SIZE;
                            }
                            // 创建正则表达式模式
                            String patternSize = "\"nextExpectedRanges\":\\[\"(.*?)-.*?\"\\]";
                            // 创建Pattern对象
                            Pattern regexSize = Pattern.compile(patternSize);
                            // 创建正则表达式模式
                            // 创建Pattern对象
                            Pattern sizeOfRegex = Pattern.compile(REGEX_SIZE);
                            while (true) {
                                if(Objects.isNull(uploadRes)) {
                                    // 文件已经上传完毕
                                    return null;
                                }
                                // 创建Matcher对象
                                Matcher matcherSize = sizeOfRegex.matcher(uploadRes);
                                if(matcherSize.find()) {
                                    log.warn(uploadRes);
                                    return uploadRes;
                                }
                                // 创建Matcher对象
                                Matcher sizeOfMatcher = regexSize.matcher(uploadRes);
                                if (sizeOfMatcher.find()) {
                                    String aSize = sizeOfMatcher.group(1);
                                    uploadRes = doFileUpload(filePath, url.replace("\\/", "/"), Long.parseLong(aSize), chunkSize, totalSize);
                                } else {
                                    break;
                                }
                            }
                        }
                    } else {
                        throw new DdmusicException(DdMusicExceptionEnums.ERROR_FILE_UPLOAD);
                    }
                }
            } catch (IOException e) {
                log.error("current file doCheckStatus has occurred IO-Exception: {}", e.getMessage());
            } catch (InterruptedException e) {
                log.error("current file doCheckStatus has occurred InterruptedException: {}", e.getMessage());
                Thread.currentThread().interrupt();
            } catch (URISyntaxException e) {
                log.error("current file doCheckStatus has occurred URISyntaxException: {}", e.getMessage());
            }
        }
        log.error("待上传的文件不存在~");
        throw new DdmusicException(DdMusicExceptionEnums.ERROR_FILE_NOT_EXSIT);
    }

    /**
     *
     * @param filePath 文件路径
     * @param url 上传的URL地址
     * @param offset 文件偏移量
     * @param chunkSize 读取的字节数
     * @param totalSize 总文件的大小
     * @return url的请求结果，"__null__"代表访问失败
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     */
    private static String doFileUpload(String filePath, String url, long offset, int chunkSize, long totalSize) throws IOException, InterruptedException {
        // 构建请求体
        Path file = Path.of(filePath);
        byte[] chunk = readChunk(file, offset, chunkSize);
        byte[] compressedChunk = compress(chunk);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofByteArray(chunk);
        long ext = (offset + chunkSize) > totalSize ? (totalSize - 1) : (offset + chunkSize - 1);
        log.info("bytes {} -{} /{} " ,offset ,ext ,totalSize);
        // 创建HttpRequest构建器
        HttpRequest.Builder requestBuilder = null;
        try {
            requestBuilder = HttpRequest.newBuilder(new URI(url))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                    .header("Referer", "https://img.mediy.cn/")
                    .header("Origin", "https://img.mediy.cn")
                    .header("Cache-Control", "no-cache")
                    .header("Pragma", "no-cache")
                    .header("Sec-Ch-Ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
                    .header("Sec-Ch-Ua-Mobile", "?0")
                    .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                    .header("Sec-Fetch-Dest", "empty")
                    .header("Sec-Fetch-Mode", "cors")
                    .header("Sec-Fetch-Site", "cross-site")
                    .header("Accept", "*/*")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Content-Range", "bytes " + offset + "-" + ext + "/" + totalSize)
                    .timeout(Duration.ofSeconds(90))
                    .version(Version.HTTP_1_1)
                    .PUT(bodyPublisher);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(requestBuilder)) {
            log.error("requestBuilder is null");
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
        // 发送请求并获取响应
        HttpResponse<byte[]> response = HTTP_CLIENT.send(requestBuilder.build(), BodyHandlers.ofByteArray());
        // 检查响应状态码
        int statusCode = response.statusCode();
        if (statusCode >= HttpURLConnection.HTTP_OK && statusCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            log.info("文件部分字节上传成功！");
        } else if (statusCode==HttpURLConnection.HTTP_CONFLICT){
            log.info("文件上传完毕！");
            return null;
        }else {
            log.warn("文件部分字节上传失败。状态码: {}" ,statusCode);
            return null;
        }
        HttpHeaders headers = response.headers();
        boolean isGzip = "gzip".equalsIgnoreCase(headers.firstValue("Content-Encoding").orElse(""));
        byte[] body = response.body();
        if (isGzip) {
            try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(body))) {
                ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = gzipInputStream.read(buffer)) > 0) {
                    resultStream.write(buffer, 0, length);
                }
                body = resultStream.toByteArray();
            }
        }
        String responseBody = new String(body, StandardCharsets.UTF_8);
        // 输出结果
        log.info("Status code: {}" ,statusCode);
        log.info("Response body: {}" ,responseBody);
        return responseBody;
    }


    /**
     * 读取文件的扩展名
     * @param str 传入一个文件名
     * @return "." + 扩展名
     */
    private static String getExtension(String str) {
        String[] strArray = str.split("\\.");
        if (strArray.length == 1) {
            return "";
        }
        String ext = strArray[strArray.length - 1].toLowerCase();
        String regex = "\\.";
        String a = str.replace(regex, "");
        if (a.equals(ext)) {
            ext = "";
        } else {
            ext = "." + ext;
        }
        return ext;
    }

    /**
     * 文件上传之前的前置操作
     * 如果文件已经存在，则直接返回文件的下载地址：
     * 拼接URL的方式返回已有文件的地址
     * 首次上传前，请求上传地址，获取当前上传的文件偏移量 offset ，实现断点续传
     * @param url 文件上传的url
     * @return 文件偏移量 offset
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     * @throws URISyntaxException URI异常
     */
    private static String getFileUploadSessionUrl(String url) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                // 设置Header:
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .header("Referer", "https://img.mediy.cn/")
                .header("Origin", "https://img.mediy.cn")
                .header("Cache-Control", "no-cache")
                .header("Pragma", "no-cache")
                .header("Sec-Ch-Ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
                .header("Sec-Ch-Ua-Mobile", "?0")
                .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "cross-site")
                .header("Accept", "*/*")
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                .header("Accept-Encoding", "gzip, deflate, br")
                // 设置超时:
                .timeout(Duration.ofSeconds(90))
                // 设置版本:
                .version(Version.HTTP_1_1).build();
        HttpResponse<byte[]> response = HTTP_CLIENT.send(request, BodyHandlers.ofByteArray());
        // 处理响应
        int statusCode = response.statusCode();
        if (statusCode != HttpURLConnection.HTTP_OK) {
            return null;
        }
        HttpHeaders headers = response.headers();
        boolean isGzip = "gzip".equalsIgnoreCase(headers.firstValue("Content-Encoding").orElse(""));
        byte[] body = response.body();
        // 判断是否是gzip压缩数据
        if (isGzip) {
            try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(body))) {
                ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = gzipInputStream.read(buffer)) > 0) {
                    resultStream.write(buffer, 0, length);
                }
                body = resultStream.toByteArray();
            }
        }
        String responseBody = new String(body, StandardCharsets.UTF_8);
        // 输出结果
        log.info("Status code: {}", statusCode);
        log.info("Response body: {}",responseBody);
        return responseBody;
    }


    /**
     * 获取文件的上传地址
     * @param filePath 本地文件的路径，绝对路径
     * @return 接口返回的数据，"__null__"代表访问失败
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     * @throws URISyntaxException URI异常
     */
    private static String checkFileIfExist(String filePath) throws IOException, InterruptedException, URISyntaxException {
        String url = "https://img.mediy.cn/?action=upbigfile";
        File file = new File(filePath);
        long filesize;
        long fileLastModified;
        if (file.exists()) {
            filesize = file.length();
            long lastModified = file.lastModified();
            Date lastModifiedDate = new Date(lastModified);
            fileLastModified = lastModifiedDate.getTime();
        } else {
            log.info("File not found.");
            return "File not found";
        }
        String hexHash = FileUtil.getFileMd5(filePath);
        String[] split = filePath.split("\\\\");
        String ext = split[split.length - 1];
        String[] starry = ext.split("\\.");
        String fileMd5 = URLEncoder.encode(starry[0], StandardCharsets.UTF_8) + "_" + hexHash;
        String upBigFileName = fileMd5 + getExtension(ext);
        // 构建请求参数
        Map<String, String> formData = new HashMap<>(5);
        formData.put("upbigfilename", upBigFileName);
        formData.put("filesize", "" + filesize);
        formData.put("fileLastModified", "" + fileLastModified);
        formData.put("filemd5", fileMd5);
        log.info(formData.toString());
        // 创建Multipart请求主体
        String boundary = "----WebKitFormBoundaryXzjdflPfE192mWpF";
        String contentType = "multipart/form-data; boundary=" + boundary;
        HttpRequest.BodyPublisher bodyPublisher = buildMultipartBody(formData, boundary);
        // 获取请求对象
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                // 设置Header:
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .header("Referer", "https://img.mediy.cn/")
                // .header("Host", "img.mediy.cn")
                .header("Origin", "https://img.mediy.cn")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Sec-Ch-Ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
                .header("Sec-Ch-Ua-Mobile", "?0")
                .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "cross-site")
                .header("Accept", "*/*")
                .header("Content-Type", contentType)
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                // .header("Accept-Encoding", "gzip, deflate, br")
                // 设置超时:
                .timeout(Duration.ofSeconds(5))
                // 设置版本:
                .version(Version.HTTP_1_1)
                .POST(bodyPublisher)
                .build();
        // 发送请求并获取响应
        HttpResponse<String> response = HTTP_CLIENT.send(request, BodyHandlers.ofString());
        // 处理响应
        int statusCode = response.statusCode();
        if (statusCode != HttpURLConnection.HTTP_OK) {
            // 文件已存在 warning
            log.warn(response.body());
            if (Objects.nonNull(response.body())&&response.body().contains(ALREADY_EXISTING)) {
                // 文件已经存在 直接返回文件的下载地址
                return FILE_SERVER_PREFIX.concat(upBigFileName);
            }
            log.error("unknown file upload errors！");
            return null;
        }
        if (Objects.nonNull(response.body())){
            return response.body();
        }
       return null;
    }
    /**
     * 构建form-data的请求体
     * @param formData 请求的键值对
     * @param boundary 分割字符
     * @return POST方法的请求体
     */
    private static HttpRequest.BodyPublisher buildMultipartBody(Map<String, String> formData, String boundary) {
        var builder = new StringBuilder();
        // 添加每个键值对
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            builder.append("--").append(boundary).append("\r\n");
            builder.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"\r\n");
            builder.append("\r\n");
            builder.append(entry.getValue()).append("\r\n");
        }
        builder.append("--").append(boundary).append("--").append("\r\n");
        return HttpRequest.BodyPublishers.ofString(builder.toString(), StandardCharsets.UTF_8);
    }

}
