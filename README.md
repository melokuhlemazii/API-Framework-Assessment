# API Assessment - Melokuhle

A comprehensive API testing framework built with Java and REST Assured for automated testing of user authentication endpoints.

## 📋 Overview

This project demonstrates automated API testing for user login functionality. It integrates database-driven testing by fetching test data from a MySQL database and validates API responses using REST Assured assertions.

## 🏗️ Architecture

The framework follows a modular architecture with clear separation of concerns:

- **Tests**: TestNG-based test classes
- **RequestBuilder**: API request construction utilities
- **PayloadBuilder**: JSON payload creation
- **Utilities**: Database connections and helper methods
- **Common**: Configuration and constants

## 🛠️ Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven
- **Testing Framework**: TestNG
- **API Testing**: REST Assured 6.0.0
- **Database**: MySQL (MySQL Connector/J 9.6.0)
- **JSON Processing**: JSON Simple, Gson
- **Reporting**: Allure TestNG 2.29.1
- **Data Generation**: JavaFaker 1.0.2

## 📁 Project Structure

```
API_Assessment_Melokuhle/
├── src/
│   └── test/
│       └── java/
│           ├── Basic/
│           │   └── UserRegistration.java
│           ├── Common/
│           │   └── BaseURIs.java              # API base URLs
│           ├── PayloadBuilder/
│           │   └── PayloadBuilder.java        # JSON payload creation
│           ├── RequestBuilder/
│           │   └── ApiRequestBuilder.java     # API request builders
│           ├── Tests/
│           │   └── UserRegistrationTest.java  # Main test class
│           └── Utilities/
│               ├── DatabaseConnection.java    # MySQL connection utility
│               └── Requests.java              # HTTP request utilities
├── target/                                    # Compiled classes
├── allure-results/                           # Test execution reports
└── pom.xml                                   # Maven configuration
```

## 🔧 Setup & Installation

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL database access (remote server configured)

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd API_Assessment_Melokuhle
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify setup**
   ```bash
   mvn test
   ```

## 🗄️ Database Configuration

The framework connects to a remote MySQL database to fetch test data:

- **Host**: 102.222.124.22:3306
- **Database**: ndosian6b8b7_teaching
- **Table**: loginUser
- **Query**: SELECT * FROM loginUser WHERE id = 1

**Note**: Database credentials are hardcoded in `DatabaseConnection.java` for demonstration purposes. In production, consider using environment variables or secure credential management.

## 🧪 Test Execution

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=UserRegistrationTest
```

### Generate Allure Reports
```bash
mvn allure:report
```

## 📊 Test Cases

### User Login Test

**Test Method**: `adminLoginTest()`

**Purpose**: Validates successful user authentication via API

**Steps**:
1. Establishes database connection
2. Retrieves user credentials (email, password) for user ID=1
3. Constructs login payload with retrieved credentials
4. Sends POST request to `/APIDEV/login` endpoint
5. Validates response status code (200)
6. Extracts authentication token from response

**API Endpoint**: `https://ndosiautomation.co.za/APIDEV/login`

**Request Payload**:
```json
{
  "email": "user@example.com",
  "password": "userpassword"
}
```

## 📈 Reporting

The framework integrates Allure reporting for comprehensive test results:

- **Location**: `allure-results/` directory
- **Generation**: Automatic during test execution
- **View Reports**: Run `mvn allure:serve` to view interactive reports

## 🔒 Security Considerations

- Database credentials are currently hardcoded
- Consider implementing secure credential management
- API endpoints may require authentication tokens
- Database connection uses plain text credentials

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📝 License

This project is for educational and assessment purposes.

## 👤 Author

Melokuhle - API Assessment Project
