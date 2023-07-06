package com.stickpoint.ddmusic.common.utils;

import com.google.gson.Gson;
import com.stickpoint.ddmusic.common.factory.SingletonFactory;

/**
 * @author fntp
 * @since 2023/7/5
 */
public class JsonUtil {

    private static final Gson GSON;

    static {
        GSON = SingletonFactory.getWeakInstace(Gson.class);
    }

    private JsonUtil() throws IllegalAccessException {
        throw new IllegalAccessException("JsonUtil is not initialized properly");
    }

    public static Gson getGson() {
        return GSON;
    }

}
