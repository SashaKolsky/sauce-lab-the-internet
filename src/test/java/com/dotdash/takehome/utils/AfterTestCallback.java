package com.dotdash.takehome.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.dotdash.takehome.utils.Utils.takeScreenshot;

public class AfterTestCallback implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            String filename = String.join("-", context.getRequiredTestClass().getSimpleName(),
                    context.getRequiredTestMethod().getName());
            takeScreenshot(DriverManager.getDriver(), filename);
        }
        allureScreenshotAsStep();
    }

    @Step
    @Attachment(value = "Final Screenshot", type = "image/png")
    @DisplayName("Take screenshot at the end of test")
    private byte[] allureScreenshotAsStep() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
