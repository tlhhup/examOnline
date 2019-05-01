-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: exam_auth
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_permission`
--
create database exam_auth;
use exam_auth;

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `iframe` bit(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9rtng6uswl50iw3s5a96cfe4` (`pid`),
  CONSTRAINT `FKl9rtng6uswl50iw3s5a96cfe4` FOREIGN KEY (`pid`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (6,'2019-02-04 20:21:45.839000',NULL,'system','\0',NULL,'认证中心','sytstem:*',1,'2019-02-04 20:39:07.284000','/auth',NULL),(7,'2019-02-04 20:22:24.718000',NULL,'user','\0',NULL,'用户管理','system:user',1,'2019-02-04 20:39:20.881000','user',6),(8,'2019-02-04 20:23:10.449000',NULL,'system_role','\0',NULL,'角色管理','system:role',2,'2019-02-04 20:39:31.546000','role',6),(9,'2019-02-04 20:24:54.150000',NULL,'password','\0',NULL,'权限管理','system:permission',3,'2019-02-04 20:39:40.750000','permission',6),(10,'2019-02-04 20:25:32.963000',NULL,'system','\0',NULL,'配置中心','config:*',1,'2019-02-04 20:40:00.205000','/config',NULL),(11,'2019-02-04 20:26:25.030000',NULL,'','\0',NULL,'环境配置','config:env',1,'2019-02-04 20:40:13.078000','environment',10),(12,'2019-02-04 20:28:17.909000',NULL,'env','\0',NULL,'环境配置','config:env:env',1,'2019-02-04 20:40:23.775000','env',11),(13,'2019-02-04 20:28:59.028000',NULL,'config','\0',NULL,'参数配置','config:env:param',2,'2019-02-04 20:40:34.053000','environmentParam',11),(14,'2019-02-04 20:31:05.579000',NULL,'lock','\0',NULL,'加密管理','config:env:encryp',3,'2019-02-04 20:40:43.195000','encryption',11),(15,'2019-02-04 20:31:38.146000',NULL,'','\0',NULL,'配置中心','config:config:*',2,'2019-02-04 20:40:52.813000','configCenter',10),(16,'2019-02-04 20:32:19.062000',NULL,'project','\0',NULL,'项目管理','config:config:project',1,'2019-02-04 20:41:01.828000','project',15),(17,'2019-02-04 20:32:52.902000',NULL,'param','\0',NULL,'配置管理','config:config:param',2,'2019-02-04 20:41:35.262000','configParam',15),(18,'2019-02-04 20:34:08.727000',NULL,'monitor','\0',NULL,'监控中心','monitor:*',3,'2019-02-04 20:41:50.223000','monitor',NULL),(19,'2019-02-04 20:36:28.319000',NULL,'eureka','\0',NULL,'注册中心','monitor:register',1,'2019-02-04 20:42:09.148000','http://localhost:10011',18),(20,'2019-02-04 20:37:01.618000',NULL,'monitor_app','\0',NULL,'应用监控','monitor:app',2,'2019-02-04 20:42:20.632000','http://localhost:10015/#/applications',18),(21,'2019-02-04 20:37:30.327000',NULL,'monitor_trace','\0',NULL,'链路监控','monitor:trace',3,'2019-02-04 20:42:31.263000','http://localhost:9411/',18);
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_roles`
--

DROP TABLE IF EXISTS `auth_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_value` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_roles`
--

LOCK TABLES `auth_roles` WRITE;
/*!40000 ALTER TABLE `auth_roles` DISABLE KEYS */;
INSERT INTO `auth_roles` VALUES (7,'2019-01-27 13:56:48.320000','admin','超级用户','','admin',0,'2019-01-27 13:56:48.353000');
/*!40000 ALTER TABLE `auth_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_roles_permissions`
--

DROP TABLE IF EXISTS `auth_roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) NOT NULL,
  KEY `FKq40yyfxdkm6n9lgen9u06sgao` (`permissions_id`),
  KEY `FKs3encvd07w2v7ksm0y513gcsd` (`role_id`),
  CONSTRAINT `FKq40yyfxdkm6n9lgen9u06sgao` FOREIGN KEY (`permissions_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `FKs3encvd07w2v7ksm0y513gcsd` FOREIGN KEY (`role_id`) REFERENCES `auth_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_roles_permissions`
--

LOCK TABLES `auth_roles_permissions` WRITE;
/*!40000 ALTER TABLE `auth_roles_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_users`
--

DROP TABLE IF EXISTS `auth_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5ymwwawhjvvuw2fqrqoxdneht` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_users`
--

LOCK TABLES `auth_users` WRITE;
/*!40000 ALTER TABLE `auth_users` DISABLE KEYS */;
INSERT INTO `auth_users` VALUES (1,'2019-01-27 12:57:01.000000','','ea36156448119724a507b503fe50e7258b59f3f9080da51f60ba26e6fb96204628c28d0f6f53f879','2019-01-27 22:10:59.800000','admin',1),(7,'2019-02-24 18:22:26.748000','','23a71de86c43cbc4250f57a092a31ed2db40089777305704e0eafe2aaf54d92430e5924b9b4d53c2','2019-02-24 18:26:15.288000','test123',1);
/*!40000 ALTER TABLE `auth_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_users_roles`
--

DROP TABLE IF EXISTS `auth_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_users_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  KEY `FKc0icxqyw4g0a4r7aot5rrcbi1` (`roles_id`),
  KEY `FKptxbe0rfpj7wffab0089q0egl` (`user_id`),
  CONSTRAINT `FKc0icxqyw4g0a4r7aot5rrcbi1` FOREIGN KEY (`roles_id`) REFERENCES `auth_roles` (`id`),
  CONSTRAINT `FKptxbe0rfpj7wffab0089q0egl` FOREIGN KEY (`user_id`) REFERENCES `auth_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_users_roles`
--

LOCK TABLES `auth_users_roles` WRITE;
/*!40000 ALTER TABLE `auth_users_roles` DISABLE KEYS */;
INSERT INTO `auth_users_roles` VALUES (1,7),(7,7);
/*!40000 ALTER TABLE `auth_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-01 13:38:18
