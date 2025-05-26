# 🏥 Patient Management Microservices

A **production-ready** Java-based Patient Management System built using **Spring Boot** and **Microservice Architecture**. This project handles core healthcare workflows such as **patient record management**, **authentication**, **billing**, and **auditing** — all deployed and tested with Docker and AWS (LocalStack).

---

## 🚀 Tech Stack

- **Microservices** using Spring Boot 3.44
- **REST APIs** with JWT-based Authentication
- **gRPC** with Protocol Buffers
- **Apache Kafka** for asynchronous communication
- **Docker** for containerization
- **AWS CloudFormation** via LocalStack for Infrastructure as Code (IaC)
- **Rest Assured** for API Integration Testing

---

## 🧩 Microservices Overview

| Service              | Description                                                                |
|----------------------|----------------------------------------------------------------------------|
| `auth-service`       | Handles user login/authentication using **JWT tokens**                     |
| `patient-service`    | Manages patient data: **create, retrieve, update**                         |
| `analytics-service`  | Tracks and audits activities, integrates with **Kafka & gRPC**             |
| `billing-service`    | Generates bills and handles transactions via **Kafka & gRPC**              |
| `api-gateway`        | Validates and filters requests; serves as the unified API entry point      |
| `integration-tests`  | A separate service for **integration testing** using **Rest Assured**      |
| `infrastructure`     | Generates **AWS CloudFormation templates** to deploy services on LocalStack|

---

## 🔐 Authentication Flow

- Users authenticate through `auth-service` using **REST API**
- JWT token is issued on successful login
- Protected services like `patient-service` require a valid JWT token in the request header

---

## 📡 Communication Protocols

- **REST API** for external clients
- **gRPC** for internal microservice communication and for fast perfomance (patient ↔ analytics, billing ↔ analytics)
- **Kafka** is used for message-driven asynchronous events between services

---

## 🧪 Testing

- Integration testing is handled via a dedicated `integration-tests` using **Rest Assured**
- Tests include service communication, data flow, and token-based authorization

---

## 🐳 Docker Setup

```bash
# Build Docker images
...
docker build -t auth-service:latest .
docker build -t patient-service:latest .
docker build -t analytics-service:latest .
docker build -t billing-service:latest .
```
---
## ☁️ AWS Deployment with LocalStack
The infrastructure-service generates CloudFormation YAML templates

These templates are used to deploy services in AWS LocalStack, simulating a real AWS environment for testing infrastructure as code.

---

## ✅ Features
JWT-secured Authentication

Distributed Microservices

Audit & Billing via Kafka and gRPC

API Gateway-based request validation

Infrastructure automation with CloudFormation

End-to-end integration testing

Dockerized for local testing

Cloud-ready via LocalStack

---

## 📬 Contact
- Created by **Arunachalam S**

- 📧 LinkedIn - [www.linkedin.com/in/arunachalam-s-javadeveloper]
