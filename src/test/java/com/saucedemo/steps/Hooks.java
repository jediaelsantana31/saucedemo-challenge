package com.saucedemo.steps;

import com.google.common.collect.ImmutableMap;
import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.settings.ProjectSettings;
import com.saucedemo.utilities.settings.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

//import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class Hooks {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();


    @Before
    public void beforeScenario() {
        scenarioContext.getDriver();
        configureReport();
    }

    public void configureReport() {
        Capabilities capabilities = scenarioContext.printBrowserInfo();
        /*allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", capabilities.getBrowserName())
                        .put("Browser.Version", capabilities.getBrowserVersion())
                        .put("URL", ProjectSettings.URL).build(),
                System.getProperty("user.dir") + "/allure-results/");*/
    }

    @After
    public void afterScenario(Scenario scenario) {
        scenarioContext.testResult(scenario);
        scenarioContext.reset();
    }
}
