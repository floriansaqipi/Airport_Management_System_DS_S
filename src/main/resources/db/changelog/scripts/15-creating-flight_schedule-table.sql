CREATE TABLE IF NOT EXISTS flight_schedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    scheduled_departure_time DATETIME NOT NULL,
    scheduled_arrival_time DATETIME NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);