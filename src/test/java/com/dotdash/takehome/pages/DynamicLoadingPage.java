package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicLoadingPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/dynamic_loading";

    @FindBy(css = "#start > button")
    private WebElement startButton;

    @FindBy(css = "#finish > h4")
    private WebElement hiddenText;

    @FindBy(partialLinkText = "Example 1: Element on page that is hidden")
    private WebElement example1PageLink;

    @FindBy(partialLinkText = "Example 2")
    private WebElement example2PageLink;

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DynamicLoadingPage example1PageLink() {
        example1PageLink.click();
        return this;
    }

    public DynamicLoadingPage example2PageLink() {
        example1PageLink.click();
        return this;
    }

    public DynamicLoadingPage showHiddenText() {
        startButton.click();
        waitForElementToAppear(hiddenText);
        return this;
    }

    public String getHiddenText() {
        return hiddenText.getText();
    }
}

