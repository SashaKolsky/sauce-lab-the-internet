package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.CheckboxesPage;
import com.dotdash.takehome.pages.CheckboxesPageAlternative;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxesTest extends BaseTest{

    @Test
    public void checkboxes_CheckUncheck() {
        CheckboxesPage checkboxPage = new TheInternetHomePage(getDriver())
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
    public void checkboxesAlternative_checkUncheck() {
        CheckboxesPageAlternative checkboxPage = new TheInternetHomePage(getDriver())
                .altCheckboxesPageLinkClick();

        boolean checkbox1Status = checkboxPage.getCheckbox1Status();
        boolean checkbox2Status = checkboxPage.getCheckbox2Status();

        assertThat(checkboxPage.toggleCheckbox1()).isNotEqualTo(checkbox1Status);
        assertThat(checkboxPage.toggleCheckbox2()).isNotEqualTo(checkbox2Status);

        assertThat(checkboxPage.toggleCheckbox1()).isEqualTo(checkbox1Status);
        assertThat(checkboxPage.toggleCheckbox2()).isEqualTo(checkbox2Status);
    }

}
