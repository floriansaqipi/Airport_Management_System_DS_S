CREATE TABLE IF NOT EXISTS aircrafts (
    aircraft_id INT AUTO_INCREMENT PRIMARY KEY,
    tail_number VARCHAR(10) UNIQUE NOT NULL,
    model VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    airline_id INT,
    FOREIGN KEY (airline_id) REFERENCES airlines(airline_id)
);