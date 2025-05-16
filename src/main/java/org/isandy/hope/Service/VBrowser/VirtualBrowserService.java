package org.isandy.hope.Service.VBrowser;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Config.AppConfig;
import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Service.VirtualBrowser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualBrowserService implements VirtualBrowser {

    private final HopeProjectVirtualBrowserRepository hopeProjectVirtualBrowserRepository;

    private final AppConfig appConfig;

    @Override
    public String getBrowserList() {
        var res = HttpRequest.get(appConfig.getVirtualBrowserHost() + "/api/getBrowserList")
                .header("api-key", appConfig.getVirtualBrowserApiKey())
                .execute().body();
        JSONObject jsonObject = JSONObject.parseObject(res);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            log.error(res);
            return null;
        }
        return jsonObject.getString("data");
    }

    @Override
    public int addBrowser() {
        List<Integer> chromeVersion = List.of(124, 123, 122, 121, 125, 126, 130, 131, 121, 132, 120, 119, 118, 117, 116);
        Random random = new Random();
        JSONObject root = new JSONObject();
        int anInt = random.nextInt(chromeVersion.size());
        root.put("chrome_version", chromeVersion.get(anInt));
        String res = HttpRequest.post(appConfig.getVirtualBrowserHost() + "/api/addBrowser")
                .body(JSON.toJSONString(root))
                .header("api-key", appConfig.getVirtualBrowserApiKey())
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(res);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            log.error("创建浏览器失败: {}", jsonObject.getString("error"));
            return -1;
        }
        JSONObject dataId = jsonObject.getJSONObject("data");
        HopeProjectVirtualBrowser browser = new HopeProjectVirtualBrowser();
        browser
                .setBrowserId(dataId.getLongValue("id"))
                .setCreateTime(LocalDateTime.now());
        hopeProjectVirtualBrowserRepository.save(browser);
        return dataId.getIntValue("id");
    }

    @Override
    public int launchBrowser(int id) {
        String res = HttpRequest.post(appConfig.getVirtualBrowserHost() + "/api/launchBrowser")
                .body(JSON.toJSONString(Map.of("id", id)))
                .header("api-key", appConfig.getVirtualBrowserApiKey())
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        var res = HttpRequest.post(appConfig.getVirtualBrowserHost() + "/api/stopBrowser")
                .header("api-key", appConfig.getVirtualBrowserApiKey())
                .body(jsonObject.toJSONString())
                .execute().body();
        return JSON.parseObject(res).getBoolean("success");
    }
}
