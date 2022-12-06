package com.stickpoint.ddmusic.common.utils;
import com.stickpoint.ddmusic.common.enums.DDMusicExceptionEnum;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 * description: YamlUtils
 *
 * @ClassName : YamlUtils
 * @Date 2022/9/22 13:31
 * @Author fntp
 * @PackageName com.stickpoint.ddmusic.common.utils
 */
public class YamlUtil extends LinkedHashMap<String, String> {

    @Serial
    private static final long serialVersionUID = 702565982353979710L;

    /**
     * 构建日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(SystemPropertiesUtil.class);

    /**
     * 含参构造：构建一个YAML解析器
     * @param ymlFilePath 传入一个yaml文件的地址
     */
    public YamlUtil(String ymlFilePath) {
        if (Objects.isNull(ymlFilePath)) {
            // 传入的地址是空的
            log.info( DDMusicExceptionEnum.ERROR_PROPERTIES_CENTER_FILE_PATH_IS_NULL.getMessage());
            return;
        }
        this.load(ymlFilePath);
    }

    /**
     * 加载指定地址的yaml文件
     * @param ymlFilePath 传入一个yaml文件地址
     *                （1） 网络地址
     *                （2）本地classpath地址
     */
    private void load(String ymlFilePath) {
        URL fileUrl;
        InputStream is = null;
        if (ymlFilePath.startsWith(InfoEnums.FILE_PATH_HEADER.getInfoContent())){
            // 如果是网络文件
            try {
                fileUrl = new URL(ymlFilePath);
                URLConnection urlConnection = fileUrl.openConnection();
                urlConnection.setUseCaches(false);
                is = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // 否则就是本地classpath底下的文件
            is = this.getClass().getClassLoader().getResourceAsStream(ymlFilePath);
        }
        assert is != null;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder sb = new StringBuilder();
        LinkedList<String> keyQueue = new LinkedList<>();
        LinkedList<Integer> numberQueue = new LinkedList<>();
        try {
            while ((line = br.readLine()) != null) {
                line = line.split("#")[0];
                String trim = line.trim();
                if (trim.length() == 0) {
                    continue;
                }
                int number = 0;
                char[] val = line.toCharArray();
                while ((number < line.length()) && (val[number] <= ' ')) {
                    number++;
                }
                if (number == 0) {
                    keyQueue.clear();
                    numberQueue.clear();
                } else {
                    Integer last;
                    while ((last = numberQueue.getFirst()) != null && last >= number) {
                        keyQueue.pop();
                        numberQueue.pop();
                    }
                }
                String[] split = trim.split(": ");
                if (split.length == 1) {
                    keyQueue.push(trim.substring(0, trim.length() - 1));
                    numberQueue.push(number);
                } else {
                    sb.setLength(0);
                    for (int i = keyQueue.size() - 1; i > -1; i--) {
                        String s = keyQueue.get(i);
                        sb.append(s).append(".");
                    }
                    sb.append(split[0]);
                    String key = sb.toString();
                    this.put(key, split[1]);
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage(),e);
            e.printStackTrace();
        }
    }

}
