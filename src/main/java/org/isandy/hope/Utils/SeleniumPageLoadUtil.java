package org.isandy.hope.Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SeleniumPageLoadUtil {
    private static final int DEFAULT_TIMEOUT = 30;

    public static void waitForPageLoad(WebDriver driver) {
        waitForPageLoad(driver, DEFAULT_TIMEOUT);
    }

    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver -> Objects.equals(
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState"),
                        "complete"));
    }
}
