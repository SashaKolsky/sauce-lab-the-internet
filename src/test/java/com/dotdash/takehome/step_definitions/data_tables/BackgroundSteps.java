package com.dotdash.takehome.step_definitions.data_tables;

import com.dotdash.takehome.base.BasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.dotdash.takehome.utils.DriverManager.getDriver;

public class BackgroundSteps {

    @Given("user is on the main page")
    public void userIsOnTheMainPage() {
        getDriver().navigate().to(BasePage.BASE_URL);
    }

    @Given("^user clicks on \"(.+)\" link$")
    public void userClicksOnLink(String pageLink) {
        getDriver()
            .findElement(By.partialLinkText(pageLink))
            .click();
    }

    @Then("user in on the {string} page with {string} in the path")
    public void userInOnThePageWithInThePath(String expectedPageHeader, String expectedPartUrl) {
        String actualPageHeader = getDriver()
                .findElement(By.tagName("h3"))
                .getText();

        Assertions.assertEquals(expectedPageHeader, actualPageHeader);
        Assertions.assertTrue(getDriver().getCurrentUrl().contains(expectedPartUrl));
    }
}
