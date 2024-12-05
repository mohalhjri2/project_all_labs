INSERT INTO pets (name, animal_type, breed, age) VALUES
                                                     ('Buddy', 'Dog', 'Golden Retriever', 3),
                                                     ('Mittens', 'Cat', 'Siamese', 2),
                                                     ('Charlie', 'Dog', 'Beagle', 4),
                                                     ('Whiskers', 'Cat', 'Persian', 5),
                                                     ('Coco', 'Rabbit', 'Holland Lop', 1),
                                                     ('Goldie', 'Fish', 'Goldfish', 1),
                                                     ('Polly', 'Bird', 'Parakeet', 2),
                                                     ('Max', 'Dog', 'German Shepherd', 5),
                                                     ('Luna', 'Cat', 'Maine Coon', 3),
                                                     ('Nibbles', 'Hamster', 'Syrian Hamster', 1);

INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
                                                                                                  ('D02XY45', 3, 5, 1),
                                                                                                  ('A94B6F3', 4, 6, 0),
                                                                                                  ('T12AB34', 2, 4, 1),
                                                                                                  ('C15DE67', 5, 7, 1),
                                                                                                  ('F12GH89', 1, 2, 0),
                                                                                                  ('B78IJ01', 3, 5, 1),
                                                                                                  ('M34KL56', 4, 6, 0),
                                                                                                  ('P90QR78', 2, 4, 1),
                                                                                                  ('V23ST01', 5, 7, 1),
                                                                                                  ('X45UV67', 1, 2, 0),
                                                                                                  ('Y67WX89', 3, 5, 1),
                                                                                                  ('Z01YZ23', 4, 6, 0),
                                                                                                  ('Q45AB78', 2, 4, 1),
                                                                                                  ('R67CD01', 5, 7, 1),
                                                                                                  ('S23EF45', 1, 2, 0);

INSERT INTO users (username, password, role, locked, first_name, last_name, county)
VALUES
    ('admin@example.com', '$2a$10$7B/YFv/jWl0ReJg0LzLkFuCMT8Rd/dDxczcBbzDZrrMwz82PGVRfK', 'ADMIN', false, 'Admin', 'User', 'Cork'),
    ('user@example.com', '$2a$10$7B/YFv/jWl0ReJg0LzLkFuCMT8Rd/dDxczcBbzDZrrMwz82PGVRfK', 'USER', false, 'Regular', 'User', 'Kerry');
