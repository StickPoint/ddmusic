package com.stickpoint.ddmusic.common.service;
import com.stickpoint.ddmusic.common.model.entity.DdMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import java.util.List;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.service
 * @Author: fntp
 * @CreateTime: 2022-11-06  21:19
 * @Description: TODO
 * @Version: 1.0
 */
public interface IMusicService {

    /**
     * 搜索音乐列表
     * 根据不同的实现类去做不同的具体的实现
     * @param baseInfo 基础信息
      *  musicKey 音乐搜索关键字
     *  pageNumber 页码
     *  pageSize 一页多少条数据
     * @return 返回一个搜索的音乐列表结果
     */
    List<? extends DdMusicEntity> searchMusicList(RequestBaseInfoVO baseInfo);

    /**
     * 根据音乐基础信息获得音乐播放地址
     *  musicId 传入一个音乐id
     *  musicQualityLevel 传入一个音乐
     * @param baseInfo 基础信息
     * @return 返回一个音乐
     */
    DdMusicEntity getMusicUrlByMusicBaseInfo(RequestBaseInfoVO baseInfo);

    /**
     * 获得网易云推荐音乐列表
     * @return 返回一个网易云推荐音乐列表
     */
    List<? extends DdMusicEntity> getWyRecommendedMusicList();


}
