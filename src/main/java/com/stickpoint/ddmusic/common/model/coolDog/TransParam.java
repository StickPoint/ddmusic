package com.stickpoint.ddmusic.common.model.coolDog;

import com.google.gson.annotations.SerializedName;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.coolDog
 * @Author: fntp
 * @CreateTime: 2022-11-19  17:00
 * @Description: TODO
 * @Version: 1.0
 */
public class TransParam {
    @SerializedName("cpy_grade")
    private int cpyGrade;
    @SerializedName("cpy_attr0")
    private int cpyAttr0;
    @SerializedName("cpy_level")
    private int cpyLevel;
    private long cid;
    private Qualitymap qualitymap;
    @SerializedName("pay_block_tpl")
    private int payBlockTpl;
    @SerializedName("hash_multitrack")
    private String hashMultitrack;
    @SerializedName("display_rate")
    private int displayRate;
    private Classmap classmap;
    private int display;
    @SerializedName("musicpack_advance")
    private int musicpackAdvance;
    public void setCpyGrade(int cpyGrade) {
        this.cpyGrade = cpyGrade;
    }
    public int getCpyGrade() {
        return cpyGrade;
    }

    public void setCpyAttr0(int cpyAttr0) {
        this.cpyAttr0 = cpyAttr0;
    }
    public int getCpyAttr0() {
        return cpyAttr0;
    }

    public void setCpyLevel(int cpyLevel) {
        this.cpyLevel = cpyLevel;
    }
    public int getCpyLevel() {
        return cpyLevel;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
    public long getCid() {
        return cid;
    }

    public void setQualitymap(Qualitymap qualitymap) {
        this.qualitymap = qualitymap;
    }
    public Qualitymap getQualitymap() {
        return qualitymap;
    }

    public void setPayBlockTpl(int payBlockTpl) {
        this.payBlockTpl = payBlockTpl;
    }
    public int getPayBlockTpl() {
        return payBlockTpl;
    }

    public void setHashMultitrack(String hashMultitrack) {
        this.hashMultitrack = hashMultitrack;
    }
    public String getHashMultitrack() {
        return hashMultitrack;
    }

    public void setDisplayRate(int displayRate) {
        this.displayRate = displayRate;
    }
    public int getDisplayRate() {
        return displayRate;
    }

    public void setClassmap(Classmap classmap) {
        this.classmap = classmap;
    }
    public Classmap getClassmap() {
        return classmap;
    }

    public void setDisplay(int display) {
        this.display = display;
    }
    public int getDisplay() {
        return display;
    }

    public void setMusicpackAdvance(int musicpackAdvance) {
        this.musicpackAdvance = musicpackAdvance;
    }
    public int getMusicpackAdvance() {
        return musicpackAdvance;
    }

}