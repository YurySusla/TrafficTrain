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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `id_staff` int(11) NOT NULL,
  `train_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id_staff`),
  KEY `trainFK_idx` (`train_id`),
  KEY `driverFK_idx` (`employee_id`),
  CONSTRAINT `driverFK` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `trainFK` FOREIGN KEY (`train_id`) REFERENCES `train` (`idtrain`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,1,1,1),(2,1,5,3),(3,1,9,5),(4,1,1,7),(5,2,13,2),(6,2,17,5),(7,3,9,1),(8,3,5,2),(9,3,13,4),(10,4,1,3),(11,4,5,7),(12,1,2,1),(13,1,6,3),(14,1,10,5),(15,1,2,7),(16,2,14,2),(17,2,18,5),(18,3,10,1),(19,3,14,2),(20,3,2,4),(21,4,6,3),(22,4,10,7),(23,1,3,1),(24,1,7,3),(25,1,11,5),(26,1,3,7),(27,2,15,2),(28,2,19,5),(29,3,3,1),(30,3,11,2),(31,3,15,4),(32,4,7,3),(33,4,11,7),(34,1,4,1),(35,1,8,3),(36,1,12,5),(37,1,4,7),(38,2,16,2),(39,2,20,5),(40,3,8,1),(41,3,4,2),(42,3,16,4),(43,4,12,3),(44,4,20,7);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
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
