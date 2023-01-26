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

import java.util.concurrent.TimeUnit;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseTest {

    static final Logger log = getLogger(lookup().lookupClass());
    private WebDriver driver;
    static private long startTime;
    static private long endTime;

    @BeforeAll
    static void beforeClass() {
        startTime = System.nanoTime();
    }

    @BeforeEach
    protected void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        driver = new ChromeDriver(options);

        driver.navigate().to(TheInternetHomePage.PAGE_URL);
    }

    @AfterEach
    protected void teardown() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    @AfterAll
    static void afterClass() {
        endTime = System.nanoTime();
        long runtime = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        log.atInfo().log("Runtime for all tests in class: "+ runtime);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
