# Farm Management (Spring Boot + React)

A full-stack Java project for farm management where farmers can:
- Create farm-specific accounts
- Add crop expenses and revenue entries
- Track total profit per farm account

## Tech Stack
- Backend: Java 17, Spring Boot, Spring Data JPA, **MySQL**
- Frontend: React, Axios

## MySQL Setup
1. Install and run MySQL server.
2. Make sure credentials match `backend/src/main/resources/application.properties`:
   - DB: `farm_management`
   - Username: `root`
   - Password: `root`
3. You can change these values in `application.properties` to match your environment.

## Backend Setup
```bash
cd backend
mvn spring-boot:run
```

Backend runs at: `http://localhost:8080`

## Frontend Setup
```bash
cd frontend
npm install
npm start
```

Frontend runs at: `http://localhost:3000`

## API Endpoints
- `POST /api/farms` create farm account
- `GET /api/farms` list farm accounts
- `POST /api/farms/{accountId}/records` add crop record
- `GET /api/farms/{accountId}/records` list crop records
- `GET /api/farms/{accountId}/profit` get total profit
