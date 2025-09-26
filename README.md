# Store Project

## Overview
This project is a simple Java application that simulates a store management system.  
It demonstrates the usage of **OOP principles**, **collections**, and **unit testing with JUnit 5**.  

The system supports:
- Managing workers (hire, fire, find).
- Managing consumers and products.
- Creating stores with products and quantities.
- Generating random test data using utility classes.

---

## Project Structure
- **entity.impl**  
  Contains implementations of core domain classes:
  - `Worker` — represents an employee with a name and position.  
  - `Consumer` — represents a customer with a name and balance.  
  - `Product` — represents an item with a name and price.  
  - `Store` — represents a store with address, phone, email, and products.  

- **entity.enums**  
  - `Position` — enumeration for worker positions (e.g., SELLER, MANAGER).  

- **service**  
  - `WorkerService` — interface defining worker-related operations.  
  - `WorkerServiceImpl` — implementation of worker management.  
  - `ModelsUtils` — utility class for generating random objects, test data, and constants.  

- **test**  
  - `WorkerServiceImplTest` — JUnit 5 test class that validates `WorkerServiceImpl`.  

---

## Features
### Worker Management
- Hire a new worker.
- Fire a worker by name.
- Find a worker by name.
- Retrieve all hired workers.

### Utilities
- Generate random workers, products, and consumers for testing.  
- Predefined constants for test objects.  

---

## Technologies Used
- **Java 21+**
- **JUnit 5**
- **Maven**

---
