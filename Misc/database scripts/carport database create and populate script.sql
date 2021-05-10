CREATE DATABASE  IF NOT EXISTS `carport` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `Material_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  ` Unit` varchar(10) NOT NULL,
  `Buy_price_per_unit` double NOT NULL,
  `Price_per_unit` double NOT NULL,
  `Length` int NOT NULL,
  `Width` int NOT NULL,
  `Height` int NOT NULL,
  PRIMARY KEY (`Material_id`),
  UNIQUE KEY `Material_id_UNIQUE` (`Material_id`),
  KEY `fk_Materials_Unit1_idx` (` Unit`),
  CONSTRAINT `fk_Materials_Unit1` FOREIGN KEY (` Unit`) REFERENCES `unit` (`Unit`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'25x200 mm. trykimp. Brædt','Stk',0,0,3600,200,25),(2,'25x200 mm. trykimp. Brædt','Stk',0,0,5400,200,25),(3,'25x125mm. trykimp. Brædt','Stk',0,0,3600,125,25),(4,'25x125mm. trykimp. Brædt','Stk',0,0,5400,125,25),(5,'38x73 mm. Lægte ubh.','Stk',0,0,4200,73,38),(6,'45x95 mm. Reglar ub.','Stk',0,0,2700,95,45),(7,'45x95 mm. Reglar ub.','Stk',0,0,2400,95,45),(8,'45x195 mm. spærtræ ubh.','Stk',0,0,6000,195,45),(9,'45x195 mm. spærtræ ubh.','Stk',0,0,4800,195,45),(10,'97x97 mm. trykimp. Stolpe','Stk',0,0,300,97,97),(11,'19x100 mm. trykimp. Brædt','Stk',0,0,2100,100,19),(12,'19x100 mm. trykimp. Brædt','Stk',0,0,5400,100,19),(13,'19x100 mm. trykimp. Brædt','Stk',0,0,3600,100,19),(14,'Plastmo Ecolite blåtonet','Stk',0,0,6000,0,0),(15,'Plastmo Ecolite blåtonet','Stk',0,0,3600,0,0),(16,'Plastmo bundskruer 200 stk.','Pakke',0,0,0,0,0),(17,'Hulbånd 1x20 mm. 10 mtr.','Rulle',0,0,0,0,0),(18,'Universal 190 mm højre','Stk',0,0,190,0,0),(19,'Universal 190 mm venstre','Stk',0,0,190,0,0),(20,'4,5 x 60 mm. skruer 200 stk.','Pakke',0,0,60,0,0),(21,'4,0 x 50 mm. beslagskruer 250\nstk.','Pakke',0,0,50,0,0),(22,'Bræddebolt 10 x 120 mm.','Stk',0,0,120,10,0),(23,'Firkantskiver 40x40x11mm','Stk',0,0,11,40,40),(24,'4,5 x 70 mm. Skruer 400 stk.','Pakke',0,0,70,0,0),(25,'4,5 x 50 mm. Skruer 300 stk.','Pakke',0,0,50,0,0),(26,'Stalddørsgreb 50x75','Sæt',0,0,75,50,0),(27,'T hængsel 390 mm','Stk',0,0,390,0,0),(28,'Vinkelbeslag 35','Stk',0,0,0,0,0);
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `Status` varchar(10) NOT NULL,
  PRIMARY KEY (`Status`),
  UNIQUE KEY `Status_UNIQUE` (`Status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES ('Afsluttet'),('Bestilt'),('Betalt');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `Order_id` int NOT NULL,
  `Material_id` int NOT NULL,
  `Quantity` int NOT NULL,
  `Description` varchar(145) NOT NULL,
  PRIMARY KEY (`Order_id`,`Material_id`),
  KEY `fk_Orderline_Materials1_idx` (`Material_id`),
  CONSTRAINT `fk_Orderline_Materials1` FOREIGN KEY (`Material_id`) REFERENCES `materials` (`Material_id`),
  CONSTRAINT `fk_Orderline_Orders1` FOREIGN KEY (`Order_id`) REFERENCES `orders` (`Order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `Order_id` int NOT NULL AUTO_INCREMENT,
  `Total` double NOT NULL DEFAULT 0,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Profit_margin` double NOT NULL DEFAULT 0,
  `User_id` int NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Carport_length` int NOT NULL,
  `Carport_width` int NOT NULL,
  `Shed_length` int NOT NULL DEFAULT 0,
  `Shed_width` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`Order_id`),
  UNIQUE KEY `Order_id_UNIQUE` (`Order_id`),
  KEY `fk_Orders_Users1_idx` (`User_id`),
  KEY `fk_Orders_Order_status1_idx` (`Status`),
  CONSTRAINT `fk_Orders_Order_status1` FOREIGN KEY (`Status`) REFERENCES `order_status` (`Status`),
  CONSTRAINT `fk_Orders_Users1` FOREIGN KEY (`User_id`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `Unit` varchar(10) NOT NULL,
  PRIMARY KEY (`Unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES ('Pakke'),('Rulle'),('Stk'),('Sæt');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_ID_UNIQUE` (`User_ID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tester1','1234','customer'),(2,'barbie@world.dk','jensen','customer'),(3,'ken@world.com','jensen','customer'),(4,'robin@gotham.com','batman','employee'),(5,'rootie','root','employee');
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

-- Dump completed on 2021-05-05 15:20:52
