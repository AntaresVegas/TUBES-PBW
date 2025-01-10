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


-- Membuat tipe ENUM
CREATE TYPE user_role AS ENUM ('member', 'admin');

-- Tabel Users dengan ENUM
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role user_role NOT NULL DEFAULT 'member'
);


-- Tabel Artists
CREATE TABLE Artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Tabel Shows
CREATE TABLE Shows (
    id SERIAL PRIMARY KEY,
    artist_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    venue VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES Artists(id) ON DELETE CASCADE
);

-- Tabel Setlists
CREATE TABLE Setlists (
    id SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    songs TEXT NOT NULL, -- Songs will be stored as a comma-separated list
    FOREIGN KEY (show_id) REFERENCES Shows(id) ON DELETE CASCADE
);

-- Tabel Festivals
CREATE TABLE Festivals (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    venue VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- Tabel Festival_Schedule
CREATE TABLE Festival_Schedule (
    id SERIAL PRIMARY KEY,
    festival_id INT NOT NULL,
    show_id INT NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (festival_id) REFERENCES Festivals(id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES Shows(id) ON DELETE CASCADE
);
