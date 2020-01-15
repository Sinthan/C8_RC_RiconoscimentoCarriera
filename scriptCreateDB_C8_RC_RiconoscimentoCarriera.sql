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
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attached`
--

LOCK TABLES `attached` WRITE;
/*!40000 ALTER TABLE `attached` DISABLE KEYS */;
INSERT INTO `attached` (`ID_ATTACHED`, `FILENAME`, `FK_REQUEST`, `FK_USER`) VALUES (1,'certificato.pdf',1,'prova@unisa.it'),(12,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(13,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(18,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(19,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(24,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(25,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(30,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(31,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(36,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(37,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(42,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(43,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(48,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(49,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(54,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(55,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(60,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(61,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(66,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(67,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(72,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(73,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(78,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(79,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(84,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(85,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(90,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(91,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(96,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(97,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(102,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(103,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(108,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(109,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(114,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(115,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(120,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(121,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(126,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(127,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(132,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(133,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(138,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(139,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(144,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(145,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(150,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(151,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(156,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(157,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(162,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(163,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(168,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(169,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(174,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(175,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(180,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(181,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(186,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(187,'certificato.pdf',8,'a.prova@studenti.unisa.it'),(192,'richiesta.pdf',8,'a.prova@studenti.unisa.it'),(193,'certificato.pdf',8,'a.prova@studenti.unisa.it');
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
  CONSTRAINT `contains_ibfk_1` FOREIGN KEY (`FK_REQUEST_RC`) REFERENCES `request_rc` (`id_request`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contains_ibfk_2` FOREIGN KEY (`FK_EXAM`) REFERENCES `exam` (`id_exam`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contains`
--

LOCK TABLES `contains` WRITE;
/*!40000 ALTER TABLE `contains` DISABLE KEYS */;
INSERT INTO `contains` (`FK_REQUEST_RC`, `FK_EXAM`) VALUES (2,2),(4,2),(4,4),(4,5),(4,6),(4,7),(16,19),(21,19),(25,19);
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
INSERT INTO `ente` (`ID_ENTE`, `EMAIL`, `NAME`, `SITE`, `STATO`) VALUES (1,'','Cambridge Assessment English','',1),(2,'','City and Guilds (Pitman)','',1),(3,'','Edexcel /Pearson Ltd','',1),(4,'','Educational Testing Service (ETS)','',1),(5,'','English Speaking Board (ESB)','',1),(6,'','International English Language Testing System (IELTS)','',1),(7,'','Pearson - LCCI','',1),(8,'','Pearson - EDI','',1),(9,'','Trinity College London (TCL)','',1),(10,'','Department of English, Faculty of Arts - University of Malta','',1),(11,'','NQAI - ACELS','',1),(12,'','Ascentis','',1),(13,'','AIM Awards','',1),(14,'','Learning Resource Network (LRN)','',1),(15,'','British Institutes','',1),(16,'','Gatehouse Awards Ltd','',1),(17,'','LanguageCert','',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` (`ID_EXAM`, `NAME`, `CFU`, `LINK_PROGRAM`) VALUES (1,'Programmazione 1',12,'www.sito.it/dati/esame/prog1'),(2,'Analisi Matematica I',9,'http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/147-analisi-matematica-i'),(3,'IS',9,'www.sito.it/dati/esame/IS'),(4,'Algoritmi e strutture dati I',9,'http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/148-algoritmi-e-strutture-dati-i'),(5,'Economia ed organizzazione aziendale',6,'http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/87-economia-ed-organizzazione-aziendale'),(6,'Architettura degli elaboratori',9,'http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/146-architettura-degli-elaboratori-i'),(7,'Fisica Generale I',6,'http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/141-fisica-generale-i'),(8,'testtest',9,'//link di riferimento//'),(19,'programmazione 1',9,'http://unisa.it/programmazione1');
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
  `LINK_PDF` varchar(200) NOT NULL,
  `FK_ID_REQUEST_RC` int(20) NOT NULL,
  PRIMARY KEY (`ID_PDF`),
  KEY `FK_ID_REQUEST_RC` (`FK_ID_REQUEST_RC`),
  CONSTRAINT `file_pdf_ibfk_1` FOREIGN KEY (`FK_ID_REQUEST_RC`) REFERENCES `request_rc` (`id_request`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_pdf`
--

LOCK TABLES `file_pdf` WRITE;
/*!40000 ALTER TABLE `file_pdf` DISABLE KEYS */;
INSERT INTO `file_pdf` (`ID_PDF`, `LINK_PDF`, `FK_ID_REQUEST_RC`) VALUES (3,'FILEPDFCARR2',2),(4,'FILEPDFDOC2',2),(5,'/WebContent/DocumentsRequestRC/g.rossi31@studenti.unisa.it/ID.pdf',4),(6,'/WebContent/DocumentsRequestRC/g.rossi31@studenti.unisa.it/CP.pdf',4),(7,'WebContent/DocumentsRequestRC/g.rossi31@studenti.unisa.it/fakeCP.pdf',5),(8,'invalidPath',7),(25,'/WebContent/DocumentsRequestRC/dekio.99@gmail.it/ID.pdf',16),(26,'/WebContent/DocumentsRequestRC/dekio.99@gmail.it/CP.pdf',16),(35,'/WebContent/DocumentsRequestRC/aaa@aaa.com/ID.pdf',21),(36,'/WebContent/DocumentsRequestRC/aaa@aaa.com/CP.pdf',21),(43,'/WebContent/DocumentsRequestRC/g.c@studenti.unisa.it/ID.pdf',25),(44,'/WebContent/DocumentsRequestRC/g.c@studenti.unisa.it/CP.pdf',25);
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
  `NOTE` text,
  PRIMARY KEY (`ID_REPORT`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` (`ID_REPORT`, `NOTE`) VALUES (1,'La sua carriera ok okok'),(2,'La sua carriera NON è ok okok'),(3,'La sua carriera NON è ok ok'),(4,'test'),(5,'gwteg53'),(7,NULL),(8,NULL),(9,NULL),(10,NULL),(11,NULL),(12,NULL),(13,NULL),(14,NULL),(15,NULL),(16,NULL),(17,NULL),(18,NULL),(19,NULL),(20,NULL),(21,NULL),(22,NULL);
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
INSERT INTO `request` (`ID_REQUEST`, `CERTIFICATE_SERIAL`, `LEVEL`, `RELEASE_DATE`, `EXPIRY_DATE`, `YEAR`, `REQUESTED_CFU`, `SERIAL`, `VALIDATED_CFU`, `FK_USER`, `FK_CERTIFIER`, `FK_STATE`) VALUES (1,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova@unisa.it',2,3),(2,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova2@unisa.it',2,4),(3,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova3@unisa.it',3,3),(4,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova4@unisa.it',4,7),(5,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova5@unisa.it',3,4),(6,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova6@unisa.it',3,3),(7,'A00000001','A1','2015-02-14','2020-02-14',2018,6,512103578,0,'a.prova@studenti.unisa.it',1,1),(8,'A00000001','A1','2015-02-14','2020-02-14',2018,6,512103579,0,'l.l@studenti.unisa.it',1,2);
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
  `FK_UNIVERSITY` varchar(100) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_rc`
--

LOCK TABLES `request_rc` WRITE;
/*!40000 ALTER TABLE `request_rc` DISABLE KEYS */;
INSERT INTO `request_rc` (`ID_REQUEST`, `DATE_REQUEST`, `STATE`, `FK_UNIVERSITY`, `FK_USER`, `FK_REPORT`, `FK_EMAIL_UC`) VALUES (2,'2020-01-30',2,'Università degli Studi di SALERNO','provaRC2@unisa.it',2,'EMAILUC@gmail.it'),(3,'2020-04-10',3,'Università degli Studi di SALERNO','prova2@unisa.it',1,'EMAILUC@gmail.it'),(4,'2020-01-20',3,'Università degli Studi di NAPOLI Federico II','g.rossi31@studenti.unisa.it',NULL,'EMAILUC@gmail.it'),(5,'2020-01-11',0,'Università degli Studi di SALERNO','g.rossiNOESAMI@studenti.unisa.it',NULL,'EMAILUC@gmail.it'),(6,'2020-01-11',1,'Università degli Studi di SALERNO','g.rossiNOESAMINOPDF@studenti.unisa.it',NULL,'EMAILUC@gmail.it'),(7,'2020-01-11',1,'Università degli Studi di SALERNO','g.rossiNOESAMIINVALIDPATH@studenti.unisa.it',NULL,'EMAILUC@gmail.it'),(16,'2020-01-15',2,'Università degli Studi di GENOVA','dekio.99@gmail.it',4,'EMAILUC@gmail.it'),(21,'2020-01-15',0,'Università degli Studi di NAPOLI Federico II','aaa@aaa.com',13,'EMAILUC@gmail.it'),(25,'2020-01-15',2,'Università degli Studi di SALERNO','g.c@studenti.unisa.it',5,'EMAILUC@gmail.it');
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
INSERT INTO `state` (`ID_STATE`, `DESCRIPTION`) VALUES (1,'Parzialmente Completata'),(2,'In elaborazione dalla Segreteria'),(3,'In elaborazione dall&quot; Amministratore'),(4,'Accettata e In elaborazione dal Consiglio Didattico'),(5,'Rifiutata e In elaborazione dal Consiglio Didattico'),(6,'Conclusa e Accettata'),(7,'Conclusa e Rifiutata');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `suggestion` (
  `NAME_UNIVERSITY` varchar(50) NOT NULL,
  `NAME_EXAM_EXTERN` varchar(50) NOT NULL,
  `NUMBER_CFU_EXTERN` tinyint(1) NOT NULL,
  `VALIDATED_CFU` tinyint(1) NOT NULL,
  `MODE_VALIDATION` text NOT NULL,
  `DATE_VALIDATION` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
INSERT INTO `suggestion` (`NAME_UNIVERSITY`, `NAME_EXAM_EXTERN`, `NUMBER_CFU_EXTERN`, `VALIDATED_CFU`, `MODE_VALIDATION`, `DATE_VALIDATION`) VALUES ('Università degli Studi di NAPOLI Federico II','Analisi Matematica I',9,9,'Validato come ANALISI MATEMATICA','2020-01-02'),('Università degli Studi di NAPOLI Federico II','Algoritmi e strutture dati I',9,9,'Validato come PROGRAMMAZIONE & STRUTTURE DATI','2020-01-03'),('Università degli Studi di NAPOLI Federico II','Linguaggi di Programmazione I',6,6,'Validato come PROGRAMMAZIONE & STRUTTURE DATI','2020-01-04'),('Università degli Studi di NAPOLI Federico II','Economia ed organizzazione aziendale',6,0,'Il corso di laurea di informatica non prevede un esame affine a quello che lo studente intende riconoscere','2020-01-06'),('Università degli Studi di NAPOLI Federico II','Fisica Generale I',6,6,'Validato come FISICA','2020-01-16'),('Università degli Studi di GENOVA','programmazione 1',9,9,'g4g43g43','2020-01-15'),('Università degli Studi di SALERNO','programmazione 1',9,9,'prog1','2020-01-15'),('Università degli Studi di NAPOLI Federico II','Analisi xxxI',9,9,'Complimenti\n','2020-01-15'),('Università degli Studi di NAPOLI Federico II','Analisi I',9,9,'Complimenti\n','2020-01-15');
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
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
INSERT INTO `system_attribute` (`SLUG`, `VALUE`, `FK_USER`) VALUES ('request-accepted','6','fferrucci@unisa.it'),('request-allowed-extension-upload','.pdf','fferrucci@unisa.it'),('request-matriculation-year-range','5','fferrucci@unisa.it'),('request-max-cfu','12','fferrucci@unisa.it'),('request-min-cfu','1','fferrucci@unisa.it'),('request-number-max-upload','2','fferrucci@unisa.it'),('request-partially-completed','1','fferrucci@unisa.it'),('request-refused','7','fferrucci@unisa.it'),('request-upload-path','//home//vale//newWorkspace//EV_EnglishValidation//uploads//','fferrucci@unisa.it'),('request-working-admin','3','fferrucci@unisa.it'),('request-working-educational-advice-1','4','fferrucci@unisa.it'),('request-working-educational-advice-2','5','fferrucci@unisa.it'),('request-working-secretary','2','fferrucci@unisa.it');
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
INSERT INTO `uc` (`EMAIL`, `PASSWORD`, `TELEPHONE`, `FAX`) VALUES ('EMAILUC@gmail.it','12356789','3331231233','3331231233'),('uc@unisa.it','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3385099635','4585698569');
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
INSERT INTO `university` (`NAME`) VALUES ('Libera Università degli Studi Maria SS.Assunta - LUMSA'),('Libera Università di BOLZANO'),('Università Ca Foscari VENEZIA'),('Università degli Studi de L\'AQUILA'),('Università degli Studi del MOLISE'),('Università degli Studi del PIEMONTE ORIENTALE Amedeo Avogadro-Vercelli'),('Università degli Studi della BASILICATA'),('Università degli Studi di BARI ALDO MORO'),('Università degli Studi di BOLOGNA'),('Università degli Studi di CAGLIARI'),('Università degli Studi di CAMERINO'),('Università degli Studi di CATANIA'),('Università degli Studi di FERRARA'),('Università degli Studi di FIRENZE'),('Università degli Studi di GENOVA'),('Università degli Studi di MESSINA'),('Università degli Studi di MILANO'),('Università degli Studi di MILANO-BICOCCA'),('Università degli Studi di MODENA e REGGIO EMILIA'),('Università degli Studi di NAPOLI Federico II'),('Università degli Studi di NAPOLI Parthenope'),('Università degli Studi di PADOVA'),('Università degli Studi di PALERMO'),('Università degli Studi di PARMA'),('Università degli Studi di ROMA La Sapienza'),('Università degli Studi di ROMA Tor Vergata'),('Università degli Studi di SALERNO'),('Università degli Studi di TORINO'),('Università degli Studi di TRENTO'),('Università degli Studi di UDINE'),('Università degli Studi di URBINO Carlo Bo'),('Università degli Studi di VERONA'),('Università degli Studi insurbia VARESE-COMO'),('Università della CALABRIA'),('Università di PISA');
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
INSERT INTO `user` (`EMAIL`, `NAME`, `SURNAME`, `SEX`, `PASSWORD`, `USER_TYPE`, `DATE_REGISTRATION`) VALUES ('0jtpi870ga@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('3ocllzphmp@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('6bleobvygv@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('9rj7tc3xmc@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('aaa@aaa.com','ffff','fffff','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('ahvydfpxdv@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('crnwexokad@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('d2cs29wgt9@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('dekio.95@hotmail.it','vin','dek','M','123456789',0,NULL),('dekio.99@gmail.it','Vincenzo','de Chiara','M','9611edf7f716b00c8a44441973906aa7f5c0c580',0,'2020-01-15'),('dshqyx7uwe@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('eh1zjevhnz@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('fferrucci@unisa.it','Luigia','Melchionno','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',2,NULL),('g.c@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('g.rossi31@studenti.unisa.it','Gianluca','Rossi','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,NULL),('g.rossi@studenti.unisa.it','Gianluca','Rossi','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,NULL),('g.rossiNOESAMI@studenti.unisa.it','Gianluca','Rossi','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,NULL),('g.rossiNOESAMIINVALIDPATH@studenti.unisa.it','Gianluca','Rossi','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,NULL),('g.rossiNOESAMINOPDF@studenti.unisa.it','Gianluca','Rossi','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,NULL),('g2dipkaty6@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('g87yjnxipq@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('ghhvt74lbg@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('gourg2zaee@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('gwc7jnb8xe@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('ijmmzq8e15@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('kf9smv5nsr@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('lehncobbu8@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('lollo1@gmail.com','Lorenzo','Maturo','M','9611edf7f716b00c8a44441973906aa7f5c0c580',1,NULL),('lollo@gmail.com','PROVA1','RC1','M','123456789',0,'2001-01-10'),('lorenzo@gmail.com','PROVA1','RC1','M','123456789',1,'2001-01-10'),('ls7l5bvpb3@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('matdzei8w5@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('mkbamhafxs@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('mmg1itiowh@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('mna6a2dx4a@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('nhtsffwarh@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('nowpucs2iv@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('od5pw2qbnc@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('owxcshffpg@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('pati5cfpp9@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('prova2@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9e',0,NULL),('prova3@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9c',0,NULL),('prova4@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9f',0,NULL),('prova5@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9g',0,NULL),('prova6@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9h',0,NULL),('prova@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9d',0,NULL),('provaRC2@unisa.it','PROVA2','RC2','M','12345678',0,'2020-10-20'),('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',1,NULL),('syman2cm1l@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('ucjkj5kfp7@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('vldyryjrui@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('vzziqnh8ui@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15'),('yzgef6gawd@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0,'2020-01-15');
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
  `MODE_VALIDATION` text,
  `FK_ID_REPORT` int(20) NOT NULL,
  PRIMARY KEY (`ID_EXAM_VALIDATE`),
  KEY `FK_ID_REPORT` (`FK_ID_REPORT`),
  CONSTRAINT `validate_exam_ibfk_1` FOREIGN KEY (`FK_ID_REPORT`) REFERENCES `report` (`id_report`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validate_exam`
--

LOCK TABLES `validate_exam` WRITE;
/*!40000 ALTER TABLE `validate_exam` DISABLE KEYS */;
INSERT INTO `validate_exam` (`ID_EXAM_VALIDATE`, `NAME_EXAM`, `CFU_CONVALIDATED`, `MODE_VALIDATION`, `FK_ID_REPORT`) VALUES (1,'PROGRAMMAZIONE 1',9,'è stato validato bene',1),(2,'ANALISI 1',0,'non sono stati validati cfu',2),(3,'ANALISI 1',0,'non sono stati validati cfu',3),(4,'programmazione 1',9,'g4g43g43',4),(5,'programmazione 1',9,'prog1',5),(7,'programmazione 1',-1,NULL,13);
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

-- Dump completed on 2020-01-15 19:20:16
