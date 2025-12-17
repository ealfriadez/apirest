-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: apirest_db
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(36) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(36) NOT NULL,
  `route` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN','2025-12-15 20:12:00.000000','https://cdn-icons-png.flaticon.com/512/6417/6417422.png','ADMIN','admin/home','2025-12-15 20:12:00.000000'),('CLIENT','2025-12-15 20:07:00.000000','https://cdn-icons-png.flaticon.com/256/4814/4814852.png','CLIENTE','client/home','2025-12-15 20:07:00.000000'),('DRIVER','2025-12-15 20:15:00.000000','https://cdn-icons-png.flaticon.com/256/4900/4900915.png','CONDUCTOR','driver/home','2025-12-15 20:15:00.000000');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_roles`
--

DROP TABLE IF EXISTS `user_has_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_roles` (
  `id_rol` varchar(255) NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_rol`,`id_user`),
  KEY `FKbbw4mca33bf4soo1497t76pym` (`id_user`),
  CONSTRAINT `FKbbw4mca33bf4soo1497t76pym` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqgwbbpl6ysror8rl6ln4o15w6` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_roles`
--

LOCK TABLES `user_has_roles` WRITE;
/*!40000 ALTER TABLE `user_has_roles` DISABLE KEYS */;
INSERT INTO `user_has_roles` VALUES ('CLIENT',1),('CLIENT',2),('CLIENT',3),('CLIENT',4);
/*!40000 ALTER TABLE `user_has_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `notification_token` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-12-16 01:58:57.817241','pilaxis@gmail.com','/uploads/users/1/ecommerce-and-growth-icon-concept-vector.jpg','CORONADO RODRIGUEZ','DORIS PILAR',NULL,'$2a$10$/EVbTIecoTtDE/sARQBpnef5r6InmYzT0gaDe7JRL.lkjf7m970/6','989296816','2025-12-17 02:44:01.619558'),(2,'2025-12-16 03:03:46.101808','sebas@gmail.com','/uploads/users/2/images.png','ALFRIADEZ CORONADO','SEBASTIAN ALFREDO',NULL,'$2a$10$C1m55qLsbW67nTo1XcK7d./jlFNR.MZaGvmryfKxcFRN91FwCqZEm','986976324','2025-12-17 02:56:33.128236'),(3,'2025-12-17 03:13:54.438134','santiago@gmail.com','/uploads/users/3/images.jfif','ALFRIADEZ CORONADO','SANTIAGO NICOLAS',NULL,'$2a$10$qPIo1kom4BhgPGxow2dzieRbhMXKI3d41bMtlbhP6avgwWUq8E1Uy','946768506','2025-12-17 03:23:26.315442'),(4,'2025-12-17 04:41:53.359841','moka@gmail.com',NULL,'moka coronado','moka@gmail.com',NULL,'$2a$10$WGAKtgfsujH7/uHL5piZuOBqpGY12/TzuTD83Zaxs77x/bAxz8r1C','20202020','2025-12-17 04:41:53.359841');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'apirest_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-16 23:46:36
