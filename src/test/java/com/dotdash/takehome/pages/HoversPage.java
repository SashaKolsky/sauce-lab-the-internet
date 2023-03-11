package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HoversPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/hovers";

    @FindBy(css = ".figure:nth-of-type(1)")
    private WebElement user1;

    @FindBy(css = ".figure:nth-of-type(2)")
    private WebElement user2;

    @FindBy(css = ".figure:nth-of-type(3)")
    private WebElement user3;

    private final String user1NamePopupCSS = (".figure:nth-of-type(1) h5");
    @FindBy(css = user1NamePopupCSS)
    private WebElement user1NamePopup;

    private final String user2NamePopupCSS = (".figure:nth-of-type(2) h5");
    @FindBy(css = user2NamePopupCSS)
    private WebElement user2NamePopup;

    private final String user3NamePopupCSS = (".figure:nth-of-type(3) h5");
    @FindBy(css = user3NamePopupCSS)
    private WebElement user3NamePopup;

    public HoversPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUser1HoverData() {
        Actions actions = new Actions(driver);
        actions.moveToElement(user1).perform();
        waitForElementToAppear(user1NamePopup);
        return user1NamePopup.getText();
    }

    public String getUser2HoverData() {
        Actions actions = new Actions(driver);
        actions.moveToElement(user2).perform();
        waitForElementToAppear(user2NamePopup);
        return user2NamePopup.getText();
    }

    public String getUser3HoverData() {
        Actions actions = new Actions(driver);
        actions.moveToElement(user3).perform();
        waitForElementToAppear(user3NamePopup);
        return user3NamePopup.getText();
    }

    public boolean isUser1HoverDataNotPresent() {
        return isElementNotPresent("css", user1NamePopupCSS);
    }

    public boolean isUser2HoverDataNotPresent() {
        return isElementNotPresent("css", user2NamePopupCSS);
    }

    public boolean isUser3HoverDataNotPresent() {
        return isElementNotPresent("css", user3NamePopupCSS);
    }
}

