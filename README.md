# 🎟️ Backend Tickets – Fullstack App

Proyecto de gestión de **tickets** desarrollado con **Spring Boot 3 + GraphQL + Swagger/OpenAPI**, acompañado de un **frontend Angular** y con soporte para **Docker**.

---

## 📑 Tabla de Contenidos
1. [Requisitos](#-requisitos)
2. [Ejecución del Backend](#-ejecución-del-backend)
3. [Ejecución del Frontend](#-ejecución-del-frontend)
4. [Ejecución con Docker](#-ejecución-con-docker)
5. [Swagger – Documentación de la API](#-swagger--documentación-de-la-api)
6. [Colección de Postman](#-colección-de-postman)
7. [GraphQL Playground](#-graphql-playground)
8. [Autor](#-autor)

---

## ✅ Requisitos

Asegúrate de tener instalados:

- ☕ **Java 17+**
- 🐘 **Maven 3.9+**
- 🐳 **Docker + Docker Compose**
- 🌐 **Node.js 18+** y **Angular CLI**

---

## 🚀 Ejecución del Backend

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/backend_tickets.git
   cd backend_tickets

2. Clonar el repositorio:
   ```bash
   mvn clean package -DskipTests

3. Ejecutar el JAR:
   ```bash
   java -jar target/backend_tickets-0.0.1-SNAPSHOT.jar

4. El backend estará disponible en:
   ```bash
   http://localhost:8080


## 🚀 Ejecución del Frontend

1. Ir al directorio del frontend:
   ```bash
   cd frontend_tickets

2. Instalar dependencias:
   ```bash
   npm install

3. Ejecutar en modo desarrollo:
   ```bash
   ng serve

4. El frontend quedará disponible en:
   ```bash
   http://localhost:4200


## 🚀 Ejecución con Docker

1. Construir la imagen del backend:
   ```bash
   docker build -t backend-tickets .

2. Levantar el contenedor:
   ```bash
   docker run -p 8080:8080 backend-tickets

3. (Opcional) Usar Docker Compose para backend + base de datos:
   ```bash
   docker-compose up --build


## 🚀 Swagger – Documentación de la API

Una vez levantado el backend, accede a la documentación interactiva en:

[Swagger](http://localhost:8080/swagger-ui.html)


## 🚀 Colección de Postman

1. Abre Postman.

2. Clic en Import → Upload Files.

3. Selecciona el archivo:
    ```bash
   docs/postman/BackendTickets.postman_collection.json

4. Ejecuta las requests preconfiguradas.


## 🚀 GraphQL Playground

1. El backend también expone GraphQL en:

    ```bash
    POST http://localhost:8080/graphql

## 🚀 Autor

# Edyson Fabian Leal Marin
Ingeniero de Sistemas – Desarrollador Fullstack 🚀