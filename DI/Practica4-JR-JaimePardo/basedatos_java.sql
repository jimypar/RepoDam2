CREATE DATABASE if not exists jrJaimePardo;
--
USE jrJaimePardo;
--
create table if not exists cliente(
id_cliente int auto_increment primary key,
dni varchar (50) not null,
nombre varchar(50) not null,
apellidos varchar(150) not null,
direccion varchar (150) not null,
cp int not null,
ciudad varchar (150) not null,
telefono int not null);
