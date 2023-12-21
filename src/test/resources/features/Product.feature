Feature: User Authentication

  Background:
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"

  @Scen_03 @Regression
  Scenario: Validate the product list
    When the user navigates to the products page
    Then the user should see a list of available products





   # Given the user is on the home page
  #  When the user enters the "<username>" and the "<password>"
   # Then the user should be successfully authenticated

  #Scenario Outline: Login with Invalid Credentials
  #  Given the user is on the home page
  #  When the user enters the "<username>" and the "<password>"
  #  Then the user should see the error message "Credentials Error"

   # Examples:
   #   | username | password |
   #   | invalid   | incorrect  |