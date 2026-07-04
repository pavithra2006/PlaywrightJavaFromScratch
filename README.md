# Playwright Java Automation Framework

## Features

✅ Playwright Java

✅ Maven

✅ TestNG Runner

✅ Cucumber BDD

✅ Page Object Model (POM)

✅ Config Management

✅ Environment Support (QA/UAT/PROD)

✅ Extent Reports

✅ Video recording, HAR, traces on Failure

✅ Logging (SLF4J + Logback)

✅ Hooks

✅ ThreadLocal

✅ Parallel Execution

✅ Retry Mechanism

✅ Utility Classes

✅ Base Classes

✅ GitHub Actions

## Tech Stack

- Playwright Java 
- TestNG
- Cucumber
- Maven
- Extent Reports
- SLF4J + Logback

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

## Folder structure
-src
-main
-test
-reports
-logs
## Run Tests

mvn clean test

## Generate Reports

test-output/


## IMPROVEMENTS
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
Soft Assertions
Centralized Wait Strategy
Faker-based Test Data Generation
Request/Response Logging
AI-assisted Self-Healing Locator Layer

## Notes
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

## About TestNG:
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

## Parallel execution 
uses Cucumber dataprovider thread count, so configure in pom.xml surefire plugin
dataproviderthreadcount = 2

To view traceviewer files use below link and drag and drop trace zip files
https://trace.playwright.dev/

## Video recording on failure 
webm - developed by google 2010, these are smaller than MP4

## MDC logging (Mapped Diagnostic Context)
Is a technique in Java logging that allows developers to attach contextual information to log messages, making it easier to trace and analyze logs in multi-threaded or distributed applications.

## HAR recording
HAR = HTTP Archive
It is a record of all network traffic between the browser and the server during test execution.