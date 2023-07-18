package com.dotdash.takehome.pages;

import com.dotdash.takehome.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WysiwygEditorPage extends BasePage {

    @FindBy(xpath = "//html[1]")
    private WebElement mainPage;

    private final String popupCloseBtnXpath = "//div[@aria-label='Close']";
    @FindBy(xpath = popupCloseBtnXpath)
    private WebElement popupCloseButton;

    @FindBy(xpath = "//div[@class='tox-notifications-container']")
    private WebElement notificationPopover;

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
    private WebElement wysiwygIFrame;

    @FindBy(id = "tinymce")
    private WebElement textEditor;

    @FindBy(xpath = "//button[@title='Formats']")
    private WebElement formatsDropDown;

    @FindBy(xpath = "//div[@title='Headings']")
    private WebElement headingsSubmenu;

    @FindBy(xpath = "//div[@title='Heading 2']")
    private WebElement heading2Option;

    public WysiwygEditorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getTextEditor() {
        waitForTextEditorToLoad();
        return textEditor;
    }

    public WysiwygEditorPage prepTextEditor() {
        waitForTextEditorToLoad();
        clearTextEditor();
        return this;
    }

    private WysiwygEditorPage waitForTextEditorToLoad() {
        waitForElementToBeClickable(wysiwygIFrame);
        closeThisDomainIsNotRegisteredPopover();
        switchToIFrame();
        waitForElementToBeClickable(textEditor);
        return this;
    }

    private WysiwygEditorPage closeThisDomainIsNotRegisteredPopover() {
        if (driver.findElements(By.xpath(popupCloseBtnXpath)).size() > 0) {
            popupCloseButton.click();
            //waitForElementToDisappear(notificationPopover);       // long wait with implicitWait
        }
        return this;
    }

    private WysiwygEditorPage clearTextEditor() {
        textEditor.clear();
        return this;
    }

    public WysiwygEditorPage enterText(String text) {
        textEditor.sendKeys(text);
        return this;
    }

    public WysiwygEditorPage switchToIFrame() {
        driver.switchTo().frame(wysiwygIFrame);
        return this;
    }

    public WysiwygEditorPage switchToMain() {
        driver.switchTo().parentFrame();
        return this;
    }

    public WysiwygEditorPage boldButtonClick() {
        switchToMain();
        boldButton.click();
        return this;
    }

    public WysiwygEditorPage italicButtonClick() {
        switchToMain();
        italicButton.click();
        return this;
    }

    public WysiwygEditorPage formatsDropDownClick() {
        formatsDropDown.click();
        return this;
    }

    public WysiwygEditorPage headingsSubmenuClick() {
        headingsSubmenu.click();
        return this;
    }

    public WysiwygEditorPage heading2OptionClick() {
        heading2Option.click();
        return this;
    }

    public WysiwygEditorPage alignLeftButtonClick() {
        alignLeftButton.click();
        return this;
    }

    public WysiwygEditorPage alignRightButtonClick() {
        alignRightButton.click();
        return this;
    }

    public WysiwygEditorPage alignCenterButtonClick() {
        alignCenterButton.click();
        return this;
    }

    public WysiwygEditorPage selectPartOfText(String text) {

        // 1. get text length
        // 2. move to element with string
        // 3. mouse click to get the text cursor @ end of string
        // 4. get starting position of the matched text
        // 5. move to it from end of string
        // 6. hold Shift and move Right text.length number of times

        Actions actions = new Actions(driver);

        String elementXpath = String.format("//p[contains(., '%s')]", text);
        WebElement element = driver.findElement(By.xpath(elementXpath));
        waitForElementToAppear(element);

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

        // Check if text was indeed selected
        String selectedText = getSelectedTextWithJS(driver);
        if (selectedText != null && !selectedText.isEmpty() && text.contains(selectedText.trim()))
            return this;
        else {
            switchToMain();
            closeThisDomainIsNotRegisteredPopover();
            switchToIFrame();
            return selectPartOfText(text);
        }
    }

    private String getSelectedTextWithJS(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return window.getSelection().toString();"
        );
    }

}

