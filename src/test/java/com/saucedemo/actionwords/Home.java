package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.settings.ProjectSettings;
import org.junit.Assert;
import org.openqa.selenium.By;

public class Home extends SeleniumHelper {

    public void openApplication() {
        goTo(ProjectSettings.URL);
    }

    public void authenticateInTheApplication(String username, String password) {
        fillField(By.cssSelector("input[name='user-name']"), username);
        fillField(By.cssSelector("input[name='password']"), password);
        clickElement(By.id("login-button"));
    }

    public void validateErrorMessage(String errorMessage) {
        Assert.assertEquals(errorMessage, grabTextFrom(By.cssSelector("h3[data-test='error']")));
    }
}
