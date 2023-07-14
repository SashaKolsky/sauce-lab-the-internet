package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicControlsPage extends BasePage {
    @FindBy(css = "#checkbox")
    private WebElement checkbox;

    @FindBy(css = "#checkbox-example > button")
    private WebElement removeAddButton;

    @FindBy(css = "#input-example > input")
    private WebElement inputField;

    @FindBy(css = "#input-example > button")
    private WebElement enableDisableButton;

    @FindBy(css = "#content > div.row:nth-of-type(2) img")
    private WebElement thirdImage;

    @FindBy(css = "#content > div.row:nth-of-type(2) div:nth-of-type(2)")
    private WebElement thirdText;

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DynamicControlsPage removeAddButtonClick() {
        removeAddButton.click();
        waitForElementToBeClickable(removeAddButton);
        return this;
    }

    public boolean isCheckboxDisplayed() {
        return checkbox.isDisplayed();
    }

    public boolean isCheckboxVisible() {
        waitForElementToDisappear(checkbox);
        return false;
    }

    public DynamicControlsPage enableDisableButtonClick() {
        enableDisableButton.click();
        waitForElementToBeClickable(enableDisableButton);
        return this;
    }

    public boolean isInputFieldEnabled() {
        return inputField.isEnabled();
    }
}

