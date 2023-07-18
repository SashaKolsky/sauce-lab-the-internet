package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TheInternetHomePage extends BasePage {

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

    @FindBy(partialLinkText = "Hovers")
    WebElement hoversLink;

    public TheInternetHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FormAuthenticationPage loginPageLinkClick(){
        loginPageLink.click();
        return new FormAuthenticationPage(driver);
    }

    public CheckboxesAsListPage checkboxesPageLinkClick(){
        checkboxesPageLink.click();
        return new CheckboxesAsListPage(driver);
    }

    public CheckboxesWithUpwardXPathPage altCheckboxesPageLinkClick(){
        checkboxesPageLink.click();
        return new CheckboxesWithUpwardXPathPage(driver);
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

    public WysiwygEditorPage wysiwygLinkClick() {
        framesLink.click();
        iFrameLink.click();
        return new WysiwygEditorPage(driver);
    }

    public HoversPage hoversLinkClick() {
        hoversLink.click();
        return new HoversPage(driver);
    }

}