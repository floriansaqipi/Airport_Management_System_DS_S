CREATE TABLE IF NOT EXISTS maintenance (
    maintenance_id INT AUTO_INCREMENT PRIMARY KEY,
    aircraft_id INT,
    date DATETIME NOT NULL,
    type VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id)
);