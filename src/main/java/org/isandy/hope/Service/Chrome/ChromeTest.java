package org.isandy.hope.Service.Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.IChromeService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChromeTest implements IChromeService {

    @Override
    public void start() {
        ChromeOptions options = new ChromeOptions();
        // 启用无头模式
        options.addArguments("--window-size=1280x720"); // 设置浏览器窗口大小
//        options.addArguments("--headless"); // 无头模式
        options.addArguments("--no-sandbox"); // 无沙盒模式
        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu"); //禁用GPU
        options.addArguments("--enable-automation"); // 启用自动化
        options.addArguments("--disable-notifications"); // 禁用通知

        // 设置自定义 User-Agent 和其他指纹伪造配置
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("--lang=en-US");
        //加载插件
        options.addArguments("--load-extension=C:/Users/isandy/Documents/crx/okx.crx");

        // 禁用 WebDriver 特性
        options.addArguments("--disable-blink-features=AutomationControlled");
        log.info("Starting Chrome");
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get("https://x.com/?logout=1722934801221");

        WebDriverWait driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10L));
        WebElement until;

        until = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div[1]/div[1]/div/div[3]/div[4]/a")));

        until.click();

        until = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[4]/label/div/div[2]/div/input")));

        until.sendKeys("zhangch_hotmail.com");

        WebElement element = until.findElement(By.xpath("//span[text() = 'Next']"));

        element.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            chromeDriver.quit();
        }

//        chromeDriver.quit();

    }
}
