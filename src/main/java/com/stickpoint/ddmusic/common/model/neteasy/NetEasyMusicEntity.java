package com.stickpoint.ddmusic.common.model.neteasy;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.neteasy
 * @Author: fntp
 * @CreateTime: 2022-11-07  22:03
 * @Description: TODO
 * @Version: 1.0
 */
public class NetEasyMusicEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3085656316138757278L;
        private String name;
        private long id;
        private int pst;
        private int t;
        private List<Ar> ar;
        private List<String> alia;
        private int pop;
        private int st;
        private String rt;
        private int fee;
        private int v;
        private String crbt;
        private String cf;
        private Al al;
        private long dt;
        private H h;
        private M m;
        private L l;
        private Sq sq;
        private String hr;
        private String a;
        private String cd;
        private int no;
        private String rtUrl;
        private int ftype;
        private List<String> rtUrls;
        private int djId;
        private int copyright;
        private int s_id;
        private int mark;
        private int originCoverType;
        private String originSongSimpleData;
        private String tagPicList;
        private boolean resourceState;
        private int version;
        private String songJumpInfo;
        private String entertainmentTags;
        private int single;
        private String noCopyrightRcmd;
        private int rtype;
        private String rurl;
        private int mst;
        private int cp;
        private long mv;
        private long publishTime;
        private Privilege privilege;
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

        public void setPst(int pst) {
            this.pst = pst;
        }
        public int getPst() {
            return pst;
        }

        public void setT(int t) {
            this.t = t;
        }
        public int getT() {
            return t;
        }

        public void setAr(List<Ar> ar) {
            this.ar = ar;
        }
        public List<Ar> getAr() {
            return ar;
        }

        public void setAlia(List<String> alia) {
            this.alia = alia;
        }
        public List<String> getAlia() {
            return alia;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }
        public int getPop() {
            return pop;
        }

        public void setSt(int st) {
            this.st = st;
        }
        public int getSt() {
            return st;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }
        public String getRt() {
            return rt;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
        public int getFee() {
            return fee;
        }

        public void setV(int v) {
            this.v = v;
        }
        public int getV() {
            return v;
        }

        public void setCrbt(String crbt) {
            this.crbt = crbt;
        }
        public String getCrbt() {
            return crbt;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }
        public String getCf() {
            return cf;
        }

        public void setAl(Al al) {
            this.al = al;
        }
        public Al getAl() {
            return al;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }
        public long getDt() {
            return dt;
        }

        public void setH(H h) {
            this.h = h;
        }
        public H getH() {
            return h;
        }

        public void setM(M m) {
            this.m = m;
        }
        public M getM() {
            return m;
        }

        public void setL(L l) {
            this.l = l;
        }
        public L getL() {
            return l;
        }

        public void setSq(Sq sq) {
            this.sq = sq;
        }
        public Sq getSq() {
            return sq;
        }

        public void setHr(String hr) {
            this.hr = hr;
        }
        public String getHr() {
            return hr;
        }

        public void setA(String a) {
            this.a = a;
        }
        public String getA() {
            return a;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }
        public String getCd() {
            return cd;
        }

        public void setNo(int no) {
            this.no = no;
        }
        public int getNo() {
            return no;
        }

        public void setRtUrl(String rtUrl) {
            this.rtUrl = rtUrl;
        }
        public String getRtUrl() {
            return rtUrl;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }
        public int getFtype() {
            return ftype;
        }

        public void setRtUrls(List<String> rtUrls) {
            this.rtUrls = rtUrls;
        }
        public List<String> getRtUrls() {
            return rtUrls;
        }

        public void setDjId(int djId) {
            this.djId = djId;
        }
        public int getDjId() {
            return djId;
        }

        public void setCopyright(int copyright) {
            this.copyright = copyright;
        }
        public int getCopyright() {
            return copyright;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }
        public int getS_id() {
            return s_id;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
        public int getMark() {
            return mark;
        }

        public void setOriginCoverType(int originCoverType) {
            this.originCoverType = originCoverType;
        }
        public int getOriginCoverType() {
            return originCoverType;
        }

        public void setOriginSongSimpleData(String originSongSimpleData) {
            this.originSongSimpleData = originSongSimpleData;
        }
        public String getOriginSongSimpleData() {
            return originSongSimpleData;
        }

        public void setTagPicList(String tagPicList) {
            this.tagPicList = tagPicList;
        }
        public String getTagPicList() {
            return tagPicList;
        }

        public void setResourceState(boolean resourceState) {
            this.resourceState = resourceState;
        }
        public boolean getResourceState() {
            return resourceState;
        }

        public void setVersion(int version) {
            this.version = version;
        }
        public int getVersion() {
            return version;
        }

        public void setSongJumpInfo(String songJumpInfo) {
            this.songJumpInfo = songJumpInfo;
        }
        public String getSongJumpInfo() {
            return songJumpInfo;
        }

        public void setEntertainmentTags(String entertainmentTags) {
            this.entertainmentTags = entertainmentTags;
        }
        public String getEntertainmentTags() {
            return entertainmentTags;
        }

        public void setSingle(int single) {
            this.single = single;
        }
        public int getSingle() {
            return single;
        }

        public void setNoCopyrightRcmd(String noCopyrightRcmd) {
            this.noCopyrightRcmd = noCopyrightRcmd;
        }
        public String getNoCopyrightRcmd() {
            return noCopyrightRcmd;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }
        public int getRtype() {
            return rtype;
        }

        public void setRurl(String rurl) {
            this.rurl = rurl;
        }
        public String getRurl() {
            return rurl;
        }

        public void setMst(int mst) {
            this.mst = mst;
        }
        public int getMst() {
            return mst;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }
        public int getCp() {
            return cp;
        }

        public void setMv(long mv) {
            this.mv = mv;
        }
        public long getMv() {
            return mv;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
        public long getPublishTime() {
            return publishTime;
        }

        public void setPrivilege(Privilege privilege) {
            this.privilege = privilege;
        }
        public Privilege getPrivilege() {
            return privilege;
        }

    @Override
    public String toString() {
        return "NetEasyMusicEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", pst=" + pst +
                ", t=" + t +
                ", ar=" + ar +
                ", alia=" + alia +
                ", pop=" + pop +
                ", st=" + st +
                ", rt='" + rt + '\'' +
                ", fee=" + fee +
                ", v=" + v +
                ", crbt='" + crbt + '\'' +
                ", cf='" + cf + '\'' +
                ", al=" + al +
                ", dt=" + dt +
                ", h=" + h +
                ", m=" + m +
                ", l=" + l +
                ", sq=" + sq +
                ", hr='" + hr + '\'' +
                ", a='" + a + '\'' +
                ", cd='" + cd + '\'' +
                ", no=" + no +
                ", rtUrl='" + rtUrl + '\'' +
                ", ftype=" + ftype +
                ", rtUrls=" + rtUrls +
                ", djId=" + djId +
                ", copyright=" + copyright +
                ", s_id=" + s_id +
                ", mark=" + mark +
                ", originCoverType=" + originCoverType +
                ", originSongSimpleData='" + originSongSimpleData + '\'' +
                ", tagPicList='" + tagPicList + '\'' +
                ", resourceState=" + resourceState +
                ", version=" + version +
                ", songJumpInfo='" + songJumpInfo + '\'' +
                ", entertainmentTags='" + entertainmentTags + '\'' +
                ", single=" + single +
                ", noCopyrightRcmd='" + noCopyrightRcmd + '\'' +
                ", rtype=" + rtype +
                ", rurl='" + rurl + '\'' +
                ", mst=" + mst +
                ", cp=" + cp +
                ", mv=" + mv +
                ", publishTime=" + publishTime +
                ", privilege=" + privilege +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NetEasyMusicEntity that = (NetEasyMusicEntity) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
