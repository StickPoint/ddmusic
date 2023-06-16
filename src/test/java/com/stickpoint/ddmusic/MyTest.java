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
import com.stickpoint.ddmusic.common.utils.SecurityUtil;
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

    @Test
    public void testFileUpload() throws Exception {
        File file = new File("E:\\文档\\kfs_9fc50da1f365b260abbdabed77af3980.pdf");
        System.out.println(file.length());
        System.out.println(file.lastModified());
        int timestamp = (int) (System.currentTimeMillis() / 1000L);
        String uploadUrl = "https://mediycn-my.sharepoint.com/personal/images_mediy_cn/_api/v2.0/drive/items/01UQJ43ZDRLRKBUWKBQVGLJRDOU3D7NCIZ/uploadSession?guid='8f4c358a-a9e9-4742-bf3f-68f08cfa37aa'&overwrite=False&rename=False&dc=0&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAvbWVkaXljbi1teS5zaGFyZXBvaW50LmNvbUA4NGI0MzhhOS04ZTVkLTRlZTctYTcwNi1hMzljYzljYWU3YTAiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjg2OTA4MjI3IiwiZXhwIjoiMTY4Njk5NDYyNyIsImVuZHBvaW50dXJsIjoiMFRjRjVjRDZINHFZZFE5VkhNREpIbGJOR0FEeUpyQnFjQk1oWnhIU2hEYz0iLCJlbmRwb2ludHVybExlbmd0aCI6IjIwNyIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoiOVV3bjN4cVRMMGU1YXoyd2lrbVQ5dz09IiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6Ik56ZzVPVFE0TXpVdFpXTmhNQzAwTlRsa0xUZ3lZbVF0WXpOa1pqQmhNREF6WkdZeiIsImFwcF9kaXNwbGF5bmFtZSI6Ik9uZU1hbmFnZXIiLCJhcHBpZCI6IjczNGVmOTI4LWQ3NGMtNDU1NS04ZDFiLWQ5NDJmYTBhMWE0MSIsInRpZCI6Ijg0YjQzOGE5LThlNWQtNGVlNy1hNzA2LWEzOWNjOWNhZTdhMCIsInVwbiI6ImltYWdlc0BtZWRpeS5jbiIsInB1aWQiOiIxMDAzMjAwMjNCMUYyMEM5IiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDIzYjFmMjBjOUBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIGFsbHNpdGVzLndyaXRlIiwidHQiOiIyIiwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzIifQ.SxwThMlXS_i3rPNOxhMveOMlaEUR8CHmlgCc1JZr2bk";
        HttpUtils.chunkedUploadFile(file,uploadUrl,timestamp,"kfs_9fc50da1f365b260abbdabed77af3980.pdf");
    }

    @Test
    public void getMd5(){
        String fileMD5 = SecurityUtil.getFileMd5("E:\\文档\\kfs.pdf");
        log.info(fileMD5);
    }
}
