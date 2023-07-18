package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.DynamicContentPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dynamic Content page: /dynamic_content")
public class DynamicContentTest extends BaseTest {

    static final int POSSIBLE_RETRIES_OF_IMAGE_ERROR = 5;
    static int attemptsCounter = POSSIBLE_RETRIES_OF_IMAGE_ERROR;

    @Test
    @DisplayName("Verify any text change on page refresh")
    void verifyAnyTextChangesOnPageRefresh() {
        DynamicContentPage dcPage;
        dcPage = new TheInternetHomePage(getDriver()).dynamicContentClick();

        String contentText = dcPage.getContent().getText();

        getDriver().navigate().refresh();

        assertThat(dcPage.getContent().getText()).isNotEqualTo(contentText);
    }

    @Test
    @DisplayName("Verify all images and text change on refresh")
    void verifyAllImagesAndTextChangeOnRefresh() {
        DynamicContentPage dcPage;
        dcPage = new TheInternetHomePage(getDriver()).dynamicContentClick();

        List<Map<String,String>> allBeforeRefresh = dcPage.getAllDynamicContent();

        getDriver().navigate().refresh();

        List<Map<String,String>> allAfterRefresh = dcPage.getAllDynamicContent();

        // Assert all texts are different
        assertThat(getContentOfType("text", allAfterRefresh))
                .doesNotContainSequence(getContentOfType("text", allBeforeRefresh));

        // Avatars do not always get the new image, so we can check on multiple refreshes
        assertImageUrlsAreChanging(allBeforeRefresh, dcPage);

    }

    private void assertImageUrlsAreChanging(List<Map<String,String>> allBeforeRefresh,
                                            DynamicContentPage dcPage) {
        boolean success = false;
        try {
            success = checkImageUrl(allBeforeRefresh, dcPage.getAllDynamicContent());
        } catch (AssertionError e) {
            while (!success && attemptsCounter > 0) {
                attemptsCounter--;
                try {
                    getDriver().navigate().refresh();
                    success = checkImageUrl(allBeforeRefresh,  dcPage.getAllDynamicContent());
                } catch (AssertionError error) {
                    System.out.println("Failed to obtain new images. Attempts left: "+ attemptsCounter);
                }
            }
            if (!success) {
                throw new AssertionError(String.format("Ran out of %d retry attempts for dynamic images",
                        POSSIBLE_RETRIES_OF_IMAGE_ERROR));
            }
        }
    }

    private boolean checkImageUrl(List<Map<String,String>> allBeforeRefresh,
                               List<Map<String,String>> allAfterRefresh) {
        assertThat(getContentOfType("imgSrc", allAfterRefresh))
                .doesNotContainSequence(getContentOfType("imgSrc", allBeforeRefresh));

        return true;
    }

    private List<String> getContentOfType(String type, List<Map<String,String>> from) {
        return from.stream()
                .map(e -> e.get(type))
                .collect(Collectors.toList());
    }

}
