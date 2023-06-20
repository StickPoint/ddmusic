package com.stickpoint.ddmusic.common.model.entity;
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
    private String upBigFileName;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件上次修改时间
     */
    private String fileLastModified;
    /**
     * 文件md5
     * 主要是为了实现秒传
     */
    private String fileMd5;
    /**
     * 原始File对象
     */
    private File originalFile;
    /**
     * 文件地址
     */
    private String filePath;
    /**
     * 文件下载地址
     */
    private String fileDownloadUrl;

    public DdMusicFileInfo() {
    }

    public DdMusicFileInfo(String upBigFileName, long fileSize, String fileLastModified, String fileMd5, File originalFile) {
        this.upBigFileName = upBigFileName;
        this.fileSize = fileSize;
        this.fileLastModified = fileLastModified;
        this.fileMd5 = fileMd5;
        this.originalFile = originalFile;
    }

    public DdMusicFileInfo(File originalFile) {
        this.originalFile = originalFile;
    }

    public DdMusicFileInfo(String upBigFileName, long fileSize, String fileLastModified, String fileMd5) {
        this.upBigFileName = upBigFileName;
        this.fileSize = fileSize;
        this.fileLastModified = fileLastModified;
        this.fileMd5 = fileMd5;
    }

    public String getUpBigFileName() {
        return upBigFileName;
    }

    public void setUpBigFileName(String upBigFileName) {
        this.upBigFileName=upBigFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize=fileSize;
    }

    public String getFileLastModified() {
        return fileLastModified;
    }

    public void setFileLastModified(String fileLastModified) {
        this.fileLastModified=fileLastModified;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5=fileMd5;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "DdMusicFileInfo{" +
                "upBigFileName=" + upBigFileName +
                ", fileSize=" + fileSize +
                ", fileLastModified=" + fileLastModified +
                ", fileMd5=" + fileMd5 +
                ", originalFile=" + originalFile +
                ", filePath='" + filePath + '\'' +
                ", fileDownloadUrl='" + fileDownloadUrl + '\'' +
                '}';
    }
}
