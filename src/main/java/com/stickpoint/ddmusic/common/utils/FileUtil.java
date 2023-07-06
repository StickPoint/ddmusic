package com.stickpoint.ddmusic.common.utils;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @author fntp
 * @since 2023/6/19
 */
public class FileUtil {

    private FileUtil() throws IllegalAccessException {
        throw new IllegalAccessException("DdMusicFxUtils is not initialized");
    }

    /**
     * 生成文件的MD5值
     * @param filePath 文件路径
     * @return 返回一个文件的MD5
     */
    public static String getFileMd5(String filePath) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(filePath);
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                md5.update(buffer);
                buffer.clear();
            }
            channel.close();
            fis.close();
            byte[] md5Bytes = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : md5Bytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
