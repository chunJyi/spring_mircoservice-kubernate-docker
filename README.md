Spring Boot Microservices with Docker & Kubernetes

A Spring Boot Microservices architecture deployed using Docker and
Kubernetes. This project demonstrates how to build, containerize, and
orchestrate multiple microservices with modern cloud-native tools.

Technologies Used - Java 21 - Spring Boot - Docker - Kubernetes -
PostgreSQL - RabbitMQ - Maven - Jib Maven Plugin

Architecture This project follows a Microservices Architecture where
each service runs independently and communicates with others.

Client | v Customer Service | | REST Call v Fraud Service | | Message
Queue v RabbitMQ | v Notification Service

Microservices

Customer Service - Handles customer registration - Stores customer
data - Calls Fraud Service to check fraud status

Fraud Service - Performs fraud detection - Returns fraud check result

Notification Service - Sends notification messages - Consumes messages
from RabbitMQ

Docker Each microservice is packaged as a Docker container.

Build Docker image: mvn compile jib:dockerBuild

Kubernetes Deployment

Apply Kubernetes configuration: kubectl apply -f k8s/

Check pods: kubectl get pods

Check services: kubectl get svc

PostgreSQL Database used for storing service data.

Example configuration: username: postgres password: root port: 5432

RabbitMQ Used for asynchronous communication between microservices.

Example flow: Customer Service -> RabbitMQ Queue -> Notification Service

Running Locally

1.  Start Minikube minikube start

2.  Build Docker Images mvn clean compile jib:dockerBuild

3.  Deploy to Kubernetes kubectl apply -f k8s/

4.  Check Pods kubectl get pods

5.  Check Services kubectl get svc

Project Structure

spring_mircoservice-kubernate-docke | |– customer |– fraud |–
notification |– k8s | |– postgres | |– rabbitmq | |– services | |–
docker-compose.yml |– README.md

Author Developed by Chun
