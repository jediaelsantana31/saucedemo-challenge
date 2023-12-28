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
        return findElement(locator).getText();
    }

    public boolean isTextPresent(By locator, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        System.out.println("titulo -------> " + driver.getTitle());
        try {
            return wait.until(driver -> {
                WebElement element = driver.findElement(locator);
                System.out.println("element -------> " + element.getText());
                return element.getText().contains(text);
            });
        } catch (Exception e) {
            return false;
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

//    public static void waitForElement(WebDriver driver, By locator, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//    }

    public boolean isCurrentUrlContains(String partialUrl) {
        return driver.getCurrentUrl().contains(partialUrl);
    }


    public void selectOptionFromDropdown(By selectLocator, String optionText) {
        Select dropdown = new Select(findElement(selectLocator));
        dropdown.selectByVisibleText(optionText);
    }

    public void pressKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

    public void waitForElementVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
