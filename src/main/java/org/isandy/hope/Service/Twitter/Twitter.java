package org.isandy.hope.Service.Twitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Service.HopeStorage;
import org.isandy.hope.Service.TwitterSeleniumService;
import org.isandy.hope.Service.VirtualBrowser;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class Twitter implements TwitterSeleniumService {

    private final HopeStorage hopeStorage;

    private final HopeProjectVBARepository hopeProjectVBARepository;

    private final HopeProjectVBRepository hopeProjectVBRepository;

    private final VirtualBrowser  virtualBrowser;

    @Override
    public void loginTwitter() {
        HopeProjectVirtualBrowser byHost = hopeStorage.getVirtualBrowserByHost();
        Long tw = hopeProjectVBARepository.findVirtualBrowserIndexIdNotInAccountType(byHost.getVirtualBrowserId(), "tw");
        // 当没有浏览器索引ID时，创建一个浏览器索引ID
        int vb_index = 0;
        if (Objects.isNull(tw)) {
            //生成一个浏览器索引ID
            vb_index = virtualBrowser.addBrowser();
            if (vb_index == 0) return;
        }
        vb_index = vb_index > 0 ? vb_index : Integer.parseInt(String.valueOf(tw));
        int port = virtualBrowser.launchBrowserId(vb_index);
    }
}
