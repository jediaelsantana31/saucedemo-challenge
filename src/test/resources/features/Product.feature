Feature: Produto

  Background:
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"

  @Scen_03 @Regression
  Scenario: Validate the product list
    When the user navigates to the products page
    Then the user should see a list of available products

  @Scen_04 @Regression
  Scenario: Add product to cart
    When the user adds the following products to the cart:
      | Sauce Labs Backpack               |
      | Test.allTheThings() T-Shirt (Red) |
    Then the products should appear in the cart
