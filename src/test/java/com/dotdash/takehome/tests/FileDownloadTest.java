package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.dotdash.takehome.utils.DriverManager.BROWSER_DOWNLOAD_FOLDER;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File Download page: /download")
public class FileDownloadTest extends BaseTest {

    @Test
    public void fileDownloaded_withDriverOptions() {

        ConditionFactory await = Awaitility.await().atMost(Duration.ofSeconds(5));

        // NOTE: name should match the name of the file we are downloading
        File tempFile = new File(BROWSER_DOWNLOAD_FOLDER, "some-file.txt");

        assertThat(tempFile.exists()).isFalse();

        new TheInternetHomePage(getDriver())
                .fileDownloadLinkClick()
                .fileToDownloadClick();

        await.until(tempFile::exists);
        assertThat(tempFile.exists()).isTrue();

        assertThat(tempFile.delete()).isTrue();
    }

    @Test
    public void fileDownloaded_withHTTPClient() throws IOException {

        // NOTE: we can assign new name that is not the name of the file we are downloading
        File tempFile = new File(BROWSER_DOWNLOAD_FOLDER, "newNameOfDownloadedFile");

        assertThat(tempFile).doesNotExist();

        WebElement fileToDownload = new TheInternetHomePage(getDriver())
                .fileDownloadLinkClick()
                .getFileToDownload();

        Utils.fileDownloader(fileToDownload.getAttribute("href"), tempFile);
        assertThat(fileToDownload.isEnabled()).isTrue();
        assertThat(tempFile).exists();

        assertThat(tempFile.delete()).isTrue();
    }




}
