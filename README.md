# SauceDemo Automation

## Overview

This project performs test automation for the SauceDemo application using Selenium WebDriver and Java.

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
    ├── data
    ├── features
    └── chromedriver.exe
└── pom.xml


## Configuration

Make sure you have the following tools installed:

- [Java](https://www.java.com/)
- [Maven](https://maven.apache.org/)
- [Chromedriver](https://sites.google.com/chromium.org/driver/)

## Running Tests

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/saucedemo-automation.git
cd saucedemo-automation

