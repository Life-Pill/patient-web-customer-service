# LifePill Web App for Patients

## 🚀 Customer Service

### Quick Start

#### Prerequisites
- Java 17+
- Maven 3.9+
- Docker & Docker Compose (for containerized deployment)
- PostgreSQL 15+
- MongoDB 7+

#### Running Locally

**Option 1: Using Maven**
```bash
cd CustomerService/customer-service
./mvnw spring-boot:run
```

**Option 2: Using Docker**
```bash
cd CustomerService/customer-service
docker build -t lifepill-customer-service .
docker run -p 8070:8070 \
  -e DB_DATABASE_URL=jdbc:postgresql://your-host:5432/lifepill \
  -e DB_USER=your_user \
  -e DB_PASSWORD=your_password \
  -e MDB_CONNECTION_STRING=mongodb://your-mongo-host:27017/lifepill \
  -e STRIPE_SECRET_KEY=your_stripe_key \
  lifepill-customer-service
```

**Option 3: Using Docker Compose (Recommended for Development)**
```bash
cd CustomerService/customer-service
docker-compose up -d
```

The service will be available at `http://localhost:8070`

### API Documentation
Once the service is running, access the Swagger UI at:
- http://localhost:8070/swagger-ui.html

### Building the Project

```bash
cd CustomerService/customer-service
./mvnw clean package
```

### Running Tests

```bash
cd CustomerService/customer-service
./mvnw test
```

### Environment Variables

Create an `env.properties` file with:

```properties
DB_DATABASE_URL=jdbc:postgresql://localhost:5432/lifepill
DB_USER=your_db_user
DB_PASSWORD=your_db_password
MDB_CONNECTION_STRING=mongodb://localhost:27017/lifepill
STRIPE_SECRET_KEY=your_stripe_secret_key
```

### CI/CD Pipeline

The project includes a GitHub Actions workflow that automatically:
1. Builds and tests the application
2. Creates a Docker image
3. Pushes the image to GitHub Container Registry
4. (Optional) Deploys to production

Configure GitHub Secrets for deployment: `SERVER_HOST`, `SERVER_USER`, `SSH_PRIVATE_KEY`, `DB_DATABASE_URL`, `DB_USER`, `DB_PASSWORD`, `MDB_CONNECTION_STRING`, `STRIPE_SECRET_KEY`

```docker exec lifepill-postgres psql -U postgres -c "CREATE DATABASE branch_service_db;" && \
docker exec lifepill-postgres psql -U postgres -c "CREATE DATABASE inventory_service_db;" && \
docker exec lifepill-postgres psql -U postgres -c "CREATE DATABASE customer_service_db;" && \
docker exec lifepill-postgres psql -U postgres -c "CREATE DATABASE identity_service_db;" && \
docker exec lifepill-postgres psql -U postgres -c "CREATE DATABASE mobile_user_auth_db;" && \
echo "All databases created"
```