package org.isandy.hope.Utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonBodyExtractor {
    /**
     * 提取 JSON body 中的关键词字段，并反序列化为指定类型
     *
     * @param body         原始 JSON 字符串
     * @param keyword      要提取的关键词（字段名）
     * @param targetClass  要转换成的目标类型
     * @param <T>          泛型类型
     * @return             提取出的对象，若无则返回 null
     */
    public static <T> T extract(String body, String keyword, Class<T> targetClass) {
        try {
            JSONObject json = JSON.parseObject(body);
            if (json.containsKey(keyword)) {
                return json.getObject(keyword, targetClass);
            }
        } catch (Exception e) {
            log.error("解析 JSON 出错:{}", e.getMessage());
        }
        return null;
    }
}
