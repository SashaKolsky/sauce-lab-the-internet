package com.dotdash.takehome.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextMenuPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/context_menu";

    @FindBy(id = "hot-spot")
    private WebElement hotSpot;

    @FindBy(tagName = "h3")
    private WebElement title;

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
        Actions actions = new Actions(driver);
        actions
                .keyDown(Keys.ESCAPE)
                .keyUp(Keys.ESCAPE)
                .build()
                .perform();
        return this;
    }

    public String getAlertText() {
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
        return text;
    }

    /**
     *  Get a stuck context menu in Chrome 111.0.5563 non-headless.
     *  Nothing seems to help get rid of it. git */
    public void closePopups() {
//        Actions actions = new Actions(driver);
//        actions.moveToElement(title).click().build().perform();
//
//        driver.switchTo().window(mainWindowHandle);
//        title.click();
        driver.navigate().to("https://google.com");
        driver.navigate().refresh();
        driver.navigate().to(PAGE_URL);
    }
}
