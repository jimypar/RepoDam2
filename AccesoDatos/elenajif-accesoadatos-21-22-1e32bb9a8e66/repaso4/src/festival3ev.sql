CREATE DATABASE festival3ev;
USE festival3ev;

CREATE TABLE artistas(
id INT AUTO_INCREMENT PRIMARY KEY,
dni VARCHAR(100),
nombre VARCHAR(100),
estilo VARCHAR(100),
cache float
);

CREATE TABLE asistentes(
id INT AUTO_INCREMENT PRIMARY KEY,
dni VARCHAR(100),
nombre VARCHAR(100),
fecha_nacimiento DATE,
nacionalidad VARCHAR(100)
);

INSERT INTO artistas(dni, nombre, estilo, cache)
VALUES ("000001", "Andrea", "Pop",250),
("000002", "Carlos", "Rock",200),
("000003", "Maria", "Opera",350);

INSERT INTO asistentes(dni, nombre, fecha_nacimiento, nacionalidad) 
VALUES('7324534F', 'María', '2000-05-06', 'spain'), 
('1234567F', 'Alfonso', '1990-10-16', 'france'), 
('7656456T', 'Pedro', '2010-01-26', 'spain'), 
('8734566L', 'Belen', '2005-09-02', 'german'); 


