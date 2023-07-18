package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.FloatingMenuPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FloatingMenuTest extends BaseTest {

    /**
     * Verify all menu buttons are inline, visible and their position changes with scroll.
     * Both tests ran with 500 repetitions and no failures.
     * Lowering down Thread.sleep value to 10 produces one failed run over 500 samples.
     * The timeout parameter in waitForElementVPositionToBeInRange is arbitrary.
     */
    @Test
    @DisplayName("Verify menu stays at top during scroll with while loop")
    void verifyMenuStaysAtTopDuringScrollWithWhileLoop() {
        FloatingMenuPage fmPage = new TheInternetHomePage(getDriver())
                .floatingMenuLinkClick();

        int scrollUpToPosition = fmPage.getFistParagraph().getLocation().getY();
        int scrollDownToPosition = fmPage.getSeventhParagraph().getLocation().getY();

        fmPage.scrollPageTo(fmPage.getSeventhParagraph());
        assertThat(Utils.waitForElementVPositionToBeInRange(
                        fmPage.getHomeButton(),
                        scrollDownToPosition,
                        2,
                        5000)
        ).isTrue();

        fmPage.scrollPageTo(fmPage.getFistParagraph());
        assertThat(Utils.waitForElementVPositionToBeInRange(
                        fmPage.getHomeButton(),
                        scrollUpToPosition,
                        2,
                        5000)
        ).isTrue();
    }

    @Test
    @DisplayName("Verify menu stays at top during scroll with Thread.sleep")
    void verifyMenuStaysAtTopDuringScrollWithThreadSleep() throws InterruptedException {
        FloatingMenuPage fmPage = new TheInternetHomePage(getDriver())
                .floatingMenuLinkClick();

        int scrollUpToPosition = fmPage.getFistParagraph().getLocation().getY();
        int scrollDownToPosition = fmPage.getSeventhParagraph().getLocation().getY();

        assertThat(fmPage.scrollPageToWithThreadSleep(fmPage.getSeventhParagraph())
                        .homeButtonVerticalPosition()
        ).isCloseTo(scrollDownToPosition, Offset.offset(2));

        assertThat(fmPage.scrollPageToWithThreadSleep(fmPage.getFistParagraph())
                        .homeButtonVerticalPosition()
        ).isCloseTo(scrollUpToPosition, Offset.offset(2));
    }

}
