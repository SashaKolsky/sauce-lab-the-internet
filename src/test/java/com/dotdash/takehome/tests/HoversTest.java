package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.HoversPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoversTest extends BaseTest{

    public static final String user1ExpectedValue = "name: user1";
    public static final String user2ExpectedValue = "name: user2";
    public static final String user3ExpectedValue = "name: user3";

    @Test
    public void floatingMenuTest() {
        HoversPage hoversPage = new TheInternetHomePage(getDriver())
                .hoversLinkClick();

        // None shown
        assertThat(hoversPage.isUser1HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser2HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser3HoverDataNotPresent()).isTrue();

        // 1st shown after hover
        assertThat(hoversPage.getUser1HoverData()).isEqualTo(user1ExpectedValue);
        assertThat(hoversPage.isUser2HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser3HoverDataNotPresent()).isTrue();

        // 2nd shown after hover
        assertThat(hoversPage.getUser2HoverData()).isEqualTo(user2ExpectedValue);
        assertThat(hoversPage.isUser1HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser3HoverDataNotPresent()).isTrue();

        // 3rd shown after hover
        assertThat(hoversPage.getUser3HoverData()).isEqualTo(user3ExpectedValue);
        assertThat(hoversPage.isUser1HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser2HoverDataNotPresent()).isTrue();
    }

}
