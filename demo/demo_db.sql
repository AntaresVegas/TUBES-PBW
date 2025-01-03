-- Database: demo_db

-- DROP DATABASE IF EXISTS demo_db;

CREATE DATABASE demo_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE users (
    id SERIAL PRIMARY KEY,          -- ID unik, auto-increment
    username VARCHAR(50) NOT NULL,  -- Nama pengguna (unik)
    password VARCHAR(100) NOT NULL, -- Kata sandi
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Waktu pembuatan
);


INSERT INTO users (username, password)
VALUES 
    ('bram2', 'test2'),
	('bram', 'test'),
	('reine', 'reinegapernahmasuk'),
	('afifah', 'afifahlophenry'),
    ('john_doe', 'password123'),
    ('jane_doe', 'securepass');


SELECT * FROM users;
