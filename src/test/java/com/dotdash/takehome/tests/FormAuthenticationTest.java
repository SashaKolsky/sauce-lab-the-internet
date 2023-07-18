package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.AfterTestCallback;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(AfterTestCallback.class)
public class FormAuthenticationTest extends BaseTest {

    @Test
    public void login_withValidCredentials() {
        new TheInternetHomePage(getDriver())
                .loginPageLinkClick()
                .login("tomsmith", "SuperSecretPassword!");

        assertThat(getDriver().getCurrentUrl()).contains("secure");
    }

    @Test
    public void login_withBlankUsername() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .loginNegative("", "SuperSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your username is invalid!");
    }

    @Test
    public void login_withInvalidUsername() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .loginNegative("toomsmith", "SuperSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your username is invalid!");
    }

    @Test
    public void login_withValidUsernameBlankPassword() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .loginNegative("tomsmith", "")
                        .flashMessage_getText()
        ).contains("Your password is invalid!");
    }

    @Test
    public void login_withInvalidPassword() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .loginNegative("tomsmith", "SupeSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your password is invalid!");
    }

}
