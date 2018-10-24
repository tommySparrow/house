CREATE TABLE `house_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `house_id` bigint(20) NOT NULL COMMENT '房屋id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `type` tinyint(1) NOT NULL COMMENT '1-售卖，2-收藏',
  PRIMARY KEY (`id`),
  UNIQUE KEY `house_id_user_id_type` (`house_id`,`user_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;