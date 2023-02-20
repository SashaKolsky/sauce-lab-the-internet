package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseTest {

    static final Logger log = getLogger(lookup().lookupClass());
    static WebDriver driver;

    @BeforeAll
    static void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        //options.addArguments("--window-size=1240,1200");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    protected void setup() {
        driver.navigate().to(TheInternetHomePage.PAGE_URL);
    }

    @AfterEach
    protected void teardown() {
    }

    @AfterAll
    static void afterClass() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
