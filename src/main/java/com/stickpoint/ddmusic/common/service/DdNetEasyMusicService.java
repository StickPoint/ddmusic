package com.stickpoint.ddmusic.common.service;

import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;

import java.util.List;

/**
 * @author fntp
 * @since 2023/6/21
 */
public interface DdNetEasyMusicService {

    /**
     * 搜索音乐列表
     * 根据不同的实现类去做不同的具体的实现
     * @param baseInfo 基础信息
     *  musicKey 音乐搜索关键字
     *  pageNumber 页码
     *  pageSize 一页多少条数据
     * @return 返回一个搜索的音乐列表结果
     */
    List<? extends AbstractDdMusicEntity> searchMusicList(RequestBaseInfoVO baseInfo);

    /**
     * 根据音乐基础信息获得音乐播放地址
     *  musicId 传入一个音乐id
     *  musicQualityLevel 传入一个音乐
     * @param baseInfo 基础信息
     * @return 返回一个音乐
     */
    AbstractDdMusicEntity getMusicUrlByMusicBaseInfo(RequestBaseInfoVO baseInfo);

    /**
     * 获得网易云推荐音乐列表
     * @return 返回一个网易云推荐音乐列表
     */
    List<? extends AbstractDdMusicEntity> getWyRecommendedMusicList();

    /**
     * 根据网易云音乐id获取音乐播放地址
     * @param musicId 网易云音乐id
     * @return 返回一个播放地址
     */
    String getMusicPlayUrl(String musicId);

    /**
     * 获取将要播放的音乐的歌词内容
     * @param musicId 传入一个音乐id
     * @return 返回一个音乐歌词内容
     */
    String getMusicLrcContent(String musicId);

    /**
     * 根据歌单id获取歌单详情
     * @param playListId 传入一个歌单id
     * @return 返回一个歌单详情
     */
    List<? extends AbstractDdMusicEntity> getPlayListInfoByPlayListId(String playListId);
}
