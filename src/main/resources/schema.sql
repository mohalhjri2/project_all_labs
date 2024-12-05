-- Create the household table first
CREATE TABLE household (
                           eircode VARCHAR(8) PRIMARY KEY,
                           number_of_occupants INT NOT NULL,
                           max_number_of_occupants INT NOT NULL,
                           owner_occupied BIT NOT NULL
);

-- Create the pets table after the household table
CREATE TABLE pets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      animal_type VARCHAR(255) NOT NULL,
                      breed VARCHAR(255) NOT NULL,
                      age INT NOT NULL,
                      household_eircode VARCHAR(8),
                      CONSTRAINT fk_household FOREIGN KEY (household_eircode) REFERENCES household (eircode)
);
-- Create the 'users' table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE, -- Email address
                       password VARCHAR(255) NOT NULL,        -- Encrypted password
                       role VARCHAR(50) NOT NULL,             -- Role: USER or ADMIN
                       locked BOOLEAN NOT NULL DEFAULT FALSE, -- Account locked status
                       first_name VARCHAR(100) NOT NULL,      -- First name
                       last_name VARCHAR(100) NOT NULL,       -- Last name
                       county VARCHAR(50) NOT NULL            -- County (e.g., 'Cork', 'Kerry')
);
