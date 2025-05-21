package org.isandy.hope.Service.Storage;

import lombok.RequiredArgsConstructor;
import org.isandy.hope.Config.AppConfig;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Service.HopeStorage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageServer implements HopeStorage {

    private final AppConfig appConfig;

    private final HopeProjectVBRepository hopeProjectVBRepository;

    @Override
    public String getVirtualBrowserApiKey() {
        return hopeProjectVBRepository.findByBindHostAndUserId(appConfig.getHost(), appConfig.getUserId()).getApiKey();
    }

    @Override
    public HopeProjectVirtualBrowser getVirtualBrowserByHost() {
        return hopeProjectVBRepository.findByBindHostAndUserId(appConfig.getHost(), appConfig.getUserId());
    }
}
