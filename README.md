# API Assessment - Melokuhle

A comprehensive API testing framework built with Java and REST Assured for automated testing of user management and authentication workflows. This framework demonstrates advanced API testing patterns including test dependency management, token-based authentication, database-driven testing, and comprehensive test reporting.

## 📋 Overview

This project provides a complete automated testing solution for user lifecycle management APIs. It covers the full user journey from registration, approval, role assignment, and deletion. The framework integrates MySQL database testing, dynamic data generation using JavaFaker, and REST Assured for API validation.

**Key Features**:
- ✅ Database-driven test data retrieval
- ✅ Dynamic test data generation with JavaFaker
- ✅ Token-based authentication management
- ✅ Test dependency chaining for workflow validation
- ✅ Comprehensive API response assertions
- ✅ Allure test reporting integration
- ✅ Modular architecture with separation of concerns

## 🏗️ Architecture

The framework follows a **builder pattern** architecture with clear separation of concerns:

### Core Modules

| Module | Purpose |
|--------|---------|
| **PayloadBuilder** | Constructs JSON request bodies for API operations |
| **ApiRequestBuilder** | Executes HTTP requests and manages authentication tokens |
| **DatabaseConnection** | Manages MySQL connections and test data retrieval |
| **BaseURIs** | Centralized API base URL configuration |
| **UserRegistrationTest** | Main test suite orchestrating the complete workflow |

### Design Pattern: Builder Pattern

```
PayloadBuilder (Data Construction)
         ↓
ApiRequestBuilder (Request Execution + Token Management)
         ↓
UserRegistrationTest (Test Orchestration & Assertions)
```

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 21 |
| **Build Tool** | Maven | 3.6+ |
| **Testing Framework** | TestNG | 7.12.0 |
| **API Testing** | REST Assured | 6.0.0 |
| **Database Driver** | MySQL Connector/J | 9.6.0 |
| **JSON Processing** | JSON Simple | 1.1.1 |
| **JSON Serialization** | Gson | 2.13.0 |
| **Test Reporting** | Allure TestNG | 2.29.1 |
| **Data Generation** | JavaFaker | 1.0.2 |
| **JSON Schema Validation** | REST Assured Schema Validator | 5.5.1 |

## 📁 Project Structure

```
API_Assessment_Melokuhle/
├── src/
│   └── test/
│       └── java/
│           ├── Basic/
│           │   └── UserRegistration.java           # Basic API testing examples
│           │
│           ├── Common/
│           │   └── BaseURIs.java                   # API base URL constants
│           │
│           ├── PayloadBuilder/
│           │   └── PayloadBuilder.java             # JSON payload creation
│           │       ├── loginUserPayload()
│           │       ├── registerUserPayload()
│           │       ├── approveUserRegistrationPayload()
│           │       ├── makeUserAdminPayload()
│           │       └── deleteUserPayload()
│           │
│           ├── RequestBuilder/
│           │   └── ApiRequestBuilder.java          # API request execution
│           │       ├── loginUserResponse()
│           │       ├── registerUserResponse()
│           │       ├── approveUserRegistrationResponse()
│           │       ├── makeUserAdminResponse()
│           │       ├── verifyUserAdminResponse()
│           │       ├── deleteUserResponse()
│           │       └── Token Management Variables
│           │
│           ├── Tests/
│           │   └── UserRegistrationTest.java       # Main test orchestration
│           │
│           └── Utilities/
│               ├── DatabaseConnection.java        # MySQL connectivity
│               └── Requests.java                   # Reserved for future utilities
│
├── target/                                         # Compiled bytecode
├── allure-results/                                 # Allure test reports (JSON)
├── pom.xml                                         # Maven configuration
└── README.md                                       # This file
```

## 🔧 Setup & Installation

### Prerequisites

- **Java 21+** - Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - Download from [maven.apache.org](https://maven.apache.org/download.cgi)
- **MySQL** - Remote server configured (credentials in DatabaseConnection.java)
- **IDE** - IntelliJ IDEA or Eclipse recommended

### Installation Steps

1. **Clone the repository**
   ```powershell
   git clone <repository-url>
   cd API_Assessment_Melokuhle
   ```

2. **Install dependencies**
   ```powershell
   mvn clean install
   ```

3. **Verify Java and Maven installation**
   ```powershell
   java -version
   mvn -version
   ```

4. **Run test suite**
   ```powershell
   mvn test
   ```

## 🗄️ Database Configuration

The framework connects to a **remote MySQL database** for test data retrieval:

| Property | Value |
|----------|-------|
| **Host** | 102.222.124.22:3306 |
| **Database** | ndosian6b8b7_teaching |
| **Username** | ndosian6b8b7_teaching |
| **Table** | loginUser |
| **Query** | SELECT * FROM loginUser WHERE id = 7 |

**Current Configuration**: The `dbConnection()` method retrieves admin credentials from the database to authenticate subsequent test operations.

### Credentials Storage

```java
// DatabaseConnection.java
public static String getEmail;      // Retrieved from database
public static String getPassword;   // Retrieved from database
```

**⚠️ Security Note**: Database credentials are currently hardcoded. For production environments, implement:
- Environment variables
- Secure credential vaults
- Configuration management systems
- CI/CD pipeline secrets

## 🧪 Test Execution

### Run All Tests
```powershell
mvn test
```

### Run Specific Test Class
```powershell
mvn test -Dtest=UserRegistrationTest
```

### Run with Debug Logging
```powershell
mvn test -X
```

### Generate Allure Report After Execution
```powershell
mvn allure:report
mvn allure:serve
```

## 📊 Test Cases - User Lifecycle Workflow

The `UserRegistrationTest.java` class orchestrates a complete user lifecycle test workflow with **test dependency chaining** using TestNG's `dependsOnMethods`:

### 1️⃣ User Registration Test
**Method**: `userRegistration()`

| Aspect | Details |
|--------|---------|
| **Purpose** | Register a new user with dynamically generated email |
| **HTTP Method** | POST |
| **Endpoint** | `/APIDEV/register` |
| **Payload** | firstName, lastName, email, password, confirmPassword, groupId |
| **Expected Status** | 201 Created |
| **Data Generation** | JavaFaker generates unique email address |
| **Assertion** | `statusCode(201)` + `success=true` |

**Request Example**:
```json
{
  "firstName": "Ntuthuko",
  "lastName": "Mazii",
  "email": "generated@faker.com",
  "password": "Ntuthuko1031@",
  "confirmPassword": "Ntuthuko1031@",
  "groupId": "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526"
}
```

---

### 2️⃣ Admin Login Test
**Method**: `adminLoginTest()` | **Depends On**: `userRegistration`

| Aspect | Details |
|--------|---------|
| **Purpose** | Authenticate admin user and retrieve JWT token |
| **HTTP Method** | POST |
| **Endpoint** | `/APIDEV/login` |
| **Payload** | email, password (from database) |
| **Expected Status** | 200 OK |
| **Token Capture** | Stores token as `adminToken` for authorized requests |
| **Assertion** | `statusCode(200)` + `success=true` |

**Token Storage**:
```java
static String adminToken;  // Used for admin operations
static String userToken;   // Used for user-level operations
static String authToken;   // Generic authentication token
```

---

### 3️⃣ Approve User Registration Test
**Method**: `approveUserRegistration()` | **Depends On**: `adminLoginTest`

| Aspect | Details |
|--------|---------|
| **Purpose** | Approve pending user registration (admin operation) |
| **HTTP Method** | PUT |
| **Endpoint** | `/APIDEV/admin/users/{userId}/approve` |
| **Payload** | status: "approved" |
| **Authorization** | Bearer `adminToken` (JWT) |
| **Expected Status** | 200 OK |
| **Assertion** | `statusCode(200)` + `success=true` |

**Headers**:
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
Content-Type: application/json
```

---

### 4️⃣ Make User Admin Test
**Method**: `makeUserAdminTest()` | **Depends On**: `approveUserRegistration`

| Aspect | Details |
|--------|---------|
| **Purpose** | Promote registered user to admin role |
| **HTTP Method** | PUT |
| **Endpoint** | `/APIDEV/admin/users/{userId}/role` |
| **Payload** | role: "admin" |
| **Authorization** | Bearer `adminToken` (JWT) |
| **Expected Status** | 200 OK |
| **Assertion** | `statusCode(200)` + `success=true` |

**Request Payload**:
```json
{
  "role": "admin"
}
```

---

### 5️⃣ User Login Test (New Admin)
**Method**: `userLoginTest()` | **Depends On**: `makeUserAdminTest`

| Aspect | Details |
|--------|---------|
| **Purpose** | Login with newly promoted admin account |
| **HTTP Method** | POST |
| **Endpoint** | `/APIDEV/login` |
| **Payload** | email (registered email), password |
| **Expected Status** | 200 OK |
| **Token Capture** | Stores token as `userToken` |
| **Assertion** | `statusCode(200)` + `success=true` |

---

### 6️⃣ Verify User Is Admin Test
**Method**: `verifyUserIsAdminTest()` | **Depends On**: `makeUserAdminTest`

| Aspect | Details |
|--------|---------|
| **Purpose** | Verify user's admin role via GET endpoint |
| **HTTP Method** | GET |
| **Endpoint** | `/APIDEV/admin/users/{userId}` |
| **Authorization** | Bearer `userToken` (JWT) |
| **Expected Status** | 200 OK |
| **Response Validation** | data.Role = "admin" |
| **Assertion** | `statusCode(200)` + `body("data.Role", equalTo("admin"))` |

**Response Example**:
```json
{
  "success": true,
  "message": "User retrieved successfully",
  "data": {
    "Id": "ad428915-9db7-4cfc-b403-14b65b999f58",
    "FirstName": "Ntuthuko",
    "LastName": "Mazii",
    "Email": "email@example.com",
    "Role": "admin",
    "IsActive": 1,
    "EmailVerified": 0,
    "CreatedAt": "2026-04-10 15:30:52",
    "UpdatedAt": "2026-04-10 15:30:56",
    "stats": {
      "testimonialCount": 0,
      "taskCount": 0,
      "enrollmentCount": 0
    }
  }
}
```

---

### 7️⃣ Re-Login As Admin Before Delete
**Method**: `reLoginAsAdminBeforeDelete()` | **Depends On**: `verifyUserIsAdminTest`

| Aspect | Details |
|--------|---------|
| **Purpose** | Re-authenticate as admin for delete operation |
| **HTTP Method** | POST |
| **Endpoint** | `/APIDEV/login` |
| **Payload** | Database admin credentials |
| **Expected Status** | 200 OK |
| **Token Refresh** | Updates `adminToken` for delete operation |
| **Assertion** | `statusCode(200)` + `success=true` |

**Reasoning**: This test ensures the admin token is fresh and valid before attempting the delete operation.

---

### 8️⃣ Delete User Test
**Method**: `deleteUserTest()` | **Depends On**: `reLoginAsAdminBeforeDelete`

| Aspect | Details |
|--------|---------|
| **Purpose** | Delete registered user (admin operation only) |
| **HTTP Method** | DELETE |
| **Endpoint** | `/APIDEV/admin/users/{userId}` |
| **Payload** | status: "deleted" |
| **Authorization** | Bearer `adminToken` (JWT) |
| **Expected Status** | 200 OK |
| **Assertion** | `statusCode(200)` + `success=true` |

**Request Payload**:
```json
{
  "status": "deleted"
}
```

**API Constraint**: Users cannot delete their own accounts - must use different admin token.

## 🔑 Key Components Explained

### PayloadBuilder Pattern

Creates reusable, immutable JSON request bodies:

```java
// Example: Login payload
JSONObject loginPayload = PayloadBuilder.loginUserPayload("email@example.com", "password123");
// Produces: { "email": "email@example.com", "password": "password123" }
```

**Benefits**:
- Centralized payload structure management
- Easy payload modification
- DRY principle compliance
- Type-safe payload creation

### ApiRequestBuilder Pattern

Orchestrates HTTP requests with token management:

```java
// Registers a user and captures the returned user ID
Response response = ApiRequestBuilder.registerUserResponse(firstName, lastName, email, password, groupId);
// Automatically stores registeredUserId for subsequent requests
```

**Features**:
- Manages multiple token types (`adminToken`, `userToken`, `authToken`)
- Automatically extracts and stores response data (IDs, tokens)
- Encapsulates REST Assured request building
- Provides clean separation between request logic and test logic

### Token Management Strategy

```java
static String authToken;      // Generic token from any login
static String adminToken;     // Admin-level authentication
static String userToken;      // User-level authentication
static String registeredUserId;    // Tracks created user for operations
```

**Flow**:
1. `adminLoginTest()` → Stores `adminToken`
2. `userLoginTest()` → Stores `userToken`
3. Admin operations use `adminToken`
4. User operations use `userToken`
5. Re-login before delete refreshes token

## 📈 Reporting

### Allure Reports

The framework integrates **Allure TestNG** for comprehensive test reporting:

**Features**:
- Timeline view of test execution
- Detailed failure logs with request/response
- Test dependency visualization
- Attachment support (screenshots, logs)
- Historical trend analysis

**Report Location**: `allure-results/` directory (JSON format)

**Generate Reports**:
```powershell
# Generate report
mvn allure:report

# Serve report on local server (opens browser)
mvn allure:serve
```

**Report Contents**:
- Test execution timeline
- Pass/fail statistics
- Detailed test logs
- HTTP request/response pairs
- Assertion failure details
- Test parameter information

## 🔒 Security Considerations

### Current Issues ⚠️

| Issue | Risk | Recommendation |
|-------|------|-----------------|
| Hardcoded DB credentials | **HIGH** | Use environment variables |
| Plain text passwords | **HIGH** | Use secure vaults (HashiCorp Vault, AWS Secrets Manager) |
| JWT tokens in logs | **MEDIUM** | Implement token masking in logs |
| No SSL certificate validation | **MEDIUM** | Validate SSL certificates in production |
| Database in test code | **LOW** | Extract to configuration files |

### Recommended Security Implementation

```java
// Use environment variables instead of hardcoding
String dbURL = System.getenv("DB_URL");
String dbUsername = System.getenv("DB_USERNAME");
String dbPassword = System.getenv("DB_PASSWORD");
```

## 🤝 Contributing

### Development Workflow

1. Create a feature branch
   ```powershell
   git checkout -b feature/add-new-endpoint-test
   ```

2. Add test cases following existing patterns
3. Ensure all tests pass
   ```powershell
   mvn clean test
   ```

4. Generate and review reports
   ```powershell
   mvn allure:report
   ```

5. Commit with descriptive messages
   ```powershell
   git commit -m "Add: New endpoint tests for user management"
   ```

6. Push and create pull request
   ```powershell
   git push origin feature/add-new-endpoint-test
   ```

### Code Style Guidelines

- Follow Java naming conventions (camelCase for methods/variables)
- Use descriptive test method names following pattern: `testScenarioName()`
- Organize tests using TestNG annotations (`@BeforeClass`, `@Test`, `@AfterClass`)
- Use meaningful assertion messages
- Keep test methods focused on single responsibilities
- Document complex test logic with comments

## 🐛 Known Issues & Limitations

| Issue | Status | Workaround |
|-------|--------|-----------|
| Super admin access required for certain operations | DOCUMENTED | Use database admin credentials |
| User cannot delete their own account | BY DESIGN | Use different admin token for deletion |
| Email verification required in some endpoints | PENDING | Use verified test accounts |
| Network latency causing intermittent timeouts | KNOWN | Increase request timeout in configuration |

## 📚 API Reference

### Base URL
```
https://ndosiautomation.co.za
```

### Authentication
All admin endpoints require JWT token in Authorization header:
```
Authorization: Bearer {token}
Content-Type: application/json
```

### Endpoints Tested

| Method | Endpoint | Purpose |
|--------|----------|---------|
| POST | `/APIDEV/login` | User authentication |
| POST | `/APIDEV/register` | User registration |
| PUT | `/APIDEV/admin/users/{id}/approve` | Approve registration |
| PUT | `/APIDEV/admin/users/{id}/role` | Change user role |
| GET | `/APIDEV/admin/users/{id}` | Retrieve user details |
| DELETE | `/APIDEV/admin/users/{id}` | Delete user |

## 📝 License

This project is for educational and assessment purposes.

## 👤 Author

**Melokuhle** - API Assessment Project  
Date: April 2026

---

## 📞 Support

For issues, questions, or improvements, please create an issue in the repository.

Last Updated: April 10, 2026
