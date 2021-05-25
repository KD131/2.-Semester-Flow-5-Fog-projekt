CREATE DATABASE  IF NOT EXISTS `carport` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport`;
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.23

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
INSERT INTO `materials` VALUES (1,'25x200 mm. trykimp. Brædt','Stk',0,0,3600,200,25),(2,'25x200 mm. trykimp. Brædt','Stk',0,0,5400,200,25),(3,'25x125mm. trykimp. Brædt','Stk',0,0,3600,125,25),(4,'25x125mm. trykimp. Brædt','Stk',0,0,5400,125,25),(5,'38x73 mm. Lægte ubh.','Stk',0,0,4200,73,38),(6,'45x95 mm. Reglar ubh.','Stk',0,0,2700,95,45),(7,'45x95 mm. Reglar ubh.','Stk',0,0,2400,95,45),(8,'45x195 mm. \rspærtræ ubh.','Stk',0,0,6000,195,45),(9,'45x195 mm. spærtræ ubh.','Stk',0,0,4800,195,45),(10,'97x97 mm. trykimp. \rStolpe','Stk',0,0,3000,97,97),(11,'19x100 mm. trykimp. Brædt','Stk',0,0,2100,100,19),(12,'19x100 mm. trykimp. Brædt','Stk',0,0,5400,100,19),(13,'19x100 mm. trykimp. Brædt','Stk',0,0,3600,100,19),(14,'Plastmo Ecolite blåtonet','Stk',0,0,6000,0,0),(15,'Plastmo Ecolite \r\rblåtonet','Stk',0,0,3600,0,0),(16,'Plastmo bundskruer 200 stk.','Pakke',0,0,0,0,0),(17,'Hulbånd 1x20 mm. 10 mtr.','Rulle',0,0,10000,0,0),(18,'Universal 190 mm højre','Stk',0,0,190,0,0),(19,'Universal 190 mm venstre','Stk',0,0,190,0,0),(20,'4,5 x 60 mm. skruer 200 \rstk.','Pakke',0,0,60,0,0),(21,'4,0 x 50 mm. beslagskruer 250\nstk.','Pakke',0,0,50,0,0),(22,'Bræddebolt 10 x 120 mm.','Stk',0,0,120,10,0),(23,'Firkantskiver 40x40x11mm','Stk',0,0,11,40,40),(24,'4,5 x 70 mm. Skruer 400 stk.','Pakke',0,0,70,0,0),(25,'4,5 x 50 mm. Skruer 300 stk.','Pakke',0,0,50,0,0),(26,'Stalddørsgreb 50x75 mm.','Sæt',0,0,75,50,0),(27,'T hængsel 390 mm.','Stk',0,0,390,0,0),(28,'Vinkelbeslag \r35 mm.','Stk',0,0,0,0,0);
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
                             PRIMARY KEY (`Order_id`,`Material_id`,`Description`),
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
INSERT INTO `orderline` VALUES (1,1,2,'Understernbrædder til for & bag ende'),(1,1,2,'Understernbrædder til siderne'),(1,3,1,'Oversternbrædder til forenden'),(1,3,2,'Oversternbrædder til siderne'),(1,9,2,'Remme til siderne'),(1,9,7,'Spær, monteres på rem'),(1,10,6,'Stolper nedgraves 90 cm. i jord'),(1,13,1,'Vandbrædt på stern i forende'),(1,13,2,'Vandbrædt på stern i sider'),(1,15,4,'Tagplader, monteres på spær'),(1,16,2,'Skruer til tagplader'),(1,17,1,'Til vindkryds på spær'),(1,18,7,'Til montering af spær på rem'),(1,19,7,'Til montering af spær på rem'),(1,20,1,'Til montering af stern & vandbrædt'),(1,21,3,'Til montering af universalbeslag & hulbånd'),(1,22,12,'Til montering af rem på stolper'),(1,23,6,'Til montering af rem på stolper'),(2,1,4,'Understernbrædder til for & bag ende'),(2,1,4,'Understernbrædder til siderne'),(2,3,2,'Oversternbrædder til forenden'),(2,3,4,'Oversternbrædder til siderne'),(2,5,1,'Til z på bagside af dør'),(2,8,12,'Spær, monteres på rem'),(2,9,4,'Remme til siderne'),(2,10,10,'Stolper nedgraves 90 cm. i jord'),(2,11,200,'Til beklædning af skur 1 på 2'),(2,13,2,'Vandbrædt på stern i forende'),(2,13,4,'Vandbrædt på stern i sider'),(2,15,14,'Tagplader, monteres på spær'),(2,16,4,'Skruer til tagplader'),(2,17,2,'Til vindkryds på spær'),(2,18,12,'Til montering af spær på rem'),(2,19,12,'Til montering af spær på rem'),(2,20,1,'Til montering af stern & vandbrædt'),(2,21,3,'Til montering af universalbeslag & hulbånd'),(2,22,16,'Til montering af rem på stolper'),(2,23,8,'Til montering af rem på stolper'),(2,24,2,'Til montering af yderste beklædning'),(2,25,2,'Til montering af inderste beklædning'),(2,26,1,'Til lås på dør i skur'),(2,27,2,'Til skurdør'),(2,28,32,'Til montering af løsholter i skur'),(3,1,4,'Understernbrædder til for & bag ende'),(3,1,2,'Understernbrædder til siderne'),(3,2,2,'Understernbrædder til siderne'),(3,3,2,'Oversternbrædder til forenden'),(3,3,2,'Oversternbrædder til siderne'),(3,4,2,'Oversternbrædder til siderne'),(3,5,1,'Til z på bagside af dør'),(3,8,15,'Spær, monteres på rem'),(3,9,4,'Remme til siderne'),(3,10,10,'Stolper nedgraves 90 cm. i jord'),(3,11,296,'Til beklædning af skur 1 på 2'),(3,12,2,'Vandbrædt på stern i sider'),(3,13,2,'Vandbrædt på stern i forende'),(3,13,2,'Vandbrædt på stern i sider'),(3,14,6,'Tagplader, monteres på spær'),(3,15,6,'Tagplader, monteres på spær'),(3,16,3,'Skruer til tagplader'),(3,17,2,'Til vindkryds på spær'),(3,18,15,'Til montering af spær på rem'),(3,19,15,'Til montering af spær på rem'),(3,20,1,'Til montering af stern & vandbrædt'),(3,21,3,'Til montering af universalbeslag & hulbånd'),(3,22,16,'Til montering af rem på stolper'),(3,23,8,'Til montering af rem på stolper'),(3,24,2,'Til montering af yderste beklædning'),(3,25,2,'Til montering af inderste beklædning'),(3,26,1,'Til lås på dør i skur'),(3,27,2,'Til skurdør'),(3,28,32,'Til montering af løsholter i skur'),(4,1,2,'Understernbrædder til for & bag ende'),(4,1,2,'Understernbrædder til siderne'),(4,3,1,'Oversternbrædder til forenden'),(4,3,2,'Oversternbrædder til siderne'),(4,9,2,'Remme til siderne'),(4,9,7,'Spær, monteres på rem'),(4,10,6,'Stolper nedgraves 90 cm. i jord'),(4,13,1,'Vandbrædt på stern i forende'),(4,13,2,'Vandbrædt på stern i sider'),(4,15,3,'Tagplader, monteres på spær'),(4,16,2,'Skruer til tagplader'),(4,17,1,'Til vindkryds på spær'),(4,18,7,'Til montering af spær på rem'),(4,19,7,'Til montering af spær på rem'),(4,20,1,'Til montering af stern & vandbrædt'),(4,21,3,'Til montering af universalbeslag & hulbånd'),(4,22,12,'Til montering af rem på stolper'),(4,23,6,'Til montering af rem på stolper'),(6,1,4,'Understernbrædder til for & bag ende'),(6,1,2,'Understernbrædder til siderne'),(6,3,2,'Oversternbrædder til forenden'),(6,3,2,'Oversternbrædder til siderne'),(6,8,6,'Spær, monteres på rem'),(6,9,2,'Remme til siderne'),(6,10,6,'Stolper nedgraves 90 cm. i jord'),(6,13,2,'Vandbrædt på stern i forende'),(6,13,2,'Vandbrædt på stern i sider'),(6,15,8,'Tagplader, monteres på spær'),(6,16,4,'Skruer til tagplader'),(6,17,2,'Til vindkryds på spær'),(6,18,6,'Til montering af spær på rem'),(6,19,6,'Til montering af spær på rem'),(6,20,1,'Til montering af stern & vandbrædt'),(6,21,3,'Til montering af universalbeslag & hulbånd'),(6,22,12,'Til montering af rem på stolper'),(6,23,6,'Til montering af rem på stolper'),(7,1,4,'Understernbrædder til for & bag ende'),(7,1,2,'Understernbrædder til siderne'),(7,2,2,'Understernbrædder til siderne'),(7,3,2,'Oversternbrædder til forenden'),(7,3,2,'Oversternbrædder til siderne'),(7,4,2,'Oversternbrædder til siderne'),(7,5,1,'Til z på bagside af dør'),(7,8,15,'Spær, monteres på rem'),(7,9,4,'Remme til siderne'),(7,10,10,'Stolper nedgraves 90 cm. i jord'),(7,11,188,'Til beklædning af skur 1 på 2'),(7,12,2,'Vandbrædt på stern i sider'),(7,13,2,'Vandbrædt på stern i forende'),(7,13,2,'Vandbrædt på stern i sider'),(7,14,7,'Tagplader, monteres på spær'),(7,15,7,'Tagplader, monteres på spær'),(7,16,4,'Skruer til tagplader'),(7,17,2,'Til vindkryds på spær'),(7,18,15,'Til montering af spær på rem'),(7,19,15,'Til montering af spær på rem'),(7,20,1,'Til montering af stern & vandbrædt'),(7,21,3,'Til montering af universalbeslag & hulbånd'),(7,22,16,'Til montering af rem på stolper'),(7,23,8,'Til montering af rem på stolper'),(7,24,2,'Til montering af yderste beklædning'),(7,25,2,'Til montering af inderste beklædning'),(7,26,1,'Til lås på dør i skur'),(7,27,2,'Til skurdør'),(7,28,32,'Til montering af løsholter i skur'),(8,1,4,'Understernbrædder til siderne'),(8,2,2,'Understernbrædder til for & bag ende'),(8,3,4,'Oversternbrædder til siderne'),(8,4,1,'Oversternbrædder til forenden'),(8,8,2,'Remme til siderne'),(8,8,11,'Spær, monteres på rem'),(8,10,6,'Stolper nedgraves 90 cm. i jord'),(8,12,1,'Vandbrædt på stern i forende'),(8,13,4,'Vandbrædt på stern i sider'),(8,14,6,'Tagplader, monteres på spær'),(8,16,3,'Skruer til tagplader'),(8,17,2,'Til vindkryds på spær'),(8,18,11,'Til montering af spær på rem'),(8,19,11,'Til montering af spær på rem'),(8,20,1,'Til montering af stern & vandbrædt'),(8,21,3,'Til montering af universalbeslag & hulbånd'),(8,22,12,'Til montering af rem på stolper'),(8,23,6,'Til montering af rem på stolper'),(10,1,4,'Understernbrædder til for & bag ende'),(10,1,4,'Understernbrædder til siderne'),(10,3,2,'Oversternbrædder til forenden'),(10,3,4,'Oversternbrædder til siderne'),(10,8,12,'Spær, monteres på rem'),(10,9,4,'Remme til siderne'),(10,10,6,'Stolper nedgraves 90 cm. i jord'),(10,13,2,'Vandbrædt på stern i forende'),(10,13,4,'Vandbrædt på stern i sider'),(10,15,12,'Tagplader, monteres på spær'),(10,16,3,'Skruer til tagplader'),(10,17,2,'Til vindkryds på spær'),(10,18,12,'Til montering af spær på rem'),(10,19,12,'Til montering af spær på rem'),(10,20,1,'Til montering af stern & vandbrædt'),(10,21,3,'Til montering af universalbeslag & hulbånd'),(10,22,12,'Til montering af rem på stolper'),(10,23,6,'Til montering af rem på stolper'),(11,1,2,'Understernbrædder til for & bag ende'),(11,1,4,'Understernbrædder til siderne'),(11,2,2,'Understernbrædder til for & bag ende'),(11,3,1,'Oversternbrædder til forenden'),(11,3,4,'Oversternbrædder til siderne'),(11,4,1,'Oversternbrædder til forenden'),(11,5,1,'Til z på bagside af dør'),(11,8,12,'Spær, monteres på rem'),(11,9,4,'Remme til siderne'),(11,10,10,'Stolper nedgraves 90 cm. i jord'),(11,11,380,'Til beklædning af skur 1 på 2'),(11,12,1,'Vandbrædt på stern i forende'),(11,13,1,'Vandbrædt på stern i forende'),(11,13,4,'Vandbrædt på stern i sider'),(11,15,16,'Tagplader, monteres på spær'),(11,16,4,'Skruer til tagplader'),(11,17,2,'Til vindkryds på spær'),(11,18,12,'Til montering af spær på rem'),(11,19,12,'Til montering af spær på rem'),(11,20,1,'Til montering af stern & vandbrædt'),(11,21,3,'Til montering af universalbeslag & hulbånd'),(11,22,16,'Til montering af rem på stolper'),(11,23,8,'Til montering af rem på stolper'),(11,24,2,'Til montering af yderste beklædning'),(11,25,2,'Til montering af inderste beklædning'),(11,26,1,'Til lås på dør i skur'),(11,27,2,'Til skurdør'),(11,28,32,'Til montering af løsholter i skur'),(15,2,2,'Understernbrædder til for & bag ende'),(15,2,2,'Understernbrædder til siderne'),(15,4,1,'Oversternbrædder til forenden'),(15,4,2,'Oversternbrædder til siderne'),(15,9,2,'Remme til siderne'),(15,9,8,'Spær, monteres på rem'),(15,10,6,'Stolper nedgraves 90 cm. i jord'),(15,12,1,'Vandbrædt på stern i forende'),(15,12,2,'Vandbrædt på stern i sider'),(15,14,5,'Tagplader, monteres på spær'),(15,16,3,'Skruer til tagplader'),(15,17,1,'Til vindkryds på spær'),(15,18,8,'Til montering af spær på rem'),(15,19,8,'Til montering af spær på rem'),(15,20,1,'Til montering af stern & vandbrædt'),(15,21,3,'Til montering af universalbeslag & hulbånd'),(15,22,12,'Til montering af rem på stolper'),(15,23,6,'Til montering af rem på stolper'),(16,2,2,'Understernbrædder til for & bag ende'),(16,2,2,'Understernbrædder til siderne'),(16,4,1,'Oversternbrædder til forenden'),(16,4,2,'Oversternbrædder til siderne'),(16,9,2,'Remme til siderne'),(16,9,8,'Spær, monteres på rem'),(16,10,6,'Stolper nedgraves 90 cm. i jord'),(16,12,1,'Vandbrædt på stern i forende'),(16,12,2,'Vandbrædt på stern i sider'),(16,14,5,'Tagplader, monteres på spær'),(16,16,3,'Skruer til tagplader'),(16,17,1,'Til vindkryds på spær'),(16,18,8,'Til montering af spær på rem'),(16,19,8,'Til montering af spær på rem'),(16,20,1,'Til montering af stern & vandbrædt'),(16,21,3,'Til montering af universalbeslag & hulbånd'),(16,22,12,'Til montering af rem på stolper'),(16,23,6,'Til montering af rem på stolper'),(17,1,2,'Understernbrædder til for & bag ende'),(17,1,2,'Understernbrædder til siderne'),(17,3,1,'Oversternbrædder til forenden'),(17,3,2,'Oversternbrædder til siderne'),(17,9,2,'Remme til siderne'),(17,9,5,'Spær, monteres på rem'),(17,10,6,'Stolper nedgraves 90 cm. i jord'),(17,13,1,'Vandbrædt på stern i forende'),(17,13,2,'Vandbrædt på stern i sider'),(17,15,3,'Tagplader, monteres på spær'),(17,16,2,'Skruer til tagplader'),(17,17,1,'Til vindkryds på spær'),(17,18,5,'Til montering af spær på rem'),(17,19,5,'Til montering af spær på rem'),(17,20,1,'Til montering af stern & vandbrædt'),(17,21,3,'Til montering af universalbeslag & hulbånd'),(17,22,12,'Til montering af rem på stolper'),(17,23,6,'Til montering af rem på stolper');
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,'2021-05-07 09:40:40',NULL,1,'Bestilt',360,360,0,0),(2,NULL,'2021-05-07 09:41:31',NULL,1,'Godkendt',630,630,270,240),(3,NULL,'2021-05-10 09:19:08',NULL,1,'Betalt',780,600,210,540),(4,NULL,'2021-05-10 09:19:56',NULL,1,'Afsluttet',360,240,0,0),(6,NULL,'2021-05-11 08:42:24',NULL,1,'Bestilt',300,720,0,0),(7,NULL,'2021-05-11 08:42:31',NULL,1,'Bestilt',780,660,240,240),(8,NULL,'2021-05-11 21:19:38',NULL,1,'Bestilt',600,540,0,0),(9,NULL,'2021-05-18 08:26:51',NULL,1,'Bestilt',600,390,240,540),(10,NULL,'2021-05-18 08:27:02',NULL,1,'Bestilt',660,600,0,0),(11,NULL,'2021-05-22 14:32:03',NULL,1,'Bestilt',630,750,270,690),(15,NULL,'2021-05-25 11:16:09',NULL,1,'Bestilt',420,420,0,0),(16,NULL,'2021-05-25 11:20:36',NULL,1,'Bestilt',420,420,0,0),(17,NULL,'2021-05-25 11:26:21',NULL,1,'Bestilt',240,240,0,0);
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

-- Dump completed on 2021-05-25 15:26:49
