# MISCLearning Automation Framework

## Overview

This project is a Java-based automation framework using the Page Object Model (POM) design pattern. It is structured for maintainability, scalability, and ease of use for UI test automation.

## Project Structure

```
misclearning/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── factory/         # Driver and other factory classes
│   │       ├── page/            # Page Object classes
│   │       ├── utilities/       # Utility/helper classes
│   │       ├── manager/         # Page manager classes
│   │       ├── base/            # BasePage, BaseTest, etc.
│   │       └── listener/        # TestNG listeners
│   └── test/
│       └── java/
│           └── tests/           # Test classes
│
├── reports/                     # Test reports
├── pom.xml                      # Maven dependencies and build config
└── README.md                    # Project documentation
```

## Key Features

- **Page Object Model (POM)** for maintainable test code
- **Driver Factory** for WebDriver management
- **BasePage/BaseTest** for common functionality
- **Page Manager** for page object instantiation
- **Utility Classes** for reusable helpers
- **TestNG Listeners** for enhanced reporting
- **Report Generation** for test results

## Getting Started

### Prerequisites

- Java 11 or above
- Maven 3.6+
- ChromeDriver/GeckoDriver (as per your browser)

### Setup

1. Clone the repository:
   ```
   git clone <repo-url>
   ```
2. Navigate to the project directory:
   ```
   cd misclearning
   ```
3. Install dependencies:
   ```
   mvn clean install
   ```

### Running Tests

To execute tests, use:
```
mvn test
```

### Reports

Test execution reports are generated in the `reports/` directory.

## Contribution

- Follow Java coding standards.
- Place new page objects in the `page` folder.
- Add utilities to the `utilities` folder.
- Update the README as needed.

## License

This project is for educational and internal use.
