package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.pages.WysiwygEditorPage;
import com.dotdash.takehome.utils.AfterTestCallback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AfterTestCallback.class)
@DisplayName("WYSIWYG editor: /tinymce")
public class WysiwygEditorTest extends BaseTest {

    @Test
    @DisplayName("Verify Word is bold after bold button click")
    void verifyWordIsBoldAfterBoldButtonClick() {
        String text = "\nSome text with bold word in it\n";
        String partText = "bold";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .boldButtonClick();

        String verifyXpathTag = "strong";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        int actual = wysiwygEditorPage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Verify text is italic after italic button click")
    void verifyTextIsItalicAfterItalicButtonClick() {
        String text = "\nSome text with italic word in it\n";
        String partText = "italic";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .italicButtonClick();

        String verifyXpathTag = "em";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);

        int actual = wysiwygEditorPage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).withFailMessage("No <em> elements found in text").isEqualTo(1);
    }

    @Test
    @DisplayName("Verify test is bold then italic after bold then italic button click")
    void verifyTestIsBoldThenItalicAfterBoldThenItalicButtonClick() {
        String text = "\nSome text with bold word in it\n";
        String partText = "bold";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .boldButtonClick();

        String verifyXpathTag = "strong";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        int actual = wysiwygEditorPage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);

        wysiwygEditorPage.switchToMain().boldButtonClick().italicButtonClick();

        verifyXpathTag = "em";
        verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        actual = wysiwygEditorPage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Verify text is bold and italic after bold and italic button click")
    void verifyTextIsBoldAndItalicAfterBoldAndItalicButtonClick() {
        String text = "\nSome text with bold and italic words in it\n";
        String partText = "bold and italic";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .boldButtonClick()
                .italicButtonClick();

        String verifyXpathTag1 = "em";
        String verifyXpath1 = String.format("//%s[contains(.,'%s')]", verifyXpathTag1, partText);

        String verifyXpathTag2 = "strong";
        String verifyXpath2 = String.format("//%s[contains(.,'%s')]", verifyXpathTag2, partText);

        int actual = wysiwygEditorPage.getTextEditor()
                .findElement(By.xpath(verifyXpath1))
                .findElements(By.xpath(verifyXpath2))
                .size();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Verify text is of size header 2 after header 2 selected from dropdown")
    void verifyTextIsOfSizeHeader2AfterHeader2SelectedFromDropdown() {
        String text = "\nThis is a header text\n";
        String partText = "header";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .switchToMain()
                .formatsDropDownClick()
                .headingsSubmenuClick()
                .heading2OptionClick();

        String verifyXpathTag = "h2";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        int actual = wysiwygEditorPage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Verify text alignment works as specified with alignment buttons clicked")
    void verifyTextAlignmentWorksAsSpecifiedWithAlignmentButtonsClicked() {
        String text = "\nThis text is here for alignment test\n";
        String partText = "alignment";

        WysiwygEditorPage wysiwygEditorPage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .switchToMain()
                .alignRightButtonClick();

        String verifyXpathTag = "p";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        WebElement matchedResult = wysiwygEditorPage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style")).contains("text-align: right");

        wysiwygEditorPage.switchToMain().alignCenterButtonClick();
        matchedResult = wysiwygEditorPage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style")).contains("text-align: center");

        wysiwygEditorPage.switchToMain().alignLeftButtonClick();
        matchedResult = wysiwygEditorPage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style"))
                .satisfiesAnyOf(
                        result -> assertThat(result).isNull(),
                        result -> assertThat(result).isEmpty(),
                        result -> assertThat(result).contains("text-align: left")
                );

    }
}
