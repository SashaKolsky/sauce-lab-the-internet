package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.AfterTestCallback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Form Authentication Page: /login")
@ExtendWith(AfterTestCallback.class)
public class FormAuthenticationTest extends BaseTest {

    @RepeatedTest(5)
    @DisplayName("Verify login with valid credentials")
    void verifyLoginWithValidCredentials() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .login("tomsmith", "SuperSecretPassword!")
                        .flashMessage_getText()
        ).contains("You logged into a secure area!");
    }

    @RepeatedTest(5)
    @DisplayName("Verify login with Blank Username shows error")
    void verifyLoginWithBlankUsernameShowsError() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .login("", "SuperSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your username is invalid!");
    }

    @RepeatedTest(5)
    @DisplayName("Verify login with Valid Username and Blank Password produce error message")
    void verifyLoginWithValidUsernameAndBlankPasswordProduceErrorMessage() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .login("tomsmith", "")
                        .flashMessage_getText()
        ).contains("Your password is invalid!");
    }

    @RepeatedTest(5)
    @DisplayName("Verify login with Invalid Username produce error message")
    void verifyLoginWithInvalidUsernameProduceErrorMessage() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .login("toomsmith", "SuperSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your username is invalid!");
    }

    @RepeatedTest(5)
    @DisplayName("Verify login with invalid password produces error message")
    void verifyLoginWithInvalidPasswordProducesErrorMessage() {
        assertThat(
                new TheInternetHomePage(getDriver())
                        .loginPageLinkClick()
                        .login("tomsmith", "SupeSecretPassword!")
                        .flashMessage_getText()
        ).contains("Your password is invalid!");
    }

}
