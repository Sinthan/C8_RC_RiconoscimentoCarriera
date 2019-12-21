CREATE DATABASE  IF NOT EXISTS `englishvalidation` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `englishvalidation`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: englishvalidation
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `attached`
--

DROP TABLE IF EXISTS `attached`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attached` (
  `ID_ATTACHED` int(20) NOT NULL AUTO_INCREMENT,
  `FILENAME` varchar(50) NOT NULL,
  `FK_REQUEST` int(20) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_ATTACHED`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attached`
--

LOCK TABLES `attached` WRITE;
/*!40000 ALTER TABLE `attached` DISABLE KEYS */;
INSERT INTO `attached` VALUES (1,'certificato.pdf',1,'prova@unisa.it'),(12,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(13,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(18,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(19,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(24,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(25,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(30,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(31,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(36,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(37,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(42,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(43,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(48,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(49,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(54,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(55,'certificato.pdf',8,'a.prova@studenti.unisa.it');
/*!40000 ALTER TABLE `attached` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contains`
--

DROP TABLE IF EXISTS `contains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contains` (
  `FK_REQUEST_RC` int(20) NOT NULL,
  `FK_EXAM` int(20) NOT NULL,
  PRIMARY KEY (`FK_REQUEST_RC`,`FK_EXAM`),
  KEY `FK_EXAM` (`FK_EXAM`),
  CONSTRAINT `contains_ibfk_1` FOREIGN KEY (`FK_REQUEST_RC`) REFERENCES `request_rc` (`id_request`),
  CONSTRAINT `contains_ibfk_2` FOREIGN KEY (`FK_EXAM`) REFERENCES `exam` (`id_exam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contains`
--

LOCK TABLES `contains` WRITE;
/*!40000 ALTER TABLE `contains` DISABLE KEYS */;
INSERT INTO `contains` VALUES (1,1),(2,2),(1,3);
/*!40000 ALTER TABLE `contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ente`
--

DROP TABLE IF EXISTS `ente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ente` (
  `ID_ENTE` int(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `SITE` varchar(50) NOT NULL,
  `STATO` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_ENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ente`
--

LOCK TABLES `ente` WRITE;
/*!40000 ALTER TABLE `ente` DISABLE KEYS */;
INSERT INTO `ente` VALUES (1,'','Cambridge Assessment English','',1),(2,'','City and Guilds (Pitman)','',1),(3,'','Edexcel /Pearson Ltd','',1),(4,'','Educational Testing Service (ETS)','',1),(5,'','English Speaking Board (ESB)','',1),(6,'','International English Language Testing System (IELTS)','',1),(7,'','Pearson - LCCI','',1),(8,'','Pearson - EDI','',1),(9,'','Trinity College London (TCL)','',1),(10,'','Department of English, Faculty of Arts - University of Malta','',1),(11,'','NQAI - ACELS','',1),(12,'','Ascentis','',1),(13,'','AIM Awards','',1),(14,'','Learning Resource Network (LRN)','',1),(15,'','British Institutes','',1),(16,'','Gatehouse Awards Ltd','',1),(17,'','LanguageCert','',1);
/*!40000 ALTER TABLE `ente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exam` (
  `ID_EXAM` int(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `CFU` tinyint(1) NOT NULL,
  `LINK_PROGRAM` varchar(256) NOT NULL,
  PRIMARY KEY (`ID_EXAM`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'Programmazione 1',12,'www.sito.it/dati/esame/prog1'),(2,'Analisi 1',9,'www.sito.it/dati/esame/anal1'),(3,'IS',9,'www.sito.it/dati/esame/IS'),(8,'ingegneria del test',9,'//link di riferimento//');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_pdf`
--

DROP TABLE IF EXISTS `file_pdf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `file_pdf` (
  `ID_PDF` int(20) NOT NULL AUTO_INCREMENT,
  `LINK_PDF` varchar(100) NOT NULL,
  `FK_ID_REQUEST_RC` int(20) NOT NULL,
  PRIMARY KEY (`ID_PDF`),
  KEY `FK_ID_REQUEST_RC` (`FK_ID_REQUEST_RC`),
  CONSTRAINT `file_pdf_ibfk_1` FOREIGN KEY (`FK_ID_REQUEST_RC`) REFERENCES `request_rc` (`id_request`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_pdf`
--

LOCK TABLES `file_pdf` WRITE;
/*!40000 ALTER TABLE `file_pdf` DISABLE KEYS */;
INSERT INTO `file_pdf` VALUES (1,'FILEPDFCARR1',1),(2,'FILEPDFDOC1',1),(3,'FILEPDFCARR2',2),(4,'FILEPDFDOC2',2);
/*!40000 ALTER TABLE `file_pdf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `report` (
  `ID_REPORT` int(20) NOT NULL AUTO_INCREMENT,
  `NOTE` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`ID_REPORT`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'La sua carriera ok okok'),(2,'La sua carriera NON è ok okok'),(3,'La sua carriera NON è ok ok'),(4,'la tua carriera è ok'),(5,'la tua richiesta è ok');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `ID_REQUEST` int(20) NOT NULL AUTO_INCREMENT,
  `CERTIFICATE_SERIAL` varchar(50) NOT NULL,
  `LEVEL` varchar(7) NOT NULL,
  `RELEASE_DATE` date NOT NULL,
  `EXPIRY_DATE` date NOT NULL,
  `YEAR` year(4) NOT NULL,
  `REQUESTED_CFU` tinyint(2) NOT NULL,
  `SERIAL` int(10) NOT NULL,
  `VALIDATED_CFU` tinyint(2) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  `FK_CERTIFIER` int(20) NOT NULL,
  `FK_STATE` int(20) NOT NULL,
  PRIMARY KEY (`ID_REQUEST`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova@unisa.it',2,3),(2,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova2@unisa.it',2,4),(3,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova3@unisa.it',3,3),(4,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova4@unisa.it',4,7),(5,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova5@unisa.it',3,4),(6,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova6@unisa.it',3,3),(7,'A00000001','A1','2015-02-14','2020-02-14',2018,6,512103578,0,'a.prova@studenti.unisa.it',1,1),(8,'A00000001','A1','2015-02-14','2020-02-14',2018,6,512103579,0,'l.l@studenti.unisa.it',1,2);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_rc`
--

DROP TABLE IF EXISTS `request_rc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request_rc` (
  `ID_REQUEST` int(20) NOT NULL AUTO_INCREMENT,
  `DATE_REQUEST` date NOT NULL,
  `STATE` int(20) NOT NULL,
  `FK_UNIVERSITY` varchar(50) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  `FK_REPORT` int(20) DEFAULT NULL,
  `FK_EMAIL_UC` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_REQUEST`),
  KEY `FK_UNIVERSITY` (`FK_UNIVERSITY`),
  KEY `FK_USER` (`FK_USER`),
  KEY `FK_REPORT` (`FK_REPORT`),
  KEY `FK_EMAIL_UC` (`FK_EMAIL_UC`),
  CONSTRAINT `request_rc_ibfk_1` FOREIGN KEY (`FK_UNIVERSITY`) REFERENCES `university` (`name`),
  CONSTRAINT `request_rc_ibfk_2` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`email`),
  CONSTRAINT `request_rc_ibfk_3` FOREIGN KEY (`FK_REPORT`) REFERENCES `report` (`id_report`),
  CONSTRAINT `request_rc_ibfk_4` FOREIGN KEY (`FK_EMAIL_UC`) REFERENCES `uc` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_rc`
--

LOCK TABLES `request_rc` WRITE;
/*!40000 ALTER TABLE `request_rc` DISABLE KEYS */;
INSERT INTO `request_rc` VALUES (1,'2019-03-11',1,'UNINA','provaRC1@unisa.it',1,'UC@unisa.it'),(2,'2020-01-30',2,'UNIBO','provaRC2@unisa.it',2,'UC@unisa.it'),(3,'2020-04-10',3,'UNIBO','prova2@unisa.it',1,'UC@unisa.it'),(4,'2019-12-13',3,'UNIBO','dekio.95@hotmail.it',4,'UC@unisa.it'),(5,'2019-11-12',2,'UNISA','g.damiano@studenti.unisa.it',5,'UC@unisa.it');
/*!40000 ALTER TABLE `request_rc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `state` (
  `ID_STATE` int(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_STATE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Parzialmente Completata'),(2,'In elaborazione dalla Segreteria'),(3,'In elaborazione dall&quot; Amministratore'),(4,'Accettata e In elaborazione dal Consiglio Didattico'),(5,'Rifiutata e In elaborazione dal Consiglio Didattico'),(6,'Conclusa e Accettata'),(7,'Conclusa e Rifiutata');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sugestion`
--

DROP TABLE IF EXISTS `sugestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sugestion` (
  `NAME_UNIVERSITY` varchar(50) NOT NULL,
  `NAME_EXAM_EXTERN` varchar(50) NOT NULL,
  `NUMBER_CFU_EXTERN` varchar(30) NOT NULL,
  `MODE_VALIDATION` varchar(50) NOT NULL,
  `DATE_VALIDATION` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugestion`
--

LOCK TABLES `sugestion` WRITE;
/*!40000 ALTER TABLE `sugestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sugestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_attribute`
--

DROP TABLE IF EXISTS `system_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `system_attribute` (
  `SLUG` varchar(50) NOT NULL,
  `VALUE` varchar(100) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`SLUG`),
  KEY `FK_USER` (`FK_USER`),
  CONSTRAINT `system_attribute_ibfk_1` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attribute`
--

LOCK TABLES `system_attribute` WRITE;
/*!40000 ALTER TABLE `system_attribute` DISABLE KEYS */;
INSERT INTO `system_attribute` VALUES ('request-accepted','6','fferrucci@unisa.it'),('request-allowed-extension-upload','.pdf','fferrucci@unisa.it'),('request-matriculation-year-range','5','fferrucci@unisa.it'),('request-max-cfu','12','fferrucci@unisa.it'),('request-min-cfu','1','fferrucci@unisa.it'),('request-number-max-upload','2','fferrucci@unisa.it'),('request-partially-completed','1','fferrucci@unisa.it'),('request-refused','7','fferrucci@unisa.it'),('request-upload-path','//home//vale//newWorkspace//EV_EnglishValidation//uploads//','fferrucci@unisa.it'),('request-working-admin','3','fferrucci@unisa.it'),('request-working-educational-advice-1','4','fferrucci@unisa.it'),('request-working-educational-advice-2','5','fferrucci@unisa.it'),('request-working-secretary','2','fferrucci@unisa.it');
/*!40000 ALTER TABLE `system_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uc`
--

DROP TABLE IF EXISTS `uc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uc` (
  `EMAIL` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `TELEPHONE` varchar(10) NOT NULL,
  `FAX` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uc`
--

LOCK TABLES `uc` WRITE;
/*!40000 ALTER TABLE `uc` DISABLE KEYS */;
INSERT INTO `uc` VALUES ('UC@unisa.it','9611edf7f716b00c8a44441973906aa7f5c0c580','3331231233','3331231233');
/*!40000 ALTER TABLE `uc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `university` (
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES ('UNIBO'),('UNINA'),('UNISA');
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `SEX` char(1) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `USER_TYPE` tinyint(1) NOT NULL,
  `DATE_REGISTRATION` date DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('a.cassese9@studenti.unisa.it','agostino','cassese','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-19'),('aaaa@stnti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('aaaa@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('ag2owpdiqs@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('dekio.95@hotmail.it','vincenzo','de chiara','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-14'),('dvypntcp7w@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('fferrucci@unisa.it','Luigia','Melchionno','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',2,NULL),('g.c@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('g.damiano@studenti.unisa.it','gerardo','damiano','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-14'),('kiwi@gmail.it','fefewfew','fewfewfwef','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-20'),('lollo@gmail.it','lorenzo','maturo','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-14'),('pjiqenetr3@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('prova2@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9e',0,NULL),('prova3@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9c',0,NULL),('prova4@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9f',0,NULL),('prova5@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9g',0,NULL),('prova6@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9h',0,NULL),('prova@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9d',0,NULL),('provaaaa@unisa.it','fewfewfewfe','cewfewf','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-19'),('provaRC1@unisa.it','PROVA1','RC1','M','12345678',0,'2001-01-10'),('provaRC2@unisa.it','PROVA2','RC2','M','12345678',0,'2020-10-20'),('qletto@gmail.it','dewdewd','dewdewdew','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-20'),('quarto@gmail.it','fefewf','fewfewfwe','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-20'),('qvxfwy64md@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('r.deluca@studenti.unisa.it','feafeaffeafc','fewcew','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-21'),('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',1,NULL),('soalzitqpx@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('testreg@gmail.it','fefewfewfewf','fewfewfewtes','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2019-12-21'),('ujlhmqdboi@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19'),('v.dechiara11@studenti.unisa.it','vincenzo','de chiara','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,NULL),('wmouynplzy@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2019-12-19');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validate_exam`
--

DROP TABLE IF EXISTS `validate_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `validate_exam` (
  `ID_EXAM_VALIDATE` int(20) NOT NULL AUTO_INCREMENT,
  `NAME_EXAM` varchar(50) NOT NULL,
  `CFU_CONVALIDATED` tinyint(1) NOT NULL,
  `MODE_VALIDATION` varchar(50) NOT NULL,
  `FK_ID_REPORT` int(20) NOT NULL,
  PRIMARY KEY (`ID_EXAM_VALIDATE`),
  KEY `FK_ID_REPORT` (`FK_ID_REPORT`),
  CONSTRAINT `validate_exam_ibfk_1` FOREIGN KEY (`FK_ID_REPORT`) REFERENCES `report` (`id_report`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validate_exam`
--

LOCK TABLES `validate_exam` WRITE;
/*!40000 ALTER TABLE `validate_exam` DISABLE KEYS */;
INSERT INTO `validate_exam` VALUES (1,'PROGRAMMAZIONE 1',9,'è stato validato bene',1),(2,'ANALISI 1',0,'non sono stati validati cfu',2),(3,'ANALISI 1',0,'non sono stati validati cfu',3);
/*!40000 ALTER TABLE `validate_exam` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-21 19:09:38
