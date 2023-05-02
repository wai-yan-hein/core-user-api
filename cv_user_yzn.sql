-- MariaDB dump 10.19  Distrib 10.6.12-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: cv_user_yzn
-- ------------------------------------------------------
-- Server version	10.6.12-MariaDB

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
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuser` (
  `user_code` varchar(15) NOT NULL,
  `role_code` varchar(15) NOT NULL,
  `user_short_name` varchar(25) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0',
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_code`,`role_code`),
  UNIQUE KEY `user_short_name_UNIQUE` (`user_short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES ('000','1','ground','ground','','','ground',''),('002','012022-001','wwm','wwm','','','88ww',''),('003','012022-001','thp','thp','','','55tt',''),('004','012022-001','phn','phn','','','22ml',''),('005','012022-001','nll','nll','','','44so',''),('006','1','tdw','tdw','','','66td',''),('007','012022-001','ttw','ttw','','','00tw',''),('008','1','wsale','wsale','','','wsale',''),('009','1','yzn2','yzn2','','','yzn2',''),('011','1','tran','tran','','','tran',''),('012','012022-001','stdn','stdn','','','77nn',''),('013','1','hk','HK Factory','','','hk',''),('1','1','admin','admin','',NULL,'admin',NULL),('2.0','1','user','user pc','','plm@gmail.com','userpc','123456');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_type`
--

DROP TABLE IF EXISTS `business_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_type` (
  `bus_id` int(11) NOT NULL,
  `bus_name` varchar(255) NOT NULL,
  PRIMARY KEY (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_type`
--

LOCK TABLES `business_type` WRITE;
/*!40000 ALTER TABLE `business_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `business_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_info` (
  `comp_code` varchar(15) NOT NULL,
  `user_code` varchar(15) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `short_code` varchar(5) DEFAULT NULL,
  `security_code` varchar(255) DEFAULT NULL,
  `parent` varchar(15) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `currency` varchar(15) NOT NULL,
  `created_by` varchar(15) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `bus_id` int(11) DEFAULT NULL,
  `batch_lock` bit(1) NOT NULL DEFAULT b'0',
  `year_end_date` date DEFAULT NULL,
  PRIMARY KEY (`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_info`
--

LOCK TABLES `company_info` WRITE;
/*!40000 ALTER TABLE `company_info` DISABLE KEYS */;
INSERT INTO `company_info` VALUES ('0010010','01','Yuzana Lephet & Assorted Fries','01-242526,255525,255635, 09787363980, 09977987455, 09979893142','',NULL,NULL,NULL,'No.22, NAWADAY STREET DAGON TOWNSHIP,YANGON MYANMAR','','2022-06-01','2029-06-01','MMK',NULL,NULL,NULL,'\0',NULL),('1',NULL,'Account','','',NULL,NULL,NULL,'','','2020-04-01','2023-05-31','MMK',NULL,NULL,NULL,'\0',NULL);
/*!40000 ALTER TABLE `company_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `cur_code` varchar(15) NOT NULL,
  `cur_name` varchar(255) DEFAULT NULL,
  `cur_symbol` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `cur_gain_acc` varchar(15) DEFAULT NULL,
  `cur_lost_acc` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`cur_code`),
  UNIQUE KEY `cur_code` (`cur_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES ('MMK','MMK','MMK','',NULL,NULL);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL,
  `user_code` varchar(15) NOT NULL,
  `dept_name` varchar(255) NOT NULL,
  `inv_queue` varchar(255) DEFAULT NULL,
  `acc_queue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'H','Head Office',NULL,NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mac_prop`
--

DROP TABLE IF EXISTS `mac_prop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mac_prop` (
  `mac_id` int(11) NOT NULL,
  `prop_key` varchar(255) NOT NULL,
  `prop_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mac_id`,`prop_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mac_prop`
--

LOCK TABLES `mac_prop` WRITE;
/*!40000 ALTER TABLE `mac_prop` DISABLE KEYS */;
/*!40000 ALTER TABLE `mac_prop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_info`
--

DROP TABLE IF EXISTS `machine_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `machine_info` (
  `mac_id` int(11) NOT NULL AUTO_INCREMENT,
  `mac_ip` varchar(225) DEFAULT NULL,
  `mac_name` varchar(225) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `pro_update` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`mac_id`,`mac_name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_info`
--

LOCK TABLES `machine_info` WRITE;
/*!40000 ALTER TABLE `machine_info` DISABLE KEYS */;
INSERT INTO `machine_info` VALUES (22,'192.168.0.200','YZN-SVR','2022-05-29 10:07:41',''),(23,'192.168.0.106','YZN-5','2022-05-29 10:44:19',''),(24,'192.168.0.201','DESKTOP-7SG2L75','2022-05-31 11:24:17',''),(25,'192.168.0.202','ACC-PC','2022-05-31 12:25:49',''),(26,'192.168.0.104','nay','2022-05-31 12:28:55',''),(27,'192.168.0.110','DESKTOP-E0PG85T','2022-06-01 08:12:24',''),(28,'192.168.100.65','WAIGYI','2023-04-25 09:50:38','');
/*!40000 ALTER TABLE `machine_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_code` varchar(15) NOT NULL,
  `comp_code` varchar(15) NOT NULL,
  `user_code` varchar(15) DEFAULT NULL,
  `menu_class` varchar(150) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_name_mm` varchar(500) DEFAULT NULL,
  `menu_url` varchar(500) DEFAULT NULL,
  `parent_menu_code` varchar(50) NOT NULL,
  `menu_type` varchar(50) DEFAULT NULL,
  `account` varchar(15) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_code`,`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('01','0010010',NULL,'Inventory','Report',NULL,'','79','Menu',NULL,100),('012022-001','0010010',NULL,'Account','Account',NULL,'','1','Menu','',NULL),('012022-002','0010010',NULL,'Account','Setup',NULL,'','012022-001','Menu','',NULL),('012022-003','0010010',NULL,'Account','Department',NULL,'','012022-002','Menu','',NULL),('012022-004','0010010',NULL,'AllCash','Cash Book',NULL,'','012022-001','Menu','',NULL),('012022-005','0010010',NULL,'Setup','COA Managment',NULL,'','012022-002','Menu','',NULL),('012022-006','0010010',NULL,'AllCash','Daily Cash',NULL,'','012022-004','Menu','1-0015',0),('012022-007','0010010',NULL,'System','User Setup',NULL,'','50','Menu','',NULL),('012022-008','0010010',NULL,'Inventory','Sale By Stock (Summary)',NULL,'SaleByStockSummary','01','Report','',NULL),('012022-009','0010010',NULL,'Inventory','Opening Stock By Group',NULL,'OpeningByGroup','01','Report','',0),('012022-010','0010010',NULL,'Inventory','Opening Stock By Location',NULL,'OpeningByLocation','01','Report','',0),('012022-011','0010010',NULL,'Inventory','Sale By Customer (Summary)',NULL,'SaleByCustomerSummary','01','Report','',NULL),('012022-012','0010010',NULL,'Inventory','Purchase By Supplier (Summary)',NULL,'PurchaseBySupplierSummary','01','Report','',NULL),('012022-013','0010010',NULL,'Inventory','Purchase By Stock (Summary)',NULL,'PurchaseByStockSummary','01','Report','',NULL),('012022-014','0010010',NULL,'System','Company',NULL,'','50','Menu','',NULL),('012022-015','0010010',NULL,'Inventory','Stock In/Out (Summary)',NULL,'StockInOutSummary','01','Report','',NULL),('022022-001','0010010',NULL,'Inventory','Stock In/Out (Detail)',NULL,'StockInOutDetail','01','Report','',NULL),('032022-001','0010010',NULL,'Inventory','Stock Out By Voucher Type (Detail)',NULL,'StockOutByVoucherTypeDetail','01','Report','',0),('032022-002','0010010',NULL,'Inventory','Stock In/Out Price Calender',NULL,'StockInOutPriceCalender','01','Report','',0),('0423-001','0010010',NULL,'Account','Journal',NULL,'','012022-001','Menu','',0),('0423-002','0010010',NULL,'Account','Journal Voucher',NULL,'','0423-001','Menu','',0),('0423-003','0010010',NULL,'Account','AR / AP',NULL,'','052022-001','Menu','',0),('052022-001','0010010',NULL,'','Report',NULL,'','012022-001','Menu','',0),('052022-002','0010010',NULL,'','G/L Listing',NULL,'','052022-001','Menu','',0),('052022-003','0010010',NULL,'Inventory','Sale By Sale Man (Summary)',NULL,'SaleBySaleManSummary','01','Report','',0),('052022-004','0010010',NULL,'Inventory','Sale By Sale Man (Detail)',NULL,'SaleBySaleManDetail','01','Report','',0),('052022-005','0010010',NULL,'Inventory','Sale Price Calender',NULL,'SalePriceCalender','01','Report','',0),('052022-006','0010010',NULL,'Inventory','Purchase Price Calender',NULL,'PurchasePriceCalender','01','Report','',0),('052022-007','0010010',NULL,'','Machine Property',NULL,'','50','Menu','',0),('062022-001','0010010',NULL,'Inventory','Stock Value',NULL,'StockValue','01','Report','',0),('082022-001','0010010',NULL,'','Transfer',NULL,'','52','Menu','',6),('1122-001','0010010',NULL,'Inventory','Manufacture',NULL,'','52','Menu','',8),('1122-002','0010010',NULL,'Inventory','Raw Material Usage (Detail)',NULL,'ProductionUsageDetail','01','Report','',0),('1122-003','0010010',NULL,'Inventory','Finished Goods Ouput (Detail)',NULL,'ProductionOutputDetail','01','Report','',0),('1122-004','0010010',NULL,'Inventory','Raw Material Usage (Summary)',NULL,'ProductionUsageSummary','01','Report','',0),('1122-005','0010010',NULL,'Inventory','Finished Goods Ouput (Summary)',NULL,'ProductionOutputSummary','01','Report','',0),('1122-006','0010010',NULL,'Inventory','Weight Loss',NULL,'','52','Menu','',9),('49','0010010',NULL,'Inventory','Role Setting','Role Setting','','50','Menu',NULL,3),('50','0010010',NULL,'Inventory','System','ကုန္ပစၥည္း စာရင္း','','1','Menu',NULL,NULL),('52','0010010',NULL,'Inventory','Entry','Entry',NULL,'79','Menu',NULL,1),('54','0010010',NULL,'Inventory','Menu','Menu','','50','Menu',NULL,2),('56','0010010',NULL,'Inventory','Sale','ကုန္ေရာင္း ေဘာက္ခ်ာ','','52','Menu',NULL,1),('57','0010010',NULL,'Inventory','Purchase','ကုန္ဝယ္ ေဘာက္ခ်ာ','','52','Menu',NULL,2),('58','0010010',NULL,'Inventory','Return In','ကုန္ေရာင္း ျပန္သြင္း','','52','Menu',NULL,3),('59','0010010',NULL,'Inventory','Return Out','ကုန္ဝယ္ ျပန္ပို႔','','52','Menu',NULL,4),('79','0010010',NULL,'Inventory','Inventory','ကုန္ပစၥည္း စာရင္း','','1','Menu',NULL,1),('80','0010010',NULL,'Inventory','Setup','Setup',NULL,'79','Menu',NULL,3),('81','0010010',NULL,'Inventory','Supplier','Supplier',NULL,'80','Menu',NULL,3),('82','0010010',NULL,'Inventory','Customer','Customer',NULL,'80','Menu',NULL,2),('84','0010010',NULL,'Inventory','Other Setup','Other Setup',NULL,'80','Menu',NULL,4),('85','0010010',NULL,'Inventory','Stock','Stock',NULL,'80','Menu',NULL,1),('89','0010010',NULL,'Inventory','Stock In/Out','ကုန္ဝင္ / ကုန္ထြက္','','52','Menu',NULL,5),('95','0010010',NULL,'Inventory','Opening','Opening',NULL,'80','Menu',NULL,NULL),('96','0010010',NULL,'Inventory','System Property','System Propery','','50','Menu','',1),('97','0010010',NULL,'Inventory','Pattern Setup','Pattern Setup','','80','Menu',NULL,1),('99','0010010',NULL,'Inventory','Reorder Level','Reorder Level','','52','Menu','',7),('rp-01','0010010',NULL,'Inventory','Sale By Stock (Detail)',NULL,'SaleByStockDetail','01','Report',NULL,NULL),('rp-02','0010010',NULL,'Inventory','Sale By Customer (Detail)',NULL,'SaleByCustomerDetail','01','Report',NULL,NULL),('rp-03','0010010',NULL,'Inventory','Purchase By Supplier (Detail)',NULL,'PurchaseBySupplierDetail','01','Report',NULL,NULL),('rp-04','0010010',NULL,'Inventory','Purchase By Stock (Detail)',NULL,'PurchaseByStockDetail','01','Report',NULL,NULL),('rp-06','0010010',NULL,'Inventory','Stock List By Group','Stock List By Group','StockListByGroup','01','Report',NULL,NULL),('rp-07','0010010',NULL,'Inventory','Top Sale By Customer','Top Sale By Customer','TopSaleByCustomer','01','Report',NULL,NULL),('rp-08','0010010',NULL,'Inventory','Top Sale By Sale Man','Top Sale By Sale Man','TopSaleBySaleMan','01','Report',NULL,NULL),('rp-09','0010010',NULL,'Inventory','Top Sale By Stock','Top Sale By Stock','TopSaleByStock','01','Report',NULL,NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_template`
--

DROP TABLE IF EXISTS `menu_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_template` (
  `menu_id` int(15) NOT NULL,
  `bus_id` int(15) NOT NULL,
  `menu_class` varchar(150) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_name_mm` varchar(500) DEFAULT NULL,
  `menu_url` varchar(500) DEFAULT NULL,
  `parent_menu_id` int(11) NOT NULL,
  `menu_type` varchar(50) DEFAULT NULL,
  `account` varchar(15) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`,`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_template`
--

LOCK TABLES `menu_template` WRITE;
/*!40000 ALTER TABLE `menu_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege_company`
--

DROP TABLE IF EXISTS `privilege_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_company` (
  `role_code` varchar(15) NOT NULL,
  `comp_code` varchar(15) NOT NULL,
  `allow` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`role_code`,`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_company`
--

LOCK TABLES `privilege_company` WRITE;
/*!40000 ALTER TABLE `privilege_company` DISABLE KEYS */;
INSERT INTO `privilege_company` VALUES ('001','0010010','\0'),('001','1','\0'),('002','0010010','\0'),('002','1','\0'),('003','0010010','\0'),('003','1','\0'),('004','0010010','\0'),('004','1','\0'),('012022-001','0010010',''),('012022-001','1','\0'),('1','0010010',''),('1','1','\0');
/*!40000 ALTER TABLE `privilege_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege_menu`
--

DROP TABLE IF EXISTS `privilege_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_menu` (
  `menu_code` varchar(15) NOT NULL,
  `role_code` varchar(15) NOT NULL,
  `comp_code` varchar(15) NOT NULL,
  `allow` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`menu_code`,`role_code`,`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_menu`
--

LOCK TABLES `privilege_menu` WRITE;
/*!40000 ALTER TABLE `privilege_menu` DISABLE KEYS */;
INSERT INTO `privilege_menu` VALUES ('01','001','0010010','\0'),('01','002','0010010','\0'),('01','003','0010010','\0'),('01','004','0010010','\0'),('01','012022-001','0010010',''),('01','1','0010010',''),('012022-001','001','0010010','\0'),('012022-001','002','0010010','\0'),('012022-001','003','0010010','\0'),('012022-001','004','0010010','\0'),('012022-001','012022-001','0010010','\0'),('012022-001','1','0010010',''),('012022-002','001','0010010','\0'),('012022-002','002','0010010','\0'),('012022-002','003','0010010','\0'),('012022-002','004','0010010','\0'),('012022-002','012022-001','0010010','\0'),('012022-002','1','0010010',''),('012022-003','001','0010010','\0'),('012022-003','002','0010010','\0'),('012022-003','003','0010010','\0'),('012022-003','004','0010010','\0'),('012022-003','012022-001','0010010','\0'),('012022-003','1','0010010',''),('012022-004','001','0010010','\0'),('012022-004','002','0010010','\0'),('012022-004','003','0010010','\0'),('012022-004','004','0010010','\0'),('012022-004','012022-001','0010010','\0'),('012022-004','1','0010010',''),('012022-005','001','0010010','\0'),('012022-005','002','0010010','\0'),('012022-005','003','0010010','\0'),('012022-005','004','0010010','\0'),('012022-005','012022-001','0010010','\0'),('012022-005','1','0010010',''),('012022-006','001','0010010','\0'),('012022-006','002','0010010','\0'),('012022-006','003','0010010','\0'),('012022-006','004','0010010','\0'),('012022-006','012022-001','0010010','\0'),('012022-006','1','0010010',''),('012022-007','001','0010010','\0'),('012022-007','002','0010010','\0'),('012022-007','003','0010010','\0'),('012022-007','004','0010010','\0'),('012022-007','012022-001','0010010','\0'),('012022-007','1','0010010',''),('012022-008','001','0010010','\0'),('012022-008','002','0010010','\0'),('012022-008','003','0010010','\0'),('012022-008','004','0010010','\0'),('012022-008','012022-001','0010010',''),('012022-008','1','0010010',''),('012022-009','001','0010010','\0'),('012022-009','002','0010010','\0'),('012022-009','003','0010010','\0'),('012022-009','004','0010010','\0'),('012022-009','012022-001','0010010',''),('012022-009','1','0010010',''),('012022-010','001','0010010','\0'),('012022-010','002','0010010','\0'),('012022-010','003','0010010','\0'),('012022-010','004','0010010','\0'),('012022-010','012022-001','0010010',''),('012022-010','1','0010010',''),('012022-011','001','0010010','\0'),('012022-011','002','0010010','\0'),('012022-011','003','0010010','\0'),('012022-011','004','0010010','\0'),('012022-011','012022-001','0010010',''),('012022-011','1','0010010',''),('012022-012','001','0010010','\0'),('012022-012','002','0010010','\0'),('012022-012','003','0010010','\0'),('012022-012','004','0010010','\0'),('012022-012','012022-001','0010010',''),('012022-012','1','0010010',''),('012022-013','001','0010010','\0'),('012022-013','002','0010010','\0'),('012022-013','003','0010010','\0'),('012022-013','004','0010010','\0'),('012022-013','012022-001','0010010',''),('012022-013','1','0010010',''),('012022-014','001','0010010','\0'),('012022-014','002','0010010','\0'),('012022-014','003','0010010','\0'),('012022-014','004','0010010','\0'),('012022-014','012022-001','0010010',''),('012022-014','1','0010010',''),('012022-015','001','0010010','\0'),('012022-015','002','0010010','\0'),('012022-015','003','0010010','\0'),('012022-015','004','0010010','\0'),('012022-015','012022-001','0010010',''),('012022-015','1','0010010',''),('012022-016','012022-001','0010010',''),('012022-016','1','0010010',''),('022022-001','001','0010010','\0'),('022022-001','002','0010010','\0'),('022022-001','003','0010010','\0'),('022022-001','004','0010010','\0'),('022022-001','012022-001','0010010',''),('022022-001','1','0010010',''),('032022-001','001','0010010','\0'),('032022-001','002','0010010','\0'),('032022-001','003','0010010','\0'),('032022-001','004','0010010','\0'),('032022-001','012022-001','0010010',''),('032022-001','1','0010010',''),('032022-002','001','0010010','\0'),('032022-002','002','0010010','\0'),('032022-002','003','0010010','\0'),('032022-002','004','0010010','\0'),('032022-002','012022-001','0010010',''),('032022-002','1','0010010',''),('0423-001','003','0010010',''),('0423-001','012022-001','0010010',''),('0423-001','1','0010010',''),('0423-002','003','0010010',''),('0423-002','012022-001','0010010',''),('0423-002','1','0010010',''),('0423-003','003','0010010',''),('0423-003','012022-001','0010010',''),('0423-003','1','0010010',''),('052022-001','001','0010010','\0'),('052022-001','002','0010010','\0'),('052022-001','003','0010010','\0'),('052022-001','004','0010010','\0'),('052022-001','012022-001','0010010',''),('052022-001','1','0010010',''),('052022-002','001','0010010','\0'),('052022-002','002','0010010','\0'),('052022-002','003','0010010','\0'),('052022-002','004','0010010','\0'),('052022-002','012022-001','0010010',''),('052022-002','1','0010010',''),('052022-003','001','0010010','\0'),('052022-003','002','0010010','\0'),('052022-003','003','0010010','\0'),('052022-003','004','0010010','\0'),('052022-003','012022-001','0010010',''),('052022-003','1','0010010',''),('052022-004','001','0010010','\0'),('052022-004','002','0010010','\0'),('052022-004','003','0010010','\0'),('052022-004','004','0010010','\0'),('052022-004','012022-001','0010010',''),('052022-004','1','0010010',''),('052022-005','001','0010010','\0'),('052022-005','002','0010010','\0'),('052022-005','003','0010010','\0'),('052022-005','004','0010010','\0'),('052022-005','012022-001','0010010',''),('052022-005','1','0010010',''),('052022-006','001','0010010','\0'),('052022-006','002','0010010','\0'),('052022-006','003','0010010','\0'),('052022-006','004','0010010','\0'),('052022-006','012022-001','0010010',''),('052022-006','1','0010010',''),('052022-007','001','0010010','\0'),('052022-007','002','0010010','\0'),('052022-007','003','0010010','\0'),('052022-007','004','0010010','\0'),('052022-007','012022-001','0010010',''),('052022-007','1','0010010',''),('062022-001','001','0010010','\0'),('062022-001','002','0010010','\0'),('062022-001','003','0010010','\0'),('062022-001','004','0010010','\0'),('062022-001','012022-001','0010010',''),('062022-001','1','0010010',''),('082022-001','001','0010010','\0'),('082022-001','002','0010010','\0'),('082022-001','003','0010010','\0'),('082022-001','004','0010010','\0'),('082022-001','012022-001','0010010',''),('082022-001','1','0010010',''),('1122-001','001','0010010','\0'),('1122-001','002','0010010','\0'),('1122-001','003','0010010','\0'),('1122-001','004','0010010','\0'),('1122-001','012022-001','0010010',''),('1122-001','1','0010010',''),('1122-002','001','0010010',''),('1122-002','002','0010010',''),('1122-002','003','0010010','\0'),('1122-002','004','0010010','\0'),('1122-002','012022-001','0010010',''),('1122-002','1','0010010',''),('1122-003','001','0010010',''),('1122-003','002','0010010',''),('1122-003','003','0010010','\0'),('1122-003','004','0010010','\0'),('1122-003','012022-001','0010010',''),('1122-003','1','0010010',''),('1122-004','001','0010010',''),('1122-004','002','0010010',''),('1122-004','003','0010010','\0'),('1122-004','004','0010010','\0'),('1122-004','012022-001','0010010',''),('1122-004','1','0010010',''),('1122-005','001','0010010',''),('1122-005','002','0010010',''),('1122-005','003','0010010','\0'),('1122-005','004','0010010','\0'),('1122-005','012022-001','0010010',''),('1122-005','1','0010010',''),('1122-006','001','0010010',''),('1122-006','002','0010010',''),('1122-006','003','0010010',''),('1122-006','004','0010010',''),('1122-006','012022-001','0010010',''),('1122-006','1','0010010',''),('49','001','0010010','\0'),('49','002','0010010','\0'),('49','003','0010010','\0'),('49','004','0010010','\0'),('49','012022-001','0010010','\0'),('49','1','0010010',''),('50','001','0010010','\0'),('50','002','0010010','\0'),('50','003','0010010','\0'),('50','004','0010010','\0'),('50','012022-001','0010010','\0'),('50','1','0010010',''),('52','001','0010010','\0'),('52','002','0010010','\0'),('52','003','0010010','\0'),('52','004','0010010','\0'),('52','012022-001','0010010',''),('52','1','0010010',''),('53','001','0010010','\0'),('53','002','0010010','\0'),('53','003','0010010','\0'),('53','004','0010010','\0'),('53','012022-001','0010010','\0'),('53','1','0010010',''),('54','001','0010010','\0'),('54','002','0010010','\0'),('54','003','0010010','\0'),('54','004','0010010','\0'),('54','012022-001','0010010','\0'),('54','1','0010010',''),('56','001','0010010','\0'),('56','002','0010010','\0'),('56','003','0010010','\0'),('56','004','0010010','\0'),('56','012022-001','0010010',''),('56','1','0010010',''),('57','001','0010010','\0'),('57','002','0010010','\0'),('57','003','0010010','\0'),('57','004','0010010','\0'),('57','012022-001','0010010',''),('57','1','0010010',''),('58','001','0010010','\0'),('58','002','0010010','\0'),('58','003','0010010','\0'),('58','004','0010010','\0'),('58','012022-001','0010010',''),('58','1','0010010',''),('59','001','0010010','\0'),('59','002','0010010','\0'),('59','003','0010010','\0'),('59','004','0010010','\0'),('59','012022-001','0010010',''),('59','1','0010010',''),('65','1','0010010',''),('66','1','0010010',''),('79','001','0010010','\0'),('79','002','0010010','\0'),('79','003','0010010','\0'),('79','004','0010010','\0'),('79','012022-001','0010010',''),('79','1','0010010',''),('80','001','0010010','\0'),('80','002','0010010','\0'),('80','003','0010010','\0'),('80','004','0010010','\0'),('80','012022-001','0010010',''),('80','1','0010010',''),('81','001','0010010','\0'),('81','002','0010010','\0'),('81','003','0010010','\0'),('81','004','0010010','\0'),('81','012022-001','0010010',''),('81','1','0010010',''),('82','001','0010010','\0'),('82','002','0010010','\0'),('82','003','0010010','\0'),('82','004','0010010','\0'),('82','012022-001','0010010',''),('82','1','0010010',''),('84','001','0010010','\0'),('84','002','0010010','\0'),('84','003','0010010','\0'),('84','004','0010010','\0'),('84','012022-001','0010010',''),('84','1','0010010',''),('85','001','0010010','\0'),('85','002','0010010','\0'),('85','003','0010010','\0'),('85','004','0010010','\0'),('85','012022-001','0010010',''),('85','1','0010010',''),('89','001','0010010','\0'),('89','002','0010010','\0'),('89','003','0010010','\0'),('89','004','0010010','\0'),('89','012022-001','0010010',''),('89','1','0010010',''),('95','001','0010010','\0'),('95','002','0010010','\0'),('95','003','0010010','\0'),('95','004','0010010','\0'),('95','012022-001','0010010',''),('95','1','0010010',''),('96','001','0010010','\0'),('96','002','0010010','\0'),('96','003','0010010','\0'),('96','004','0010010','\0'),('96','012022-001','0010010','\0'),('96','1','0010010',''),('97','001','0010010','\0'),('97','002','0010010','\0'),('97','003','0010010','\0'),('97','004','0010010','\0'),('97','012022-001','0010010',''),('97','1','0010010',''),('99','001','0010010','\0'),('99','002','0010010','\0'),('99','003','0010010','\0'),('99','004','0010010','\0'),('99','012022-001','0010010',''),('99','1','0010010',''),('rp-01','001','0010010','\0'),('rp-01','002','0010010','\0'),('rp-01','003','0010010','\0'),('rp-01','004','0010010','\0'),('rp-01','012022-001','0010010','\0'),('rp-01','1','0010010',''),('rp-02','001','0010010','\0'),('rp-02','002','0010010','\0'),('rp-02','003','0010010','\0'),('rp-02','004','0010010','\0'),('rp-02','012022-001','0010010','\0'),('rp-02','1','0010010',''),('rp-03','001','0010010','\0'),('rp-03','002','0010010','\0'),('rp-03','003','0010010','\0'),('rp-03','004','0010010','\0'),('rp-03','012022-001','0010010','\0'),('rp-03','1','0010010',''),('rp-04','001','0010010','\0'),('rp-04','002','0010010','\0'),('rp-04','003','0010010','\0'),('rp-04','004','0010010','\0'),('rp-04','012022-001','0010010','\0'),('rp-04','1','0010010',''),('rp-05','012022-001','0010010','\0'),('rp-05','1','0010010',''),('rp-06','001','0010010','\0'),('rp-06','002','0010010','\0'),('rp-06','003','0010010','\0'),('rp-06','004','0010010','\0'),('rp-06','012022-001','0010010','\0'),('rp-06','1','0010010',''),('rp-07','001','0010010','\0'),('rp-07','002','0010010','\0'),('rp-07','003','0010010','\0'),('rp-07','004','0010010','\0'),('rp-07','012022-001','0010010','\0'),('rp-07','1','0010010',''),('rp-08','001','0010010','\0'),('rp-08','002','0010010','\0'),('rp-08','003','0010010','\0'),('rp-08','004','0010010','\0'),('rp-08','012022-001','0010010','\0'),('rp-08','1','0010010',''),('rp-09','001','0010010','\0'),('rp-09','002','0010010','\0'),('rp-09','003','0010010','\0'),('rp-09','004','0010010','\0'),('rp-09','012022-001','0010010','\0'),('rp-09','1','0010010',''),('rp-10','012022-001','0010010','\0'),('rp-10','1','0010010','');
/*!40000 ALTER TABLE `privilege_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_code` varchar(15) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_code`,`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('003','admin'),('012022-001','Shop'),('1','Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_prop`
--

DROP TABLE IF EXISTS `role_prop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_prop` (
  `role_code` varchar(15) NOT NULL,
  `prop_key` varchar(50) NOT NULL,
  `comp_code` varchar(15) NOT NULL,
  `remark` varchar(15) DEFAULT NULL,
  `prop_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_code`,`prop_key`,`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_prop`
--

LOCK TABLES `role_prop` WRITE;
/*!40000 ALTER TABLE `role_prop` DISABLE KEYS */;
INSERT INTO `role_prop` VALUES ('001','calculate.stock','0010010',NULL,'true'),('001','check.sale.A5','0010010',NULL,'true'),('001','check.sale.voucher','0010010',NULL,'true'),('001','chk.sale.A5','0010010',NULL,'true'),('001','default.currency','0010010',NULL,'MMK'),('001','default.customer','0010010',NULL,'y063'),('001','default.location','0010010',NULL,'022-0002'),('001','default.sale.paid','0010010',NULL,'true'),('001','default.saleman','0010010',NULL,'09-001'),('001','default.supplier','0010010',NULL,'SUP00001-011'),('001','sale.price.change','0010010',NULL,'true'),('001','sale.price.option','0010010',NULL,'true'),('001','sale.voucher.edit','0010010',NULL,'true'),('001','unit.relation','0010010',NULL,'true'),('002','calculate.stock','0010010',NULL,'true'),('002','check.sale.A5','0010010',NULL,'true'),('002','check.sale.voucher','0010010',NULL,'true'),('002','chk.sale.A5','0010010',NULL,'true'),('002','default.currency','0010010',NULL,'MMK'),('002','default.customer','0010010',NULL,'y063'),('002','default.location','0010010',NULL,'022-0002'),('002','default.sale.paid','0010010',NULL,'true'),('002','default.saleman','0010010',NULL,'09-001'),('002','default.supplier','0010010',NULL,'SUP00001-011'),('002','purchase.voucher.edit','0010010',NULL,'true'),('002','sale.price.change','0010010',NULL,'true'),('002','sale.price.option','0010010',NULL,'true'),('002','sale.voucher.edit','0010010',NULL,'true'),('002','unit.relation','0010010',NULL,'true'),('003','calculate.stock','0010010',NULL,'true'),('003','check.sale.A5','0010010',NULL,'true'),('003','check.sale.voucher','0010010',NULL,'true'),('003','chk.sale.A5','0010010',NULL,'true'),('003','default.currency','0010010',NULL,'MMK'),('003','default.customer','0010010',NULL,'y063'),('003','default.location','0010010',NULL,'022-0002'),('003','default.sale.paid','0010010',NULL,'true'),('003','default.saleman','0010010',NULL,'09-001'),('003','default.supplier','0010010',NULL,'SUP00001-011'),('003','purchase.voucher.edit','0010010',NULL,'true'),('003','sale.price.change','0010010',NULL,'true'),('003','sale.price.option','0010010',NULL,'true'),('003','sale.voucher.edit','0010010',NULL,'true'),('003','unit.relation','0010010',NULL,'true'),('004','calculate.stock','0010010',NULL,'true'),('004','check.sale.A5','0010010',NULL,'true'),('004','check.sale.voucher','0010010',NULL,'true'),('004','chk.sale.A5','0010010',NULL,'true'),('004','default.currency','0010010',NULL,'MMK'),('004','default.customer','0010010',NULL,'y063'),('004','default.location','0010010',NULL,'022-0002'),('004','default.sale.paid','0010010',NULL,'true'),('004','default.saleman','0010010',NULL,'09-001'),('004','default.supplier','0010010',NULL,'SUP00001-011'),('004','purchase.voucher.edit','0010010',NULL,'true'),('004','sale.price.change','0010010',NULL,'true'),('004','sale.price.option','0010010',NULL,'true'),('004','sale.voucher.edit','0010010',NULL,'true'),('004','unit.relation','0010010',NULL,'true'),('012022-001','calculate.stock','0010010',NULL,'true'),('012022-001','check.sale.voucher','0010010',NULL,'true'),('012022-001','default.currency','0010010',NULL,'MMK'),('012022-001','default.customer','0010010',NULL,'001'),('012022-001','default.location','0010010',NULL,'022-0001'),('012022-001','default.sale.paid','0010010',NULL,'true'),('012022-001','default.saleman','0010010',NULL,'09-001'),('012022-001','default.supplier','0010010',NULL,'-'),('012022-001','sale.price.option','0010010',NULL,'false'),('012022-001','sale.voucher.edit','0010010',NULL,'false'),('1','calculate.stock','0010010',NULL,'true'),('1','check.sale.A5','0010010',NULL,'true'),('1','chk.sale.A5','0010010',NULL,'true'),('1','default.currency','0010010',NULL,'MMK'),('1','default.customer','0010010',NULL,'y063'),('1','default.location','0010010',NULL,'022-0002'),('1','default.sale.paid','0010010',NULL,'true'),('1','default.saleman','0010010',NULL,'09-001'),('1','default.supplier','0010010',NULL,'SUP00001-011'),('1','purchase.voucher.edit','0010010',NULL,'true'),('1','sale.price.change','0010010',NULL,'true'),('1','sale.price.option','0010010',NULL,'true'),('1','sale.voucher.edit','0010010',NULL,'true'),('1','unit.relation','0010010',NULL,'true');
/*!40000 ALTER TABLE `role_prop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_table`
--

DROP TABLE IF EXISTS `seq_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_table` (
  `seq_no` int(11) NOT NULL,
  `option` varchar(15) NOT NULL,
  `period` varchar(15) NOT NULL,
  PRIMARY KEY (`seq_no`,`period`,`option`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_table`
--

LOCK TABLES `seq_table` WRITE;
/*!40000 ALTER TABLE `seq_table` DISABLE KEYS */;
INSERT INTO `seq_table` VALUES (1,'Role','012022'),(1,'Menu','022022'),(1,'Company','052022'),(1,'Menu','062022'),(1,'Menu','082022'),(2,'Menu','032022'),(3,'Menu','0423'),(4,'Role','1122'),(5,'AppUser','012022'),(6,'Menu','1122'),(7,'Menu','052022'),(16,'Menu','012022');
/*!40000 ALTER TABLE `seq_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_prop`
--

DROP TABLE IF EXISTS `sys_prop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_prop` (
  `prop_key` varchar(255) NOT NULL,
  `prop_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `comp_code` varchar(15) NOT NULL,
  PRIMARY KEY (`prop_key`,`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_prop`
--

LOCK TABLES `sys_prop` WRITE;
/*!40000 ALTER TABLE `sys_prop` DISABLE KEYS */;
INSERT INTO `sys_prop` VALUES ('creditor.account','001-00084',NULL,'0010010'),('customer.account','001-00202',NULL,'0010010'),('debtor.account','001-00079',NULL,'0010010'),('default.currency','MMK',NULL,'0010010'),('disable.pattern.stockio','false',NULL,'0010010'),('printer.name','CP-Q3',NULL,'0010010'),('printer.print','1',NULL,'0010010'),('report.path','C:\\CoreValue\\services\\core-inventory\\font\\Zawgyi-One.ttf',NULL,'0010010'),('report.purchase.voucher','PurchaseVoucherYZN',NULL,'0010010'),('report.sale.A4','SaleVoucherA4',NULL,'0010010'),('report.sale.A5','SaleVoucherA5',NULL,'0010010'),('report.sale.voucher','SaleVoucherPrinter',NULL,'0010010'),('supplier.account','001-00103',NULL,'0010010'),('trader.balance','true',NULL,'0010010');
/*!40000 ALTER TABLE `sys_prop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_role_company`
--

DROP TABLE IF EXISTS `v_role_company`;
/*!50001 DROP VIEW IF EXISTS `v_role_company`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_role_company` AS SELECT
 1 AS `role_code`,
  1 AS `comp_code`,
  1 AS `allow`,
  1 AS `name`,
  1 AS `phone`,
  1 AS `address`,
  1 AS `start_date`,
  1 AS `end_date`,
  1 AS `currency`,
  1 AS `batch_lock`,
  1 AS `year_end_date` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_role_menu`
--

DROP TABLE IF EXISTS `v_role_menu`;
/*!50001 DROP VIEW IF EXISTS `v_role_menu`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_role_menu` AS SELECT
 1 AS `menu_code`,
  1 AS `role_code`,
  1 AS `comp_code`,
  1 AS `allow`,
  1 AS `menu_name`,
  1 AS `menu_url`,
  1 AS `menu_type`,
  1 AS `menu_class`,
  1 AS `account`,
  1 AS `parent_menu_code`,
  1 AS `order_by` */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_role_company`
--

/*!50001 DROP VIEW IF EXISTS `v_role_company`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_role_company` AS select `p`.`role_code` AS `role_code`,`p`.`comp_code` AS `comp_code`,`p`.`allow` AS `allow`,`com`.`name` AS `name`,`com`.`phone` AS `phone`,`com`.`address` AS `address`,`com`.`start_date` AS `start_date`,`com`.`end_date` AS `end_date`,`com`.`currency` AS `currency`,`com`.`batch_lock` AS `batch_lock`,`com`.`year_end_date` AS `year_end_date` from (`privilege_company` `p` join `company_info` `com` on(`p`.`comp_code` = `com`.`comp_code`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_role_menu`
--

/*!50001 DROP VIEW IF EXISTS `v_role_menu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_role_menu` AS select `p`.`menu_code` AS `menu_code`,`p`.`role_code` AS `role_code`,`p`.`comp_code` AS `comp_code`,`p`.`allow` AS `allow`,`m`.`menu_name` AS `menu_name`,`m`.`menu_url` AS `menu_url`,`m`.`menu_type` AS `menu_type`,`m`.`menu_class` AS `menu_class`,`m`.`account` AS `account`,`m`.`parent_menu_code` AS `parent_menu_code`,`m`.`order_by` AS `order_by` from (`privilege_menu` `p` join `menu` `m` on(`p`.`menu_code` = `m`.`menu_code` and `p`.`comp_code` = `m`.`comp_code`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-02 12:19:35
