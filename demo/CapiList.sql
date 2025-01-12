-- DROP TABLE untuk menghapus tabel jika sudah ada
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS festivals;
DROP TABLE IF EXISTS setlists;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS artists;

-- Membuat ulang tabel sesuai desain sebelumnya
CREATE TABLE admins (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE members (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL
);

CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE festivals (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    location VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE shows (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist_id INT NOT NULL,
    festival_id INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES artists (id) ON DELETE CASCADE,
    FOREIGN KEY (festival_id) REFERENCES festivals (id) ON DELETE CASCADE
);

CREATE TABLE songs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist_id INT NOT NULL,
    genre VARCHAR(100),
    FOREIGN KEY (artist_id) REFERENCES artists (id) ON DELETE CASCADE
);

CREATE TABLE setlists (
    id SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    song_id INT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows (id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES songs (id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    comment TEXT NOT NULL,
    festival_id INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE CASCADE,
    FOREIGN KEY (festival_id) REFERENCES festivals (id) ON DELETE CASCADE
);

-- SELECT semua data dari masing-masing tabel
SELECT * FROM admins;
SELECT * FROM members;
SELECT * FROM artists;
SELECT * FROM festivals;
SELECT * FROM shows;
SELECT * FROM songs;
SELECT * FROM setlists;
SELECT * FROM comments;
