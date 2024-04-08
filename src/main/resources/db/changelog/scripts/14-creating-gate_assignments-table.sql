CREATE TABLE IF NOT EXISTS gate_assignments (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    gate VARCHAR(10),
    assignment_time DATETIME NOT NULL,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);