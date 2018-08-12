/*
SQLyog Ultimate v11.22 (64 bit)
MySQL - 5.7.22-log : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'ADMIN'),(2,'USER'),(3,'VIP');

/*Table structure for table `url` */

DROP TABLE IF EXISTS `url`;

CREATE TABLE `url` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'url的id',
  `name` varchar(200) DEFAULT NULL COMMENT 'url字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `url` */

insert  into `url`(`id`,`name`) values (1,'/'),(4,'/user'),(5,'/vip'),(6,'/admin'),(7,'/logout');

/*Table structure for table `url_role` */

DROP TABLE IF EXISTS `url_role`;

CREATE TABLE `url_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '链接角色中间表id',
  `url_id` int(11) DEFAULT NULL COMMENT '链接的id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `url_role` */

insert  into `url_role`(`id`,`url_id`,`role_id`) values (1,4,2),(2,5,3),(3,6,1),(4,7,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values (1,'超级管理员','123456'),(2,'一无所有王健林','123456'),(3,'我是脸盲刘强东','123456'),(4,'家境平平马化腾','123456'),(6,'vip客户','123456'),(7,'龙的传人爱打人','123456');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色中间表id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,2),(2,2,2),(3,3,2),(4,4,2),(5,5,2),(6,6,2),(7,7,2),(8,1,1),(9,6,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
