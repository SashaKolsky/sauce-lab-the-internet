package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxesPageAlternative extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/checkboxes";

    @FindBy(xpath = "//form/text()[contains(., 'checkbox 1')]/preceding::input[1]")
    @CacheLookup
    private WebElement checkbox1;

    @FindBy(xpath = "//form/text()[contains(., 'checkbox 2')]/preceding::input[1]")
    @CacheLookup
    private WebElement checkbox2;

    public CheckboxesPageAlternative(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean getCheckbox1Status() {
        return checkbox1.isSelected();
    }

    public boolean getCheckbox2Status() {
        return checkbox2.isSelected();
    }

    public boolean toggleCheckbox1() {
        checkbox1.click();
        return  checkbox1.isSelected();
    }

    public boolean toggleCheckbox2() {
        checkbox2.click();
        return checkbox2.isSelected();
    }

}