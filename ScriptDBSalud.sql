SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Horario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Horario` (
  `idHorario` INT NOT NULL ,
  `horaInicio` TIME NOT NULL ,
  `horaFin` TIME NOT NULL ,
  `Tipo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idHorario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cotacto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Cotacto` (
  `idCotacto` INT NOT NULL ,
  `nombre` VARCHAR(50) NOT NULL ,
  `telefono` VARCHAR(45) NOT NULL ,
  `celular` VARCHAR(45) NOT NULL ,
  `correoElectronico` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idCotacto`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Servicios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Servicios` (
  `idServicios` INT NOT NULL ,
  `nombre` VARCHAR(150) NOT NULL ,
  `información` VARCHAR(500) NOT NULL ,
  `sitioWeb` VARCHAR(150) NOT NULL ,
  `Horario_idHorario` INT NOT NULL ,
  `Cotacto_idCotacto` INT NOT NULL ,
  PRIMARY KEY (`idServicios`) ,
  INDEX `fk_Servicios_Horario_idx` (`Horario_idHorario` ASC) ,
  INDEX `fk_Servicios_Cotacto1_idx` (`Cotacto_idCotacto` ASC) ,
  CONSTRAINT `fk_Servicios_Horario`
    FOREIGN KEY (`Horario_idHorario` )
    REFERENCES `mydb`.`Horario` (`idHorario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Servicios_Cotacto1`
    FOREIGN KEY (`Cotacto_idCotacto` )
    REFERENCES `mydb`.`Cotacto` (`idCotacto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
