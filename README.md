# QA Automation Assignment

We have added two tests: the first task is API-Test.feature and the second is UI-Test.feature.
- For the API-Test.feature, please visit https://reqres.in/. This should contain all the requirements.
- UI-Test.feature please visit https://www.saucedemo.com/


Please DO use Page objects, make sure the code is reusable and feel free to improve the current code.

**Note: We have intentionally added some bugs for you to debug.** 

Please contact the Mission Team if you have any questions.


Good luck!

## Test Automation Framework

- This is a Maven based framework
- `pom.xml` should have everything you need to create and run the tests. Please add further dependencies if you require it.

The following folder `src/test/java/AutomationTest/mission` contains the following class:

- `Hook` - this is the before and after. This launches and kills the browser.
- `RunnerTest` - contains the CucumberOptions which runs the BDD's

The following folder `src/main/java/AutomationTest/mission` contains the following class:

- `BrowserSetup` - This contains the setup of a given browser based on what is set to Browser property within `TestData.properties` 

 
## Steps to execute this project

- Pre-requisites
    - JAVA SDK 1.8 or higher
    - Maven CLI
    
- Steps
    - Clone the project to local
    - Got o command line or any IDE that supports JAVA & Maven dependencies
    - We may need to import the Maven dependencies (Scope got set to Compile for Newly added dependencies in pom.xml)
    - Execute the command: `mvn clean test`
    - Alternatively, we can run `testng.xml` from IDE after downloading the dependencies
    - Result will be captured in `test-output` folder

---------------------------------------------------------------------------------------------
Mission QA Automation Framework

This repository contains a test automation framework developed for the Mission Under QA assignment. The framework supports UI and API testing using Cucumber BDD, Selenium WebDriver, RestAssured, and TestNG with Maven.

Technology Stack

Java
Maven
Selenium WebDriver
Cucumber (BDD)
TestNG
RestAssured (API testing)
SLF4J (logging)

Framework Highlights

Page Object Model (POM) for UI automation
Separate layers for UI tests and API tests
Reusable API client (ReqresClient)
Centralized configuration using TestData.properties
browser management using Cucumber Hooks
Tag-based test execution (@ui, @api) [In rennuer currently deified  tags = "@api or @ui" Run test based on requirement execution]
Cucumber HTML report generation
Logging added for better execution traceability

Project Structure
src
 ├── main/java/mission
 │     ├── api
 │     ├── pages
 │     ├── BasePage.java
 │     ├── BrowserSetup.java
 │     └── LoadProp.java
 │
 ├── test/java/mission
 │     ├── RunnerTest.java
 │     ├── Hook.java
 │     ├── ApiStepDefinition.java
 │     └── UIStepDefinition.java
 │
 └── test/resources
       ├── features
       │     ├── API-Test.feature
       │     └── UI-Test.feature
       └── TestData.properties

Running Tests
Run all tests:
    mvn clean test

Run only API tests:
    mvn clean test -Dcucumber.filter.tags="@api"

Run only UI tests:
    mvn clean test -Dcucumber.filter.tags="@ui"

Test Coverage
API Tests

Tests implemented using ReqRes API:
Get users list
Get single user
Handle user not found
Create user
Login success / failure
Delayed response validation

UI Tests
Tests implemented using SauceDemo:

Login
Add products to cart
Validate cart item count
Remove item from cart
Complete checkout
Validate item total and tax


Reports
Execution reports are generated in:

    target/cucumber-report.html

Notes

The framework is designed with maintainability and scalability in mind, following common automation practices such as Page Object Model, reusable API clients, configuration management, and tag-based execution.