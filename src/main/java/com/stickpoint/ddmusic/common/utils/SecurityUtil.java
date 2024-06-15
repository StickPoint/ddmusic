package com.stickpoint.ddmusic.common.utils;

import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Objects;

/**
 * @author fntp
 * @since 2023/7/4
 */
public class SecurityUtil {

    private SecurityUtil() throws IllegalAccessException {
        throw new IllegalAccessException("SecurityUtil is not initialized properly when using security manager");
    }

    /**
     * 解码base64字符串
     * @param base64Str 传入一个base64字符串
     * @return  返回一个解码之后的数据
     */
    public static String getOriginalStrFromBase64Str(String base64Str) {
        if (base64Str.isBlank()||base64Str.isEmpty()) {
            throw new DdmusicException(DdMusicExceptionEnums.ERROR_BASE_64_STR_IS_NULL);
        }
        Decoder decoder = Base64.getDecoder();
        byte[] decodeResultBytes = decoder.decode(base64Str);
        if (Objects.isNull(decodeResultBytes)) {
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
        return new String(decodeResultBytes, StandardCharsets.UTF_8);
    }

    public static String getBase64StrFromOriginalStr(String originalStr) {
        if (originalStr.isBlank()||originalStr.isEmpty()) {
            throw new DdmusicException(DdMusicExceptionEnums.ERROR_BASE_64_STR_IS_NULL);
        }
        byte[] encodeResultBytes = Base64.getEncoder().encode(originalStr.getBytes(StandardCharsets.UTF_8));
        if (Objects.isNull(encodeResultBytes)) {
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
        return new String(encodeResultBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
//        String originalStr = "[{\"name\":\"轮播歌单推荐1\",\"picUrl\":\"https://s1.ax1x.com/2023/07/04/pCs2pgH.png\",\"id\":\"7516337632\"},{\"name\":\"轮播歌单推荐2\",\"picUrl\":\"https://s1.ax1x.com/2023/07/04/pCsgzCD.png\",\"id\":\"2732518642\"},{\"name\":\"轮播歌单推荐3\",\"picUrl\":\"https://s1.ax1x.com/2023/07/04/pCs2S8e.png\",\"id\":\"8408789437\"},{\"name\":\"轮播歌单推荐4\",\"picUrl\":\"https://s1.ax1x.com/2023/07/04/pCs29vd.png\",\"id\":\"971826288\"},{\"name\":\"轮播歌单推荐5\",\"picUrl\":\"https://s1.ax1x.com/2023/07/04/pCsgv4O.png\",\"id\":\"2556553762\"}]";
//        String base64StrFromOriginalStr = getBase64StrFromOriginalStr(originalStr);
//        System.out.println(getBase64StrFromOriginalStr(originalStr));
//        String originalStrFromBase64Str = getOriginalStrFromBase64Str(base64StrFromOriginalStr);
//        System.out.println(originalStrFromBase64Str);
//        System.out.println();
//        String currentStr = "W3sibmFtZSI6ICLova7mkq3mrYzljZXmjqjojZAxIiwicGljVXJsIjogImh0dHBzOi8vczEuYXgxeC5jb20vMjAyMy8wNy8wNC9wQ3MycGdILnBuZyIsImlkIjogIjc1MTYzMzc2MzIiLCJ0eXBlIjowfSx7Im5hbWUiOiAi6L2u5pKt5q2M5Y2V5o6o6I2QMiIsInBpY1VybCI6ICJodHRwczovL3MxLmF4MXguY29tLzIwMjMvMDcvMDQvcENzZ3pDRC5wbmciLCJpZCI6ICIyNzMyNTE4NjQyIiwidHlwZSI6MH0seyJuYW1lIjogIui9ruaSreatjOWNleaOqOiNkDMiLCJwaWNVcmwiOiAiaHR0cHM6Ly9zMS5heDF4LmNvbS8yMDIzLzA3LzA0L3BDczJTOGUucG5nIiwiaWQiOiAiODQwODc4OTQzNyIsInR5cGUiOjB9LHsibmFtZSI6ICLova7mkq3mrYzljZXmjqjojZA0IiwicGljVXJsIjogImh0dHBzOi8vczEuYXgxeC5jb20vMjAyMy8wNy8wNC9wQ3MyOXZkLnBuZyIsImlkIjogIjk3MTgyNjI4OCIsInR5cGUiOjB9LHsibmFtZSI6ICLova7mkq3mrYzljZXmjqjojZA1IiwicGljVXJsIjogImh0dHBzOi8vczEuYXgxeC5jb20vMjAyMy8wNy8wNC9wQ3NndjRPLnBuZyIsImlkIjogIjI1NTY1NTM3NjIiLCJ0eXBlIjowfV0=";
//        System.out.println(getOriginalStrFromBase64Str(currentStr));
        String lastStr = "[{\"name\": \"轮播歌单推荐1\",\"picUrl\": \"https://qnm.hunliji.com/o_1hqfmke501nr1oae8kf1fpp898f.png\",\"id\": \"7516337632\",\"type\":0},{\"name\": \"轮播歌单推荐2\",\"picUrl\": \"https://qnm.hunliji.com/o_1hqfmke50v4vk4s1kle1j721enpe.png\",\"id\": \"2732518642\",\"type\":0},{\"name\": \"轮播歌单推荐3\",\"picUrl\": \"https://qnm.hunliji.com/o_1hqfmke50u45h08hqhdevre3d.png\",\"id\": \"8408789437\",\"type\":0},{\"name\": \"轮播歌单推荐4\",\"picUrl\": \"https://qnm.hunliji.com/o_1hqi2e4t376b1pb432u1v7d7j9.png\",\"id\": \"971826288\",\"type\":0},{\"name\": \"轮播歌单推荐5\",\"picUrl\": \"https://qnm.hunliji.com/o_1hqfmke50163h14t4ah6gc414luc.png\",\"id\": \"2556553762\",\"type\":0}]";
        System.out.println(getBase64StrFromOriginalStr(lastStr));
    }
}
