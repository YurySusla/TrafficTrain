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
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `station` (
  `idstation` int(11) NOT NULL AUTO_INCREMENT,
  `nameOfStation` varchar(45) NOT NULL,
  `isRailroadStation` tinyint(1) NOT NULL,
  `waitingHall` tinyint(1) NOT NULL,
  PRIMARY KEY (`idstation`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Minsk',1,1),(2,'Puhovichi',0,1),(3,'Osipovichi',0,0),(4,'Bobryusk',1,1),(5,'Krasnyy bereg',0,0),(6,'Zhlobin',1,1),(7,'Buda-Koshelevskaya',0,0),(8,'Gomel',1,1),(9,'Grodno',1,1),(10,'Skidel',0,0),(11,'Mosti',0,1),(12,'Ros',0,0),(13,'Volkovisk',1,1),(14,'Zelva',0,0),(15,'Ozernica',0,0),(16,'Slonim',1,1),(17,'Baranovichi',1,1),(18,'Gorodeya',0,0),(19,'Stolbci',0,0),(20,'Svetlogorsk',1,1),(21,'Kalinkovichi',1,1),(22,'Vasilevichi',0,0),(23,'Rechica',1,1);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-05 17:24:58
