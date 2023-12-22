package com.saucedemo.steps;

import com.saucedemo.actionwords.Checkout;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutStep {
    private Checkout checkout;

    public CheckoutStep() {
        checkout = new Checkout();
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user should view the order information")
    public void theUserShouldViewTheOrderInformation() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
