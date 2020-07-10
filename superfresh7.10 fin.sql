/*
Navicat MySQL Data Transfer

Source Server         : dxq
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : superfresh

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2020-07-10 14:51:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `add_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `add_sheng` varchar(20) NOT NULL,
  `add_shi` varchar(20) NOT NULL,
  `add_qu` varchar(20) NOT NULL,
  `add_juti` varchar(20) NOT NULL,
  `add_name` varchar(20) NOT NULL,
  `add_tel` varchar(20) NOT NULL,
  PRIMARY KEY (`add_id`),
  KEY `FK_Relationship_2` (`user_id`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('6', '1', '11', '22', '2', '2', '232', '1111');
INSERT INTO `address` VALUES ('7', '1', 'hz', 'hz', 'hz', '222', '111', 'qqqq');
INSERT INTO `address` VALUES ('8', '1', 'hz', 'hz', 'hz', '222', '111', 'qqqq');
INSERT INTO `address` VALUES ('9', '1', 'hz', 'hz', 'hz', '222', '111', 'qqqq');
INSERT INTO `address` VALUES ('10', '1', 'qwe', 'qwe', 'qwe', 'qwe', 'qwe', 'qwe');
INSERT INTO `address` VALUES ('11', '6', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx');

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `buy_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) NOT NULL,
  `mz_id` int(11) DEFAULT NULL,
  `cx_id` int(11) DEFAULT NULL,
  `buy_count` int(11) NOT NULL,
  `buy_situ` varchar(20) DEFAULT NULL,
  `flag` int(11) NOT NULL,
  `buy_sum` double NOT NULL,
  `buy_discsum` double NOT NULL,
  PRIMARY KEY (`buy_id`),
  KEY `FK_Relationship_8` (`com_id`),
  KEY `FK222` (`mz_id`),
  KEY `FK333` (`cx_id`),
  CONSTRAINT `FK222` FOREIGN KEY (`mz_id`) REFERENCES `manzinfo` (`mz_id`),
  CONSTRAINT `FK333` FOREIGN KEY (`cx_id`) REFERENCES `xianscx` (`cx_id`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES ('3', '4', '5', '4', '100', 'ss', '1', '201', '39');
INSERT INTO `buy` VALUES ('4', '4', null, null, '10', 'gm', '4', '0', '0');
INSERT INTO `buy` VALUES ('6', '4', null, null, '10', 'dd', '5', '25', '0');
INSERT INTO `buy` VALUES ('7', '4', null, null, '10', 'fher', '8', '24', '0');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_name` varchar(20) NOT NULL,
  `com_price` double NOT NULL,
  `com_vippri` double NOT NULL,
  `com_count` int(11) NOT NULL,
  `com_guige` varchar(20) DEFAULT NULL,
  `com_juti` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', 'ee', '11', '11', '11', 'dd', 'dd');
INSERT INTO `commodity` VALUES ('4', 'cola', '2.5', '2.4', '2000', 'none', 'none');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `cou_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `cou_what` varchar(40) DEFAULT NULL,
  `cou_suit_jine` double NOT NULL,
  `cou_dis_jine` double NOT NULL,
  `cou_start_day` date NOT NULL,
  `cou_end_day` date NOT NULL,
  PRIMARY KEY (`cou_id`),
  KEY `FK11` (`user_id`),
  CONSTRAINT `FK11` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `com_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `eva_what` varchar(40) DEFAULT NULL,
  `eva_day` date DEFAULT NULL,
  `eva_star` int(11) DEFAULT NULL,
  `eva_pic` longblob,
  PRIMARY KEY (`com_id`,`user_id`),
  KEY `FK_evaluate2` (`user_id`),
  CONSTRAINT `FK_evaluate` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`),
  CONSTRAINT `FK_evaluate2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('1', '1', '点点点', '2020-02-02', '4', null);
INSERT INTO `evaluate` VALUES ('1', '7', '点点点', '2020-02-02', '44', null);

-- ----------------------------
-- Table structure for fresh
-- ----------------------------
DROP TABLE IF EXISTS `fresh`;
CREATE TABLE `fresh` (
  `fre_id` int(20) NOT NULL AUTO_INCREMENT,
  `fre_name` varchar(20) NOT NULL,
  `fre_what` varchar(20) NOT NULL,
  PRIMARY KEY (`fre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of fresh
-- ----------------------------
INSERT INTO `fresh` VALUES ('2', 'fish', 'www');

-- ----------------------------
-- Table structure for gm
-- ----------------------------
DROP TABLE IF EXISTS `gm`;
CREATE TABLE `gm` (
  `gm_id` int(11) NOT NULL AUTO_INCREMENT,
  `gm_name` varchar(20) NOT NULL,
  `gm_pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`gm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gm
-- ----------------------------
INSERT INTO `gm` VALUES ('1', 'lcy', '123');

-- ----------------------------
-- Table structure for gmord
-- ----------------------------
DROP TABLE IF EXISTS `gmord`;
CREATE TABLE `gmord` (
  `gmord_id` int(11) NOT NULL AUTO_INCREMENT,
  `gm_id` int(11) NOT NULL,
  `com_id` int(11) NOT NULL,
  `gmord_cnt` int(11) NOT NULL,
  `gmord_situ` varchar(20) NOT NULL,
  PRIMARY KEY (`gmord_id`),
  KEY `FK_Relationship_10` (`gm_id`),
  KEY `FK_Relationship_9` (`com_id`),
  CONSTRAINT `FK_Relationship_10` FOREIGN KEY (`gm_id`) REFERENCES `gm` (`gm_id`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gmord
-- ----------------------------
INSERT INTO `gmord` VALUES ('7', '1', '1', '3', 'xx');

-- ----------------------------
-- Table structure for manzinfo
-- ----------------------------
DROP TABLE IF EXISTS `manzinfo`;
CREATE TABLE `manzinfo` (
  `mz_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) NOT NULL,
  `mz_what` varchar(40) DEFAULT NULL,
  `mz_count` int(11) NOT NULL,
  `mz_discount` double NOT NULL,
  `mz_start_day` date NOT NULL,
  `mz_end_day` date NOT NULL,
  PRIMARY KEY (`mz_id`),
  KEY `FK_2` (`com_id`),
  CONSTRAINT `FK_2` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of manzinfo
-- ----------------------------
INSERT INTO `manzinfo` VALUES ('3', '1', 'sdgsga', '7', '0.3', '2020-07-10', '2020-07-10');
INSERT INTO `manzinfo` VALUES ('5', '4', 'dfgre', '100', '0.85', '2020-07-08', '2020-07-11');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `men_id` int(11) NOT NULL AUTO_INCREMENT,
  `men_name` varchar(20) NOT NULL,
  `fre_id` int(11) NOT NULL,
  `men_step` varchar(40) NOT NULL,
  `men_pic` longblob,
  PRIMARY KEY (`men_id`),
  KEY `FK234` (`fre_id`),
  CONSTRAINT `FK234` FOREIGN KEY (`fre_id`) REFERENCES `fresh` (`fre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'xx', '2', 'g', null);
INSERT INTO `menu` VALUES ('2', 'dby', '2', 'qaq', null);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ord_id` int(11) NOT NULL AUTO_INCREMENT,
  `cou_id` int(11) NOT NULL,
  `add_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ord_init_jine` float NOT NULL,
  `ord_sum_jine` float DEFAULT NULL,
  `ord_ttl` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ord_situ` varchar(20) NOT NULL,
  PRIMARY KEY (`ord_id`),
  KEY `FK_Relationship_4` (`cou_id`),
  KEY `FK_Relationship_6` (`add_id`),
  KEY `FK_Relationship_7` (`user_id`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`cou_id`) REFERENCES `coupon` (`cou_id`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`add_id`) REFERENCES `address` (`add_id`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for ordjt
-- ----------------------------
DROP TABLE IF EXISTS `ordjt`;
CREATE TABLE `ordjt` (
  `mz_id` int(11) NOT NULL,
  `ord_id` int(11) NOT NULL,
  `com_id` int(11) NOT NULL,
  `ordjt_cnt` int(11) DEFAULT NULL,
  `com_price` float DEFAULT NULL,
  `ordjt_disc` float DEFAULT NULL,
  PRIMARY KEY (`mz_id`,`ord_id`,`com_id`),
  KEY `FK_ordjt2` (`ord_id`),
  KEY `FK_ordjt3` (`com_id`),
  CONSTRAINT `FK_ordjt` FOREIGN KEY (`mz_id`) REFERENCES `manzinfo` (`mz_id`),
  CONSTRAINT `FK_ordjt2` FOREIGN KEY (`ord_id`) REFERENCES `orders` (`ord_id`),
  CONSTRAINT `FK_ordjt3` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ordjt
-- ----------------------------

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `men_id` int(11) NOT NULL,
  `com_id` int(11) NOT NULL,
  `rec_how` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`men_id`,`com_id`),
  KEY `FK_recommend2` (`com_id`),
  CONSTRAINT `FK_recommend` FOREIGN KEY (`men_id`) REFERENCES `menu` (`men_id`),
  CONSTRAINT `FK_recommend2` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES ('1', '1', 'uig');
INSERT INTO `recommend` VALUES ('2', '1', '烦烦烦');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_sex` varchar(5) NOT NULL,
  `user_pwd` varchar(20) NOT NULL,
  `user_tel` varchar(20) NOT NULL,
  `user_mail` varchar(20) NOT NULL,
  `user_city` varchar(20) NOT NULL,
  `user_reg_day` date NOT NULL,
  `user_vip` varchar(5) NOT NULL,
  `user_vip_end` date DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'sdd', 'B', '123', '123', '123', '123', '2020-07-06', '123', '2020-07-06');
INSERT INTO `users` VALUES ('6', 'sww', 'w', '123', 'w', 'w', 'w', '2020-07-09', 'w', '2020-07-06');
INSERT INTO `users` VALUES ('7', 'sss', '男', '123', '6666', '888@qq', 'ss', '2020-07-09', '是', '2020-07-01');

-- ----------------------------
-- Table structure for xianscx
-- ----------------------------
DROP TABLE IF EXISTS `xianscx`;
CREATE TABLE `xianscx` (
  `cx_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) DEFAULT NULL,
  `cx_price` double NOT NULL,
  `cx_count` int(11) NOT NULL,
  `cx_start_day` date NOT NULL,
  `cx_end_day` date NOT NULL,
  PRIMARY KEY (`cx_id`),
  KEY `FK_Relationship_1` (`com_id`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`com_id`) REFERENCES `commodity` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xianscx
-- ----------------------------
INSERT INTO `xianscx` VALUES ('1', '1', '9.9', '5', '2020-07-09', '2020-07-09');
INSERT INTO `xianscx` VALUES ('4', '4', '2', '100', '2020-07-08', '2020-07-11');
