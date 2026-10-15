# BITS — Basic Inventory & Tracking System (Java version)

This is the starting Java version of BITS: a desktop electronics-component inventory manager built with **Java Swing + JDBC + MySQL**. It includes registration/login, user-specific inventory, categories, search, add/edit, stock increment/decrement, and delete.

## Requirements
- JDK 17+
- Maven 3.8+
- MySQL 8+

## Setup
1. Open MySQL Workbench or a MySQL terminal and run `database/schema.sql`.
2. Open `src/main/java/bits/database/DBConnection.java` and set your MySQL username/password (or set environment variables `BITS_DB_URL`, `BITS_DB_USER`, `BITS_DB_PASSWORD`).
3. From this folder, run:
   ```bash
   mvn clean compile exec:java
   ```
   On Windows PowerShell, run the same command from the project directory.
4. Register a new user from the app's login screen.

Default DB URL: `jdbc:mysql://localhost:3306/bits_inventory?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true`

## Notes
- Passwords are stored as salted PBKDF2 hashes, not plaintext.
- This is a student starter project. For deployment, move credentials out of source code and add stronger validation/backups.
- If Maven is unavailable, import this folder as a Maven project in IntelliJ IDEA or VS Code with Java extensions.
