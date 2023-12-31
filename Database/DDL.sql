-- MySQL Script generated by MySQL Workbench
-- lun 20 nov 2023 19:09:59
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema paqueteria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `paqueteria` ;

-- -----------------------------------------------------
-- Schema paqueteria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paqueteria` ;
USE `paqueteria` ;

-- -----------------------------------------------------
-- Table `paqueteria`.`Asamblea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Asamblea` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Asamblea` (
  `idAsamblea` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaRealizada` DATE NULL DEFAULT NULL,
  `lugarRealizada` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idAsamblea`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Departamento` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Departamento` (
  `idDepartamento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(145) NULL DEFAULT NULL,
  `idDepartamento1` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idDepartamento`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Persona` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Persona` (
  `idPersona` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(145) NULL DEFAULT NULL,
  `edad` INT(11) NULL DEFAULT NULL,
  `domicilio` VARCHAR(45) NULL DEFAULT NULL,
  `celular` VARCHAR(45) NULL DEFAULT NULL,
  `nit` INT(11) NOT NULL,
  `password` VARCHAR(45) NULL,
  `rol` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE INDEX `nit_UNIQUE` (`nit` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Municipio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Municipio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Municipio` (
  `idMunicipio` INT(11) NOT NULL AUTO_INCREMENT,
  `idDepartamento` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idMunicipio`),
  INDEX `fk_Municipio_Dep_idx` (`idDepartamento` ASC) VISIBLE,
  CONSTRAINT `fk_Municipio_Dep`
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `paqueteria`.`Departamento` (`idDepartamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22018
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Sucursal` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Sucursal` (
  `idSucursal` INT(11) NOT NULL AUTO_INCREMENT,
  `idDepartamento` INT(11) NULL DEFAULT NULL,
  `idMunicipio` INT(11) NULL DEFAULT NULL,
  `direccion` VARCHAR(145) NULL DEFAULT NULL,
  `nombre` VARCHAR(200) NULL DEFAULT NULL,
  `esEnlace` TINYINT(4) NULL DEFAULT NULL,
  `estado` TINYINT(4) NULL DEFAULT NULL,
  `longitud` DOUBLE NULL DEFAULT NULL,
  `latitud` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idSucursal`),
  INDEX `fk_Sucursal_dep_idx` (`idDepartamento` ASC) VISIBLE,
  INDEX `fk_Sucursal_muni_idx` (`idMunicipio` ASC) VISIBLE,
  CONSTRAINT `fk_Sucursal_dep`
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `paqueteria`.`Departamento` (`idDepartamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_muni`
    FOREIGN KEY (`idMunicipio`)
    REFERENCES `paqueteria`.`Municipio` (`idMunicipio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 63
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Envio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Envio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Envio` (
  `idEnvio` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursalOrigen` INT(11) NULL DEFAULT NULL,
  `idSucursalDestino` INT(11) NULL DEFAULT NULL,
  `nitEmisor` INT(11) NULL DEFAULT NULL,
  `nitReceptor` INT(11) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `total` INT(11) NULL DEFAULT NULL,
  `peso` DOUBLE NULL,
  `volumen` DOUBLE NULL,
  `diasTranscurridos` INT NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idEnvio`),
  INDEX `fk_Envio_SucursalOr_idx` (`idSucursalOrigen` ASC) VISIBLE,
  INDEX `fk_Envio_SucursalDes_idx` (`idSucursalDestino` ASC) VISIBLE,
  INDEX `fk_Envio_Emisor_idx` (`nitEmisor` ASC) VISIBLE,
  INDEX `fk_Envio_Receptor_idx` (`nitReceptor` ASC) VISIBLE,
  CONSTRAINT `fk_Envio_Emisor`
    FOREIGN KEY (`nitEmisor`)
    REFERENCES `paqueteria`.`Persona` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_Receptor`
    FOREIGN KEY (`nitReceptor`)
    REFERENCES `paqueteria`.`Persona` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_SucursalDes`
    FOREIGN KEY (`idSucursalDestino`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_SucursalOr`
    FOREIGN KEY (`idSucursalOrigen`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Detalle_Envio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Detalle_Envio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Detalle_Envio` (
  `idDetalleEnvio` INT(11) NOT NULL AUTO_INCREMENT,
  `idEnvio` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(350) NULL DEFAULT NULL,
  `peso` DOUBLE NULL DEFAULT NULL,
  `volumen` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idDetalleEnvio`),
  INDEX `fk_DetalleEnvio_Envio_idx` (`idEnvio` ASC) VISIBLE,
  CONSTRAINT `fk_DetalleEnvio_Envio`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Gasto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Gasto` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Gasto` (
  `idGasto` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `monto` DOUBLE NULL DEFAULT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  `Asamblea_idAsamblea` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idGasto`),
  INDEX `fk_Gasto_Sucursal_idx` (`idSucursal` ASC) VISIBLE,
  INDEX `fk_Gasto_Asamblea1_idx` (`Asamblea_idAsamblea` ASC) VISIBLE,
  CONSTRAINT `fk_Gasto_Sucursal`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gasto_Asamblea1`
    FOREIGN KEY (`Asamblea_idAsamblea`)
    REFERENCES `paqueteria`.`Asamblea` (`idAsamblea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Historico_Sucursales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Historico_Sucursales` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Historico_Sucursales` (
  `idHistorico` INT(11) NOT NULL AUTO_INCREMENT,
  `idEnvio` INT(11) NULL DEFAULT NULL,
  `idSucursal` INT(11) NULL DEFAULT NULL,
  `idVehiculo` INT(11) NOT NULL,
  `fecha` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`idHistorico`),
  INDEX `fk_Historico_Sucursales_Envio_idx` (`idEnvio` ASC) VISIBLE,
  CONSTRAINT `fk_Historico_Sucursales_Envio`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Rol` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Rol` (
  `idRol` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(350) NULL DEFAULT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Personal` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Personal` (
  `idPersonal` INT(11) NOT NULL AUTO_INCREMENT,
  `idPersona` INT(11) NOT NULL,
  `idRol` INT(11) NOT NULL,
  `idSucursal` INT(11) NOT NULL,
  PRIMARY KEY (`idPersonal`),
  INDEX `fk_Personal_per_idx` (`idPersona` ASC) VISIBLE,
  INDEX `fk_Personal_rol_idx` (`idRol` ASC) VISIBLE,
  INDEX `fk_Personal_sucrusal_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Personal_per`
    FOREIGN KEY (`idPersona`)
    REFERENCES `paqueteria`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personal_rol`
    FOREIGN KEY (`idRol`)
    REFERENCES `paqueteria`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personal_sucrusal`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Honorario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Honorario` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Honorario` (
  `idHonorario` INT(11) NOT NULL AUTO_INCREMENT,
  `idAsamblea` INT(11) NULL DEFAULT NULL,
  `nombre` VARCHAR(145) NULL DEFAULT NULL,
  `descripcion` VARCHAR(350) NULL DEFAULT NULL,
  `monto` DOUBLE NULL DEFAULT NULL,
  `Personal_idPersonal` INT(11) NOT NULL,
  PRIMARY KEY (`idHonorario`),
  INDEX `fk_Honorario_Asamblea_idx` (`idAsamblea` ASC) VISIBLE,
  INDEX `fk_Honorario_Personal1_idx` (`Personal_idPersonal` ASC) VISIBLE,
  CONSTRAINT `fk_Honorario_Asamblea`
    FOREIGN KEY (`idAsamblea`)
    REFERENCES `paqueteria`.`Asamblea` (`idAsamblea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Honorario_Personal1`
    FOREIGN KEY (`Personal_idPersonal`)
    REFERENCES `paqueteria`.`Personal` (`idPersonal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Ruta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Ruta` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Ruta` (
  `idRuta` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursalOrigen` INT(11) NULL DEFAULT NULL,
  `idSucursalDestino` INT(11) NULL DEFAULT NULL,
  `distancia` DOUBLE NULL DEFAULT NULL,
  `dias` INT(11) NULL DEFAULT NULL,
  `costo` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idRuta`),
  INDEX `fk_Ruta_SuOrigen_idx` (`idSucursalOrigen` ASC) VISIBLE,
  INDEX `fk_Ruta_SuDestino_idx` (`idSucursalDestino` ASC) VISIBLE,
  CONSTRAINT `fk_Ruta_SuDestino`
    FOREIGN KEY (`idSucursalDestino`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ruta_SuOrigen`
    FOREIGN KEY (`idSucursalOrigen`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 131
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Tarifa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Tarifa` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Tarifa` (
  `idTarifa` INT(11) NOT NULL,
  `Nombre` VARCHAR(45) NULL DEFAULT NULL,
  `Descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `precio` DOUBLE NULL DEFAULT NULL,
  `idSucursalOrigen` INT(11) NULL DEFAULT NULL,
  `idSucursalDestino` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idTarifa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Vehiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Vehiculo` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Vehiculo` (
  `idVehiculo` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` INT(11) NULL,
  `marca` VARCHAR(45) NULL DEFAULT NULL,
  `tipo` VARCHAR(255) NULL DEFAULT NULL,
  `placa` VARCHAR(45) NULL DEFAULT NULL,
  `tonelage` VARCHAR(45) NULL DEFAULT NULL,
  `anio` VARCHAR(45) NULL DEFAULT NULL,
  `noAsientos` INT(11) NULL DEFAULT NULL,
  `capacidadVol` DOUBLE NULL DEFAULT NULL,
  `capacidadTon` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idVehiculo`),
  INDEX `fk_Vehiculo_1_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Vehiculo_1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `paqueteria`.`Pasos_Envio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Pasos_Envio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Pasos_Envio` (
  `idPasos_Envio` INT NOT NULL AUTO_INCREMENT,
  `idEnvio` INT(11) NULL,
  `idSucursal` INT(11) NULL,
  PRIMARY KEY (`idPasos_Envio`),
  INDEX `fk_Pasos_Envio_1_idx` (`idEnvio` ASC) VISIBLE,
  INDEX `fk_Pasos_Envio_2_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Pasos_Envio_1`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pasos_Envio_2`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Envio_Atrasado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Envio_Atrasado` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Envio_Atrasado` (
  `idEnvio_Atrasado` INT NOT NULL AUTO_INCREMENT,
  `idEnvio` INT(11) NULL,
  `idSucursal` INT(11) NULL,
  PRIMARY KEY (`idEnvio_Atrasado`),
  INDEX `fk_Envio_Atrasado_1_idx` (`idEnvio` ASC) VISIBLE,
  INDEX `fk_Envio_Atrasado_2_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Envio_Atrasado_1`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_Atrasado_2`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Historial_Sucursales_Vehiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Historial_Sucursales_Vehiculo` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Historial_Sucursales_Vehiculo` (
  `idHistorial_Sucursales_Vehiculo` INT NOT NULL AUTO_INCREMENT,
  `idVehiculo` INT(11) NULL,
  `idSucursal` INT(11) NULL,
  `ocupacion` DOUBLE NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`idHistorial_Sucursales_Vehiculo`),
  INDEX `fk_Historial_Sucursales_Vehiculo_1_idx` (`idSucursal` ASC) VISIBLE,
  INDEX `fk_Historial_Sucursales_Vehiculo_2_idx` (`idVehiculo` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Sucursales_Vehiculo_1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Sucursales_Vehiculo_2`
    FOREIGN KEY (`idVehiculo`)
    REFERENCES `paqueteria`.`Vehiculo` (`idVehiculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `paqueteria`.`Variables_Mejora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Variables_Mejora` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Variables_Mejora` (
  `idVariables_Mejora` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `valor` INT NULL,
  PRIMARY KEY (`idVariables_Mejora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`ReportesTem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`ReportesTem` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`ReportesTem` (
  `idReportesTem` INT NOT NULL AUTO_INCREMENT,
  `origen` INT NULL,
  `destino` INT NULL,
  `vehiculo` INT NULL,
  `numeroEnviados` INT NULL,
  `ocupacion` INT NULL,
  PRIMARY KEY (`idReportesTem`))
ENGINE = InnoDB;

USE `paqueteria` ;

-- -----------------------------------------------------
-- Placeholder table for view `paqueteria`.`cantidad_envio_por_sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paqueteria`.`cantidad_envio_por_sucursal` (`id` INT, `idSucursalOrigen` INT, `idSucursalDestino` INT, `cantidadEnvios` INT);

-- -----------------------------------------------------
-- View `paqueteria`.`cantidad_envio_por_sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`cantidad_envio_por_sucursal`;
DROP VIEW IF EXISTS `paqueteria`.`cantidad_envio_por_sucursal` ;
USE `paqueteria`;
CREATE OR REPLACE VIEW cantidad_envio_por_sucursal as SELECT  UUID() as id,E.idSucursalOrigen, E.idSucursalDestino, COUNT(E.idEnvio) AS cantidadEnvios FROM Sucursal S JOIN Envio E ON S.idSucursal = E.idSucursalOrigen WHERE
    E.estado = 'enRuta' GROUP BY E.idSucursalOrigen, E.idSucursalDestino;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
