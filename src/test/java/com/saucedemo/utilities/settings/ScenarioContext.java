package com.saucedemo.utilities.settings;

import com.saucedemo.utilities.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class ScenarioContext {
    private static ScenarioContext instance;
    private final DriverManager driverManager;

    private ScenarioContext() {
        driverManager = new DriverManager();
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driverManager.getDriver() == null) {
            driverManager.initializeDriver();
        }
        return driverManager.getDriver();
    }

    public void quitDriver() {
        driverManager.quitDriver();
    }

    public void reset() {
        quitDriver();
        instance = null;
    }

}
