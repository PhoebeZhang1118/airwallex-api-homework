## Airwallex FX Rates API Test Framework
A robust testing framework built with Java, TestNG, and REST Assured for validating the Airwallex Foreign Exchange Rates API.

## Key Features
- Comprehensive positive test cases for rate queries

- Negative test scenarios for error handling

- Response structure validation

- Exchange rate calculation verification

- Error handling mechanisms

## Project Structure
src/
├── main/java/com/airwallex/
│   ├── api/            # API client implementations
│   ├── actions/        # Test actions and assertions  
│   ├── models/         # Response data structures
│   └── utils/          # Utility classes
└── test/java/com/airwallex/
├── tests/          # Test classes
└── listeners/      # Test listeners

## Prerequisites
- Java 11 or higher
- Maven 3.6+ (if using Maven)

## Setup
- Clone the repository
- Build the project: `mvn clean install`

## Running Tests
### Using Maven
** Run all tests: ** 
bash
mvn test

** Run specific test class: **
bash
mvn test -Dtest=com.airwallex.tests.FxRatesPositiveTests

** Run specific test method:**
bash
mvn test -Dtest=com.airwallex.tests.FxRatesPositiveTests#testValidAudUsdWithAmount

** Run with custom testng.xml: **
bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml

## Configuration
Modify src/test/resources/config.properties for environment settings:
properties
api.baseUrl=https://api-demo.airwallex.com
api.authEndpoint=/api/v1/authentication/login
api.ratesEndpoint=/api/v1/fx/rates/current

## Adding New Tests
- Create new test class under src/test/java/com/airwallex/tests/

- Extend BaseTest for shared configurations

- Use assertion methods from fxRatesActions

- Reference new test class in testng.xml
