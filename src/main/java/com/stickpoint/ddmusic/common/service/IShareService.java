package com.stickpoint.ddmusic.common.service;

import java.io.File;

/**
 * 文件服务
 * @author puye(0303)
 * @since 2023/6/15
 */
public interface IShareService {

    /**
     * 上传文件并且获取下载链接
     * @param file 传入一个文件对象
     * @return 返回一个文件上传后的下载地址
     */
    String uploadFileAndGetDownloadUrl(File file);

    /**
     * 根据文件下载地址下载文件
      * @param fileDownloadUrl 文件下载链接
     *  执行下载文件
     */
    void downloadFileByFileUrl(String fileDownloadUrl);

}
