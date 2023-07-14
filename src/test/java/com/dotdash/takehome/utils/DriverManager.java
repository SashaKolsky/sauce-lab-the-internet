package com.dotdash.takehome.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.dotdash.takehome.utils.Utils.getProjectProperty;

public class DriverManager {

    public static final File BROWSER_DOWNLOAD_FOLDER = new File(System.getProperty("user.home"), "Downloads");

    public enum Type {
        CHROME, FIREFOX, GRID
    }

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static WebDriver setDriver(Type type) {

        switch (type) {
            case GRID -> {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
                driverThreadLocal.set(new RemoteWebDriver(capabilities));
            }
            case CHROME -> {
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .withLogOutput(System.out)
                        .build();
                driverThreadLocal.set(new ChromeDriver(service, getChromeOptions()));
            }
            case FIREFOX ->
                driverThreadLocal.set(new FirefoxDriver(getFirefoxOptions()));
        }

        return getDriver();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    private static ChromeOptions getChromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(getProjectProperty("headless"))) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--window-size=");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", BROWSER_DOWNLOAD_FOLDER.toString());
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {

        // Specific to these tests (firefox)
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.addArguments("browser.download.dir", BROWSER_DOWNLOAD_FOLDER.toString());
        //firefoxOptions.addArguments("browser.download.manager.showWhenStarting", "false");

        if (Boolean.parseBoolean(getProjectProperty("headless"))) {
            firefoxOptions.addArguments("--headless");
        }

        return firefoxOptions;
    }
}