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


DROP TABLE IF EXISTS Festival_Schedule;
DROP TABLE IF EXISTS Setlists;
DROP TABLE IF EXISTS Shows;
DROP TABLE IF EXISTS Festivals;
DROP TABLE IF EXISTS Artists;
DROP TABLE IF EXISTS Members;
DROP TABLE IF EXISTS Admins;

SELECT * FROM Members

-- Tabel Members
CREATE TABLE Members (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap member
    full_name VARCHAR(100) NOT NULL,     -- Nama lengkap member
    username VARCHAR(50) NOT NULL UNIQUE, -- Username unik
    email VARCHAR(100) NOT NULL UNIQUE,  -- Email unik
    password VARCHAR(255) NOT NULL       -- Password
);


CREATE TABLE Admins (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap admin
    username VARCHAR(50) NOT NULL UNIQUE, -- Username unik
    email VARCHAR(100) NOT NULL UNIQUE,  -- Email unik
    password VARCHAR(255) NOT NULL       -- Password
);


CREATE TABLE Artists (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap artis
    name VARCHAR(100) NOT NULL UNIQUE    -- Nama artis
);


CREATE TABLE Shows (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap konser
    artist_id INT NOT NULL,              -- ID artis terkait
    name VARCHAR(100) NOT NULL,          -- Nama konser
    venue VARCHAR(100) NOT NULL,         -- Tempat konser
    date DATE NOT NULL,                  -- Tanggal konser
    FOREIGN KEY (artist_id) REFERENCES Artists(id) ON DELETE CASCADE
);


CREATE TABLE Setlists (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap setlist
    show_id INT NOT NULL,                -- ID konser terkait
    songs TEXT NOT NULL,                 -- Daftar lagu (dipisahkan koma)
    FOREIGN KEY (show_id) REFERENCES Shows(id) ON DELETE CASCADE
);


CREATE TABLE Festivals (
    id SERIAL PRIMARY KEY,               -- ID unik untuk setiap festival
    name VARCHAR(100) NOT NULL,          -- Nama festival
    venue VARCHAR(100) NOT NULL,         -- Tempat festival
    start_date DATE NOT NULL,            -- Tanggal mulai festival
    end_date DATE NOT NULL               -- Tanggal akhir festival
);


CREATE TABLE Festival_Schedule (
    id SERIAL PRIMARY KEY,               -- ID unik untuk jadwal festival
    festival_id INT NOT NULL,            -- ID festival terkait
    show_id INT NOT NULL,                -- ID konser terkait
    time TIME NOT NULL,                  -- Waktu konser dalam festival
    FOREIGN KEY (festival_id) REFERENCES Festivals(id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES Shows(id) ON DELETE CASCADE
);
