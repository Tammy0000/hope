package org.isandy.hope.Utils;

import com.alibaba.fastjson2.JSONObject;

public class Extractor2FA {
    /**
     * 从 JSON 字符串中提取 otp 的值
     * @param json JSON 字符串，格式如：{"ok":true,"data":{"otp":"292950","timeRemaining":4}}
     * @return 返回 otp 的字符串值，如果提取失败返回 null
     */
    public static String extractOtp(String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject.getBoolean("ok") && jsonObject.containsKey("data")) {
                JSONObject data = jsonObject.getJSONObject("data");
                return data.getString("otp");
            }
        } catch (Exception e) {
            // 解析失败或字段不存在时返回 null
            e.printStackTrace();
        }
        return null;
    }
}
