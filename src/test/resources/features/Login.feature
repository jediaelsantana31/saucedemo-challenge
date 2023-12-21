@Login
Feature: User Authentication

  @Scen_01 @Regression
  Scenario: User logs in successfully
    Given the user is on the home page
    When the user enters the "standard_user" and the "secret_sauce"
    Then the user should be successfully authenticated

  @Scen_02 @Regression
  Scenario Outline: Login with Invalid Credentials
    Given the user is on the home page
    When the user enters the "<username>" and the "<password>"
    Then the user should see the error message "<error_message>"

    Examples:
      | username         | password        | error_message                                                             |
      |                  | secret_sauce    | Epic sadface: Username is required                                        |
      | standard_user    |                 | Epic sadface: Password is required                                        |
      | standard_user_In | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
      | standard_user    | secret_sauce_In | Epic sadface: Username and password do not match any user in this service |
