# Hardware Shop Management System

**Name:** Ishimwe Obed  
**Student ID:** 26185  
**Project:** Hardware Shop Management System  
**Course:** Web Technology and Internet


---

## Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 17+

---

## Database Setup

Open pgAdmin or psql and run:

```sql
CREATE DATABASE hardware_shop_db;
```

---

## Configure Credentials

Open `src/main/resources/application.properties` and set your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hardware_shop_db
spring.datasource.username=postgres
spring.datasource.password=admin
```

---

## How to Run

```bash
mvn spring-boot:run
```

The server starts on `http://localhost:8080`.

---

## APIs Summary

| ID  | Method | Endpoint                                              | Description                        |
|-----|--------|-------------------------------------------------------|------------------------------------|
| A2  | POST   | /api/product-groups/save                              | Save product group                 |
| A2  | GET    | /api/product-groups/all                               | Get all product groups             |
| A2  | GET    | /api/product-groups/{id}                              | Get product group by id            |
| A2  | PUT    | /api/product-groups/update/{id}                       | Update product group               |
| A2  | DELETE | /api/product-groups/delete/{id}                       | Delete product group               |
| A2  | POST   | /api/counters/save                                    | Save counter                       |
| A2  | GET    | /api/counters/all                                     | Get all counters                   |
| A2  | GET    | /api/counters/{id}                                    | Get counter by id                  |
| A2  | PUT    | /api/counters/update/{id}                             | Update counter                     |
| A2  | DELETE | /api/counters/delete/{id}                             | Delete counter                     |
| A2  | POST   | /api/salespersons/save                                | Save salesperson                   |
| A2  | GET    | /api/salespersons/all                                 | Get all salespersons               |
| A2  | GET    | /api/salespersons/{id}                                | Get salesperson by id              |
| A2  | PUT    | /api/salespersons/update/{id}                         | Update salesperson                 |
| A2  | DELETE | /api/salespersons/delete/{id}                         | Delete salesperson                 |
| A2  | POST   | /api/customers/save                                   | Save customer                      |
| A2  | GET    | /api/customers/all                                    | Get all customers                  |
| A2  | GET    | /api/customers/{id}                                   | Get customer by id                 |
| A2  | PUT    | /api/customers/update/{id}                            | Update customer                    |
| A2  | DELETE | /api/customers/delete/{id}                            | Delete customer                    |
| A2  | POST   | /api/sales-orders/save                                | Save sales order                   |
| A2  | GET    | /api/sales-orders/all                                 | Get all sales orders               |
| A2  | GET    | /api/sales-orders/{id}                                | Get sales order by id              |
| A2  | PUT    | /api/sales-orders/update/{id}                         | Update sales order                 |
| A2  | DELETE | /api/sales-orders/delete/{id}                         | Delete sales order                 |
| B1  | GET    | /api/customers/by-last-name?lastName=                 | Customers by last name             |
| B2  | GET    | /api/sales-orders/by-status?status=                   | Orders by status                   |
| B3  | GET    | /api/sales-orders/between?start=&end=                 | Orders between dates               |
| C1  | GET    | /api/salespersons/by-product-group?name=              | Salespersons by product group      |
| C2  | GET    | /api/counters/free                                    | Free counters                      |
| C3  | GET    | /api/product-groups/unused                            | Unused product groups              |
| C4  | GET    | /api/customers/of-salesperson/{salespersonId}         | Customers of a salesperson         |
| D1  | POST   | /api/sales-orders/save                                | Save with business rules           |
| D2  | PATCH  | /api/sales-orders/{id}/status?status=                 | Update order status                |
| D3  | PUT    | /api/salespersons/{id}/product-groups/{pgId}          | Add product group to salesperson   |
| D4  | PATCH  | /api/salespersons/{id}/counter?counterNumber=         | Change salesperson counter         |
| E1  | POST   | /api/sold-items/save?salesOrderId=                    | Save sold item                     |
| E2  | GET    | /api/sold-items/customer/{phone}                      | Sold items by customer phone       |
| E3  | DELETE | /api/sold-items/delete/{id}                           | Delete sold item                   |
| F1  | GET    | /api/sales-orders/stats/by-status                     | Order count grouped by status      |
| F2  | PATCH  | /api/sales-orders/cancel-day?salespersonId=&date=     | Bulk cancel orders for a day       |
| +   | GET    | /api/dashboard/summary                                | Dashboard summary                  |
| +   | GET    | /api/sales-orders/page?page=&size=&sort=              | Paginated sales orders             |
