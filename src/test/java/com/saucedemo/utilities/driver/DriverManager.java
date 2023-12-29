package com.saucedemo.utilities.driver;

import com.saucedemo.utilities.settings.ProjectSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private WebDriver driver;

    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();

        // Verifica se a variável de sistema está presente e configurada como "true"
        String headlessMode = System.getProperty("headlessMode");
        boolean isHeadless = "true".equalsIgnoreCase(headlessMode);

        // Configuração para headless se a variável estiver presente e configurada como "true"
        if (isHeadless) {
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
        } else {
            System.setProperty(ProjectSettings.CHROME_DRIVER, ProjectSettings.CHROMEDRIVER_PATH);
        }

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
