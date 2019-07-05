DROP DATABASE IF EXISTS friend;
CREATE DATABASE friend CHARACTER SET utf8 COLLATE utf8_general_ci;
USE friend;

CREATE TABLE `meta` (
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)    NOT NULL,
    type VARCHAR(45)     NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/*INSERT INTO `user` (`name`, `password`, `lastLoginTime`) VALUES ('test', 'test', NOW());*/