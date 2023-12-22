package com.saucedemo.steps;

import com.saucedemo.actionwords.Products;
import com.saucedemo.utilities.base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class ProductsStep extends BaseTest {
    private Products products;

    public ProductsStep() {
        products = new Products();
    }

    @When("the user navigates to the products page")
    public void theUserNavigatesToTheProductsPage() {
        products.validateProductScreenDisplayed();
    }

    @When("the user adds the following products to the cart:")
    public void userAddsProductsToCart(List<String> productNames) {
        products.addsProductsToCart(productNames).navigateToCart();
        setVariable("cartProducts", productNames);
    }

    @Then("the user should see a list of available products")
    public void theUserShouldSeeAListOfAvailableProducts() {
        products.validateProductInformation();
    }

}
