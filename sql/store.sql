/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : store

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-05-29 19:44:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `goods_id` int(20) DEFAULT NULL,
  `goods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `original_img` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `goods_num` int(20) DEFAULT NULL,
  `member_goods_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `goods_id` (`goods_id`),
  KEY `goods_name` (`goods_name`),
  KEY `market_price` (`market_price`),
  KEY `goods_price` (`goods_price`),
  KEY `original_img` (`original_img`),
  CONSTRAINT `cart_ibfk_6` FOREIGN KEY (`original_img`) REFERENCES `goods` (`original_img`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`goods_name`) REFERENCES `goods` (`goods_name`),
  CONSTRAINT `cart_ibfk_4` FOREIGN KEY (`market_price`) REFERENCES `goods` (`market_price`),
  CONSTRAINT `cart_ibfk_5` FOREIGN KEY (`goods_price`) REFERENCES `goods` (`shop_price`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `goods_content` text COLLATE utf8_bin,
  `original_img` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_hot` tinyint(1) DEFAULT NULL,
  `goods_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `goods_name` (`goods_name`),
  KEY `market_price` (`market_price`),
  KEY `shop_price` (`shop_price`),
  KEY `original_img` (`original_img`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '小米 4c 标准版', '1399', '1299', 0xE5B08FE7B1B320346320E6A087E58786E7898820E585A8E7BD91E9809A20E799BDE889B220E7A7BBE58AA8E88194E9809AE794B5E4BFA13447E6898BE69CBA20E58F8CE58DA1E58F8CE5BE85, 'img/1/c_0001.jpg', '1', '1');
INSERT INTO `goods` VALUES ('2', '中兴 AXON', '2899', '2699', 0xE4B8ADE585B42041584F4E20E5A4A9E69CBA206D696E6920E58E8BE58A9BE5B18FE7898820423230313520E58D8EE5B094E9879120E7A7BBE58AA8E88194E9809AE794B5E4BFA1344720E58F8CE58DA1E58F8CE5BE85, 'img/1/c_0002.jpg', '0', '1');
INSERT INTO `goods` VALUES ('3', '华为荣耀6', '1599', '1499', 0xE88DA3E88080203620284836302D4C30312920334742E58685E5AD98E6A087E58786E7898820E9BB91E889B220E7A7BBE58AA83447E6898BE69CBA, 'img/1/c_0003.jpg', '1', '1');
INSERT INTO `goods` VALUES ('4', '联想 P1', '2199', '1999', 0xE88194E683B32050312031364720E4BCAFE788B5E9879120E7A7BBE58AA8E88194E9809A3447E6898BE69CBAE58585E794B535E58886E9929FEFBC8CE9809AE8AF9D33E5B08FE697B6EFBC81E7A791E68A80E6BA90E4BA8EE8B685E8B68AEFBC81E59381E8B4A8E6BA90E4BA8EE6B289E6B780EFBC81353030306D4168E5A4A7E794B5E6B1A0EFBC81E9AB98E7ABAFE59586E58AA1E4BDB3E9858DEFBC81, 'img/1/c_0004.jpg', '1', '1');
INSERT INTO `goods` VALUES ('5', '摩托罗拉 moto x（x+1）', '1799', '1699', 0xE691A9E68998E7BD97E68B89206D6F746F2078EFBC88782B31EFBC892858543130383529203332474220E5A4A9E784B6E7ABB920E585A8E7BD91E9809A3447E6898BE69CBA3131E69C883131E5A4A9EFBC814D4F544F2058E99C87E692BCE789B9E683A0E69DA5E8A2ADEFBC8131363939E58583EFBC81E5B8A6E4BDA0E78EA9E8BDACE9BB91E7A791E68A80EFBC81E5A4A9E784B6E69D90E8B4A8EFBC8CE58E9FE7949FE6B581E79585E7B3BBE7BB9FEFBC81, 'img/1/c_0005.jpg', '1', '1');
INSERT INTO `goods` VALUES ('6', '魅族 MX5 16GB 银黑色', '1899', '1799', 0xE9AD85E6978F204D5835203136474220E993B6E9BB91E889B220E7A7BBE58AA8E88194E9809AE58F8C3447E6898BE69CBA20E58F8CE58DA1E58F8CE5BE85E98081E58E9FE58E82E992A2E58C96E8869C2BE4BF9DE68AA4E5A3B32BE880B3E69CBAEFBC81352E35E88BB1E5AFB8E5A4A7E5B18FE5B995EFBC8C3347E8BF90E8A18CE58685E5AD98EFBC8C32303730E4B8872B353030E4B887E5838FE7B4A0E69184E5838FE5A4B4EFBC81E995BFE69C9FE79C81E6898DE698AFE79C9FE79A84E79C81EFBC81, 'img/1/c_0006.jpg', '1', '1');
INSERT INTO `goods` VALUES ('7', '三星 Galaxy On7', '1499', '1398', 0xE4B889E6989F2047616C617879204F6E37EFBC884736303030EFBC89E69882E5B08FE4B88320E98791E889B220E585A8E7BD91E9809A3447E6898BE69CBA20E58F8CE58DA1E58F8CE5BE85E696B0E59381E781ABE78886E68AA2E8B4ADE4B8ADEFBC81E4BAACE4B89CE5B08AE4BAABE58D83E58583E889AFE69CBAEFBC81352E35E88BB1E5AFB8E9AB98E6B885E5A4A7E5B18FEFBC81313330302B35303057E5838FE7B4A0EFBC81E8AF84E4BBB7E8B5A23330E58583E8AF9DE8B4B9E588B8EFBC81, 'img/1/c_0007.jpg', '0', '1');
INSERT INTO `goods` VALUES ('8', 'NUU NU5', '1288', '1190', 0x4E5555204E5535203136474220E7A7BBE58AA8E88194E9809AE58F8C3447E699BAE883BDE6898BE69CBA20E58F8CE58DA1E58F8CE5BE8520E69992E58D95E69C89E7A4BC20E699A8E58589E98791E9A699E6B8AFE59381E7898C20322E3544E5BCA7E5BAA6E5898DE5908EE992A2E58C96E78EBBE7928320E99A8FE69CBAE99984E8B5A0E6898BE69CBAE5A5972BE992A2E58C96E8B4B4E8869C20E69992E58D95E98081E7A7BBE58AA8E794B5E6BA902BE8939DE78999E880B3E69CBA, 'img/1/c_0008.jpg', '0', '1');
INSERT INTO `goods` VALUES ('9', '乐视（Letv）乐1pro（X800）', '2399', '2299', 0xE4B990E8A786EFBC884C657476EFBC89E4B9903170726FEFBC8858383030EFBC893634474220E98791E889B220E7A7BBE58AA8E88194E9809A3447E6898BE69CBA20E58F8CE58DA1E58F8CE5BE85E4B990E8A786E7949FE6808155492B352E35E88BB1E5AFB8324BE5B18F2BE9AB98E9809A38E6A0B8E5A484E79086E599A82B344742E8BF90E8A18CE58685E5AD982B36344742E5AD98E582A82B31333030E4B887E69184E5838FE5A4B4EFBC81, 'img/1/c_0009.jpg', '1', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '系统管理', null, '0');
INSERT INTO `sys_permission` VALUES ('2', '基础数据', null, '0');
INSERT INTO `sys_permission` VALUES ('3', '用户模块', '/user/findAll', '1');
INSERT INTO `sys_permission` VALUES ('4', '角色模块', '/role/findAll', '1');
INSERT INTO `sys_permission` VALUES ('5', '商品管理', '/goods/findAll', '2');
INSERT INTO `sys_permission` VALUES ('7', '权限管理', '/permission/findAll', '2');
INSERT INTO `sys_permission` VALUES ('8', '商品浏览', '/goods/findAll', '0');
INSERT INTO `sys_permission` VALUES ('9', '购物车浏览', '/cart/findAll', '0');
INSERT INTO `sys_permission` VALUES ('10', '日志管理', '', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'USER', '用户');
INSERT INTO `sys_role` VALUES ('3', 'VISITOR', '游客');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permissionId` bigint(20) NOT NULL,
  `roleId` int(20) NOT NULL,
  PRIMARY KEY (`permissionId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '1');
INSERT INTO `sys_role_permission` VALUES ('8', '2');
INSERT INTO `sys_role_permission` VALUES ('9', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'admin', '1410289853@qq.com', '$2a$10$fm..z0JduHD1Et8Ej7qno.sywhcyjSItj.RdmP42AX8F8KEFj4mpe', '1');
INSERT INTO `sys_user` VALUES ('6', 'cqcq', '1410289853@qq.com', '$2a$10$SFDGS9gPjUGzElF.bgySbOYWKkzGidx0q2KZGsk5IcYj2kE5u1o86', '1');
INSERT INTO `sys_user` VALUES ('11', 'cq', '1410289853@qq.com', '$2a$10$e1LPNT3ixA1EZTFeUPcqIeR/OgewNWTZ8vjV7KrgvmD8hmUTmY8YS', '1');
INSERT INTO `sys_user` VALUES ('18', 'zhangsan', '1410289853@qq.com', '$2a$10$wEPDceaE5BuvV4/lVz206.0mvxu1D3RgSMP7nGoqINOiEEjdXm6Qi', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` int(20) NOT NULL,
  `roleId` int(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `roleId` (`roleId`) USING BTREE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('5', '1');
INSERT INTO `sys_user_role` VALUES ('6', '1');
INSERT INTO `sys_user_role` VALUES ('6', '2');
INSERT INTO `sys_user_role` VALUES ('11', '2');
INSERT INTO `sys_user_role` VALUES ('18', '2');
