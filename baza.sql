/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `library`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `authorID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `dateOfBirth` date NOT NULL,
  PRIMARY KEY (`authorID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `author` */

insert  into `author`(`authorID`,`firstName`,`lastName`,`dateOfBirth`) values 
(1,'Willian','Shakespeare','1564-04-23'),
(2,'Johann Wolfgang','von Goethe','1749-08-28'),
(3,'Leo','Tolstoj','1828-09-09'),
(4,'Fjodor','Dostojevski','1821-11-11'),
(5,'Victor','Hugo','1802-02-26'),
(6,'Charles','Dickens','1812-02-07'),
(7,'Franz','Kafka','1883-07-03'),
(8,'Ernest','Haminway','1899-07-21'),
(9,'Gabriel','Garcia','1927-03-06'),
(10,'Ivo','Andric','1892-09-09'),
(11,'Nikola','Ilic','2001-04-23');

/*Table structure for table `authorbook` */

DROP TABLE IF EXISTS `authorbook`;

CREATE TABLE `authorbook` (
  `bookID` bigint(20) unsigned NOT NULL,
  `authorID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`bookID`,`authorID`),
  KEY `author` (`authorID`),
  CONSTRAINT `author` FOREIGN KEY (`authorID`) REFERENCES `author` (`authorID`) ON UPDATE CASCADE,
  CONSTRAINT `book` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `authorbook` */

insert  into `authorbook`(`bookID`,`authorID`) values 
(2,3),
(2,4),
(3,8);

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `totalQuantity` int(10) unsigned DEFAULT NULL,
  `stockQuantity` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `book` */

insert  into `book`(`bookID`,`title`,`genre`,`totalQuantity`,`stockQuantity`) values 
(2,'Mali Princ','NOVEL',55,54),
(3,'Starac i more','NOVEL',122,119);

/*Table structure for table `itemreceipt` */

DROP TABLE IF EXISTS `itemreceipt`;

CREATE TABLE `itemreceipt` (
  `itemReceiptID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `purchasedQuantity` int(10) unsigned DEFAULT NULL,
  `bookID` bigint(20) unsigned NOT NULL,
  `receiptID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`itemReceiptID`),
  KEY `bookID` (`bookID`),
  KEY `receiptID` (`receiptID`),
  CONSTRAINT `itemreceipt_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON UPDATE CASCADE,
  CONSTRAINT `itemreceipt_ibfk_2` FOREIGN KEY (`receiptID`) REFERENCES `receipt` (`receiptID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `itemreceipt` */

/*Table structure for table `librarian` */

DROP TABLE IF EXISTS `librarian`;

CREATE TABLE `librarian` (
  `librarianID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`librarianID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `librarian` */

insert  into `librarian`(`librarianID`,`firstName`,`lastName`,`username`,`password`) values 
(1,'Nikola','Ilic','n','123'),
(2,'Nikola','Ilic','nikola','123');

/*Table structure for table `loan` */

DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `loanID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `loanDate` date NOT NULL,
  `memberID` bigint(20) unsigned NOT NULL,
  `bookID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`loanID`),
  KEY `memberID` (`memberID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`memberID`) REFERENCES `member` (`memberID`) ON UPDATE CASCADE,
  CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `loan` */

insert  into `loan`(`loanID`,`loanDate`,`memberID`,`bookID`) values 
(1,'2025-08-23',1,3),
(5,'2025-08-24',1,3),
(6,'2025-08-24',1,3),
(7,'2025-08-23',2,2);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `memberID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `JMBG` varchar(13) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `membershipValidityDate` date NOT NULL,
  PRIMARY KEY (`memberID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`memberID`,`firstName`,`lastName`,`JMBG`,`dateOfBirth`,`address`,`city`,`phone`,`email`,`membershipValidityDate`) values 
(1,'Nikola','Ilic','290800176001','2001-08-29','Mocartova 30','Smederevo','0611736202','myxyq5@gmail.com','2028-08-22'),
(2,'Nikola','Ilic','1203003234055','2003-12-23','Mocartova','Smederevo','0643810456','djfhksanfld@gmail.com','2024-08-27'),
(3,'Marija','Maric','2303003760125','2003-03-23','Nehruova 12','Beograd','0612345678','marija@gmail.com','2026-08-27');

/*Table structure for table `receipt` */

DROP TABLE IF EXISTS `receipt`;

CREATE TABLE `receipt` (
  `receiptID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `releaseDate` date NOT NULL,
  `librarianID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`receiptID`),
  KEY `librarianID` (`librarianID`),
  CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`librarianID`) REFERENCES `librarian` (`librarianID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `receipt` */

insert  into `receipt`(`receiptID`,`releaseDate`,`librarianID`) values 
(1,'2025-08-22',1),
(2,'2025-08-22',1),
(3,'2025-08-22',1),
(4,'2025-08-23',1),
(5,'2025-08-23',1),
(6,'2025-08-27',1),
(7,'2025-08-27',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
