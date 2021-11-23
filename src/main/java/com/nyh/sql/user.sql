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

 Date: 22/11/2021 17:45:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userpwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regtime` timestamp NULL DEFAULT NULL,
  `prelogtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, '胡桃', '15139449158', '411226199509011234', 'a123', '2021-10-03 17:46:30', '2021-10-06 17:46:36');
INSERT INTO `user` VALUES (13, '钟离', '15555555555', '411110100001011234', 'a123', '2021-10-01 17:47:21', '2021-10-06 17:47:25');
INSERT INTO `user` VALUES (14, '枫原万叶', '15323232323', '411329199908081234', 'a123', '2021-10-03 17:48:08', '2021-10-06 17:48:11');
INSERT INTO `user` VALUES (15, '迪卢克', '15424242424', '411327199809011234', 'a123', '2021-10-01 17:48:55', '2021-10-06 17:49:00');
INSERT INTO `user` VALUES (16, '温迪', '18662626262', '411328110001011234', 'a123', '2021-10-01 17:49:41', '2021-11-20 14:33:07');
INSERT INTO `user` VALUES (18, '可莉', '18866666666', '411666199606166666', 'a123456', '2021-10-03 17:51:11', '2021-10-05 17:51:14');
INSERT INTO `user` VALUES (22, 'keli', '18866666668', '411666199606166666', 'akeli123', '2021-11-15 16:59:59', NULL);
INSERT INTO `user` VALUES (25, '小马', '18652422358', '412568200001030000', 'a123456', '2021-11-15 17:31:12', NULL);
INSERT INTO `user` VALUES (26, 'it_小牛', '15039449158', '410222200111230323', 'a123456', '2021-11-20 13:45:56', NULL);
INSERT INTO `user` VALUES (28, '段澳伟', '18837287383', '410522199906069374', 'sq180613', '2021-11-20 19:59:43', NULL);

SET FOREIGN_KEY_CHECKS = 1;
