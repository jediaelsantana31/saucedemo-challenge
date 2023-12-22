Feature: User Authentication

  Background:
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"

  @Scen_05 @Regression
  Scenario: Remove product from cart
    When the user adds the following products to the cart:
      | Sauce Labs Backpack               |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bolt T-Shirt           |
    When the user removes the following products from the cart:
      | Test.allTheThings() T-Shirt (Red) |
    Then the product(s) should be removed from the cart
