-- 用户表
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (255) NOT NULL COMMENT '姓名',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 基金信息
-- 现金余额

-- 用户交易记录
drop table if exists `t_trade_record`;

-- 基金股票份额

-- 股票收盘记录
