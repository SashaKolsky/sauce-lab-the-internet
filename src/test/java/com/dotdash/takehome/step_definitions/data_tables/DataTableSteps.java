package com.dotdash.takehome.step_definitions.data_tables;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.dotdash.takehome.utils.DriverManager.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class DataTableSteps {

    List<TableRecord> dataInDefaultOrder;
    List<TableRecord> actualValue;
    List<TableRecord> expectedValue;

    WebElement example1Table = getDriver().findElement(By.xpath("//table[@id='table1']"));

    @When("^user clicks on \"?([\\w ]+)\"? ?(?:once|again)$")
    public void userClicksOnColumnHeaderNameOnes(String tableHeader) {
        tableHeader = tableHeader.trim();
        // Note: you have to start sub xpath selector with ".", otherwise it will be a global search
        String headerElementXpath = String.format(".//*[contains(text(), '%s')]", tableHeader);
        WebElement headerElement = example1Table.findElement(By.xpath(headerElementXpath));
        headerElement.click();
    }

    @Then("^data is sorted by (.+) in (ascending|descending) order")
    public void tableDataIsSortedByColumnHeader(String tableHeader, String order) {
        actualValue = getRowsAsLists();
        expectedValue = new LinkedList<>(actualValue);

        sortListData(expectedValue, tableHeader, order.equalsIgnoreCase("descending"));
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Then("^data is[ \\w]* sorted by default order")
    public void dataIsSortedByDefaultOrder() {
        actualValue = getRowsAsLists();
        assertThat(actualValue).isEqualTo(dataInDefaultOrder);
    }

    private List<String> getColumnNames() {
        return example1Table
                .findElements(By.xpath("./thead//th"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    private List<TableRecord> getRowsAsLists() {
        List<WebElement> rows = example1Table
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
        List<TableRecord> actualData = new ArrayList<>();

        for (WebElement row : rows) {
            List<String> cellsData = row
                    .findElements(By.tagName("td"))
                    .stream().map(WebElement::getText).toList();
            actualData.add(new TableRecord(cellsData));
        }
        return actualData;
    }

    private void sortListData(List<TableRecord> list, String tableHeader, boolean isReverse) {
        tableHeader = tableHeader.toLowerCase().replaceAll("\"", "");
        Comparator<TableRecord> comparator;
        switch (tableHeader) {
            case "first name" -> comparator = Comparator.comparing(TableRecord::firstName);
            case "last name" -> comparator = Comparator.comparing(TableRecord::lastName);
            case "email" -> comparator = Comparator.comparing(TableRecord::email);
            case "due" -> comparator = Comparator.comparing(TableRecord::due);
            case "web site" -> comparator = Comparator.comparing(TableRecord::webSite);
            case "action" -> comparator = Comparator.comparing(TableRecord::action);
            default -> throw new RuntimeException("provided header doesn't match headers available in the table: " + getColumnNames());
        }

        if (!isReverse)
            list.sort(comparator);
        else
            list.sort(comparator.reversed());
    }

    private record TableRecord(String lastName,
                               String firstName,
                               String email,
                               double due,
                               String webSite,
                               String action) {

        public TableRecord(List<String> cellsData) {
            this(cellsData.get(0),
                    cellsData.get(1),
                    cellsData.get(2),
                    Double.parseDouble(cellsData.get(3).substring(1)),
                    cellsData.get(4),
                    cellsData.get(5)
            );
        }
    }
}