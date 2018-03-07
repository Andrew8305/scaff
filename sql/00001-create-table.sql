
-- 商户信息表
DROP TABLE IF EXISTS plana.merchants;
CREATE TABLE plana.merchants (
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  merchantUuid varchar(50) NOT NULL COMMENT '商户唯一uuid',
  merchantAccountUuid varchar(50) NOT NULL COMMENT '商户账户id',
  merchantName varchar(50) NOT NULL COMMENT '商户名称',
  merchantKeyId varchar(50) NOT NULL COMMENT '商户公私钥id',
  merchantServicesKey varchar(50) NOT NULL COMMENT '商户服务配置id',
  createTime TIMESTAMP DEFAULT now() NOT NULL,
  updateTime TIMESTAMP DEFAULT now() NOT NULL,
  CONSTRAINT merchants_PK PRIMARY KEY (id),
  CONSTRAINT merchants_UN UNIQUE KEY (merchantUuid)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8
  COLLATE=utf8_general_ci
  COMMENT='商户表' ;

-- 商户账户信息表
DROP TABLE IF EXISTS plana.merchantAccount;
CREATE TABLE plana.merchantAccount (
  id BIGINT NOT NULL AUTO_INCREMENT,
  accountUuid varchar(50) NOT NULL COMMENT '商户账户uuid',
  accountBalance BIGINT DEFAULT 0 NOT NULL COMMENT '账户余额',
  createTime TIMESTAMP DEFAULT now() NOT NULL,
  updateTime TIMESTAMP DEFAULT now() NOT NULL,
  CONSTRAINT merchant_account_PK PRIMARY KEY (id),
  CONSTRAINT merchant_account_UN UNIQUE KEY (accountUuid)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8
  COLLATE=utf8_general_ci
  COMMENT='商户账户信息表' ;

-- 服务信息表
DROP TABLE IF EXISTS plana.services;
CREATE TABLE plana.services (
  id BIGINT NOT NULL AUTO_INCREMENT,
  serviceUuid varchar(50) NOT NULL COMMENT '服务uuid',
  serviceName varchar(50) NOT NULL COMMENT '服务名称',
  servicePrice BIGINT DEFAULT 0 NOT NULL COMMENT '服务单价',
  createTime TIMESTAMP DEFAULT now() NOT NULL,
  updateTime TIMESTAMP DEFAULT now() NOT NULL,
  CONSTRAINT plana_services_PK PRIMARY KEY (id),
  CONSTRAINT plana_services_UN UNIQUE KEY (serviceUuid)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8
  COLLATE=utf8_general_ci
  COMMENT='服务信息表' ;
