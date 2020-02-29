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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Иван','Ivanov',50,21,1),(2,'Сергей','Smirnov',48,10,2),(3,'Petr','Petrov',48,12,3),(4,'Natalia','Semenova',35,8,4),(5,'Petr','Stepanov',44,12,1),(6,'Semen','Slepakov',40,5,2),(7,'Andrei','Petrov',35,10,3),(8,'Irina','Ivanova',40,9,4),(9,'Alexandr','Stepcov',45,14,1),(10,'Stepan','Holodcov',48,12,2),(11,'Anna','Petrova',45,11,3),(12,'Tamara','Saharova',42,8,4),(13,'Ivan','Kupcov',46,11,1),(14,'Petr','Grogorov',30,5,2),(15,'Anatoliy','Vaserman',44,9,3),(16,'Alla','Samoilova',45,7,4),(17,'Dmitriy','Komarov',42,6,1),(18,'Anton','Kovalev',38,5,2),(19,'Stepan','Borsukov',41,3,3),(20,'Tatiana','Stepanova',43,13,4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `positions` (
  `idpositions` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idpositions`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'driver'),(2,'assistant'),(3,'chief'),(4,'conductor');
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,1,1,1),(2,1,5,3),(3,1,9,5),(4,1,1,7),(5,2,13,2),(6,2,17,5),(7,3,9,1),(8,3,5,2),(9,3,13,4),(10,4,1,3),(11,4,5,7),(12,1,2,1),(13,1,6,3),(14,1,10,5),(15,1,2,7),(16,2,14,2),(17,2,18,5),(18,3,10,1),(19,3,14,2),(20,3,2,4),(21,4,6,3),(22,4,10,7),(23,1,3,1),(24,1,7,3),(25,1,11,5),(26,1,3,7),(27,2,15,2),(28,2,19,5),(29,3,3,1),(30,3,11,2),(31,3,15,4),(32,4,7,3),(33,4,11,7),(34,1,4,1),(35,1,8,3),(36,1,12,5),(37,1,4,7),(38,2,16,2),(39,2,20,5),(40,3,8,1),(41,3,4,2),(42,3,16,4),(43,4,12,3),(44,4,20,7);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Minsk',1,1),(2,'Puhovichi',0,1),(3,'Osipovichi',0,0),(4,'Bobryusk',1,1),(5,'Krasnyy bereg',0,0),(6,'Zhlobin',1,1),(7,'Buda-Koshelevskaya',0,0),(8,'Gomel',1,1),(9,'Grodno',1,1),(10,'Skidel',0,0),(11,'Mosti',0,1),(12,'Ros',0,0),(13,'Volkovisk',1,1),(14,'Zelva',0,0),(15,'Ozernica',0,0),(16,'Slonim',1,1),(17,'Baranovichi',1,1),(18,'Gorodeya',0,0),(19,'Stolbci',0,0),(20,'Svetlogorsk',1,1),(21,'Kalinkovichi',1,1),(22,'Vasilevichi',0,0),(23,'Rechica',1,1);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `timetable` (
  `idtimetable` int(11) NOT NULL AUTO_INCREMENT,
  `trainid` int(11) NOT NULL,
  `stationid` int(11) NOT NULL,
  `day` int(2) NOT NULL,
  `departureTime` time DEFAULT NULL,
  `arrivalTime` time DEFAULT NULL,
  PRIMARY KEY (`idtimetable`),
  KEY `trainFK_idx` (`trainid`),
  KEY `stationFK_idx` (`stationid`),
  KEY `train_timetableFK_idx` (`trainid`),
  CONSTRAINT `stationFK` FOREIGN KEY (`stationid`) REFERENCES `station` (`idstation`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `train_timetableFK` FOREIGN KEY (`trainid`) REFERENCES `train` (`idtrain`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT INTO `timetable` VALUES (1,1,1,1,NULL,'00:25:00'),(2,1,2,1,'01:21:00','01:25:00'),(3,1,3,1,'02:04:00','02:14:00'),(4,1,4,1,'02:59:00','03:03:00'),(5,1,5,1,'03:47:00','03:50:00'),(6,1,6,1,'04:14:00','04:19:00'),(7,1,7,1,'04:55:00','04:57:00'),(8,1,8,1,'05:51:00',NULL),(9,1,1,3,NULL,'00:25:00'),(10,1,2,3,'01:21:00','01:24:00'),(11,1,3,3,'02:03:00','02:14:00'),(12,1,4,3,'02:58:00','03:03:00'),(13,1,5,3,'03:47:00','03:50:00'),(14,1,6,3,'04:14:00','04:19:00'),(15,1,7,3,'04:55:00','04:57:00'),(16,1,8,3,'05:51:00',NULL),(17,1,1,5,NULL,'00:25:00'),(18,1,2,5,'01:21:00','01:24:00'),(19,1,3,5,'02:03:00','02:14:00'),(20,1,4,5,'02:58:00','03:03:00'),(21,1,5,5,'03:47:00','03:50:00'),(22,1,6,5,'04:14:00','04:19:00'),(23,1,7,5,'04:55:00','04:57:00'),(24,1,8,5,'05:51:00',NULL),(25,1,1,7,NULL,'00:25:00'),(26,1,2,7,'01:21:00','01:24:00'),(27,1,3,7,'02:03:00','02:14:00'),(28,1,4,7,'02:58:00','03:03:00'),(29,1,5,7,'03:47:00','03:50:00'),(30,1,6,7,'04:14:00','04:19:00'),(31,1,7,7,'04:55:00','04:57:00'),(32,1,8,7,'05:51:00',NULL),(33,2,9,2,NULL,'16:33:00'),(34,2,10,2,'17:03:00','17:05:00'),(35,2,11,2,'17:32:00','17:56:00'),(36,2,12,2,'18:13:00','18:15:00'),(37,2,13,2,'18:37:00','18:41:00'),(38,2,14,2,'19:02:00','19:04:00'),(39,2,15,2,'19:23:00','19:27:00'),(40,2,16,2,'19:49:00','19:51:00'),(41,2,17,2,'20:48:00','21:24:00'),(42,2,18,2,'21:56:00','21:58:00'),(43,2,19,2,'22:17:00','22:19:00'),(44,2,1,2,'23:42:00','23:56:00'),(45,2,2,3,'00:44:00','00:46:00'),(46,2,3,3,'01:19:00','01:28:00'),(47,2,4,3,'02:02:00','02:06:00'),(48,2,5,3,'02:58:00','02:59:00'),(49,2,6,3,'03:17:00','03:26:00'),(50,2,20,3,'03:57:00','04:07:00'),(51,2,21,3,'05:05:00','05:40:00'),(52,2,22,3,'06:23:00','06:25:00'),(53,2,23,3,'07:08:00','07:11:00'),(54,2,8,3,'07:54:00',NULL),(55,2,9,5,NULL,'16:33:00'),(56,2,10,5,'17:03:00','17:05:00'),(57,2,11,5,'17:32:00','17:56:00'),(58,2,12,5,'18:13:00','18:15:00'),(59,2,13,5,'18:37:00','18:41:00'),(60,2,14,5,'19:02:00','19:04:00'),(61,2,15,5,'19:23:00','19:27:00'),(62,2,16,5,'19:49:00','19:51:00'),(63,2,17,5,'20:48:00','21:24:00'),(64,2,18,5,'21:56:00','21:58:00'),(65,2,19,5,'22:17:00','22:19:00'),(66,2,1,5,'23:42:00','23:56:00'),(67,2,2,6,'00:44:00','00:46:00'),(68,2,3,6,'01:19:00','01:28:00'),(69,2,4,6,'02:02:00','02:06:00'),(70,2,5,6,'02:58:00','02:59:00'),(71,2,6,6,'03:17:00','03:26:00'),(72,2,20,6,'03:57:00','04:07:00'),(73,2,21,6,'05:05:00','05:40:00'),(74,2,22,6,'06:23:00','06:25:00'),(75,2,23,6,'07:08:00','07:11:00'),(76,2,8,6,'07:54:00',NULL),(77,3,8,1,NULL,'21:20:00'),(78,3,7,1,'22:19:00','22:20:00'),(79,3,6,1,'23:07:00',NULL),(80,3,8,2,NULL,'21:20:00'),(81,3,7,2,'22:19:00','22:20:00'),(82,3,6,2,'23:07:00',NULL),(83,3,8,4,NULL,'21:20:00'),(84,3,7,4,'22:19:00','22:20:00'),(85,3,6,4,'23:07:00',NULL),(87,4,8,3,NULL,'06:51:00'),(88,4,23,3,'08:18:00','08:20:00'),(89,4,22,3,'09:20:00','09:21:00'),(90,4,21,3,'10:09:00',NULL),(91,4,8,7,NULL,'06:51:00'),(92,4,23,7,'08:18:00','08:20:00'),(93,4,22,7,'09:20:00','09:21:00'),(94,4,21,7,'10:09:00',NULL);
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `train` (
  `idtrain` int(11) NOT NULL AUTO_INCREMENT,
  `typeOfTrain` tinyint(1) NOT NULL,
  `distance` int(11) NOT NULL,
  `isBrandedTrain` tinyint(1) NOT NULL,
  `numberOfTrain` varchar(45) NOT NULL,
  PRIMARY KEY (`idtrain`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (1,1,323,1,'684B'),(2,1,450,1,'632B'),(3,0,90,0,'6463'),(4,0,100,0,'6473');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  `role` int(1) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'stepan','123456',1),(2,'иван','654321',2),(3,'petr','12345',3),(4,'иван','123456',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-10 16:47:30
