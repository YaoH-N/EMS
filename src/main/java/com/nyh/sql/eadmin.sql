/*
 Navicat Premium Data Transfer

 Source Server         : 101.201.239.83
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 101.201.239.83:3306
 Source Schema         : ems

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 22/11/2021 17:45:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eadmin
-- ----------------------------
DROP TABLE IF EXISTS `eadmin`;
CREATE TABLE `eadmin`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logintime` datetime NULL DEFAULT NULL,
  `createtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eadmin
-- ----------------------------
INSERT INTO `eadmin` VALUES (1, 'admin', '123', '0:0:0:0:0:0:0:1', '2021-11-22 17:14:53', '2020-06-06 09:45:09');
INSERT INTO `eadmin` VALUES (2, '陈明', '123456', '120.219.89.74', '2021-11-20 19:35:15', '2021-11-06 20:02:17');
INSERT INTO `eadmin` VALUES (3, '赵立龙', '123456', '120.219.73.59', '2021-11-21 21:07:29', '2021-11-18 18:30:15');

SET FOREIGN_KEY_CHECKS = 1;
