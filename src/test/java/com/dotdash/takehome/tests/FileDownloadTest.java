package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloadTest extends BaseTest{

    static File targetFolder;

    @BeforeAll
    static void beforeClass() {
        targetFolder = new File(System.getProperty("user.home"), "Downloads");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", targetFolder.toString());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new", "--window-size=1240,1200");
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
    }

    @Test
    public void fileDownloaded_withDriverOptions() {

        new TheInternetHomePage(getDriver())
                .fileDownloadLinkClick()
                .fileToDownloadClick();

        ConditionFactory await = Awaitility.await().atMost(Duration.ofSeconds(5));
        File tempFile = new File(targetFolder, "some-file.txt");
        assertThat(tempFile).doesNotExist();
        await.until(tempFile::exists);

        assertThat(tempFile.delete()).isTrue();
    }

    @Test
    public void fileDownloaded_withHTTPClient() throws IOException {
        WebElement fileToDownload = new TheInternetHomePage(getDriver())
                .fileDownloadLinkClick()
                .getFileToDownload();

        File tempFile = new File(".", "someFile");
        assertThat(tempFile).doesNotExist();

        Utils.fileDownloader(fileToDownload.getAttribute("href"), tempFile);
        assertThat(fileToDownload.isEnabled()).isTrue();
        assertThat(tempFile).exists();

        assertThat(tempFile.delete()).isTrue();
    }




}
