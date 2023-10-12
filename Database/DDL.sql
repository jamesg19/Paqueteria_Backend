-- MySQL Script generated by MySQL Workbench
-- mié 11 oct 2023 22:26:17
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
-- Table `paqueteria`.`Departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Departamento` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Departamento` (
  `idDepartamento` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(145) NULL,
  PRIMARY KEY (`idDepartamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Municipio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Municipio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Municipio` (
  `idMunicipio` VARCHAR(10) NOT NULL,
  `idDepartamento` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idMunicipio`, `idDepartamento`),
  INDEX `fk_Municipio_Dep_idx` (`idDepartamento` ASC) VISIBLE,
  CONSTRAINT `fk_Municipio_Dep`
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `paqueteria`.`Departamento` (`idDepartamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Sucursal` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Sucursal` (
  `idSucursal` VARCHAR(10) NOT NULL,
  `idDepartamento` VARCHAR(10) NULL,
  `idMunicipio` VARCHAR(10) NOT NULL,
  `direccion` VARCHAR(145) NULL,
  `nombre` VARCHAR(200) NULL,
  `esEnlace` TINYINT NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`idSucursal`, `idMunicipio`),
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Asamblea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Asamblea` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Asamblea` (
  `idAsamblea` VARCHAR(10) NOT NULL,
  `fechaRealizada` DATE NULL,
  `lugarRealizada` VARCHAR(45) NULL,
  PRIMARY KEY (`idAsamblea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Honorario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Honorario` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Honorario` (
  `idHonorario` VARCHAR(10) NOT NULL,
  `idAsamblea` VARCHAR(10) NULL,
  `nombre` VARCHAR(145) NULL,
  `descripcion` VARCHAR(350) NULL,
  `monto` DOUBLE NULL,
  PRIMARY KEY (`idHonorario`),
  INDEX `fk_Honorario_Asamblea_idx` (`idAsamblea` ASC) VISIBLE,
  CONSTRAINT `fk_Honorario_Asamblea`
    FOREIGN KEY (`idAsamblea`)
    REFERENCES `paqueteria`.`Asamblea` (`idAsamblea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Persona` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Persona` (
  `idPersona` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(145) NULL,
  `edad` INT NULL,
  `domicilio` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Rol` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Rol` (
  `idRol` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(350) NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Personal` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Personal` (
  `idPersonal` VARCHAR(10) NOT NULL,
  `idPersona` VARCHAR(10) NOT NULL,
  `idRol` VARCHAR(10) NOT NULL,
  `idSucursal` VARCHAR(10) NOT NULL,
  `idHonorario` VARCHAR(10) NOT NULL,
  `pass` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersonal`),
  INDEX `fk_Personal_hon_idx` (`idHonorario` ASC) VISIBLE,
  INDEX `fk_Personal_per_idx` (`idPersona` ASC) VISIBLE,
  INDEX `fk_Personal_rol_idx` (`idRol` ASC) VISIBLE,
  INDEX `fk_Personal_sucrusal_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Personal_hon`
    FOREIGN KEY (`idHonorario`)
    REFERENCES `paqueteria`.`Honorario` (`idHonorario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Modelo_Vehiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Modelo_Vehiculo` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Modelo_Vehiculo` (
  `idModelo_Vehiculo` VARCHAR(10) NOT NULL,
  `marca` VARCHAR(45) NULL,
  `serie` VARCHAR(45) NULL,
  `tonelage` VARCHAR(45) NULL,
  `anio` VARCHAR(45) NULL,
  `noAsientos` INT NULL,
  `capacidadVol` DOUBLE NULL,
  `capacidadTon` DOUBLE NULL,
  PRIMARY KEY (`idModelo_Vehiculo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Vehiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Vehiculo` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Vehiculo` (
  `idVehiculo` VARCHAR(10) NOT NULL,
  `idModelo_Vehiculo` VARCHAR(10) NOT NULL,
  `idSucursal` INT NULL,
  `fechaCompra` DATETIME NULL,
  PRIMARY KEY (`idVehiculo`),
  INDEX `fk_Vehiculo_Modelo_idx` (`idModelo_Vehiculo` ASC) VISIBLE,
  INDEX `fk_Vehiculo_suc_idx` (`idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Vehiculo_Modelo`
    FOREIGN KEY (`idModelo_Vehiculo`)
    REFERENCES `paqueteria`.`Modelo_Vehiculo` (`idModelo_Vehiculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vehiculo_suc`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Usuario` (
  `idUsuarios` VARCHAR(10) NOT NULL,
  `idPersona` VARCHAR(10) NULL,
  PRIMARY KEY (`idUsuarios`),
  INDEX `fk_Usuarios_Persona_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_Persona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `paqueteria`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Envio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Envio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Envio` (
  `idEnvio` VARCHAR(10) NOT NULL,
  `idSucursalOrigen` VARCHAR(10) NULL,
  `idSucursalDestino` VARCHAR(10) NULL,
  `idUsuarioEmisor` VARCHAR(10) NULL,
  `idUsuarioReceptor` VARCHAR(10) NULL,
  `fechaLlegada` DATETIME NULL,
  `subTotal` INT NULL,
  PRIMARY KEY (`idEnvio`),
  INDEX `fk_Envio_SucursalOr_idx` (`idSucursalOrigen` ASC) VISIBLE,
  INDEX `fk_Envio_SucursalDes_idx` (`idSucursalDestino` ASC) VISIBLE,
  INDEX `fk_Envio_UsuarioEmisor_idx` (`idUsuarioEmisor` ASC) VISIBLE,
  INDEX `fk_Envio_UsuarioReceptor_idx` (`idUsuarioReceptor` ASC) VISIBLE,
  CONSTRAINT `fk_Envio_SucursalOr`
    FOREIGN KEY (`idSucursalOrigen`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_SucursalDes`
    FOREIGN KEY (`idSucursalDestino`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_UsuarioEmisor`
    FOREIGN KEY (`idUsuarioEmisor`)
    REFERENCES `paqueteria`.`Usuario` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Envio_UsuarioReceptor`
    FOREIGN KEY (`idUsuarioReceptor`)
    REFERENCES `paqueteria`.`Usuario` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Detalle_Envio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Detalle_Envio` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Detalle_Envio` (
  `idDetalleEnvio` VARCHAR(10) NOT NULL,
  `idEnvio` VARCHAR(10) NULL,
  `descripcion` VARCHAR(350) NULL,
  `peso` DOUBLE NULL,
  `volumen` DOUBLE NULL,
  PRIMARY KEY (`idDetalleEnvio`),
  INDEX `fk_DetalleEnvio_Envio_idx` (`idEnvio` ASC) VISIBLE,
  CONSTRAINT `fk_DetalleEnvio_Envio`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Tarifa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Tarifa` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Tarifa` (
  `idTarifa` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `precio` DOUBLE NULL,
  `idSucursalOrigen` INT NULL,
  `idSucursalDestino` INT NULL,
  PRIMARY KEY (`idTarifa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Ruta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Ruta` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Ruta` (
  `idRuta` VARCHAR(10) NOT NULL,
  `idSucursalOrigen` VARCHAR(10) NULL,
  `idSucursalDestino` VARCHAR(10) NULL,
  `distancia` DOUBLE NULL,
  `dias` INT NULL,
  `costo` DOUBLE NULL,
  PRIMARY KEY (`idRuta`),
  INDEX `fk_Ruta_SuOrigen_idx` (`idSucursalOrigen` ASC) VISIBLE,
  INDEX `fk_Ruta_SuDestino_idx` (`idSucursalDestino` ASC) VISIBLE,
  CONSTRAINT `fk_Ruta_SuOrigen`
    FOREIGN KEY (`idSucursalOrigen`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ruta_SuDestino`
    FOREIGN KEY (`idSucursalDestino`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Historico_Sucursales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Historico_Sucursales` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Historico_Sucursales` (
  `idHistorico` VARCHAR(10) NOT NULL,
  `idEnvio` VARCHAR(10) NULL,
  `idSucursal` VARCHAR(10) NULL,
  `fecha` DATETIME NULL,
  PRIMARY KEY (`idHistorico`),
  INDEX `fk_Historico_Sucursales_Envio_idx` (`idEnvio` ASC) VISIBLE,
  CONSTRAINT `fk_Historico_Sucursales_Envio`
    FOREIGN KEY (`idEnvio`)
    REFERENCES `paqueteria`.`Envio` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paqueteria`.`Gasto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paqueteria`.`Gasto` ;

CREATE TABLE IF NOT EXISTS `paqueteria`.`Gasto` (
  `idGasto` VARCHAR(10) NOT NULL,
  `idSucursal` VARCHAR(10) NULL,
  `idAsamblea` VARCHAR(10) NULL,
  `descripcion` VARCHAR(45) NULL,
  `moto` DOUBLE NULL,
  PRIMARY KEY (`idGasto`),
  INDEX `fk_Gasto_Sucursal_idx` (`idSucursal` ASC) VISIBLE,
  INDEX `fk_Gasto_Asamblea_idx` (`idAsamblea` ASC) VISIBLE,
  CONSTRAINT `fk_Gasto_Sucursal`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `paqueteria`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gasto_Asamblea`
    FOREIGN KEY (`idAsamblea`)
    REFERENCES `paqueteria`.`Asamblea` (`idAsamblea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
