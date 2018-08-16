package utils;

import com.alibaba.fastjson.JSON;

public final class JsonUtils {
    private JsonUtils() {}

    public static String encode(Object o) {
        return JSON.toJSONString(o);
    }

    public static <T> T decode(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }
}
