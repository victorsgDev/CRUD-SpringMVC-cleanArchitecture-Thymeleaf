CREATE DATABASE BIBLIOTECA;
USE BIBLIOTECA;

CREATE TABLE USER
(
    idUser int primary key,
    name   varchar(30),
    nif    varchar(10)
);

CREATE TABLE LIBRO
(
    idLibro    int primary key,
    name       varchar(20),
    author     varchar(20),
    editorial  varchar(20),
    numPages   int,
    disponible boolean,
    idUser int,
    foreign key (idUser) references USER(idUser)
);