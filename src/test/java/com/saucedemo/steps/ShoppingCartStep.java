package com.saucedemo.steps;

import com.saucedemo.actionwords.ShoppingCart;
import com.saucedemo.utilities.base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class ShoppingCartStep extends BaseTest {
    private ShoppingCart shoppingCart;

    public ShoppingCartStep() {
        shoppingCart = new ShoppingCart();
    }

    @When("the user removes the following products from the cart:")
    public void theUserRemovesTheFollowingProductsFromTheCart(List<String> productNames) {
        shoppingCart.removeItemsFromCart(productNames);
        setVariable("productsDeleted", productNames);
    }

    @Then("the products should appear in the cart")
    public void theProductsShouldAppearInTheCart() {
        List<String> productNames = (List<String>) getVariable("cartProducts");
        shoppingCart.cartIsDisplayed().validateCartItems(productNames);
    }

    @Then("the product\\(s) should be removed from the cart")
    public void theProductSShouldBeRemovedFromTheCart() {
        List<String> productNames = (List<String>) getVariable("productsDeleted");
        shoppingCart.validateProductNotInCart(productNames);
    }
}
