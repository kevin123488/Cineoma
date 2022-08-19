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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `my_point` int DEFAULT NULL,
  `nickname` varchar(255) NOT NULL,
  `no` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `room_no` int DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('EhEldk','EHELdk@EHELDK.COM','profile_mafia.png',NULL,0,'또띠아',0,'EHELDK11',1494,'또띠아'),('hahahi','hahahi@naver.com','profile_mafia.png',NULL,0,'hahahi',0,'hahahi',1,'내침ㄴ아'),('hansol','hansol@naver.com','profile_mafia.png','방가방가 하이루~',0,'솔솔',0,'1234',1,'남한솔'),('jehaak','wpgkr1093@naver.com','profile_mafia.png','하이하이',0,'jehaak',0,'jehaak',1,'jehak'),('jeongssafy','정싸피@ssafy.com','profile_mafia.png',NULL,0,'정싸피',0,'wjdTkvl',1,'정싸피'),('jihun333','wlgns3914@naver.com','profile_mafia.png',NULL,0,'나도 순정이 있어',0,'dja1wkd@',1,'남한솔'),('kevin1010','kevin@ssafy.com','profile_mafia.png',NULL,0,'정현정현',0,'kevin1010',1,'박정현'),('kmc8888','kmc8888@naver.com','profile_doctor.png','시민',0,'민철민철',0,'1234',1,'민철'),('lghee22','lghee282@gmail.com','profile_mafia.png',NULL,0,'희근이',0,'fnfnfn1',1,'이근희'),('pjehak','wpgkr1093@naver.com','profile_mafia.png',NULL,0,'마피아초고수',0,'jehak',1,'박제학'),('rnjsalsdyd','asdasd@asd.asd','profile_mafia.png',NULL,0,'권민용',0,'1',1,'권민용'),('sonnim','sonnim@gmail.com','profile_mafia.png',NULL,0,'sonnim@gmail.com',0,'sonnim',1,'sonnim'),('ssafy1234','ssafy1234@ssafy.com','profile_mafia.png',NULL,0,'싸피맨',0,'1q2w3e',1,'싸피맨'),('star11','star1@a.com','profile_mafia.png',NULL,0,'star1',0,'star1',1494,'안녕'),('test111','test111@test.com','profile_doctor.png',NULL,0,'test111',0,'test111',1,'test111'),('test222','test222@test.com','profile_mafia.png',NULL,0,'test222',0,'test222',1,'test222'),('test333','test333@test.com','profile_mafia.png',NULL,0,'test333',0,'test333',1,'test333'),('test444','test444@test.com','profile_mafia.png',NULL,0,'test444',0,'test444',1,'test444'),('test555','test555@test.com','profile_mafia.png',NULL,0,'test555',0,'test555',1,'test555'),('whowho','asdf@naver.com','profile_mafia.png',NULL,0,'해커용',0,'123',1494,'해커'),('wns0394','qownstlr0394@naver.com','profile_doctor.png',NULL,0,'대 정 현',0,'wnstlr9797!',1,'배준식');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:58:46
