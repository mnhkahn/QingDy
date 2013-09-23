-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
--
-- Host: localhost    Database: QingDy
-- ------------------------------------------------------
-- Server version	5.6.12

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `ANSWER` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `QUESTION` bigint(20) DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  `STAR` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK735D33BE3A899A61` (`ID`),
  KEY `FK735D33BEF50BC92C` (`QUESTION`),
  KEY `FK735D33BEA56422A9` (`POSTER`),
  CONSTRAINT `FK735D33BE3A899A61` FOREIGN KEY (`ID`) REFERENCES `question` (`ID`),
  CONSTRAINT `FK735D33BEA56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`),
  CONSTRAINT `FK735D33BEF50BC92C` FOREIGN KEY (`QUESTION`) REFERENCES `question` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (3,'lender','title1','2013-08-24 11:15:29',3,-1,NULL),(4,'lender','好难啊，我也不知道，唉','2013-08-26 22:00:26',10,-1,NULL);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `ID` bigint(20) NOT NULL,
  `TITLE` varchar(50) NOT NULL,
  `CONTENT` longtext NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `POSTDATE` datetime NOT NULL,
  `CATEGORY` varchar(50) NOT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1F27A2D01CAE32` (`USERNAME`),
  CONSTRAINT `FK1F27A2D01CAE32` FOREIGN KEY (`USERNAME`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (2,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷<img src=\'http://localhost:8080/avatar/1.jpg\' />','lender','2013-08-24 14:23:36','test type',1),(3,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','lender','2013-08-24 15:13:45','test type',1),(4,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','lender','2013-08-24 15:13:46','test type',1),(5,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','lender','2013-08-24 15:13:47','test type',1),(6,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','lender','2013-08-24 15:13:48','test type',1),(7,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','lender','2013-08-24 15:13:48','test type',1),(8,'test1','***********','lender','2013-08-24 15:13:56','test type',0),(10,'undefined','','lender','2013-08-26 20:27:20','小额贷款',-1),(11,'undefined','&nbsp;的撒','lender','2013-09-15 11:37:45','小额贷款',-1);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluate`
--

DROP TABLE IF EXISTS `evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluate` (
  `ID` bigint(20) NOT NULL,
  `STAR` int(11) NOT NULL,
  `POSTDATE` datetime NOT NULL,
  `BLOGID` bigint(20) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `FK384A9C99D01CAE32` (`USERNAME`),
  KEY `FK384A9C9985B60D3F` (`BLOGID`),
  CONSTRAINT `FK384A9C9985B60D3F` FOREIGN KEY (`BLOGID`) REFERENCES `blog` (`ID`),
  CONSTRAINT `FK384A9C99D01CAE32` FOREIGN KEY (`USERNAME`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluate`
--

LOCK TABLES `evaluate` WRITE;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `LINK` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `OID` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3BDDB089A56422A9` (`POSTER`),
  CONSTRAINT `FK3BDDB089A56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
INSERT INTO `favourite` VALUES (2,'lender','title','localhost','2013-09-11 20:20:55',1,5),(3,'loaner','title','localhost','2013-09-11 20:58:12',1,5);
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `AMOUNT` float DEFAULT NULL,
  `STARTTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `USESOFLOAN` varchar(255) DEFAULT NULL,
  `LENDTYPE` varchar(255) DEFAULT NULL,
  `HASPAWN` int(11) DEFAULT NULL,
  `PAWN` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK23BCF0A56422A9` (`POSTER`),
  CONSTRAINT `FK23BCF0A56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (1,'lender',5,'2013-07-01 08:00:00','2013-08-23 08:00:00','use','type',1,'pawn','title','content','2013-08-24 11:55:55','location',1),(2,'lender',1,'2013-08-26 08:00:00','2013-10-25 08:00:00','null',NULL,1,'null','','','2013-08-26 10:02:45','北京海淀',-1),(3,'lender',1,'2013-08-26 08:00:00','2013-10-25 08:00:00','个体户经营贷款,创业贷款',NULL,1,'办公楼,土地','求贷款','穷死了啊','2013-08-26 10:03:10','北京海淀',-1),(4,'lender',3,'2013-09-15 08:00:00','2013-11-14 08:00:00','短期周转贷款,一手房按揭贷款',NULL,1,'土地,船坞','我快穷疯了','本人学生，最近缺钱花，穷疯了','2013-09-15 10:31:02','北京海淀',-1);
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall`
--

DROP TABLE IF EXISTS `mall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `ANNOUNCEMENT` varchar(255) DEFAULT NULL,
  `EXPERIENCE` varchar(255) DEFAULT NULL,
  `CTYPE` varchar(255) DEFAULT NULL,
  `CNAME` varchar(255) DEFAULT NULL,
  `CPHONENUMBER` varchar(255) DEFAULT NULL,
  `POSITION` varchar(255) DEFAULT NULL,
  `SITE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `FAX` varchar(255) DEFAULT NULL,
  `POSTCODE` varchar(255) DEFAULT NULL,
  `CLIENTLOCATION` varchar(255) DEFAULT NULL,
  `USESOFLOAN` varchar(255) DEFAULT NULL,
  `SPECIALITY` varchar(255) DEFAULT NULL,
  `CLIENTS` varchar(255) DEFAULT NULL,
  `LENDTYPE` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK23FE14D01CAE32` (`USERNAME`),
  CONSTRAINT `FK23FE14D01CAE32` FOREIGN KEY (`USERNAME`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall`
--

LOCK TABLES `mall` WRITE;
/*!40000 ALTER TABLE `mall` DISABLE KEYS */;
INSERT INTO `mall` VALUES (1,'lender','content','announcement','experence','ctyp','cname','12345678901','ceo','www.taobao.com','lichao0407@gmail.com','china','010-12345678','100081','null','短期周转贷款','机械设备','个体工商户','贵重物品','2013-08-24 16:11:42',1);
/*!40000 ALTER TABLE `mall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL,
  `SENDER` varchar(255) DEFAULT NULL,
  `RECEIVER` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `ISREADED` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK63B68BE7A9F33F91` (`SENDER`),
  KEY `FK63B68BE7AFBF0F4B` (`RECEIVER`),
  CONSTRAINT `FK63B68BE7A9F33F91` FOREIGN KEY (`SENDER`) REFERENCES `userdetail` (`USERNAME`),
  CONSTRAINT `FK63B68BE7AFBF0F4B` FOREIGN KEY (`RECEIVER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'bryce','lender','你好','欢迎来到青帝网','2013-09-03 10:39:58',1),(3,'bryce','lender','你好','欢迎来到青帝网','2013-09-03 11:07:37',0),(4,'lender',NULL,'你好','欢迎来到青帝网','2013-09-22 14:46:34',0),(5,'lender','loaner','你好','欢迎来到青帝网','2013-09-22 14:47:21',0),(6,'lender','loaner','测试message','能不能发呢？','2013-09-22 14:48:32',0),(7,'lender','loaner','服装测试','测试','2013-09-22 14:56:42',0),(8,'lender','bryce','[object Object]','[object Object]','2013-09-22 14:58:09',0),(9,'lender','bryce','hello','fuck you                ','2013-09-22 15:57:15',0),(10,'lender','bryce','发撒的','                发达','2013-09-22 15:59:07',0),(11,'lender','bryce','fdsa ','                fdsa ','2013-09-22 16:00:23',0),(12,'lender','bryce','fdsa','                fdsa','2013-09-22 16:02:11',0),(13,'lender','bryce','fdas','                fdsa','2013-09-22 16:02:50',0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `ID` bigint(20) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `CLASSES` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK2482D3A56422A9` (`POSTER`),
  CONSTRAINT `FK2482D3A56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','小额贷款','2013-08-24 11:54:43'),(2,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','创业贷款','2013-08-24 11:54:43'),(3,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','无抵押贷款','2013-08-24 11:54:43'),(4,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','消费贷款','2013-08-24 11:54:43'),(5,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','求学贷款','2013-08-24 11:54:43'),(6,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','房产贷款','2013-08-24 11:54:43'),(7,'生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','bryce','银行贷款','2013-08-24 11:54:43');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `MALL` bigint(20) DEFAULT NULL,
  `PNAME` varchar(255) DEFAULT NULL,
  `MAX` int(11) DEFAULT NULL,
  `MIN` int(11) DEFAULT NULL,
  `RATETYPE` varchar(255) DEFAULT NULL,
  `RATE` float DEFAULT NULL,
  `STARTTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `CLIENTLOCATION` varchar(255) DEFAULT NULL,
  `CLIENT` varchar(255) DEFAULT NULL,
  `REPAYMENTMETHOD` varchar(255) DEFAULT NULL,
  `PTYPE` varchar(255) DEFAULT NULL,
  `USESOFLOAN` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `PROCESSES` varchar(255) DEFAULT NULL,
  `APPLICATION` varchar(255) DEFAULT NULL,
  `FAQ` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK185958CFA56422A9` (`POSTER`),
  KEY `FK185958CFD88D0D88` (`MALL`),
  CONSTRAINT `FK185958CFA56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`),
  CONSTRAINT `FK185958CFD88D0D88` FOREIGN KEY (`MALL`) REFERENCES `mall` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'lender',1,'天梭TISSOT Classic',1,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-24 10:37:03',1),(4,'lender',1,'天梭TISSOT Classic',2,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-24 10:37:03',1),(5,'lender',1,'天梭TISSOT Classic',3,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-24 10:37:03',1),(6,'lender',1,'天梭TISSOT Classic',4,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-24 10:37:03',1),(7,'lender',1,'天梭TISSOT Classic',5,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-23 08:00:00',1),(8,'lender',1,'天梭TISSOT Classic',6,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-23 08:00:00',1),(9,'lender',1,'天梭TISSOT Classic',7,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-23 08:00:00',1),(10,'lender',1,'天梭TISSOT Classic',8,1,'rateType',0,'2012-08-22 08:00:00','2013-08-23 08:00:00','location','client','method','pType','usesofloan','content','processes','application','faq','2013-08-23 08:00:00',1),(11,NULL,NULL,'11',1,1,'reate',1,NULL,NULL,'福建福州',NULL,NULL,'null','null','','','','',NULL,-1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `CLASSES` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  `BEST` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKE9282BE6A56422A9` (`POSTER`),
  CONSTRAINT `FKE9282BE6A56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'loaner','生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','classes','2013-08-24 10:35:07',1,-1),(2,'loaner','生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','classes','2013-08-24 10:37:03',1,-1),(3,'loaner','生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','classes','2013-08-24 11:14:11',1,3),(4,'loaner','生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','classes','2013-08-24 11:14:11',1,3),(5,'loaner','生源地助学贷款常见问题解答1','据悉，从7月22日起，如皋市学生资助中心开始接受2013年国开行生源地信用助学贷','classes','2013-08-24 11:14:11',1,3),(6,NULL,NULL,NULL,NULL,'2013-08-26 21:33:58',-1,-1),(7,NULL,NULL,NULL,NULL,'2013-08-26 21:34:44',-1,-1),(8,NULL,NULL,NULL,NULL,'2013-08-26 21:35:07',-1,-1),(9,NULL,NULL,NULL,NULL,'2013-08-26 21:35:37',-1,-1),(10,'lender','1+1等于几','你们知道么？','青帝网帮助其它问题','2013-08-26 21:38:19',1,-1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `ID` bigint(20) NOT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4B14672A56422A9` (`POSTER`),
  CONSTRAINT `FK4B14672A56422A9` FOREIGN KEY (`POSTER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,'lender',1),(2,'lender',2),(3,'lender',-2);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `ID` bigint(20) NOT NULL,
  `LOANER` varchar(255) DEFAULT NULL,
  `LENDER` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `POSTDATE` datetime DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `FRONTCOVER` varchar(255) DEFAULT NULL,
  `VERIFY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKFFF466BE9E015338` (`LENDER`),
  KEY `FKFFF466BE9E887AF9` (`LOANER`),
  CONSTRAINT `FKFFF466BE9E015338` FOREIGN KEY (`LENDER`) REFERENCES `userdetail` (`USERNAME`),
  CONSTRAINT `FKFFF466BE9E887AF9` FOREIGN KEY (`LOANER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (2,'loaner','lender','title11','2013-08-24 13:40:32','comments','frontcover',0),(3,'loaner','lender','title','2013-08-24 13:41:41','comments','frontcover',1),(4,'loaner','lender','title','2013-08-24 13:43:39','comments','frontcover',-1),(5,NULL,'lender','','2013-08-26 20:20:14','',NULL,-1),(6,'loaner','lender','乐于助人','2013-08-26 20:20:54','我就是喜欢帮助别人&nbsp;',NULL,-1);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `GROUPID` int(11) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('bryce','bryce',0),('lender','lender',2),('loaner','loaner',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetail`
--

DROP TABLE IF EXISTS `userdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetail` (
  `USERNAME` varchar(255) NOT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `GENDER` int(11) DEFAULT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `TEL` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `SITE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `INTRODUCE` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `REGDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetail`
--

LOCK TABLES `userdetail` WRITE;
/*!40000 ALTER TABLE `userdetail` DISABLE KEYS */;
INSERT INTO `userdetail` VALUES ('bryce','李','超',1,'/avatar/1.jpg','15652997385','360924857','site','lichao0407@gmail.com','introduce','北京海淀','2013-08-24 13:38:55'),('lender','阿','蒙',0,'/avatar/1.jpg','13452172009','290355479','www.cyeam.com','lichao0407@gmail.com','我就是我','北京海淀','2013-08-24 10:36:58'),('loaner','车','明光',1,'/avatar/avatar.png','13452172009','360924857','site','lichao0407@gmail.com','introduce','北京海淀','2013-08-24 13:38:55');
/*!40000 ALTER TABLE `userdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `ID` bigint(20) NOT NULL,
  `OID` bigint(20) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `USER` varchar(255) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4DE552BE02A1127` (`USER`),
  CONSTRAINT `FK4DE552BE02A1127` FOREIGN KEY (`USER`) REFERENCES `userdetail` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (2,1,5,'loaner','2013-09-04 19:53:25',NULL),(3,1,1,'lender','2013-09-19 10:42:20',NULL),(4,1,5,'lender','2013-09-22 18:25:37','0:0:0:0:0:0:0:1'),(5,1,5,'lender','2013-09-22 18:30:33','0:0:0:0:0:0:0:1'),(6,1,5,'lender','2013-09-22 18:31:03','0:0:0:0:0:0:0:1'),(7,1,5,'lender','2013-09-22 18:32:09','0:0:0:0:0:0:0:1'),(8,1,5,'lender','2013-09-22 18:32:54','0:0:0:0:0:0:0:1'),(9,1,5,'lender','2013-09-22 18:35:27','0:0:0:0:0:0:0:1'),(10,1,5,'lender','2013-09-22 18:37:14','0:0:0:0:0:0:0:1'),(11,1,5,'lender','2013-09-22 18:40:29','0:0:0:0:0:0:0:1'),(12,1,5,'lender','2013-09-22 18:41:24','0:0:0:0:0:0:0:1'),(13,1,5,'lender','2013-09-22 18:42:15','0:0:0:0:0:0:0:1'),(14,1,5,'lender','2013-09-22 18:48:13','0:0:0:0:0:0:0:1'),(15,9,5,'lender','2013-09-22 19:33:22','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-23 12:18:25
