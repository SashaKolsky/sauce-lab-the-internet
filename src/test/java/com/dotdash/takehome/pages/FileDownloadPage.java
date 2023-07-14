package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileDownloadPage extends BasePage {

    @FindBy(partialLinkText = "some-file.txt")
    private WebElement fileToDownload;

    public FileDownloadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FileDownloadPage fileToDownloadClick() {
        fileToDownload.click();
        return this;
    }

    public WebElement getFileToDownload() {
        return fileToDownload;
    }
}

