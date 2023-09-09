# D24 Hostel Management System

The D24 Hostel Management System is a Java-based application designed to efficiently manage students, rooms, and
reservations for the D24 Hostel. It utilizes JavaFX and CSS for the frontend, MySQL as the database, and Hibernate as
the Object-Relational Mapping (ORM) framework.

---

## Features

- **Student Management**: Allows CRUD operations for managing student records, including creating, reading, updating,
  and deleting student information. Students can be registered with their personal details, including name, contact
  information, and campus student ID.
  <br><br>
- **Room Management**: Provides CRUD operations for managing room details, including room types, availability, and
  capacity. Hostel staff can easily add, update, or remove rooms from the system, ensuring accurate information about
  available accommodations.
  <br><br>
- **Reservation Management**: Facilitates the reservation process by allowing students to request room reservations for
  specific time periods. Hostel staff can review and approve these reservations, ensuring efficient allocation of
  available rooms to students.
  <br><br>
- **Payment Tracking**: Tracks the payment details of students, including key money payments and monthly fees. Hostel
  staff can easily monitor payment statuses, identify outstanding balances, and generate payment reports.
  <br><br>
- **User Authentication and Access Control**: Implements a secure login system, ensuring that only authorized users can
  access the system.

---

## Technologies Used

* Java JDK  `version 11.0.18`
* MySQL `version 8.0.33`
* Hibernate `version 6.2.7.Final`
* JavaFX `version 19.0.2`

---

## Installation

1. Clone the repository:
```
https://github.com/viniduminsara/D24_Hostel_Management_System.git
```

2. Navigate to the project directory:
```
cd D24-Hostel-Management-System
```

3. If not using maven run add this run configuration
```
--module-path
"PATH_TO_YOUR_JAVAFX_SDK"
--add-modules
javafx.controls,javafx.fxml
--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED
--add-exports=javafx.base/com.sun.javafx.binding=ALL-UNNAMED
--add-exports=javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED
--add-exports=javafx.base/com.sun.javafx.event=ALL-UNNAMED
```
---

## Database Configuration

Configure the database by changing necessary settings in the configuration files. Change Hibernate configuration
settings in `src/main/resources/hibernate.properties`

```properties
# Database connection properties

hibernate.connection.driver_class = com.mysql.jdbc.Driver
hibernate.connection.url = jdbc:mysql://localhost:3306/d24_hostel
hibernate.connection.username = root
hibernate.connection.password = 1234
hibernate.dialect = org.hibernate.dialect.MySQLDialect
hibernate.show_sql = true
hibernate.hbm2ddl.auto = update
```
---
## License

This project is licensed under the [MIT License](./LICENSE)

---

Happy coding 😊


