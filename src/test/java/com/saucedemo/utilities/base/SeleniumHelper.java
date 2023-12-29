package com.saucedemo.utilities.base;

import com.saucedemo.utilities.settings.ScenarioContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumHelper {
    private ScenarioContext context;
    private WebDriver driver;

    public SeleniumHelper() {
        context = ScenarioContext.getInstance();
        driver = context.getDriver();
    }

    public void goTo(String url) {
        log("Navigating to: " + url);
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void fillField(By locator, String value) {
        log("Filling field: " + locator + " with value: " + value);
        findElement(locator).sendKeys(value);
    }

    public void clickElement(By locator) {
        log("Clicking element: " + locator);
        findElement(locator).click();
    }

    public String grabTextFrom(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            log("Waiting for element to be visible: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            log("Element is visible. Text grabbed from " + locator + ": " + text);
            return text;
        } catch (TimeoutException e) {
            log("Timeout waiting for element to be visible: " + locator);
            return null;
        }
    }

    public void iSee(String text) {
        log("Checking if text is present: " + text);
        if (!driver.getPageSource().contains(text)) {
            throw new AssertionError("Text not found: " + text);
        }
    }

    public boolean isElementDisplayed(By by) {
        try {
            log("Checking if element is displayed: " + by);
            boolean isDisplayed = findElement(by).isDisplayed();
            log("Element is displayed: " + by);
            return isDisplayed;
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            log("Element is not displayed: " + by);
            return false;
        }
    }

    private void log(String message) {
        System.out.println("[INFO] " + message);
    }
}
