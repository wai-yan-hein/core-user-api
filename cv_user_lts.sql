-- MariaDB dump 10.19  Distrib 10.9.3-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: cv_user_lts
-- ------------------------------------------------------
-- Server version	10.9.3-MariaDB

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES
('admin','1','admin','admin','',NULL,'admin',NULL);
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
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
  `active` bit(1) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `currency` varchar(15) NOT NULL,
  PRIMARY KEY (`comp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_info`
--

LOCK TABLES `company_info` WRITE;
/*!40000 ALTER TABLE `company_info` DISABLE KEYS */;
INSERT INTO `company_info` VALUES
('0010010','01','Linn Thitsar Hospital','09450060099','',NULL,NULL,NULL,'Wartan, Yagon.','','2021-06-01','2030-06-01','MMK');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES
('MMK','MMK','MMK','',NULL,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES
(1,'H','Head Office','corevalue.inventory.server','corevalue.account.server');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_info`
--

LOCK TABLES `machine_info` WRITE;
/*!40000 ALTER TABLE `machine_info` DISABLE KEYS */;
INSERT INTO `machine_info` VALUES
(1,'192.168.100.213','svr','2023-01-10 13:28:19',''),
(2,'192.168.100.63','WAIGYI','2023-01-11 07:50:29',''),
(3,'192.168.200.10','admin-PC','2023-01-11 10:56:48','');
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
  `user_code` varchar(15) DEFAULT NULL,
  `menu_class` varchar(150) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_name_mm` varchar(500) DEFAULT NULL,
  `menu_url` varchar(500) DEFAULT NULL,
  `parent_menu_code` varchar(50) NOT NULL,
  `menu_type` varchar(50) DEFAULT NULL,
  `account` varchar(15) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_code`),
  UNIQUE KEY `menu_id` (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES
('01',NULL,'Inventory','Report',NULL,'','79','Menu',NULL,100),
('012022-002',NULL,'Account','Setup',NULL,'','012022-001','Menu','',NULL),
('012022-003',NULL,'Account','Department',NULL,'','012022-002','Menu','',NULL),
('012022-004',NULL,'AllCash','Cash Book',NULL,'','012022-001','Menu','',NULL),
('012022-005',NULL,'Setup','COA Managment',NULL,'','012022-002','Menu','',NULL),
('012022-006',NULL,'AllCash','Daily Cash',NULL,NULL,'012022-004','Menu','001-00015',NULL),
('012022-007',NULL,'Inventory','Opening Stock By Location',NULL,'OpeningByLocation','01','Report','',NULL),
('012022-008',NULL,'Inventory','Sale By Stock (Summary)',NULL,'SaleByStockSummary','01','Report','',NULL),
('012022-009',NULL,'Inventory','Sale By Customer (Summary)',NULL,'SaleByCustomerSummary','01','Report','',NULL),
('012022-010',NULL,'Inventory','Purchase By Supplier (Summary)',NULL,'PurchaseBySupplierSummary','01','Report','',NULL),
('012022-011',NULL,'Inventory','Purchase By Stock (Summary)',NULL,'PurchaseByStockSummary','01','Report','',NULL),
('012022-012',NULL,'Inventory','Opening Stock By Group',NULL,'OpeningByGroup','01','Report','',NULL),
('012022-013',NULL,'Inventory','Stock In/Out (Summary)',NULL,'StockInOutSummary','01','Report','',NULL),
('0123-001',NULL,'Account','Individual Statement',NULL,'IndividualStatement','1122-008','Report','',0),
('0123-002',NULL,'Account','Dr & Cr Voucher',NULL,'','1122-003','Menu','',0),
('0123-003',NULL,'','Opening Balance',NULL,'','1122-004','Menu','',0),
('0123-004',NULL,'Inventory','RFID',NULL,'','52','Menu','',1),
('0123-005',NULL,'Account','Journal Stock Closing',NULL,'','1122-003','Menu','',3),
('032022-001',NULL,'Inventory','Stock In/Out (Detail)',NULL,'StockInOutDetail','01','Report','',0),
('032022-002',NULL,'Inventory','Stock Value',NULL,'StockValue','01','Report','',0),
('062022-001',NULL,'','Transfer',NULL,'','52','Menu','',6),
('062022-002',NULL,'','User Setup',NULL,'','50','Menu','',0),
('062022-003',NULL,'','Company',NULL,'','50','Menu','',0),
('062022-004',NULL,'Inventory','Sale Price Calender',NULL,'SalePriceCalender','01','Report','',0),
('062022-005',NULL,'Inventory','Purchase Price Calender',NULL,'PurchasePriceCalender','01','Report','',0),
('1122-001',NULL,'Account','Account',NULL,'','1','Menu','',2),
('1122-002',NULL,'Account','Cash Book',NULL,'','1122-001','Menu','',1),
('1122-003',NULL,'Account','Journal',NULL,'','1122-001','Menu','',3),
('1122-004',NULL,'','Setup',NULL,'','1122-001','Menu','',4),
('1122-005',NULL,'Account','Report',NULL,'','1122-001','Menu','',5),
('1122-006',NULL,'Account','G/L Listing',NULL,'','1122-005','Menu','',1),
('1122-007',NULL,'Account','AR / AP',NULL,'','1122-005','Menu','',2),
('1122-008',NULL,'Account','Financial Report',NULL,'','1122-005','Menu','',3),
('1122-009',NULL,'Account','Department',NULL,'','1122-004','Menu','',1),
('1122-010',NULL,'Account','Chart Of Account',NULL,'','1122-004','Menu','',2),
('1122-011',NULL,'Account','Journal Voucher',NULL,'','1122-003','Menu','',1),
('1122-012',NULL,'Account','COA Managment',NULL,'','1122-004','Menu','',3),
('1122-013',NULL,'Account','Income  & Expenditure (Detail)',NULL,'Income&ExpenditureDetail','1122-008','Report','',0),
('1122-014',NULL,'Inventory','Income & Expenditure (Summary)',NULL,'Income&ExpenditureSummary','1122-008','Report','',0),
('1122-015',NULL,'Account','Profit or Loss (Summary)',NULL,'Profit&LossSummary','1122-008','Report','',0),
('1122-016',NULL,'Account','Profit or Loss (Detail)',NULL,'Profit&LossDetail','1122-008','Report','',0),
('1122-017',NULL,'Account','Balance Sheet (Detail)',NULL,'BalanceSheetDetail','1122-008','Report','',0),
('1122-018',NULL,'Account','Balance Sheet (Summary)',NULL,'BalanceSheetSummary','1122-008','Report','',0),
('1122-019',NULL,'Account','Credit Detail',NULL,'CreditDetail','1122-008','Report','',0),
('1222-001',NULL,'System','Cloud Config',NULL,'','50','Menu','',0),
('1222-002',NULL,'AllCash','Adj Cash',NULL,'','1122-002','Menu','001-00017',2),
('1222-003',NULL,'AllCash','Daily Cash',NULL,'','1122-002','Menu','001-00015',1),
('1222-004',NULL,'AllCash','Plaza Cash',NULL,NULL,'012022-004','Menu','006-00001',NULL),
('1222-005',NULL,'AllCash','Bank',NULL,'','1122-001','Menu','',2),
('49',NULL,'Inventory','Role Setting','Role Setting','','50','Menu',NULL,3),
('50',NULL,'System','System','ကုန္ပစၥည္း စာရင္း','','1','Menu','',0),
('52',NULL,'Inventory','Entry','Entry',NULL,'79','Menu',NULL,1),
('54',NULL,'Inventory','Menu','Menu','','50','Menu',NULL,2),
('56',NULL,'Inventory','Sale','ကုန္ေရာင္း ေဘာက္ခ်ာ','','52','Menu',NULL,1),
('57',NULL,'Inventory','Purchase','ကုန္ဝယ္ ေဘာက္ခ်ာ','','52','Menu',NULL,2),
('58',NULL,'Inventory','Return In','ကုန္ေရာင္း ျပန္သြင္း','','52','Menu',NULL,3),
('59',NULL,'Inventory','Return Out','ကုန္ဝယ္ ျပန္ပို႔','','52','Menu',NULL,4),
('79',NULL,'Inventory','Inventory','ကုန္ပစၥည္း စာရင္း','','1','Menu',NULL,1),
('80',NULL,'Inventory','Setup','Setup',NULL,'79','Menu',NULL,3),
('81',NULL,'Inventory','Supplier','Supplier','','80','Menu','',4),
('82',NULL,'Inventory','Customer','Customer','','80','Menu','',3),
('84',NULL,'Inventory','Other Setup','Other Setup','','80','Menu','',6),
('85',NULL,'Inventory','Stock','Stock','','80','Menu','',1),
('89',NULL,'Inventory','Stock In/Out','ကုန္ဝင္ / ကုန္ထြက္','','52','Menu',NULL,5),
('95',NULL,'Inventory','Opening','Opening','','80','Menu','',2),
('96',NULL,'Inventory','System Property','System Propery','','50','Menu','',1),
('97',NULL,'Inventory','Pattern Setup','Pattern Setup','','80','Menu','',5),
('99',NULL,'Inventory','Reorder Level','Reorder Level','','52','Menu','',7),
('rp-01',NULL,'Inventory','Sale By Stock (Detail)',NULL,'SaleByStockDetail','01','Report',NULL,NULL),
('rp-02',NULL,'Inventory','Sale By Customer (Detail)',NULL,'SaleByCustomerDetail','01','Report',NULL,NULL),
('rp-03',NULL,'Inventory','Purchase By Supplier (Detail)',NULL,'PurchaseBySupplierDetail','01','Report',NULL,NULL),
('rp-04',NULL,'Inventory','Purchase By Stock (Detail)',NULL,'PurchaseByStockDetail','01','Report',NULL,NULL),
('rp-06',NULL,'Inventory','Stock List By Group','Stock List By Group','StockListByGroup','01','Report',NULL,NULL),
('rp-07',NULL,'Inventory','Top Sale By Customer','Top Sale By Customer','TopSaleByCustomer','01','Report',NULL,NULL),
('rp-08',NULL,'Inventory','Top Sale By Sale Man','Top Sale By Sale Man','TopSaleBySaleMan','01','Report',NULL,NULL),
('rp-09',NULL,'Inventory','Top Sale By Stock','Top Sale By Stock','TopSaleByStock','01','Report',NULL,NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_company`
--

LOCK TABLES `privilege_company` WRITE;
/*!40000 ALTER TABLE `privilege_company` DISABLE KEYS */;
INSERT INTO `privilege_company` VALUES
('1','0010010','');
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
  `allow` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`menu_code`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_menu`
--

LOCK TABLES `privilege_menu` WRITE;
/*!40000 ALTER TABLE `privilege_menu` DISABLE KEYS */;
INSERT INTO `privilege_menu` VALUES
('01','1','\0'),
('012022-001','1',''),
('012022-002','1',''),
('012022-003','1',''),
('012022-004','1',''),
('012022-005','1',''),
('012022-006','1',''),
('012022-007','1','\0'),
('012022-008','1','\0'),
('012022-009','1','\0'),
('012022-010','1','\0'),
('012022-011','1','\0'),
('012022-012','1','\0'),
('012022-013','1','\0'),
('0123-001','1',''),
('0123-002','1',''),
('0123-003','1',''),
('0123-004','1','\0'),
('0123-005','1',''),
('032022-001','1','\0'),
('032022-002','1','\0'),
('062022-001','1','\0'),
('062022-002','1',''),
('062022-003','1',''),
('062022-004','1','\0'),
('062022-005','1','\0'),
('1122-001','1',''),
('1122-002','1',''),
('1122-003','1',''),
('1122-004','1',''),
('1122-005','1',''),
('1122-006','1',''),
('1122-007','1',''),
('1122-008','1',''),
('1122-009','1',''),
('1122-010','1',''),
('1122-011','1',''),
('1122-012','1',''),
('1122-013','1',''),
('1122-014','1',''),
('1122-015','1',''),
('1122-016','1',''),
('1122-017','1',''),
('1122-018','1',''),
('1122-019','1',''),
('1222-001','1',''),
('1222-002','1',''),
('1222-003','1',''),
('1222-004','1',''),
('1222-005','1',''),
('1222-006','1',''),
('1222-007','1',''),
('1222-008','1',''),
('1222-009','1',''),
('1222-010','1',''),
('1222-011','1',''),
('1222-012','1',''),
('1222-013','1',''),
('1222-014','1',''),
('1222-015','1',''),
('1222-016','1',''),
('1222-017','1',''),
('1222-018','1',''),
('1222-019','1',''),
('1222-020','1',''),
('1222-021','1',''),
('49','1',''),
('50','1',''),
('52','1','\0'),
('53','1',''),
('54','1',''),
('56','1','\0'),
('57','1','\0'),
('58','1','\0'),
('59','1','\0'),
('65','1',''),
('66','1',''),
('79','1','\0'),
('80','1','\0'),
('81','1','\0'),
('82','1','\0'),
('84','1','\0'),
('85','1','\0'),
('89','1','\0'),
('95','1','\0'),
('96','1',''),
('97','1','\0'),
('99','1','\0'),
('rp-01','1','\0'),
('rp-02','1','\0'),
('rp-03','1','\0'),
('rp-04','1','\0'),
('rp-05','1',''),
('rp-06','1','\0'),
('rp-07','1','\0'),
('rp-08','1','\0'),
('rp-09','1','\0'),
('rp-10','1','');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES
('1','Admin');
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
  `remark` varchar(15) DEFAULT NULL,
  `prop_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_code`,`prop_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_prop`
--

LOCK TABLES `role_prop` WRITE;
/*!40000 ALTER TABLE `role_prop` DISABLE KEYS */;
INSERT INTO `role_prop` VALUES
('1','calculate.stock',NULL,'true'),
('1','default.currency',NULL,'MMK'),
('1','default.customer',NULL,'CUS00001-011'),
('1','default.location',NULL,'001-0001'),
('1','default.saleman',NULL,'09-001'),
('1','default.supplier',NULL,'SUP00001-011'),
('1','purchase.voucher.edit',NULL,'true'),
('1','sale.price.option',NULL,'false'),
('1','sale.voucher.edit',NULL,'true');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_table`
--

LOCK TABLES `seq_table` WRITE;
/*!40000 ALTER TABLE `seq_table` DISABLE KEYS */;
INSERT INTO `seq_table` VALUES
(1,'Role','1222'),
(2,'Menu','032022'),
(2,'AppUser','062022'),
(5,'Menu','0123'),
(5,'Menu','062022'),
(13,'Menu','012022'),
(19,'Menu','1122'),
(21,'Menu','1222');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_prop`
--

LOCK TABLES `sys_prop` WRITE;
/*!40000 ALTER TABLE `sys_prop` DISABLE KEYS */;
INSERT INTO `sys_prop` VALUES
('balancesheet.process','001-00008,001-00005,001-00002,001-00001',NULL,'01'),
('check.sale.A5','true',NULL,'01'),
('check.sale.voucher','false',NULL,'01'),
('cloud.activemq.account.server.queue','corevalue.account.server',NULL,'01'),
('cloud.activemq.inventory.server.queue','corevalue.inventory.server',NULL,'01'),
('cloud.activemq.url','tcp://163.44.197.237:61616',NULL,'01'),
('creditor.account','001-00035',NULL,'01'),
('customer.account','001-00025',NULL,'01'),
('debtor.account','001-00024',NULL,'01'),
('default.cash','600001',NULL,'01'),
('default.department','001',NULL,'01'),
('income.expense.process','001-00003,001-00007,001-00006,001-00004',NULL,'01'),
('inventory.group','001-00028',NULL,'01'),
('logo.name','logo.jpg',NULL,'01'),
('pl.process','001-00003,001-00006,001-00007,001-00004',NULL,'01'),
('printer.name','XP-80C',NULL,'01'),
('printer.pages','2',NULL,'01'),
('report.purchase.voucher','PurchaseVoucher',NULL,'01'),
('report.sale.A5','SaleVoucher',NULL,'01'),
('sale.price.change','true',NULL,'01'),
('sale.price.option','false',NULL,'01'),
('supplier.account','006-00109',NULL,'01'),
('trader.balance','false',NULL,'01');
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
  1 AS `currency` */;
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
/*!50001 VIEW `v_role_company` AS select `p`.`role_code` AS `role_code`,`p`.`comp_code` AS `comp_code`,`p`.`allow` AS `allow`,`com`.`name` AS `name`,`com`.`phone` AS `phone`,`com`.`address` AS `address`,`com`.`start_date` AS `start_date`,`com`.`end_date` AS `end_date`,`com`.`currency` AS `currency` from (`privilege_company` `p` join `company_info` `com` on(`p`.`comp_code` = `com`.`comp_code`)) */;
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
/*!50001 VIEW `v_role_menu` AS select `p`.`menu_code` AS `menu_code`,`p`.`role_code` AS `role_code`,`p`.`allow` AS `allow`,`m`.`menu_name` AS `menu_name`,`m`.`menu_url` AS `menu_url`,`m`.`menu_type` AS `menu_type`,`m`.`menu_class` AS `menu_class`,`m`.`account` AS `account`,`m`.`parent_menu_code` AS `parent_menu_code`,`m`.`order_by` AS `order_by` from (`privilege_menu` `p` join `menu` `m` on(`p`.`menu_code` = `m`.`menu_code`)) */;
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

-- Dump completed on 2023-03-14 14:35:29
