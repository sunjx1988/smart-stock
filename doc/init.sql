-- 信托人表
DROP TABLE IF EXISTS `trustee`;

CREATE TABLE `trustee` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (255) NOT NULL COMMENT '姓名',
	`phone` VARCHAR (255) NOT NULL COMMENT '手机号',
	`login_pwd` VARCHAR (255) NOT NULL COMMENT '密码',
	`login_salt` VARCHAR (255) NOT NULL COMMENT '加密盐',
	`status` INT (1) NOT NULL COMMENT '持有状态 0持有 1不持有',
	`principal` DECIMAL (10, 2) NOT NULL COMMENT '总投资额(本金)',
	`total_unit` INT (8) NOT NULL COMMENT '份额',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总资产',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '信托人表';

-- 信托人每日报表
DROP TABLE IF EXISTS `trustee_by_day`;

CREATE TABLE `trustee_by_day` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`trustee_id` BIGINT (20) NOT NULL,
	`date` VARCHAR (20) NOT NULL COMMENT '日期',
	`name` VARCHAR (255) NOT NULL COMMENT '姓名',
	`principal` DECIMAL (10, 2) NOT NULL COMMENT '总投资额(本金)',
	`total_unit` INT (8) NOT NULL COMMENT '份额',
	`net_unit_value` DECIMAL (5, 2) NOT NULL COMMENT '净值',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总资产',
	`income` DECIMAL (10, 2) NOT NULL COMMENT '收益',
	`rate_of_return` DECIMAL (6, 2) NOT NULL COMMENT '收益率',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '信托人日报表';

-- 基金信息
DROP TABLE IF EXISTS `fund`;

CREATE TABLE `fund` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (255) NOT NULL COMMENT '名称',
	`banlance` DECIMAL (10, 2) NOT NULL COMMENT '现金余额',
	`principal` DECIMAL (10, 2) NOT NULL COMMENT '总投资额(本金)',
	`position` DECIMAL (10, 2) NOT NULL COMMENT '仓位(百分数)',
	`total_unit` INT (8) NOT NULL COMMENT '总份额',
	`num_of_trustee` INT (3) NOT NULL COMMENT '信托人数',
	`create_time` TIMESTAMP NOT NULL,
	`update_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '基金';

-- 基金每日报表
DROP TABLE IF EXISTS `fund_by_day`;

CREATE TABLE `fund_by_day` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`fund_id` BIGINT (20) NOT NULL,
	`date` VARCHAR (20) NOT NULL COMMENT '日期',
	`name` VARCHAR (255) NOT NULL COMMENT '名称',
	`banlance` DECIMAL (10, 2) NOT NULL COMMENT '现金余额',
	`principal` DECIMAL (10, 2) NOT NULL COMMENT '总投资额(本金)',
	`position` DECIMAL (10, 2) NOT NULL COMMENT '仓位(百分数)',
	`total_unit` INT (8) NOT NULL COMMENT '份额',
	`num_of_trustee` INT (3) NOT NULL COMMENT '信托人数',
	`market_value` DECIMAL (10, 2) NOT NULL COMMENT '股票市值',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总资产',
	`net_unit_value` DECIMAL (5, 2) NOT NULL COMMENT '净值',
	`income` DECIMAL (10, 2) NOT NULL COMMENT '总收益',
	`rate_of_return` DECIMAL (6, 2) NOT NULL COMMENT '收益率',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '基金每日报表';

-- 信托人交易记录
DROP TABLE IF EXISTS `trustee_trade`;

CREATE TABLE `trustee_trade` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`trustee_id` BIGINT (20) NOT NULL,
	`name` VARCHAR (255) NOT NULL COMMENT '姓名',
	`fund_id` BIGINT (20) NOT NULL,
	`fund_name` VARCHAR (255) NOT NULL COMMENT '基金名',
	`start_date` TIMESTAMP NOT NULL COMMENT '日期',
	`end_date` TIMESTAMP NOT NULL COMMENT '到期日期',
	`unit` INT (8) NOT NULL COMMENT '交易份额',
	`unit_price` DECIMAL (10, 2) NOT NULL COMMENT '交易价',
	`type` INT (1) NOT NULL COMMENT '交易类型 0买入 1卖出',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总金额',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '信托人交易记录';

-- 基金股票份额
DROP TABLE IF EXISTS `fund_stock`;

CREATE TABLE `fund_stock` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (6) NOT NULL COMMENT '股票名称',
	`code` VARCHAR (6) NOT NULL COMMENT '股票代码',
	`market_type` INT (1) NOT NULL COMMENT '股票市场 0上证 1深证',
	`status` INT (1) NOT NULL COMMENT '持有状态 0持有 1不持有',
	`fund_id` BIGINT (20) NOT NULL,
	`fund_name` VARCHAR (255) NOT NULL COMMENT '基金名',
	`unit` INT (8) NOT NULL COMMENT '数量',
	`unit_price` DECIMAL (10, 2) NOT NULL COMMENT '成本价',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总成本',
	`now_unit_price` DECIMAL (10, 2) NOT NULL COMMENT '最新价',
	`now_total` DECIMAL (10, 2) NOT NULL COMMENT '最新市值',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '基金股票份额';

-- 股票交易记录
DROP TABLE IF EXISTS `stock_trade`;

CREATE TABLE `stock_trade` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (6) NOT NULL COMMENT '股票名称',
	`code` VARCHAR (6) NOT NULL COMMENT '股票代码',
	`fund_id` BIGINT (20) NOT NULL,
	`fund_name` VARCHAR (255) NOT NULL COMMENT '基金名',
	`unit` INT (8) NOT NULL COMMENT '数量',
	`unit_price` DECIMAL (10, 2) NOT NULL COMMENT '成交价',
	`total` DECIMAL (10, 2) NOT NULL COMMENT '总成交额',
	`type` INT (1) NOT NULL COMMENT '交易类型 0买入 1卖出',
	`date` VARCHAR (20) NOT NULL COMMENT '日期',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '股票交易记录';

-- 股票分红
DROP TABLE IF EXISTS `stock_fix`;

CREATE TABLE `stock_fix` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (6) NOT NULL COMMENT '股票名称',
	`code` VARCHAR (6) NOT NULL COMMENT '股票代码',
	`unit` INT (8) NULL COMMENT '每10股派股数量',
	`cash` DECIMAL (10, 2) NULL COMMENT '每10股派发现金',
	`reason` VARCHAR (255) NOT NULL COMMENT '调整原因',
	`date` VARCHAR (20) NOT NULL COMMENT '除权日期',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '股票分红';

-- 股票收盘记录
DROP TABLE IF EXISTS `stock_price`;

CREATE TABLE `stock_price` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (6) NOT NULL COMMENT '股票名称',
	`code` VARCHAR (6) NOT NULL COMMENT '股票代码',
	`price` DECIMAL (10, 2) NULL COMMENT '收盘价',
	`date` VARCHAR (20) NOT NULL COMMENT '日期',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '股票收盘记录';

-- 角色
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (20) NOT NULL COMMENT '角色名',
	`code` VARCHAR (20) NOT NULL COMMENT '代码',
	`status` INT(1) NOT NULL COMMENT '状态 0启用 1禁用',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '角色';

-- 权限
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (20) NOT NULL COMMENT '权限名',
	`code` VARCHAR (50) NOT NULL COMMENT '代码',
	`url`  VARCHAR (255) NULL COMMENT '资源链接',
	`status` INT(1) NOT NULL COMMENT '状态 0启用 1禁用',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '权限';

-- 用户角色表
DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `trustee_role` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`trustee_id` BIGINT (20) NOT NULL COMMENT '用户ID',
	`role_id` BIGINT (20) NOT NULL COMMENT '角色ID',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '用户角色表';

-- 角色权限表
DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`permission_id` BIGINT (20) NOT NULL COMMENT '权限ID',
	`role_id` BIGINT (20) NOT NULL COMMENT '角色ID',
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '角色权限表';


-- 测试数据 phone:100 pwd:000
INSERT INTO `role` VALUES ('1', '系统管理员', 'admin', '0', '2019-03-08 22:06:53');
INSERT INTO `trustee_role` VALUES ('1', '1', '1', '2019-03-08 22:07:03');
INSERT INTO `trustee` VALUES ('1', 'sun', '100', '0cLUnxHdkemzwUwhCmSYhW4J4hey+mgrFW4onKmMbcw=', 'GdrucpP6szbow28+aFOXSw==', '1', '0.00', '0', '0.00', '2019-03-08 21:05:54');