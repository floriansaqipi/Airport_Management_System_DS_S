CREATE TABLE IF NOT EXISTS airports (
    airport_id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(5) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    location_city VARCHAR(255) NOT NULL,
    location_country VARCHAR(255) NOT NULL
);