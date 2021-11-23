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

 Date: 22/11/2021 17:44:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courier
-- ----------------------------
DROP TABLE IF EXISTS `courier`;
CREATE TABLE `courier`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `exname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trannumber` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `regtime` timestamp NULL DEFAULT NULL,
  `prelogtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of courier
-- ----------------------------
INSERT INTO `courier` VALUES (12, '九条', '18999999999', '419192199903061236', 'a123', 00000000908, '2021-09-01 17:46:17', '2021-09-12 17:46:20');
INSERT INTO `courier` VALUES (18, '帅哥', '15023236654', '410522200405099987', 'a123', 00000000000, '2021-11-07 19:25:42', '2021-11-07 19:25:42');
INSERT INTO `courier` VALUES (19, '王五', '15094451286', '421055200302145546', 'a123', 00000000888, '2021-11-09 22:08:20', '2021-11-18 20:49:35');
INSERT INTO `courier` VALUES (20, 'abc', '15039449158', '410522200302134564', 'abc1234', 00000099999, '2021-11-18 20:03:05', '2021-11-22 17:25:44');
INSERT INTO `courier` VALUES (32, '黄忠', '18856479523', '410222200311023569', 'a123', 00000000000, '2021-11-14 21:09:46', NULL);
INSERT INTO `courier` VALUES (33, 'huangzhong', '15320144785', '410222200311023569', 'a123', 00000000000, '2021-11-14 21:10:42', NULL);
INSERT INTO `courier` VALUES (37, '武庚', '15320165897', '410222200312033356', 'a123', 00000000000, '2021-11-14 21:23:08', NULL);

SET FOREIGN_KEY_CHECKS = 1;
