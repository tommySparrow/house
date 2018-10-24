CREATE TABLE `community` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `city_code` varchar(11) NOT NULL DEFAULT '' COMMENT '城市编码',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '小区名称',
  `city_name` varchar(11) NOT NULL DEFAULT '' COMMENT '城市名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;