package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileUploadPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/upload";

    @CacheLookup
    @FindBy(id = "file-upload")
    private WebElement uploadInput;

    @CacheLookup
    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadButtonConfirmationDiv;

    @FindBy(id = "drag-drop-upload")
    private WebElement dragAndDropUploadDiv;

    @FindBy(className = "dz-hidden-input")
    private WebElement dragAndDropHiddenInput;

    @FindBy(css = ".dz-filename > span")
    private WebElement dragAndDropUploadConfirmation;

    public FileUploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FileUploadPage uploadInputFile(String filePath) {
        uploadInput.sendKeys(filePath);
        return this;
    }

    public WebElement uploadFile() {
        uploadButton.click();
        waitForElementToAppear(uploadButtonConfirmationDiv);
        return uploadButtonConfirmationDiv;
    }

    public FileUploadPage uploadFileWithDragAndDrop(String filePath) {
        dragAndDropHiddenInput.sendKeys(filePath);
        return this;
    }

    public String getDragAndDropUploadConfirmation() {
        waitForElementToAppear(dragAndDropUploadConfirmation);
        return dragAndDropUploadConfirmation.getText().trim();
    }
}

