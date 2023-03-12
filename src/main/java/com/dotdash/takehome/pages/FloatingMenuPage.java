package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FloatingMenuPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/upload";

    @FindBy(linkText = "Home")
    private WebElement homeButton;

    @FindBy(linkText = "News")
    private WebElement newsButton;

    @FindBy(linkText = "Contact")
    private WebElement contactButton;

    @FindBy(linkText = "About")
    private WebElement aboutButton;

    @FindBy(css = ".scroll > p:nth-of-type(1)")
    private WebElement fistParagraph;

    @FindBy(css = ".scroll > p:nth-of-type(7)")
    private WebElement seventhParagraph;

    public FloatingMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FloatingMenuPage scrollPageTo(WebElement el) {
        jsScroll(el.getLocation().getY());
        return this;
    }

    public FloatingMenuPage scrollPageToWithThreadSleep(WebElement el) throws InterruptedException {
        jsScroll(el.getLocation().getY());
        Thread.sleep(13);
        return this;
    }

    public boolean allButtonsInline() {
        return (homeButton.getLocation().getY() == newsButton.getLocation().getY() &&
                homeButton.getLocation().getY() == contactButton.getLocation().getY() &&
                homeButton.getLocation().getY() == aboutButton.getLocation().getY());
    }

    public WebElement getHomeButton() {
        return homeButton;
    }

    public int homeButtonVerticalPosition() {
        return homeButton.getLocation().getY();
    }

    public boolean allButtonsAreClickable() {
        return homeButton.isEnabled() &&
                newsButton.isEnabled() &&
                contactButton.isEnabled() &&
                aboutButton.isEnabled();
    }

    public WebElement getFistParagraph() {
        return fistParagraph;
    }

    public WebElement getSeventhParagraph() {
        return seventhParagraph;
    }
}

