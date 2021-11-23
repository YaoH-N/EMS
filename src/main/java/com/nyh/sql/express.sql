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

 Date: 22/11/2021 17:45:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intime` timestamp NULL DEFAULT NULL,
  `outtime` timestamp NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `sysPhone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES (1, '100', '胡桃', '15139449158', '京东快递', NULL, '2021-09-06 20:44:02', '2021-11-17 21:52:31', 1, '18888888888');
INSERT INTO `express` VALUES (3, '102', '胡桃', '15139449158', '京东快递', '122222', '2021-09-05 20:46:04', '2021-09-06 20:46:10', 1, '18888888888');
INSERT INTO `express` VALUES (4, '103', '钟离', '15555555555', '韵达快递', '666666', '2021-09-04 20:47:51', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (5, '104', '钟离', '15555555555', '京东快递', NULL, '2021-09-06 20:48:44', '2021-11-20 18:20:06', 1, '16666666666');
INSERT INTO `express` VALUES (9, '106', '枫原万叶', '15323232323', '京东快递', '123458', '2021-09-08 10:54:50', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (10, '107', '枫原万叶', '15323232323', '韵达快递', '123459', '2021-09-09 10:55:33', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (11, '108', '迪卢克', '15424242424', '京东快递', '133001', '2021-09-09 10:57:12', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (12, '109', '迪卢克', '15424242424', '京东快递', '133002', '2021-09-09 10:58:31', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (13, '110', '迪卢克', '15424242424', '京东快递', '133003', '2021-09-09 10:58:35', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (14, '111', '迪卢克', '15424242424', '京东快递', '133004', '2021-09-09 10:58:40', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (15, '112', '迪卢克', '15424242424', '京东快递', '133005', '2021-09-09 10:58:44', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (16, '113', '温迪', '18662626262', '京东快递', '133006', '2021-09-02 10:59:47', '2021-09-04 10:59:52', 1, '18888888888');
INSERT INTO `express` VALUES (17, '114', '温迪', '18662626262', '京东快递', '133007', '2021-09-03 11:01:12', '2021-09-04 11:01:18', 1, '18888888888');
INSERT INTO `express` VALUES (18, '115', '温迪', '18662626262', '京东快递', '133008', '2021-09-06 11:01:23', '2021-09-07 11:01:28', 1, '18888888888');
INSERT INTO `express` VALUES (19, '116', '温迪', '18662626262', '京东快递', '133009', '2021-09-09 11:01:34', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (20, '117', '温迪', '18662626262', '京东快递', '133010', '2021-09-09 11:01:37', '2021-11-17 19:21:06', 1, '18888888888');
INSERT INTO `express` VALUES (31, '166', '可莉', '18866666666', '顺丰速运', '343876', '2021-09-11 16:50:29', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (45, '10000', '牛耀辉', '15039449158', '顺丰速运', '341968', '2021-11-08 20:20:22', '2021-11-17 20:39:28', 1, '18888888888');
INSERT INTO `express` VALUES (46, '10001', '牛耀辉', '15039449158', '顺丰速运', '216062', '2021-11-08 20:21:33', '2021-11-08 20:22:40', 1, '15039449158');
INSERT INTO `express` VALUES (64, '200', '牛耀辉', '15039449158', '顺丰速运', '511923', '2021-11-09 18:28:43', NULL, 0, '15039449158');
INSERT INTO `express` VALUES (65, '300', '牛耀辉', '15039449158', '顺丰速运', '203537', '2021-11-10 11:57:17', NULL, 0, '15039449158');
INSERT INTO `express` VALUES (67, '2000', '牛耀辉', '15039449158', '顺丰速运', '859424', '2021-11-10 14:18:57', '2021-11-10 14:19:46', 1, '15039449158');
INSERT INTO `express` VALUES (75, '002', '小桃子', '18886451154', '顺丰速运', '829736', '2021-11-14 14:36:32', NULL, 0, '15039449158');
INSERT INTO `express` VALUES (76, '003', '小桃子', '18886451154', '顺丰速运', '773545', '2021-11-14 14:39:58', '2021-11-14 14:52:29', 1, '15039449158');
INSERT INTO `express` VALUES (77, '004', '武庚', '10086', '顺丰速运', '603163', '2021-11-14 21:05:37', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (79, 'abc123', 'it_小牛', '15039449158', '顺丰速运', '144589', '2021-11-15 14:25:45', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (81, '005', 'it_小牛', '15039449158', '顺丰速运', '466990', '2021-11-18 20:50:14', NULL, 0, '15094451286');
INSERT INTO `express` VALUES (82, '006', '大帅比', '15039449158', '顺丰速运', '307529', '2021-11-18 20:53:42', NULL, 0, '15094451286');
INSERT INTO `express` VALUES (83, '007', '大帅比', '15039449158', '顺丰速运', '639678', '2021-11-18 20:57:07', NULL, 0, '15094451286');
INSERT INTO `express` VALUES (84, '1111', 'it_小牛', '15039449158', '顺丰速运', '359315', '2021-11-18 20:59:11', NULL, 0, '15094451286');

SET FOREIGN_KEY_CHECKS = 1;
