package org.isandy.hope.Service.Edge;


import cn.hutool.http.HttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Service.SeleniumService;
import org.isandy.hope.Service.VirtualBrowser;
import org.isandy.hope.Utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

@Primary
@Service
@Slf4j
@RequiredArgsConstructor
public class EdgeService implements SeleniumService {
    private final ResourceLoader resourceLoader;

    private final ProjectService projectService;

    private final HopeProjectTwitterRepository hopeProjectTwitterRepository;

    private final VirtualBrowser virtualBrowser;

    @Override
    @Async
    public void start() throws InterruptedException, MalformedURLException {
        EdgeOptions options = new EdgeOptions();
        Resource resource = resourceLoader.getResource("classpath:static/oxk.crx");
        File file;
        try {
            file = resource.getFile();
        } catch (Exception e) {
            return;
        }

        options.addExtensions(file);
        // 设置窗口
        options.addArguments("--window-size=1280x720");
        // 关闭控制提示
        options.addArguments("--disable-infobars");
        // 禁用自动化提示
        options.addArguments("--disable-popup-blocking");
        // 使用持久化配置
//        options.addArguments("user-data-dir=D:/ex/dir");
//        options.addArguments("profile-directory=Default");

        URI uri;
        try {
            uri = new URI("http://10.0.0.2:9527");
        } catch (URISyntaxException e) {
            return;
        }
        RemoteWebDriver driver = new RemoteWebDriver(uri.toURL(),options);

        Thread.sleep(5*1000);


        driver.get("https://www.baidu.com/");

        RemoteWebDriver okx = null;

        //获取所有窗口句柄
        for (String handle : driver.getWindowHandles()) {
            //切换窗口
            driver.switchTo().window(handle);
            //输出当前窗口的标题
            log.info(driver.getTitle());

            if(driver.getTitle().equals("OKX Wallet")){
                okx = driver;
                break;
            }
        }
        if(okx == null){
            driver.quit();
            return;
        }
        okx.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[3]/div/div[2]/button")).click();

        Thread.sleep(2*1000);

        okx.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[3]/div/div[1]/div[2]/div")).click();

        Thread.sleep(2*1000);

        for (int i = 1; i < 13; i++) {
            Thread.sleep(1000);
            var xpath = "//*[@id=\"app\"]/div/div[1]/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/" + "/div[" + i + "]/div[2]/input";

            okx.findElement(By.xpath(xpath)).sendKeys("12");
        }


        Thread.sleep(20*1000);

        driver.quit();
    }

    @Override
//    @Async
    public void updateTwitterPassword(Long userId) throws Exception {
        List<HopeProjectTwitter> editPasswords = hopeProjectTwitterRepository.findByUserIdAndIsEditPassword(userId, false);
        HopeProjectTwitter first = editPasswords.get(1);

        ChromeLauncher.launch(9222, "C:/tmp/" + first.getTwitterAccount(), "https://x.com/?logout=1746464052695");
//         设置远程调试端口
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver driver = new ChromeDriver(options);
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[1]/div/div/div[3]/div[4]/a/div")).click();
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[4]/label/div/div[2]/div/input")).sendKeys(first.getTwitterAccount());
        Thread.sleep(3*1000);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]")).click();
        Thread.sleep(3*1000);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[3]/div/label/div/div[2]/div[1]/input")).sendKeys(first.getTwitterSourcePassword());
        Thread.sleep(2*1000);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button")).click();
        String res = HttpRequest.get("https://2fa.fb.rip/api/otp/" + first.getTwitterSource2faPassword()).execute().body();
        String otp = Extractor2FA.extractOtp(res);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/label/div/div[2]/div/input")).sendKeys(otp);
        Thread.sleep(2*1000);
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/button")).click();
        driver.quit();
    }

    @Override
    public void TestAdsBrowser() throws InterruptedException {
        int port = virtualBrowser.launchBrowserId(1);
        ChromeOptions options = ChromeLauncher.createChromeOptions(port);
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://x.com/?logout=1747143776809");
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//span[text()='Sign in']")).click();
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("zhangch_@hotmail.com");
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//button[.//span[text()='Next']]")).click();
        Thread.sleep(5*1000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("zhssadwx");
        driver.findElement(By.xpath("//button[.//span[text()='Log in']]")).click();
    }
}
