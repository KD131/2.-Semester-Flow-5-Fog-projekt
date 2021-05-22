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
-- Table structure for table `functionalities`
--

DROP TABLE IF EXISTS `functionalities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `functionalities` (
  `functionality` varchar(10) NOT NULL,
  PRIMARY KEY (`functionality`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functionalities`
--

LOCK TABLES `functionalities` WRITE;
/*!40000 ALTER TABLE `functionalities` DISABLE KEYS */;
INSERT INTO `functionalities` VALUES ('beklædning'),('dør'),('løsholter'),('overstern'),('rem'),('spær'),('stolpe'),('tagplade'),('tilbehør'),('understern'),('vandbrædt'),('vindkryds');
/*!40000 ALTER TABLE `functionalities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_functionalities`
--

DROP TABLE IF EXISTS `material_functionalities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_functionalities` (
  `material_id` int NOT NULL,
  `functionality` varchar(10) NOT NULL,
  PRIMARY KEY (`material_id`,`functionality`),
  KEY `fk_material_functionalities_functionalities1_idx` (`functionality`),
  CONSTRAINT `fk_material_functionalities_functionalities1` FOREIGN KEY (`functionality`) REFERENCES `functionalities` (`functionality`),
  CONSTRAINT `fk_material_functionalities_materials1` FOREIGN KEY (`material_id`) REFERENCES `materials` (`Material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_functionalities`
--

LOCK TABLES `material_functionalities` WRITE;
/*!40000 ALTER TABLE `material_functionalities` DISABLE KEYS */;
INSERT INTO `material_functionalities` VALUES (11,'Beklædning'),(5,'Dør'),(6,'Løsholter'),(7,'Løsholter'),(3,'Overstern'),(4,'Overstern'),(8,'Rem'),(9,'Rem'),(8,'Spær'),(9,'Spær'),(10,'Stolpe'),(14,'Tagplade'),(15,'Tagplade'),(16,'Tilbehør'),(17,'Tilbehør'),(18,'Tilbehør'),(19,'Tilbehør'),(20,'Tilbehør'),(21,'Tilbehør'),(22,'Tilbehør'),(23,'Tilbehør'),(24,'Tilbehør'),(25,'Tilbehør'),(26,'Tilbehør'),(27,'Tilbehør'),(28,'Tilbehør'),(1,'Understern'),(2,'Understern'),(12,'Vandbrædt'),(13,'Vandbrædt');
/*!40000 ALTER TABLE `material_functionalities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `Material_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Unit` varchar(10) NOT NULL,
  `Buy_price_per_unit` double NOT NULL,
  `Price_per_unit` double NOT NULL,
  `Length` int NOT NULL,
  `Width` int NOT NULL,
  `Height` int NOT NULL,
  PRIMARY KEY (`Material_id`),
  UNIQUE KEY `Material_id_UNIQUE` (`Material_id`),
  KEY `fk_Materials_Unit1_idx` (`Unit`),
  CONSTRAINT `fk_Materials_Unit1` FOREIGN KEY (`Unit`) REFERENCES `unit` (`Unit`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'25x200 mm. trykimp. Brædt','Stk',0,0,3600,200,25),(2,'25x200 mm. trykimp. Brædt','Stk',0,0,5400,200,25),(3,'25x125mm. trykimp. Brædt','Stk',0,0,3600,125,25),(4,'25x125mm. trykimp. Brædt','Stk',0,0,5400,125,25),(5,'38x73 mm. Lægte \n\nubh.','Stk',0,0,4200,73,38),(6,'45x95 mm. Reglar ub.','Stk',0,0,2700,95,45),(7,'45x95 mm. Reglar ub.','Stk',0,0,2400,95,45),(8,'45x195 mm. \n\nspærtræ ubh.','Stk',0,0,6000,195,45),(9,'45x195 mm. spærtræ ubh.','Stk',0,0,4800,195,45),(10,'97x97 mm. trykimp. \n\nStolpe','Stk',0,0,3000,97,97),(11,'19x100 mm. trykimp. Brædt','Stk',0,0,2100,100,19),(12,'19x100 mm. trykimp. Brædt','Stk',0,0,5400,100,19),(13,'19x100 mm. trykimp. Brædt','Stk',0,0,3600,100,19),(14,'Plastmo Ecolite blåtonet','Stk',0,0,6000,0,0),(15,'Plastmo Ecolite \r\rblåtonet','Stk',0,0,3600,0,0),(16,'Plastmo bundskruer 200 stk.','Pakke',0,0,0,0,0),(17,'Hulbånd 1x20 mm. 10 mtr.','Rulle',0,0,0,0,0),(18,'Universal 190 mm højre','Stk',0,0,190,0,0),(19,'Universal 190 mm venstre','Stk',0,0,190,0,0),(20,'4,5 x 60 mm. skruer 200 \n\nstk.','Pakke',0,0,60,0,0),(21,'4,0 x 50 mm. beslagskruer 250\nstk.','Pakke',0,0,50,0,0),(22,'Bræddebolt 10 x 120 mm.','Stk',0,0,120,10,0),(23,'Firkantskiver 40x40x11mm','Stk',0,0,11,40,40),(24,'4,5 x 70 mm. Skruer 400 stk.','Pakke',0,0,70,0,0),(25,'4,5 x 50 mm. Skruer 300 \n\nstk.','Pakke',0,0,50,0,0),(26,'Stalddørsgreb 50x75','Sæt',0,0,75,50,0),(27,'T hængsel 390 mm','Stk',0,0,390,0,0),(28,'Vinkelbeslag \n\n35','Stk',0,0,0,0,0);
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
INSERT INTO `order_status` VALUES ('Afsluttet'),('Bestilt'),('Betalt'),('Godkendt');
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
  `Total` double DEFAULT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Profit_margin` double DEFAULT NULL,
  `User_id` int NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Carport_length` int NOT NULL,
  `Carport_width` int NOT NULL,
  `Shed_length` int DEFAULT NULL,
  `Shed_width` int DEFAULT NULL,
  PRIMARY KEY (`Order_id`),
  UNIQUE KEY `Order_id_UNIQUE` (`Order_id`),
  KEY `fk_Orders_Users1_idx` (`User_id`),
  KEY `fk_Orders_Order_status1_idx` (`Status`),
  CONSTRAINT `fk_Orders_Order_status1` FOREIGN KEY (`Status`) REFERENCES `order_status` (`Status`),
  CONSTRAINT `fk_Orders_Users1` FOREIGN KEY (`User_id`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,'2021-05-07 09:40:40',NULL,1,'Godkendt',360,360,150,150),(2,NULL,'2021-05-07 09:41:31',NULL,1,'Bestilt',630,630,270,240),(3,NULL,'2021-05-10 09:19:08',NULL,1,'Bestilt',510,510,0,0),(4,NULL,'2021-05-10 09:19:56',NULL,1,'Bestilt',360,240,0,150),(6,NULL,'2021-05-11 08:42:24',NULL,1,'Bestilt',300,720,0,0),(7,NULL,'2021-05-11 08:42:31',NULL,1,'Bestilt',780,660,240,240),(8,NULL,'2021-05-11 21:19:38',NULL,1,'Bestilt',600,540,0,0),(9,NULL,'2021-05-18 08:26:51',NULL,1,'Bestilt',600,390,240,540),(10,NULL,'2021-05-18 08:27:02',NULL,1,'Bestilt',660,600,0,0),(11,NULL,'2021-05-22 14:32:03',NULL,1,'Bestilt',630,750,270,690),(12,NULL,'2021-05-22 14:37:32',NULL,6,'Bestilt',360,300,0,0),(13,NULL,'2021-05-22 14:38:15',NULL,6,'Bestilt',600,600,0,0),(14,NULL,'2021-05-22 14:38:29',NULL,6,'Bestilt',480,600,270,540);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tester1','1234','customer'),(2,'barbie@world.dk','jensen','customer'),(3,'ken@world.com','jensen','customer'),(4,'robin@gotham.com','batman','employee'),(5,'rootie','root','employee'),(6,'tester69','69','customer');
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

-- Dump completed on 2021-05-22 21:08:04
