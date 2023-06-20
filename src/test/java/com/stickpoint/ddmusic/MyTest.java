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
import com.stickpoint.ddmusic.common.utils.FileUtil;
import com.stickpoint.ddmusic.common.utils.HttpUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic
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

    @Test
    public void testFileUpload() throws Exception {
        File file = new File("D:\\irs11.pdf");
        System.out.println(file.length());
        System.out.println(file.lastModified());
        int timestamp = (int) (System.currentTimeMillis() / 1000L);
        String uploadUrl = "https://mediycn-my.sharepoint.com/personal/images_mediy_cn/_api/v2.0/drive/items/01UQJ43ZDOMGBN22EPCREKILWZPUYNSW4X/uploadSession?guid='3cfec447-ae1e-461c-943a-129f80e9927e'&overwrite=False&rename=False&dc=0&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAvbWVkaXljbi1teS5zaGFyZXBvaW50LmNvbUA4NGI0MzhhOS04ZTVkLTRlZTctYTcwNi1hMzljYzljYWU3YTAiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjg3MTM5ODg5IiwiZXhwIjoiMTY4NzIyNjI4OSIsImVuZHBvaW50dXJsIjoiaVNoV2hhdTVoOUNSSUt5OVlaT1NXd095NFF0Z0ZzWHFWUXkzbkxWUW84OD0iLCJlbmRwb2ludHVybExlbmd0aCI6IjIwNyIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoid1I5QU92SGw0VW1xOVEydVJyaXEzZz09IiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6Ik56ZzVPVFE0TXpVdFpXTmhNQzAwTlRsa0xUZ3lZbVF0WXpOa1pqQmhNREF6WkdZeiIsImFwcF9kaXNwbGF5bmFtZSI6Ik9uZU1hbmFnZXIiLCJhcHBpZCI6IjczNGVmOTI4LWQ3NGMtNDU1NS04ZDFiLWQ5NDJmYTBhMWE0MSIsInRpZCI6Ijg0YjQzOGE5LThlNWQtNGVlNy1hNzA2LWEzOWNjOWNhZTdhMCIsInVwbiI6ImltYWdlc0BtZWRpeS5jbiIsInB1aWQiOiIxMDAzMjAwMjNCMUYyMEM5IiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDIzYjFmMjBjOUBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIGFsbHNpdGVzLndyaXRlIiwidHQiOiIyIiwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzAifQ.CTBgIYa115muAIQgxkjZ5LxiSh7E6chYoqt_zjLV614";

    }

    @Test
    public void testCheckFile(){
        String resultUrl = HttpUtils.uploadFile("E:\\desktop\\卢锡安.jpg");
        log.info(resultUrl);
    }

    @Test
    public void getMd5(){
        String fileMD5 = FileUtil.getFileMd5("D:\\irs11.pdf");
        File file = new File("D:\\irs11.pdf");
        log.info(String.valueOf(file.length()));
        log.info(String.valueOf(file.lastModified()));
        log.info(fileMD5);
    }
}
