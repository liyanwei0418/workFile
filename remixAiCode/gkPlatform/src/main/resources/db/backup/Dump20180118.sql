CREATE DATABASE  IF NOT EXISTS `gk_platform` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gk_platform`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: gk_platform
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `teach_class_card_app`
--

DROP TABLE IF EXISTS `teach_class_card_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_app` (
  `id` varchar(50) NOT NULL,
  `app_url` varchar(255) DEFAULT NULL,
  `app_name` varchar(45) DEFAULT NULL,
  `app_version` varchar(45) DEFAULT NULL,
  `package_name` varchar(45) DEFAULT NULL COMMENT '包名',
  `auto_start` int(11) DEFAULT NULL COMMENT '自动重启  1：是， 0：否',
  `school_id` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标记【0正常，1已经删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_app`
--

LOCK TABLES `teach_class_card_app` WRITE;
/*!40000 ALTER TABLE `teach_class_card_app` DISABLE KEYS */;
INSERT INTO `teach_class_card_app` VALUES ('06b58ac106b0ce6e0a98601cd44c2f57','6','6','6','6',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515486738562,'2c3c5aed7cb8fc26174adc96142132f7',1515486751865,1),('0d00c03effea7b083bf4cb526b7787a8','22','22','2','2',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515488419028,NULL,NULL,1),('277a867dc8afa56e69006d4a5e4a640b','www.b1.com','b1','1.1','b1.com',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515486297299,'2c3c5aed7cb8fc26174adc96142132f7',1515486372662,1),('3fea0c1c649529cf06614c4d12f025ea','3232','3','32','34',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515488424645,NULL,NULL,1),('6fcd156b045c1893091cabcfd2395edf','1','哈哈','1','1',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515490724961,NULL,NULL,1),('7093261787aa615fcec4e7e2e75e6eaf','1','app1','1','1',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515490413106,'2c3c5aed7cb8fc26174adc96142132f7',1515552278155,0),('8e546ae6c4dcb712f1ac57a495deb03b','http://www.a23.com','a23','1.2.3','a23.com',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515486017677,'2c3c5aed7cb8fc26174adc96142132f7',1515486230220,1),('91107b46c766b951b977c67cd22ec60f','5','5','5','5',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515486719945,NULL,NULL,1),('99952aed5a201230a762a34d06b9bcb7','3','你是谁啊','3','4',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515490425436,NULL,NULL,1),('b75e479ebfc70ab3a92de206f52577cb','454','45','54','545',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515488429407,NULL,NULL,1),('bedf4c36e2eb931a7a5105ef985d2cad','1','app2','1','1',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515488294844,'2c3c5aed7cb8fc26174adc96142132f7',1515552286473,0),('e1271491e40d6c98306e8007e6670503','http://www.a1.com','app4','1.14888','a1.com',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515483521198,'2c3c5aed7cb8fc26174adc96142132f7',1515552299035,0),('e42541761ad71a291cb5fb8df4f0068d','http://ww.app5.com','app5','1.5','app5.com',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515554098949,NULL,NULL,1),('f55de9649a7722375c5c43456bab2cd2','656','65','56','65',0,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515488434418,NULL,NULL,1),('fbb41d563f39b421f0003a7c9025cdc9','sc','app3','csssfffffff','cssss',1,'c74f7f8b253c8762df3d9288f58b1f95','2c3c5aed7cb8fc26174adc96142132f7',1515486383349,'2c3c5aed7cb8fc26174adc96142132f7',1515552293690,0);
/*!40000 ALTER TABLE `teach_class_card_app` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `teach_class_card_config` VALUES ('623815d9eadfafd1148ed9be3b9f09bb','课间操',1515513600000,1517328000000,'1,2,3,4,5',26580000,37380000,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515569073658,0),('6f080982afc35e343a9f9aaa732b3319','修改成功了',1514736000000,1546272000000,'1,2,3',33720000,37320000,'2c3c5aed7cb8fc26174adc96142132f7',1515660772643,'2c3c5aed7cb8fc26174adc96142132f7',1515576159350,0),('7c55760f164a17a9f2cb449d664e460c','测试添加新的配置',1514736000000,1546272000000,'3,',32460000,36060000,'2c3c5aed7cb8fc26174adc96142132f7',1516004596775,'2c3c5aed7cb8fc26174adc96142132f7',1515578501806,0),('9c46005b894a700c33c59dea35ecffac','下午课间操',1514736000000,1515513600000,'1,2,3,4,5',34020000,44820000,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515569258279,0),('9d869c3e1d2b26c08f6623952cb5d135','添加一个正经的配置',1515945600000,1515945600000,'2,3,4,',36600000,43860000,'2c3c5aed7cb8fc26174adc96142132f7',1516018268214,'2c3c5aed7cb8fc26174adc96142132f7',1516011137681,0),('c3813f9f696751c49fda1468bf8e8b5f','添加一个正经的配置',1515945600000,1517328000000,'2,',46260000,49860000,'2c3c5aed7cb8fc26174adc96142132f7',1516067689228,'2c3c5aed7cb8fc26174adc96142132f7',1516020699061,0),('c3d1b69fc3187b8941861eda17cd4b98','添加回填没问题的配置',1514736000000,1514908800000,'2,3,',38340000,41940000,'2c3c5aed7cb8fc26174adc96142132f7',1516010758006,'2c3c5aed7cb8fc26174adc96142132f7',1515580796123,0),('d16102c698892b0cedbbcee5afbb1329','添加一个配置',1515945600000,1517241600000,'2,3,',44940000,52140000,'2c3c5aed7cb8fc26174adc96142132f7',1516019433381,'2c3c5aed7cb8fc26174adc96142132f7',1516019398121,0);
/*!40000 ALTER TABLE `teach_class_card_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_card_introduction`
--

DROP TABLE IF EXISTS `teach_class_card_introduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_introduction` (
  `id` varchar(50) NOT NULL,
  `send_word` varchar(255) DEFAULT NULL COMMENT '教师寄语',
  `class_backbone` varchar(255) DEFAULT NULL COMMENT '班级骨干',
  `class_introduction` varchar(255) DEFAULT NULL COMMENT '班级简介',
  `class_id` varchar(50) NOT NULL COMMENT '班级id',
  `class_card_id` varchar(50) NOT NULL COMMENT '班牌id',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  `picture_id` varchar(50) DEFAULT NULL,
  `video_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_introduction`
--

LOCK TABLES `teach_class_card_introduction` WRITE;
/*!40000 ALTER TABLE `teach_class_card_introduction` DISABLE KEYS */;
INSERT INTO `teach_class_card_introduction` VALUES ('01c8f63a4bbfdaf93994a7ff4eee4a2b','心有多大，天地就有多大；梦想有多远，路就能走多远！\r\n今天的付出，一定能在明天得到回报。有了信心、。','','我们初二1班是个团结奋进、朝气蓬勃、温暖和谐的集体，我们的班级目标是：爱学习、能吃苦、品行正，在班主任老师的引导和带领下，在各科老师的教导和帮助下，我们正朝着这个目标努力行进，相信三年后，我们会成就一个独一无二的班集体。','dd403a6126d725e1364af4652d3be2ba','21bdf0bf44b33ce57d98b10bf1947ca6','2c3c5aed7cb8fc26174adc96142132f7',1509436000800,'2c3c5aed7cb8fc26174adc96142132f7',1504426624105,0,'3b5701c5f64a8da0f89b71de058f16f9',NULL),('06ba784161dee5e080bbbfb5bcfd5661','欢迎新同学','欢迎新同学','欢迎新同学','128430d009adbc01154959a53d80959b','0a226fc9e21528cc50a3cce6e46dc0f1',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1504422079475,0,NULL,NULL),('0fb9c03799bfbe0f46dec431c2556e4f','做一个有理想的人，有领导能力的人，能领袖群伦的人。理想不灭，激情不灭。','','我们初二4班是一个充满和谐、美好的大家庭！在这个家庭里没有打骂和歧视，只有关心和帮助，在老师和同学的共同努力下，我们一定会走得踏实，走出风采，请大家记住我们初二4班这个快乐的团体吧！','07b210343db97e089d2e203d04942652','89cf10f5f5f59a7d7ec60b088c4ec29e','2c3c5aed7cb8fc26174adc96142132f7',1504426868842,'2c3c5aed7cb8fc26174adc96142132f7',1504426588224,0,NULL,NULL),('1c24d84133264598e590fc2fe1c5b55c','尊敬师长，诚信守纪，团结友善，爱校如家，学乐善思，勤学苦练，勇敢顽强，持之以恒，健康成长。','','我们初一1班是一个团结互助,积极向上的班集体.我们班紧紧围绕老师形成一个非常温暖的大家庭.我们班班风正,学习风气浓,课间随处可见互相讨论学习和请教问题的同学.在学校的各项活动中,我们都积极参与,努力拼搏,取得了很好的成绩.我们同学肯吃苦肯付出,平常做事都坚持只要做了就一定做好的原则,所以成功经常在我们这里落脚.我们尊敬师长,爱护同学,珍惜自己的学习环境,友好帮助他人,每个人都德智体美劳全面发展,是','60e299fff1aaec72d53cceb471a423b9','095d164675c7fbc760f9e5b1cc826e4f','2c3c5aed7cb8fc26174adc96142132f7',1515727049167,'2c3c5aed7cb8fc26174adc96142132f7',1504426559278,0,'','4192e580b4c129c2a6768acc7dacda8a'),('1eca9f5354580d45b53506a005dd69c4','爱拼才会赢。                       \r\n自信创造奇迹，勤奋改写人生。\r\n零碎的时间','','初二（2）班是一个团结协作，互相帮助，互相关心，坦诚相待，有着很强的集体荣誉\r\n感，有着严密的纪律性和较强的自我管理能力 的班集体。相信在老师的精心教育下，在全班同学的勤奋努力下，一定能创造辉煌。\r\n班级口号：知识改变命运，现在决定未来。','bb21ced9b9f99f60d201a75df2d7742c','6b043ea74d5bc53ec65cd29c8a6ea761','2c3c5aed7cb8fc26174adc96142132f7',1509436117552,'2c3c5aed7cb8fc26174adc96142132f7',1504426613718,0,'7ec729fcd0b9b038dffed0753fa2bafc',NULL),('29f0ed774d1d03fa7fb86d78682699c4','学会欣赏别人，心中有美的人才能在别人的身上发现美。','','在一个硕果累累的秋天，我们移过来了一个新的学校，一个新的班级，一群新的同学。有了新的班级就会有班级新的特色，我们五班当然也有特色，我们的特色就是活泼！\r\n为什么说我们班的特色是活泼呢？因为我们班能歌善舞，打得了流氓下得了厨房。除此之外，我们的班主任也是一位活泼可爱的老师。她会教书，会卖萌，是一位严厉与可爱并存的老师。说完了老师，我们再来看看我们班的同学吧。同学们那一个个都是高手呀！刘程会武术，唐坤等','3722607312e9b10b211d043c5316790e','a7505cea0f18bbd28071054844a88277','2c3c5aed7cb8fc26174adc96142132f7',1509439687309,'2c3c5aed7cb8fc26174adc96142132f7',1504426048439,0,'8ada4b6b0d7022a13230e87c06b7b0f1',NULL),('2db0666e762d5da51daf0605571eb6b5','我知道，你已经为自己设计好了一幅美好的蓝图，并且你也正在为实现这一目标努力奋斗着。成功属于不懈追求。','22','我们敢于挑战，伴着坚韧的闪光；我们勇攀高峰，拥有顽强的臂膀；我们是初升的太阳，我们是祖国下一任栋梁，扬起智慧的风帆，在未来的大海上，乘风破浪，扬帆远航！这就是我们的七年级（6）班，一个团结进取的班级，一个锐意进取的班级，一个永不放弃的班级，一个拥有43名兄弟姐妹的大家庭','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2','2c3c5aed7cb8fc26174adc96142132f7',1515984540954,'2c3c5aed7cb8fc26174adc96142132f7',1504425667926,0,'','642da7f0135af02978e2bb8d1b29a007'),('313fb910b28d2fd21538305eddd04965','谁把安逸当成幸福的花朵，那么等到结果时节，他只能望着空枝叹息。','','初二年级3班——“学生是班级的主人，班主任是班级的核心。班级的发展就是学生的发展。良好的班级环境，必须有文化底蕴。” 我们的班训是“诚实做人，踏实做事、扎实学习”。 我们以“团结自律”要求自己，以“超越自我”激励自己。 目标提出了，它还要有个着眼点，经过讨论与选择，我们提出了“增强班级文化底蕴，争创校园书香班级”的口号。','822785df916c59578fa12fe82dbbec79','0232fc6b3c023f34432b2075a9287803','2c3c5aed7cb8fc26174adc96142132f7',1509439824082,'2c3c5aed7cb8fc26174adc96142132f7',1504426599223,0,'ff7998f50ecb56ee33664e5da4c34216',NULL),('577b63693704aa12b3f5d702cb9de0e8','扬起生命之风帆，让涛声为伴奏，扯缆绳作琴弦；吹响理想之号角，奏一曲超越时空的乐声。','','热爱集体，关心集体、团结互助，努力争创五星班级，文明班级','2694c72627b4fd57327452b62c719d1d','e7fb8a6fa7190fc59418245196bf780c','2c3c5aed7cb8fc26174adc96142132f7',1509439636020,'2c3c5aed7cb8fc26174adc96142132f7',1504426119990,0,'9a7280cc52fef2cbd9874f4530cfe6d4',NULL),('598b8abfa057faff30240e466522fbf3','书山路漫漫，以勤奋为首，学海苦茫茫，须刻苦当先！','李龙鑫，何泽富，罗秋，曾恋，朱钰堞，代玲，王立芳，吴亚娟，张彪，段兴勇。','三（4）班的每一位同学珍惜现在的条件，充实自我，发展自己。抓紧每一天，从小事做起，去挥汗，去耕耘，去描绘。愿你们时时用功，步步踏实，迈好人生的第一步。愿你们用理想和汗水编织多姿多彩的青春生活，奏响时代的旋律，与时俱进。','0d01720991c16e9334933276e36c9ee3','00d97699b5e6c1180d0058713a0877f1','98b9ac5e8543274da759f6cbe04a1784',1512633275508,'2c3c5aed7cb8fc26174adc96142132f7',1504426653939,0,'1affc1ff4b8a7eef4dfd3f593a0aa8df',NULL),('6fa6ae218e925a65e5806afad2abccad','鲜花与荆棘同路，泪水伴欢笑齐飞。','','初三1班是我们的家，每个同学都深爱着它。走进初三1班,迎接您的是一张张活泼、可爱的脸。在这个大家庭里，我们互相学习、互相帮助、互相关心，共同进步。虽然我们淘气好动，可充满活力、多才多艺。 我们爱我们这个家，在这个大家庭里，我们学习感恩，学习知识，学习生活，尽情享受着成长的快乐。我们牢记：我自信，我能行！虽然在某些方面我们还存在着不足，但我们相信，只要我们充满信心，团结一致，','6a4e88dbd29c88a8ff22646551ab60fe','efdb96c3893433bb6e878792a4a393e6','2c3c5aed7cb8fc26174adc96142132f7',1509441249516,'2c3c5aed7cb8fc26174adc96142132f7',1504426697404,0,'7f8df8eee46083ea2e0887635ad4179b',NULL),('71081a744fc7df54c0e5a8ab70013e0d','劝君莫惜金缕衣，劝君惜取少年时。\r\n 新的学年，希望初一（3）班同学能继续保持优秀，建设一111111','','初一三班，一个由44名少年郎组成的班集体。这是一群意气风发、激扬文字的少年；这是一个挥斥方遒、指点江山的团体。他们时刻谨记的是那一句“胸藏文墨怀若谷，腹有诗书气自华。”他们希望通过不断学习与奋发向上，成为最绚丽的风景线。而在班级建设中，每名同学都是主力军。书法天地、原创作品、文化角的建设为三班添了几分独特的文化韵味。书香、墨香常伴的教室里，他们初心不改、共此三年。','bb7be4d819bf9e54430deaa2cc65f7de','29e5fbec64a2c75ab0e9e5f3e81325f1','2c3c5aed7cb8fc26174adc96142132f7',1515037137242,'2c3c5aed7cb8fc26174adc96142132f7',1504426205581,0,'190557fe2e8dfe1ddbcf985ad3977396',NULL),('7f09aad57ef6d9c696c5160af94b8c5b','同学们！你们是最好的，拿出百分百的拼搏精神，坚持下来，实现自己的梦想！','罗双英  管育情 李志莲 熊宇洁  李小芳 龚建党  唐德杰  刘相','初三（3）班是一个具有魅力的集体，有着自己独特的风格与灵魂。它是一个多才多艺的集体。三(3）班的同学个个都是一个音符，都有着自己特有的音调与节奏。相信有了他们的组合，才会奏响出动人的旋律.张扬个性，发展特长，让资源共享，让每个孩子都能在德智体美劳等方面，得到全面的发展。让每个孩子都学会做人、学会生活、学会学习，让学习成为他们最大的快乐。','98f37f40cc8ca2c48c0c846a991c019b','a16bbd52393c7d7a149876b2f37dcc10','6e202ebfaad431ed6e3083b1c2b84c87',1513222785760,'2c3c5aed7cb8fc26174adc96142132f7',1504426671439,0,'3ecc31f6194922098f8fc76e512c6b03',NULL),('bca6bccb140e663187c02926ebdb1ee9','也许我的成绩没有排在前列，但我却是最努力的一个\r\n也许今天的我不太惹人注意，但明天的我一定会十分出彩','李可，董玥，刘柯含，王婕','我们是44个单项式，我们各有不同，但我们最终加在一起，成为了初一（2）班这个多项式。我们会经历艰难困苦，但我相信我们最终会找到我们的同类项（共同的目标）。班级宣言：多少悔，无需诉；多少耻，尚未雪；猛回头，奋发起！要自律，肯努力；要自信，不放弃；挥汗雨，永前进！\r\n班训：做最好的自己，我能！','7a8a4ced48c96fa7c2d507202b63acb7','5df402d6c06190d513516426606969fc','2c3c5aed7cb8fc26174adc96142132f7',1515575521328,'2c3c5aed7cb8fc26174adc96142132f7',1504426489991,0,'178f0992129df53f8ef1386497d67143',NULL),('dee4acafd367683c1fc9540b8f70c4be','做人以德，德高人敬：学习靠勤，勤能取胜。','','初二五班共47人，男生有24人，女生有23人，是一个积极向上、奋勇拼搏、勇于进取、朝气蓬勃的班集体。在初一上学期曾获得先进班集体称号，初一上学期大课间比赛获得第二名，广播操比赛第三名。2017年冬季运动会获得初二年级团体总分第一名的好成绩。我们班要把运动场上的拼搏精神用到学习上，学习成绩更上一层楼。','ede086427aa3d73ec7ab90ecc3c39dc9','46f036f4abc7eb08643743070e749f6f','2c3c5aed7cb8fc26174adc96142132f7',1509437643983,'2c3c5aed7cb8fc26174adc96142132f7',1504426574863,0,'5f962f628ae1e4e3d0f35c352291f2e2',NULL),('ea15bd45d3221f1570be842be10bf8a8','是否能赢在起跑线上并不重要。重要的是，让自己始终保持奔跑的状态，最后，成为世上独一无二的自己。','张浩、李要斗，胡梦媛，孙玉佳，尹鑫炎','41张天真烂漫的笑脸，41颗纯真质朴的童心，41个鲜活灵动的生命，构筑成一个温馨的大家庭。Hi，这里是初三2班，我们在这里生活，也在这里学习；在这里，人人都是班干部，个个都是班级的小主人。课堂上，我们碰撞智慧的火花；放学时，我们结伴同行；操场上，我们挥汗如雨；宿舍里，我们相互偎依。2班，我的家！','7f48c56ba0b6539a9a62a0da0ed8400c','143a68774f3a3685c885eb8730b57a08','a18401b5823dd89be5f102d48fc45db6',1513170509060,'2c3c5aed7cb8fc26174adc96142132f7',1504426684835,0,'b00ddf4aef84e466169802a7386cd38a',NULL);
/*!40000 ALTER TABLE `teach_class_card_introduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_card_picture`
--

DROP TABLE IF EXISTS `teach_class_card_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_picture` (
  `id` varchar(50) NOT NULL,
  `pic_name` varchar(255) DEFAULT NULL COMMENT '照片名称',
  `pid` varchar(50) DEFAULT NULL COMMENT '//父级id(pid)   pid为-1表示照片合集，其他为照片,bgzl 为百舸争流图片 hdly 为活动掠影图片',
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级id',
  `class_card_id` varchar(50) DEFAULT NULL COMMENT '班牌id',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '照片地址',
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `pic_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `school_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_picture`
--

LOCK TABLES `teach_class_card_picture` WRITE;
/*!40000 ALTER TABLE `teach_class_card_picture` DISABLE KEYS */;
INSERT INTO `teach_class_card_picture` VALUES ('158f500ac4307afa1d37ce7eda659c78','2.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246603649,0,'classcard/schoolculture/pic/201801/1516246417897.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('2199633a7a8d3cf9484e7e80b81d92ee','14.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516258922664,0,'classcard/schoolculture/pic/201801/1516258883582.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('24dee8e5e320cef94662c8383b358ce7','1.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246552763,0,'classcard/schoolculture/pic/201801/1516246417847.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('2617a5f8383ecc7c111ea378c26b2303','40.jpg','hdly','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181091584,0,'classcard/classspace/pic/201801/1516181070214.jpg','','fff(2)',NULL),('291aea1fcee8ce2a31f8b7f02fa51611','8.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516258746945,0,'classcard/schoolculture/pic/201801/1516258673423.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('2e081a424c81820f0e63419e12896d5c','22.jpg','bgzl','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516240969238,0,'classcard/classspace/pic/201801/1516240968382.jpg','','1111','c74f7f8b253c8762df3d9288f58b1f95'),('2e23a99ad99004c01168be8f628d9d5e','百舸争流.png','bgzl','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516241007688,0,'classcard/classspace/pic/201801/1516241004441.png','classcard/classspace/pic/201801/thumbnail/1516241004441.jpg','444','c74f7f8b253c8762df3d9288f58b1f95'),('34c2738d3cab0201939e20fc2aaa329d','22.jpg','hdly','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181694508,0,'classcard/classspace/pic/201801/1516181690277.jpg','','ggg(2)',NULL),('35773caaa5e1dbcf9635d9636108e796','品牌.jpg','935a6ed7ec3b444725c5ce615242b531','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181670241,0,'classcard/classspace/pic/201801/1516181665200.jpg','','fff',NULL),('3a0779d30c861488dc6eee58539858d9','webwxgeticon.jpg',NULL,NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246443547,0,'classcard/schoolculture/pic/201801/1516246403996.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('585e0335377186303746f7a1f0a11652','班级简介.png','935a6ed7ec3b444725c5ce615242b531','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181670375,0,'classcard/classspace/pic/201801/1516181664852.png','classcard/classspace/pic/201801/thumbnail/1516181664852.jpg','fff',NULL),('66a3f1e55a333ab2a526b552d561dba9','6.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516257373008,0,'classcard/schoolculture/pic/201801/1516257351371.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('72df1cc75c282c639a669224b6a60fb7','4.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246603750,0,'classcard/schoolculture/pic/201801/1516246417969.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('857ce47a8a2347cc4214559b5206ced6','班级简介.png','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516257440377,0,'classcard/schoolculture/pic/201801/1516257426583.png','classcard/schoolculture/pic/201801/thumbnail/1516257426583.jpg',NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('935a6ed7ec3b444725c5ce615242b531',NULL,'-1','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181670125,0,'classcard/classspace/pic/201801/1516181664926.jpg',NULL,'fff',NULL),('a012984a186034af44b5cc7dba26b1f8','班级简介.png','hdly','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181091618,0,'classcard/classspace/pic/201801/1516181070121.png','classcard/classspace/pic/201801/thumbnail/1516181070121.jpg','fff(1)',NULL),('a8e6f782995761083902a46a74ce4e79','3.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246603684,0,'classcard/schoolculture/pic/201801/1516246417949.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('aeb4a4348428ef03f25efcd16d0669e5','40.jpg','935a6ed7ec3b444725c5ce615242b531','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181670199,0,'classcard/classspace/pic/201801/1516181664926.jpg','','fff',NULL),('b75d963682c4c1766c2d87f5c071b3d7','9.jpg','bgzl','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516240636386,0,'classcard/classspace/pic/201801/1516240635192.jpg','','sss',NULL),('bb2e55c529c4561a0c3f08ca72f54421','百舸争流.png','hdly','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516181694562,0,'classcard/classspace/pic/201801/1516181690140.png','classcard/classspace/pic/201801/thumbnail/1516181690140.jpg','ggg(1)',NULL),('cacace8f17e25ba44406b482a0dc3c14','5.jpg','f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246603717,0,'classcard/schoolculture/pic/201801/1516246417991.jpg',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95'),('f4233ee4829a72e6d9dc40694262fe2e',NULL,NULL,NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246489997,0,NULL,NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95');
/*!40000 ALTER TABLE `teach_class_card_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_card_school_culture`
--

DROP TABLE IF EXISTS `teach_class_card_school_culture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_school_culture` (
  `id` varchar(50) NOT NULL,
  `video_id` varchar(50) DEFAULT NULL COMMENT '宣传视频	',
  `school_badge_pic_id` varchar(50) DEFAULT NULL COMMENT '校徽',
  `school_pic_id` varchar(50) DEFAULT NULL COMMENT '校园图片的父级',
  `school_id` varchar(50) DEFAULT NULL,
  `introduction` text,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0' COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_school_culture`
--

LOCK TABLES `teach_class_card_school_culture` WRITE;
/*!40000 ALTER TABLE `teach_class_card_school_culture` DISABLE KEYS */;
INSERT INTO `teach_class_card_school_culture` VALUES ('627b80253f711fe69bb8ffd9167c65c6','14a296d232211c8f46ceb92b096e3e93','3a0779d30c861488dc6eee58539858d9','f4233ee4829a72e6d9dc40694262fe2e','c74f7f8b253c8762df3d9288f58b1f95','这个是第一版校园简介4','2c3c5aed7cb8fc26174adc96142132f7',1516246617941,'2c3c5aed7cb8fc26174adc96142132f7',1516258923208,0);
/*!40000 ALTER TABLE `teach_class_card_school_culture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_card_video`
--

DROP TABLE IF EXISTS `teach_class_card_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_card_video` (
  `id` varchar(50) NOT NULL,
  `video_name` varchar(255) DEFAULT NULL,
  `class_id` varchar(45) DEFAULT NULL,
  `class_card_id` varchar(45) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `thumbnail_url` varchar(255) DEFAULT NULL COMMENT '压缩路径',
  `video_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `school_id` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_card_video`
--

LOCK TABLES `teach_class_card_video` WRITE;
/*!40000 ALTER TABLE `teach_class_card_video` DISABLE KEYS */;
INSERT INTO `teach_class_card_video` VALUES ('14a296d232211c8f46ceb92b096e3e93','wx_camera_1504964901067.mp4',NULL,NULL,'classcard/schoolculture/video/201801/1516257577165.mp4',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516257597379,0),('4192e580b4c129c2a6768acc7dacda8a','wx_camera_1500002099552.mp4','60e299fff1aaec72d53cceb471a423b9','095d164675c7fbc760f9e5b1cc826e4f','classcard/classspace/video/201801/1515727047647.mp4',NULL,NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515727049130,0),('642da7f0135af02978e2bb8d1b29a007','cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2','classcard/classspace/video/201801/1515984539391.mp4',NULL,NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515984540913,0),('6ec4f3a6764f3618ee1ad6c3fd148291','VID_20170727_190740.mp4',NULL,NULL,'classcard/schoolculture/video/201801/1516246412122.mp4',NULL,NULL,'c74f7f8b253c8762df3d9288f58b1f95',NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1516246465917,0),('a23049e22f3b80cf24826d64cc820284','cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4','3acd1f05fd4d3f749dc6bdb2c0d3f792','3984e35e31a71ab7681c1bec3e2895e2','classcard/classspace/video/201801/1515742915115.mp4',NULL,NULL,NULL,NULL,NULL,'2c3c5aed7cb8fc26174adc96142132f7',1515742926181,0);
/*!40000 ALTER TABLE `teach_class_card_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_ref_classcard_app`
--

DROP TABLE IF EXISTS `teach_ref_classcard_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_ref_classcard_app` (
  `id` varchar(50) NOT NULL,
  `class_card_id` varchar(50) DEFAULT NULL,
  `class_card_app_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_ref_classcard_app`
--

LOCK TABLES `teach_ref_classcard_app` WRITE;
/*!40000 ALTER TABLE `teach_ref_classcard_app` DISABLE KEYS */;
INSERT INTO `teach_ref_classcard_app` VALUES ('0134b0975e81acad0eb3f093c366fb3c','160e5e505d55bf16f995f8b7b56cb721','bedf4c36e2eb931a7a5105ef985d2cad','c74f7f8b253c8762df3d9288f58b1f95'),('f5e21f7da120abae12b6a1c44ba0dc9f','160e5e505d55bf16f995f8b7b56cb721','7093261787aa615fcec4e7e2e75e6eaf','c74f7f8b253c8762df3d9288f58b1f95');
/*!40000 ALTER TABLE `teach_ref_classcard_app` ENABLE KEYS */;
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
INSERT INTO `teach_ref_classcard_config` VALUES ('192cc6a4c76814870d5f55a6ea654143','c3d1b69fc3187b8941861eda17cd4b98','a7505cea0f18bbd28071054844a88277','c74f7f8b253c8762df3d9288f58b1f95',0),('26a68cafd57e68a8d844101ce15b1d39','9d869c3e1d2b26c08f6623952cb5d135','0232fc6b3c023f34432b2075a9287803','c74f7f8b253c8762df3d9288f58b1f95',0),('317e37c7359e6f89486737c890591ac3','d16102c698892b0cedbbcee5afbb1329','095d164675c7fbc760f9e5b1cc826e4f','c74f7f8b253c8762df3d9288f58b1f95',0),('4a4be88d588a20be345f8303a5cf70d8','c3813f9f696751c49fda1468bf8e8b5f','035d4b761592efad5b16debead7217f5','c74f7f8b253c8762df3d9288f58b1f95',0),('4c28a316030019bf0881d84366c8b441','c3d1b69fc3187b8941861eda17cd4b98','0a226fc9e21528cc50a3cce6e46dc0f1','c74f7f8b253c8762df3d9288f58b1f95',0),('575c4844fdfc9c29477d3cf553263874','7c55760f164a17a9f2cb449d664e460c','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('65cb93a77540e79238d653dffa7aeebc','c3d1b69fc3187b8941861eda17cd4b98','a16bbd52393c7d7a149876b2f37dcc10','c74f7f8b253c8762df3d9288f58b1f95',0),('72942f6ac7d9f12adb305f9ae2aaaa74','d16102c698892b0cedbbcee5afbb1329','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('738fad4f729a0253ee524a73e65fbc76','c3813f9f696751c49fda1468bf8e8b5f','160e5e505d55bf16f995f8b7b56cb721','c74f7f8b253c8762df3d9288f58b1f95',0),('7580753d981cf661f61e9567d879b390','c3813f9f696751c49fda1468bf8e8b5f','46f036f4abc7eb08643743070e749f6f','c74f7f8b253c8762df3d9288f58b1f95',0),('80a07fec35063a6053a0364f1ae6bf69','c3813f9f696751c49fda1468bf8e8b5f','89cf10f5f5f59a7d7ec60b088c4ec29e','c74f7f8b253c8762df3d9288f58b1f95',0),('8cd43a2947f5eb413e721683fdea5b4f','d16102c698892b0cedbbcee5afbb1329','5df402d6c06190d513516426606969fc','c74f7f8b253c8762df3d9288f58b1f95',0),('98d4196af1993867aed0cdc8bf470476','c3d1b69fc3187b8941861eda17cd4b98','efdb96c3893433bb6e878792a4a393e6','c74f7f8b253c8762df3d9288f58b1f95',0),('9e3715023bfbd6aaa95b52ec4431941a','d16102c698892b0cedbbcee5afbb1329','3984e35e31a71ab7681c1bec3e2895e2','c74f7f8b253c8762df3d9288f58b1f95',0),('acc13bfab947c1a5fcd51c2cd32e9075','c3813f9f696751c49fda1468bf8e8b5f','6b043ea74d5bc53ec65cd29c8a6ea761','c74f7f8b253c8762df3d9288f58b1f95',0),('b67d12fdafd540c1b965622dc9896d71','c3813f9f696751c49fda1468bf8e8b5f','0232fc6b3c023f34432b2075a9287803','c74f7f8b253c8762df3d9288f58b1f95',0),('d3be6aa09a298acf24e8b75d8aef9950','9d869c3e1d2b26c08f6623952cb5d135','29e5fbec64a2c75ab0e9e5f3e81325f1','c74f7f8b253c8762df3d9288f58b1f95',0),('d63dc10f84adbcd9c7cdbbeb6ccdf7fa','d16102c698892b0cedbbcee5afbb1329','29e5fbec64a2c75ab0e9e5f3e81325f1','c74f7f8b253c8762df3d9288f58b1f95',0),('d98a3102222ce1326eb184b6971b56bb','c3813f9f696751c49fda1468bf8e8b5f','21bdf0bf44b33ce57d98b10bf1947ca6','c74f7f8b253c8762df3d9288f58b1f95',0),('d9e6c5dad7e48cf15e3190607b044e8c','c3d1b69fc3187b8941861eda17cd4b98','e7fb8a6fa7190fc59418245196bf780c','c74f7f8b253c8762df3d9288f58b1f95',0),('dffc9a4149458e6d1ba1295a00a58ec1','c3d1b69fc3187b8941861eda17cd4b98','00d97699b5e6c1180d0058713a0877f1','c74f7f8b253c8762df3d9288f58b1f95',0),('f545e56cd559d241ec8f11461df980eb','c3d1b69fc3187b8941861eda17cd4b98','143a68774f3a3685c885eb8730b57a08','c74f7f8b253c8762df3d9288f58b1f95',0),('fa9228f0172fd945e7aa9807fbbf7b52','7c55760f164a17a9f2cb449d664e460c','efdb96c3893433bb6e878792a4a393e6','c74f7f8b253c8762df3d9288f58b1f95',0),('fb5504221f4d422d72129bb7a6916552','d16102c698892b0cedbbcee5afbb1329','a7505cea0f18bbd28071054844a88277','c74f7f8b253c8762df3d9288f58b1f95',0);
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
INSERT INTO `teach_ref_classcard_config_screen_off` VALUES ('0fa960db2a631cb1af27c77741987de7','c3d1b69fc3187b8941861eda17cd4b98','2,3,4,',-19440000,-12180000,0),('1a0a2ff32647221c64cb3137838b3c35','9d869c3e1d2b26c08f6623952cb5d135','2,4,',43920000,47520000,0),('585c314d7649f03f20d39e1c93a2f429','c3813f9f696751c49fda1468bf8e8b5f','1,',46260000,53460000,0),('5c6a35fcadeac4ce2191575811eba003','7c55760f164a17a9f2cb449d664e460c','',48060000,51720000,0),('63682516aada437a1d176c50ecc17d13','c3813f9f696751c49fda1468bf8e8b5f','1,',53700000,57300000,0),('6e7eff8f9672471f581ad8055cf7b4fe','d16102c698892b0cedbbcee5afbb1329','2,3,',45000000,45120000,0),('7b1992c3fa9c01e5b840da58e761a322','d16102c698892b0cedbbcee5afbb1329','2,3,',37740000,41460000,0),('939a565c0cccb0fe57622954b5e0686d','c3d1b69fc3187b8941861eda17cd4b98','2,3,4,',36240000,39840000,0),('9c5c88fb5ef7e84c06ba11042585da0a','6f080982afc35e343a9f9aaa732b3319','',48060000,51720000,0),('a5e2d9a6fd757d53045a6caf797c583c','d16102c698892b0cedbbcee5afbb1329','2,3,',52140000,55740000,0),('bc71fdee00e85e9ea07507ec6a83dc6b','9d869c3e1d2b26c08f6623952cb5d135','2,4,',36660000,36720000,0),('c21caa6dd0c2b95127eed344f93a3143','9d869c3e1d2b26c08f6623952cb5d135','2,4,',41580000,41820000,0),('ce1f442d7d36e254f1909a3299076ab6','6f080982afc35e343a9f9aaa732b3319','',15120000,11460000,0),('e203fbb697eb6b39e9b17eb71ca6f475','7c55760f164a17a9f2cb449d664e460c','',15120000,11460000,0),('eef62a6a78a4ecfeac6e573509729c55','7c55760f164a17a9f2cb449d664e460c','',-16620000,-5820000,0);
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

-- Dump completed on 2018-01-18 16:19:52
