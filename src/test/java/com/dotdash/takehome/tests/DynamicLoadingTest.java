package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicLoadingTest extends BaseTest{

    @Test
    public void showHiddenText() {
        assertThat(new TheInternetHomePage(getDriver())
                .dynamicLoadingClick()
                .example1PageLink()
                .showHiddenText()
                .getHiddenText()
        ).isEqualTo("Hello World!");
    }

    @Test
    public void showRenderedAfterTheFactText() {
        assertThat(new TheInternetHomePage(getDriver())
                .dynamicLoadingClick()
                .example2PageLink()
                .showHiddenText()
                .getHiddenText()
        ).isEqualTo("Hello World!");
    }

}
