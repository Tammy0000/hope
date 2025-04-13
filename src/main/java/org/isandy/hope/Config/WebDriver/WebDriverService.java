package org.isandy.hope.Config.WebDriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class WebDriverService {
    @Bean
    public ChromeOptions chromeOptions() {
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

        // 禁用 WebDriver 特性
        options.addArguments("--disable-blink-features=AutomationControlled");

        return options;
    }
}
