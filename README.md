# SauceDemo Automation 

## Overview

This project is dedicated to test automation for the SauceDemo application, leveraging Selenium WebDriver and Java technologies. The automated tests provide a robust and scalable solution for verifying the functionality and integrity of the SauceDemo application, ensuring a reliable and efficient testing process.

## System Functionality Map

![System Functionality Map]([URL_do_Mapa_Mental](https://github.com/jediaelsantana31/saucedemo-challenge/blob/main/src/test/resources/mind%20map.png))

## Prerequisites

Make sure you have the following tools installed before running the tests:

- [Java -JDK21](https://www.oracle.com/br/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Allure Report](https://allurereport.org/docs/gettingstarted-installation/)

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/jediaelsantana31/saucedemo-challenge.git
   
2. Navigate to the project root:
   ```bash
   cd saucedemo-automation

3. Build the project using Maven:
   ```bash
   mvn clean install

## Running Tests
Execute the Selenium tests using the following Maven command:

1. Headless Mode:
   	```
   	mvn test -DheadlessMode=true -D"cucumber.filter.tags=@tag"
   	```
2. Chromedriver:
   	```
   	mvn test -DheadlessMode=false -D"cucumber.filter.tags=@tag"
	```

## Generate Report
 	allure serve
## Project Structure

        saucedemo-automation
        ├── .github
        │   └── workflows
        │       └── selenium-test.yaml
        ├── src
        │   └── test
        │       └── java
        │           └── com.saucedemo
        │               ├── actionwords
        │               ├── runner
        │               ├── steps
        │               └── utilities
        └── resources
        │   ├── data
        │   ├── features
        │   └── chromedriver.exe
        └── pom.xml

## Test Execution Workflow
The project utilizes [GitHub Actions](https://github.com/jediaelsantana31/saucedemo-challenge/actions) for continuous integration. The workflow is defined in the `.github/workflows/selenium-test.yaml` file.

--------------
# Test Cases

### Login:

#### TC001 - Login successfully

**Description:** Check if the system allows login with valid credentials.

**Preconditions:** User must have access to the system.

**Status:** Done

**Gherkin:**
	
	Background:
	    Given the user is on the home page

	@TC001 @Regression
	Scenario: Login successfully
	    When the user enters the "standard_user" and the "secret_sauce"
	    Then the user should be successfully authenticated
     

#### TC002 - Invalid login

**Description:** Check if the system displays an error message when trying to log in with invalid credentials.

**Preconditions:** N/A

**Status:** Done

**Gherkin:**

	Background:
		Given the user is on the home page

	@TC002 @Regression
	Scenario Outline: Invalid login
		When the user enters the "<username>" and the "<password>"
		Then the user should see the error message "<error_message>"

	Examples:
		| username          | password        | error_message                                                             |
		|                   | secret_sauce    | Epic sadface: Username is required                                        |
		| standard_user     |                 | Epic sadface: Password is required                                        |
		| standard_user_In  | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
		| standard_user     | secret_sauce_In | Epic sadface: Username and password do not match any user in this service |

### Products:

#### TC003 - Validate the product list

**Description:** The user should see a list of available products.

**Preconditions:** User must be logged in.

**Status:** Done

**Gherkin:**

	Background:
		Given the user is on the home page
		When the user enters the "standard_user" and the "secret_sauce"

	@TC003 @Regression
	Scenario: Validate the product list
		When the user navigates to the products page
		Then the user should see a list of available products
	
	
#### TC004 - Add product to cart

**Description:** Check if a product can be added to the cart.

**Preconditions:** User must be logged in.

**Status:** Done

**Gherkin:**

	Background:
		Given the user is on the home page
		When the user enters the "standard_user" and the "secret_sauce"

	@TC004 @Regression
	Scenario: Add product to cart
		When the user adds the following products to the cart:
			| Sauce Labs Backpack               |
			| Test.allTheThings() T-Shirt (Red) |
		Then the products should appear in the cart

  ### Cart:

#### TC005 - Remove product from cart

**Description:** Check that the product can be removed from the cart.

**Preconditions:** User must be logged in and have products in the cart.

**Status:** Done

**Gherkin:**

	Background:
		Given the user is on the home page
		When the user enters the "standard_user" and the "secret_sauce"

	@TC005 @Regression
	Scenario: Remove product from cart
		When the user adds the following products to the cart:
			| Sauce Labs Backpack               |
			| Test.allTheThings() T-Shirt (Red) |
			| Sauce Labs Bolt T-Shirt           |
		When the user removes the following products from the cart:
			| Test.allTheThings() T-Shirt (Red) |
		Then the product(s) should be removed from the cart


### Checkout:

#### TC006 - Verify Order Information During Checkout

**Description:** Validate the purchase of products on Sauce Demo.

**Preconditions:** User must be logged in and have products in the cart.

**Status:** Done

**Gherkin:**

	Background:
		Given the user is on the home page
		When the user enters the "standard_user" and the "secret_sauce"
		When the user adds the following products to the cart:
			| Sauce Labs Fleece Jacket |
			| Sauce Labs Onesie        |

	@TC006 @Regression @Smoke
	Scenario: Verify Order Information During Checkout
		When the user proceeds to checkout
		Then the user should view the order information
		And the final message should be "Thank you for your order!"
