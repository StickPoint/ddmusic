package com.stickpoint.ddmusic.common.model.neteasy;
import java.io.Serial;
import java.io.Serializable;

public class L implements Serializable {

    @Serial
    private static final long serialVersionUID = 310174188837913839L;
    private long br;
    private int fid;
    private long size;
    private int vd;
    private int sr;
    public void setBr(long br) {
         this.br = br;
     }
     public long getBr() {
         return br;
     }

    public void setFid(int fid) {
         this.fid = fid;
     }
     public int getFid() {
         return fid;
     }

    public void setSize(long size) {
         this.size = size;
     }
     public long getSize() {
         return size;
     }

    public void setVd(int vd) {
         this.vd = vd;
     }
     public int getVd() {
         return vd;
     }

    public void setSr(int sr) {
         this.sr = sr;
     }
     public int getSr() {
         return sr;
     }

}