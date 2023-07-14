package com.dotdash.takehome.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static com.dotdash.takehome.utils.Utils.getProjectProperty;

public class BasePage {

    protected WebDriver driver;
    private static final int TIMEOUT = 7;
    private static final int POLLING = 100;
    private static WebDriverWait wait;

    public static final URL BASE_URL;
    static {
        try {
            String hostname = getProjectProperty("hostname");
            String port = (getProjectProperty("port").trim().length() > 0)
                    ? ":"+ getProjectProperty("port")
                    : "";
            String url = String.join("", "http://", hostname, port);
            BASE_URL = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(POLLING));
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void jsScroll(int to) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsScript = String.format("window.scrollTo(0, %d);", to);
        js.executeScript(jsScript);
    }

    protected boolean isElementNotPresent(String locatorType, String locatorString) {
        By locator;
        if (locatorType.equalsIgnoreCase("css")) {
            locator = By.cssSelector(locatorString);
        } else {
            locator = By.xpath(locatorString);
        }
        List<WebElement> elements = driver.findElements(locator);

        return elements.size() == 0 || !elements.get(0).isDisplayed();
    }
}
