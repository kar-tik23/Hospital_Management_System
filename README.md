# Smart Hospital Queue Management System

A full-stack hospital appointment and live queue management web application built using Spring Boot, Spring Security, Thymeleaf, and MySQL.

This system allows patients to book appointments with doctors, generates queue tokens automatically, and provides separate dashboards for users and administrators.

---

# 🚀 Features

## 👤 User Features
- User Registration & Login
- Secure Password Encryption using BCrypt
- Book Doctor Appointments
- Prevent Duplicate Slot Booking
- View Personal Appointments
- Live Queue Tracking
- Token-Based Appointment System

---

## 👨‍💼 Admin Features
- Admin Login
- View All Appointments
- Mark Appointment as Completed
- Cancel Appointments
- Queue Management

---

## 🧠 Smart Backend Logic
- Prevent Booking in Past Dates
- Prevent Double Booking for Same Time Slot
- Role-Based Authorization
- Automatic Token Number Generation
- Real-Time Queue Refresh
- Protected Routes using Spring Security

---

# 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database ORM |
| Hibernate | ORM Provider |
| Thymeleaf | Server-Side Rendering |
| MySQL | Database |
| Maven | Dependency Management |
| HTML/CSS | Frontend |

---

# 📁 Project Structure

```text
src
 └── main
      ├── java
      │     └── com.project
      │           ├── controller
      │           ├── model
      │           ├── repository
      │           ├── service
      │           └── config
      │
      └── resources
            ├── templates
            │      ├── admin
            │      ├── user
            │      └── auth
            │
            ├── static
            └── application.properties
```

---

# ⚙️ Prerequisites

Install the following before running the project:

- Java 21+
- Maven
- MySQL Server
- IntelliJ IDEA / VS Code

---

# 🗄️ Database Setup

## Step 1 — Open MySQL

Login to MySQL:

```sql
mysql -u root -p
```

---

## Step 2 — Create Database

```sql
CREATE DATABASE hospital_db;
```

---

# ⚙️ Configure Application Properties

Open:

```text
src/main/resources/application.properties
```

Add:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.thymeleaf.cache=false
```

---

# ▶️ Run The Project

## Using IntelliJ
Run:

```text
AppointmentApplication.java
```

---

## Using Terminal

```bash
mvn spring-boot:run
```

---

# 🔐 Default Roles

The project supports:

| Role | Access |
|---|---|
| USER | Book appointments |
| ADMIN | Manage appointments |

---

# 👨‍💼 Creating Admin User

Passwords are encrypted using BCrypt.

Generate encrypted password:

```java
System.out.println(
    new BCryptPasswordEncoder().encode("admin123")
);
```

Copy generated hash and insert manually into MySQL:

```sql
INSERT INTO user(username,password,role)
VALUES(
'admin',
'PASTE_BCRYPT_HASH',
'ADMIN'
);
```

---

# 🌐 Application URLs

| URL | Description |
|---|---|
| `/register` | User Registration |
| `/login` | Login Page |
| `/user/dashboard` | User Dashboard |
| `/admin/dashboard` | Admin Dashboard |
| `/user/book` | Book Appointment |
| `/user/my` | User Appointments |
| `/user/queue/{doctorId}` | Live Queue View |

---

# 🔄 Queue System

The application uses a token-based queue mechanism.

Example:

```text
Current Token: 5
Waiting Queue:
6
7
8
```

When an appointment is completed:
- next token automatically becomes active
- queue refreshes every few seconds

---

# 🔒 Security Features

- Password Encryption using BCrypt
- Spring Security Authentication
- Route Protection
- Role-Based Access Control
- Session-Based Authentication

---

# 🚧 Future Improvements

- WebSocket Real-Time Updates
- Doctor Dashboard
- Medical Records Module
- Billing & Invoice System
- Email Notifications
- Prescription Uploads
- Analytics Dashboard
- Glassmorphism UI

---

# 📸 Screenshots

_Add project screenshots here later._

---

# 🤝 Contributing

Pull requests are welcome.

For major changes:
- create a feature branch
- commit changes properly
- open a pull request

---

# 📄 License

This project is created for educational and portfolio purposes.

---

# 👨‍💻 Author

Kartik Ravikiran Suttraway

GitHub: https://github.com/kar-tik23