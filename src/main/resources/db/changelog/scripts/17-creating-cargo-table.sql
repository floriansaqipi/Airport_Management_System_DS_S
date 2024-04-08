CREATE TABLE IF NOT EXISTS cargo (
    cargo_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    weight DECIMAL(10, 2),
    dimensions VARCHAR(50),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);