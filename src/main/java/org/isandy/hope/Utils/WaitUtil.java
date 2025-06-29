package org.isandy.hope.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {
    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * 构造方法，初始化等待工具类。
     *
     * @param driver  WebDriver 实例
     * @param timeout 等待超时时间
     */
    public WaitUtil(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    /**
     * 等待元素可以被点击，并返回该元素。
     *
     * @param locator 元素定位器
     * @return 可点击的 WebElement
     */
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * 等待元素可见（存在于 DOM 中且非隐藏），并返回该元素。
     *
     * @param locator 元素定位器
     * @return 可见的 WebElement
     */
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * 等待元素出现在 DOM 中，不保证其可见性。
     *
     * @param locator 元素定位器
     * @return 出现在 DOM 中的 WebElement
     */
    public WebElement waitForPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * 等待某个元素从页面中消失（不可见或不存在）。
     *
     * @param locator 元素定位器
     * @return 如果元素在指定时间内消失，返回 true；否则 false
     */
    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * 等待元素可点击，并执行点击操作。
     *
     * @param locator 元素定位器
     */
    public void waitAndClick(By locator) {
        waitForClickable(locator).click();
    }

    /**
     * 等待元素可见，并向其输入文本。
     *
     * @param locator 元素定位器
     * @param keys    要输入的文本
     */
    public void waitAndSendKeys(By locator, String keys) {
        waitForVisible(locator).sendKeys(keys);
    }
}
