package org.isandy.hope.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    // 使用 ThreadLocal 为每个线程提供独立的 WebDriver 实例
    private static ThreadLocal<WebDriver> driverThreadLocal = ThreadLocal.withInitial(() -> new EdgeDriver());

    // 使用 ThreadLocal 为每个线程提供独立的 WebDriverWait 实例
    private static ThreadLocal<WebDriverWait> waitThreadLocal = ThreadLocal.withInitial(() -> {
        return new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(10));
    });

    // 获取当前线程的 WebDriver 实例
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // 获取当前线程的 WebDriverWait 实例
    public static WebDriverWait getWait() {
        return waitThreadLocal.get();
    }

    // 初始化 WebDriver（如果需要）并获取 WebDriver 实例
    public static void initializeWebDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(new ChromeDriver());
        }
    }

    // 等待某个元素可见并返回该元素
    public static WebElement waitForVisibilityOfElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 等待某个元素可点击并返回该元素
    public static WebElement waitForElementToBeClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    // 等待页面完全加载
    public static void waitForPageToLoad() {
        getWait().until(driver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete"));
    }

    // 关闭 WebDriver 实例
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
            waitThreadLocal.remove();
        }
    }
}
