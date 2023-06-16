package com.stickpoint.ddmusic.common.model.entity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import java.io.File;

/**
 * @author puye(0303)
 * @since 2023/6/15
 */
@SuppressWarnings("unused")
public class DdMusicFileInfo {

    /**
     * 上传的文件名
     */
    private StringProperty upBigFileName;
    /**
     * 文件大小
     */
    private IntegerProperty fileSize;
    /**
     * 文件上次修改时间
     */
    private StringProperty fileLastModified;
    /**
     * 文件md5
     * 主要是为了实现秒传
     */
    private StringProperty fileMd5;
    /**
     * 原始File对象
     */
    private File originalFile;

    /**
     * 文件下载地址
     */
    private String fileDownloadUrl;

    public DdMusicFileInfo(StringProperty upBigFileName, IntegerProperty fileSize, StringProperty fileLastModified, StringProperty fileMd5, File originalFile) {
        this.upBigFileName = upBigFileName;
        this.fileSize = fileSize;
        this.fileLastModified = fileLastModified;
        this.fileMd5 = fileMd5;
        this.originalFile = originalFile;
    }

    public DdMusicFileInfo(File originalFile) {
        this.originalFile = originalFile;
    }

    public DdMusicFileInfo(StringProperty upBigFileName, IntegerProperty fileSize, StringProperty fileLastModified, StringProperty fileMd5) {
        this.upBigFileName = upBigFileName;
        this.fileSize = fileSize;
        this.fileLastModified = fileLastModified;
        this.fileMd5 = fileMd5;
    }

    public String getUpBigFileName() {
        return upBigFileName.get();
    }

    public void setUpBigFileName(String upBigFileName) {
        this.upBigFileName.set(upBigFileName);
    }

    public int getFileSize() {
        return fileSize.get();
    }

    public void setFileSize(int fileSize) {
        this.fileSize.set(fileSize);
    }

    public String getFileLastModified() {
        return fileLastModified.get();
    }

    public void setFileLastModified(String fileLastModified) {
        this.fileLastModified.set(fileLastModified);
    }

    public String getFileMd5() {
        return fileMd5.get();
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5.set(fileMd5);
    }

    public File getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(File originalFile) {
        this.originalFile = originalFile;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }

    @Override
    public String toString() {
        return "DdMusicFileInfo{" +
                "upBigFileName=" + upBigFileName +
                ", fileSize=" + fileSize +
                ", fileLastModified=" + fileLastModified +
                ", fileMd5=" + fileMd5 +
                ", originalFile=" + originalFile +
                ", fileDownloadUrl='" + fileDownloadUrl + '\'' +
                '}';
    }
}
