-- liquibase formatted sql

-- changeset Florian_Saqipi:33
DROP TABLE user_roles;

-- changeset Florian_Saqipi:34
ALTER TABLE users
ADD COLUMN role_id INT,
ADD CONSTRAINT fk_role_id
FOREIGN KEY (role_id)
REFERENCES roles(role_id);

-- changeset Florian_Saqipi:35
ALTER TABLE abilities
MODIFY verb VARCHAR(255);

-- changeset Florian_Saqipi:36
ALTER TABLE abilities
ADD COLUMN is_personal_allowed TINYINT(1) DEFAULT NULL;

-- changeset Florian_Saqipi:37
ALTER TABLE abilities DROP COLUMN is_personal_allowed;
