package com.saucedemo.utilities.base;

import com.saucedemo.utilities.settings.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHelper {
    protected ScenarioContext context;
    private WebDriver driver;

    public SeleniumHelper() {
        context = ScenarioContext.getInstance();
        driver = context.getDriver();
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        findElement(locator).click();
    }

    public void fillFormField(By locator, String value) {
        findElement(locator).sendKeys(value);
    }

    public boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }

   /* public static void waitForElement(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }*/

    public boolean isCurrentUrlContains(String partialUrl) {
        return driver.getCurrentUrl().contains(partialUrl);
    }

    public String getTextFromElement(By locator) {
        return findElement(locator).getText();
    }

    public void selectOptionFromDropdown(By selectLocator, String optionText) {
        Select dropdown = new Select(findElement(selectLocator));
        dropdown.selectByVisibleText(optionText);
    }

    public void pressKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

}
