package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.AfterTestCallback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AfterTestCallback.class)
@DisplayName("Context Menu Page: /context_menu")
public class ContextMenuTest extends BaseTest{

    @Tag("flaky")
    @Test
    @DisplayName("Verify right click in target area triggers alert popup")
    void verifyRightClickInTargetAreaTriggersAlertPopup() {
        String actual = new TheInternetHomePage(getDriver())
                .contextMenuClick()
                .hotSpotRightClick()
                .getAlertText();

        assertThat(actual).contains("You selected a context menu");

    }

}
