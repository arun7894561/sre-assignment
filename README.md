# SRE Assignment – Backend + Platform Engineering

## Part 1 – User Metadata Service
APIs:
POST /user
GET /user/{id}

Features:
- Idempotency
- Retry with exponential backoff
- Circuit breaker
- Metrics
- Logging with request ID
- Docker containerization

## Part 2 – Deployment Portal Backend

API:
POST /deploy/register-service

Inputs:
- service_name
- team_name
- repo_url

Automation via Terraform:
- Creates ECR repository
- Creates IAM role
- Generates Kubernetes deployment template
- Generates CI pipeline template

## Tech Stack
Java
Spring Boot
Terraform
Docker
AWS (ECR, IAM)
Jenkins / GitLab CI

## Running the Project

Run backend:

mvn spring-boot:run

Test API:

POST http://localhost:8080/deploy/register-service

## Terraform

cd terraform
terraform init
terraform plan

Note:
AWS credentials are required to apply infrastructure.
