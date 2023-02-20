package com.dotdash.takehome.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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

    public static String readFileContents(String fileName) {

        URI filePath;
        try {
            filePath = Utils.class.getResource(fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Create a StringBuilder object to store the contents of the file
        StringBuilder contents = new StringBuilder();

        try {
            // Create a File object
            File file = new File(filePath);

            // Create a FileReader object
            FileReader fileReader = new FileReader(file);

            // Wrap the FileReader object in a BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read each line of the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contents.append(line).append("\n");
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return contents.toString();
    }

}
