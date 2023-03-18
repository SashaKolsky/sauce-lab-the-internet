package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.HoversPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.ReplaceCamelCase;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class HoversTest extends BaseTest{

    @CsvSource({"1, name: user1","2, name: user2","3, name: user3"})
    @ParameterizedTest(name = "When floating cursor over user {0}, hidden info for User {0} pops up")
    public void floatingMenuShowsUpIfHoveredOverUserProfilePicture(int userId, String expected) {
        HoversPage hoversPage = new TheInternetHomePage(getDriver())
                .hoversLinkClick();

        // None shown
        assertThat(hoversPage.isUser1HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser2HoverDataNotPresent()).isTrue();
        assertThat(hoversPage.isUser3HoverDataNotPresent()).isTrue();

        try {
            Method method = hoversPage.getClass().getMethod("getUser"+ userId +"HoverData");
            assertThat(method.invoke(hoversPage)).isEqualTo(expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
