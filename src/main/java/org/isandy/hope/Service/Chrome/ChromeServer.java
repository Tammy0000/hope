package org.isandy.hope.Service.Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.IChromeService;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChromeServer implements IChromeService {


    @Override
    public void start() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        // 启用无头模式
        options.addArguments("--window-size=1280x720"); // 设置浏览器窗口大小
//        options.addArguments("--headless"); // 无头模式
        options.addArguments("--no-sandbox"); // 无沙盒模式
        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu"); //禁用GPU
        options.addArguments("--enable-automation"); // 启用自动化
        options.addArguments("--disable-notifications"); // 禁用通知
//        options.addArguments("--auto-open-devtools-for-tabs");
        options.addArguments("--lang=zh-CN");

        // 设置自定义 User-Agent 和其他指纹伪造配置
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//        options.addArguments("--lang=en-US");
        //加载插件
        options.addArguments("--load-extension=C:\\Users\\isandy\\Documents\\crx");

        // 禁用 "Chrome 正受到自动化软件控制" 提示
        options.addArguments("--disable-automation");

        // 禁用 WebDriver 特性
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver(options);
        webDriver.get("chrome-extension://mcohilncbfahbmgdjkbpemcciiolgcge/popup.html");
        log.info("ChromeServer successful!");
        log.info(webDriver.getTitle());
        Thread.sleep(60*1000);
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
