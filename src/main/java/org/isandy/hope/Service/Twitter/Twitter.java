package org.isandy.hope.Service.Twitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.isandy.hope.Service.HopeStorage;
import org.isandy.hope.Service.TwitterSeleniumService;
import org.isandy.hope.Service.VirtualBrowser;
import org.isandy.hope.Utils.ChromeLauncher;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class Twitter implements TwitterSeleniumService {

    private final HopeStorage hopeStorage;

    private final HopeProjectVBARepository hopeProjectVBARepository;

    private final HopeProjectVBRepository hopeProjectVBRepository;

    private final VirtualBrowser  virtualBrowser;

    private final HopeProjectTwitterRepository hopeProjectTwitterRepository;

    @Override
    public void loginTwitter() throws InterruptedException {
        HopeProjectVirtualBrowser byHost = hopeStorage.getVirtualBrowserByHost();
        List<HopeProjectTwitter> logins = hopeProjectTwitterRepository.findByUserIdAndIsLogin(byHost.getUserId(), false);
        if (logins.isEmpty()) {
            log.warn("没有需要登录的账号");
            return;
        }
        Long virtualBrowserId = byHost.getVirtualBrowserId();
        Long userId = byHost.getUserId();
        for (HopeProjectTwitter login : logins) {
            Long tw = hopeProjectVBARepository.findVirtualBrowserIndexIdNotInAccountType(userId, virtualBrowserId, "tw");
            // 当没有浏览器索引ID时，创建一个浏览器索引ID
            int vb_index;
            if (Objects.isNull(tw)) {
                //生成一个浏览器索引ID
                vb_index = virtualBrowser.addBrowser();
                if (vb_index == 0) return;
            } else {
                vb_index = Integer.parseInt(String.valueOf(tw));
            }
            int port = virtualBrowser.launchBrowserId(vb_index);
            ChromeOptions options = ChromeLauncher.createChromeOptions(port);
            ChromeDriver driver = new ChromeDriver(options);
            driver.get(login.getTwitterLoginWebsite());
            Thread.sleep(10*1000);
            driver.findElement(By.xpath("//span[text()='Sign in']")).click();
            Thread.sleep(5*1000);
            driver.findElement(By.xpath("//input[@name='text']")).sendKeys(login.getTwitterAccount());
            Thread.sleep(5*1000);
            driver.findElement(By.xpath("//button[.//span[text()='Next']]")).click();
            Thread.sleep(5*1000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(login.getTwitterSourcePassword());
            driver.findElement(By.xpath("//button[.//span[text()='Log in']]")).click();
            Thread.sleep(8*1000);
            try {
                // 登录成功后，在主页右下角有一个Grok按钮，如果存在点击无报错则代表登录成功
                driver.findElement(By.xpath("//button[contains(@class, 'r-6koalj') and contains(@class, 'r-1ny4l3l')]")).click();
                log.info("登录成功: {}", login.getTwitterAccount());
                HopeProjectVirtualBrowserLinkAccount browserIndexId = hopeProjectVBARepository.findByUserIdAndVirtualBrowserIdAndVirtualBrowserIndexId(userId, virtualBrowserId, (long) vb_index);
                //处理推特关联账号信息，方便下次自动识别
                browserIndexId.setAccount(login.getTwitterAccount())
                        .setAccountType("tw")
                        .setLoginUrl(login.getTwitterLoginWebsite());
                hopeProjectVBARepository.save(browserIndexId);
                //在推特账号信息标记为已登录
                HopeProjectTwitter twitterAccount = hopeProjectTwitterRepository.findByUserIdAndTwitterAccount(userId, login.getTwitterAccount());
                twitterAccount.setIsLogin(true);
                hopeProjectTwitterRepository.save(twitterAccount);
            } catch (Exception e) {
                log.error("登录失败");
            } finally {
                //使用api工具去停止浏览器
                virtualBrowser.stopBrowser(Math.toIntExact(vb_index));
            }
        }
    }
}
