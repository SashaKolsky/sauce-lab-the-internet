package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesWithUpwardXPathPage extends BasePage {

    public CheckboxesWithUpwardXPathPage(WebDriver driver) {
        super(driver);
    }

    public boolean getStateOfCheckboxWithText(String text) {
        return getCheckboxWithText(text).isSelected();
    }

    public boolean toggleCheckboxWithText(String text) {
        WebElement checkbox = getCheckboxWithText(text);
        checkbox.click();
        return checkbox.isSelected();
    }

    private WebElement getCheckboxWithText(String text) {
        By xpath = By.xpath("//form/text()[contains(., '" + text + "')]/preceding::input[1]");
        return driver.findElement(xpath);
    }

}