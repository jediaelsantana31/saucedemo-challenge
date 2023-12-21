package com.saucedemo.steps;

import com.saucedemo.actionwords.Products;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsStep {
    private Products products;

    public ProductsStep() {
        products = new Products();
    }

    @When("the user navigates to the products page")
    public void theUserNavigatesToTheProductsPage() {
        products.validateProductScreenDisplayed();
    }

    @Then("the user should see a list of available products")
    public void theUserShouldSeeAListOfAvailableProducts() {
       products.validateProductInformation();
    }
}
