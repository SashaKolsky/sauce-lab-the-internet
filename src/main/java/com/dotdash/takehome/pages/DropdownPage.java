package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/dropdown";
    Select dropdownEl;

    @CacheLookup
    private WebElement dropdown;

    public DropdownPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Select getDropdown() {
        return (dropdownEl == null) ? new Select(dropdown) : dropdownEl;
    }
}

