CREATE DATABASE tallerJaimePardo;
--
USE tallerJaimePardo;
--
create table if not exists cliente (
id int primary key,
dni VARCHAR(30) not null,
nombre VARCHAR(30) not null,
apellidos VARCHAR(30) not null,
email VARCHAR(30) not null,
telefono int
);
--
create table if not exists coche (
id int auto_increment primary key,
tipo VARCHAR(20) not null,
matricula VARCHAR(20) not null UNIQUE,
marca VARCHAR(40) not null,
fecha_alta TIMESTAMP,
id_cliente int,
FOREIGN KEY (id) REFERENCES cliente(id)
);
--
create table if not exists mecanico(
id int primary key,
nombre VARCHAR(30) not null,
apellidos VARCHAR(30) not null,
telefono VARCHAR(30) not null
);
--
create table if not exists mecanico_coche(
id int AUTO_INCREMENT PRIMARY KEY,
id_coche int,
id_mecanico int,
FOREIGN KEY (id_coche) REFERENCES coche(id),
FOREIGN KEY (id_mecanico) REFERENCES mecanico(id)
);
--
create table if not exists recambio(
id int primary key
);
--
create table if not exists recambio_coche(
id int AUTO_INCREMENT PRIMARY KEY,
id_coche int,
id_recambio int,
FOREIGN KEY (id_coche) REFERENCES coche(id),
FOREIGN KEY (id_recambio) REFERENCES recambio(id)
);
--
create table if not exists recambiosCombustion(
id int primary key,
nombre VARCHAR(30) not null UNIQUE,
precio FLOAT not null,
FOREIGN KEY (id) REFERENCES recambio(id)
);
--
create table if not exists recambiosElectrico(
id int primary key,
nombre VARCHAR(30) not null UNIQUE,
FOREIGN KEY (id) REFERENCES recambio(id)
);
--
create table if not exists recambiosHibrido(
id int primary key,
nombre VARCHAR(30) not null UNIQUE,
precio FLOAT not null,
FOREIGN KEY (id) REFERENCES recambio(id)
);
--
create function existeMatricula(f_matricula varchar(40))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(id) from coche)) do
    if  ((select matricula from coche where id = (i + 1)) like f_matricula) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end;
--
create function existeDniCliente(f_dni varchar(50))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(id) from cliente)) do
    if  ((select dni from cliente where id = (i + 1)) like f_dni) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end;



