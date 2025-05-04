package org.isandy.hope.Service.Edge;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Entity.HopeProjectTwitter;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Service.SeleniumService;
import org.isandy.hope.Utils.ChromeLauncher;
import org.isandy.hope.Utils.KeyboardTyper;
import org.isandy.hope.Utils.RobotMouseUtil;
import org.isandy.hope.Utils.RobotTextInputUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Primary
@Service
@Slf4j
@RequiredArgsConstructor
public class EdgeService implements SeleniumService {
    private final ResourceLoader resourceLoader;

    private final ProjectService projectService;
    private final HopeProjectTwitterRepository hopeProjectTwitterRepository;

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
    public void updateTwitterPassword(Long userId) throws InterruptedException, AWTException {
        List<HopeProjectTwitter> editPasswords = hopeProjectTwitterRepository.findByUserIdAndIsEditPassword(userId, false);
        HopeProjectTwitter first = editPasswords.getFirst();

        ChromeLauncher.launch(9222, "D:/tmp", "https://firstmail.ltd/en-US/webmail/login");
        Robot robot = new Robot();
        Thread.sleep(5*1000);
        robot.mouseMove(2937, 223);
        Thread.sleep(3*1000);
        robot.mouseMove(2996, 565);
        //  点击
        RobotMouseUtil.leftClick(robot);
        //输入文字
        RobotTextInputUtil.inputString(first.getEmail(), 100);
        Thread.sleep(2*1000);
        robot.mouseMove(3032, 625);
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        RobotTextInputUtil.inputString(first.getEmailSourcePassword(), 100);
        Thread.sleep(5*1000);
        //按回车
        RobotTextInputUtil.pressEnter();
        Thread.sleep(3*1000);
        //释放鼠标
        RobotMouseUtil.leftClick(robot);


//        HopeProjectTwitter first = editPasswords.getFirst();
//
//         设置远程调试端口
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver driver = new ChromeDriver(options);
        Thread.sleep(10*1000);
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div[1]/div/div/div[2]/div[2]/div/a")).click();
        Thread.sleep(3*1000);
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div[1]/div/div/div[2]/div[2]/div/ul/li[3]/a")).click();

//        System.out.println("✅ 已成功连接到现有 Chrome 实例");
//        Thread.sleep(5*1000);
//
//        Thread.sleep(10*1000);
//        driver.getTitle();

//        Thread.sleep(10*1000);
//        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(first.getEmail());
//        Thread.sleep(2*1000);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(first.getEmailSourcePassword());


//        EdgeDriver driver;
//
//
//        for (HopeProjectTwitter twitter : editPasswords) {
//            // 初始化 edge 浏览器
//            driver = new EdgeDriver(options);
//            Actions actions = new Actions(driver);
//
////             JavaScript修改
////            ((JavascriptExecutor) driver).executeScript(
////                    "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
////            );
////            ((JavascriptExecutor)driver).executeScript(
////                    "window.navigator.chrome = {runtime: {}, etc: {}};");
//
//            // 先登录邮箱去改邮箱密码
//            driver.get("https://firstmail.ltd/en-US/webmail/login");
//
//            Thread.sleep(10 * 1000);
//
//            //
//            WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"email\"]"));
//            WebElement pwd = driver.findElement(By.xpath("//*[@id=\"password\"]"));
//            WebElement login = driver.findElement(By.xpath("//*[@id=\"formAuthentication\"]/div[3]/button"));
//
//            actions.moveToElement(emailInput).pause(1000).click().perform();
//
//            // 输入邮箱地址
//            emailInput.sendKeys(twitter.getEmail());
//
//            Thread.sleep(2 * 1000);
//
//            actions.moveToElement(pwd).pause(1000).click().perform();
//
//            Thread.sleep(2 * 1000);
//
//            pwd.sendKeys(twitter.getEmailSourcePassword());
//
//            log.info(twitter.getEmail());
//
//            Thread.sleep(10 * 1000);
//            driver.close();
//            break;
//        }
    }
}
