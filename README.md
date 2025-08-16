
# 🏨 AinBnB – Hotel Booking Platform Backend

A full-featured **Airbnb-like hotel booking backend** built with **Spring Boot** and **PostgreSQL**, featuring **JWT authentication, role-based access control, payment integration, and clean REST APIs**.

This project demonstrates production-ready backend architecture, scalability, and real-world feature implementation.

---

## ✨ Features

✅ **Authentication & Authorization**

- JWT-based authentication with refresh tokens
- Role-based access control (Admin, User)
- Secure endpoints with Spring Security

✅ **Hotel & Room Management (Admin)**

- Create, update, delete hotels and rooms
- Manage room inventory and availability
- Generate hotel reports & view booking stats

✅ **Hotel Browsing & Booking (User)**

- Search hotels with filters (location, dates, guests, rooms)
- View hotel details & room availability
- Initialize bookings, add guests, cancel or confirm bookings

✅ **Payment Integration**

- Stripe payment gateway for booking checkout
- Refunds & session-based payment verification via webhooks

✅ **Documentation & Testing**

- Interactive API testing with **Swagger/OpenAPI 3.1**
- Clear DTOs and request/response models

---

## 🛠 Tech Stack

- **Backend:** Spring Boot, Spring Security, Spring Data JPA
- **Database:** PostgreSQL
- **Authentication:** JWT (Access + Refresh Tokens)
- **Payments:** Stripe API + Webhooks
- **API Docs:** Swagger / OpenAPI 3.1
- **Utilities:** ModelMapper, Lombok, Logback
- **Build Tool:** Maven

---

## 📚 API Endpoints (Highlights)

### 🔑 Authentication

- `POST /auth/signup` → Register new user
- `POST /auth/login` → Login & receive JWT
- `POST /auth/refresh` → Refresh active JWT token using refresh token

### 🏨 Hotels (Admin)

- `POST /admin/hotels` → Create hotels
- `PATCH /admin/hotels/{hotelId}` → Update hotel info
- `DELETE /admin/hotels/{hotelId}` → Delete hotel

### 🛏 Rooms & Inventory

- `POST /admin/hotels/{hotelId}/rooms` → Add room to hotels
- `PUT /admin/inventory/rooms/{roomId}` → Update room inventory

### 📖 Booking

- `POST /bookings/init` → Initialize booking
- `POST /bookings/{bookingId}/payments` → Complete payment
- `POST /bookings/{bookingId}/cancel` → Cancel booking

### 💳 Payments

- `POST /webhook/payment` → Stripe webhook for success/failure

### 👤 User

- `GET /users/profile` → Get user profile
- `GET /users/myBookings` → Get all user booking

## Booking Flow

<img width="1748" height="837" alt="diagram-export-8-16-2025-4_59_34-PM" src="https://github.com/user-attachments/assets/05b14a3e-fff5-4bab-84d7-86f269869770" />


---

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/priyanshu798/AirBnb-Backend.git
cd AirBnb-Backend

```

### 2️⃣ Configure Environment

Set up `application.yml` with your database and Stripe keys:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/airbnb
    username: postgres
    password: yourpassword
stripe:
  secret-key: your_stripe_secret_key
  webhook-secret: your_stripe_webhook_key

```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run

```

The app will start on [**http://localhost:8080/api/v1**](http://localhost:8080/api/v1)

### 4️⃣ Explore Swagger Docs

Visit:

👉 http://localhost:8080/api/v1/v3/api-docs

👉 http://localhost:8080/swagger-ui/index.html

---
