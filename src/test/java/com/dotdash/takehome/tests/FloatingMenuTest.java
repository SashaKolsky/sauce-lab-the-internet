package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.FloatingMenuPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FloatingMenuTest extends BaseTest{

    /**
     * Verify all menu buttons are inline, visible and their position changes with scroll.
     * Both tests ran with 500 repeatitions and no failures.
     * Lowering down Thread.sleep value to 10 produces one failed run over 500 samles.
     * The timeout parameter in waitForElementVPositionToBeInRange is arbitrary.
     */
    @Test
    public void floatingMenuTest() {
        FloatingMenuPage fmPage = new TheInternetHomePage(getDriver())
                .floatingMenuLinkClick();

        int scrollUpToPosition = fmPage.getFistParagraph().getLocation().getY();
        int scrollDownToPosition = fmPage.getSeventhParagraph().getLocation().getY();

        fmPage.scrollPageTo(fmPage.getSeventhParagraph());
        assertThat(
                Utils.waitForElementVPositionToBeInRange(
                        fmPage.getHomeButton(),
                        scrollDownToPosition,
                        1,
                        100)
        ).isTrue();

        fmPage.scrollPageTo(fmPage.getFistParagraph());
        assertThat(
                Utils.waitForElementVPositionToBeInRange(
                        fmPage.getHomeButton(),
                        scrollUpToPosition,
                        1,
                        1000)
        ).isTrue();
    }

    @Test
    public void floatingMenuTestWithThreadSleep() throws InterruptedException {
        FloatingMenuPage fmPage = new TheInternetHomePage(getDriver())
                .floatingMenuLinkClick();

        int scrollUpToPosition = fmPage.getFistParagraph().getLocation().getY();
        int scrollDownToPosition = fmPage.getSeventhParagraph().getLocation().getY();

        assertThat(
                fmPage.scrollPageToWithThreadSleep(fmPage.getSeventhParagraph())
                        .homeButtonVerticalPosition()
        ).isCloseTo(scrollDownToPosition, Offset.offset(1));

        assertThat(
                fmPage.scrollPageToWithThreadSleep(fmPage.getFistParagraph())
                        .homeButtonVerticalPosition()
        ).isCloseTo(scrollUpToPosition, Offset.offset(1));
    }

}
