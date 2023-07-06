package com.dotdash.takehome.utils;

import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.openqa.selenium.*;

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

    public static File createTempFile(String tempfile) throws IOException {
        File file = File.createTempFile(tempfile, null);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Temp file input");
        writer.close();
        return file;
    }


    public static void fileDownloader(String link, File destination) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpUriRequestBase request = new HttpGet(link);
            client.execute(request, classicHttpResponse -> {
                FileUtils.copyInputStreamToFile(classicHttpResponse.getEntity().getContent(), destination);
                return null;
            });
        }
    }


    public static boolean waitForElementVPositionToBeInRange(
            WebElement element,
            int expected,
            int offset,
            int timeout) {
        int counter = 0;
        int actual;
        do {
            actual = element.getLocation().getY();
            if (Math.abs(actual - expected) <= offset) {
                return true;
            }
            counter++;
        } while (counter <= timeout);
        return false;
    }

    public static void mouseMoveToConsoleJS(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript("""
                window.addEventListener('mousemove', mouseCoordinates);
                function mouseCoordinates(event){
                    console.log("pageX: ", event.pageX,
                        "pageY: ", event.pageY,
                        "clientX: ", event.clientX,
                        "clientY:", event.clientY
                    );
                }
                """
        );
    }

}
