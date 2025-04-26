package org.isandy.hope.Service.Firefox;


import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.IChromeService;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;

@Primary
@Service
@Slf4j
public class FireFoxService implements IChromeService {
    @Override
    public void start() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addExtensions(new File("D:/ex/oxk.crx"));
        // 设置窗口
        options.addArguments("--window-size=1280x720");
        // 关闭控制提示
        options.addArguments("--disable-infobars");
        // 禁用自动化提示
        options.addArguments("--disable-popup-blocking");
        // 使用持久化配置
        options.addArguments("user-data-dir=D:/ex/dir");
        options.addArguments("profile-directory=Default");


        EdgeDriver driver = new EdgeDriver(options);

        Thread.sleep(5*1000);

        driver.get("https://www.baidu.com");

        EdgeDriver okx = null;

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
}
