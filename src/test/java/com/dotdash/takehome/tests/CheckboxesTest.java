package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.CheckboxesAsListPage;
import com.dotdash.takehome.pages.CheckboxesWithUpwardXPathPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

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

            int finali = i;
            step("verify that toggle for checkbox "+ i +" is working as expected", () -> {
                boolean checkboxStatus = checkboxPage.isCheckboxSelected(finali);
                assertThat(checkboxPage
                        .clickCheckbox(finali)
                        .isCheckboxSelected(finali)
                ).isNotEqualTo(checkboxStatus);

                checkboxStatus = checkboxPage.isCheckboxSelected(finali);
                assertThat(checkboxPage
                        .clickCheckbox(finali)
                        .isCheckboxSelected(finali)
                ).isNotEqualTo(checkboxStatus);
            });
        }
    }


    @Test
    @DisplayName("Verify checkboxes with upward xpath check and uncheck properly")
    void verifyCheckboxesWithUpwardXpathCheckAndUncheckProperly() {
        CheckboxesWithUpwardXPathPage checkboxPage =
                new TheInternetHomePage(getDriver())
                        .altCheckboxesPageLinkClick();

        final String textForCheckbox1 = "checkbox 1";
        final String textForCheckbox2 = "checkbox 2";

        step("assert that clicking checkbox changes its state", () -> {
            boolean checkbox1Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox1);
            boolean checkbox2Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox2);

            assertThat(checkboxPage.toggleCheckboxWithText(textForCheckbox1)).isNotEqualTo(checkbox1Status);
            assertThat(checkboxPage.toggleCheckboxWithText(textForCheckbox2)).isNotEqualTo(checkbox2Status);

            checkbox1Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox1);
            checkbox2Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox2);

            assertThat(checkboxPage.toggleCheckboxWithText(textForCheckbox1)).isNotEqualTo(checkbox1Status);
            assertThat(checkboxPage.toggleCheckboxWithText(textForCheckbox2)).isNotEqualTo(checkbox2Status);
        });

        step("assert that clicking on one checkbox doesn't change the state of the other", () -> {
            boolean checkbox2Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox2);
            checkboxPage.toggleCheckboxWithText(textForCheckbox1);
            assertThat(checkboxPage.getStateOfCheckboxWithText(textForCheckbox2)).isEqualTo(checkbox2Status);

            boolean checkbox1Status = checkboxPage.getStateOfCheckboxWithText(textForCheckbox1);
            checkboxPage.toggleCheckboxWithText(textForCheckbox2);
            assertThat(checkboxPage.getStateOfCheckboxWithText(textForCheckbox1)).isEqualTo(checkbox1Status);
        });

    }

}
