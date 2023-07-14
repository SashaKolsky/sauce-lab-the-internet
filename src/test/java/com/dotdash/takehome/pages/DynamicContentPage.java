package com.dotdash.takehome.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicContentPage extends BasePage {
    private WebElement content;

    @FindBy(css = "#content > div.row:nth-of-type(1) img")
    private WebElement firstImage;

    @FindBy(css = "#content > div.row:nth-of-type(1) div:nth-of-type(2)")
    private WebElement firstText;

    @FindBy(css = "#content > div.row:nth-of-type(2) img")
    private WebElement secondImage;

    @FindBy(css = "#content > div.row:nth-of-type(2) div:nth-of-type(2)")
    private WebElement secondText;

    @FindBy(css = "#content > div.row:nth-of-type(2) img")
    private WebElement thirdImage;

    @FindBy(css = "#content > div.row:nth-of-type(2) div:nth-of-type(2)")
    private WebElement thirdText;

    public DynamicContentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getContent() {
        return content;
    }

    public List<Map<String, String>> getAllDynamicContent() {
        List<Map<String, String>> allContent = new ArrayList<>() {{
            add(new HashMap<>() {{
                put("imgSrc", firstImage.getAttribute("src"));
                put("text", firstText.getText());
            }});
            add(new HashMap<>() {{
                put("imgSrc", secondImage.getAttribute("src"));
                put("text", secondText.getText());
            }});
            add(new HashMap<>() {{
                put("imgSrc", thirdImage.getAttribute("src"));
                put("text", thirdText.getText());
            }});
        }};
        return allContent;
    }
}

