Framework Features

✅ Playwright Java

✅ Maven

✅ TestNG Runner

✅ Cucumber BDD

✅ Page Object Model (POM)

✅ Config Management

✅ Environment Support (QA/UAT/PROD)

✅ Extent Reports (most commonly used)

✅ Screenshot on Failure

✅ Logging (SLF4J + Logback)

✅ Hooks

✅ Parallel Execution

✅ Retry Mechanism

✅ Utility Classes

✅ Base Classes

✅ GitHub Actions/Jenkins Ready



playwright-java-framework
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base
│   │   │   │    ├── PlaywrightFactory.java
│   │   │   │    └── BasePage.java
│   │   │   │
│   │   │   ├── pages
│   │   │   │    ├── LoginPage.java
│   │   │   │    └── HomePage.java
│   │   │   │
│   │   │   ├── utils
│   │   │   │    ├── ConfigReader.java
│   │   │   │    ├── ScreenshotUtil.java
│   │   │   │    ├── WaitUtils.java
│   │   │   │    └── ExtentManager.java
│   │   │   │
│   │   │   └── constants
│   │   │        └── FrameworkConstants.java
│   │
│   └── resources
│        ├── config
│        │     └── config.properties
│        └── logback.xml
│
├── src/test
│   ├── java
│   │    ├── stepdefinitions
│   │    │      └── LoginSteps.java
│   │    │
│   │    ├── runners
│   │    │      └── TestRunner.java
│   │    │
│   │    ├── hooks
│   │    │      └── Hooks.java
│   │    │
│   │    └── listeners
│   │           └── RetryListener.java
│   │
│   └── resources
│         └── features
│               └── Login.feature
│
├── reports
│
├── test-output
│
├── pom.xml
│
├── testng.xml
│
└── README.md



# Playwright Java Automation Framework

## Tech Stack

- Playwright Java
- TestNG
- Cucumber
- Maven
- Extent Reports
- Logback

## Design Pattern

- Page Object Model

## Features

- Multi Browser Support
- Parallel Execution
- Screenshot on Failure
- Retry Mechanism
- Environment Configurations
- Detailed Reporting
- Thread Safe Execution

## Run Tests

mvn clean test

## Run Specific Environment

mvn clean test -Denvironment=qa

## Generate Reports

reports/ExtentReport.html


IMPROVEMENTS
Generic Playwright Actions Layer (ElementActions)
API Layer (RestAssured)
Database Utilities
Test Data Builder Pattern
Owner Library for Config Management
Dependency Injection using PicoContainer
Parallel Cucumber Execution
Allure Reporting (along with Extent)
Jenkins Pipeline
Docker Support
GitHub Actions
BrowserStack/SauceLabs Integration
Trace Viewer Collection
Video Recording on Failure
Soft Assertions
Custom Exceptions
Centralized Wait Strategy
Faker-based Test Data Generation
Request/Response Logging
AI-assisted Self-Healing Locator Layer
mdc scenario login

Notes
SLF4J = Logging API (interface/facade)
Logback/Log4j2 = Logging implementation
Your Framework
│
logger.info("Login Successful")
│
▼
SLF4J API
│
▼
Logback
│
▼
Console + automation.log

About TestNG:
TestNg- Creates worker threads.
↓
DataProvider- Distributes scenarios to those threads.
↓
Your ThreadLocal- Creates:
Playwright
Browser
BrowserContext
Page

for each thread.

Parallel execution - uses Cucumber dataprovider thread count, so configure in pom.xml surefire plugin
dataproviderthreadcount = 2