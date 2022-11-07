
package com.stickpoint.ddmusic.common.model.neteasy;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
public class Privilege implements Serializable {

    @Serial
    private static final long serialVersionUID = -3102485340864054174L;
    private long id;
    private int fee;
    private int payed;
    private int st;
    private int pl;
    private int dl;
    private int sp;
    private int cp;
    private int subp;
    private boolean cs;
    private long maxbr;
    private int fl;
    private boolean toast;
    private int flag;
    private boolean preSell;
    private long playMaxbr;
    private long downloadMaxbr;
    private String maxBrLevel;
    private String playMaxBrLevel;
    private String downloadMaxBrLevel;
    private String plLevel;
    private String dlLevel;
    private String flLevel;
    private String rscl;
    private FreeTrialPrivilege freeTrialPrivilege;
    private List<ChargeInfoList> chargeInfoList;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setFee(int fee) {
         this.fee = fee;
     }
     public int getFee() {
         return fee;
     }

    public void setPayed(int payed) {
         this.payed = payed;
     }
     public int getPayed() {
         return payed;
     }

    public void setSt(int st) {
         this.st = st;
     }
     public int getSt() {
         return st;
     }

    public void setPl(int pl) {
         this.pl = pl;
     }
     public int getPl() {
         return pl;
     }

    public void setDl(int dl) {
         this.dl = dl;
     }
     public int getDl() {
         return dl;
     }

    public void setSp(int sp) {
         this.sp = sp;
     }
     public int getSp() {
         return sp;
     }

    public void setCp(int cp) {
         this.cp = cp;
     }
     public int getCp() {
         return cp;
     }

    public void setSubp(int subp) {
         this.subp = subp;
     }
     public int getSubp() {
         return subp;
     }

    public void setCs(boolean cs) {
         this.cs = cs;
     }
     public boolean getCs() {
         return cs;
     }

    public void setMaxbr(long maxbr) {
         this.maxbr = maxbr;
     }
     public long getMaxbr() {
         return maxbr;
     }

    public void setFl(int fl) {
         this.fl = fl;
     }
     public int getFl() {
         return fl;
     }

    public void setToast(boolean toast) {
         this.toast = toast;
     }
     public boolean getToast() {
         return toast;
     }

    public void setFlag(int flag) {
         this.flag = flag;
     }
     public int getFlag() {
         return flag;
     }

    public void setPreSell(boolean preSell) {
         this.preSell = preSell;
     }
     public boolean getPreSell() {
         return preSell;
     }

    public void setPlayMaxbr(long playMaxbr) {
         this.playMaxbr = playMaxbr;
     }
     public long getPlayMaxbr() {
         return playMaxbr;
     }

    public void setDownloadMaxbr(long downloadMaxbr) {
         this.downloadMaxbr = downloadMaxbr;
     }
     public long getDownloadMaxbr() {
         return downloadMaxbr;
     }

    public void setMaxBrLevel(String maxBrLevel) {
         this.maxBrLevel = maxBrLevel;
     }
     public String getMaxBrLevel() {
         return maxBrLevel;
     }

    public void setPlayMaxBrLevel(String playMaxBrLevel) {
         this.playMaxBrLevel = playMaxBrLevel;
     }
     public String getPlayMaxBrLevel() {
         return playMaxBrLevel;
     }

    public void setDownloadMaxBrLevel(String downloadMaxBrLevel) {
         this.downloadMaxBrLevel = downloadMaxBrLevel;
     }
     public String getDownloadMaxBrLevel() {
         return downloadMaxBrLevel;
     }

    public void setPlLevel(String plLevel) {
         this.plLevel = plLevel;
     }
     public String getPlLevel() {
         return plLevel;
     }

    public void setDlLevel(String dlLevel) {
         this.dlLevel = dlLevel;
     }
     public String getDlLevel() {
         return dlLevel;
     }

    public void setFlLevel(String flLevel) {
         this.flLevel = flLevel;
     }
     public String getFlLevel() {
         return flLevel;
     }

    public void setRscl(String rscl) {
         this.rscl = rscl;
     }
     public String getRscl() {
         return rscl;
     }

    public void setFreeTrialPrivilege(FreeTrialPrivilege freeTrialPrivilege) {
         this.freeTrialPrivilege = freeTrialPrivilege;
     }
     public FreeTrialPrivilege getFreeTrialPrivilege() {
         return freeTrialPrivilege;
     }

    public void setChargeInfoList(List<ChargeInfoList> chargeInfoList) {
         this.chargeInfoList = chargeInfoList;
     }
     public List<ChargeInfoList> getChargeInfoList() {
         return chargeInfoList;
     }

}