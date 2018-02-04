-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gk_platform
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `teach_class_card_custom_page`
--

DROP TABLE IF EXISTS `teach_class_card_custom_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_custom_page` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '自定义界面的名字',
  `start_time` bigint(20) DEFAULT NULL COMMENT '循环时间段的开始时间',
  `end_time` bigint(20) DEFAULT NULL COMMENT '循环时间段的结束时间',
  `status` int(10) DEFAULT '0' COMMENT '界面的状态\n0，编辑\n1，发布',
  `template_id` varchar(50) DEFAULT NULL COMMENT '界面父类模板id',
  `loop_mark` varchar(10) DEFAULT 'D' COMMENT '界面循环的方式\nD：天     date     默认\nW：周    week\nM：月     month\nN：不循环    no',
  `loop_date` varchar(45) DEFAULT NULL COMMENT '循环的具体时间，\n 1,3,5\n如果mark = W ，代表周一三五循环  ',
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `school_id` varchar(50) DEFAULT NULL COMMENT '自定义界面所属的学校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班牌自定义根据模板创建的页面';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_custom_page`
--

LOCK TABLES `teach_class_card_custom_page` WRITE;
/*!40000 ALTER TABLE `teach_class_card_custom_page` DISABLE KEYS */;
INSERT INTO `teach_class_card_custom_page` VALUES ('1714ebb7d6e212cebfdf4d0123453323','放假通知',0,43200000,0,'c3d1b69fc3187b8941861eda17cd4b98','D',NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516692842358,'2c3c5aed7cb8fc26174adc96142132f7',1516692388625,0,'c74f7f8b253c8762df3d9288f58b1f95'),('365935966c13449d8f3998c172c7f95c','周三晚上家庭作业',34200000,36000000,1,'c3d1b69fc3187b8941861eda17cd4b98','D',NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516691076330,'2c3c5aed7cb8fc26174adc96142132f7',1516690846042,0,'c74f7f8b253c8762df3d9288f58b1f95'),('a6f2e3585ddf3d32fa1fdbc8c9b0b977','新建测试页面',27300000,34500000,1,'c3d1b69fc3187b8941861eda17cd4b98','D',NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516700821457,'2c3c5aed7cb8fc26174adc96142132f7',1516692892052,0,'c74f7f8b253c8762df3d9288f58b1f95'),('fc5b36451bda129835d195333fe8febf','周五作业',34020000,44820000,0,'c3d1b69fc3187b8941861eda17cd4b98','D',NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516700810001,'2c3c5aed7cb8fc26174adc96142132f7',1516699270468,0,'c74f7f8b253c8762df3d9288f58b1f95');
/*!40000 ALTER TABLE `teach_class_card_custom_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_card_custom_template`
--

DROP TABLE IF EXISTS `teach_class_card_custom_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_custom_template` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '模板的名字',
  `detailed` varchar(100) DEFAULT NULL COMMENT '模板的详细描述',
  `preview` varchar(50) DEFAULT NULL COMMENT '模板预览，预览图片的url',
  `file_path` varchar(50) DEFAULT NULL COMMENT '模板的路径，包含文件的名字\n/aa/aa.html',
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `school_id` varchar(50) DEFAULT NULL COMMENT '模板使用的学校，',
  `share_flag` int(10) DEFAULT '0' COMMENT '模板共享的标记，多个学校之间共享，0共享，1不共享',
  `status` int(10) DEFAULT '0' COMMENT '模板的状态，编辑？发布？停用？ ，默认0是可用的状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存页面模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_custom_template`
--

LOCK TABLES `teach_class_card_custom_template` WRITE;
/*!40000 ALTER TABLE `teach_class_card_custom_template` DISABLE KEYS */;
INSERT INTO `teach_class_card_custom_template` VALUES ('1c0204e3e6519185f048bae0dd55a777','视频模板','上传视频的模板','user-icon.png','templateDemo4','2c3c5aed7cb8fc26174adc96142132f7',1515576159350,NULL,NULL,0,'c74f7f8b253c8762df3d9288f58b1f95',0,0),('1c0204e3e6519185f048bae0dd55a8ba','图片模板','上传图片的模板','user-icon.png','templateDemo3','2c3c5aed7cb8fc26174adc96142132f7',1515576159350,NULL,NULL,0,'c74f7f8b253c8762df3d9288f58b1f95',0,0),('c3d1b69fc3187b8941861eda17cd4b98','模板名字1','创建的第一个模板','user-icon.png','templateDemo1','2c3c5aed7cb8fc26174adc96142132f7',1515576159350,NULL,NULL,0,'c74f7f8b253c8762df3d9288f58b1f95',0,0),('cc5353cce83d328cae1169b391f5d328','模板2','上下结构的模板','user-icon.png','templateDemo2','2c3c5aed7cb8fc26174adc96142132f7',1515576159350,NULL,NULL,0,'c74f7f8b253c8762df3d9288f58b1f95',0,0);
/*!40000 ALTER TABLE `teach_class_card_custom_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_ref_classcard_custom`
--

DROP TABLE IF EXISTS `teach_ref_classcard_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_ref_classcard_custom` (
  `id` varchar(50) NOT NULL,
  `page_id` varchar(50) NOT NULL,
  `class_card_id` varchar(50) NOT NULL,
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义界面发布的设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_ref_classcard_custom`
--

LOCK TABLES `teach_ref_classcard_custom` WRITE;
/*!40000 ALTER TABLE `teach_ref_classcard_custom` DISABLE KEYS */;
INSERT INTO `teach_ref_classcard_custom` VALUES ('01a0f9697c1aecf2b0e7fe261d639f4c','365935966c13449d8f3998c172c7f95c','e7fb8a6fa7190fc59418245196bf780c',0),('0897af9f9ce7f68490c38cce0dfd5740','a6f2e3585ddf3d32fa1fdbc8c9b0b977','29e5fbec64a2c75ab0e9e5f3e81325f1',0),('1fa29bc2b5c7560304a45a0a14bb382b','a6f2e3585ddf3d32fa1fdbc8c9b0b977','3984e35e31a71ab7681c1bec3e2895e2',0),('40266866839b1f6486aa27aa4ea0d74c','365935966c13449d8f3998c172c7f95c','3984e35e31a71ab7681c1bec3e2895e2',0),('45e719e97a84febe01f5ff307466f3f8','365935966c13449d8f3998c172c7f95c','5df402d6c06190d513516426606969fc',0),('5409b8ad73a5f695e14c8fb22e7a71e9','365935966c13449d8f3998c172c7f95c','a7505cea0f18bbd28071054844a88277',0),('83b01b1d9678de4e50cb41bdd15f109f','365935966c13449d8f3998c172c7f95c','29e5fbec64a2c75ab0e9e5f3e81325f1',0),('853596465313c64dea8ccea41c20aa10','a6f2e3585ddf3d32fa1fdbc8c9b0b977','5df402d6c06190d513516426606969fc',0),('995f09074872c1c5751fe2327bf07e28','a6f2e3585ddf3d32fa1fdbc8c9b0b977','a7505cea0f18bbd28071054844a88277',0),('b4aa45520a610f3abd10509069190815','a6f2e3585ddf3d32fa1fdbc8c9b0b977','095d164675c7fbc760f9e5b1cc826e4f',0),('d6c899c27a287a56ece535ce200c3de6','365935966c13449d8f3998c172c7f95c','095d164675c7fbc760f9e5b1cc826e4f',0),('e5869626b917b192ce0c3dca092a3d7b','a6f2e3585ddf3d32fa1fdbc8c9b0b977','e7fb8a6fa7190fc59418245196bf780c',0);
/*!40000 ALTER TABLE `teach_ref_classcard_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_ref_classcard_custom_content`
--

DROP TABLE IF EXISTS `teach_ref_classcard_custom_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_ref_classcard_custom_content` (
  `id` varchar(50) NOT NULL,
  `page_id` varchar(50) DEFAULT NULL COMMENT '内容对应的页面的ID',
  `name` varchar(50) DEFAULT NULL COMMENT '页面替换的内容的名字，标签名？？\n例如：多个文字注意区别',
  `value` text COMMENT '界面的内容。\n\n修改记录：\n1.修改字段的类型VarChar-->TEXT，调整为保存富文本编辑框的内容content，字段名字没有起好，',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='按照模板创建界面，保存其中替换的内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_ref_classcard_custom_content`
--

LOCK TABLES `teach_ref_classcard_custom_content` WRITE;
/*!40000 ALTER TABLE `teach_ref_classcard_custom_content` DISABLE KEYS */;
INSERT INTO `teach_ref_classcard_custom_content` VALUES ('0c10c3fe008efdfd6a637a219bba4c91','fc5b36451bda129835d195333fe8febf','pageContent','<p>\n	英语作业：\n</p>\n<p>\n	第一单元的单词抄写三遍<img src=\"http://localhost:8080/platform/assetsNew/kindeditor-4.1.10/plugins/emoticons/images/20.gif\" border=\"0\" alt=\"\">\n</p>'),('11f967d0421e50a2f5cc8650936d3741','365935966c13449d8f3998c172c7f95c','pageContent','<p>\n	第一板块：\n</p>\n<p>\n	数学作业：<img src=\"http://localhost:8080/platform/assetsNew/kindeditor-4.1.10/plugins/emoticons/images/0.gif\" border=\"0\" alt=\"\"> \n</p>\n<p>\n	<br>\n</p>\n<p>\n	<strong>X</strong><sup>2</sup><strong> - 4 = 0&nbsp; 求未知数X的值？</strong> \n</p>\n<p>\n	<strong><br>\n</strong> \n</p>\n<p>\n	<strong><img src=\"http://localhost:8080/platform/assetsNew/kindeditor-4.1.10/plugins/emoticons/images/44.gif\" border=\"0\" alt=\"\"><br>\n</strong> \n</p>\n<p>\n	<br>\n</p>\n<p>\n	<br>\n</p>\n<p>\n	第二板块：\n</p>\n<p>\n	英语作业：\n</p>\n<p>\n	<br>\n</p>\n<p>\n	<strong><span style=\"font-size:18px;\">背诵</span><span style=\"background-color:#E56600;font-size:18px;\">Until3 topic2</span><span style=\"font-size:18px;\"> 的全部对话内容</span></strong> \n</p>\n<p>\n	<img src=\"http://localhost:8080/platform/assetsNew/kindeditor-4.1.10/plugins/emoticons/images/30.gif\" border=\"0\" alt=\"\"> \n</p>'),('172721437f706d000b3cbbe210979fa0','1714ebb7d6e212cebfdf4d0123453323','pageContent','<h2 style=\"text-align:center;\">\n	<strong><span style=\"font-size:24px;\">通知</span></strong> \n</h2>\n<blockquote>\n	<p>\n		根据国家法律规定，10月1日为国家法定假期，我校假期为10月1日到10月7日，10月8日正式上课，按时到校。\n	</p>\n</blockquote>\n<p>\n	假期出行注意安全。\n</p>\n<blockquote>\n	<p>\n		特此通知\n	</p>\n	<p>\n		<span>北京中学教务处</span>\n	</p>\n	<p>\n		<span>2018年9月25号</span>\n	</p>\n</blockquote>\n<p>\n	<br>\n</p>'),('b29453359333aa2317c7d5bb5a1c73f6','b24e4c703b8cf64ff9d10842d4b4cc0d','pageContent','<div>\n    <p>上传视频的模板</p>\n    <video id=\"my-video\" autoplay=\"\" loop=\"\" width=\"80%\" height=\"80%\">\n        <source src=\"http://121.42.168.14:10012/video/test.mp4\" type=\"video/mp4\">\n    </video>\n</div>'),('e93c617033455c6f54bafcfb19de919a','edc6a7d73a5e3075e582410950d77538','pageContent','上传图片的模板样例 <img src=\"http://localhost:8080/platform/assetsNew/images/user-icon.png\" alt=\"样例图片\"><br>\n<b>下面还有一个图片的标签：asdf</b><br>\n<p>\n	<img src=\"http://localhost:8080/platform/classcard/custom\" style=\"width:80%;height:80%;\">\n</p>\n<p>\n	创建的上传图片的模板，怎么选择想要上传的图片？？？？？\n</p>');
/*!40000 ALTER TABLE `teach_ref_classcard_custom_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gk_platform'
--

--
-- Dumping routines for database 'gk_platform'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-23 18:11:45
