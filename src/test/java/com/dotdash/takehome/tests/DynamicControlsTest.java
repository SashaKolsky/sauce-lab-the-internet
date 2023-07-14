package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.DynamicControlsPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Dynamic Controls page: /dynamic_controls")
public class DynamicControlsTest extends BaseTest{

    @Test
    @DisplayName("Verify checkbox disappears and reappears upon clicking on remove add button")
    void verifyCheckboxDisappearsAndReappearsUponClickingOnRemoveAddButton() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .removeAddButtonClick();

        Assertions.assertThrows(NoSuchElementException.class, dynamicControlsPage::isCheckboxDisplayed);

        dynamicControlsPage.removeAddButtonClick();

        Assertions.assertTrue(dynamicControlsPage.isCheckboxDisplayed());
    }

    @Test
    @DisplayName("Verify checkbox disappears and reappears with check for invisibility through expected condition")
    void verifyCheckboxDisappearsAndReappearsWithCheckForInvisibilityThroughExpectedCondition() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .removeAddButtonClick();

        assertThat(dynamicControlsPage.isCheckboxVisible(), is(false));

        dynamicControlsPage.removeAddButtonClick();

        assertThat(dynamicControlsPage.isCheckboxDisplayed(), is(true));

    }

    @Test
    @DisplayName("Verify input field is enabled / disable on enable / disable button click")
    void verifyInputFieldIsEnabledDisableOnEnableDisableButtonClick() {
        DynamicControlsPage dynamicControlsPage = new TheInternetHomePage(getDriver())
                .dynamicControlsClick()
                .enableDisableButtonClick();

        assertThat(dynamicControlsPage.isInputFieldEnabled(), is(true));

        dynamicControlsPage.enableDisableButtonClick();

        assertThat(dynamicControlsPage.isInputFieldEnabled(), is(false));
    }

}
