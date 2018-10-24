CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '评论内容',
  `house_id` bigint(20) NOT NULL COMMENT '房屋id',
  `create_time` date NOT NULL COMMENT '发布时间戳',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `type` tinyint(1) NOT NULL COMMENT '类型1-房产评论，2-博客评论',
  `user_id` bigint(20) NOT NULL COMMENT '评论用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;