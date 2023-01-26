package com.dotdash.takehome.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String takeScreenshot(WebDriver driver, String filename) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = formatterLocalDate.format(localDate);

        try {
            String folderPath = "output" + File.separator + "screenshots" + File.separator;
            FileUtils.copyFile(screenshot, new File(folderPath + filename +"_"+ timestamp + ".png"));
        } catch (IOException exception) {
            return "IOException: could not save file: " + filename + "\n" + exception.getMessage();
        }

        return "";
    }

}
