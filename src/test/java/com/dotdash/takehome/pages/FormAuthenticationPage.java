package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static io.qameta.allure.Allure.step;

public class FormAuthenticationPage extends BasePage {

    // element binding implied with field name = id of element on page
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

    @Step("Fill out login form with username: {userName}, password: {pwd}")
    public FormAuthenticationPage login(String userName, String pwd) {
        username.sendKeys(userName);
        password.sendKeys(pwd);
        step("Click submit button", () ->
                submit.click()
        );
        return this;
    }

    @Step("Get the flash banner text for assertion")
    public String flashMessage_getText() {
        return flashMessage.getText();
    }
}
