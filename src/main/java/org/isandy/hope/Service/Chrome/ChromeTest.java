package org.isandy.hope.Service.Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Config.WebDriver.WebDriverService;
import org.isandy.hope.Service.IChromeService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class ChromeTest implements IChromeService {

    private final ChromeOptions chromeOptions;

    @Override
    public void start() {
        log.info("Starting Chrome");
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
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
