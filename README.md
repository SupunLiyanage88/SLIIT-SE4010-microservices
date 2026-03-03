# Microservices Lab - SE4010

📋 Lab Overview

This project implements a complete microservices system with four independent services communicating through an API Gateway. Each service runs in its own Docker container and can be developed using different technology stacks.

## System Architecture

Client (Port 8080)
        │
        ▼
┌───────────────┐
│  API Gateway  │  Spring Cloud Gateway
│   (Port 8080) │
└───────────────┘
        │
        ├──────────► Item Service (Port 8081)
        │            ├─ GET /items
        │            ├─ POST /items
        │            └─ GET /items/{id}
        │
        ├──────────► Order Service (Port 8082)
        │            ├─ GET /orders
        │            ├─ POST /orders
        │            └─ GET /orders/{id}
        │
        └──────────► Payment Service (Port 8083)
                     ├─ GET /payments
                     ├─ POST /payments/process
                     └─ GET /payments/{id}

## Project Structure
microservices-lab/
├── api-gateway/           # Spring Cloud Gateway
├── item-service/          # Item microservice
├── order-service/         # Order microservice
├── payment-service/       # Payment microservice
├── docker-compose.yml     # Docker composition
└── README.md              # Project documentation

## Technology Stack
| Service         | Technology           | Port |
| --------------- | -------------------- | ---- |
| API Gateway     | Spring Cloud Gateway | 8080 |
| Item Service    | Spring Boot 3.2.3    | 8081 |
| Order Service   | Spring Boot 3.2.3    | 8082 |
| Payment Service | Spring Boot 3.2.3    | 8083 |

Java 21 – All services
Maven – Build tool
Docker & Docker Compose – Containerization
Spring Cloud Gateway – API routing

## Prerequisites

Java 21
Maven
Docker Desktop
Postman

## Installation & Setup

### Clone the Repository
git clone https://github.com/yourusername/SLIIT-SE4010-microservices.git
cd SLIIT-SE4010-microservices

### Build the Services
# Build Item Service
cd item-service
mvn clean package
cd ..

# Build Order Service
cd order-service
mvn clean package
cd ..

# Build Payment Service
cd payment-service
mvn clean package
cd ..

# Build API Gateway
cd api-gateway
mvn clean package
cd ..

### Run with Docker Compose
docker-compose up --build

Services will start in this order:

 Item Service (port 8081)
 Order Service (port 8082)
 Payment Service (port 8083)
 API Gateway (port 8080)

### Verify Services are Running
docker-compose ps

## API Endpoints
http://localhost:8080\

🛍️ Item Service
Method	Endpoint	Description	Request Body
GET	/items	Get all items	-
POST	/items	Create new item	{"name": "Headphones", "price": 99.99}
GET	/items/{id}	Get item by ID	-
📦 Order Service
Method	Endpoint	Description	Request Body
GET	/orders	Get all orders	-
POST	/orders	Create new order	{"item": "Laptop", "quantity": 2, "customerId": "C001"}
GET	/orders/{id}	Get order by ID	-
💳 Payment Service
Method	Endpoint	Description	Request Body
GET	/payments	Get all payments	-
POST	/payments/process	Process payment	{"orderId": 1, "amount": 1299.99, "method": "CARD"}
GET	/payments/{id}	Get payment status	-


