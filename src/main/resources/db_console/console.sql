DROP DATABASE IF EXISTS BIBLIOTECA;
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
    name       varchar(40),
    author     varchar(20),
    editorial  varchar(20),
    numPages   int,
    disponible boolean,
    idUser int,
    foreign key (idUser) references USER(idUser)
);

INSERT INTO USER VALUES (0,'Sin Usuario','-');
INSERT INTO USER VALUES (01,'Test','012345678L');

INSERT INTO LIBRO VALUES (01,'Don Quijote de la Mancha','Miguel de Cervantes','Vicens vives',115,true,0);
INSERT INTO LIBRO VALUES (02,'TODO VA A MEJORAR','ALMUDENA GRANDES','TUSQUETS EDITORES',496,false,01);
