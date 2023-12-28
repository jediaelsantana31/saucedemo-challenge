package com.saucedemo.utilities.base;

import com.saucedemo.utilities.settings.ScenarioContext;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumHelper {
    protected ScenarioContext context;
    private WebDriver driver;

    public SeleniumHelper() {
        context = ScenarioContext.getInstance();
        driver = context.getDriver();
    }

    public void goTo(String url) {
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
        findElement(locator).sendKeys(value);
    }

    public void clickElement(By locator) {
        findElement(locator).click();
    }

    public String grabTextFrom(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Timeout waiting for element to be visible: " + locator);
        }
    }

    public void iSee(String text) {
        if (!driver.getPageSource().contains(text)) {
            throw new AssertionError("Text not found: " + text);
        }
    }

    public void iSeeTitle(String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(driver -> driver.getTitle().contains(expectedText));
        } catch (TimeoutException e) {
            String actualTitle = driver.getTitle();
            throw new AssertionError("Page title does not match the expected. Expected: "
                    + expectedText + ", Actual: " + actualTitle);
        }
    }

    public boolean isElementDisplayed(By by) {
        try {
            return findElement(by).isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

}
