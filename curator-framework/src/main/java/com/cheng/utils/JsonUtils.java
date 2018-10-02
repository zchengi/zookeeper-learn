package com.cheng.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * JSON/对象转换类
 *
 * @author cheng
 *         2018/10/2 14:49
 */
public class JsonUtils {

    private JsonUtils() {
    }

    /**
     * 定义 jackson 对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json结果集转换为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {

        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
