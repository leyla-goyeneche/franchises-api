# franchises-api

API REST desarrollada en **Spring Boot** para la gestión de **Franquicias**, **Sucursales** y **Productos** (prueba técnica Backend).  
Incluye endpoints para crear franquicias/sucursales/productos, actualizar stock y consultar el producto con mayor stock por sucursal.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.x
- Maven Wrapper (`mvnw`)
- Spring Web
- Spring Data JPA
- MySQL 8 (Docker Compose)

---

## 📁 Estructura del proyecto

src/main/java/com/nequi/franchises
├── api
│ ├── controller
│ └── dto
├── application
│ └── service
├── domain
│ └── exception
├── infrastructure
│ └── persistence
│ ├── entity
│ └── repository
└── shared
└── error



---

## ✅ Requisitos

- JDK 17
- Docker Desktop (para MySQL)
- Git

Validar Java:

```bash
java -version


🐳 Levantar MySQL con Docker
En la raíz del proyecto:

1. docker compose up -d
2. docker ps

MySQL queda disponible en localhost:3307 (host) → 3306 (container).

⚙️ Configuración

La aplicación usa configuración en src/main/resources/application.yml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/franchises_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: root


▶️ Ejecutar la aplicación (local)
./mvnw clean spring-boot:run

----La API quedará disponible en:

http://localhost:8080 -----

🧪 Ejecutar build / tests

./mvnw clean test


🔌 Endpoints principales

Franquicias

POST /api/v1/franchises → Crear franquicia

POST /api/v1/franchises/{franchiseId}/branches → Crear sucursal en franquicia

GET /api/v1/franchises/{franchiseId}/max-stock-products → Producto con más stock por sucursal (por franquicia)

PATCH /api/v1/franchises/{franchiseId} → Renombrar franquicia (plus)

Sucursales

POST /api/v1/branches/{branchId}/products → Crear producto en sucursal

DELETE /api/v1/branches/{branchId}/products/{productId} → Eliminar producto de sucursal

PATCH /api/v1/branches/{branchId} → Renombrar sucursal (plus)

Productos

PATCH /api/v1/products/{productId}/stock → Actualizar stock

PATCH /api/v1/products/{productId} → Renombrar producto (plus)

## ✅ Quick test (PowerShell)

```powershell
# 1) Crear franquicia
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/franchises" -ContentType "application/json" -Body '{"name":"Franquicia 1"}'

# 2) Crear sucursal (reemplazar FRANCHISE_ID)
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/franchises/{FRANCHISE_ID}/branches" -ContentType "application/json" -Body '{"name":"Sucursal A"}'

# 3) Crear producto (reemplazar BRANCH_ID)
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/branches/{BRANCH_ID}/products" -ContentType "application/json" -Body '{"name":"Producto X","stock":10}'

# 4) Actualizar stock (reemplazar PRODUCT_ID)
Invoke-RestMethod -Method Patch -Uri "http://localhost:8080/api/v1/products/{PRODUCT_ID}/stock" -ContentType "application/json" -Body '{"stock":25}'

# 5) Max stock por sucursal (reemplazar FRANCHISE_ID)
Invoke-RestMethod -Method Get -Uri "http://localhost:8080/api/v1/franchises/{FRANCHISE_ID}/max-stock-products"





👤 Autor

Leyla Goyeneche