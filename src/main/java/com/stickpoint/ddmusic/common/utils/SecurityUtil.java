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
}
