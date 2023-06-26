package com.stickpoint.ddmusic.common.service.impl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.neteasy.Album;
import com.stickpoint.ddmusic.common.model.neteasy.Artist;
import com.stickpoint.ddmusic.common.model.neteasy.NetEasyMusicEntityAbstract;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.DdNetEasyMusicService;
import com.stickpoint.ddmusic.common.utils.HttpUtils;
import com.stickpoint.ddmusic.common.utils.NetEasyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.service.impl
 * @Author: fntp
 * @CreateTime: 2022-11-20  11:38
 * @Description: TODO 将当前类中的所有的常量字符串都迁移到常量枚举类中去
 * @Version: 1.0
 */
public class NetEasyMusicServiceImpl implements DdNetEasyMusicService {

    /**
     * 网易云音乐服务日志
     */
    private static final Logger log = LoggerFactory.getLogger(NetEasyMusicServiceImpl.class);

    /**
     * 初始化api请求的基础地址
     */
    private StringBuilder getBaseUrl() {
        StringBuilder baseBuilder = new StringBuilder(InfoEnums.HTTP_REQUEST_PROTOCOL.getInfoContent());
        String baseRequestUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.API_FINAL_REQUEST_BASE_URL.getInfoContent());
        if (Objects.nonNull(baseRequestUrl)){
            baseBuilder.append(baseRequestUrl);
        }else {
            // 第一步 获得主机地址
            String baseHost = (String) SystemCache.APP_PROPERTIES
                    .get(InfoEnums.API_DDMUSIC_SOURCE_LIST_WP_MUSIC_HOST.getInfoContent());
            // 第二步 获得前缀
            String baseUrlPrefix = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.
                    API_DDMUSIC_SOURCE_LIST_WP_MUSIC_PREFIX.getInfoContent());
            // 第四部 拼接baseUrl
            return baseBuilder.append(baseHost).append(baseUrlPrefix);
        }
        return baseBuilder;
    }

    /**
     * 搜索音乐列表
     * 根据不同的实现类去做不同的具体的实现
     * 默认首先搜索的肯定是网易云的音乐
     *
     * @param baseInfo 基础信息
     *                 musicKey 音乐搜索关键字
     *                 pageNumber 页码
     *                 pageSize 一页多少条数据
     * @return 返回一个搜索的音乐列表结果
     */
    @Override
    public List<? extends AbstractDdMusicEntity> searchMusicList(RequestBaseInfoVO baseInfo) {
        // 获得网易云音乐请求的前缀地址
        String netEasyPrefixUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_PREFIX.getInfoContent());
        String netEasyMusicSearchUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_GET_SEARCH.getInfoContent());
        // 拼接网易云音乐搜索音乐接口地址
        StringBuilder finalRequestUrl = getBaseUrl().append(netEasyPrefixUrl).append(netEasyMusicSearchUrl);
        // 然后装载当前网易云音乐请求所传递的参数数据，然后获得最终的请求地址
        Map<String, Object> paramMap = appendParam(baseInfo);
        // 获得网易云音乐当前请求的结果，是一个json数据，需要装换为Object对象
        String netEasyMusicSearchResult = HttpUtils.doGetWithParams(finalRequestUrl.toString(),paramMap);
        // 转为GSON对象进行解析
        JsonElement jsonElement = JsonParser.parseString(netEasyMusicSearchResult);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonObject reqJson = asJsonObject.getAsJsonObject("result");
        JsonArray data = reqJson.getAsJsonArray("songs");
        List<NetEasyMusicEntityAbstract> ps = Collections.emptyList();
        if (Objects.nonNull(data)) {
            ps = new Gson().fromJson(data, new TypeToken<List<NetEasyMusicEntityAbstract>>(){}.getType());
            List<NetEasyMusicEntityAbstract> finalPs = ps;
            ps.forEach(item->{
                item.setDdNumber(String.valueOf(finalPs.indexOf(item)+1));
                item.setDdTitle(item.getName());
                Album al = item.getAl();
                item.setDdAlbum(al.getName());
                List<Artist> ar = item.getAr();
                StringBuilder artistName = new StringBuilder();
                for (Artist artist : ar) {
                    artistName.append(artist.getName());
                    artistName.append(",");
                }
                String artistsNameResult = artistName.append("#").toString().replace(",#", "");
                item.setDdArtists(artistsNameResult);
                item.setDdTimes(NetEasyUtil.getTimes(item.getDt()));
                item.setDdId(String.valueOf(item.getId()));
                if (Objects.nonNull(item.getAl())) {
                    item.setAlbumPicture(item.getAl().getPicUrl());
                }
            });
        }
        return ps;
    }

    /**
     * 根据音乐基础信息获得音乐播放地址
     * musicId 传入一个音乐id
     * musicQualityLevel 传入一个音乐
     *
     * @param baseInfo 基础信息
     * @return 返回一个音乐
     */
    @Override
    public AbstractDdMusicEntity getMusicUrlByMusicBaseInfo(RequestBaseInfoVO baseInfo) {
        return null;
    }

    /**
     * 获得网易云推荐音乐列表
     *
     * @return 返回一个网易云推荐音乐列表
     */
    @Override
    public List<? extends AbstractDdMusicEntity> getWyRecommendedMusicList() {
        // 获得网易云音乐请求的基础地址
        String netEasyGetRecommendRequestUrl = (String) SystemCache.APP_PROPERTIES
                .get(InfoEnums.NETEASY_GET_RECOMMEND.getInfoContent());
        // 拼接网易云音乐搜索音乐接口地址
        StringBuilder requestUrl = getBaseUrl().append(netEasyGetRecommendRequestUrl);
        // 获得网易云音乐当前请求的结果，是一个json数据，需要装换为Object对象
        String netEasyGetRecommendResult = HttpUtils.doAbsoluteGet(requestUrl.toString());
        Gson gson = new Gson();
        List<NetEasyMusicEntityAbstract> resp = null;
        if (Objects.nonNull(netEasyGetRecommendResult)) {
            log.info(netEasyGetRecommendResult);
            resp = gson.fromJson(netEasyGetRecommendResult, new TypeToken<List<NetEasyMusicEntityAbstract>>() {
            }.getType());
        }
        return resp;
    }

    /**
     * 根据网易云音乐id获取音乐播放地址
     *
     * @param musicId 网易云音乐id
     * @return 返回一个播放地址
     */
    @Override
    public String getMusicPlayUrl(String musicId) {
        String netEasyPrefixUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_PREFIX.getInfoContent());
        String getSongUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_GET_SONG.getInfoContent());
        StringBuilder getSongBuilder = getBaseUrl().append(netEasyPrefixUrl).append(getSongUrl);
        Map<String, Object> paramMap = new ConcurrentHashMap<>(1);
        paramMap.put("id",musicId);
        String respJson = HttpUtils.doGetWithParams(getSongBuilder.toString(), paramMap);
        JsonElement jsonElement = JsonParser.parseString(respJson);
        String finalUrl = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();
        if (Objects.nonNull(finalUrl)) {
            return finalUrl;
        }
        throw new DdmusicException(DdMusicExceptionEnums.FAILED);
    }

    /**
     * 获取将要播放的音乐的歌词内容
     *
     * @param musicId 传入一个音乐id
     * @return 返回一个音乐歌词内容
     */
    @Override
    public String getMusicLrcContent(String musicId) {
        String netEasyPrefixUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_PREFIX.getInfoContent());
        String getLyricUrl = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.NETEASY_GET_LRC.getInfoContent());
        StringBuilder getLyricBuilder = getBaseUrl().append(netEasyPrefixUrl).append(getLyricUrl);
        Map<String, Object> paramMap = new ConcurrentHashMap<>(1);
        paramMap.put("wid",musicId);
        String respJson = HttpUtils.doGetWithParams(getLyricBuilder.toString(), paramMap);
        JsonElement jsonElement = JsonParser.parseString(respJson);
        String finalLyric = jsonElement.getAsJsonObject().get("lrc").getAsJsonObject().get("lyric").getAsString();
        if (Objects.nonNull(finalLyric)) {
            return finalLyric;
        }
        throw new DdmusicException(DdMusicExceptionEnums.ERROR_FAILED_TO_GET_LYRIC);
    }

    private Map<String,Object> appendParam(RequestBaseInfoVO baseInfo) {
        Map<String,Object> paramMap = new ConcurrentHashMap<>(4);
        if (Objects.nonNull(baseInfo.getSearchKey())) {
            // 如果搜索的关键词存在的话，那么直接请求传递参数
            paramMap.put("key",baseInfo.getSearchKey());
        }
        if (Objects.nonNull(baseInfo.getPageNumber())) {
            // 如果设置了页码与页面偏移量
            paramMap.put("offset=", baseInfo.getPageNumber());
        }
        if (Objects.nonNull(baseInfo.getPageSize())) {
            paramMap.put("limit", baseInfo.getPageSize());
        }
        if (Objects.nonNull(baseInfo.getType())) {
            paramMap.put("type", baseInfo.getType());
        }
        return paramMap;
    }



}
