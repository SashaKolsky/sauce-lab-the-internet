package com.dotdash.takehome.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class ContextMenuPage extends BasePage {

    private static final URL PAGE_URL;
    static {
        try {
            PAGE_URL = new URL(BASE_URL.getProtocol(), BASE_URL.getHost(), BASE_URL.getPort(), "/context_menu");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "hot-spot")
    private WebElement hotSpot;

    String mainWindowHandle;

    public ContextMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ContextMenuPage hotSpotRightClick() {
        Actions actions = new Actions(driver);
        actions.contextClick(hotSpot).perform();
        return this;
    }

    public ContextMenuPage escapeToCloseTheContextMenu() {
        new Actions(driver)
                .keyDown(Keys.ESCAPE)
                .keyUp(Keys.ESCAPE)
                .build()
                .perform();
        return this;
    }

    public String getAlertText() {
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        escapeToCloseTheContextMenu();
        return text;
    }
}
