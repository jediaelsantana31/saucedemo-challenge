package com.saucedemo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.saucedemo.steps",
        tags = "@Scen_02",
        plugin = {"pretty", "html:target/cucumber-reports"},
        snippets = CAMELCASE
)
public class TestRunner {
}
