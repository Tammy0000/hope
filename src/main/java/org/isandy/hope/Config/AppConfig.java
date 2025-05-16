package org.isandy.hope.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppConfig {

    private final Environment env;

    public String getVirtualBrowserHost() {
        return env.getProperty("virtualBrowser.url");
    }

    public String getVirtualBrowserApiKey() {
        return env.getProperty("virtualBrowser.api-key");
    }
}
