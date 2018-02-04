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
-- Table structure for table `teach_class_card_config`
--

DROP TABLE IF EXISTS `teach_class_card_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_config` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '配置信息的名字',
  `start_date` bigint(20) DEFAULT NULL COMMENT '配置信息生效的时间',
  `end_date` bigint(20) DEFAULT NULL COMMENT '配置信息结束的时间',
  `week` varchar(50) DEFAULT NULL COMMENT '配置信息有效的星期（周）',
  `start_time` bigint(20) DEFAULT NULL COMMENT '配置信息的参数，班牌开机的时间',
  `end_time` bigint(20) DEFAULT NULL COMMENT '配置信息参数，班牌关机时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记，1删除，0正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班牌设备开关机的配置，与息屏和班级信息都有关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_config`
--

LOCK TABLES `teach_class_card_config` WRITE;
/*!40000 ALTER TABLE `teach_class_card_config` DISABLE KEYS */;
INSERT INTO `teach_class_card_config` VALUES ('623815d9eadfafd1148ed9be3b9f09bb','课间操',1515513600000,1517328000000,'1,2,3,4,5',26580000,37380000,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515569073658,0),('6f080982afc35e343a9f9aaa732b3319','修改成功了',1514736000000,1546272000000,'1,2,3',33720000,37320000,'2c3c5aed7cb8fc26174adc96142132f7',1515660772643,'2c3c5aed7cb8fc26174adc96142132f7',1515576159350,0),('7c55760f164a17a9f2cb449d664e460c','测试添加新的配置',1514736000000,1546272000000,'3,',32460000,36060000,'2c3c5aed7cb8fc26174adc96142132f7',1516004596775,'2c3c5aed7cb8fc26174adc96142132f7',1515578501806,0),('9c46005b894a700c33c59dea35ecffac','下午课间操',1514736000000,1515513600000,'1,2,3,4,5',34020000,44820000,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515569258279,0),('9d869c3e1d2b26c08f6623952cb5d135','添加一个正经的配置',1515945600000,1515945600000,'2,3,4,',36600000,43860000,'2c3c5aed7cb8fc26174adc96142132f7',1516018268214,'2c3c5aed7cb8fc26174adc96142132f7',1516011137681,0),('a911d296c5379e5b7b31d413bfa9e30e','样式调整OK',1580486400000,1643040000000,'1,',37560000,41160000,'2c3c5aed7cb8fc26174adc96142132f7',1516011000783,'2c3c5aed7cb8fc26174adc96142132f7',1515580011411,0),('c3813f9f696751c49fda1468bf8e8b5f','添加一个正经的配置',1515945600000,1517328000000,'2,',46260000,49860000,'2c3c5aed7cb8fc26174adc96142132f7',1516020957789,'2c3c5aed7cb8fc26174adc96142132f7',1516020699061,0),('c3d1b69fc3187b8941861eda17cd4b98','添加回填没问题的配置',1514736000000,1514908800000,'2,3,',38340000,41940000,'2c3c5aed7cb8fc26174adc96142132f7',1516010758006,'2c3c5aed7cb8fc26174adc96142132f7',1515580796123,0),('d16102c698892b0cedbbcee5afbb1329','添加一个配置',1515945600000,1517241600000,'2,3,',44940000,52140000,'2c3c5aed7cb8fc26174adc96142132f7',1516019433381,'2c3c5aed7cb8fc26174adc96142132f7',1516019398121,0);
/*!40000 ALTER TABLE `teach_class_card_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_ref_classcard_config`
--

DROP TABLE IF EXISTS `teach_ref_classcard_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_ref_classcard_config` (
  `id` varchar(50) NOT NULL,
  `class_card_config_id` varchar(50) NOT NULL COMMENT '班牌配置文件表的id',
  `class_card_id` varchar(50) NOT NULL COMMENT '班牌id',
  `school_id` varchar(50) NOT NULL,
  `del_flag` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班牌配置文件和班牌设备对应关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_ref_classcard_config`
--

LOCK TABLES `teach_ref_classcard_config` WRITE;
/*!40000 ALTER TABLE `teach_ref_classcard_config` DISABLE KEYS */;
INSERT INTO `teach_ref_classcard_config` VALUES ('192cc6a4c76814870d5f55a6ea654143','c3d1b69fc3187b8941861eda17cd4b98','a7505cea0f18bbd28071054844a88277','c74f7f8b253c8762df3d9288f58b1f95',0),('26a68cafd57e68a8d844101ce15b1d39','9d869c3e1d2b26c08f6623952cb5d135','0232fc6b3c023f34432b2075a9287803','c74f7f8b253c8762df3d9288f58b1f95',0),('311910252a20068e5529a1bf386681ae','c3813f9f696751c49fda1468bf8e8b5f','46f036f4abc7eb08643743070e749f6f','c74f7f8b253c8762df3d9288f58b1f95',0),('317e37c7359e6f89486737c890591ac3','d16102c698892b0cedbbcee5afbb1329','095d164675c7fbc760f9e5b1cc826e4f','c74f7f8b253c8762df3d9288f58b1f95',0),('4c28a316030019bf0881d84366c8b441','c3d1b69fc3187b8941861eda17cd4b98','0a226fc9e21528cc50a3cce6e46dc0f1','c74f7f8b253c8762df3d9288f58b1f95',0),('52b99d0103475b23b7f9c58710228f5b','c3813f9f696751c49fda1468bf8e8b5f','89cf10f5f5f59a7d7ec60b088c4ec29e','c74f7f8b253c8762df3d9288f58b1f95',0),('575c4844fdfc9c29477d3cf553263874','7c55760f164a17a9f2cb449d664e460c','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('65cb93a77540e79238d653dffa7aeebc','c3d1b69fc3187b8941861eda17cd4b98','a16bbd52393c7d7a149876b2f37dcc10','c74f7f8b253c8762df3d9288f58b1f95',0),('67de8bedd8f5b89b34d0be6908917067','a911d296c5379e5b7b31d413bfa9e30e','21bdf0bf44b33ce57d98b10bf1947ca6','c74f7f8b253c8762df3d9288f58b1f95',0),('72942f6ac7d9f12adb305f9ae2aaaa74','d16102c698892b0cedbbcee5afbb1329','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('8aac8b11b50977e8e8261f5d502be867','a911d296c5379e5b7b31d413bfa9e30e','46f036f4abc7eb08643743070e749f6f','c74f7f8b253c8762df3d9288f58b1f95',0),('8cd43a2947f5eb413e721683fdea5b4f','d16102c698892b0cedbbcee5afbb1329','5df402d6c06190d513516426606969fc','c74f7f8b253c8762df3d9288f58b1f95',0),('98d4196af1993867aed0cdc8bf470476','c3d1b69fc3187b8941861eda17cd4b98','efdb96c3893433bb6e878792a4a393e6','c74f7f8b253c8762df3d9288f58b1f95',0),('9e3715023bfbd6aaa95b52ec4431941a','d16102c698892b0cedbbcee5afbb1329','3984e35e31a71ab7681c1bec3e2895e2','c74f7f8b253c8762df3d9288f58b1f95',0),('9fbdd38ebe7864be76b31eb0d4f2bd65','a911d296c5379e5b7b31d413bfa9e30e','efdb96c3893433bb6e878792a4a393e6','c74f7f8b253c8762df3d9288f58b1f95',0),('b1e09d514c4c6c5cfdc486adf3af4b23','c3813f9f696751c49fda1468bf8e8b5f','0232fc6b3c023f34432b2075a9287803','c74f7f8b253c8762df3d9288f58b1f95',0),('ce068bce743097c5ebad73478b26bae1','c3813f9f696751c49fda1468bf8e8b5f','21bdf0bf44b33ce57d98b10bf1947ca6','c74f7f8b253c8762df3d9288f58b1f95',0),('d3be6aa09a298acf24e8b75d8aef9950','9d869c3e1d2b26c08f6623952cb5d135','29e5fbec64a2c75ab0e9e5f3e81325f1','c74f7f8b253c8762df3d9288f58b1f95',0),('d63dc10f84adbcd9c7cdbbeb6ccdf7fa','d16102c698892b0cedbbcee5afbb1329','29e5fbec64a2c75ab0e9e5f3e81325f1','c74f7f8b253c8762df3d9288f58b1f95',0),('d9e6c5dad7e48cf15e3190607b044e8c','c3d1b69fc3187b8941861eda17cd4b98','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('dffc9a4149458e6d1ba1295a00a58ec1','c3d1b69fc3187b8941861eda17cd4b98','00d97699b5e6c1180d0058713a0877f1','c74f7f8b253c8762df3d9288f58b1f95',0),('e7943704dec3a56b03d5c67daeac9ef0','a911d296c5379e5b7b31d413bfa9e30e','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('e8912b8dd0507855aec1fd52ff9277a5','c3813f9f696751c49fda1468bf8e8b5f','6b043ea74d5bc53ec65cd29c8a6ea761','c74f7f8b253c8762df3d9288f58b1f95',0),('f545e56cd559d241ec8f11461df980eb','c3d1b69fc3187b8941861eda17cd4b98','143a68774f3a3685c885eb8730b57a08','c74f7f8b253c8762df3d9288f58b1f95',0),('fa9228f0172fd945e7aa9807fbbf7b52','7c55760f164a17a9f2cb449d664e460c','efdb96c3893433bb6e878792a4a393e6','c74f7f8b253c8762df3d9288f58b1f95',0),('fb5504221f4d422d72129bb7a6916552','d16102c698892b0cedbbcee5afbb1329','a7505cea0f18bbd28071054844a88277','c74f7f8b253c8762df3d9288f58b1f95',0);
/*!40000 ALTER TABLE `teach_ref_classcard_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_ref_classcard_config_screen_off`
--

DROP TABLE IF EXISTS `teach_ref_classcard_config_screen_off`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_ref_classcard_config_screen_off` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `class_card_config_id` varchar(50) NOT NULL COMMENT '班牌配置的id，外键',
  `screen_off_week` varchar(50) DEFAULT NULL COMMENT '息屏的周',
  `screen_off_start_time` bigint(20) DEFAULT NULL COMMENT '息屏开始时间，当天的时间，保存时分秒',
  `screen_off_end_time` bigint(20) DEFAULT NULL COMMENT '息屏结束时间，当天的时间，保存时分秒',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记，0正常，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置班牌的息屏周期';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_ref_classcard_config_screen_off`
--

LOCK TABLES `teach_ref_classcard_config_screen_off` WRITE;
/*!40000 ALTER TABLE `teach_ref_classcard_config_screen_off` DISABLE KEYS */;
INSERT INTO `teach_ref_classcard_config_screen_off` VALUES ('0fa960db2a631cb1af27c77741987de7','c3d1b69fc3187b8941861eda17cd4b98','2,3,4,',-19440000,-12180000,0),('1a0a2ff32647221c64cb3137838b3c35','9d869c3e1d2b26c08f6623952cb5d135','2,4,',43920000,47520000,0),('3bd8332374a42037fd21330eda2a580a','c3813f9f696751c49fda1468bf8e8b5f','1,',46260000,53460000,0),('5c6a35fcadeac4ce2191575811eba003','7c55760f164a17a9f2cb449d664e460c','',48060000,51720000,0),('6e7eff8f9672471f581ad8055cf7b4fe','d16102c698892b0cedbbcee5afbb1329','2,3,',45000000,45120000,0),('7b1992c3fa9c01e5b840da58e761a322','d16102c698892b0cedbbcee5afbb1329','2,3,',37740000,41460000,0),('939a565c0cccb0fe57622954b5e0686d','c3d1b69fc3187b8941861eda17cd4b98','2,3,4,',36240000,39840000,0),('9c5c88fb5ef7e84c06ba11042585da0a','6f080982afc35e343a9f9aaa732b3319','',48060000,51720000,0),('a5e2d9a6fd757d53045a6caf797c583c','d16102c698892b0cedbbcee5afbb1329','2,3,',52140000,55740000,0),('a9439ff5210de5e5de33e3cfc2c515d5','a911d296c5379e5b7b31d413bfa9e30e','',4320000,11460000,0),('ae82e1dafbbf21c550ad0f9ab49a18de','c3813f9f696751c49fda1468bf8e8b5f','1,',53700000,57300000,0),('bc71fdee00e85e9ea07507ec6a83dc6b','9d869c3e1d2b26c08f6623952cb5d135','2,4,',36660000,36720000,0),('be3d8077b25e58e0d2734b9ef0cd0ed8','a911d296c5379e5b7b31d413bfa9e30e','',51780000,54540000,0),('c21caa6dd0c2b95127eed344f93a3143','9d869c3e1d2b26c08f6623952cb5d135','2,4,',41580000,41820000,0),('ce1f442d7d36e254f1909a3299076ab6','6f080982afc35e343a9f9aaa732b3319','',15120000,11460000,0),('e0a0e53fe03c79ed166b8b5dac43f5b4','a911d296c5379e5b7b31d413bfa9e30e','',48060000,51720000,0),('e203fbb697eb6b39e9b17eb71ca6f475','7c55760f164a17a9f2cb449d664e460c','',15120000,11460000,0),('e3e300f84e9ded43a9c3ccbfb011fb98','a911d296c5379e5b7b31d413bfa9e30e','',16680000,38280000,0),('eef62a6a78a4ecfeac6e573509729c55','7c55760f164a17a9f2cb449d664e460c','',-16620000,-5820000,0);
/*!40000 ALTER TABLE `teach_ref_classcard_config_screen_off` ENABLE KEYS */;
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

-- Dump completed on 2018-01-16  9:16:28
