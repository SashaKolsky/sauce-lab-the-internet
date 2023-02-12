package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormAuthenticationPage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/login";

    WebElement username;
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submit;

    @FindBy(id = "flash")
    WebElement flashMessage;

    public FormAuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String userName, String pwd) {
        username.sendKeys(userName);
        password.sendKeys(pwd);
        submit.click();
    }

    public FormAuthenticationPage loginNegative(String userName, String pwd) {
        username.sendKeys(userName);
        password.sendKeys(pwd);
        submit.click();

        return this;
    }

    public String flashMessage_getText() {
        return flashMessage.getText();
    }
}
