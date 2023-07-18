package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.CheckboxesAsListPage;
import com.dotdash.takehome.pages.CheckboxesWithUpwardXPathPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("simple")
@DisplayName("Checkboxes page: /checkboxes")
public class CheckboxesTest extends BaseTest {

    @Test
    @DisplayName("Verify checkboxes with list check and uncheck properly")
    void verifyCheckboxesWithListCheckAndUncheckProperly() {
        CheckboxesAsListPage checkboxPage =
                new TheInternetHomePage(getDriver())
                        .checkboxesPageLinkClick();

        int numberOfCheckboxes = checkboxPage.numberOfCheckboxes();
        for(int i = 0; i < numberOfCheckboxes; i++) {

            if (checkboxPage.checkboxStatus(i)) {
                checkboxPage.clickCheckbox(i);
                assertThat(checkboxPage.checkboxStatus(i)).isFalse();

                checkboxPage.clickCheckbox(i);
                assertThat(checkboxPage.checkboxStatus(i)).isTrue();
            } else {
                checkboxPage.clickCheckbox(i);
                assertThat(checkboxPage.checkboxStatus(i)).isTrue();

                checkboxPage.clickCheckbox(i);
                assertThat(checkboxPage.checkboxStatus(i)).isFalse();
            }
        }
    }


    @Test
    @DisplayName("Verify checkboxes with upward xpath check and uncheck properly")
    void verifyCheckboxesWithUpwardXpathCheckAndUncheckProperly() {
        CheckboxesWithUpwardXPathPage checkboxPage =
                new TheInternetHomePage(getDriver())
                        .altCheckboxesPageLinkClick();

        boolean checkbox1Status = checkboxPage.getCheckbox1Status();
        boolean checkbox2Status = checkboxPage.getCheckbox2Status();

        assertThat(checkboxPage.toggleCheckbox1()).isNotEqualTo(checkbox1Status);
        assertThat(checkboxPage.toggleCheckbox2()).isNotEqualTo(checkbox2Status);

        assertThat(checkboxPage.toggleCheckbox1()).isEqualTo(checkbox1Status);
        assertThat(checkboxPage.toggleCheckbox2()).isEqualTo(checkbox2Status);
    }

}
