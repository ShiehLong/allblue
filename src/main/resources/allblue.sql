/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.6.17 : Database - allblue
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`allblue` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `allblue`;

/*Table structure for table `blue_customer` */

DROP TABLE IF EXISTS `blue_customer`;

CREATE TABLE `blue_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户id 主键自增长ID',
  `customer_name` varchar(30) NOT NULL DEFAULT '' COMMENT '客户名字',
  `mobile` varchar(20) NOT NULL DEFAULT '0' COMMENT '手机账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别：0：未知， 1 ：男， 2：女',
  `effective` tinyint(1) NOT NULL DEFAULT '0' COMMENT '客户有效性：0：未验证， 1 ：验证无效， 2：验证有效',
  `region` tinyint(1) NOT NULL DEFAULT '0' COMMENT '组区域：0：未知， 1 ：上海， 2：北京',
  `bank_card` varchar(30) NOT NULL DEFAULT '' COMMENT '银行卡',
  `idcard_num` varchar(30) NOT NULL DEFAULT '' COMMENT '身份证',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted_at` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除，0否，大于0是删除（大于0是存的删除的时间戳）',
  `belong_user_id` int(11) DEFAULT NULL COMMENT '归属id',
  `group_id` int(11) DEFAULT NULL COMMENT '归属组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `blue_customer` */

insert  into `blue_customer`(`id`,`customer_name`,`mobile`,`password`,`sex`,`effective`,`region`,`bank_card`,`idcard_num`,`remark`,`deleted_at`,`belong_user_id`,`group_id`) values (1,'pcz','18221531111','111111',1,2,1,'888888','888888','',0,NULL,NULL);

/*Table structure for table `blue_group` */

DROP TABLE IF EXISTS `blue_group`;

CREATE TABLE `blue_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `blue_group` */

/*Table structure for table `blue_user` */

DROP TABLE IF EXISTS `blue_user`;

CREATE TABLE `blue_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


/*Data for the table `blue_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(3) NOT NULL,
  `pic` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `video` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;