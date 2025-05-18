package org.isandy.hope.Service.Storage;

import lombok.RequiredArgsConstructor;
import org.isandy.hope.Config.AppConfig;
import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Service.HopeStorage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageServer implements HopeStorage {

    private final AppConfig appConfig;

    private final HopeProjectVirtualBrowserRepository hopeProjectVirtualBrowserRepository;

    @Override
    public String getVirtualBrowserApiKey() {
        return hopeProjectVirtualBrowserRepository.findByBindHost(appConfig.getHost()).getApiKey();
    }
}
