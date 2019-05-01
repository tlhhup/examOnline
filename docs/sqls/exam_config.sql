-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: exam_config
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
-- Table structure for table `encrypt_key`
--
create database exam_config;
use exam_config;

DROP TABLE IF EXISTS `encrypt_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encrypt_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `e_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encrypt_key`
--

LOCK TABLES `encrypt_key` WRITE;
/*!40000 ALTER TABLE `encrypt_key` DISABLE KEYS */;
INSERT INTO `encrypt_key` VALUES (12,'fsdf'),(13,'daffffff'),(14,'ff');
/*!40000 ALTER TABLE `encrypt_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `env`
--

DROP TABLE IF EXISTS `env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `env` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_server_name` varchar(255) DEFAULT NULL,
  `context_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `registry_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mgywbisx209jpyo855ft7h846` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `env`
--

LOCK TABLES `env` WRITE;
/*!40000 ALTER TABLE `env` DISABLE KEYS */;
INSERT INTO `env` VALUES (36,'EXAM-CONFIG-SERVER','','dev','http://localhost:10011/eureka'),(47,'f','f','test','f');
/*!40000 ALTER TABLE `env` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `env_param`
--

DROP TABLE IF EXISTS `env_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `env_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_key` varchar(255) DEFAULT NULL,
  `p_value` varchar(255) DEFAULT NULL,
  `env_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt9r9ll92k616y8mq8ud1ydy15` (`env_id`),
  CONSTRAINT `FKt9r9ll92k616y8mq8ud1ydy15` FOREIGN KEY (`env_id`) REFERENCES `env` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `env_param`
--

LOCK TABLES `env_param` WRITE;
/*!40000 ALTER TABLE `env_param` DISABLE KEYS */;
INSERT INTO `env_param` VALUES (49,'dfas','fasd',36),(50,'ff','fasdf',36);
/*!40000 ALTER TABLE `env_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (160),(160),(160),(160),(160),(160);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4nf623d0nwyd4aqje3d3cxx1b` (`project_id`),
  CONSTRAINT `FK4nf623d0nwyd4aqje3d3cxx1b` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (17,'master',16),(23,'dev',16),(44,'master',43),(132,'dev',43),(152,'master',151);
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3k75vvu7mevyvvb5may5lj8k7` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (151,'exam-user'),(43,'fasd'),(16,'scca-client');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_envs`
--

DROP TABLE IF EXISTS `project_envs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_envs` (
  `projects_id` bigint(20) NOT NULL,
  `envs_id` bigint(20) NOT NULL,
  KEY `FKxjs9tfii16deymxa85bk0016` (`envs_id`),
  KEY `FKmb8biqw9t9ikgfhvyoe792vd` (`projects_id`),
  CONSTRAINT `FKmb8biqw9t9ikgfhvyoe792vd` FOREIGN KEY (`projects_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKxjs9tfii16deymxa85bk0016` FOREIGN KEY (`envs_id`) REFERENCES `env` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_envs`
--

LOCK TABLES `project_envs` WRITE;
/*!40000 ALTER TABLE `project_envs` DISABLE KEYS */;
INSERT INTO `project_envs` VALUES (16,47),(43,36),(43,47),(151,36);
/*!40000 ALTER TABLE `project_envs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_key` varchar(255) DEFAULT NULL,
  `p_value` varchar(255) DEFAULT NULL,
  `env_id` bigint(20) DEFAULT NULL,
  `label_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc9p3ohe2voalce0d6kr6eif4i` (`env_id`),
  KEY `FK1oldmt4arjswo2i73c03euk0` (`label_id`),
  KEY `FKse1wjbyeeeq1i1bd4huqyvhw9` (`project_id`),
  CONSTRAINT `FK1oldmt4arjswo2i73c03euk0` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`),
  CONSTRAINT `FKc9p3ohe2voalce0d6kr6eif4i` FOREIGN KEY (`env_id`) REFERENCES `env` (`id`),
  CONSTRAINT `FKse1wjbyeeeq1i1bd4huqyvhw9` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (142,'ddd','asda',47,44,43),(143,'df','fasd',47,132,43),(148,'zhangsan.address','chengdu',36,44,43),(149,'zhangsan.name','lisi',36,44,43),(150,'zhangsan.age','10',36,44,43),(159,'exam.user.name','qwe1231阿法',36,152,151);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-01 13:36:37
