package com.stickpoint.ddmuisc;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.ddmusic.common.model.neteasy.NetEasyMusicEntity;
import com.stickpoint.ddmusic.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmuisc
 * @Author: fntp
 * @CreateTime: 2022-11-02  22:19
 * @Description: TODO
 * @Version: 1.0
 */
public class MyTest {

    private static final Logger log = LoggerFactory.getLogger(MyTest.class);

    public static void main(String[] args) throws IOException {
//        String getResult = new String(MyHttpUtils.builder()
//                .setUrl("http://101.37.160.25:5000/v1/wy/search")
//                .addQuery("key","我不对")
//                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                .addHeader("Content-Type","application/json; charset=utf-8")
//                .addHeader("Connection","keep-alive")
//                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.42")
//                .get().getByteContent(), StandardCharsets.UTF_8);
        Map<String,String> params = new ConcurrentHashMap<>();
        params.put("key","第三人称");
        HttpUtils httpUtils = HttpUtils.getInstance();
        String getResult = httpUtils.doNormalGet("http://101.37.160.25:5000/v1/wy/search",params);
        JsonElement jsonElement = JsonParser.parseString(getResult);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonObject reqJson = asJsonObject.getAsJsonObject("result");
        JsonArray data = reqJson.getAsJsonArray("songs");
        if (Objects.nonNull(data)) {
            log.info(data.toString());
            List<NetEasyMusicEntity> ps = new Gson().fromJson(data, new TypeToken<List<NetEasyMusicEntity>>(){}.getType());
            ps.forEach(item->{
                log.info(item.toString());
            });
        }
    }
}
