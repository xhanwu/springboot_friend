/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : friend

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-06-26 16:31:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `friend_weibo`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo`;
CREATE TABLE `friend_weibo` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '发布人ID',
  `source_id` bigint(20) UNSIGNED NULL COMMENT '源微博ID',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '微博内容',
  `image_url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片的url，有多张图片用“;”分隔url',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `status` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '数据状态',
  `is_top` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否置顶',
  `is_comment` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否开启评论',
  `support_count` int(11) UNSIGNED NULL DEFAULT '0' COMMENT '微博点赞数量',
  `comment_count` int(11) UNSIGNED NULL DEFAULT '0' COMMENT '微博评论数量',
  `weibo_type` varchar(255) NULL COMMENT '微博类型',
  `crowd_id` bigint(20) UNSIGNED NULL COMMENT '圈子id(发布圈子微博时需要)',
  `repost_count` int(11) UNSIGNED NULL COMMENT '微博转发数量',
  `from_device` varchar(40) NULL COMMENT '发送平台',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo
-- ----------------------------
/*
-- ----------------------------
-- Table structure for `friend_weibo_cache`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_cache`;
CREATE TABLE `friend_weibo_cache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weibo_id` int(11) NOT NULL,
  `groups` varchar(100) NOT NULL,
  `cache_html` text NOT NULL,
  `create_time` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `weibo_id` (`weibo_id`),
  KEY `groups` (`groups`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='微博html缓存表';

-- ----------------------------
-- Records of friend_weibo_cache
-- ----------------------------
*/
-- ----------------------------
-- Table structure for `friend_weibo_comment`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_comment`;
CREATE TABLE `friend_weibo_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `weibo_id` bigint(20) UNSIGNED NOT NULL COMMENT '被评论的微博ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论用户的ID',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论的内容',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论的时间',
  `commentreply_count` int(11) UNSIGNED NULL DEFAULT '0' COMMENT '评论回复数量',
  `support_count` int(11) UNSIGNED NULL DEFAULT '0' COMMENT '评论点赞数量',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_weibo_comment_reply`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_comment_reply`;
CREATE TABLE `friend_weibo_comment_reply` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '被评论的评论ID',
  `from_userid` bigint(20) UNSIGNED NOT NULL COMMENT '回复用户id',
  `to_userid` bigint(20) UNSIGNED NOT NULL COMMENT '目标用户id',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复内容',
  `comment_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论的时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo_comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_weibo_support`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_support`;
CREATE TABLE `friend_weibo_support`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `support_id` bigint(20) UNSIGNED NOT NULL COMMENT '被点赞的微博ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '点赞用户ID',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '点赞状态',
  `support_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo_support
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_comment_support`
-- ----------------------------
DROP TABLE IF EXISTS `friend_comment_support`;
CREATE TABLE `friend_comment_support`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `support_id` bigint(20) UNSIGNED NOT NULL COMMENT '被点赞的评论ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '点赞用户ID',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '点赞状态',
  `support_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_comment_support
-- ----------------------------

/*
-- ----------------------------
-- Table structure for `friend_weibo_top`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_top`;
CREATE TABLE `friend_weibo_top` (
  `weibo_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(64) NOT NULL COMMENT '置顶标题',
  `dead_time` int(11) NOT NULL COMMENT '过期日期',
  `type` varchar(32) NOT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` int(11) NOT NULL,
  PRIMARY KEY (`weibo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='置顶微博表';

-- ----------------------------
-- Records of friend_weibo_top
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_weibo_topic`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_topic`;
CREATE TABLE `friend_weibo_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '话题名',
  `logo` varchar(255) NOT NULL DEFAULT '/topicavatar.jpg' COMMENT '话题logo',
  `intro` varchar(255) NOT NULL COMMENT '导语',
  `qrcode` varchar(255) NOT NULL COMMENT '二维码',
  `uadmin` int(11) NOT NULL DEFAULT '0' COMMENT '话题管理   默认无',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读',
  `is_top` tinyint(4) NOT NULL,
  `weibo_num` int(11) NOT NULL DEFAULT '0' COMMENT '微博数',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo_topic
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_weibo_topic_follow`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_topic_follow`;
CREATE TABLE `friend_weibo_topic_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_weibo_topic_follow
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_weibo_topic_link`
-- ----------------------------
DROP TABLE IF EXISTS `friend_weibo_topic_link`;
CREATE TABLE `friend_weibo_topic_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weibo_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` int(11) NOT NULL,
  `is_top` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='话题、微博关联表';

-- ----------------------------
-- Records of friend_weibo_topic_link
-- ----------------------------
*/