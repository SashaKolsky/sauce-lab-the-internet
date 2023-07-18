package com.dotdash.takehome.tests;

import com.dotdash.takehome.base.BaseTest;
import com.dotdash.takehome.pages.DragAndDropPage;
import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static org.assertj.core.api.Assertions.assertThat;

public class DragAndDropTest extends BaseTest {

    @Test
    @DisplayName("verifyContentSwitchesOnDropBothWays")
    void verifyContentSwitchesOnDropBothWays() {
        DragAndDropPage dndPage;
        dndPage = new TheInternetHomePage(getDriver()).dragAndDropClick();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = Utils.readFileContents("/dragAndDrop.js");

        // First Drag & Drop
        js. executeScript(script, dndPage.getColumnA(), dndPage.getColumnB());
        assertThat(dndPage.getColumnAHeaderText()).isEqualTo("B");
        assertThat(dndPage.getColumnBHeaderText()).isEqualTo("A");

        // Second Drag & Drop (Reverse)
        js. executeScript(script, dndPage.getColumnB(), dndPage.getColumnA());
        assertThat(dndPage.getColumnAHeaderText()).isEqualTo("A");
        assertThat(dndPage.getColumnBHeaderText()).isEqualTo("B");

    }

}
