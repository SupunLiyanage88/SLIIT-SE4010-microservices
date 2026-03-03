Microservices Lab - SE4010
https://img.shields.io/badge/Java-21-orange.svg
https://img.shields.io/badge/Spring%2520Boot-3.2.3-brightgreen.svg
https://img.shields.io/badge/Docker-24.0-blue.svg
https://img.shields.io/badge/Postman-Collection-orange.svg

📋 Lab Overview
This project implements a complete microservices system with four independent services communicating through an API Gateway. Each service runs in its own Docker container and can be developed using different technology stacks.

System Architecture
text
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
🏗️ Project Structure
text
microservices-lab/
├── api-gateway/           # Spring Cloud Gateway
├── item-service/          # Item microservice
├── order-service/         # Order microservice
├── payment-service/       # Payment microservice
├── docker-compose.yml     # Docker composition
└── README.md              # This file
🚀 Technology Stack
Service	Technology	Port
API Gateway	Spring Cloud Gateway	8080
Item Service	Spring Boot 3.2.3	8081
Order Service	Spring Boot 3.2.3	8082
Payment Service	Spring Boot 3.2.3	8083
Java 21 - All services

Maven - Build tool

Docker & Docker Compose - Containerization

Spring Cloud Gateway - API routing

📦 Prerequisites
Java 21

Maven

Docker Desktop

Postman (for testing)

🔧 Installation & Setup
1. Clone the Repository
bash
git clone https://github.com/yourusername/SLIIT-SE4010-microservices.git
cd SLIIT-SE4010-microservices
2. Build the Services
bash
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
3. Run with Docker Compose
bash
docker-compose up --build
The services will start in this order:

✅ Item Service (port 8081)

✅ Order Service (port 8082)

✅ Payment Service (port 8083)

✅ API Gateway (port 8080)

4. Verify Services are Running
bash
docker-compose ps
Expected output:

text
NAME              STATUS          PORTS
api-gateway       Up              0.0.0.0:8080->8080/tcp
item-service      Up              0.0.0.0:8081->8081/tcp
order-service     Up              0.0.0.0:8082->8082/tcp
payment-service   Up              0.0.0.0:8083->8083/tcp
🛣️ API Endpoints
All endpoints are accessible through the API Gateway at http://localhost:8080

Item Service
Method	Endpoint	Description	Request Body
GET	/items	Get all items	-
POST	/items	Create new item	{"name": "Headphones", "price": 99.99}
GET	/items/{id}	Get item by ID	-
Order Service
Method	Endpoint	Description	Request Body
GET	/orders	Get all orders	-
POST	/orders	Create new order	{"item": "Laptop", "quantity": 2, "customerId": "C001"}
GET	/orders/{id}	Get order by ID	-
Payment Service
Method	Endpoint	Description	Request Body
GET	/payments	Get all payments	-
POST	/payments/process	Process payment	{"orderId": 1, "amount": 1299.99, "method": "CARD"}
GET	/payments/{id}	Get payment status	-
🧪 Testing with Postman
Postman Collection
https://run.pstmn.io/button.svg

Sample Test Workflow
1. Create an Item

json
POST http://localhost:8080/items
{
    "name": "Gaming Laptop",
    "price": 1499.99
}
2. Create an Order

json
POST http://localhost:8080/orders
{
    "item": "Gaming Laptop",
    "quantity": 1,
    "customerId": "C123"
}
3. Process Payment

json
POST http://localhost:8080/payments/process
{
    "orderId": 1,
    "amount": 1499.99,
    "method": "CREDIT_CARD"
}
4. Verify Data

bash
curl http://localhost:8080/items
curl http://localhost:8080/orders
curl http://localhost:8080/payments
Quick Test Script
Create a test.sh file:

bash
#!/bin/bash

echo "=== Testing Item Service ==="
curl -X POST http://localhost:8080/items \
  -H "Content-Type: application/json" \
  -d '{"name":"Headphones","price":99.99}'

echo -e "\n\n=== Getting All Items ==="
curl http://localhost:8080/items

echo -e "\n\n=== Creating Order ==="
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"item":"Laptop","quantity":2,"customerId":"C001"}'

echo -e "\n\n=== Processing Payment ==="
curl -X POST http://localhost:8080/payments/process \
  -H "Content-Type: application/json" \
  -d '{"orderId":1,"amount":1299.99,"method":"CARD"}'

echo -e "\n\n=== Testing Complete ==="
🐳 Docker Commands
Start all services
bash
docker-compose up
Start in background
bash
docker-compose up -d
Stop all services
bash
docker-compose down
View logs
bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f api-gateway
Rebuild a specific service
bash
docker-compose up --build api-gateway
📊 Monitoring & Debugging
Check service health
bash
curl http://localhost:8080/actuator/health
View container details
bash
docker inspect item-service | grep IPAddress
Test internal connectivity
bash
docker-compose exec api-gateway curl http://item-service:8081/items
🎯 Learning Outcomes Achieved
✅ Microservices Communication - Services communicate through API Gateway
✅ Containerization - Each service runs in Docker with Docker Compose
✅ REST API Design - Clean REST endpoints across all services
✅ API Gateway Pattern - Single entry point with routing rules
✅ Polyglot Ready - Architecture supports different technology stacks
✅ Testing - Comprehensive Postman test collection

⚠️ Troubleshooting
Common Issues & Solutions
1. Port already in use

bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
2. Services not starting

bash
docker-compose down
docker-compose build --no-cache
docker-compose up
3. Cannot connect to services

bash
# Check if services are running
docker-compose ps

# Check logs
docker-compose logs -f

# Test direct connection
curl http://localhost:8081/items
curl http://localhost:8082/orders
curl http://localhost:8083/payments
4. API Gateway 404 errors

bash
# Check gateway configuration
docker-compose exec api-gateway cat /app/application.yml

# Test internal connectivity
docker-compose exec api-gateway curl http://item-service:8081/items
📝 Submission Requirements
Complete working microservices system

Docker setup with docker-compose.yml

All required API endpoints implemented

Postman collection with tests

Public GitHub repository

Comprehensive README file
