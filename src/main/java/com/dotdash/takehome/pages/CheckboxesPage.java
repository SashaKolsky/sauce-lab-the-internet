package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckboxesPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/checkboxes";

    @FindBy(css = "#checkboxes>input[type='checkbox']")
    @CacheLookup
    private List<WebElement> checkboxes;

    public CheckboxesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckboxesPage clickCheckbox(int index) {
        checkboxes.get(index).click();
        return this;
    }

    public boolean checkboxStatus(int index) {
        return checkboxes.get(index).isSelected();
    }

    public int numberOfCheckboxes() {
        return checkboxes.size();
    }
}