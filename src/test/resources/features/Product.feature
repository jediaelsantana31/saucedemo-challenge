Feature: User Authentication


  Scenario: Verify if products are displayed correctly
    Given the user is on the home page
    When the user enters the "<username>" and the "<password>"
    Then the user should be successfully authenticated

  Scenario Outline: Login with Invalid Credentials
    Given the user is on the home page
    When the user enters the "<username>" and the "<password>"
    Then the user should see the error message "Credentials Error"

    Examples:
      | username | password |
      | invalid   | incorrect  |