package org.isandy.hope.Service.Twitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.TwitterSeleniumService;
import org.isandy.hope.Utils.ChromeLauncher;
import org.isandy.hope.Utils.SeleniumBrowserTabUtils;
import org.isandy.hope.Utils.SeleniumPageLoadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class Twitter implements TwitterSeleniumService {

    @Override
    public void launchTwitter() throws InterruptedException {
        ChromeLauncher.launch(9222, "C:/tmp/19", "https://x.com/home");
        ChromeOptions options = new ChromeOptions();
        //链接开发者工具
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(options);
        WebElement element;
        try {
            element = driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/button"));
        } catch (Exception e) {
            log.error("element not found");
            return;
        }
        element.click();
        Thread.sleep(2*1000);
       driver.findElement(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div/div[4]/a")).click();
    }

    @Override
    public void TestLoad() throws InterruptedException {
        //启动浏览器, 打开空白页
        ChromeLauncher.launch(9222, "C:/tmp/19", "https://x.com/home");
        ChromeOptions options = new ChromeOptions();
        //链接开发者工具
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(options);
        Thread.sleep(10*1000);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[1]/div/div[1]/div[1]/div/nav/div/div[2]/div/div[2]/a")).click();
        SeleniumPageLoadUtil.waitForPageLoad(driver);

        SeleniumBrowserTabUtils.openNewTabAndReturnDriver(driver, "https://www.baidu.com");

    }
}
