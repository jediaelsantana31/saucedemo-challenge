package com.saucedemo.steps;

import com.saucedemo.utilities.settings.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();


    @Before
    public void beforeScenario() {
        scenarioContext.getDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        scenarioContext.testResult(scenario);
        scenarioContext.reset();
    }
}
