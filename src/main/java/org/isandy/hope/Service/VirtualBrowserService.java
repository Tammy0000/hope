package org.isandy.hope.Service;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class VirtualBrowserService implements VirtualBrowser {
    @Override
    public Object getBrowserList() {
        return VirtualBrowser.super.getBrowserList();
    }

    @Override
    public int addBrowser(String data) {
        return VirtualBrowser.super.addBrowser(data);
    }

    @Override
    public int launchBrowser(int id) {
        String res = HttpRequest.post("http://localhost:9000/api/launchBrowser")
                .body(JSON.toJSONString(Map.of("id", id)))
                .header("api-key", "KK3VD4s918t4gXyCFcz34Fq65ChUCONm")
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(res);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            log.error("启动浏览器失败: {}", jsonObject.getString("error"));
            return -1;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        return data.getIntValue("debuggingPort");
    }

    @Override
    public boolean stopBrowser(int id) {
        return VirtualBrowser.super.stopBrowser(id);
    }
}
