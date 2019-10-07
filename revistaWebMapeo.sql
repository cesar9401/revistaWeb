
CREATE SCHEMA IF NOT EXISTS `revistaWeb` ;
USE `revistaWeb` ;

-- -----------------------------------------------------
-- Table `revistaWeb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`usuario` (
  `idUsuario` VARCHAR(30) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(35) NULL,
  `apellidos` VARCHAR(30) NULL,
  `ubicacion` VARCHAR(30) NULL,
  `fechaNac` DATE NULL,
  `sexo` VARCHAR(9) NULL,
  `password` VARCHAR(30) NOT NULL,
  `hobbies` TEXT(1000) NULL,
  `descripcion` TEXT(1000) NULL,
  `foto` MEDIUMBLOB NULL,
  `editor` TINYINT NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`revista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`revista` (
  `idRevista` INT NOT NULL AUTO_INCREMENT,
  `tituloRevista` VARCHAR(45) NOT NULL,
  `archivoPdf` MEDIUMBLOB NOT NULL,
  `categoria` VARCHAR(100) NOT NULL,
  `descripcion` TEXT(1000) NOT NULL,
  `edicion` INT NOT NULL,
  `cuotaSuscripcion` DECIMAL(10,2) NOT NULL,
  `reaccion` TINYINT NOT NULL,
  `comentarios` TINYINT NOT NULL,
  `fechaPubl` DATE NULL,
  `suscripciones` INT NULL DEFAULT 0,
  `bloquear` TINYINT NOT NULL,
  `usuario_idUsuario` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idRevista`),
  CONSTRAINT `fk_revista_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `revistaWeb`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`suscripcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`suscripcion` (
  `idSuscripcion` INT NOT NULL AUTO_INCREMENT,
  `revista_idRevista` INT NOT NULL,
  `usuario_idUsuario` VARCHAR(30) NOT NULL,
  `fechaSusc` DATE NULL,
  PRIMARY KEY (`idSuscripcion`),
  CONSTRAINT `fk_suscripcion_revista1`
    FOREIGN KEY (`revista_idRevista`)
    REFERENCES `revistaWeb`.`revista` (`idRevista`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_suscripcion_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `revistaWeb`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`registroUtilidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`registroUtilidades` (
  `idRegistroUtilidades` INT NOT NULL AUTO_INCREMENT,
  `revista_idRevista` INT NOT NULL,
  `porcentaje` DECIMAL(4,3) NULL,
  `costoDia` DECIMAL(8,2) NULL,
  `totalSuscripcion` DECIMAL(10,2) NULL DEFAULT 0,
  `totalSuscripcionEditor` DECIMAL(10,2) NULL DEFAULT 0,
  `totalSuscripcionPagina` DECIMAL(10,2) NULL DEFAULT 0,
  `totalCosto` DECIMAL(10,2) NULL DEFAULT 0,
  PRIMARY KEY (`idRegistroUtilidades`),
  CONSTRAINT `fk_precios_revista1`
    FOREIGN KEY (`revista_idRevista`)
    REFERENCES `revistaWeb`.`revista` (`idRevista`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`comentario` (
  `idComentario` INT NOT NULL AUTO_INCREMENT,
  `suscripcion_idSuscripcion` INT NOT NULL,
  `idRevista` INT NOT NULL,
  `idUsuario` VARCHAR(30) NOT NULL,
  `comentario` VARCHAR(300) NOT NULL,
  `fechaComentario` DATE NOT NULL,
  PRIMARY KEY (`idComentario`),
  CONSTRAINT `fk_comentario_suscripcion1`
    FOREIGN KEY (`suscripcion_idSuscripcion`)
    REFERENCES `revistaWeb`.`suscripcion` (`idSuscripcion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`metodosPago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`metodosPago` (
  `tarjetaCredito` BIGINT(16) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `vencimiento` DATE NOT NULL,
  `codigoSeguridad` VARCHAR(45) NOT NULL,
  `usuario_idUsuario` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`tarjetaCredito`),
  CONSTRAINT `fk_metodosPago_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `revistaWeb`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`reaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`reaccion` (
  `idReaccion` INT NOT NULL AUTO_INCREMENT,
  `suscripcion_idSuscripcion` INT NOT NULL,
  `tipoReaccion` TINYINT NOT NULL,
  `fecha` DATE NOT NULL,
  `edicionRevista` INT NULL,
  PRIMARY KEY (`idReaccion`),
  CONSTRAINT `fk_reaccion_suscripcion1`
    FOREIGN KEY (`suscripcion_idSuscripcion`)
    REFERENCES `revistaWeb`.`suscripcion` (`idSuscripcion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`administrador` (
  `usuarioAdmin` VARCHAR(30) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuarioAdmin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`edicionesRevistas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`edicionesRevistas` (
  `idEdicion` INT NOT NULL,
  `revista_idRevista` INT NOT NULL,
  `edicionRevista` INT NOT NULL,
  `archivoPdf` MEDIUMBLOB NOT NULL,
  `fechaPublicacion` DATE NULL,
  PRIMARY KEY (`idEdicion`),
  CONSTRAINT `fk_edicionesRevistas_revista1`
    FOREIGN KEY (`revista_idRevista`)
    REFERENCES `revistaWeb`.`revista` (`idRevista`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`registroIngresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`registroIngresos` (
  `idRegistroIngresos` INT NOT NULL AUTO_INCREMENT,
  `suscripcion_idSuscripcion` INT NOT NULL,
  `fechaCobro` DATE NOT NULL,
  `cuotaSuscripcion` DECIMAL(10,2) NOT NULL,
  `porcentajeEditor` DECIMAL(10,2) NOT NULL,
  `porcentajePagina` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idRegistroIngresos`, `suscripcion_idSuscripcion`),
  CONSTRAINT `fk_ingresos_suscripcion1`
    FOREIGN KEY (`suscripcion_idSuscripcion`)
    REFERENCES `revistaWeb`.`suscripcion` (`idSuscripcion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`registroCostos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`registroCostos` (
  `idRegistroCostos` INT NOT NULL AUTO_INCREMENT,
  `revista_idRevista` INT NOT NULL,
  `cuotaDia` DECIMAL(10,2) NOT NULL,
  `fechaCobro` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRegistroCostos`, `revista_idRevista`),
  CONSTRAINT `fk_registrosCostos_revista1`
    FOREIGN KEY (`revista_idRevista`)
    REFERENCES `revistaWeb`.`revista` (`idRevista`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`etiquetas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`etiquetas` (
  `idEtiquetas` INT NOT NULL AUTO_INCREMENT,
  `etiqueta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEtiquetas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `revistaWeb`.`revista_has_etiquetas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revistaWeb`.`revista_has_etiquetas` (
  `revista_idRevista` INT NOT NULL,
  `etiquetas_idEtiquetas` INT NOT NULL,
  PRIMARY KEY (`revista_idRevista`, `etiquetas_idEtiquetas`),
  CONSTRAINT `fk_revista_has_etiquetas_revista1`
    FOREIGN KEY (`revista_idRevista`)
    REFERENCES `revistaWeb`.`revista` (`idRevista`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_revista_has_etiquetas_etiquetas1`
    FOREIGN KEY (`etiquetas_idEtiquetas`)
    REFERENCES `revistaWeb`.`etiquetas` (`idEtiquetas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
