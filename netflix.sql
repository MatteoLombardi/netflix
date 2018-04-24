CREATE DATABASE  IF NOT EXISTS `netflix` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `netflix`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: netflix
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `abbonamento`
--

DROP TABLE IF EXISTS `abbonamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abbonamento` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIZIONE` varchar(50) DEFAULT NULL,
  `DURATA` int(11) DEFAULT NULL,
  `COSTO` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abbonamento`
--

LOCK TABLES `abbonamento` WRITE;
/*!40000 ALTER TABLE `abbonamento` DISABLE KEYS */;
INSERT INTO `abbonamento` VALUES (1,'Bello',12,36.90),(2,'Simpatico',1,19.99);
/*!40000 ALTER TABLE `abbonamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attore`
--

DROP TABLE IF EXISTS `attore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attore` (
  `ID` bigint(20) NOT NULL,
  `ID_NAZIONE` bigint(20) NOT NULL,
  KEY `ID_INDEX` (`ID`),
  KEY `ID_NAZIONE_INDEX` (`ID_NAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attore`
--

LOCK TABLES `attore` WRITE;
/*!40000 ALTER TABLE `attore` DISABLE KEYS */;
INSERT INTO `attore` VALUES (4,3),(5,3),(8,1),(9,1),(12,1);
/*!40000 ALTER TABLE `attore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entita_x_premio`
--

DROP TABLE IF EXISTS `entita_x_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entita_x_premio` (
  `ID_ENTITA` bigint(20) DEFAULT NULL,
  `ID_PREMIO` bigint(20) DEFAULT NULL,
  `ANNO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entita_x_premio`
--

LOCK TABLES `entita_x_premio` WRITE;
/*!40000 ALTER TABLE `entita_x_premio` DISABLE KEYS */;
INSERT INTO `entita_x_premio` VALUES (4,1,2010),(4,2,2014),(5,1,2012),(12,1,2019);
/*!40000 ALTER TABLE `entita_x_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITOLO` varchar(50) DEFAULT NULL,
  `DESCRIZIONE` varchar(250) DEFAULT NULL,
  `ANNO` int(11) DEFAULT NULL,
  `DURATA` int(11) DEFAULT NULL,
  `ID_NAZIONE` bigint(20) DEFAULT NULL,
  `ID_LINGUA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Pacific Rim','Film con robot e mostri alieni',2013,131,3,5);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_x_genere`
--

DROP TABLE IF EXISTS `film_x_genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film_x_genere` (
  `ID_FILM` bigint(20) DEFAULT NULL,
  `ID_GENERE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_x_genere`
--

LOCK TABLES `film_x_genere` WRITE;
/*!40000 ALTER TABLE `film_x_genere` DISABLE KEYS */;
INSERT INTO `film_x_genere` VALUES (1,1);
/*!40000 ALTER TABLE `film_x_genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genere`
--

DROP TABLE IF EXISTS `genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genere` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITOLO` varchar(50) NOT NULL,
  `DESCRIZIONE` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genere`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
INSERT INTO `genere` VALUES (1,'Fantascienza','Film con gli alieni');
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lingua`
--

DROP TABLE IF EXISTS `lingua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lingua` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIZIONE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lingua`
--

LOCK TABLES `lingua` WRITE;
/*!40000 ALTER TABLE `lingua` DISABLE KEYS */;
INSERT INTO `lingua` VALUES (1,'ITALIANO'),(2,'FRANCESE'),(3,'TEDESCO'),(4,'SPAGNOLO'),(5,'INGLESE');
/*!40000 ALTER TABLE `lingua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nazione`
--

DROP TABLE IF EXISTS `nazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nazione` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIZIONE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nazione`
--

LOCK TABLES `nazione` WRITE;
/*!40000 ALTER TABLE `nazione` DISABLE KEYS */;
INSERT INTO `nazione` VALUES (1,'ITALIA'),(2,'FRANCIA'),(3,'STATI UNITI');
/*!40000 ALTER TABLE `nazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) NOT NULL,
  `COGNOME` varchar(100) NOT NULL,
  `DATA_NASCITA` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Mario','Rossi','1990-03-25'),(2,'Giuseppe','Verdi','1985-08-23'),(3,'Steven','Spielberg','1960-05-23'),(4,'Keanu','Reeves','1970-02-12'),(5,'George','Clooney','1965-10-25'),(8,'Alvaro','Vitali','1950-02-12'),(9,'Michele','Placido','1955-05-26'),(12,'Claudio','Bisio','1956-03-22');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_x_abbonamento`
--

DROP TABLE IF EXISTS `persona_x_abbonamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_x_abbonamento` (
  `ID_PERSONA` bigint(20) DEFAULT NULL,
  `ID_ABBONAMENTO` bigint(20) DEFAULT NULL,
  `METODO_PAGAMENTO` varchar(50) DEFAULT NULL,
  `DATA_SOTTOSCRIZIONE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_x_abbonamento`
--

LOCK TABLES `persona_x_abbonamento` WRITE;
/*!40000 ALTER TABLE `persona_x_abbonamento` DISABLE KEYS */;
INSERT INTO `persona_x_abbonamento` VALUES (1,1,'C/C','2018-04-19');
/*!40000 ALTER TABLE `persona_x_abbonamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_x_film`
--

DROP TABLE IF EXISTS `persona_x_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_x_film` (
  `ID_PERSONA` bigint(20) DEFAULT NULL,
  `ID_FILM` bigint(20) DEFAULT NULL,
  `DATA_NOLEGGIO` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_x_film`
--

LOCK TABLES `persona_x_film` WRITE;
/*!40000 ALTER TABLE `persona_x_film` DISABLE KEYS */;
INSERT INTO `persona_x_film` VALUES (1,1,'2018-02-21');
/*!40000 ALTER TABLE `persona_x_film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premio`
--

DROP TABLE IF EXISTS `premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premio` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(50) DEFAULT NULL,
  `TIPO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premio`
--

LOCK TABLES `premio` WRITE;
/*!40000 ALTER TABLE `premio` DISABLE KEYS */;
INSERT INTO `premio` VALUES (1,'Oscar','Miglior Attore'),(2,'Palma D\'Oro','Miglior Attore'),(3,'Oscar','Miglior Regia'),(4,'Oscar','Miglior Fotografia'),(5,'Oscar','Migliore Colonna Sonora'),(6,'Palma D\'Oro','Miglior Regia'),(7,'Palma D\'Oro','Miglior Fotografia'),(8,'Palma D\'Oro','Migliore Colonna Sonora'),(9,'Oscar','Miglior Attrice'),(10,'Palma D\'Oro','Miglior Attrice');
/*!40000 ALTER TABLE `premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regista`
--

DROP TABLE IF EXISTS `regista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regista` (
  `ID` bigint(20) NOT NULL,
  `ID_NAZIONE` bigint(20) NOT NULL,
  KEY `ID_INDEX` (`ID`),
  KEY `ID_NAZIONE_INDEX` (`ID_NAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regista`
--

LOCK TABLES `regista` WRITE;
/*!40000 ALTER TABLE `regista` DISABLE KEYS */;
INSERT INTO `regista` VALUES (3,3);
/*!40000 ALTER TABLE `regista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `ID` bigint(20) NOT NULL,
  `CODICE_FISCALE` char(16) DEFAULT NULL,
  `MAIL` varchar(250) DEFAULT NULL,
  KEY `ID_INDEX` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'RSSMRI90G23G273F','mario.rossi@mail.it'),(2,'VRDGPP56F56R456W','g.verdi@gmail.com');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 11:42:07
