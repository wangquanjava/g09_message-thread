/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : g03_audioplayer

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-08-25 18:35:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for playlist
-- ----------------------------
DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `music_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playlist
-- ----------------------------
INSERT INTO `playlist` VALUES ('1', '1.mp3');
INSERT INTO `playlist` VALUES ('2', 'Kalimba.mp3');
INSERT INTO `playlist` VALUES ('3', 'Maid with the Flaxen Hair.mp3');
INSERT INTO `playlist` VALUES ('4', 'Sleep Away.mp3');
INSERT INTO `playlist` VALUES ('5', '1 - 副本.mp3');
INSERT INTO `playlist` VALUES ('6', '赵咏华 - 最浪漫的事.mp3');
INSERT INTO `playlist` VALUES ('7', '赵咏华 - 最浪漫的事 - 副本.mp3');
