package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.IframePage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.AfterTestCallback;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AfterTestCallback.class)
public class IframeTest extends BaseTest {

    @Test
    public void textEditorTestBoldButton() {
        String text = "\nSome text with bold word in it\n";
        String partText = "bold";

        IframePage iframePage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .boldButtonClick();

        String verifyXpathTag = "strong";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        int actual = iframePage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void textEditorTestItalicButton() {
        String text = "\nSome text with italic word in it\n";
        String partText = "italic";

        IframePage iframePage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .italicButtonClick();

        String verifyXpathTag = "em";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);

        int actual = iframePage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).withFailMessage("No <em> elements found in text").isEqualTo(1);
    }

    @Test
    public void textEditorTestBoldThenItalicButton() {
        String text = "\nSome text with bold word in it\n";
        String partText = "bold";

        IframePage iframePage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .boldButtonClick();

        String verifyXpathTag = "strong";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        int actual = iframePage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);

        iframePage.switchToMain().boldButtonClick().italicButtonClick();

        verifyXpathTag = "em";
        verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        actual = iframePage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void textEditorTestBoldAndItalicButtons() {
        String text = "\nSome text with bold and italic words in it\n";
        String partText = "bold and italic";

        IframePage iframePage = new TheInternetHomePage(getDriver())
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

        int actual = iframePage.getTextEditor()
                .findElement(By.xpath(verifyXpath1))
                .findElements(By.xpath(verifyXpath2))
                .size();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void textEditorTestHeaderText() {
        String text = "\nThis is a header text\n";
        String partText = "header";

        IframePage iframePage = new TheInternetHomePage(getDriver())
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
        int actual = iframePage.getTextEditor().findElements(By.xpath(verifyXpath)).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void textEditorTestAlignment() {
        String text = "\nThis text is here for alignment test\n";
        String partText = "alignment";

        IframePage iframePage = new TheInternetHomePage(getDriver())
                .wysiwygLinkClick()
                .prepTextEditor()
                .enterText(text)
                .selectPartOfText(partText)
                .switchToMain()
                .alignRightButtonClick();

        String verifyXpathTag = "p";
        String verifyXpath = String.format("//%s[contains(.,'%s')]", verifyXpathTag, partText);
        WebElement matchedResult = iframePage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style")).contains("text-align: right");

        iframePage.switchToMain().alignCenterButtonClick();
        matchedResult = iframePage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style")).contains("text-align: center");

        iframePage.switchToMain().alignLeftButtonClick();
        matchedResult = iframePage.getTextEditor().findElement(By.xpath(verifyXpath));
        assertThat(matchedResult.getDomAttribute("style"))
                .satisfiesAnyOf(
                        result -> assertThat(result).isNull(),
                        result -> assertThat(result).isEmpty(),
                        result -> assertThat(result).contains("text-align: left")
                );

    }
}
