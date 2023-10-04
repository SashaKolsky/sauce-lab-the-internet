package com.dotdash.takehome.step_definitions;

import com.dotdash.takehome.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class Hooks {

    @Before
    public void setup() {
        DriverManager.setDriver(DriverManager.Type.CHROME);
        DriverManager.getDriver()
                .manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(2));
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
