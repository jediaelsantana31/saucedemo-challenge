@Products
Feature: Product list
  As a user, I must view the product list

  Background:
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"

  @TC003 @Regression
  Scenario: Validate the product list
    When the user navigates to the products page
    Then the user should see a list of available products

  @TC004 @Regression
  Scenario: Add product to cart
    When the user adds the following products to the cart:
      | Sauce Labs Backpack               |
      | Test.allTheThings() T-Shirt (Red) |
    Then the products should appear in the cart
