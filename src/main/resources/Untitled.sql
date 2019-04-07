create schema `scoala`;
USE `scoala`;

CREATE TABLE `scoala`.`clasa` (
  `idClasa` INT NOT NULL AUTO_INCREMENT,
  `numeClasa` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idClasa`));

CREATE TABLE `scoala`.`elevi` (
  `idElev` INT NOT NULL AUTO_INCREMENT,
  `numeElev` VARCHAR(45) NOT NULL,
  `idClasa` INT NOT NULL,
  PRIMARY KEY (`idElev`),
  Constraint FOREIGN KEY (`idClasa`) REFERENCES clasa(`idClasa`));


CREATE TABLE `scoala`.`materie` (
  `idMaterie` INT NOT NULL AUTO_INCREMENT,
  `numeMaterie` VARCHAR(45) NULL,
  PRIMARY KEY (`idMaterie`));

  CREATE TABLE `scoala`.`profesori` (
  `idProfesor` INT NOT NULL AUTO_INCREMENT,
  `numeProfesor` VARCHAR(45) NULL,
  `idMateriePredata` INT NULL,
  PRIMARY KEY (`idProfesor`),
  Constraint FOREIGN KEY (`idMateriePredata`) REFERENCES materie(`idMaterie`))
  ;

CREATE TABLE `scoala`.`note` (
  `idNote` INT NOT NULL AUTO_INCREMENT,
  `valoareNota` VARCHAR(45) NULL,
  `idProfesor` INT NULL,
  `idMaterie` INT NULL,
  `idElev` INT NULL,
  PRIMARY KEY (`idNote`),
  Constraint FOREIGN KEY (`idProfesor`) REFERENCES profesori(`idProfesor`),
  Constraint FOREIGN KEY (`idMaterie`) REFERENCES materie(`idMaterie`),
  Constraint FOREIGN KEY (`idElev`) REFERENCES elevi(`idElev`)
      );

insert into scoala.clasa (numeClasa) values
('9A'),
('10A'),
('11A');

insert into scoala.materie (numeMaterie) values
('Matematica'),
('Fizica'),
('Religie'),
('Informatica'),
('Traforaj');

insert into scoala.profesori (numeProfesor,idMateriePredata)
values
('Mirela Manolescu', 1),
('Cornelia Cornelescu', 2),
('Dan Diaconescu', 3),
('Adrian Magureanu', 4),
('Manuel Manuelescovici', 5),
('Dorel Greuceanu', (
    select idMaterie from scoala.materie
    where numeMaterie = 'Fizica')),
('Floriocel Gherorge', 3);

create view allProfi as 
select numeProfesor, numeMaterie from scoala.profesori inner join 
scoala.materie on profesori.idMateriePredata = materie.idMaterie; 
 
create view clasa10A as
 select numeElev, numeClasa from scoala.elevi inner join scoala.clasa
 on elevi.idClasa = clasa.idClasa where clasa.numeClasa = '10A';
 

 insert into scoala.elevi(numeElev, idClasa) values
('Cristi',1),
('Mircea', 1),
('Andor',1),
('Catalina', 1),
('Florin',1),
('Beatrice',1),
('Costi',2),
('Rox',2),
('Dinu',2),
('Alex',2),
('Mihai', 3),
('Irene',3),
('Nicu', 3),
('Florian',3),
('Andrei',3);

insert into scoala.note (valoareNota, idProfesor, idMaterie, idElev) 
values
(10,
 (select idProfesor from scoala.profesori where 
profesori.numeProfesor = 'Adrian Magureanu'),
 (select idMaterie from scoala.materie where 
materie.numeMaterie = 'Informatica'),
(select idElev from scoala.elevi where
 elevi.numeElev = 'Rox')),
 (7,1,1,1);