package com.stickpoint.ddmusic.common.utils;

import java.net.URL;
import java.util.Objects;

/**
 * @author fntp
 * @since 2023/5/29
 */
@SuppressWarnings("unused")
public class DdMusicFxUtil {

    private DdMusicFxUtil() throws IllegalAccessException {
        throw new IllegalAccessException("DdMusicFxUtils is not initialized");
    }

    /**
     * 这个函数的作用是返回资源文件的外部形式（即资源文件的 URL 地址），。
     * @param relativePath 参数 relativePath 是资源文件的路径
     * @return  即资源文件的 URL 地址
     * getResource() 方法是 Java 中的 API 方法，用于获取资源文件的 URL 地址
     * toExternalForm() 方法则是将 URL 地址转换为字符串形式
     */
    public static String getResourceExternalForm(String relativePath){
        URL resource = DdMusicFxUtil.class.getResource(relativePath);
        if (Objects.nonNull(resource)) {
           return resource.toExternalForm();
        }
        return null;
    }
}
