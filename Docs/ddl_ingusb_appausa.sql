-- MySQL Workbench Synchronization
-- Generated: 2020-09-29 12:36
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: COMPURED

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `ingusb_appausa` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`empleado` (
  `num_documento` INT(10) NOT NULL,
  `tipo_documento` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `edad` INT(3) NOT NULL,
  `correo_electronico` VARCHAR(45) NOT NULL,
  `telefono` INT(10) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `nacionalidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`num_documento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`comentario` (
  `cod` INT(6) NOT NULL AUTO_INCREMENT,
  `contenido` TEXT(150) NOT NULL,
  `leido` TINYINT(4) NOT NULL,
  `respuesta` VARCHAR(45) NULL DEFAULT NULL,
  `fecha` DATETIME NOT NULL,
  `cuenta_empleado` INT(10) NOT NULL,
  PRIMARY KEY (`cod`),
  INDEX `fk_comentario_cuenta_idx` (`cuenta_empleado` ASC),
  CONSTRAINT `fk_comentario_cuenta`
    FOREIGN KEY (`cuenta_empleado`)
    REFERENCES `ingusb_appausa`.`cuenta` (`empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`empresa` (
  `nit` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `correo_electronico` VARCHAR(45) NOT NULL,
  `descrip_empresa` TEXT(120) NOT NULL,
  `num_empleados` INT(3) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nit`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`cuenta` (
  `empleado` INT(10) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `ultimo_acceso` DATETIME NOT NULL,
  `puntaje_total` VARCHAR(45) NOT NULL DEFAULT 0,
  `puntaje_mes` VARCHAR(45) NOT NULL DEFAULT 0,
  `tiempo_total` TIME NOT NULL DEFAULT '00:00:00',
  `tiempo_mes` TIME NOT NULL DEFAULT '00:00:00',
  UNIQUE INDEX `uk_username` (`username` ASC),
  PRIMARY KEY (`empleado`),
  CONSTRAINT `fk_cuenta_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `ingusb_appausa`.`empleado` (`num_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`juego` (
  `id` INT(6) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `puntaje` INT(3) NOT NULL,
  `mecanica` TEXT(150) NOT NULL,
  `url_descarga` VARCHAR(45) NOT NULL,
  `dispositivo` VARCHAR(45) NOT NULL,
  `link_app` VARCHAR(45) NULL DEFAULT NULL,
  `tipo_juego_id` INT(3) NOT NULL,
  PRIMARY KEY (`id`, `tipo_juego_id`),
  INDEX `fk_juego_tipo_juego_idx` (`tipo_juego_id` ASC),
  CONSTRAINT `fk_juego_tipo_juego`
    FOREIGN KEY (`tipo_juego_id`)
    REFERENCES `ingusb_appausa`.`tipo_juego` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`incapacidad` (
  `cod` INT(10) NOT NULL,
  `inicio_incapacidad` DATE NOT NULL,
  `fin_incapacidad` DATE NOT NULL,
  `tipo_incapacidad` VARCHAR(45) NOT NULL,
  `indicaciones` TEXT(150) NOT NULL,
  `consulta_medica` INT(6) NOT NULL,
  PRIMARY KEY (`cod`, `consulta_medica`),
  INDEX `fk_incapacidad_tipo_idx` (`tipo_incapacidad` ASC),
  INDEX `fk_incapacidad_consulta_medica_idx` (`consulta_medica` ASC),
  CONSTRAINT `fk_incapacidad_tipo`
    FOREIGN KEY (`tipo_incapacidad`)
    REFERENCES `ingusb_appausa`.`tipo_incapacidad` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incapacidad_consulta_medica`
    FOREIGN KEY (`consulta_medica`)
    REFERENCES `ingusb_appausa`.`consulta_medica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`contrato` (
  `num_contrato` INT(11) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  `sueldo` DECIMAL NOT NULL,
  `funciones` TEXT(150) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `representante` TINYINT(4) NOT NULL,
  `empresa` VARCHAR(45) NOT NULL,
  `empleado` INT(10) NOT NULL,
  `tipo_contrato` VARCHAR(45) NOT NULL,
  `nivel_riesgo` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`num_contrato`, `empresa`, `empleado`),
  INDEX `fk_contrato_tipo_contrato_idx` (`tipo_contrato` ASC),
  INDEX `fk_contrato_empresa_idx` (`empresa` ASC),
  INDEX `fk_contrato_empleado_idx` (`empleado` ASC),
  INDEX `fk_contrato_nivel_riesgo_idx` (`nivel_riesgo` ASC),
  CONSTRAINT `fk_contrato_tipo_contrato`
    FOREIGN KEY (`tipo_contrato`)
    REFERENCES `ingusb_appausa`.`tipo_contrato` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_empresa`
    FOREIGN KEY (`empresa`)
    REFERENCES `ingusb_appausa`.`empresa` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `ingusb_appausa`.`empleado` (`num_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_nivel_riesgo`
    FOREIGN KEY (`nivel_riesgo`)
    REFERENCES `ingusb_appausa`.`nivel_riesgo` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`rol` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrpcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_evento` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripción` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`entidad` (
  `nit` VARCHAR(17) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `telefono` INT(10) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nit`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`nivel_riesgo` (
  `nombre` VARCHAR(8) NOT NULL,
  `descripción` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`afeccion_medica` (
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  `indicaciones` TEXT(120) NOT NULL,
  PRIMARY KEY (`nombre`),
  INDEX `fk_afecion_tipo_idx` (`tipo` ASC),
  CONSTRAINT `fk_afecion_tipo`
    FOREIGN KEY (`tipo`)
    REFERENCES `ingusb_appausa`.`tipo_afeccion` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_afeccion` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripción` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_incapacidad` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT(150) NOT NULL,
  `periodo_max` INT(3) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_licencia` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `remunerada` TINYINT(4) NOT NULL,
  `minimo_dia` INT(3) NULL DEFAULT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_contrato` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`rol_cuenta` (
  `rol` VARCHAR(45) NOT NULL,
  `cuenta_empleado` INT(10) NOT NULL,
  PRIMARY KEY (`rol`, `cuenta_empleado`),
  INDEX `fk_rol_cuenta_rol_idx` (`rol` ASC),
  INDEX `fk_rol_cuenta_cuenta_idx` (`cuenta_empleado` ASC),
  CONSTRAINT `fk_rol_cuenta_rol`
    FOREIGN KEY (`rol`)
    REFERENCES `ingusb_appausa`.`rol` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_cuenta_cuenta`
    FOREIGN KEY (`cuenta_empleado`)
    REFERENCES `ingusb_appausa`.`cuenta` (`empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`afiliacion_empleado` (
  `empleado` INT(10) NOT NULL,
  `entidad` VARCHAR(17) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`empleado`, `entidad`),
  INDEX `fk_afiliacion_empleado_entidad_idx` (`entidad` ASC),
  INDEX `fk_afiliacion_empleado_empleado_idx` (`empleado` ASC),
  CONSTRAINT `fk_afiliaciones_empleado_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `ingusb_appausa`.`empleado` (`num_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_afiliaciones_empleado_entidad`
    FOREIGN KEY (`entidad`)
    REFERENCES `ingusb_appausa`.`entidad` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`afiliacion_contrato` (
  `num_contrato` INT(11) NOT NULL,
  `empresa` VARCHAR(45) NOT NULL,
  `empleado` INT(10) NOT NULL,
  `entidad` VARCHAR(17) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`num_contrato`, `empresa`, `empleado`, `entidad`),
  INDEX `fk_afiliacion_contrato_entidad_idx` (`entidad` ASC),
  INDEX `fk_afiliacion_contrato_contrato_idx` (`num_contrato` ASC, `empresa` ASC, `empleado` ASC),
  CONSTRAINT `fk_afiliacion_contrato_contrato`
    FOREIGN KEY (`num_contrato` , `empresa` , `empleado`)
    REFERENCES `ingusb_appausa`.`contrato` (`num_contrato` , `empresa` , `empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_afiliacion_contrato_entidad`
    FOREIGN KEY (`entidad`)
    REFERENCES `ingusb_appausa`.`entidad` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`afiliacion_empresa` (
  `empresa` VARCHAR(45) NOT NULL,
  `entidad` VARCHAR(17) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`empresa`, `entidad`),
  INDEX `fk_afiliacion_empresa_entidad_idx` (`entidad` ASC),
  INDEX `fk_afiliacion_empresa_empresa_idx` (`empresa` ASC),
  CONSTRAINT `fk_afiliacion_empresa_empresa`
    FOREIGN KEY (`empresa`)
    REFERENCES `ingusb_appausa`.`empresa` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_afiliacion_empresa_entidad`
    FOREIGN KEY (`entidad`)
    REFERENCES `ingusb_appausa`.`entidad` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`consulta_medica` (
  `id` INT(6) NOT NULL,
  `observaciones` VARCHAR(45) NOT NULL,
  `fecha` VARCHAR(45) NOT NULL,
  `peso` VARCHAR(45) NOT NULL,
  `estatura` VARCHAR(45) NOT NULL,
  `medico` VARCHAR(45) NULL DEFAULT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `empleado` INT(10) NOT NULL,
  `entidad` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_consulta_medica_empleado_idx` (`empleado` ASC),
  INDEX `fk_consulta_medica_entidad_idx` (`entidad` ASC),
  CONSTRAINT `fk_consulta_medica_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `ingusb_appausa`.`empleado` (`num_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consulta_medica_entidad`
    FOREIGN KEY (`entidad`)
    REFERENCES `ingusb_appausa`.`entidad` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`afeccion_empleado` (
  `afeccion_medica` VARCHAR(45) NOT NULL,
  `empleado` INT(10) NOT NULL,
  `fecha_diagnostico` VARCHAR(45) NOT NULL,
  `entidad` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`afeccion_medica`, `empleado`),
  INDEX `fk_afeccion_empleado_empleado_idx` (`empleado` ASC),
  INDEX `fk_afeccion_empleado_afeccion_idx` (`afeccion_medica` ASC),
  INDEX `fk_afeccion_empleado_entidad_idx` (`entidad` ASC),
  CONSTRAINT `fk_afeccion_empleado_afeccion_medica`
    FOREIGN KEY (`afeccion_medica`)
    REFERENCES `ingusb_appausa`.`afeccion_medica` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_afeccion_empleado_empleado`
    FOREIGN KEY (`empleado`)
    REFERENCES `ingusb_appausa`.`empleado` (`num_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_afeccion_empleado_entidad`
    FOREIGN KEY (`entidad`)
    REFERENCES `ingusb_appausa`.`entidad` (`nit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`contrato_licencia` (
  `contrato` INT(11) NOT NULL,
  `contrato_empresa` VARCHAR(45) NOT NULL,
  `contrato_usuario` INT(10) NOT NULL,
  `tipo_licencia` VARCHAR(45) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`contrato`, `contrato_empresa`, `contrato_usuario`, `tipo_licencia`),
  INDEX `fk_contrato_licencia_tipo_licencia_idx` (`tipo_licencia` ASC),
  INDEX `fk_contrato_licencia_contrato_idx` (`contrato` ASC, `contrato_empresa` ASC, `contrato_usuario` ASC),
  CONSTRAINT `fk_contrato_licencia_contrato`
    FOREIGN KEY (`contrato` , `contrato_empresa` , `contrato_usuario`)
    REFERENCES `ingusb_appausa`.`contrato` (`num_contrato` , `empresa` , `empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_licencia_tipo_licencia`
    FOREIGN KEY (`tipo_licencia`)
    REFERENCES `ingusb_appausa`.`tipo_licencia` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`partida` (
  `cuenta` INT(10) NOT NULL,
  `juego` INT(6) NOT NULL,
  `inicio` DATETIME NOT NULL,
  `fin` DATETIME NULL DEFAULT NULL,
  `puntaje` INT(11) NOT NULL,
  `duracion` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`cuenta`, `juego`),
  INDEX `fk_partida_juego_idx` (`juego` ASC),
  INDEX `fk_partida_cuenta_idx` (`cuenta` ASC),
  CONSTRAINT `fk_partida_cuenta`
    FOREIGN KEY (`cuenta`)
    REFERENCES `ingusb_appausa`.`cuenta` (`empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partida_juego`
    FOREIGN KEY (`juego`)
    REFERENCES `ingusb_appausa`.`juego` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`tipo_juego` (
  `id` INT(3) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ingusb_appausa`.`log` (
  `id` INT(8) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `mensaje` VARCHAR(45) NOT NULL,
  `evento_nombre` VARCHAR(45) NOT NULL,
  `cuenta` INT(10) NOT NULL,
  `tipo_evento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_log_cuenta_idx` (`cuenta` ASC),
  INDEX `fk_log_tipo_evento_idx` (`tipo_evento` ASC),
  CONSTRAINT `fk_log_cuenta`
    FOREIGN KEY (`cuenta`)
    REFERENCES `ingusb_appausa`.`cuenta` (`empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_tipo_evento`
    FOREIGN KEY (`tipo_evento`)
    REFERENCES `ingusb_appausa`.`tipo_evento` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE USER IF NOT EXISTS `ingusb_appausa`@`%` IDENTIFIED BY "pausapp_2020";
GRANT ALL PRIVILEGES ON `ingusb_appausa`.* TO `ingusb_appausa`@`%` WITH GRANT OPTION;
FLUSH PRIVILEGES;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
