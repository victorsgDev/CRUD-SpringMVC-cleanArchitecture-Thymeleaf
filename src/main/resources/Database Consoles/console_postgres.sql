CREATE TABLE "USER"
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
    foreign key (idUser) references "USER"(idUser)
);

INSERT INTO "USER" VALUES (10,'Sin Usuario','-');
INSERT INTO "USER" VALUES (11,'Test','012345678L');



INSERT INTO LIBRO VALUES (20,'Don Quijote de la Mancha','Miguel de Cervantes','Vicens vives',115,true,10);
INSERT INTO LIBRO VALUES (21,'TODO VA A MEJORAR','ALMUDENA GRANDES','TUSQUETS EDITORES',496,false,11);

