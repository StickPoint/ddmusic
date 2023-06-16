package com.stickpoint.ddmusic.common.service.impl;
import com.stickpoint.ddmusic.common.model.entity.DdMusicFileInfo;
import com.stickpoint.ddmusic.common.service.IShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * @author puye(0303)
 * @since 2023/6/15
 */
@SuppressWarnings("unused")
public class DdmusicShareServiceImpl implements IShareService {

    /**
     * 定点音乐分享服务日志
     */
    private static final Logger shareLog = LoggerFactory.getLogger(DdmusicShareServiceImpl.class);

    /**
     * 上传文件并且获取下载链接
     *
     * @param file 传入一个文件对象
     * @return 返回一个文件上传后的下载地址
     */
    @Override
    public String uploadFileAndGetDownloadUrl(File file) {

        return null;
    }

    /**
     * 由于需要支持秒传，所以需要将文件的md5精准的计算出来，避免二次传输占用三方平台空间
     * 上传文件之前的前置操作
     * 将未填写的文件信息先填充完毕
     * 需要填充四个属性字段
     * 文件名
     * 文件大小
     * 上次修改时间
     * 文件md5值
     * @param fileInfo 文件信息
     */
    private void loadFileInfoBeforeUpload(DdMusicFileInfo fileInfo){

    }

    /**
     * 根据文件下载地址下载文件
     *
     * @param fileDownloadUrl 文件下载链接
     *                        执行下载文件
     */
    @Override
    public void downloadFileByFileUrl(String fileDownloadUrl) {

    }





}
