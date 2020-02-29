CREATE DATABASE  IF NOT EXISTS `train_traffic` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `train_traffic`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: train_traffic
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL AUTO_INCREMENT,
  `nameOfEmployee` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `experience` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`idemployee`),
  KEY `positionFK_idx` (`position`),
  CONSTRAINT `positionFK` FOREIGN KEY (`position`) REFERENCES `positions` (`idpositions`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Ivan','Ivanov',50,21,1),(2,'Sergei','Smirnov',45,10,2),(3,'Petr','Petrov',48,12,3),(4,'Natalia','Semenova',35,8,4),(5,'Petr','Stepanov',44,12,1),(6,'Semen','Slepakov',40,5,2),(7,'Andrei','Petrov',35,10,3),(8,'Irina','Ivanova',40,9,4),(9,'Alexandr','Stepcov',45,14,1),(10,'Stepan','Holodcov',48,12,2),(11,'Anna','Petrova',45,11,3),(12,'Tamara','Saharova',42,8,4),(13,'Ivan','Kupcov',46,11,1),(14,'Petr','Grogorov',30,5,2),(15,'Anatoliy','Vaserman',44,9,3),(16,'Alla','Samoilova',45,7,4),(17,'Dmitriy','Komarov',42,6,1),(18,'Anton','Kovalev',38,5,2),(19,'Stepan','Borsukov',41,3,3),(20,'Tatiana','Stepanova',43,13,4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-05 17:24:57
