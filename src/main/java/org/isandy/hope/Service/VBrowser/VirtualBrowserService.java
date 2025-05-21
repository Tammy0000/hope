package org.isandy.hope.Service.VBrowser;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Config.AppConfig;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.isandy.hope.Service.HopeStorage;
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

    private final HopeProjectVBRepository hopeProjectVBRepository;

    private final HopeProjectVBARepository hopeProjectVBARepository;

    private final AppConfig appConfig;

    private final HopeStorage hopeStorage;

    @Override
    public String getBrowserList() {
        var res = HttpRequest.get(appConfig.getVirtualBrowserURI() + "/api/getBrowserList")
                .header("api-key", hopeStorage.getVirtualBrowserApiKey())
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
        HopeProjectVirtualBrowser byHost = hopeStorage.getVirtualBrowserByHost();
        Random random = new Random();
        JSONObject root = new JSONObject();
        int anInt = random.nextInt(chromeVersion.size());
        root.put("chrome_version", chromeVersion.get(anInt));
        root.put("name", byHost.getUserId());
        String res = HttpRequest.post(appConfig.getVirtualBrowserURI() + "/api/addBrowser")
                .body(JSON.toJSONString(root))
                .header("api-key", hopeStorage.getVirtualBrowserApiKey())
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(res);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            log.error("创建浏览器失败: {}", jsonObject.getString("error"));
            return -1;
        }
        JSONObject dataId = jsonObject.getJSONObject("data");
        var indexId = dataId.getIntValue("id");
        HopeProjectVirtualBrowserLinkAccount linkAccount = new HopeProjectVirtualBrowserLinkAccount();
        linkAccount.setVirtualBrowserIndexId((long) indexId)
                .setUserId(byHost.getUserId())
                .setCreateTime(LocalDateTime.now())
                .setIsProjectAccount(false)
                .setVirtualBrowserId(byHost.getVirtualBrowserId());
        hopeProjectVBARepository.save(linkAccount);
        return indexId;
    }

    @Override
    public int launchBrowserId(int id) {
        String res = HttpRequest.post(appConfig.getVirtualBrowserURI() + "/api/launchBrowser")
                .body(JSON.toJSONString(Map.of("id", id)))
                .header("api-key", hopeStorage.getVirtualBrowserApiKey())
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
        var res = HttpRequest.post(appConfig.getVirtualBrowserURI() + "/api/stopBrowser")
                .header("api-key", hopeStorage.getVirtualBrowserApiKey())
                .body(jsonObject.toJSONString())
                .execute().body();
        return JSON.parseObject(res).getBoolean("success");
    }

}
