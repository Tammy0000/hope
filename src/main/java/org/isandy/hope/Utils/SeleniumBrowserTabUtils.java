package org.isandy.hope.Utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumBrowserTabUtils {
    /**
     * 打开新标签页并返回新标签页的driver
     * @param driver 当前ChromeDriver实例
     * @return 新标签页的ChromeDriver实例（实际上是同一个driver对象，只是切换了上下文）
     */
    public static ChromeDriver openNewTabAndReturnDriver(ChromeDriver driver) {
        return openNewTabAndReturnDriver(driver, "about:blank");
    }

    /**
     * 打开新标签页并导航到指定URL，返回新标签页的driver
     * @param driver 当前ChromeDriver实例
     * @param url 要在新标签页打开的URL
     * @return 新标签页的ChromeDriver实例
     */
    public static ChromeDriver openNewTabAndReturnDriver(ChromeDriver driver, String url) {
        String originalWindow = driver.getWindowHandle();

        // 在新标签页打开URL
        driver.executeScript("window.open(arguments[0])", url);

        // 切换到新标签页
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        return driver;
    }

    /**
     * 关闭当前标签页并切换回指定标签页
     * @param driver 当前ChromeDriver实例
     * @param originalWindow 要切换回的窗口句柄
     * @return 切换后的ChromeDriver实例
     */
    public static ChromeDriver closeAndSwitchBack(ChromeDriver driver, String originalWindow) {
        driver.close();
        driver.switchTo().window(originalWindow);
        return driver;
    }
}
