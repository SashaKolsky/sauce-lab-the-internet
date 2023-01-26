package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextMenuTest extends BaseTest{

    @Test
    public void contextMenu_click() {
        new TheInternetHomePage(getDriver())
            .contextMenuClick()
            .hotSpotRightClick();

        assertThat("You selected a context menu").contains(getDriver().switchTo().alert().getText());
        getDriver().switchTo().alert().dismiss();
    }

}
