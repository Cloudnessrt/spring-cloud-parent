/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-02 09:16:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for computer
-- ----------------------------
DROP TABLE IF EXISTS `computer`;
CREATE TABLE `computer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `balance` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of computer
-- ----------------------------

-- ----------------------------
-- Table structure for roleentity
-- ----------------------------
DROP TABLE IF EXISTS `roleentity`;
CREATE TABLE `roleentity` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `isValid` varchar(30) DEFAULT NULL COMMENT '有效性',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名',
  `useEnum` varchar(50) DEFAULT NULL COMMENT '使用状态',
  `createDate` datetime DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createUserName` varchar(255) DEFAULT NULL,
  `lastChangeDate` datetime DEFAULT NULL,
  `lastChangeUser` varchar(255) DEFAULT NULL,
  `lastChangeUserName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色实体';

-- ----------------------------
-- Records of roleentity
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `isValid` varchar(30) DEFAULT NULL COMMENT '有效性',
  `account` varchar(32) DEFAULT NULL COMMENT '用户名',
  `MD5` varchar(32) DEFAULT NULL COMMENT '密码md5加密',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `creditNum` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `sexEnum` varchar(32) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `companyId` varchar(32) DEFAULT NULL,
  `useEnum` varchar(50) DEFAULT NULL COMMENT '使用状态',
  `createDate` datetime DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createUserName` varchar(255) DEFAULT NULL,
  `lastChangeDate` datetime DEFAULT NULL,
  `lastChangeUser` varchar(255) DEFAULT NULL,
  `lastChangeUserName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('28b0a40db68b41e69bf9e24242c54ee8', 'True', 'account', null, '支付的免费', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('2bb3f24d9a3c403abcad0688a02ca4d4', 'True', 'account1', null, '支付的免费1', null, null, null, null, null, '2018-02-28 09:20:17', '1', '假', '2018-02-28 09:20:17', '1', '假');
INSERT INTO `users` VALUES ('6a00cfd31d4749a9ab889114a1eb6d4a', 'False', 'tchtch', 'tch', 'tch1', null, null, null, '123', null, '2018-03-01 14:41:21', '1', '假', '2018-03-02 09:13:59', '1', '假');
INSERT INTO `users` VALUES ('6bcb01a8ef73431c9a2af53008098981', 'True', 'szm', '1', '孙志明', 'creditnum', null, '2018-02-27 14:48:28', '123456', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('b3c03fa34aeb41d995c18d36b11cfe62', 'True', 'account13', '123', '支付的免费12', null, null, null, '123', null, '2018-03-01 09:03:06', '1', '假', '2018-03-01 09:03:06', '1', '假');
INSERT INTO `users` VALUES ('c8d9afe8cdd84465ad312f8519668b3e', 'True', 'account', null, '支付的免费', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('d79a2dfbb1644ecc9d0ada80e9af5c63', 'True', null, null, '支付的免费1', null, null, null, null, null, '2018-02-28 15:45:14', '1', '假', '2018-02-28 15:45:14', '1', '假');
INSERT INTO `users` VALUES ('df40683409704189962a51007c6b7a6a', 'True', 'account', null, '支付的免费', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('f54e928bb62043d9a7df892cd9dc862d', 'True', 'account', null, '支付的免费', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('fb42646307a844bebde5d187f1fa170a', 'True', 'True', null, 'true1', null, null, null, null, null, null, null, null, '2018-03-01 10:55:57', '1', '假');
INSERT INTO `users` VALUES ('fbeacd1e42c54841a99f1da7d91cf970', 'True', 'account1', null, '支付的免费1', null, null, null, null, null, null, null, null, null, null, null);
