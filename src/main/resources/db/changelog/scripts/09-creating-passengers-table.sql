CREATE TABLE IF NOT EXISTS passengers (
                            passenger_id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            passport_number VARCHAR(15) UNIQUE NOT NULL,
                            nationality VARCHAR(255) NOT NULL,
                            contact_details VARCHAR(255)
);