-- liquibase formatted sql

-- changeset Ardi_Zariqi:25
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- changeset Ardi_Zariqi:26
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- changeset Ardi_Zariqi:27
CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- changeset Ardit_Gjyrevci:28
ALTER TABLE passengers
ADD COLUMN p_user_id INT,
ADD CONSTRAINT fk_passengers_users
FOREIGN KEY (p_user_id)
REFERENCES users(user_id);


-- changeset Ardit_Gjyrevci:29
ALTER TABLE employees
ADD COLUMN e_user_id INT,
ADD CONSTRAINT fk_employees_users
FOREIGN KEY (e_user_id)
REFERENCES users(user_id);