-- DROP TABLE jika sudah ada sebelumnya, menggunakan CASCADE untuk menghapus dependensi
DROP TABLE IF EXISTS setlist_songs CASCADE;
DROP TABLE IF EXISTS setlists CASCADE;
DROP TABLE IF EXISTS songs CASCADE;
DROP TABLE IF EXISTS shows CASCADE;
DROP TABLE IF EXISTS festivals CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS members CASCADE;
DROP TABLE IF EXISTS admins CASCADE;
DROP TABLE IF EXISTS artists CASCADE;

-- Memeriksa semua data di tabel
SELECT * FROM admins;
SELECT * FROM members;
SELECT * FROM artists;
SELECT * FROM festivals;
SELECT * FROM shows;
SELECT * FROM songs;
SELECT * FROM setlists;
SELECT * FROM setlist_songs;
SELECT * FROM comments;

-- Menampilkan daftar setlist beserta lagu-lagunya
SELECT 
    sl.id AS setlist_id,
    sh.title AS show_title,
    sg.title AS song_title,
    sg.genre AS song_genre
FROM setlists sl
JOIN shows sh ON sl.show_id = sh.id
JOIN setlist_songs sls ON sl.id = sls.setlist_id
JOIN songs sg ON sls.song_id = sg.id;


-- Membuat tabel admins
CREATE TABLE admins (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Membuat tabel members
CREATE TABLE members (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL
);

-- Membuat tabel artists
CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Membuat tabel festivals
CREATE TABLE festivals (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    location VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- Membuat tabel shows
CREATE TABLE shows (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist_id INT NOT NULL,
    festival_id INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES artists (id) ON DELETE CASCADE,
    FOREIGN KEY (festival_id) REFERENCES festivals (id) ON DELETE CASCADE
);

-- Membuat tabel songs
CREATE TABLE songs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist_id INT NOT NULL,
    genre VARCHAR(100),
    FOREIGN KEY (artist_id) REFERENCES artists (id) ON DELETE CASCADE
);

-- Membuat tabel setlists
CREATE TABLE setlists (
    id SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows (id) ON DELETE CASCADE
);

-- Membuat tabel setlist_songs (junction table untuk relasi many-to-many)
CREATE TABLE setlist_songs (
    setlist_id INT NOT NULL,
    song_id INT NOT NULL,
    PRIMARY KEY (setlist_id, song_id),
    FOREIGN KEY (setlist_id) REFERENCES setlists (id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES songs (id) ON DELETE CASCADE
);

-- Membuat tabel comments
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    comment TEXT NOT NULL,
    festival_id INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE CASCADE,
    FOREIGN KEY (festival_id) REFERENCES festivals (id) ON DELETE CASCADE
);

-- Menambahkan data ke tabel
INSERT INTO admins (email, password) VALUES
('admin3@example.com', 'securepass789'),
('admin4@example.com', 'mypassword321'),
('superadmin@example.com', 'supersecretpass');

INSERT INTO members (username, email, password, full_name) VALUES
('alicew', 'alicew@example.com', 'passwordalice', 'Alice Wonderland'),
('bobm', 'bobm@example.com', 'passwordbob', 'Bob Marley'),
('charliet', 'charliet@example.com', 'passwordcharlie', 'Charlie Tango'),
('davidk', 'davidk@example.com', 'passworddavid', 'David Kross'),
('emilyr', 'emilyr@example.com', 'passwordemily', 'Emily Rose');

INSERT INTO artists (name) VALUES
('Adele'),
('Ed Sheeran'),
('Taylor Swift'),
('The Weeknd'),
('Imagine Dragons');

INSERT INTO festivals (name, location, start_date, end_date) VALUES
('Glastonbury Festival', 'Somerset', '2025-06-26', '2025-06-30'),
('Lollapalooza', 'Chicago', '2025-07-15', '2025-07-18'),
('Tomorrowland', 'Belgium', '2025-08-20', '2025-08-25'),
('Burning Man', 'Nevada', '2025-09-01', '2025-09-07'),
('Summer Sonic', 'Tokyo', '2025-08-10', '2025-08-12');

INSERT INTO shows (title, artist_id, festival_id, date) VALUES
('Adele Live at Glastonbury', 1, 1, '2025-06-27'),
('Ed Sheeran in Lollapalooza', 2, 2, '2025-07-16'),
('Taylor Swift at Tomorrowland', 3, 3, '2025-08-22'),
('The Weeknd at Burning Man', 4, 4, '2025-09-02'),
('Imagine Dragons at Summer Sonic', 5, 5, '2025-08-11');

INSERT INTO songs (title, artist_id, genre) VALUES
('Hello', 1, 'Pop'),
('Rolling in the Deep', 1, 'Pop'),
('Shape of You', 2, 'Pop'),
('Perfect', 2, 'Pop'),
('Love Story', 3, 'Country'),
('Blank Space', 3, 'Pop'),
('Blinding Lights', 4, 'R&B'),
('Save Your Tears', 4, 'R&B'),
('Believer', 5, 'Alternative Rock'),
('Thunder', 5, 'Alternative Rock');

INSERT INTO setlists (show_id) VALUES
(1), -- Adele Live at Glastonbury
(2), -- Ed Sheeran in Lollapalooza
(3), -- Taylor Swift at Tomorrowland
(4), -- The Weeknd at Burning Man
(5); -- Imagine Dragons at Summer Sonic

INSERT INTO setlist_songs (setlist_id, song_id) VALUES
-- Adele Live at Glastonbury
(1, 1), -- Hello
(1, 2), -- Rolling in the Deep

-- Ed Sheeran in Lollapalooza
(2, 3), -- Shape of You
(2, 4), -- Perfect

-- Taylor Swift at Tomorrowland
(3, 5), -- Love Story
(3, 6), -- Blank Space

-- The Weeknd at Burning Man
(4, 7), -- Blinding Lights
(4, 8), -- Save Your Tears

-- Imagine Dragons at Summer Sonic
(5, 9), -- Believer
(5, 10); -- Thunder

INSERT INTO comments (member_id, comment, festival_id) VALUES
(1, 'Absolutely loved the performance by Adele!', 1),
(2, 'Ed Sheeran was amazing at Lollapalooza!', 2),
(3, 'Taylor Swift brought so much energy to Tomorrowland!', 3),
(4, 'The Weeknd at Burning Man was unforgettable!', 4),
(5, 'Imagine Dragons rocked Summer Sonic!', 5),
(1, 'I can’t wait for the next Glastonbury Festival!', 1),
(2, 'The vibe at Lollapalooza was incredible!', 2);