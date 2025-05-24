package org.isandy.hope.Service.Project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Config.AppConfig;
import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.isandy.hope.Service.LoadFileAccount;
import org.isandy.hope.Utils.AccountExtractor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadFile implements LoadFileAccount {

    private final HopeProjectTwitterRepository hopeProjectTwitterRepository;

    private final AppConfig appConfig;

    private final static String TWITTER_LOG_URL = "https://x.com/?logout=1722934801221";

    @Override
    public void loadFileTwitterAccount(String twitterFilePath) {
        List<Map<String, String>> twitterMaps = AccountExtractor.extractLoginAndPassword(twitterFilePath);
        for (Map<String, String> twitterMap : twitterMaps) {
            HopeProjectTwitter twitter = new HopeProjectTwitter();
            twitter.setCreateTime(LocalDateTime.now())
                    .setUserId(appConfig.getUserId())
                    .setTwitterAccount(twitterMap.get("login"))
                    .setTwitterSourcePassword(twitterMap.get("password"))
                    .setTwitterLoginWebsite(TWITTER_LOG_URL)
                    .setIsLogin(false);
            hopeProjectTwitterRepository.save(twitter);
        }
    }
}
