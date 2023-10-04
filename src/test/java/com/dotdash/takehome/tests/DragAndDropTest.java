package com.dotdash.takehome.tests;

import com.codeborne.selenide.Condition;
import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.DragAndDropPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Drag and Drop page: /drag_and_drop")
public class DragAndDropTest extends BaseTest {

    @Test
    @DisplayName("Verify content switches on drag and drop both ways")
    void verifyContentSwitchesOnDropBothWays() {
        DragAndDropPage dndPage = new TheInternetHomePage(getDriver()).dragAndDropClick();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = Utils.readFileContents("/dragAndDrop.js");

        // First Drag & Drop
        step("Verify Header A is in column-a; Header B is in column-b", () -> {
            assertThat(dndPage.getColumnAHeaderText()).isEqualTo("A");
            assertThat(dndPage.getColumnBHeaderText()).isEqualTo("B");
        });
        step("Drag column A over to column B", () -> {
            js. executeScript(script, dndPage.getColumnA(), dndPage.getColumnB());
        });
        step("Verify Header B is in column-a; Header A is in column-b", () -> {
            assertThat(dndPage.getColumnAHeaderText()).isEqualTo("B");
            assertThat(dndPage.getColumnBHeaderText()).isEqualTo("A");
        });

        // Second Drag & Drop (Reverse)
        step("Reverse: Drag column B back to column A", () -> {
            js. executeScript(script, dndPage.getColumnB(), dndPage.getColumnA());
        });
        step("Verify Header A is in column-a; Header B is in column-b", () -> {
            assertThat(dndPage.getColumnAHeaderText()).isEqualTo("A");
            assertThat(dndPage.getColumnBHeaderText()).isEqualTo("B");
        });
    }

    @Test
    @DisplayName("Verify drag and drop with Selenide")
    void verifyDragAndDropWithSelenide() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        // First Drag & Drop
        $("#column-a").shouldHave(Condition.exactText("A"));
        $("#column-b").shouldHave(Condition.exactText("B"));
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-a").shouldHave(Condition.exactText("B"));
        $("#column-b").shouldHave(Condition.exactText("A"));

        // Second Drag & Drop (Reverse)
        $("#column-b").dragAndDropTo("#column-a");
        $("#column-a").shouldHave(Condition.exactText("A"));
        $("#column-b").shouldHave(Condition.exactText("B"));
    }

}
