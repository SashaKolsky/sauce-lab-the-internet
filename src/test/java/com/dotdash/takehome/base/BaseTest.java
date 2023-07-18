package com.dotdash.takehome.base;

import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import static com.dotdash.takehome.utils.Utils.getProjectProperty;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseTest {

    static final Logger log = getLogger(lookup().lookupClass());
    WebDriver driver;

    @BeforeAll
    static void beforeClass() {
    }

    @BeforeEach
    protected void setup() {
        DriverManager.Type driverType = selectDriverType();

        driver = DriverManager.setDriver(driverType);
        driver.navigate().to(TheInternetHomePage.BASE_URL);
    }

    protected DriverManager.Type selectDriverType() {

        String browserParameter = getProjectProperty("browser").toUpperCase();
        try {
            return DriverManager.Type.valueOf(browserParameter);
        } catch (IllegalArgumentException exception) {
            log.atError().log("Invalid driver variable value (chrome, firefox, grid). Invalid value: " + browserParameter);
            throw new RuntimeException("Please specify a valid driver option (chrome, firefox, grid). Invalid value: " + browserParameter);
        }
    }

    @AfterEach
    protected void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    static void afterClass() {
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
