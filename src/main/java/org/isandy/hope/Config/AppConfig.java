package org.isandy.hope.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AppConfig {

    private final Environment env;

    public String getVirtualBrowserURI() {
        String host = env.getProperty("vBrowser.host");
        String port = env.getProperty("vBrowser.port");
        return "http://" + host + ":" + port;
    }

    public String getHost() {
        return env.getProperty("vBrowser.host");
    }

    public Long getUserId() {
        return Long.parseLong(Objects.requireNonNull(env.getProperty("vBrowser.userId")));
    }
}
