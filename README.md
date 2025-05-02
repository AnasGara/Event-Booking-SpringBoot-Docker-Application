# 🎟️ Event Booking Platform

A full-featured event booking application built with **Spring Boot**, **Docker**, **Stripe**, and deployed on **AWS**, allowing event organizers to publish events and participants to register, pay securely, and receive unique access codes.

![GitHub repo size](https://img.shields.io/github/repo-size/AnasGara/Event-Booking-SpringBoot-Docker-Application)
![GitHub stars](https://img.shields.io/github/stars/AnasGara/Event-Booking-SpringBoot-Docker-Application)
![GitHub forks](https://img.shields.io/github/forks/AnasGara/Event-Booking-SpringBoot-Docker-Application)
![GitHub license](https://img.shields.io/github/license/AnasGara/Event-Booking-SpringBoot-Docker-Application)

---

## 🚀 Features

- 📅 **Event Publishing**: Organizers can create and manage events with customizable details.
- 👤 **User Registration**: Participants can sign up for events and manage their bookings.
- 💳 **Stripe Integration**: Secure online payment with automatic access code generation.
- 📧 **Email Notifications**: Confirmation emails sent after successful booking.
- 🔐 **JWT Authentication**: Role-based access control for users and admins.
- 🐳 **Dockerized**: Ready to deploy in any containerized environment.
- ☁️ **AWS Deployment**: Optimized for cloud scalability and reliability.

---

## 🛠 Tech Stack

| Backend         | Frontend (optional) | DevOps & Infrastructure |
|----------------|---------------------|--------------------------|
| Java 17        | Typescript | Docker |
| Spring Boot    |           Angular           | Jenkins |
| Spring Security|                     | AWS EC2 |
| Spring Data JPA|                     | Stripe |
| PostgreSQL     |                     | Jenkins |
| JWT            |                     | Docker |

---

## 📷 Screenshots


---

## 📦 Getting Started

### Prerequisites
- Java 17+
- Docker
- PostgreSQL
- Stripe account (for test API keys)

### Running Locally

```bash
# Clone the repository
git clone https://github.com/AnasGara/Event-Booking-SpringBoot-Docker-Application.git
cd Event-Booking-SpringBoot-Docker-Application

# Build and run with Docker
docker-compose up --build
