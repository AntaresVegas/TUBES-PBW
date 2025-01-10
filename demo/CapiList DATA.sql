-- Database: CapiList

-- DROP DATABASE IF EXISTS "CapiList";

CREATE DATABASE "CapiList"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_Indonesia.1252'
    LC_CTYPE = 'English_Indonesia.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

INSERT INTO Users (username, email, password, role) VALUES
('john_doe', 'john.doe@example.com', 'hashed_password1', 'member'),
('jane_doe', 'jane.doe@example.com', 'hashed_password2', 'member'),
('admin_user', 'admin@example.com', 'hashed_password3', 'admin');

INSERT INTO Artists (name) VALUES
('Metallica'),
('Coldplay'),
('Taylor Swift'),
('Ed Sheeran');

INSERT INTO Shows (artist_id, name, venue, date) VALUES
(1, 'M72 World Tour', 'Johan Cruijff Arena, Amsterdam', '2023-04-27'),
(1, 'M72 World Tour', 'Stade de France, Paris', '2023-05-19'),
(2, 'A Head Full of Dreams', 'Wembley Stadium, London', '2023-06-10'),
(3, 'Eras Tour', 'SoFi Stadium, Los Angeles', '2023-08-05');

INSERT INTO Setlists (show_id, songs) VALUES
(1, 'Whiplash, Creeping Death, For Whom the Bell Tolls, Fuel'),
(2, 'Enter Sandman, Nothing Else Matters, One, Sad But True'),
(3, 'A Sky Full of Stars, Fix You, Viva La Vida, Yellow'),
(4, 'Love Story, You Belong With Me, Shake It Off, Blank Space');

INSERT INTO Festivals (name, venue, start_date, end_date) VALUES
('British Summer Time 2024', 'Hyde Park, London', '2024-06-27', '2024-06-30'),
('Coachella 2024', 'Empire Polo Club, Indio', '2024-04-12', '2024-04-21');

INSERT INTO Festival_Schedule (festival_id, show_id, time) VALUES
(1, 1, '15:00:00'),
(1, 2, '18:00:00'),
(2, 3, '16:00:00'),
(2, 4, '20:00:00');


SELECT * FROM Shows
