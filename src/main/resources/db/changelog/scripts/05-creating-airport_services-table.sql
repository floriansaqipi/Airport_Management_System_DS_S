CREATE TABLE IF NOT EXISTS airport_services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    operating_hours VARCHAR(50)
);