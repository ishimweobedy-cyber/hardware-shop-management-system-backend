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

## Seed Data Used During Testing

### Product Groups

| ID | Name |
|----|------|
| 23cc3e8c-52c6-4721-88c5-0377a6d47d79 | Plumbing |
| 3bb6c14d-8863-43ad-a311-7354d22f8c81 | Electrical |
| 8d255c93-3e9e-4cd9-993d-04b793587b3d | Paints |
| c600038f-0168-42e9-a311-27076a2f054c | Hand Tools |

---

### Counters

| ID | Number | Name |
|----|--------|------|
| e49df622-552a-4ea8-a7d2-a28d0bbcfcc6 | 1 | Counter One |
| 068af84c-3c79-4e27-b8b4-e023f3cfed65 | 2 | Counter Two |
| 6623cf9c-0317-4ef3-96c6-2dc3549c028c | 3 | Counter Three |
| 8c9320ba-7a3f-4c4d-a29c-e51769e3aa1c | 4 | Counter Four |

---

### Salespersons

| ID | First Name | Last Name | Phone | Counter | Product Groups |
|----|-----------|-----------|-------|---------|----------------|
| 2633a94b-4ced-4476-af6b-125113fb87bb | Agasaro | Aline | 0788454675 | Counter One | Plumbing, Electrical |
| 76cc3c20-b116-434e-b535-037374686093 | Ishimwe | Obed | 0788377914 | Counter Two | Paints |
| ce7f87ae-a718-4cfe-8f75-636d6a335c06 | Ishimwe | Melissa | 0780138022 | Counter Three | Hand Tools (added via D3) |

---

### Customers

| ID | First Name | Last Name | Phone | Email |
|----|-----------|-----------|-------|-------|
| 922415ca-fc39-4628-b818-64b4c1c1dfe4 | Jean | Ishimwe | 0786999221 | jean@gmail.com |
| 9aae46ab-f3f6-4588-a1de-a2e022bebf56 | Marie | Ishimwe | 0783214568 | marie@gmail.com |
| 40a8dc8d-6647-4f72-bc62-beb6d1a26700 | Peter | Mugisha | 0783214569 | peter@gmail.com |
| a8684928-68a3-4694-9e2d-6cb0e66ca4dc | Grace | Uwera | 0783214570 | grace@gmail.com |
| e23683e7-ee50-45da-ab9d-d5a745c0c83f | David | Nkusi | 0783214571 | david@gmail.com |

---

### Sales Orders

| ID | Reference | Date | Status | Total | Customer | Salesperson |
|----|-----------|------|--------|-------|----------|-------------|
| 907c8a34-1761-4e69-b5af-ce83af62c204 | REF-001 | 2026-10-28 | DELIVERED | 160.0 | Jean Ishimwe | Agasaro Aline |
| aa3f0320-0c19-477e-9e41-f19181842329 | REF-002 | 2026-10-29 | DELIVERED | 200.0 | Jean Ishimwe | Ishimwe Obed |
| 16e967c9-e5d7-409d-87e7-428d3d2f3b1a | REF-003 | 2026-10-30 | REJECTED | 300.0 | Jean Ishimwe | Agasaro Aline |
| 11e5159b-f2ab-4909-a157-47f5d774517d | REF-004 | 2026-10-31 | REJECTED | 120.0 | Marie Ishimwe | Agasaro Aline |
| b3acbc0a-168f-438a-8d46-24cc0562af7b | REF-005 | 2026-11-01 | PLACED | 180.0 | Peter Mugisha | Ishimwe Obed |
| 420dbd82-bcbf-4f09-be49-fe580437d826 | REF-006 | 2026-11-02 | PLACED | 220.0 | Peter Mugisha | Ishimwe Melissa |
| 3c503427-5b2b-4482-80e5-f0c11b697387 | REF-007 | 2026-11-03 | PLACED | 400.0 | Grace Uwera | Agasaro Aline |
| 2b1ab278-b371-4e08-bdfe-ad9392437fe3 | REF-008 | 2026-11-04 | PLACED | 250.0 | Grace Uwera | Ishimwe Obed |
| 5d05779d-958d-4f1e-8213-663ad6cb4034 | REF-009 | 2026-11-05 | PLACED | 310.0 | David Nkusi | Ishimwe Melissa |
| a7600de2-a8f6-4a26-b376-348847e0c5bd | REF-010 | 2026-11-06 | PLACED | 175.0 | David Nkusi | Agasaro Aline |

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
