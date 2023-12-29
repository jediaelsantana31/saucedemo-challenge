# SauceDemo Automation

## Overview

This project is dedicated to test automation for the SauceDemo application, leveraging Selenium WebDriver and Java technologies. The automated tests provide a robust and scalable solution for verifying the functionality and integrity of the SauceDemo application, ensuring a reliable and efficient testing process.

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

    1. Headless Mode: mvn test -DheadlessMode=true -D"cucumber.filter.tags=@tag"
	2. Chromedriver: mvn test -DheadlessMode=false -D"cucumber.filter.tags=@tag"

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
