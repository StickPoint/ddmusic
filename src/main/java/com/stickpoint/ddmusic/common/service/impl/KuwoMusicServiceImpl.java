package com.stickpoint.ddmusic.common.service.impl;

import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.IMusicService;

import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/5/29
 */
@SuppressWarnings("unused")
public class KuwoMusicServiceImpl implements IMusicService {
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
    public List<? extends AbstractDdMusicEntity> searchMusicList(RequestBaseInfoVO baseInfo) {
        return null;
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
        return null;
    }
}
