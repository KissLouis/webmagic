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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业基本信息',
  `companyId` varchar(20) DEFAULT NULL COMMENT '工商注册号',
  `companyName` varchar(50) DEFAULT NULL COMMENT '企业姓名',
  `companyTel` varchar(50) DEFAULT NULL COMMENT '企业电话',
  `companyEmail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `companyWebsite` varchar(50) DEFAULT NULL COMMENT '网址',
  `companyAddress` varchar(200) DEFAULT NULL COMMENT '地址',
  `companySynopsis` text COMMENT '企业简介',
  `companySort` int(11) DEFAULT NULL COMMENT '搜索排序',
  `createDate` datetime DEFAULT NULL COMMENT '更新时间',
  `searchId` int(11) DEFAULT NULL COMMENT '搜索条件主表，FK',
  PRIMARY KEY (`id`),
  KEY `FK_company_company` (`searchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `company_information` */

DROP TABLE IF EXISTS `company_information`;

CREATE TABLE `company_information` (
  `id` varchar(20) DEFAULT NULL COMMENT '工商信息表',
  `legalPerson` varchar(50) DEFAULT NULL COMMENT '法人',
  `legalPersonInformation` varchar(255) DEFAULT NULL COMMENT '法人详细信息URL',
  `registeredCapital` varchar(50) DEFAULT NULL COMMENT '注册资本',
  `registrationTime` varchar(50) DEFAULT NULL COMMENT '注册时间',
  `enterpriseState` varchar(25) DEFAULT NULL COMMENT '企业状态',
  `ownershipStructure` text COMMENT '股权结构图',
  `businessRegistrationNumber` varchar(50) DEFAULT NULL COMMENT '工商注册号',
  `organizationCode` varchar(50) DEFAULT NULL COMMENT '组织机构代码',
  `uniformCreditCode` varchar(50) DEFAULT NULL COMMENT '统一信用代码',
  `enterpriseType` varchar(50) DEFAULT NULL COMMENT '企业类型',
  `taxpayerIdentification` varchar(50) DEFAULT NULL COMMENT '纳税人识别号',
  `industry` varchar(50) DEFAULT NULL COMMENT '行业',
  `businessTerm` varchar(50) DEFAULT NULL COMMENT '营业期限',
  `approvalDate` varchar(50) DEFAULT NULL COMMENT '核准日期',
  `registrationAuthority` varchar(50) DEFAULT NULL COMMENT '登记机关',
  `englishName` varchar(50) DEFAULT NULL COMMENT '英文名称',
  `registeredAddress` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `businessScope` varchar(255) DEFAULT NULL COMMENT '经营范围'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='工商信息';

/*Table structure for table `search` */

DROP TABLE IF EXISTS `search`;

CREATE TABLE `search` (
  `searchId` int(11) NOT NULL COMMENT '爬取条件表',
  `searchName` varchar(25) DEFAULT NULL COMMENT '爬取企业名称',
  `searchCount` int(11) DEFAULT NULL COMMENT '爬取数量',
  `searchUrl` varchar(255) DEFAULT NULL COMMENT '爬取URL',
  `startDate` datetime DEFAULT NULL COMMENT '开始时间',
  `endDate` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`searchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
