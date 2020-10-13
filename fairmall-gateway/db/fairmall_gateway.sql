/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : fairmall_gateway

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001
 Date: 13/10/2020 15:57:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `route_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由id',
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'uri路径',
  `predicates` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '判定器',
  `filters` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '过滤器',
  `orders` int(0) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_gateway_routes_uri`(`uri`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网关路由表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES ('101', 'authorization-server', 'lb://authorization-server:8000', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authorization-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '授权认证服务网关注册', 'Y', '2020-10-13 15:56:49', '2020-10-13 15:56:49', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('102', 'authentication-server', 'lb://authentication-server:8001', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authentication-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '签权服务网关注册', 'Y', '2020-10-13 15:56:49', '2020-10-13 15:56:49', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('103', 'organization', 'lb://organization:8010', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/organization/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '系统管理相关接口', 'Y', '2020-10-13 15:56:49', '2020-10-13 15:56:49', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('104', 'gateway-admin', 'lb://gateway-admin:8445', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/gateway-admin/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '网关管理相关接口', 'Y', '2020-10-13 15:56:49', '2020-10-13 15:56:49', 'system', 'system');

SET FOREIGN_KEY_CHECKS = 1;
