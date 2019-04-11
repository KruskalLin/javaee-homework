/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.20-log : Database - javaee
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javaee` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javaee`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_cart_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vtwutdsrmy7l12kf3hxyd6el` (`user_cart_id`),
  CONSTRAINT `FK1vtwutdsrmy7l12kf3hxyd6el` FOREIGN KEY (`user_cart_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`user_cart_id`) values (2,3);
insert  into `cart`(`id`,`user_cart_id`) values (3,4);

/*Table structure for table `cartgoods` */

DROP TABLE IF EXISTS `cartgoods`;

CREATE TABLE `cartgoods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `cart_goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg9nt0s8rv8upntmwyah8jximg` (`goods_id`),
  KEY `FK1k52isukjqfnyhxnavqpfi2fp` (`cart_goods_id`),
  CONSTRAINT `FK1k52isukjqfnyhxnavqpfi2fp` FOREIGN KEY (`cart_goods_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKg9nt0s8rv8upntmwyah8jximg` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `cartgoods` */

insert  into `cartgoods`(`id`,`quantity`,`goods_id`,`cart_goods_id`) values (11,1,1,3);
insert  into `cartgoods`(`id`,`quantity`,`goods_id`,`cart_goods_id`) values (12,3,2,3);

/*Table structure for table `creditcard` */

DROP TABLE IF EXISTS `creditcard`;

CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `creditcard` */

insert  into `creditcard`(`id`,`money`) values (1,11000);
insert  into `creditcard`(`id`,`money`) values (2,1000);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categories` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `property` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (1,'DailyUse','水桶',59,'小型');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (2,'Books','JAVAEE Tutorial',10000,'永垂不朽');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (3,'Books','Slam视觉',79,'纸质版');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (4,'Books','徐志摩诗集',50,'纸质版');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (5,'Books','三大批判',299,'经典');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (6,'Electronics','Apple Pro',6099,'银色');
insert  into `goods`(`id`,`categories`,`name`,`price`,`property`) values (7,'Sports','护膝',69,'黑色');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xqibcehjom7mfwc03ad0is6j` (`credit_card_id`),
  CONSTRAINT `FK2xqibcehjom7mfwc03ad0is6j` FOREIGN KEY (`credit_card_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`password`,`username`,`credit_card_id`) values (3,'123456','root',1);
insert  into `user`(`id`,`password`,`username`,`credit_card_id`) values (4,'123456','LimKruscal',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
