/*
Navicat MySQL Data Transfer

Source Server         : study
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : tmall

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2021-05-10 15:22:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_property_category` (`cid`),
  CONSTRAINT `fk_property_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', '83', '品牌');
INSERT INTO `property` VALUES ('2', '83', '证书状态');
INSERT INTO `property` VALUES ('3', '83', '3C产品型号');
INSERT INTO `property` VALUES ('4', '83', '申请人名称');
INSERT INTO `property` VALUES ('5', '83', '证书编号');
INSERT INTO `property` VALUES ('6', '83', '分辨率');
INSERT INTO `property` VALUES ('7', '83', '型号');
INSERT INTO `property` VALUES ('8', '83', '制造商名称');
INSERT INTO `property` VALUES ('9', '83', '网络连接方式');
INSERT INTO `property` VALUES ('10', '83', '产品名称');
INSERT INTO `property` VALUES ('11', '83', '能效等级');
INSERT INTO `property` VALUES ('12', '83', '3D类型');
INSERT INTO `property` VALUES ('13', '83', '操作系统');
INSERT INTO `property` VALUES ('14', '82', '品牌');
INSERT INTO `property` VALUES ('15', '82', '冲水按键类型');
INSERT INTO `property` VALUES ('16', '82', '坐便器类型');
INSERT INTO `property` VALUES ('17', '82', '盖板是否缓冲');
INSERT INTO `property` VALUES ('18', '82', '型号');
INSERT INTO `property` VALUES ('19', '82', '坐便器冲水方式');
INSERT INTO `property` VALUES ('20', '82', '排水方式');
INSERT INTO `property` VALUES ('21', '82', '材质');
INSERT INTO `property` VALUES ('22', '82', '最小坑距');
INSERT INTO `property` VALUES ('23', '82', '承重');
INSERT INTO `property` VALUES ('24', '82', '颜色分类');
INSERT INTO `property` VALUES ('25', '82', '坐便冲水量');
INSERT INTO `property` VALUES ('26', '82', '同城服务');
INSERT INTO `property` VALUES ('27', '81', '风格');
INSERT INTO `property` VALUES ('28', '81', '城市');
INSERT INTO `property` VALUES ('29', '81', '面料饰面工艺');
INSERT INTO `property` VALUES ('30', '81', '是否带储物空间');
INSERT INTO `property` VALUES ('31', '81', '包装体积');
INSERT INTO `property` VALUES ('32', '81', '颜色分类');
INSERT INTO `property` VALUES ('33', '81', '附加功能');
INSERT INTO `property` VALUES ('34', '81', '是否可拆洗');
INSERT INTO `property` VALUES ('35', '81', '结构工艺');
INSERT INTO `property` VALUES ('36', '81', '设计元素');
INSERT INTO `property` VALUES ('37', '81', '几人坐');
INSERT INTO `property` VALUES ('38', '81', '图案');
INSERT INTO `property` VALUES ('39', '81', '是否组装');
INSERT INTO `property` VALUES ('40', '81', '面料');
INSERT INTO `property` VALUES ('41', '81', '可送货/安装');
INSERT INTO `property` VALUES ('42', '81', '木质结构工艺');
INSERT INTO `property` VALUES ('43', '81', '沙发组合形式');
INSERT INTO `property` VALUES ('44', '81', '产地');
INSERT INTO `property` VALUES ('45', '81', '是否可定制');
INSERT INTO `property` VALUES ('46', '81', '品牌');
INSERT INTO `property` VALUES ('47', '81', '适用对象');
INSERT INTO `property` VALUES ('48', '81', '材质');
INSERT INTO `property` VALUES ('49', '81', '款式定位');
INSERT INTO `property` VALUES ('50', '81', '填充物');
INSERT INTO `property` VALUES ('51', '81', '填充物硬度');
INSERT INTO `property` VALUES ('52', '81', '出租车是否可运输');
INSERT INTO `property` VALUES ('53', '81', '型号');
INSERT INTO `property` VALUES ('54', '81', '木质材质');
INSERT INTO `property` VALUES ('55', '81', '是否可预售');
INSERT INTO `property` VALUES ('56', '81', '安装说明详情');
INSERT INTO `property` VALUES ('57', '80', '品牌');
INSERT INTO `property` VALUES ('58', '80', '证书状态');
INSERT INTO `property` VALUES ('59', '80', '3C产品型号');
INSERT INTO `property` VALUES ('60', '80', '申请人名称');
INSERT INTO `property` VALUES ('61', '80', '证书编号');
INSERT INTO `property` VALUES ('62', '80', '型号');
INSERT INTO `property` VALUES ('63', '80', '控制方式');
INSERT INTO `property` VALUES ('64', '80', '制造商名称');
INSERT INTO `property` VALUES ('65', '80', '款式');
INSERT INTO `property` VALUES ('66', '80', '产品名称');
INSERT INTO `property` VALUES ('67', '80', '能效等级');
INSERT INTO `property` VALUES ('68', '79', '上市时间');
INSERT INTO `property` VALUES ('69', '79', '款式');
INSERT INTO `property` VALUES ('70', '79', '是否商场同款');
INSERT INTO `property` VALUES ('71', '79', '货号');
INSERT INTO `property` VALUES ('72', '79', '品牌');
INSERT INTO `property` VALUES ('73', '78', '液晶显示');
INSERT INTO `property` VALUES ('74', '78', '尘盒容量');
INSERT INTO `property` VALUES ('75', '78', '外观造型');
INSERT INTO `property` VALUES ('76', '78', '功能');
INSERT INTO `property` VALUES ('77', '78', '最高高度');
INSERT INTO `property` VALUES ('78', '78', '碰撞保护');
INSERT INTO `property` VALUES ('79', '78', '是否自动充电');
INSERT INTO `property` VALUES ('80', '78', '电池容量');
INSERT INTO `property` VALUES ('81', '78', '有无虚拟墙');
INSERT INTO `property` VALUES ('82', '78', '是否有定时预约功能');
INSERT INTO `property` VALUES ('83', '78', '货号');
INSERT INTO `property` VALUES ('84', '78', '颜色分类');
INSERT INTO `property` VALUES ('85', '78', '清扫路线');
INSERT INTO `property` VALUES ('86', '78', '是否带遥控器');
INSERT INTO `property` VALUES ('87', '77', '品牌');
INSERT INTO `property` VALUES ('88', '77', '转速');
INSERT INTO `property` VALUES ('89', '77', '证书状态');
INSERT INTO `property` VALUES ('90', '77', '3C产品型号');
INSERT INTO `property` VALUES ('91', '77', '申请人名称');
INSERT INTO `property` VALUES ('92', '77', '额定功率');
INSERT INTO `property` VALUES ('93', '77', '证书编号');
INSERT INTO `property` VALUES ('94', '77', '型号');
INSERT INTO `property` VALUES ('95', '77', '制造商名称');
INSERT INTO `property` VALUES ('96', '77', '附加功能');
INSERT INTO `property` VALUES ('97', '77', '采购地');
INSERT INTO `property` VALUES ('98', '77', '机身材质');
INSERT INTO `property` VALUES ('99', '77', '颜色分类');
INSERT INTO `property` VALUES ('100', '77', '产品名称');
INSERT INTO `property` VALUES ('101', '77', '操作方式');
INSERT INTO `property` VALUES ('102', '76', '冰箱冷柜机型');
INSERT INTO `property` VALUES ('103', '76', '证书状态');
INSERT INTO `property` VALUES ('104', '76', '箱门结构');
INSERT INTO `property` VALUES ('105', '76', '美菱冰箱型号');
INSERT INTO `property` VALUES ('106', '76', '3C产品型号');
INSERT INTO `property` VALUES ('107', '76', '申请人名称');
INSERT INTO `property` VALUES ('108', '76', '证书编号');
INSERT INTO `property` VALUES ('109', '76', '制造商名称');
INSERT INTO `property` VALUES ('110', '76', '制冷方式');
INSERT INTO `property` VALUES ('111', '76', '产品名称');
INSERT INTO `property` VALUES ('112', '76', '能效等级');
INSERT INTO `property` VALUES ('113', '75', '空调类型');
INSERT INTO `property` VALUES ('114', '75', '证书状态');
INSERT INTO `property` VALUES ('115', '75', '证书编号');
INSERT INTO `property` VALUES ('116', '75', '海信空调型号');
INSERT INTO `property` VALUES ('117', '75', '工作方式');
INSERT INTO `property` VALUES ('118', '75', '冷暖类型');
INSERT INTO `property` VALUES ('119', '75', '产品名称');
INSERT INTO `property` VALUES ('120', '75', '能效等级');
INSERT INTO `property` VALUES ('121', '75', '空调功率');
INSERT INTO `property` VALUES ('122', '75', '适用面积');
INSERT INTO `property` VALUES ('123', '74', '风格');
INSERT INTO `property` VALUES ('124', '74', '颜色分类');
INSERT INTO `property` VALUES ('125', '74', '显示方式');
INSERT INTO `property` VALUES ('126', '74', '表壳材质');
INSERT INTO `property` VALUES ('127', '74', '机芯类型');
INSERT INTO `property` VALUES ('128', '74', '手表镜面材质');
INSERT INTO `property` VALUES ('129', '74', '机芯产地');
INSERT INTO `property` VALUES ('130', '74', '表盘厚度');
INSERT INTO `property` VALUES ('131', '74', '品牌产地');
INSERT INTO `property` VALUES ('132', '74', '成色');
INSERT INTO `property` VALUES ('133', '74', '表盘直径');
INSERT INTO `property` VALUES ('134', '74', '上市时间');
INSERT INTO `property` VALUES ('135', '74', '表带材质');
INSERT INTO `property` VALUES ('136', '74', '保修');
INSERT INTO `property` VALUES ('137', '74', '品牌');
INSERT INTO `property` VALUES ('138', '74', '形状');
INSERT INTO `property` VALUES ('139', '74', '防水深度');
INSERT INTO `property` VALUES ('140', '74', '流行元素');
INSERT INTO `property` VALUES ('141', '74', '手表种类');
INSERT INTO `property` VALUES ('142', '74', '型号');
INSERT INTO `property` VALUES ('143', '73', '风格');
INSERT INTO `property` VALUES ('144', '73', '颜色分类');
INSERT INTO `property` VALUES ('145', '73', '显示方式');
INSERT INTO `property` VALUES ('146', '73', '表壳材质');
INSERT INTO `property` VALUES ('147', '73', '机芯类型');
INSERT INTO `property` VALUES ('148', '73', '手表镜面材质');
INSERT INTO `property` VALUES ('149', '73', '表扣款式');
INSERT INTO `property` VALUES ('150', '73', '表盘厚度');
INSERT INTO `property` VALUES ('151', '73', '品牌产地');
INSERT INTO `property` VALUES ('152', '73', '表盘直径');
INSERT INTO `property` VALUES ('153', '73', '成色');
INSERT INTO `property` VALUES ('154', '73', '上市时间');
INSERT INTO `property` VALUES ('155', '73', '表带材质');
INSERT INTO `property` VALUES ('156', '73', '保修');
INSERT INTO `property` VALUES ('157', '73', '品牌');
INSERT INTO `property` VALUES ('158', '73', '形状');
INSERT INTO `property` VALUES ('159', '73', '防水深度');
INSERT INTO `property` VALUES ('160', '73', '表底类型');
INSERT INTO `property` VALUES ('161', '73', '表冠类型');
INSERT INTO `property` VALUES ('162', '73', '流行元素');
INSERT INTO `property` VALUES ('163', '73', '手表种类');
INSERT INTO `property` VALUES ('164', '73', '型号');
INSERT INTO `property` VALUES ('165', '72', '风格');
INSERT INTO `property` VALUES ('166', '72', '大小');
INSERT INTO `property` VALUES ('167', '72', '内部结构');
INSERT INTO `property` VALUES ('168', '72', '颜色分类');
INSERT INTO `property` VALUES ('169', '72', '箱包硬度');
INSERT INTO `property` VALUES ('170', '72', '款式');
INSERT INTO `property` VALUES ('171', '72', '图案');
INSERT INTO `property` VALUES ('172', '72', '成色');
INSERT INTO `property` VALUES ('173', '72', '里料材质');
INSERT INTO `property` VALUES ('174', '72', '上市时间');
INSERT INTO `property` VALUES ('175', '72', '品牌');
INSERT INTO `property` VALUES ('176', '72', '形状');
INSERT INTO `property` VALUES ('177', '72', '箱包外袋种类');
INSERT INTO `property` VALUES ('178', '72', '适用对象');
INSERT INTO `property` VALUES ('179', '72', '质地');
INSERT INTO `property` VALUES ('180', '72', '拿包方式');
INSERT INTO `property` VALUES ('181', '72', '适用场景');
INSERT INTO `property` VALUES ('182', '72', '提拎部件类型');
INSERT INTO `property` VALUES ('183', '72', '闭合方式');
INSERT INTO `property` VALUES ('184', '72', '是否可折叠');
INSERT INTO `property` VALUES ('185', '72', '有无夹层');
INSERT INTO `property` VALUES ('186', '72', '货号');
INSERT INTO `property` VALUES ('187', '72', '皮革材质');
INSERT INTO `property` VALUES ('188', '71', '销售渠道类型');
INSERT INTO `property` VALUES ('189', '71', '面料分类');
INSERT INTO `property` VALUES ('190', '71', '上市年份季节');
INSERT INTO `property` VALUES ('191', '71', '品牌');
INSERT INTO `property` VALUES ('192', '71', '货号');
INSERT INTO `property` VALUES ('193', '71', '基础风格');
INSERT INTO `property` VALUES ('194', '71', '材质成分');
INSERT INTO `property` VALUES ('195', '71', '厚薄');
INSERT INTO `property` VALUES ('196', '69', '风格');
INSERT INTO `property` VALUES ('197', '69', '鞋面材质');
INSERT INTO `property` VALUES ('198', '69', '低帮鞋品名');
INSERT INTO `property` VALUES ('199', '69', '鞋底材质');
INSERT INTO `property` VALUES ('200', '69', '颜色分类');
INSERT INTO `property` VALUES ('201', '69', '上市年份季节');
INSERT INTO `property` VALUES ('202', '69', '款式');
INSERT INTO `property` VALUES ('203', '69', '图案');
INSERT INTO `property` VALUES ('204', '69', '鞋面内里材质');
INSERT INTO `property` VALUES ('205', '69', '季节');
INSERT INTO `property` VALUES ('206', '69', '细分风格');
INSERT INTO `property` VALUES ('207', '69', '鞋跟高');
INSERT INTO `property` VALUES ('208', '69', '品牌');
INSERT INTO `property` VALUES ('209', '69', '适用对象');
INSERT INTO `property` VALUES ('210', '69', '销售渠道类型');
INSERT INTO `property` VALUES ('211', '69', '功能');
INSERT INTO `property` VALUES ('212', '69', '鞋制作工艺');
INSERT INTO `property` VALUES ('213', '69', '尺码');
INSERT INTO `property` VALUES ('214', '69', '闭合方式');
INSERT INTO `property` VALUES ('215', '69', '流行元素');
INSERT INTO `property` VALUES ('216', '69', '跟底款式');
INSERT INTO `property` VALUES ('217', '69', '货号');
INSERT INTO `property` VALUES ('218', '69', '鞋头款式');
INSERT INTO `property` VALUES ('219', '69', '鞋垫材质');
INSERT INTO `property` VALUES ('220', '69', '场合');
INSERT INTO `property` VALUES ('221', '68', '适用年龄');
INSERT INTO `property` VALUES ('222', '68', '颜色分类');
INSERT INTO `property` VALUES ('223', '68', '上市年份季节');
INSERT INTO `property` VALUES ('224', '68', '尺码');
INSERT INTO `property` VALUES ('225', '68', '货号');
INSERT INTO `property` VALUES ('226', '68', '品牌');
INSERT INTO `property` VALUES ('227', '68', '材质成分');
INSERT INTO `property` VALUES ('228', '64', '品牌');
INSERT INTO `property` VALUES ('229', '64', '产地');
INSERT INTO `property` VALUES ('230', '64', '上市时间');
INSERT INTO `property` VALUES ('231', '64', '型号');
INSERT INTO `property` VALUES ('232', '64', '功能');
INSERT INTO `property` VALUES ('233', '64', '适用性别');
INSERT INTO `property` VALUES ('234', '64', '风格');
INSERT INTO `property` VALUES ('235', '64', '价格区间');
INSERT INTO `property` VALUES ('236', '64', '眼镜配件类型');
INSERT INTO `property` VALUES ('237', '64', '是否商场同款');
INSERT INTO `property` VALUES ('238', '64', '镜片材质');
INSERT INTO `property` VALUES ('239', '64', '货号');
INSERT INTO `property` VALUES ('240', '64', '颜色分类');
INSERT INTO `property` VALUES ('241', '64', '是否可调');
INSERT INTO `property` VALUES ('242', '64', '适合脸型');
INSERT INTO `property` VALUES ('243', '60', '产地');
INSERT INTO `property` VALUES ('244', '60', '证书状态');
INSERT INTO `property` VALUES ('245', '60', '3C产品型号');
INSERT INTO `property` VALUES ('246', '60', '申请人名称');
INSERT INTO `property` VALUES ('247', '60', '安装接口');
INSERT INTO `property` VALUES ('248', '60', '适合体重');
INSERT INTO `property` VALUES ('249', '60', '证书编号');
INSERT INTO `property` VALUES ('250', '60', '型号');
INSERT INTO `property` VALUES ('251', '60', '制造商名称');
INSERT INTO `property` VALUES ('252', '60', '颜色分类');
INSERT INTO `property` VALUES ('253', '60', '款式');
INSERT INTO `property` VALUES ('254', '60', '产品名称');
INSERT INTO `property` VALUES ('255', '60', '人体固定方式');
INSERT INTO `property` VALUES ('256', '60', '安全座椅品牌');
INSERT INTO `property` VALUES ('257', '60', '适用年龄');
