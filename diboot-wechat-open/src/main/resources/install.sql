-- 微信开放平台公众号授权表，用于存放公众号扫码授权信息
-- DROP TABLE IF EXISTS `wx_auth_open`;
CREATE TABLE `wx_auth_open` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `rel_obj_type` varchar(100) DEFAULT NULL COMMENT '关联类型',
  `rel_obj_id` bigint(18) unsigned DEFAULT NULL COMMENT '关联数据',
  `auth_type` smallint(5) unsigned DEFAULT '1' COMMENT '授权类型',
  `appid` varchar(50) NOT NULL COMMENT 'appId',
  `nickname` varchar(255) DEFAULT NULL COMMENT '名称',
  `principal_name` varchar(255) DEFAULT NULL COMMENT '主体名称',
  `head_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `original_id` varchar(32) DEFAULT NULL COMMENT '原始ID',
  `service_type_info` smallint(5) unsigned DEFAULT NULL COMMENT '账号类型',
  `verify_type_info` smallint(5) DEFAULT NULL COMMENT '认证类型',
  `qrcode_url` varchar(255) DEFAULT NULL COMMENT '二维码',
  `wechat_alias` varchar(50) DEFAULT NULL COMMENT '所设置公众号',
  `authorizer_refresh_token` varchar(64) DEFAULT NULL COMMENT '刷新token',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '可用',
  `extdata` varchar(100) DEFAULT NULL COMMENT '扩展JSON',
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_by` bigint(18) DEFAULT '0' COMMENT '创建人ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000024 DEFAULT CHARSET=utf8 COMMENT='服务号授权';


-- 微信开发平台系统参数存储表，存储运行时与微信开放平台相关的系统参数，以便于在多个应用之间同步
-- DROP TABLE IF EXISTS `wx_config_storage`;
CREATE TABLE `wx_config_storage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `extdata` varchar(2048) DEFAULT NULL COMMENT '扩展JSON',
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_by` bigint(18) DEFAULT '0' COMMENT '创建人ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000004 DEFAULT CHARSET=utf8 COMMENT='微信参数';

-- 公众号会员表，用于记录使用该应用的各公众号的用户及会员信息
-- DROP TABLE IF EXISTS `wx_member`;
CREATE TABLE `wx_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `org_id` bigint(18) unsigned DEFAULT NULL COMMENT '单位',
  `org_customer_id` bigint(18) unsigned DEFAULT NULL COMMENT '客户',
  `wx_type` varchar(20) NOT NULL COMMENT '微信类型',
  `wx_auth_open_id` bigint(18) unsigned DEFAULT NULL COMMENT '微信认证',
  `openid` varchar(32) NOT NULL COMMENT 'openId',
  `unionid` varchar(32) DEFAULT NULL COMMENT 'unionId',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(1) DEFAULT NULL COMMENT '性别',
  `language` varchar(10) DEFAULT NULL COMMENT '语言',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `subscribeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  `remark` varchar(255) DEFAULT NULL COMMENT 'remark',
  `groupId` smallint(5) unsigned DEFAULT NULL COMMENT '分组',
  `tagIds` varchar(255) DEFAULT NULL COMMENT '标签',
  `subscribeScene` varchar(50) DEFAULT NULL COMMENT '关注方式',
  `qrScene` varchar(100) DEFAULT NULL COMMENT '二维码扫码场景',
  `qrSceneStr` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  `extdata` varchar(100) DEFAULT NULL COMMENT '扩展JSON',
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_by` bigint(18) DEFAULT '0' COMMENT '创建人ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_wx_member_openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000083 DEFAULT CHARSET=utf8 COMMENT='服务号用户';