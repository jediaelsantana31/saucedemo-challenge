package com.saucedemo.utilities.driver;

import com.saucedemo.utilities.settings.ProjectSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private WebDriver driver;

    public void initializeDriver() {
        System.setProperty(ProjectSettings.CHROME_DRIVER, ProjectSettings.CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
