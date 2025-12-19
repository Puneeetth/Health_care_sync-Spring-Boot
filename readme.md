📌 User Management Service (Spring Boot + MongoDB)
Overview

This project implements a production-ready User Management Service using Spring Boot and MongoDB, following real-world backend design principles. The service exposes RESTful APIs to create, read, update, and delete users with proper validation, uniqueness constraints, and clean API semantics.

Phase 1 – Project Setup

Initialized Spring Boot application with MongoDB integration

Configured layered architecture (Controller, Service, Repository)

Defined core domain entities and DTOs

Phase 2 – User Creation

Implemented POST /users API

Enforced unique email and phone using repository checks and database indexes

Generated MongoDB ObjectId as primary key

Introduced a sequential business identifier (userNumber) using an atomic counter

Set default user status to ACTIVE during creation

Phase 3 – User Retrieval

Implemented GET /users/{id} API

Handled “user not found” cases using Optional and fail-fast exceptions

Phase 4 – User Update (Partial Update)

Implemented PATCH /users/{id} API

Supported partial updates without overwriting existing data

Validated email and phone uniqueness excluding the current user

Preserved system-controlled fields such as status and userNumber

Phase 5 – User Deletion (Soft Delete)

Implemented DELETE /users/{id} API

Performed soft deletion by updating user status instead of physical removal

Returned proper HTTP status codes (204 No Content)

Design Principles Followed

RESTful API conventions

Fail-fast validation

Database as the source of truth for uniqueness

Separation of concerns

Production-ready error handling