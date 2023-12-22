package com.saucedemo.utilities.settings;

import com.saucedemo.utilities.driver.DriverManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance;
    private final DriverManager driverManager;
    private final Map<String, Object> scenarioData;

    private ScenarioContext() {
        driverManager = new DriverManager();
        scenarioData = new HashMap<>();
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

    public void setContextData(String key, Object value) {
        scenarioData.put(key, value);
    }

    public Object getContextData(String key) {
        return scenarioData.get(key);
    }

    public void clearContextData() {
        scenarioData.clear();
    }

}
