/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.28 : Database - webmagic_company
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webmagic_company` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `webmagic_company`;

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `unifiedSocialCreditCode` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `companyName` varchar(50) DEFAULT NULL COMMENT '企业姓名',
  `companyTel` varchar(50) DEFAULT NULL COMMENT '企业电话',
  `companyWebsite` varchar(50) DEFAULT NULL COMMENT '网址',
  `companyAddress` varchar(200) DEFAULT NULL COMMENT '地址',
  `companyProfile` text COMMENT '企业简介',
  `companySort` int(11) DEFAULT NULL COMMENT '搜索排序',
  `createDate` datetime DEFAULT NULL COMMENT '更新时间',
  `searchName` varchar(50) DEFAULT NULL COMMENT '搜索条件',
  KEY `FK_company_company` (`searchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `company` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
