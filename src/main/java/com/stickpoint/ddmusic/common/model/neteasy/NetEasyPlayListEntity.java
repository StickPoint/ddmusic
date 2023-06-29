package com.stickpoint.ddmusic.common.model.neteasy;

import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;

import java.io.Serial;
import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/5/29
 */
public class NetEasyPlayListEntity extends AbstractDdMusicEntity {

    @Serial
    private static final long serialVersionUID = 8013822770499075535L;

    private List<String> subscribers;
    private String subscribed;
    private String creator;
    private String artists;
    private List<Tracks> tracks;
    private String updateFrequency;
    private int backgroundCoverId;
    private String backgroundCoverUrl;
    private int titleImage;
    private String coverText;
    private String titleImageUrl;
    private String coverImageUrl;
    private String englishTitle;
    private boolean opRecommend;
    private String recommendInfo;
    private String socialPlaylistCover;
    private int userId;
    private long trackNumberUpdateTime;
    private long createTime;
    private boolean highQuality;
    private int specialType;
    private boolean newImported;
    private long updateTime;
    private boolean anonimous;
    private String coverImgUrl;
    private long coverImgId;
    private int trackCount;
    private String commentThreadId;
    private int totalDuration;
    private long trackUpdateTime;
    private int privacy;
    private long playCount;
    private long subscribedCount;
    private int cloudTrackCount;
    private int adType;
    private boolean ordered;
    private List<String> tags;
    private String description;
    private int status;
    private String name;
    private long id;
    private String coverImgId_str;
    private String ToplistType;
    public void setSubscribers(List<String> subscribers) {
        this.subscribers = subscribers;
    }
    public List<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }
    public String getSubscribed() {
        return subscribed;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreator() {
        return creator;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }
    public String getArtists() {
        return artists;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }
    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }
    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setBackgroundCoverId(int backgroundCoverId) {
        this.backgroundCoverId = backgroundCoverId;
    }
    public int getBackgroundCoverId() {
        return backgroundCoverId;
    }

    public void setBackgroundCoverUrl(String backgroundCoverUrl) {
        this.backgroundCoverUrl = backgroundCoverUrl;
    }
    public String getBackgroundCoverUrl() {
        return backgroundCoverUrl;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
    public int getTitleImage() {
        return titleImage;
    }

    public void setCoverText(String coverText) {
        this.coverText = coverText;
    }
    public String getCoverText() {
        return coverText;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }
    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }
    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setOpRecommend(boolean opRecommend) {
        this.opRecommend = opRecommend;
    }
    public boolean getOpRecommend() {
        return opRecommend;
    }

    public void setRecommendInfo(String recommendInfo) {
        this.recommendInfo = recommendInfo;
    }
    public String getRecommendInfo() {
        return recommendInfo;
    }

    public void setSocialPlaylistCover(String socialPlaylistCover) {
        this.socialPlaylistCover = socialPlaylistCover;
    }
    public String getSocialPlaylistCover() {
        return socialPlaylistCover;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
        this.trackNumberUpdateTime = trackNumberUpdateTime;
    }
    public long getTrackNumberUpdateTime() {
        return trackNumberUpdateTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setHighQuality(boolean highQuality) {
        this.highQuality = highQuality;
    }
    public boolean getHighQuality() {
        return highQuality;
    }

    public void setSpecialType(int specialType) {
        this.specialType = specialType;
    }
    public int getSpecialType() {
        return specialType;
    }

    public void setNewImported(boolean newImported) {
        this.newImported = newImported;
    }
    public boolean getNewImported() {
        return newImported;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setAnonimous(boolean anonimous) {
        this.anonimous = anonimous;
    }
    public boolean getAnonimous() {
        return anonimous;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgId(long coverImgId) {
        this.coverImgId = coverImgId;
    }
    public long getCoverImgId() {
        return coverImgId;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }
    public int getTrackCount() {
        return trackCount;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }
    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }
    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTrackUpdateTime(long trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }
    public long getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }
    public int getPrivacy() {
        return privacy;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }
    public long getPlayCount() {
        return playCount;
    }

    public void setSubscribedCount(long subscribedCount) {
        this.subscribedCount = subscribedCount;
    }
    public long getSubscribedCount() {
        return subscribedCount;
    }

    public void setCloudTrackCount(int cloudTrackCount) {
        this.cloudTrackCount = cloudTrackCount;
    }
    public int getCloudTrackCount() {
        return cloudTrackCount;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }
    public int getAdType() {
        return adType;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
    public boolean getOrdered() {
        return ordered;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setCoverImgId_str(String coverImgId_str) {
        this.coverImgId_str = coverImgId_str;
    }
    public String getCoverImgId_str() {
        return coverImgId_str;
    }

    public void setToplistType(String ToplistType) {
        this.ToplistType = ToplistType;
    }
    public String getToplistType() {
        return ToplistType;
    }
}
