-- liquibase formatted sql

-- changeset Florian_Saqipi:15
ALTER TABLE flights
DROP CONSTRAINT fk_assignment_id;

-- changeset Florian_Saqipi:16
ALTER TABLE flights
DROP COLUMN assignment_id;

-- changeset Florian_Saqipi:17
ALTER TABLE tickets
DROP CONSTRAINT fk_boarding_pass_id;

-- changeset Florian_Saqipi:18
ALTER TABLE tickets
DROP COLUMN boarding_pass_id;

-- changeset Florian_Saqipi:19
ALTER TABLE boarding_passes
ADD COLUMN ticket_id INT;

-- changeset Florian_Saqipi:20
ALTER TABLE boarding_passes
ADD CONSTRAINT fk_ticket_id
FOREIGN KEY (ticket_id)
REFERENCES tickets(ticket_id);

-- changeset Florian_Saqipi:21
ALTER TABLE gate_assignments
ADD COLUMN flight_id INT;

-- changeset Florian_Saqipi:22
ALTER TABLE gate_assignments
ADD CONSTRAINT fk_flight_id
FOREIGN KEY (flight_id)
REFERENCES flights(flight_id);

-- changeset Florian_Saqipi:23
ALTER TABLE flight_crews
DROP CONSTRAINT flight_crews_ibfk_3;

-- changeset Florian_Saqipi:24
ALTER TABLE flight_crews
DROP CONSTRAINT flight_crews_ibfk_4;

