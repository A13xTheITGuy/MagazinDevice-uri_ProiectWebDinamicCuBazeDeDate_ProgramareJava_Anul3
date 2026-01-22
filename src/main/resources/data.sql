CREATE TABLE IF NOT EXISTS `magazin_deviceuri`.`utilizatori` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(45) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `parola` VARCHAR(72) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

  
CREATE TABLE IF NOT EXISTS`magazin_deviceuri`.`laptopuri` (
  `id_laptop` INT NOT NULL AUTO_INCREMENT,
  `id_utilizator` INT NOT NULL,
  `nume_laptop` VARCHAR(255) NOT NULL,
  `procesor` VARCHAR(255) NOT NULL,
  `placa_video` VARCHAR(255) NOT NULL,
  `capacitate_ram` INT NOT NULL,
  `tip_ram` VARCHAR(255) NOT NULL,
  `frecventa_ram` INT NOT NULL,
  `tip_stocare` VARCHAR(255) NOT NULL,
  `capacitate_stocare` INT NOT NULL,
  `rezolutie_ecran` VARCHAR(255) NOT NULL,
  `dimensiune_ecran` FLOAT NOT NULL,
  `sistem_de_operare` VARCHAR(255) NOT NULL,
  `culoare` VARCHAR(255) NOT NULL,
  `pret` FLOAT NOT NULL,
  PRIMARY KEY (`id_laptop`),
  UNIQUE INDEX `id_laptop_UNIQUE` (`id_laptop` ASC) VISIBLE);

ALTER TABLE `magazin_deviceuri`.`laptopuri` 
ADD INDEX `fk_laptopuri_utilizatori_idx` (`id_utilizator` ASC) VISIBLE;
;
ALTER TABLE `magazin_deviceuri`.`laptopuri` 
ADD CONSTRAINT `fk_laptopuri_utilizatori`
  FOREIGN KEY (`id_utilizator`)
  REFERENCES `magazin_deviceuri`.`utilizatori` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
CREATE TABLE IF NOT EXISTS`magazin_deviceuri`.`desktopuri` (
  `id_desktop` INT NOT NULL AUTO_INCREMENT,
  `id_utilizator` INT NOT NULL,
  `nume_sistem` VARCHAR(225) NOT NULL,
  `procesor` VARCHAR(225) NOT NULL,
  `placa_video` VARCHAR(225) NOT NULL,
  `capacitate_ram` INT NOT NULL,
  `tip_ram` VARCHAR(225) NOT NULL,
  `frecventa_ram` INT NOT NULL,
  `tip_stocare` VARCHAR(225) NOT NULL,
  `capacitate_stocare` INT NOT NULL,
  `sistem_de_operare` VARCHAR(225) NOT NULL,
  `culoare` VARCHAR(225) NOT NULL,
  `pret` FLOAT NOT NULL,
  PRIMARY KEY (`id_desktop`),
  UNIQUE INDEX `id_desktop_UNIQUE` (`id_desktop` ASC) VISIBLE);

ALTER TABLE `magazin_deviceuri`.`desktopuri` 
ADD INDEX `fk_desktopuri_utilizatori_idx` (`id_utilizator` ASC) VISIBLE;
;
ALTER TABLE `magazin_deviceuri`.`desktopuri` 
ADD CONSTRAINT `fk_desktopuri_utilizatori`
  FOREIGN KEY (`id_utilizator`)
  REFERENCES `magazin_deviceuri`.`utilizatori` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


insert into utilizatori (id,nume,username,parola,rol) values (1,'User1','user1','$2a$10$8zXaE0Tm61rAyTLukVsasOElZKgbbmPUObYHmik5Hz3g9m29iitFe','ROLE_USER');
insert into utilizatori (id,nume,username,parola,rol) values (2,'User2','user2','$2a$10$n/wOgoAsYGsBaLbw/aCErOzHsGKYyTn2EPDvdGiRPdxuGxLucNHTC','ROLE_EDITOR');
insert into utilizatori (id,nume,username,parola,rol) values (3,'User3','user3','$2a$10$NKujn6sPMHvk7f02TlnAf.t6BoA9SKXVJWxRi8VmRX3eVE4GMn/Ny','ROLE_ADMIN');

insert into laptopuri (id_utilizator, nume_laptop, procesor, placa_video, capacitate_RAM, tip_RAM, frecventa_RAM, tip_stocare, capacitate_stocare, rezolutie_ecran, dimensiune_ecran, sistem_de_operare, culoare, pret)
	values (2, 'Laptop Gaming LENOVO LOQ 15ARP9', 'AMD Ryzen 7 7435HS', 'NVIDIA GeForce RTX 4050 6GB', 24, 'DDR5', 4800, 'SSD', 512, 'FHD', 15.6, 'Free DOS', 'Luna Grey', 4100);
insert into laptopuri (id_utilizator,nume_laptop, procesor, placa_video, capacitate_RAM, tip_RAM, frecventa_RAM, tip_stocare, capacitate_stocare, rezolutie_ecran, dimensiune_ecran, sistem_de_operare, culoare, pret)
	values (3, 'Laptop Gaming MSI Cyborg 15 B13WFKG-689XRO', 'Intel Core i7 13620H', 'NVIDIA GeForce RTX 5060 8GB', 32, 'DDR5', 5600, 'SSD', 1024, 'FHD', 15.6, 'Linux', 'Negru', 5800);

insert into desktopuri (id_utilizator,nume_sistem, procesor, placa_video, capacitate_RAM, tip_RAM, frecventa_RAM, tip_stocare, capacitate_stocare, sistem_de_operare, culoare, pret)
	values (2, 'Sistem Desktop Gaming ASUS ROG Strix G700TF-7265KF046X', 'Intel Core Ultra 7 265KF', 'NVIDIA GeForce RTX 5070 PRIME 12GB', 16, 'DDR5', 5600, 'SSD', 1024, 'Windows', 'Negru', 10150);
insert into desktopuri (id_utilizator,nume_sistem, procesor, placa_video, capacitate_RAM, tip_RAM, frecventa_RAM, tip_stocare, capacitate_stocare, sistem_de_operare, culoare, pret)
	values (3, 'Sistem Desktop Gaming ACER Aspire TC101-13H5U', 'Intel Core i5-13420H', 'NVIDIA GeForce RTX 5060 8GB', 32, 'DDR4', 3200, 'SSD', 1024, 'Free DOS', 'Alb', 5700);


