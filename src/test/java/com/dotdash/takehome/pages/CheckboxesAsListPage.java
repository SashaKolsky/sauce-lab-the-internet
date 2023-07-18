package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckboxesAsListPage extends BasePage {

    @FindBy(css = "#checkboxes>input[type='checkbox']")
    @CacheLookup
    private List<WebElement> checkboxes;

    public CheckboxesAsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("click checkbox with index {index}")
    public CheckboxesAsListPage clickCheckbox(int index) {
        checkboxes.get(index).click();
        return this;
    }

    @Step("check status of checkbox with index {index}")
    public boolean checkboxStatus(int index) {
        return checkboxes.get(index).isSelected();
    }

    public int numberOfCheckboxes() {
        return checkboxes.size();
    }
}