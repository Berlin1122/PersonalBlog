/*
Navicat MySQL Data Transfer

Source Server         : localMySQL
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-21 20:18:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `article_id` int(4) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `status` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `priority` int(4) DEFAULT NULL,
  `user_id` int(3) NOT NULL,
  `category_id` int(3) NOT NULL,
  `title` varchar(20) NOT NULL,
  `brief_intro` varchar(150) NOT NULL COMMENT '文章简介',
  PRIMARY KEY (`article_id`),
  KEY `fk_to_user` (`user_id`),
  KEY `fk_to_category` (`category_id`),
  CONSTRAINT `fk_to_category` FOREIGN KEY (`category_id`) REFERENCES `tb_article_category` (`category_id`),
  CONSTRAINT `fk_to_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
