package com.saucedemo.steps;

import com.saucedemo.actionwords.Checkout;
import com.saucedemo.actionwords.ShoppingCart;
import com.saucedemo.utilities.base.BaseTest;
import com.saucedemo.utilities.base.UserData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class CheckoutStep extends BaseTest {
    private Checkout checkout;
    private UserData userData;

    public CheckoutStep() {
        checkout = new Checkout();
        userData = new UserData();
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        new ShoppingCart().clickCheckoutButton();
        checkout.proceedToCheckout(userData);
    }

    @Then("the user should view the order information")
    public void theUserShouldViewTheOrderInformation() {
        List<String> cartProducts = (List<String>) getVariable("cartProducts");
        checkout.overviewIsDisplayed()
                .validateCartItems(cartProducts)
                .validatePaymentAndShippingInfo(userData.getCardNumber(), userData.getShippingInformation())
                .validateTotalPrice();
    }
}
