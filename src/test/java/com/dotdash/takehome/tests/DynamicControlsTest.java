package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.DynamicControlsPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicControlsTest extends BaseTest{

    @Test
    public void removeAddCheckboxTest() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .removeAddButtonClick();

        boolean isCheckboxPresent = false;
        try {
            isCheckboxPresent = dynamicControlsPage.getCheckbox().isDisplayed();
            assertThat(isCheckboxPresent)
                    .withFailMessage("Checkbox is not present")
                    .isFalse();
        } catch (NoSuchElementException e) {
            assertThat(isCheckboxPresent).isFalse();
        }

        dynamicControlsPage.removeAddButtonClick();
        assertThat(dynamicControlsPage.getCheckbox().isDisplayed())
                .withFailMessage("Checkbox is present / visible")
                .isTrue();
    }

    @Test
    @DisplayName("removeAddCheckboxTest (with wait for invisibility in Page class")
    public void removeAddCheckboxTest_withWaitForInvisibility() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .removeAddButtonClick();
        assertThat(dynamicControlsPage.isCheckboxPresent())
                .withFailMessage("Checkbox is present / visible")
                .isFalse();

        dynamicControlsPage.removeAddButtonClick();
        assertThat(dynamicControlsPage.getCheckbox().isDisplayed())
                .withFailMessage("Checkbox is present / visible")
                .isTrue();
    }

    @Test
    public void enableDisableTextfieldTest() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .enableDisableButtonClick();
        assertThat(dynamicControlsPage.getInputField().isEnabled())
                .withFailMessage("Text field is enabled")
                .isTrue();

        dynamicControlsPage.enableDisableButtonClick();
        assertThat(dynamicControlsPage.getInputField().isEnabled())
                .withFailMessage("Text field is disabled")
                .isFalse();
    }

}
