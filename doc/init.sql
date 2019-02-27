-- 用户表
drop table if exists `t_user`;

create table `t_user`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 基金信息
-- 现金余额

-- 用户交易记录
drop table if exists `t_trade_record`;

-- 基金股票份额

-- 股票收盘记录
