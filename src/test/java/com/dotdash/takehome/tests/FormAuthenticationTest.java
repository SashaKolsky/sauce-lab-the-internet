package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class FormAuthenticationTest extends BaseTest{

    @Test
    public void login_withValidCredentials() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("tomsmith", "SuperSecretPassword!");

        assertThat(getDriver().getCurrentUrl()).contains("secure");
    }

    @Test
    public void login_withBlankUsername() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("", "SuperSecretPassword!");

        assertThat(getDriver().findElement(By.id("flash")).getText()).contains("Your username is invalid!");
    }

    @Test
    public void login_withInvalidUsername() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("toomsmith", "SuperSecretPassword!");

        assertThat(getDriver().findElement(By.id("flash")).getText()).contains("Your username is invalid!");
    }

    @Test
    public void login_withValidUsernameBlankPassword() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("tomsmith", "");

        assertThat(getDriver().findElement(By.id("flash")).getText()).contains("Your password is invalid!");
    }

    @Test
    public void login_withInvalidPassword() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("tomsmith", "SupeSecretPassword!");

        assertThat(getDriver().findElement(By.id("flash")).getText()).contains("Your password is invalid!");
    }

}
