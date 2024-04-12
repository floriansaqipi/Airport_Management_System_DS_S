CREATE TABLE IF NOT EXISTS flight_crews (
    crew_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    employee_id INT,
    role VARCHAR(50),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);