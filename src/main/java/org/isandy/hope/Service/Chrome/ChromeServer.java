package org.isandy.hope.Service.Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.IChromeService;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChromeServer implements IChromeService {

    private final ChromeOptions chromeOptions;

    @Override
    public void start() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://x.com/?logout=1722934801221");
        log.info("ChromeServer successful!");
        log.info(webDriver.getTitle());
        //在新的页面中打开
//        WebDriver webDriver1 = webDriver.switchTo().newWindow(WindowType.TAB);
//        webDriver1.get("https://www.baidu.com");


        //获取所有窗口句柄
//        for (String handle : webDriver.getWindowHandles()) {
//            //切换窗口
//            webDriver.switchTo().window(handle);
//            //输出当前窗口的标题
//            log.info(webDriver.getTitle());
//        }
        //获取指定窗口截图
//        File screenshotAs = webDriver.getScreenshotAs(OutputType.FILE);
        //保存到指定路径
//        screenshotAs.renameTo(new File("/home/screenshot.png"));
        //关闭指定窗口, 先切换到指定窗口，再关闭
        webDriver.quit();
    }
}
