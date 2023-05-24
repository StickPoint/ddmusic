package com.stickpoint.ddmusic.common.model.cooldog;
import com.google.gson.annotations.SerializedName;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.cooldog
 * @Author: fntp
 * @CreateTime: 2022-11-19  17:02
 * @Description: TODO
 * @Version: 1.0
 */
public class CoolDogMusicEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 8227384231493785289L;
    private String Suffix;
    private String SongName;
    private long OwnerCount;
    private int MvType;
    private String UploaderContent;
    private String TopicRemark;
    private int SQFailProcess;
    private String Source;
    private int Bitrate;
    private String HQExtName;
    private long SQFileSize;
    private int Accompany;
    private int AudioCdn;
    private int MvTrac;
    private int SQDuration;
    private int recommend_type;
    private String ExtName;
    private String Auxiliary;
    private int SQPkgPrice;
    private int Category;
    @SerializedName("Scid")
    private long scid;
    private String OriSongName;
    private int FailProcess;
    private int HQPkgPrice;
    private int HQBitrate;
    private long Audioid;
    private int HiFiQuality;
    private List<String> Grp;
    private String OriOtherName;
    private int AlbumPrivilege;
    private String TopicUrl;
    private String SuperFileHash;
    private int ASQPrivilege;
    private int OldCpy;
    @SerializedName("trans_param")
    private TransParam transParam;
    private int FoldType;
    private int IsOriginal;
    private int A320Privilege;
    private String AlbumID;
    private String TagContent;
    private int ResBitrate;
    private String AlbumName;
    private int HQFailProcess;
    private List<String> mvdata;
    @SerializedName("SQPayType")
    private int sqpaytype;
    private String vvid;
    private String MixSongID;
    private int MatchFlag;
    private String SuperExtName;
    private String Type;
    private String SongLabel;
    private int SuperBitrate;
    private int SourceID;
    private int ResFileSize;
    private int Publish;
    private int HQPayType;
    private String ID;
    private int SuperFileSize;
    private int QualityLevel;
    private String SQFileHash;
    private int mvTotal;
    private int HQPrivilege;
    private String AlbumAux;
    private int SuperDuration;
    private String FileName;
    private String ResFileHash;
    private int PublishAge;
    private String MvHash;
    private String HQFileHash;
    private int SQPrivilege;
    private String PublishTime;
    private int SQBitrate;
    private int TopID;
    private String SQExtName;
    private int PkgPrice;
    private int M4aSize;
    private int Duration;
    private String OtherName;
    private int HQPrice;
    private int SQPrice;
    private int ResDuration;
    private long FileSize;
    private int Price;
    private List<Integer> SingerId;
    private String SingerName;
    private String Uploader;
    private long HQFileSize;
    private List<Singers> Singers;
    private int HQDuration;
    private int PayType;
    private int HasAlbum;
    private String FileHash;
    private int Privilege;
    public void setSuffix(String Suffix) {
        this.Suffix = Suffix;
    }
    public String getSuffix() {
        return Suffix;
    }

    public void setSongName(String SongName) {
        this.SongName = SongName;
    }
    public String getSongName() {
        return SongName;
    }

    public void setOwnerCount(long OwnerCount) {
        this.OwnerCount = OwnerCount;
    }
    public long getOwnerCount() {
        return OwnerCount;
    }

    public void setMvType(int MvType) {
        this.MvType = MvType;
    }
    public int getMvType() {
        return MvType;
    }

    public void setUploaderContent(String UploaderContent) {
        this.UploaderContent = UploaderContent;
    }
    public String getUploaderContent() {
        return UploaderContent;
    }

    public void setTopicRemark(String TopicRemark) {
        this.TopicRemark = TopicRemark;
    }
    public String getTopicRemark() {
        return TopicRemark;
    }

    public void setSQFailProcess(int SQFailProcess) {
        this.SQFailProcess = SQFailProcess;
    }
    public int getSQFailProcess() {
        return SQFailProcess;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }
    public String getSource() {
        return Source;
    }

    public void setBitrate(int Bitrate) {
        this.Bitrate = Bitrate;
    }
    public int getBitrate() {
        return Bitrate;
    }

    public void setHQExtName(String HQExtName) {
        this.HQExtName = HQExtName;
    }
    public String getHQExtName() {
        return HQExtName;
    }

    public void setSQFileSize(long SQFileSize) {
        this.SQFileSize = SQFileSize;
    }
    public long getSQFileSize() {
        return SQFileSize;
    }

    public void setAccompany(int Accompany) {
        this.Accompany = Accompany;
    }
    public int getAccompany() {
        return Accompany;
    }

    public void setAudioCdn(int AudioCdn) {
        this.AudioCdn = AudioCdn;
    }
    public int getAudioCdn() {
        return AudioCdn;
    }

    public void setMvTrac(int MvTrac) {
        this.MvTrac = MvTrac;
    }
    public int getMvTrac() {
        return MvTrac;
    }

    public void setSQDuration(int SQDuration) {
        this.SQDuration = SQDuration;
    }
    public int getSQDuration() {
        return SQDuration;
    }

    public void setRecommend_type(int recommend_type) {
        this.recommend_type = recommend_type;
    }
    public int getRecommend_type() {
        return recommend_type;
    }

    public void setExtName(String ExtName) {
        this.ExtName = ExtName;
    }
    public String getExtName() {
        return ExtName;
    }

    public void setAuxiliary(String Auxiliary) {
        this.Auxiliary = Auxiliary;
    }
    public String getAuxiliary() {
        return Auxiliary;
    }

    public void setSQPkgPrice(int SQPkgPrice) {
        this.SQPkgPrice = SQPkgPrice;
    }
    public int getSQPkgPrice() {
        return SQPkgPrice;
    }

    public void setCategory(int Category) {
        this.Category = Category;
    }
    public int getCategory() {
        return Category;
    }

    public void setScid(long scid) {
        this.scid = scid;
    }
    public long getScid() {
        return scid;
    }

    public void setOriSongName(String OriSongName) {
        this.OriSongName = OriSongName;
    }
    public String getOriSongName() {
        return OriSongName;
    }

    public void setFailProcess(int FailProcess) {
        this.FailProcess = FailProcess;
    }
    public int getFailProcess() {
        return FailProcess;
    }

    public void setHQPkgPrice(int HQPkgPrice) {
        this.HQPkgPrice = HQPkgPrice;
    }
    public int getHQPkgPrice() {
        return HQPkgPrice;
    }

    public void setHQBitrate(int HQBitrate) {
        this.HQBitrate = HQBitrate;
    }
    public int getHQBitrate() {
        return HQBitrate;
    }

    public void setAudioid(long Audioid) {
        this.Audioid = Audioid;
    }
    public long getAudioid() {
        return Audioid;
    }

    public void setHiFiQuality(int HiFiQuality) {
        this.HiFiQuality = HiFiQuality;
    }
    public int getHiFiQuality() {
        return HiFiQuality;
    }

    public void setGrp(List<String> Grp) {
        this.Grp = Grp;
    }
    public List<String> getGrp() {
        return Grp;
    }

    public void setOriOtherName(String OriOtherName) {
        this.OriOtherName = OriOtherName;
    }
    public String getOriOtherName() {
        return OriOtherName;
    }

    public void setAlbumPrivilege(int AlbumPrivilege) {
        this.AlbumPrivilege = AlbumPrivilege;
    }
    public int getAlbumPrivilege() {
        return AlbumPrivilege;
    }

    public void setTopicUrl(String TopicUrl) {
        this.TopicUrl = TopicUrl;
    }
    public String getTopicUrl() {
        return TopicUrl;
    }

    public void setSuperFileHash(String SuperFileHash) {
        this.SuperFileHash = SuperFileHash;
    }
    public String getSuperFileHash() {
        return SuperFileHash;
    }

    public void setASQPrivilege(int ASQPrivilege) {
        this.ASQPrivilege = ASQPrivilege;
    }
    public int getASQPrivilege() {
        return ASQPrivilege;
    }

    public void setOldCpy(int OldCpy) {
        this.OldCpy = OldCpy;
    }
    public int getOldCpy() {
        return OldCpy;
    }

    public void setTransParam(TransParam transParam) {
        this.transParam = transParam;
    }
    public TransParam getTransParam() {
        return transParam;
    }

    public void setFoldType(int FoldType) {
        this.FoldType = FoldType;
    }
    public int getFoldType() {
        return FoldType;
    }

    public void setIsOriginal(int IsOriginal) {
        this.IsOriginal = IsOriginal;
    }
    public int getIsOriginal() {
        return IsOriginal;
    }

    public void setA320Privilege(int A320Privilege) {
        this.A320Privilege = A320Privilege;
    }
    public int getA320Privilege() {
        return A320Privilege;
    }

    public void setAlbumID(String AlbumID) {
        this.AlbumID = AlbumID;
    }
    public String getAlbumID() {
        return AlbumID;
    }

    public void setTagContent(String TagContent) {
        this.TagContent = TagContent;
    }
    public String getTagContent() {
        return TagContent;
    }

    public void setResBitrate(int ResBitrate) {
        this.ResBitrate = ResBitrate;
    }
    public int getResBitrate() {
        return ResBitrate;
    }

    public void setAlbumName(String AlbumName) {
        this.AlbumName = AlbumName;
    }
    public String getAlbumName() {
        return AlbumName;
    }

    public void setHQFailProcess(int HQFailProcess) {
        this.HQFailProcess = HQFailProcess;
    }
    public int getHQFailProcess() {
        return HQFailProcess;
    }

    public void setMvdata(List<String> mvdata) {
        this.mvdata = mvdata;
    }
    public List<String> getMvdata() {
        return mvdata;
    }

    public void setSqpaytype(int sqpaytype) {
        this.sqpaytype = sqpaytype;
    }
    public int getSqpaytype() {
        return sqpaytype;
    }

    public void setVvid(String vvid) {
        this.vvid = vvid;
    }
    public String getVvid() {
        return vvid;
    }

    public void setMixSongID(String MixSongID) {
        this.MixSongID = MixSongID;
    }
    public String getMixSongID() {
        return MixSongID;
    }

    public void setMatchFlag(int MatchFlag) {
        this.MatchFlag = MatchFlag;
    }
    public int getMatchFlag() {
        return MatchFlag;
    }

    public void setSuperExtName(String SuperExtName) {
        this.SuperExtName = SuperExtName;
    }
    public String getSuperExtName() {
        return SuperExtName;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }

    public void setSongLabel(String SongLabel) {
        this.SongLabel = SongLabel;
    }
    public String getSongLabel() {
        return SongLabel;
    }

    public void setSuperBitrate(int SuperBitrate) {
        this.SuperBitrate = SuperBitrate;
    }
    public int getSuperBitrate() {
        return SuperBitrate;
    }

    public void setSourceID(int SourceID) {
        this.SourceID = SourceID;
    }
    public int getSourceID() {
        return SourceID;
    }

    public void setResFileSize(int ResFileSize) {
        this.ResFileSize = ResFileSize;
    }
    public int getResFileSize() {
        return ResFileSize;
    }

    public void setPublish(int Publish) {
        this.Publish = Publish;
    }
    public int getPublish() {
        return Publish;
    }

    public void setHQPayType(int HQPayType) {
        this.HQPayType = HQPayType;
    }
    public int getHQPayType() {
        return HQPayType;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }

    public void setSuperFileSize(int SuperFileSize) {
        this.SuperFileSize = SuperFileSize;
    }
    public int getSuperFileSize() {
        return SuperFileSize;
    }

    public void setQualityLevel(int QualityLevel) {
        this.QualityLevel = QualityLevel;
    }
    public int getQualityLevel() {
        return QualityLevel;
    }

    public void setSQFileHash(String SQFileHash) {
        this.SQFileHash = SQFileHash;
    }
    public String getSQFileHash() {
        return SQFileHash;
    }

    public void setMvTotal(int mvTotal) {
        this.mvTotal = mvTotal;
    }
    public int getMvTotal() {
        return mvTotal;
    }

    public void setHQPrivilege(int HQPrivilege) {
        this.HQPrivilege = HQPrivilege;
    }
    public int getHQPrivilege() {
        return HQPrivilege;
    }

    public void setAlbumAux(String AlbumAux) {
        this.AlbumAux = AlbumAux;
    }
    public String getAlbumAux() {
        return AlbumAux;
    }

    public void setSuperDuration(int SuperDuration) {
        this.SuperDuration = SuperDuration;
    }
    public int getSuperDuration() {
        return SuperDuration;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }
    public String getFileName() {
        return FileName;
    }

    public void setResFileHash(String ResFileHash) {
        this.ResFileHash = ResFileHash;
    }
    public String getResFileHash() {
        return ResFileHash;
    }

    public void setPublishAge(int PublishAge) {
        this.PublishAge = PublishAge;
    }
    public int getPublishAge() {
        return PublishAge;
    }

    public void setMvHash(String MvHash) {
        this.MvHash = MvHash;
    }
    public String getMvHash() {
        return MvHash;
    }

    public void setHQFileHash(String HQFileHash) {
        this.HQFileHash = HQFileHash;
    }
    public String getHQFileHash() {
        return HQFileHash;
    }

    public void setSQPrivilege(int SQPrivilege) {
        this.SQPrivilege = SQPrivilege;
    }
    public int getSQPrivilege() {
        return SQPrivilege;
    }

    public void setPublishTime(String PublishTime) {
        this.PublishTime = PublishTime;
    }
    public String getPublishTime() {
        return PublishTime;
    }

    public void setSQBitrate(int SQBitrate) {
        this.SQBitrate = SQBitrate;
    }
    public int getSQBitrate() {
        return SQBitrate;
    }

    public void setTopID(int TopID) {
        this.TopID = TopID;
    }
    public int getTopID() {
        return TopID;
    }

    public void setSQExtName(String SQExtName) {
        this.SQExtName = SQExtName;
    }
    public String getSQExtName() {
        return SQExtName;
    }

    public void setPkgPrice(int PkgPrice) {
        this.PkgPrice = PkgPrice;
    }
    public int getPkgPrice() {
        return PkgPrice;
    }

    public void setM4aSize(int M4aSize) {
        this.M4aSize = M4aSize;
    }
    public int getM4aSize() {
        return M4aSize;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }
    public int getDuration() {
        return Duration;
    }

    public void setOtherName(String OtherName) {
        this.OtherName = OtherName;
    }
    public String getOtherName() {
        return OtherName;
    }

    public void setHQPrice(int HQPrice) {
        this.HQPrice = HQPrice;
    }
    public int getHQPrice() {
        return HQPrice;
    }

    public void setSQPrice(int SQPrice) {
        this.SQPrice = SQPrice;
    }
    public int getSQPrice() {
        return SQPrice;
    }

    public void setResDuration(int ResDuration) {
        this.ResDuration = ResDuration;
    }
    public int getResDuration() {
        return ResDuration;
    }

    public void setFileSize(long FileSize) {
        this.FileSize = FileSize;
    }
    public long getFileSize() {
        return FileSize;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    public int getPrice() {
        return Price;
    }

    public void setSingerId(List<Integer> SingerId) {
        this.SingerId = SingerId;
    }
    public List<Integer> getSingerId() {
        return SingerId;
    }

    public void setSingerName(String SingerName) {
        this.SingerName = SingerName;
    }
    public String getSingerName() {
        return SingerName;
    }

    public void setUploader(String Uploader) {
        this.Uploader = Uploader;
    }
    public String getUploader() {
        return Uploader;
    }

    public void setHQFileSize(long HQFileSize) {
        this.HQFileSize = HQFileSize;
    }
    public long getHQFileSize() {
        return HQFileSize;
    }

    public void setSingers(List<Singers> Singers) {
        this.Singers = Singers;
    }
    public List<Singers> getSingers() {
        return Singers;
    }

    public void setHQDuration(int HQDuration) {
        this.HQDuration = HQDuration;
    }
    public int getHQDuration() {
        return HQDuration;
    }

    public void setPayType(int PayType) {
        this.PayType = PayType;
    }
    public int getPayType() {
        return PayType;
    }

    public void setHasAlbum(int HasAlbum) {
        this.HasAlbum = HasAlbum;
    }
    public int getHasAlbum() {
        return HasAlbum;
    }

    public void setFileHash(String FileHash) {
        this.FileHash = FileHash;
    }
    public String getFileHash() {
        return FileHash;
    }

    public void setPrivilege(int Privilege) {
        this.Privilege = Privilege;
    }
    public int getPrivilege() {
        return Privilege;
    }

}