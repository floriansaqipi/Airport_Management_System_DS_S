CREATE TABLE IF NOT EXISTS security_checkpoints (
    checkpoint_id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    operating_hours VARCHAR(50) NOT NULL
);