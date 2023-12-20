package com.saucedemo.steps;

import com.saucedemo.utilities.settings.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Before
    public void beforeScenario() {
        scenarioContext.getDriver();
    }

    @After
    public void afterScenario() {
        try {
            // Atraso de 10 segundos (10000 milissegundos)
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scenarioContext.reset();
    }
}
