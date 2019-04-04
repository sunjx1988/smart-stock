/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : stock

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-04-04 16:29:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fund
-- ----------------------------
DROP TABLE IF EXISTS `fund`;
CREATE TABLE `fund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `banlance` decimal(10,3) NOT NULL COMMENT '现金余额',
  `principal` decimal(10,3) NOT NULL COMMENT '总投资额(本金)',
  `position` decimal(10,3) NOT NULL COMMENT '仓位(百分数)',
  `total_unit` int(8) NOT NULL COMMENT '总份额',
  `num_of_trustee` int(3) NOT NULL COMMENT '信托人数',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='基金';

-- ----------------------------
-- Records of fund
-- ----------------------------
INSERT INTO `fund` VALUES ('1', '企业号', '116921.000', '116921.000', '0.000', '116921', '0', '2019-03-01 00:00:00', '2019-04-04 15:57:11');

-- ----------------------------
-- Table structure for fund_by_day
-- ----------------------------
DROP TABLE IF EXISTS `fund_by_day`;
CREATE TABLE `fund_by_day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fund_id` bigint(20) NOT NULL,
  `date` varchar(20) NOT NULL COMMENT '日期',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `banlance` decimal(10,3) NOT NULL COMMENT '现金余额',
  `principal` decimal(10,3) NOT NULL COMMENT '总投资额(本金)',
  `position` decimal(10,3) NOT NULL COMMENT '仓位(百分数)',
  `total_unit` int(8) NOT NULL COMMENT '份额',
  `num_of_trustee` int(3) NOT NULL COMMENT '信托人数',
  `market_value` decimal(10,3) NOT NULL COMMENT '股票市值',
  `total` decimal(10,3) NOT NULL COMMENT '总资产',
  `net_unit_value` decimal(6,3) NOT NULL COMMENT '净值',
  `income` decimal(10,3) NOT NULL COMMENT '总收益',
  `rate_of_return` decimal(7,3) NOT NULL COMMENT '收益率',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='基金每日报表';


-- ----------------------------
-- Table structure for fund_stock
-- ----------------------------
DROP TABLE IF EXISTS `fund_stock`;
CREATE TABLE `fund_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `market_type` int(1) NOT NULL COMMENT '股票市场 0上证 1深证',
  `status` int(1) NOT NULL COMMENT '持有状态 0持有 1不持有',
  `fund_id` bigint(20) NOT NULL,
  `fund_name` varchar(255) NOT NULL COMMENT '基金名',
  `unit` int(8) NOT NULL COMMENT '数量',
  `unit_price` decimal(10,3) NOT NULL COMMENT '成本价',
  `total` decimal(10,3) NOT NULL COMMENT '总成本',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='基金股票份额';

-- ----------------------------
-- Records of fund_stock
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '权限名',
  `code` varchar(50) NOT NULL COMMENT '代码',
  `url` varchar(255) DEFAULT NULL COMMENT '资源链接',
  `status` int(1) NOT NULL COMMENT '状态 0启用 1禁用',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `code` varchar(20) NOT NULL COMMENT '代码',
  `status` int(1) NOT NULL COMMENT '状态 0启用 1禁用',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', 'admin', '0', '2019-03-08 22:06:53');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='股票';

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('1', '浦发银行', '600000', '2019-04-04 15:48:46');
INSERT INTO `stock` VALUES ('2', '煌上煌', '002695', '2019-04-04 15:49:07');
INSERT INTO `stock` VALUES ('3', '利尔化学', '002258', '2019-04-04 15:49:23');
INSERT INTO `stock` VALUES ('4', '华鲁恒升', '600426', '2019-04-04 15:49:23');
INSERT INTO `stock` VALUES ('5', '双汇发展', '000895', '2019-04-04 15:49:23');

-- ----------------------------
-- Table structure for stock_finance
-- ----------------------------
DROP TABLE IF EXISTS `stock_finance`;
CREATE TABLE `stock_finance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `date` varchar(10) NOT NULL COMMENT '日期',
  `date_type` int(1) NOT NULL COMMENT '1 第一季报表 2 半年报表 3 前三季报表 4 全年报表',
  `type` varchar(10) NOT NULL COMMENT 'ZXCWZB 最新财务指标, CWBL 财务比率, ZCFZ 资产负债, LR 利润表, XJLL 现金流量, ZYSRFB 主营收入, ZCJZ 资产减值, YSZK 应收账款, QTYSZK 其他应收账款',
  `info` varchar(2000) DEFAULT NULL COMMENT 'JSON',
  `info_version` int(2) DEFAULT NULL COMMENT 'JSON数据版本',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票财务信息';

-- ----------------------------
-- Records of stock_finance
-- ----------------------------

-- ----------------------------
-- Table structure for stock_fix
-- ----------------------------
DROP TABLE IF EXISTS `stock_fix`;
CREATE TABLE `stock_fix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `unit` int(8) DEFAULT NULL COMMENT '每10股派股数量',
  `cash` decimal(10,3) DEFAULT NULL COMMENT '每10股派发现金',
  `reason` varchar(255) NOT NULL COMMENT '调整原因',
  `date` varchar(20) NOT NULL COMMENT '除权日期',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票分红';

-- ----------------------------
-- Records of stock_fix
-- ----------------------------

-- ----------------------------
-- Table structure for stock_price
-- ----------------------------
DROP TABLE IF EXISTS `stock_price`;
CREATE TABLE `stock_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `price` decimal(10,3) DEFAULT NULL COMMENT '收盘价',
  `date` varchar(20) NOT NULL COMMENT '日期',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='股票收盘记录';

-- ----------------------------
-- Records of stock_price
-- ----------------------------

-- ----------------------------
-- Table structure for stock_trade
-- ----------------------------
DROP TABLE IF EXISTS `stock_trade`;
CREATE TABLE `stock_trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL COMMENT '股票名称',
  `code` varchar(6) NOT NULL COMMENT '股票代码',
  `fund_id` bigint(20) NOT NULL,
  `fund_name` varchar(255) NOT NULL COMMENT '基金名',
  `unit` int(8) NOT NULL COMMENT '数量',
  `unit_price` decimal(10,3) NOT NULL COMMENT '成交价',
  `total` decimal(10,3) NOT NULL COMMENT '总成交额',
  `type` int(1) NOT NULL COMMENT '交易类型 0买入 1卖出',
  `date` varchar(20) NOT NULL COMMENT '日期',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='股票交易记录';

-- ----------------------------
-- Records of stock_trade
-- ----------------------------

-- ----------------------------
-- Table structure for trustee
-- ----------------------------
DROP TABLE IF EXISTS `trustee`;
CREATE TABLE `trustee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT 'E-mail',
  `login_pwd` varchar(255) NOT NULL COMMENT '密码',
  `login_salt` varchar(255) NOT NULL COMMENT '加密盐',
  `status` int(1) NOT NULL COMMENT '持有状态 0持有 1不持有',
  `principal` decimal(10,3) NOT NULL COMMENT '总投资额(本金)',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='信托人表';

-- ----------------------------
-- Records of trustee
-- ----------------------------
INSERT INTO `trustee` VALUES ('1', 'sun', '100', 'sunjx1988@163.com', '0cLUnxHdkemzwUwhCmSYhW4J4hey+mgrFW4onKmMbcw=', 'GdrucpP6szbow28+aFOXSw==', '0', '0.000', '2019-03-01 00:00:00');
INSERT INTO `trustee` VALUES ('2', '陈龙', '123', '123', 'SB1R4krej3GJIrlUwF2/RGyC04yZxXHkkDsm2xddkxM=', 'riXCHcHyoIh/ECcQijk3jg==', '0', '0.000', '2019-03-01 00:00:00');
INSERT INTO `trustee` VALUES ('3', '王震', '1231', '123', 'ZeteZW2aSJhJUirjjrDgEEGQLhFi9W7kVzg9s6ENjVY=', 'X22KZxoIIZnGX9mOBW4OOw==', '0', '0.000', '2019-03-01 00:00:00');
INSERT INTO `trustee` VALUES ('4', '刘威', '1234', '123', 'jwdZEINZXCPcpeBHYTILWf5SCBOjBLH5rNuKUsTnbBM=', 'o0q9/zvhGhCAOK8If1U2qw==', '0', '0.000', '2019-03-01 00:00:00');
INSERT INTO `trustee` VALUES ('5', '朱深辉', '12345', '123', '0/I0lcLJgRxLbiOFtOeY8pWt6TaxX+n0IoVDfOHAs0I=', '8Mc7C8bwhRGMur3QVdkWVQ==', '0', '0.000', '2019-03-01 00:00:00');

-- ----------------------------
-- Table structure for trustee_by_day
-- ----------------------------
DROP TABLE IF EXISTS `trustee_by_day`;
CREATE TABLE `trustee_by_day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trustee_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `fund_id` bigint(20) NOT NULL,
  `fund_name` varchar(255) NOT NULL COMMENT '基金名',
  `date` varchar(20) NOT NULL COMMENT '日期',
  `principal` decimal(10,3) NOT NULL COMMENT '投资额(本金)',
  `total_unit` int(8) NOT NULL COMMENT '份额',
  `net_unit_value` decimal(7,3) NOT NULL COMMENT '净值',
  `total` decimal(10,3) NOT NULL COMMENT '总资产',
  `income` decimal(10,3) NOT NULL COMMENT '收益',
  `rate_of_return` decimal(7,3) NOT NULL COMMENT '收益率',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='信托人日报表';

-- ----------------------------
-- Records of trustee_by_day
-- ----------------------------

-- ----------------------------
-- Table structure for trustee_role
-- ----------------------------
DROP TABLE IF EXISTS `trustee_role`;
CREATE TABLE `trustee_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trustee_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of trustee_role
-- ----------------------------
INSERT INTO `trustee_role` VALUES ('1', '1', '1', '2019-03-08 22:07:03');

-- ----------------------------
-- Table structure for trustee_trade
-- ----------------------------
DROP TABLE IF EXISTS `trustee_trade`;
CREATE TABLE `trustee_trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trustee_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `status` int(1) NOT NULL COMMENT '状态 0确认中 1已确认 2已赎回',
  `fund_id` bigint(20) NOT NULL,
  `fund_name` varchar(255) NOT NULL COMMENT '基金名',
  `start_date` datetime DEFAULT NULL COMMENT '日期',
  `end_date` datetime DEFAULT NULL COMMENT '到期日期',
  `cycle` int(1) NOT NULL COMMENT '持有周期',
  `unit` int(8) NOT NULL COMMENT '交易份额',
  `unit_price` decimal(10,3) NOT NULL COMMENT '认购价',
  `total` decimal(10,3) NOT NULL COMMENT '认购额',
  `sale_unit_price` decimal(10,3) DEFAULT NULL COMMENT '赎回价',
  `sale_total` decimal(10,3) DEFAULT NULL COMMENT '赎回额',
  `interest_rate` int(10) NOT NULL COMMENT '年化利率',
  `income` decimal(10,3) DEFAULT NULL COMMENT '赎回后盈利',
  `income_rate` decimal(10,3) DEFAULT NULL COMMENT '赎回后盈利率',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='信托人交易记录';

-- ----------------------------
-- Records of trustee_trade
-- ----------------------------
INSERT INTO `trustee_trade` VALUES ('1', '1', 'sun', '0', '1', '企业号', '2019-03-01 00:00:00', '2020-03-02 00:00:00', '5', '46000', '1.000', '46000.000', null, null, '0', null, null, '2019-03-01 00:00:00');
INSERT INTO `trustee_trade` VALUES ('2', '4', '刘威', '0', '1', '企业号', '2019-03-01 00:00:00', '2020-03-02 00:00:00', '1', '10000', '1.000', '10000.000', null, null, '5', null, null, '2019-03-01 00:00:00');
INSERT INTO `trustee_trade` VALUES ('3', '5', '朱深辉', '0', '1', '企业号', '2019-03-01 00:00:00', '2020-03-02 00:00:00', '1', '10000', '1.000', '10000.000', null, null, '5', null, null, '2019-03-01 00:00:00');
INSERT INTO `trustee_trade` VALUES ('4', '3', '王震', '0', '1', '企业号', '2019-03-01 00:00:00', '2020-03-02 00:00:00', '1', '10000', '1.000', '10000.000', null, null, '5', null, null, '2019-03-01 00:00:00');
INSERT INTO `trustee_trade` VALUES ('5', '2', '陈龙', '0', '1', '企业号', '2019-03-01 00:00:00', '2020-03-02 00:00:00', '1', '10000', '1.000', '10000.000', null, null, '5', null, null, '2019-03-01 00:00:00');
INSERT INTO `trustee_trade` VALUES ('6', '1', 'sun', '0', '1', '企业号', '2019-03-25 00:00:00', '2020-03-26 00:00:00', '5', '30921', '1.000', '30921.000', null, null, '0', null, null, '2019-03-25 00:00:00');
