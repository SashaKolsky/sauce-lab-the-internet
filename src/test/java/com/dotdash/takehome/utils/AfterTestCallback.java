package com.dotdash.takehome.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.dotdash.takehome.utils.Utils.takeScreenshot;

public class AfterTestCallback implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            String filename = String.join("-", context.getRequiredTestClass().getSimpleName(),
                    context.getRequiredTestMethod().getName());
            takeScreenshot(DriverManager.getDriver(), filename);
        }
    }
}
