-- liquibase formatted sql

-- changeset Florian_Saqipi:30
CREATE TABLE abilities (
    ability_id INT AUTO_INCREMENT PRIMARY KEY,
    entity VARCHAR(255) NOT NULL,
    verb VARCHAR(255) NOT NULL,
    field VARCHAR(255) NOT NULL
);

-- changeset Florian_Saqipi:31
CREATE TABLE role_abilities (
    role_id INT,
    ability_id INT,
    PRIMARY KEY (role_id, ability_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id),
    FOREIGN KEY (ability_id) REFERENCES abilities(ability_id)
);

-- changeset Florian_Saqipi:32
ALTER TABLE abilities
MODIFY field VARCHAR(255);