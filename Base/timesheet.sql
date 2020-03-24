-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.24-log - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных timesheet
CREATE DATABASE IF NOT EXISTS `timesheet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `timesheet`;

-- Дамп структуры для таблица timesheet.departments
CREATE TABLE IF NOT EXISTS `departments` (
  `id` char(36) NOT NULL,
  `id_enterprise` char(36) NOT NULL,
  `name` char(100) NOT NULL,
  `code` char(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_departments_enterprises` (`id_enterprise`),
  CONSTRAINT `FK_departments_enterprises` FOREIGN KEY (`id_enterprise`) REFERENCES `enterprises` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `id` char(36) NOT NULL,
  `name` char(100) NOT NULL,
  `id_enterprise` char(36) NOT NULL,
  `id_department` char(36) NOT NULL,
  `id_post` char(36) NOT NULL,
  `code` char(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_enterprise` (`id_enterprise`),
  KEY `id_department` (`id_department`),
  KEY `id_post` (`id_post`),
  CONSTRAINT `FK_employees_departments` FOREIGN KEY (`id_department`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_employees_enterprises` FOREIGN KEY (`id_enterprise`) REFERENCES `enterprises` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_employees_posts` FOREIGN KEY (`id_post`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.enterprises
CREATE TABLE IF NOT EXISTS `enterprises` (
  `id` char(36) NOT NULL,
  `name` char(50) NOT NULL,
  `code` char(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.hoursworked
CREATE TABLE IF NOT EXISTS `hoursworked` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_employee` char(36) NOT NULL,
  `period` date NOT NULL,
  `day1` char(30) NOT NULL,
  `day2` char(30) NOT NULL,
  `day3` char(30) NOT NULL,
  `day4` char(30) NOT NULL,
  `day5` char(30) NOT NULL,
  `day6` char(30) NOT NULL,
  `day7` char(30) NOT NULL,
  `day8` char(30) NOT NULL,
  `day9` char(30) NOT NULL,
  `day10` char(30) NOT NULL,
  `day11` char(30) NOT NULL,
  `day12` char(30) NOT NULL,
  `day13` char(30) NOT NULL,
  `day14` char(30) NOT NULL,
  `day15` char(30) NOT NULL,
  `day16` char(30) NOT NULL,
  `day17` char(30) NOT NULL,
  `day18` char(30) NOT NULL,
  `day19` char(30) NOT NULL,
  `day20` char(30) NOT NULL,
  `day21` char(30) NOT NULL,
  `day22` char(30) NOT NULL,
  `day23` char(30) NOT NULL,
  `day24` char(30) NOT NULL,
  `day25` char(30) NOT NULL,
  `day26` char(30) NOT NULL,
  `day27` char(30) NOT NULL,
  `day28` char(30) NOT NULL,
  `day29` char(30) NOT NULL,
  `day30` char(30) NOT NULL,
  `day31` char(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hoursworked_employees` (`id_employee`),
  CONSTRAINT `FK_hoursworked_employees` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.kindsoftime
CREATE TABLE IF NOT EXISTS `kindsoftime` (
  `id` char(36) NOT NULL,
  `name` char(36) NOT NULL,
  `code` char(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.posts
CREATE TABLE IF NOT EXISTS `posts` (
  `id` char(36) NOT NULL,
  `name` char(100) NOT NULL,
  `code` char(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.timesheetdoc
CREATE TABLE IF NOT EXISTS `timesheetdoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` char(36) DEFAULT NULL,
  `id_department` char(36) NOT NULL,
  `period` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_timesheetdoc_departments` (`id_department`),
  CONSTRAINT `FK_timesheetdoc_departments` FOREIGN KEY (`id_department`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.timesheettable
CREATE TABLE IF NOT EXISTS `timesheettable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_employee` char(36) NOT NULL,
  `id_timesheetdoc` int(11) NOT NULL,
  `day1` char(30) NOT NULL,
  `day2` char(30) NOT NULL,
  `day3` char(30) NOT NULL,
  `day4` char(30) NOT NULL,
  `day5` char(30) NOT NULL,
  `day6` char(30) NOT NULL,
  `day7` char(30) NOT NULL,
  `day8` char(30) NOT NULL,
  `day9` char(30) NOT NULL,
  `day10` char(30) NOT NULL,
  `day11` char(30) NOT NULL,
  `day12` char(30) NOT NULL,
  `day13` char(30) NOT NULL,
  `day14` char(30) NOT NULL,
  `day15` char(30) NOT NULL,
  `day16` char(30) NOT NULL,
  `day17` char(30) NOT NULL,
  `day18` char(30) NOT NULL,
  `day19` char(30) NOT NULL,
  `day20` char(30) NOT NULL,
  `day21` char(30) NOT NULL,
  `day22` char(30) NOT NULL,
  `day23` char(30) NOT NULL,
  `day24` char(30) NOT NULL,
  `day25` char(30) NOT NULL,
  `day26` char(30) NOT NULL,
  `day27` char(30) NOT NULL,
  `day28` char(30) NOT NULL,
  `day29` char(30) NOT NULL,
  `day30` char(30) NOT NULL,
  `day31` char(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_timesheettable_employees` (`id_employee`),
  KEY `FK_timesheettable_timesheetdoc` (`id_timesheetdoc`),
  CONSTRAINT `FK_timesheettable_employees` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `FK_timesheettable_timesheetdoc` FOREIGN KEY (`id_timesheetdoc`) REFERENCES `timesheetdoc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица timesheet.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` char(36) NOT NULL,
  `domain` char(50) NOT NULL,
  `name` char(50) NOT NULL,
  `id_department` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_users_departments` (`id_department`),
  CONSTRAINT `FK_users_departments` FOREIGN KEY (`id_department`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
