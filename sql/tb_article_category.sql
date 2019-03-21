/*
Navicat MySQL Data Transfer

Source Server         : localMySQL
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-21 20:18:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_category`;
CREATE TABLE `tb_article_category` (
  `category_id` int(3) NOT NULL AUTO_INCREMENT,
  `user_id` int(3) NOT NULL,
  `category_name` varchar(20) NOT NULL COMMENT '博客类别名称',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
