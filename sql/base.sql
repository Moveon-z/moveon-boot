/*
 Navicat Premium Data Transfer

 Source Server         : docker_local
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : moveon_boot

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 14/11/2023 16:10:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL COMMENT 'id',
                            `dept_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '部门id',
                            `username` varchar(36) NOT NULL COMMENT '用户名',
                            `name` varchar(36) NOT NULL COMMENT '昵称',
                            `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
                            `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户类型',
                            `email` varchar(50) NOT NULL COMMENT '邮箱',
                            `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
                            `phone_number` bigint DEFAULT NULL COMMENT '手机号',
                            `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
                            `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户状态',
                            `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆ip',
                            `login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_by` bigint NOT NULL COMMENT '创建人',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `update_by` bigint DEFAULT NULL COMMENT '更新人',
                            `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '删除标记',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
