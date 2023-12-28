Feature: Test Checkout Flow

  Background:
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"
    When the user adds the following products to the cart:
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |

  @Scen_07 @Regression
  Scenario: Verify Order Information During Checkout
    When the user proceeds to checkout
    Then the user should view the order information