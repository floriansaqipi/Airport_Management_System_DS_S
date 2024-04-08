CREATE TABLE IF NOT EXISTS feedback (
                          feedback_id INT AUTO_INCREMENT PRIMARY KEY,
                          passenger_id INT,
                          flight_id INT,
                          content TEXT,
                          status VARCHAR(50),
                          FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id),
                          FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);