package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage extends BasePage {

    @CacheLookup
    @FindBy(tagName = "h3")
    private WebElement pageTitle;

    @CacheLookup
    @FindBy(id = "column-a")
    private WebElement columnA;

    @CacheLookup
    @FindBy(id = "column-b")
    private WebElement columnB;

    @FindBy(css = "#column-a > header")
    private WebElement columnAHeader;

    @FindBy(css = "#column-b > header")
    private WebElement columnBHeader;

    public DragAndDropPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public WebElement getColumnA() {
        return columnA;
    }

    public WebElement getColumnB() {
        return columnB;
    }

    public WebElement getColumnAHeader() {
        return columnAHeader;
    }

    public WebElement getColumnBHeader() {
        return columnBHeader;
    }

    public String getColumnAHeaderText() {
        return columnAHeader.getText();
    }

    public String getColumnBHeaderText() {
        return columnBHeader.getText();
    }
}