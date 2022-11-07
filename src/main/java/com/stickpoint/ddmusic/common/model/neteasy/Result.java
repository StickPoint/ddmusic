package com.stickpoint.ddmusic.common.model.neteasy;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class Result implements Serializable {

    @Serial
    private static final long serialVersionUID = -5621366292527443237L;
    private String searchQcReminder;
    private List<NetEasyMusicEntity> songs;
    private int songCount;

    public void setSearchQcReminder(String searchQcReminder) {
         this.searchQcReminder = searchQcReminder;
     }

    public String getSearchQcReminder() {
         return searchQcReminder;
     }

    public void setSongs(List<NetEasyMusicEntity> songs) {
         this.songs = songs;
     }
    public List<NetEasyMusicEntity> getSongs() {
         return songs;
     }

    public void setSongCount(int songCount) {
         this.songCount = songCount;
     }

    public int getSongCount() {
         return songCount;
     }

}