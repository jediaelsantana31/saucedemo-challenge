package com.saucedemo.steps;

import com.saucedemo.actionwords.Home;
import com.saucedemo.actionwords.Products;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStep{
    private Home home;
    private Products products;

    public HomeStep() {
        home = new Home();
        products = new Products();
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        home.openApplication();
    }

    @When("the user enters the {string} and the {string}")
    public void enterCredentials(String username, String password) {
        home.authenticateInTheApplication(username, password);
    }

    @Then("the user should be successfully authenticated")
    public void the_user_should_be_successfully_authenticated() {
        products.productIsDisplayed();
    }

    @Then("the user should see the error message {string}")
    public void theUserShouldSeeTheErrorMessage(String errorMessage) {
        home.validateErrorMessage(errorMessage);
    }

}
