package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextMenuPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/context_menu";

    @FindBy(id = "hot-spot")
    WebElement hotSpot;

    public ContextMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ContextMenuPage hotSpotRightClick() {
        Actions actions = new Actions(driver);
        actions.contextClick(hotSpot).perform();
        return this;
    }
}
