-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7e107.p.ssafy.io    Database: mafia
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

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
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `no` int NOT NULL,
  `draw_count` int DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `lose_count` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `win_count` int DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (222,0,'hansol',3,'mafia',2),(223,0,'hansol',2,'police',12),(224,0,'hansol',2,'doctor',5),(225,0,'hansol',1,'citizen',8),(710,0,'sonnim',0,'mafia',0),(711,0,'sonnim',0,'police',0),(712,0,'sonnim',0,'doctor',0),(713,0,'sonnim',0,'citizen',0),(735,0,'test111',0,'mafia',0),(736,0,'test111',4,'police',0),(737,0,'test111',0,'doctor',0),(738,0,'test111',0,'citizen',0),(739,0,'test222',5,'mafia',0),(740,0,'test222',0,'police',0),(741,0,'test222',0,'doctor',0),(742,0,'test222',1,'citizen',1),(743,0,'test333',3,'mafia',0),(744,0,'test333',0,'police',0),(745,0,'test333',5,'doctor',0),(746,0,'test333',0,'citizen',2),(747,0,'test444',0,'mafia',0),(748,0,'test444',0,'police',0),(749,0,'test444',4,'doctor',0),(750,0,'test444',4,'citizen',1),(751,0,'test555',2,'mafia',0),(752,0,'test555',0,'police',0),(753,0,'test555',2,'doctor',0),(754,0,'test555',4,'citizen',3),(942,0,'jehaak',3,'mafia',0),(943,0,'jehaak',1,'police',0),(944,0,'jehaak',7,'doctor',0),(945,0,'jehaak',1,'citizen',4),(975,0,'ssafy1234',0,'mafia',0),(976,0,'ssafy1234',0,'police',0),(977,0,'ssafy1234',0,'doctor',0),(978,0,'ssafy1234',0,'citizen',0),(979,0,'jihun333',0,'mafia',0),(980,0,'jihun333',0,'police',0),(981,0,'jihun333',0,'doctor',0),(982,0,'jihun333',0,'citizen',0),(993,0,'hahahi',0,'mafia',0),(994,0,'hahahi',0,'police',0),(995,0,'hahahi',0,'doctor',0),(996,0,'hahahi',0,'citizen',0),(1224,0,'jeongssafy',0,'mafia',0),(1225,0,'jeongssafy',0,'police',0),(1226,0,'jeongssafy',0,'doctor',0),(1227,0,'jeongssafy',0,'citizen',0),(1349,0,'rnjsalsdyd',7,'mafia',4),(1350,0,'rnjsalsdyd',1,'police',0),(1351,0,'rnjsalsdyd',3,'doctor',2),(1352,0,'rnjsalsdyd',3,'citizen',4),(1358,0,'lghee22',1,'mafia',2),(1359,0,'lghee22',2,'police',2),(1360,0,'lghee22',2,'doctor',1),(1361,0,'lghee22',1,'citizen',5),(1362,0,'kmc8888',2,'mafia',3),(1363,0,'kmc8888',7,'police',2),(1364,0,'kmc8888',3,'doctor',0),(1365,0,'kmc8888',0,'citizen',2),(1443,0,'wns0394',0,'mafia',0),(1444,0,'wns0394',0,'police',0),(1445,0,'wns0394',0,'doctor',0),(1446,0,'wns0394',0,'citizen',0),(1509,0,'whowho',0,'mafia',0),(1510,0,'whowho',0,'police',0),(1511,0,'whowho',0,'doctor',0),(1512,0,'whowho',0,'citizen',0),(1513,0,'EhEldk',0,'mafia',0),(1514,0,'EhEldk',0,'police',0),(1515,0,'EhEldk',0,'doctor',0),(1516,0,'EhEldk',0,'citizen',0),(1518,0,'star11',0,'mafia',0),(1519,0,'star11',0,'police',0),(1520,0,'star11',0,'doctor',0),(1521,0,'star11',0,'citizen',0),(1524,0,'pjehak',0,'mafia',0),(1525,0,'pjehak',0,'police',0),(1526,0,'pjehak',0,'doctor',0),(1527,0,'pjehak',0,'citizen',0),(1535,0,'kevin1010',2,'mafia',4),(1536,0,'kevin1010',2,'police',3),(1537,0,'kevin1010',2,'doctor',2),(1538,0,'kevin1010',3,'citizen',1);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:58:44
