CREATE TABLE IF NOT EXISTS employees (
                           employee_id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           role VARCHAR(100) NOT NULL,
                           contact_info VARCHAR(255),
                           airport_id INT,
                           FOREIGN KEY (airport_id) REFERENCES airports(airport_id)
);