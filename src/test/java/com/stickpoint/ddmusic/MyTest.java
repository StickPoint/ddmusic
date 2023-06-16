package com.stickpoint.ddmusic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.neteasy.NetEasyMusicEntityAbstract;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.IMusicService;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
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
        Map<String,Object> params = new ConcurrentHashMap<>();
        params.put("key","第三人称");
        String getResult = HttpUtils.doGetWithParams("http://sinsy.xyz:5000/v1/wy/search?key=",params);
        JsonElement jsonElement = JsonParser.parseString(getResult);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonObject reqJson = asJsonObject.getAsJsonObject("result");
        JsonArray data = reqJson.getAsJsonArray("songs");
        if (Objects.nonNull(data)) {
            log.info(data.toString());
            List<NetEasyMusicEntityAbstract> ps = new Gson().fromJson(data, new TypeToken<List<NetEasyMusicEntityAbstract>>(){}.getType());
            ps.forEach(item-> log.info(item.toString()));
        }
    }

    @Test
    public void testSearchMusic() {
        IMusicService musicService = new NetEasyMusicServiceImpl();
        RequestBaseInfoVO requestBaseInfoVO = new RequestBaseInfoVO();
        requestBaseInfoVO.setSearchKey("夜间巡航");
        List<? extends AbstractDdMusicEntity> searchMusicList = musicService.searchMusicList(requestBaseInfoVO);
    }
}
