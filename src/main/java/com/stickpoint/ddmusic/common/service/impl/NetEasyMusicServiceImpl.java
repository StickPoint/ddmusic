package com.stickpoint.ddmusic.common.service.impl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.ddmusic.common.constriant.SystemCache;
import com.stickpoint.ddmusic.common.enums.InfoEnums;
import com.stickpoint.ddmusic.common.model.entity.DdMusicEntity;
import com.stickpoint.ddmusic.common.model.neteasy.NetEasyMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.IMusicService;
import com.stickpoint.ddmusic.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.service.impl
 * @Author: fntp
 * @CreateTime: 2022-11-20  11:38
 * @Description: TODO 将当前类中的所有的常量字符串都迁移到常量枚举类中去
 * @Version: 1.0
 */
public class NetEasyMusicServiceImpl implements IMusicService {

    /**
     * 网易云音乐服务日志
     */
    private static final Logger log = LoggerFactory.getLogger(NetEasyMusicServiceImpl.class);

    /**
     * 初始化api请求的基础地址
     */
    private StringBuilder getBaseUrl() {
        // Http请求前缀
        StringBuilder httpPrefix = new StringBuilder((String) SystemCache.APP_PROPERTIES
                .get(InfoEnums.HTTP_REQUEST_PROTOCOL.getInfoContent()));
        // 第一步 获得主机地址
        String baseHost = (String) SystemCache.APP_PROPERTIES
                .get(InfoEnums.API_DDMUSIC_SOURCE_LIST_WP_MUSIC_HOST.getInfoContent());
        // 第二步 获得前缀
        String baseUrlPrefix = (String) SystemCache.APP_PROPERTIES.get(InfoEnums.
                API_DDMUSIC_SOURCE_LIST_WP_MUSIC_PREFIX.getInfoContent());
        // 第三步 获得音乐平台
        String neteasyUrl = (String) SystemCache.APP_PROPERTIES
                .get(InfoEnums.NETEASY_PREFIX.getInfoContent());
        // 第四部 拼接baseUrl
        return httpPrefix.append(baseHost).append(baseUrlPrefix).append(neteasyUrl);
    }

    private static final HttpUtils HTTP = HttpUtils.getInstance();

    /**
     * 搜索音乐列表
     * 根据不同的实现类去做不同的具体的实现
     *
     * @param baseInfo 基础信息
     *                 musicKey 音乐搜索关键字
     *                 pageNumber 页码
     *                 pageSize 一页多少条数据
     * @return 返回一个搜索的音乐列表结果
     */
    @Override
    public List<? extends DdMusicEntity> searchMusicList(RequestBaseInfoVO baseInfo) {
        // 获得网易云音乐请求的基础地址
        String netEasyMusicSearchBaseUrl = (String) SystemCache.APP_PROPERTIES
                .get(InfoEnums.NETEASY_GET_SEARCH.getInfoContent());
        // 拼接网易云音乐搜索音乐接口地址
        StringBuilder requestUrl = getBaseUrl().append(netEasyMusicSearchBaseUrl);
        // 然后装载当前网易云音乐请求所传递的参数数据，然后获得最终的请求地址
        StringBuilder finalRequestUrl = appendParam(requestUrl, baseInfo);
        // 获得网易云音乐当前请求的结果，是一个json数据，需要装换为Object对象
        String netEasyMusicSearchResult = HTTP.doAbsoluteGet(finalRequestUrl.toString());
        // 转为GSON对象进行解析
        JsonElement jsonElement = JsonParser.parseString(netEasyMusicSearchResult);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonObject reqJson = asJsonObject.getAsJsonObject("result");
        JsonArray data = reqJson.getAsJsonArray("songs");
        List<NetEasyMusicEntity> resp = null;
        if (Objects.nonNull(data)) {
            log.info(data.toString());
            resp = new Gson().fromJson(data, new TypeToken<List<NetEasyMusicEntity>>(){}.getType());
        }
        return resp;
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
    public DdMusicEntity getMusicUrlByMusicBaseInfo(RequestBaseInfoVO baseInfo) {
        return null;
    }

    private StringBuilder appendParam(StringBuilder requestUrl,RequestBaseInfoVO baseInfo) {
        if (Objects.isNull(baseInfo.getSearchKey())) {
            // 如果搜索的关键词存在的话，那么直接请求传递参数
            requestUrl.append("?key=").append(baseInfo.getSearchKey());
        }
        if (Objects.isNull(baseInfo.getPageNumber())) {
            // 如果设置了页码与页面偏移量
            requestUrl.append("&offset=").append(baseInfo.getPageNumber());
        }
        if (Objects.isNull(baseInfo.getPageSize())) {
            requestUrl.append("&limit=").append(baseInfo.getPageSize());
        }
        if (Objects.isNull(baseInfo.getType())) {
            requestUrl.append("&type=").append(baseInfo.getType());
        }
        return requestUrl;
    }
}