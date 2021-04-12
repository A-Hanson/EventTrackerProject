-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tvdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tvdb` ;

-- -----------------------------------------------------
-- Schema tvdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tvdb` DEFAULT CHARACTER SET utf8 ;
USE `tvdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `image_url` VARCHAR(1000) NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tv_watching_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tv_watching_session` ;

CREATE TABLE IF NOT EXISTS `tv_watching_session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `start` DATETIME NULL,
  `stop` DATETIME NULL,
  `platform_id` INT NOT NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_tv_watching_session_user_idx` (`user_id` ASC),
  INDEX `fk_tv_watching_session_platform1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_tv_watching_session_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tv_watching_session_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tv_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tv_show` ;

CREATE TABLE IF NOT EXISTS `tv_show` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `rating` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `imdb_url` VARCHAR(1000) NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `season`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `season` ;

CREATE TABLE IF NOT EXISTS `season` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(1000) NULL,
  `tv_show_id` INT NOT NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_season_tv_show1_idx` (`tv_show_id` ASC),
  CONSTRAINT `fk_season_tv_show1`
    FOREIGN KEY (`tv_show_id`)
    REFERENCES `tv_show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `episode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `episode` ;

CREATE TABLE IF NOT EXISTS `episode` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number_in_season` INT NULL,
  `title` VARCHAR(45) NULL,
  `directed_by` VARCHAR(200) NULL,
  `written_by` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `original_air_date` DATE NULL,
  `season_id` INT NOT NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_episode_season1_idx` (`season_id` ASC),
  CONSTRAINT `fk_episode_season1`
    FOREIGN KEY (`season_id`)
    REFERENCES `season` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `watching_session_episode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watching_session_episode` ;

CREATE TABLE IF NOT EXISTS `watching_session_episode` (
  `tv_watching_session_id` INT NOT NULL,
  `episode_id` INT NOT NULL,
  INDEX `fk_watching_session_episode_tv_watching_session1_idx` (`tv_watching_session_id` ASC),
  INDEX `fk_watching_session_episode_episode1_idx` (`episode_id` ASC),
  CONSTRAINT `fk_watching_session_episode_tv_watching_session1`
    FOREIGN KEY (`tv_watching_session_id`)
    REFERENCES `tv_watching_session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_watching_session_episode_episode1`
    FOREIGN KEY (`episode_id`)
    REFERENCES `episode` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `purchase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase` ;

CREATE TABLE IF NOT EXISTS `purchase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `amount` DECIMAL NULL,
  `user_id` INT NOT NULL,
  `platform_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_purchase_user1_idx` (`user_id` ASC),
  INDEX `fk_purchase_platform1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_purchase_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER tvuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'tvuser'@'localhost' IDENTIFIED BY 'tvuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tvuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`, `last_name`, `role`, `deleted`) VALUES (1, 'adminuser', 'admin', 'Adam', 'Min', 'admin', 0);
INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`, `last_name`, `role`, `deleted`) VALUES (2, 'testuser', 'user', 'Betty', 'Bop', 'user', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `platform` (`id`, `name`, `image_url`, `deleted`) VALUES (1, 'Amazon Video', 'https://m.media-amazon.com/images/G/01/primevideo/seo/primevideo-seo-logo-square.png', 0);
INSERT INTO `platform` (`id`, `name`, `image_url`, `deleted`) VALUES (2, 'Netflix', 'https://i.pinimg.com/originals/1b/54/ef/1b54efef3720f6ac39647fc420d4a6f9.png', 0);
INSERT INTO `platform` (`id`, `name`, `image_url`, `deleted`) VALUES (3, 'Hulu', 'https://ravangaming.com/wp-content/uploads/2020/08/1200x630wa.png', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tv_watching_session`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `tv_watching_session` (`id`, `user_id`, `start`, `stop`, `platform_id`, `deleted`) VALUES (1, 2, '2021-04-02 00:00:00', '2021-04-02 02:30:00', 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tv_show`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `tv_show` (`id`, `name`, `rating`, `description`, `imdb_url`, `deleted`) VALUES (1, 'Bob\'s Burgers', 'TV-PG', 'Bob Belcher runs his dream restaurant with his wife and three children as their last hope of holding the family together.', 'https://www.imdb.com/title/tt1561755/', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `season`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `season` (`id`, `image_url`, `tv_show_id`, `deleted`) VALUES (1, 'https://images-na.ssl-images-amazon.com/images/I/61J8wQhWiCL.jpg', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `episode`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `episode` (`id`, `number_in_season`, `title`, `directed_by`, `written_by`, `description`, `original_air_date`, `season_id`, `deleted`) VALUES (1, 1, 'Human Flesh', 'Anthony Chun', 'Loren Bouchard, Jim Dauterive', 'Louise wanted to \"up the ante\" at Show & Tell, so she told the class her dad, Bob, uses human remains in Bob\'s Burgers. Linda\'s jealous ex-fiancée, Hugo, is the new health inspector sent to investigate. Tina and Louise advise Linda to flirt with Hugo to keep the restaurant open. Hugo is an amateur poet: \"Inspector of health, inspector of pain. I came in search of human remains. And I found a piece of human flesh I thought I\'d never see again---my heart.\" Gene makes a delivery next door to Mort the mortician. With kids, condiments, buttons, candy caskets and angry mobs: this may be one Labor Day - slash - Anniversary Bob and Linda Belcher never forget! Written by LA-Lawyer', '2011-01-09', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `watching_session_episode`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `watching_session_episode` (`tv_watching_session_id`, `episode_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `purchase`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvdb`;
INSERT INTO `purchase` (`id`, `date`, `amount`, `user_id`, `platform_id`) VALUES (1, '2021-04-01', 14.99, 2, 2);

COMMIT;

