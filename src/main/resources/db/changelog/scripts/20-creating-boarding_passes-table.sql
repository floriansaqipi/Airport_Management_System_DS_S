CREATE TABLE IF NOT EXISTS boarding_passes (
    boarding_pass_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    gate VARCHAR(10),
    boarding_time DATETIME NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);