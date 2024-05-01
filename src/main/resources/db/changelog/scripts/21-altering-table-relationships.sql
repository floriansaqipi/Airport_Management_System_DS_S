-- liquibase formatted sql

-- changeset Florian_Saqipi:1
ALTER TABLE boarding_passes
DROP CONSTRAINT boarding_passes_ibfk_1;

-- changeset Florian_Saqipi:2
ALTER TABLE boarding_passes
DROP COLUMN ticket_id;

-- changeset Florian_Saqipi:3
ALTER TABLE gate_assignments
DROP CONSTRAINT gate_assignments_ibfk_1;

-- changeset Florian_Saqipi:4
ALTER TABLE gate_assignments
DROP COLUMN flight_id;

-- changeset Florian_Saqipi:5
ALTER TABLE tickets
ADD COLUMN boarding_pass_id INT;

-- changeset Florian_Saqipi:6
ALTER TABLE tickets
ADD CONSTRAINT fk_boarding_pass_id
FOREIGN KEY (boarding_pass_id)
REFERENCES boarding_passes(boarding_pass_id);

-- changeset Florian_Saqipi:7
ALTER TABLE flights
ADD COLUMN assignment_id INT;

-- changeset Florian_Saqipi:8
ALTER TABLE flights
ADD CONSTRAINT fk_assignment_id
FOREIGN KEY (assignment_id)
REFERENCES gate_assignments(assignment_id);

-- changeset Florian_Saqipi:9
ALTER TABLE employees
DROP CONSTRAINT employees_ibfk_1;

-- changeset Florian_Saqipi:10
ALTER TABLE employees
DROP COLUMN airport_id;

-- changeset Florian_Saqipi:11
ALTER TABLE flight_crews
DROP PRIMARY KEY,
DROP COLUMN crew_id;

-- changeset Florian_Saqipi:12
ALTER TABLE flight_crews
ADD PRIMARY KEY (flight_id, employee_id);

-- changeset Florian_Saqipi:13
ALTER TABLE flight_crews
DROP COLUMN role;

-- changeset Florian_Saqipi:14
ALTER TABLE flight_crews
ADD FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
ADD FOREIGN KEY (employee_id) REFERENCES employees(employee_id);




