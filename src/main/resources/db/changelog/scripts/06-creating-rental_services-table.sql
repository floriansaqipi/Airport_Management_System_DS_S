CREATE TABLE IF NOT EXISTS rental_services (
    rental_id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    rate DECIMAL(10, 2) NOT NULL
);