# ssm-demo

Maven的多模块Spring+SpringMVC+MyBatis项目骨架

## 运行环境

- JDK1.8.0_271
- Tomcat 8.5.61
- MySQL 5.7.30
- spring.version 5.0.2.RELEASE

## 运行步骤

### 修改jdbc.properties

> 此项目默认是ssm数据库



```sql
/*
 Navicat Premium Data Transfer

 Source Server         : Rick
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : ssm

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/01/2021 17:52:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for MyDB
-- ----------------------------
DROP TABLE IF EXISTS `MyDB`;
CREATE TABLE `MyDB` (
  `id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of MyDB
-- ----------------------------
BEGIN;
INSERT INTO `MyDB` VALUES (1, '2020-04-07 23:49:45', 10);
INSERT INTO `MyDB` VALUES (2, '2020-04-07 23:50:05', 11);
INSERT INTO `MyDB` VALUES (3, '2020-04-08 23:50:17', 12);
COMMIT;

-- ----------------------------
-- Table structure for loan
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` varchar(8) NOT NULL,
  `pin` varchar(8) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of loan
-- ----------------------------
BEGIN;
INSERT INTO `loan` VALUES ('1', '123', 1, '2020-05-04 23:54:32');
INSERT INTO `loan` VALUES ('10', '456', 2, '2020-05-13 23:57:41');
INSERT INTO `loan` VALUES ('12', '456', 3, '2020-05-13 23:58:28');
INSERT INTO `loan` VALUES ('13', '456', 2, '2020-05-13 23:58:46');
INSERT INTO `loan` VALUES ('14', '456', 4, '2020-05-13 23:58:58');
INSERT INTO `loan` VALUES ('15', '789', 1, '2020-05-14 00:26:17');
INSERT INTO `loan` VALUES ('17', '000', 1, '2020-05-14 01:17:56');
INSERT INTO `loan` VALUES ('2', '123', 1, '2020-05-13 23:54:59');
INSERT INTO `loan` VALUES ('3', '123', 2, '2020-05-13 23:55:16');
INSERT INTO `loan` VALUES ('4', '123', 1, '2020-04-29 23:55:37');
INSERT INTO `loan` VALUES ('5', '123', 3, '2020-05-13 23:56:25');
INSERT INTO `loan` VALUES ('6', '123', 2, '2020-05-13 23:56:37');
INSERT INTO `loan` VALUES ('7', '123', 4, '2020-05-13 23:56:56');
INSERT INTO `loan` VALUES ('8', '456', 1, '2020-05-06 23:57:13');
INSERT INTO `loan` VALUES ('9', '456', 1, '2020-05-13 23:57:26');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'aaa', 12);
INSERT INTO `user` VALUES (2, 'bbb', 34);
INSERT INTO `user` VALUES (3, 'ccc', 56);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

```

### 修改日志位置
日志配置文件在log4.properties中，主要修改log4j.appender.LOGFILE.File=自己机器上的位置

### 部署在Tomcat上即可运行