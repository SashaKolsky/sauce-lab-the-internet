package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TheInternetHomePage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080";

    @FindAll({@FindBy(partialLinkText = "Context Menu"),
              @FindBy(partialLinkText = "Digest Authentication")})

    @FindBy(partialLinkText = "Form Authentication")
    WebElement loginPageLink;

    @FindBy(partialLinkText = "Checkboxes")
    WebElement checkboxesPageLink;

    public TheInternetHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FormAuthenticationPage loginPageLinkClick(){
        loginPageLink.click();
        return new FormAuthenticationPage(driver);
    }

    public CheckboxesPage checkboxesPageLinkClick(){
        checkboxesPageLink.click();
        return new CheckboxesPage(driver);
    }


}