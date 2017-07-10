package org.agile.common.utils.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 阿里巴巴FastJson的工具类
 * 
 * @author zhangmm
 */
public class FastJsonUtils {

    /**
     * 将Java类型的对象转换为JSON格式的字符串
     * @param object Java类型的对象
     * @return JSON格式的字符串
     */
    public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将JSON格式的字符串转换为Java类型的对象或者Java数组类型的对象，不包括Java集合类型
     * @param json JSON格式的字符串
     * @param clz Java类型或者Java数组类型，不包括Java集合类型
     * @return Java类型的对象或者Java数组类型的对象，不包括Java集合类型的对象
     */
    public static <T> T deserialize(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

    /**
     * 将JSON格式的字符串转换为List<T>类型的对象
     * @param json JSON格式的字符串
     * @param clz 指定泛型集合里面的T类型
     * @return List<T>类型的对象
     */
    public static <T> List<T> deserializeList(String json, Class<T> clz) {
        return JSON.parseArray(json, clz);
    }

    /**
     * 将JSON格式的字符串转换成任意Java类型的对象
     * @param json JSON格式的字符串
     * @param type 任意Java类型
     * @return 任意Java类型的对象
     */
    public static <T> T deserializeAny(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }

}