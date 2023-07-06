package com.dotdash.takehome.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IframePage extends BasePage {

    public static final String PAGE_URL = "http://localhost:7080/iframe";

    @FindBy(xpath = "//html[1]")
    private WebElement mainPage;

    private final String popupCloseBtnXpath = "//div[@aria-label='Close']";
    @FindBy(xpath = popupCloseBtnXpath)
    private WebElement popupCloseButton;

    @FindBy(xpath = "//button[@title='Bold']")
    private WebElement boldButton;

    @FindBy(xpath = "//button[@title='Italic']")
    private WebElement italicButton;

    @FindBy(xpath = "//button[@title='Align left']")
    private WebElement alignLeftButton;

    @FindBy(xpath = "//button[@title='Align right']")
    private WebElement alignRightButton;

    @FindBy(xpath = "//button[@title='Align center']")
    private WebElement alignCenterButton;

    @FindBy(xpath = "//button[@title='Increase indent']")
    private WebElement IncreaseIndentButton;

    @FindBy(xpath = "//button[@title='Decrease indent']")
    private WebElement DecreaseIndentButton;

    @FindBy(id = "mce_0_ifr")
    private WebElement iFrame;

    @FindBy(id = "tinymce")
    private WebElement textEditor;

    @FindBy(xpath = "//button[@title='Formats']")
    private WebElement formatsDropDown;

    @FindBy(xpath = "//div[@title='Headings']")
    private WebElement headingsSubmenu;

    @FindBy(xpath = "//div[@title='Heading 2']")
    private WebElement heading2Option;

    public IframePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getTextEditor() {
        waitForTextEditorToLoad();
        return textEditor;
    }

    public IframePage prepTextEditor() {
        waitForTextEditorToLoad();
        clearTextEditor();
        return this;
    }

    private IframePage waitForTextEditorToLoad() {
        waitForElementToAppear(iFrame);
        closePopup();
        driver.switchTo().frame(iFrame);
        waitForElementToAppear(textEditor);
        return this;
    }

    private IframePage closePopup() {
        if (driver.findElements(By.xpath(popupCloseBtnXpath)).size() > 0) {
            popupCloseButton.click();
        }
        return this;
    }

    private IframePage clearTextEditor() {
        textEditor.clear();
        return this;
    }

    public IframePage enterText(String text) {
        waitForElementToAppear(textEditor);
        textEditor.sendKeys(text);
        return this;
    }

    public IframePage switchToIFrame() {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public IframePage switchToMain() {
        driver.switchTo().parentFrame();
        return this;
    }

    public IframePage boldButtonClick() {
        switchToMain();
        boldButton.click();
        return this;
    }

    public IframePage italicButtonClick() {
        switchToMain();
        italicButton.click();
        return this;
    }

    public IframePage formatsDropDownClick() {
        formatsDropDown.click();
        return this;
    }

    public IframePage headingsSubmenuClick() {
        headingsSubmenu.click();
        return this;
    }

    public IframePage heading2OptionClick() {
        heading2Option.click();
        return this;
    }

    public IframePage alignLeftButtonClick() {
        alignLeftButton.click();
        return this;
    }

    public IframePage alignRightButtonClick() {
        alignRightButton.click();
        return this;
    }

    public IframePage alignCenterButtonClick() {
        alignCenterButton.click();
        return this;
    }

    public IframePage selectPartOfText(String text) {

        // 1. get text length
        // 2. move to element with string
        // 3. mouse click to get the text cursor @ end of string
        // 4. get starting position of the matched text
        // 5. move to it from end of string
        // 6. hold Shift and move Right text.length number of times

        Actions actions = new Actions(driver);

        String elementXpath = String.format("//p[contains(., '%s')]", text);
        WebElement element = driver.findElement(By.xpath(elementXpath));
        String elementText = element.getText();
        int iPosOfTextInValue = elementText.indexOf(text);
        int stepsToTextFromEndOfString = elementText.length() - (iPosOfTextInValue + 1);

        actions.moveToElement(element).click();
        for (int i = 0; i <= stepsToTextFromEndOfString; i++) {
            actions.sendKeys(Keys.ARROW_LEFT);
        }
        actions.keyDown(Keys.SHIFT);
        for (int i = 0; i < text.length(); i++) {
            actions.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.keyUp(Keys.SHIFT);

        actions.build().perform();
        return this;
    }

}

