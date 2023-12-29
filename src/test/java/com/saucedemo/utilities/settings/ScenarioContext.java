package com.saucedemo.utilities.settings;

import com.saucedemo.utilities.driver.DriverManager;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
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

    public Capabilities printBrowserInfo() {
        if (getDriver() != null) {
            return  ((ChromeDriver) getDriver()).getCapabilities();
        }

        return null;
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

    public void testResult(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(getDriver().getTitle(), "text/plain", "Screen Name");
            scenario.attach(scenario.getStatus().toString(), "text/plain", "Status");
            takeScreenshot("Error screen");
        } else {
            scenario.attach(scenario.getStatus().toString(), "text/plain", "Status");
            takeScreenshot("Success Screen");
        }
    }

    public void takeScreenshot(String text) {
        try {
            Allure.addAttachment(text, new ByteArrayInputStream(((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
