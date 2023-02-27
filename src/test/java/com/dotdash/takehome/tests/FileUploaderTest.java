package com.dotdash.takehome.tests;

import com.dotdash.takehome.pages.TheInternetHomePage;
import com.dotdash.takehome.utils.Utils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploaderTest extends BaseTest {

    @Test
    public void fileUploaderWithFieldAndButton() throws IOException {
        File tempFile = Utils.createTempFile("tempfile");

        assertThat(new TheInternetHomePage(getDriver())
                .fileUploadLinkClick()
                .uploadInputFile(tempFile.getAbsolutePath())
                .uploadFile()
                .getText()
                .trim()
        ).isEqualTo(tempFile.getName());
    }

    @Test
    public void fileUploaderWithDragAndDropDiv() throws IOException {
        File tempFile = Utils.createTempFile("tempfile");

        assertThat(new TheInternetHomePage(getDriver())
                .fileUploadLinkClick()
                .uploadFileWithDragAndDrop(tempFile.getAbsolutePath())
                .getDragAndDropUploadConfirmation()
        ).isEqualTo(tempFile.getName());
    }

}
