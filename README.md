# 📊 Employees Credit System

Welcome to the **Employees Credit System**, a comprehensive platform designed specifically for processing and approving credit applications from government employees in Cuba. This system streamlines the entire credit approval workflow, from application submission to final credit disbursement.

## 📝 Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Workflow](#workflow)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 📘 Project Description

The **Employees Credit System** is a robust and secure application designed to handle credit applications from government employees in Cuba. The system ensures efficiency and transparency throughout the credit approval process.

## ✨ Features

- **🛡️ User Registration and Authentication**: Secure user management with JWT-based authentication.
- **💼 Credit Request Management**: Employees can submit and track credit requests.
- **📈 Credit Evaluation**: Analysts can evaluate and approve/reject credit requests.
- **🔐 Role-Based Access Control**: Different roles (Admin, Analyst, Employee) with specific permissions.
- **📄 API Documentation**: Swagger UI for easy API exploration.
- **📊 Monitoring and Logging**: Integrated tools for monitoring and logging.

## 🏗️ Workflow

![Employee Credit](https://github.com/Alejo2075/employees-credit/assets/91127175/eddb84aa-4f20-4438-8564-2111f9325b3d)

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **MySQL**
- **Docker**
- **Swagger/OpenAPI**
- **Log4j2**
- **JUnit & Mockito**

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:

- [Java 17](https://jdk.java.net/17/)
- [Docker](https://www.docker.com/get-started)
- [Maven](https://maven.apache.org/)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Alejo2075/employees-credit.git
    cd employees-credit
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application with Docker:**

    ```bash
    docker-compose up --build
    ```
## 📦 Usage

### Accessing the Application

Once the application is up and running, you can access it at `http://localhost:8085`.

### API Endpoints

Here are the primary API endpoints provided by the system:

#### Authentication

- **User Registration:** 
  - Endpoint: `POST /api/v1/auth/register`
  - Description: Registers a new user and returns a JWT token.
  - Request Body Example:
    ```json
    {
      "email": "user@example.com",
      "password": "password123"
    }
    ```

- **User Login:** 
  - Endpoint: `POST /api/v1/auth/login`
  - Description: Authenticates a user and returns a JWT token.
  - Request Body Example:
    ```json
    {
      "email": "user@example.com",
      "password": "password123"
    }
    ```

#### User Management

- **Get User Information:** 
  - Endpoint: `GET /api/v1/user/{id}`
  - Description: Retrieves information of a user by ID.
  - Example Request: `GET /api/v1/user/123e4567-e89b-12d3-a456-426614174000`

- **Update User Information:** 
  - Endpoint: `PUT /api/v1/user/{id}`
  - Description: Updates user information by ID.
  - Request Body Example:
    ```json
    {
      "fullName": "New Name",
      "email": "newemail@example.com",
      "password": "newpassword123"
    }
    ```

- **Assign Roles to User (Admin only):**
  - Endpoint: `POST /api/v1/user/{id}/assign-role`
  - Description: Assigns roles to a user.
  - Request Body Example:
    ```json
    {
      "roles": ["ANALYST", "EMPLOYEE"]
    }
    ```

- **Get All Users (Admin only):**
  - Endpoint: `GET /api/v1/user/all`
  - Description: Retrieves a list of all users.

- **Get All Employees (Admin and Analyst only):**
  - Endpoint: `GET /api/v1/user/employees`
  - Description: Retrieves a list of all employees.

- **Get All Analysts (Admin only):**
  - Endpoint: `GET /api/v1/user/analysts`
  - Description: Retrieves a list of all analysts.

#### Credit Management

- **Submit Credit Request (Employee only):**
  - Endpoint: `POST /api/v1/credits/submit`
  - Description: Submits a new credit request.
  - Request Body Example:
    ```json
    {
      "employeeId": "123e4567-e89b-12d3-a456-426614174000",
      "amount": 5000,
      "purpose": "Home Renovation",
      "termInMonths": 24
    }
    ```

- **View Credit Request:**
  - Endpoint: `GET /api/v1/credits/view/{id}`
  - Description: Retrieves the details of a specific credit request.
  - Example Request: `GET /api/v1/credits/view/123e4567-e89b-12d3-a456-426614174000`

- **Get All Credit Requests (Analyst and Admin only):**
  - Endpoint: `GET /api/v1/credits/all`
  - Description: Retrieves a list of all credit requests.

- **Delete Credit Request (Analyst and Admin only):**
  - Endpoint: `DELETE /api/v1/credits/delete/{id}`
  - Description: Deletes a specific credit request.
  - Example Request: `DELETE /api/v1/credits/delete/123e4567-e89b-12d3-a456-426614174000`

#### Credit Evaluation

- **Evaluate Credit Request (Analyst and Admin only):**
  - Endpoint: `POST /api/v1/analytics/evaluate`
  - Description: Evaluates a credit request and returns the evaluation result.
  - Request Body Example:
    ```json
    {
      "creditRequestId": "123e4567-e89b-12d3-a456-426614174000",
      "evaluationStatus": "APPROVED",
      "comments": "The credit request has been approved."
    }
    ```

- **View Evaluated Credit Request (Analyst and Admin only):**
  - Endpoint: `GET /api/v1/analytics/view-evaluated/{id}`
  - Description: Retrieves the details of a specific evaluated credit request.
  - Example Request: `GET /api/v1/analytics/view-evaluated/123e4567-e89b-12d3-a456-426614174000`

- **Get All Evaluated Credit Requests (Analyst and Admin only):**
  - Endpoint: `GET /api/v1/analytics/all-evaluated`
  - Description: Retrieves a list of all evaluated credit requests.

## 📖 API Documentation

The API documentation is available via Swagger UI. Access it at `http://localhost:8085/swagger-ui.html`.

## 🧪 Testing

To run the tests, execute the following command:

```bash
mvn test
```

## 🤝 Contributing

We welcome contributions! Follow these steps to contribute:

1. **Fork the repository.**
2. **Create a new branch (`git checkout -b feature/YourFeature`).**
3. **Commit your changes (`git commit -m 'Add some feature'`).**
4. **Push to the branch (`git push origin feature/YourFeature`).**
5. **Open a Pull Request.**

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 📧 Contact

For any inquiries or feedback, please contact:

- **Alejo2075** - alejsant75@gmail.com
- **GitHub**: [Alejo2075](https://github.com/Alejo2075)

---

Made with ❤️ by [Alejo2075](https://github.com/Alejo2075)
