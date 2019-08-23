DROP DATABASE IF EXISTS friend;
CREATE DATABASE friend CHARACTER SET utf8 COLLATE utf8_general_ci;
USE friend;

CREATE TABLE `meta` (
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)    NOT NULL,
    type VARCHAR(45)     NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP table IF EXISTS user;
CREATE TABLE `user` (
  `uid` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

--         id:与user表uid对应
--         sex：性别
--         birthday: "0000-00-00",
--         constellation: "",//星座
--         login: "",//登录次数
--         signature: "",//个性签名
--         pos_province: "",//所在省份
--         pos_city: "", //所在城市
--         pos_district: "",//所在乡镇
--         fans: "",
--         avatar: "",//头像
--         school: "", //学校
--         major: "",//专业
--         enrollment_year: "0000",//入学年份
--         industry: "",//行业
CREATE TABLE `user_info`(
  `id` int(11) PRIMARY KEY NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `crowd` int(11) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `constellation` varchar(20) DEFAULT NULL,
  `login` int(10) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `pos_province` varchar(10) DEFAULT NULL,
  `pos_city` varchar(10) DEFAULT NULL,
  `pos_district` varchar(10) DEFAULT NULL,
  `fans` int (10) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `school` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `enrollment_year` varchar(20) DEFAULT NULL,
  `industry` varchar(20) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO `user` (`mobile`,`password`, `perms`) VALUES ('13877778888','Aa123456','user:one');
INSERT INTO `user_info` (`id`,`crowd`,`nickname`) VALUES ('1','5','嗷嗷');
