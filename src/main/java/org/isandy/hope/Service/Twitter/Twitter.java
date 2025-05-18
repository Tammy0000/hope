package org.isandy.hope.Service.Twitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Service.SeleniumService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Twitter implements SeleniumService {

    private final HopeProjectVirtualBrowserRepository hopeProjectVirtualBrowserRepository;

    private final HopeProjectVBARepository hopeProjectVBARepository;

    @Override
    public void loginTwitter(Long UserId) {
        HopeProjectVirtualBrowser byUserId = hopeProjectVirtualBrowserRepository.findByUserId(UserId);
    }
}
