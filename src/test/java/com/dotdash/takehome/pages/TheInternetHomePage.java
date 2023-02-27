package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TheInternetHomePage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080";

    @FindBy(partialLinkText = "Form Authentication")
    WebElement loginPageLink;

    @FindBy(partialLinkText = "Checkboxes")
    WebElement checkboxesPageLink;

    @FindBy(partialLinkText = "Context Menu")
    WebElement contextMenuPageLink;

    @FindBy(partialLinkText = "Drag and Drop")
    WebElement dragAndDropLink;

    @FindBy(partialLinkText = "Dropdown")
    WebElement dropdownLink;

    @FindBy(partialLinkText = "Dynamic Content")
    WebElement dynamicContentLink;

    @FindBy(partialLinkText = "Dynamic Controls")
    WebElement dynamicControlsLink;

    @FindBy(partialLinkText = "Dynamic Loading")
    WebElement dynamicLoadingLink;

    @FindBy(partialLinkText = "File Download")
    WebElement fileDownloadLink;

    @FindBy(partialLinkText = "File Upload")
    WebElement fileUploadLink;

    @FindBy(partialLinkText = "Floating Menu")
    WebElement floatingMenuLink;

    @FindBy(partialLinkText = "Frames")
    WebElement framesLink;

    @FindBy(partialLinkText = "iFrame")
    WebElement iFrameLink;

    public TheInternetHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FormAuthenticationPage loginPageLinkClick(){
        loginPageLink.click();
        return new FormAuthenticationPage(driver);
    }

    public CheckboxesPage checkboxesPageLinkClick(){
        checkboxesPageLink.click();
        return new CheckboxesPage(driver);
    }

    public CheckboxesPageAlternative altCheckboxesPageLinkClick(){
        checkboxesPageLink.click();
        return new CheckboxesPageAlternative(driver);
    }

    public ContextMenuPage contextMenuClick() {
        contextMenuPageLink.click();
        return new ContextMenuPage(driver);
    }

    public DragAndDropPage dragAndDropClick() {
        dragAndDropLink.click();
        return new DragAndDropPage(driver);
    }

    public DropdownPage dropdownClick() {
        dropdownLink.click();
        return new DropdownPage(driver);
    }

    public DynamicContentPage dynamicContentClick() {
        dynamicContentLink.click();
        return new DynamicContentPage(driver);
    }

    public DynamicControlsPage dynamicControlsClick() {
        dynamicControlsLink.click();
        return new DynamicControlsPage(driver);
    }

    public DynamicLoadingPage dynamicLoadingClick() {
        dynamicLoadingLink.click();
        return new DynamicLoadingPage(driver);
    }

    public FileDownloadPage fileDownloadLinkClick() {
        fileDownloadLink.click();
        return new FileDownloadPage(driver);
    }

    public FileUploadPage fileUploadLinkClick() {
        fileUploadLink.click();
        return new FileUploadPage(driver);
    }

    public FloatingMenuPage floatingMenuLinkClick() {
        floatingMenuLink.click();
        return new FloatingMenuPage(driver);
    }

    public IframePage iFrameLinkClick() {
        framesLink.click();
        iFrameLink.click();
        return new IframePage(driver);
    }

}