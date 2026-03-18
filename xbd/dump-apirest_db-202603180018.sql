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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `neighborhood` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg660itnc13uyo7s04yrqmcn3r` (`id_user`),
  CONSTRAINT `FKg660itnc13uyo7s04yrqmcn3r` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (2,3,'Calle 2','2026-03-18 04:13:51.883603','San Francisco Ñaña','2026-03-18 04:19:44.236153');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `description` longtext,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (12,'2026-02-07 01:29:55.000000','Soportes para celular y cargadores USB resistentes al uso diario en bicicleta y motocicleta.','/uploads/categories/12/soportes_y_cargadores.webp','Soportes y Cargadores Móviles','2026-02-07 01:29:55.000000'),(13,'2026-02-07 01:29:55.000000','Luces LED delanteras y traseras, alarmas y accesorios de seguridad para una conducción segura.','/uploads/categories/13/luces_y_seguridad.png','Luces y Seguridad','2026-02-07 01:29:55.000000'),(14,'2026-02-07 01:29:55.000000','Cámaras tipo GoPro y alternativas para grabar rutas, viajes y aventuras.','/uploads/categories/14/camaras_deportivas.jpg','Cámaras Deportivas','2026-02-07 01:29:55.000000'),(15,'2026-02-07 01:29:55.000000','Alforjas, bolsos impermeables y maletas diseñadas para viajes y uso urbano.','/uploads/categories/15/17706096618887202758434129836145.png','Bolsos y Equipaje','2026-02-09 04:01:13.336145'),(16,'2026-02-07 01:29:55.000000','Intercomunicadores Bluetooth para cascos, ideales para llamadas y navegación.','/uploads/categories/15/17706096618887202758434129836145.png','Comunicadores Bluetooth','2026-02-07 01:29:55.000000'),(17,'2026-02-07 01:29:55.000000','Power banks resistentes, baterías externas y soluciones de energía portátil.','/uploads/categories/17/17706096784127924655151009962611.png','Power Banks y Energía','2026-02-09 04:01:32.160151'),(24,'2026-02-10 04:21:55.541773','demo','/uploads/categories/24/17706972916412727814552114986218.png','demo','2026-02-10 04:21:56.197778');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_category` bigint NOT NULL,
  `description` text,
  `image1` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `price` double NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKip7b0y8ja7fsm5wl7mhmseh5n` (`id_category`),
  CONSTRAINT `FKip7b0y8ja7fsm5wl7mhmseh5n` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (4,14,'Tecnología ','/uploads/products/4/17709519978044681868809572251680.png','/uploads/products/4/17709520021471788314608431499332.png','Técnico ','2026-02-13 03:07:05.948664',1500,'2026-02-13 03:07:06.127675'),(8,17,'extracraneal 12345','/uploads/products/8/17713787792217367390512292621400.png','/uploads/products/8/17711285301315029090770658922456.png','audífonos 12345','2026-02-15 04:09:23.861704',250,'2026-02-18 01:39:41.871297'),(13,17,'yu','/uploads/products/13/17713788467801595557648001350948.png','/uploads/products/13/17713788519004883261415919964386.png','yu','2026-02-18 01:40:54.901446',5.5025,'2026-02-18 01:40:56.194447'),(14,24,'abc ty','/uploads/products/14/17713836767588355209445641059130.png','/uploads/products/14/17713834786047707791281557053639.png','abc ty','2026-02-18 02:58:18.187947',120,'2026-02-18 03:01:21.867977');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `roles` VALUES ('ADMIN','2025-12-15 20:12:00.000000','https://cdn-icons-png.flaticon.com/512/6417/6417422.png','ADMINISTRADOR','admin_graph','2025-12-15 20:12:00.000000'),('CLIENT','2025-12-15 20:07:00.000000','https://cdn-icons-png.flaticon.com/256/4814/4814852.png','CLIENTE','client_graph','2025-12-15 20:07:00.000000'),('DRIVER','2025-12-15 20:15:00.000000','https://cdn-icons-png.flaticon.com/256/4900/4900915.png','CONDUCTOR','driver/home','2025-12-15 20:15:00.000000');
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
INSERT INTO `user_has_roles` VALUES ('CLIENT',1),('CLIENT',2),('DRIVER',2),('ADMIN',3),('CLIENT',3),('CLIENT',4),('CLIENT',5),('CLIENT',6),('CLIENT',7);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-12-16 01:58:57.817241','pilaxis@gmail.com','https://cdn-icons-png.flaticon.com/512/6417/6417422.png','CORONADO RODRIGUEZ','DORIS PILAR',NULL,'$2a$10$/EVbTIecoTtDE/sARQBpnef5r6InmYzT0gaDe7JRL.lkjf7m970/6','989296816','2025-12-17 02:44:01.619558'),(2,'2025-12-16 03:03:46.101808','sebas@gmail.com','/uploads/users/2/images.png','ALFRIADEZ CORONADO','SEBASTIAN ALFREDO',NULL,'$2a$10$C1m55qLsbW67nTo1XcK7d./jlFNR.MZaGvmryfKxcFRN91FwCqZEm','986976324','2025-12-17 02:56:33.128236'),(3,'2025-12-17 03:13:54.438134','santiago@gmail.com','/uploads/users/3/images.jfif','ALFRIADEZ CORONADO','SANTIAGO NICOLAS',NULL,'$2a$10$qPIo1kom4BhgPGxow2dzieRbhMXKI3d41bMtlbhP6avgwWUq8E1Uy','946768506','2025-12-17 03:23:26.315442'),(4,'2025-12-17 04:41:53.359841','moka@gmail.com',NULL,'moka coronado','moka@gmail.com',NULL,'$2a$10$WGAKtgfsujH7/uHL5piZuOBqpGY12/TzuTD83Zaxs77x/bAxz8r1C','20202020','2025-12-17 04:41:53.359841'),(5,'2025-12-26 08:41:43.025359','ronco@gmail.com','/uploads/users/5/17699986937713400290767393675704.png','frances 12345 ','ronco 12345',NULL,'$2a$10$jhNf2KLhUgNns/jEDy3YqeVum/0fkS0tIi16S1ys5e.CU4O4UrfhS','987654321','2026-02-02 02:18:34.185651'),(6,'2025-12-27 01:35:40.164361','nacho@gmail.com',NULL,'quispe','nacho',NULL,'$2a$10$ctDrawfWvVep3/7SJg5M5evvqpl3bmDg5w1ppSw7NeCLifzlojUKK','999999999','2025-12-27 01:35:40.173362'),(7,'2026-01-30 00:58:17.659398','kuko@gmail.com',NULL,'kuko','kuko',NULL,'$2a$10$Le7X9M18NpNyLG.maFqx6ut9jHxWnhabldP0Ek9bt5yP6D/borOfe','00000000','2026-01-30 00:58:17.660383');
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

-- Dump completed on 2026-03-18  0:18:18
