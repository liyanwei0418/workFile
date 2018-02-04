/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17 : Database - gk_platform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gk_platform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gk_platform`;

/*Table structure for table `he_weather` */

DROP TABLE IF EXISTS `he_weather`;

CREATE TABLE `he_weather` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `query_date` varchar(20) DEFAULT NULL COMMENT '查询日期',
  `content` text COMMENT '天气内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `he_weather` */

LOCK TABLES `he_weather` WRITE;

UNLOCK TABLES;

/*Table structure for table `oa_notify` */

DROP TABLE IF EXISTS `oa_notify`;

CREATE TABLE `oa_notify` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `type` int(10) DEFAULT '0' COMMENT '类型[0 root， 1区级通知， 2校级通知]',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `files` varchar(2000) DEFAULT NULL COMMENT '附件',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `column_id` varchar(50) DEFAULT NULL COMMENT '通知所属栏目',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `publish_time` bigint(20) DEFAULT NULL COMMENT '发布时间，定时发布',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知通告';

/*Data for the table `oa_notify` */

LOCK TABLES `oa_notify` WRITE;

UNLOCK TABLES;

/*Table structure for table `oa_notify_column` */

DROP TABLE IF EXISTS `oa_notify_column`;

CREATE TABLE `oa_notify_column` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf32 DEFAULT NULL COMMENT '类型名字',
  `school_id` varchar(50) DEFAULT NULL COMMENT '属于机构ID',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知通告栏目';

/*Data for the table `oa_notify_column` */

LOCK TABLES `oa_notify_column` WRITE;

UNLOCK TABLES;

/*Table structure for table `oa_notify_record` */

DROP TABLE IF EXISTS `oa_notify_record`;

CREATE TABLE `oa_notify_record` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `notify_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '通知通告ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '教职工ID[teacher]',
  `read_flag` int(10) DEFAULT '0' COMMENT '阅读标记',
  `read_date` bigint(20) DEFAULT NULL COMMENT '阅读时间',
  `user_type` int(10) DEFAULT '0' COMMENT '备用字段',
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`) USING BTREE,
  KEY `oa_notify_record_user_id` (`user_id`) USING BTREE,
  KEY `oa_notify_record_read_flag` (`read_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知通告发送记录';

/*Data for the table `oa_notify_record` */

LOCK TABLES `oa_notify_record` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_class_section` */

DROP TABLE IF EXISTS `org_class_section`;

CREATE TABLE `org_class_section` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '简称',
  `limit_age` int(10) DEFAULT NULL COMMENT '入学年龄',
  `section_year` int(10) DEFAULT NULL COMMENT '学制[学校6，初中3，高中3等等]',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学段-学制表';

/*Data for the table `org_class_section` */

LOCK TABLES `org_class_section` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_course` */

DROP TABLE IF EXISTS `org_course`;

CREATE TABLE `org_course` (
  `subject_id` varchar(50) NOT NULL COMMENT '课程id',
  `subject_name` varchar(100) DEFAULT NULL COMMENT '课程名',
  `subject_short_name` varchar(50) DEFAULT NULL COMMENT '课程简称',
  `subject_type` varchar(50) DEFAULT NULL COMMENT '课程类型（关联teach_task_config的config_id course_type）',
  `subject_type_name` varchar(50) DEFAULT NULL COMMENT '课程类型名',
  `subject_score` double(10,2) DEFAULT NULL COMMENT '课程满分',
  `subject_pass_score` double(10,2) DEFAULT NULL COMMENT '课程及格分数',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `update_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `nj` varchar(10) DEFAULT NULL COMMENT '授课年级(多个年级用逗号拼接，所以用varchar类型存储数据)',
  `remarks` varchar(255) DEFAULT NULL,
  `teachYear` varchar(50) DEFAULT NULL COMMENT '学年',
  `semester` varchar(50) DEFAULT NULL COMMENT '学期',
  `class_room_type` varchar(50) DEFAULT NULL COMMENT '上课教室类型',
  `del_flag` int(10) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_course` */

LOCK TABLES `org_course` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_department` */

DROP TABLE IF EXISTS `org_department`;

CREATE TABLE `org_department` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父部门id',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID',
  `no` varchar(50) DEFAULT NULL COMMENT '部门编号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '部门简称',
  `master_id` varchar(50) DEFAULT NULL COMMENT '部门主管ID',
  `master_name` varchar(100) DEFAULT NULL COMMENT '部门主任名称',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `org_department` */

LOCK TABLES `org_department` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_grade_class` */

DROP TABLE IF EXISTS `org_grade_class`;

CREATE TABLE `org_grade_class` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '班级简称',
  `xd` varchar(50) DEFAULT NULL COMMENT '学段ID',
  `nj` int(10) DEFAULT NULL COMMENT '年级[数字表示 如 2]',
  `bh` int(10) DEFAULT NULL COMMENT '班号[班级顺序]',
  `bjlx` int(10) DEFAULT NULL COMMENT '班级类型[0普通班级，1其他]',
  `xq` varchar(255) DEFAULT NULL COMMENT '校区',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '教学周期id(关联teach_cycle的id)',
  `rxnd` bigint(20) DEFAULT NULL COMMENT '入学年度',
  `master_id` int(11) DEFAULT NULL COMMENT '班主任ID',
  `master_name` varchar(100) DEFAULT NULL COMMENT '班主任名称',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级表';

/*Data for the table `org_grade_class` */

LOCK TABLES `org_grade_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_school` */

DROP TABLE IF EXISTS `org_school`;

CREATE TABLE `org_school` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父级编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `ename` varchar(255) DEFAULT NULL COMMENT '学校英文名',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `xz` varchar(255) DEFAULT NULL COMMENT '校址',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` int(10) DEFAULT NULL COMMENT '机构类型[1 区平台  2 学校]',
  `grade` int(10) DEFAULT NULL COMMENT '机构等级【1 小学  2 初中 3 高中  4 一贯制】',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logoUrl',
  `bg_picture` varchar(255) DEFAULT NULL COMMENT '背景图片',
  `home_text` varchar(255) DEFAULT NULL COMMENT '首页文字设置',
  `bottom_text` varchar(255) DEFAULT NULL COMMENT '底部信息设置',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `m_id` varchar(50) DEFAULT NULL COMMENT '管理员id',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `patriarch_rule` int(10) DEFAULT NULL COMMENT '生产家长账号规则[1按照名字全拼]',
  `student_rule` int(10) DEFAULT NULL COMMENT '生产学生账号规则[1按照名字全拼]',
  `teacher_rule` int(10) DEFAULT NULL COMMENT '生产教师账号规则[1按照名字全拼]',
  `short_flag` varchar(100) DEFAULT NULL COMMENT '机构短标识，全局唯一',
  `deploy_url` varchar(255) DEFAULT NULL COMMENT '部署路径-强制跳转的标识',
  `userable` varchar(64) DEFAULT NULL COMMENT '是否启用',
  `primary_persion` varchar(64) DEFAULT NULL COMMENT '主负责人',
  `deputy_persion` varchar(64) DEFAULT NULL COMMENT '副负责人',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表【教育局、学校】';

/*Data for the table `org_school` */

LOCK TABLES `org_school` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_school_type` */

DROP TABLE IF EXISTS `org_school_type`;

CREATE TABLE `org_school_type` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `school_id` varchar(50) DEFAULT NULL COMMENT '学校ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(10) DEFAULT NULL COMMENT '联系电话',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构分类';

/*Data for the table `org_school_type` */

LOCK TABLES `org_school_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_subject` */

DROP TABLE IF EXISTS `org_subject`;

CREATE TABLE `org_subject` (
  `subject_id` varchar(50) NOT NULL COMMENT '课程id',
  `subject_name` varchar(100) DEFAULT NULL COMMENT '课程名',
  `subject_short_name` varchar(50) DEFAULT NULL COMMENT '课程简称',
  `subject_type` varchar(50) DEFAULT NULL COMMENT '课程类型（关联teach_task_config的config_id course_type）',
  `subject_type_name` varchar(50) DEFAULT NULL COMMENT '课程类型名',
  `subject_score` double(10,2) DEFAULT NULL COMMENT '课程满分',
  `subject_pass_score` double(10,2) DEFAULT NULL COMMENT '课程及格分数',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `update_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(10) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_subject` */

LOCK TABLES `org_subject` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_title` */

DROP TABLE IF EXISTS `org_title`;

CREATE TABLE `org_title` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `school_id` varchar(64) DEFAULT NULL COMMENT '所属学校',
  `mc` varchar(64) DEFAULT NULL COMMENT '职务名称',
  `jb` varchar(64) DEFAULT NULL COMMENT '职务级别',
  `px` varchar(64) DEFAULT NULL COMMENT '职务排序',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职务管理';

/*Data for the table `org_title` */

LOCK TABLES `org_title` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_app_role` */

DROP TABLE IF EXISTS `ref_app_role`;

CREATE TABLE `ref_app_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  `app_id` varchar(50) NOT NULL COMMENT '应用ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色，应用映射表';

/*Data for the table `ref_app_role` */

LOCK TABLES `ref_app_role` WRITE;

insert  into `ref_app_role`(`role_id`,`app_id`) values ('6390a03083116fe48dc1ef96c11aee58','a7a0c7724b38c52fdc73767070ccf6ca'),('1c0204e3e6519185f048bae0dd55a8ba','5499b97c06adf875c22c7c1e1f6cdf72'),('d0751018ca38f9bb1c9a13cb3fbb61f7','d86b3f2ca6f25ffd772332be2e6d65df'),('ef3c113aa05ca8511ff69bfaa1f0dd95','1fca23c8f69bbdb6bf7afc9de13a0ccf'),('1c0204e3e6519185f048bae0dd55a266','1fca23c8f69bbdb6bf7afc9de13a0ccd'),('1c0204e3e6519185f048bae0dd55a255','1fca23c8f69bbdb6bf7afc9de13a0cca'),('1c0204e3e6519185f048bae0dd55a233','1fca23c8f69bbdb6bf7afc9de13a0cc0');

UNLOCK TABLES;

/*Table structure for table `ref_commonly_used_app` */

DROP TABLE IF EXISTS `ref_commonly_used_app`;

CREATE TABLE `ref_commonly_used_app` (
  `app_id` varchar(50) DEFAULT NULL COMMENT '应用id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id（sys_user的id）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ref_commonly_used_app` */

LOCK TABLES `ref_commonly_used_app` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_my_app` */

DROP TABLE IF EXISTS `ref_my_app`;

CREATE TABLE `ref_my_app` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `app_id` varchar(50) NOT NULL COMMENT '应用ID',
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型【1:教师, 2:学生, 3:家长】',
  PRIMARY KEY (`user_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-我的应用';

/*Data for the table `ref_my_app` */

LOCK TABLES `ref_my_app` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_role_menu` */

DROP TABLE IF EXISTS `ref_role_menu`;

CREATE TABLE `ref_role_menu` (
  `role_id` varchar(50) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(50) NOT NULL COMMENT '菜单编号',
  `school_id` varchar(50) NOT NULL COMMENT '学校、机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

/*Data for the table `ref_role_menu` */

LOCK TABLES `ref_role_menu` WRITE;

insert  into `ref_role_menu`(`role_id`,`menu_id`,`school_id`) values ('1c0204e3e6519185f048bae0dd55a000','0','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942111','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942444','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942555','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942666','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942777','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942888','0'),('1c0204e3e6519185f048bae0dd55a000','a88235ee2abf3fa4418033c875d10345','0'),('1c0204e3e6519185f048bae0dd55a000','ba7dae25f2b12abf71fe949ba164f880','0'),('1c0204e3e6519185f048bae0dd55a000','3cfc77bb791b130cbbeb049eee55621a','0'),('1c0204e3e6519185f048bae0dd55a000','9d984765fdd49214115cdc9cb364350a','0'),('1c0204e3e6519185f048bae0dd55a000','e79c741d3eb6417716935253214a9289','0'),('1c0204e3e6519185f048bae0dd55a000','6364431286830059f26a6ed8ff45849b','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942e86','0'),('1c0204e3e6519185f048bae0dd55a000','356bd1640d9cdf9cd75e467bc396efdb','0'),('1c0204e3e6519185f048bae0dd55a000','4657b2df16a8698e47be0e1d41879542','0'),('1c0204e3e6519185f048bae0dd55a000','7e1c96eefcd3d5ef19668065e0a44c9e','0'),('1c0204e3e6519185f048bae0dd55a000','b22e786ede60bbe8fc8ab07b9eb9505e','0'),('1c0204e3e6519185f048bae0dd55a000','81468a3d8422b48ee3bef9098cb808d0','0'),('1c0204e3e6519185f048bae0dd55a000','604ad67e7313d54db9bf1e989a88dd58','0'),('1c0204e3e6519185f048bae0dd55a000','54781213ae9ff5347cbee4848b8ede78','0'),('1c0204e3e6519185f048bae0dd55a000','8a0894d3ba5972e80a358a9b81235936','0'),('1c0204e3e6519185f048bae0dd55a000','9c7e431301ebd21169284d8e828a9c1f','0'),('1c0204e3e6519185f048bae0dd55a000','c21452a46e7eb6914654d9de47356f89','0'),('1c0204e3e6519185f048bae0dd55a000','79495b8fc425d6f76435ef0565936dbb','0'),('1c0204e3e6519185f048bae0dd55a000','a5378a7a373cf7f07461bf5022566805','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942e22','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942333','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942144','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942155','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942166','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942177','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942999','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942100','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942110','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942121','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942e11','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942191','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942201','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942221','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942222','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942231','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942241','0'),('1c0204e3e6519185f048bae0dd55a000','28059d1f5f36bfd464d8ec4967942251','0'),('1c0204e3e6519185f048bae0dd55a233','0','0'),('1c0204e3e6519185f048bae0dd55a233','28059d1f5f36bfd464d8ec4967942111','0'),('1c0204e3e6519185f048bae0dd55a233','28059d1f5f36bfd464d8ec4967942444','0'),('1c0204e3e6519185f048bae0dd55a233','28059d1f5f36bfd464d8ec4967942555','0'),('1c0204e3e6519185f048bae0dd55a233','28059d1f5f36bfd464d8ec4967942666','0'),('1c0204e3e6519185f048bae0dd55a233','28059d1f5f36bfd464d8ec4967942777','0'),('1c0204e3e6519185f048bae0dd55a255','0','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942e11','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942191','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942201','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942221','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942222','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942231','0'),('1c0204e3e6519185f048bae0dd55a255','28059d1f5f36bfd464d8ec4967942241','0'),('1c0204e3e6519185f048bae0dd55a266','0','0'),('1c0204e3e6519185f048bae0dd55a266','28059d1f5f36bfd464d8ec4967942333','0'),('1c0204e3e6519185f048bae0dd55a266','28059d1f5f36bfd464d8ec4967942144','0'),('1c0204e3e6519185f048bae0dd55a266','28059d1f5f36bfd464d8ec4967942155','0'),('1c0204e3e6519185f048bae0dd55a266','28059d1f5f36bfd464d8ec4967942166','0'),('1c0204e3e6519185f048bae0dd55a333','0','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942111','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942444','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942555','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942666','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942777','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942888','0'),('1c0204e3e6519185f048bae0dd55a333','a88235ee2abf3fa4418033c875d10345','0'),('1c0204e3e6519185f048bae0dd55a333','ba7dae25f2b12abf71fe949ba164f880','0'),('1c0204e3e6519185f048bae0dd55a333','3cfc77bb791b130cbbeb049eee55621a','0'),('1c0204e3e6519185f048bae0dd55a333','9d984765fdd49214115cdc9cb364350a','0'),('1c0204e3e6519185f048bae0dd55a333','e79c741d3eb6417716935253214a9289','0'),('1c0204e3e6519185f048bae0dd55a333','6364431286830059f26a6ed8ff45849b','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942e86','0'),('1c0204e3e6519185f048bae0dd55a333','356bd1640d9cdf9cd75e467bc396efdb','0'),('1c0204e3e6519185f048bae0dd55a333','4657b2df16a8698e47be0e1d41879542','0'),('1c0204e3e6519185f048bae0dd55a333','7e1c96eefcd3d5ef19668065e0a44c9e','0'),('1c0204e3e6519185f048bae0dd55a333','b22e786ede60bbe8fc8ab07b9eb9505e','0'),('1c0204e3e6519185f048bae0dd55a333','81468a3d8422b48ee3bef9098cb808d0','0'),('1c0204e3e6519185f048bae0dd55a333','604ad67e7313d54db9bf1e989a88dd58','0'),('1c0204e3e6519185f048bae0dd55a333','54781213ae9ff5347cbee4848b8ede78','0'),('1c0204e3e6519185f048bae0dd55a333','8a0894d3ba5972e80a358a9b81235936','0'),('1c0204e3e6519185f048bae0dd55a333','9c7e431301ebd21169284d8e828a9c1f','0'),('1c0204e3e6519185f048bae0dd55a333','c21452a46e7eb6914654d9de47356f89','0'),('1c0204e3e6519185f048bae0dd55a333','79495b8fc425d6f76435ef0565936dbb','0'),('1c0204e3e6519185f048bae0dd55a333','a5378a7a373cf7f07461bf5022566805','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942e22','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942333','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942144','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942155','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942166','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942177','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942e11','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942191','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942201','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942221','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942222','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942231','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942241','0'),('1c0204e3e6519185f048bae0dd55a333','28059d1f5f36bfd464d8ec4967942251','0'),('1c0204e3e6519185f048bae0dd55a8ba','0','0'),('1c0204e3e6519185f048bae0dd55a8ba','6364431286830059f26a6ed8ff45849b','0'),('1c0204e3e6519185f048bae0dd55a8ba','356bd1640d9cdf9cd75e467bc396efdb','0'),('1c0204e3e6519185f048bae0dd55a8ba','4657b2df16a8698e47be0e1d41879542','0'),('6390a03083116fe48dc1ef96c11aee58','0','0'),('6390a03083116fe48dc1ef96c11aee58','a88235ee2abf3fa4418033c875d10345','0'),('6390a03083116fe48dc1ef96c11aee58','3cfc77bb791b130cbbeb049eee55621a','0'),('6390a03083116fe48dc1ef96c11aee58','9d984765fdd49214115cdc9cb364350a','0'),('6390a03083116fe48dc1ef96c11aee58','e79c741d3eb6417716935253214a9289','0'),('d0751018ca38f9bb1c9a13cb3fbb61f7','0','0'),('d0751018ca38f9bb1c9a13cb3fbb61f7','7e1c96eefcd3d5ef19668065e0a44c9e','0'),('d0751018ca38f9bb1c9a13cb3fbb61f7','81468a3d8422b48ee3bef9098cb808d0','0'),('d0751018ca38f9bb1c9a13cb3fbb61f7','604ad67e7313d54db9bf1e989a88dd58','0'),('d0751018ca38f9bb1c9a13cb3fbb61f7','54781213ae9ff5347cbee4848b8ede78','0'),('ef3c113aa05ca8511ff69bfaa1f0dd95','0','0'),('ef3c113aa05ca8511ff69bfaa1f0dd95','8a0894d3ba5972e80a358a9b81235936','0'),('ef3c113aa05ca8511ff69bfaa1f0dd95','c21452a46e7eb6914654d9de47356f89','0'),('ef3c113aa05ca8511ff69bfaa1f0dd95','79495b8fc425d6f76435ef0565936dbb','0'),('ef3c113aa05ca8511ff69bfaa1f0dd95','a5378a7a373cf7f07461bf5022566805','0'),('cc5353cce83d328cae1169b391f5d328','0','0'),('cc5353cce83d328cae1169b391f5d328','a88235ee2abf3fa4418033c875d10345','0'),('cc5353cce83d328cae1169b391f5d328','3cfc77bb791b130cbbeb049eee55621a','0'),('cc5353cce83d328cae1169b391f5d328','9d984765fdd49214115cdc9cb364350a','0'),('cc5353cce83d328cae1169b391f5d328','e79c741d3eb6417716935253214a9289','0'),('1c0204e3e6519185f048bae0dd55a777','0','0'),('1c0204e3e6519185f048bae0dd55a777','4cf4ea5120e7a67e2e23f510fd2c5a2b','0'),('1c0204e3e6519185f048bae0dd55a777','28059d1f5f36bfd464d8ec4967942e22','0'),('1c0204e3e6519185f048bae0dd55a777','28059d1f5f36bfd464d8ec4967942333','0'),('1c0204e3e6519185f048bae0dd55a777','28059d1f5f36bfd464d8ec4967942144','0');

UNLOCK TABLES;

/*Table structure for table `ref_role_school` */

DROP TABLE IF EXISTS `ref_role_school`;

CREATE TABLE `ref_role_school` (
  `role_id` varchar(50) NOT NULL COMMENT '角色编号',
  `school_id` varchar(50) NOT NULL COMMENT '机构编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结构-角色映射（机构有哪些角色）';

/*Data for the table `ref_role_school` */

LOCK TABLES `ref_role_school` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_school_app` */

DROP TABLE IF EXISTS `ref_school_app`;

CREATE TABLE `ref_school_app` (
  `app_id` varchar(50) NOT NULL COMMENT '应用ID',
  `school_id` varchar(50) NOT NULL COMMENT '机构ID',
  `app_status` int(10) DEFAULT NULL COMMENT '应用状态(1 已推送, 2 已上线, 3 已下线 4 其他)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构，应用映射表';

/*Data for the table `ref_school_app` */

LOCK TABLES `ref_school_app` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_teacher_class` */

DROP TABLE IF EXISTS `ref_teacher_class`;

CREATE TABLE `ref_teacher_class` (
  `teacher_id` varchar(50) NOT NULL COMMENT '老师ID 关联user_teacher id',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0：教师， 1：班主任 2：副班主任',
  `class_id` varchar(50) NOT NULL COMMENT '班级ID 关联 org_grade_class  id',
  `cycle_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师-班级';

/*Data for the table `ref_teacher_class` */

LOCK TABLES `ref_teacher_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_user_role` */

DROP TABLE IF EXISTS `ref_user_role`;

CREATE TABLE `ref_user_role` (
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色编号',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `ref_user_role` */

LOCK TABLES `ref_user_role` WRITE;

insert  into `ref_user_role`(`user_id`,`role_id`,`school_id`) values ('1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee','1c0204e3e6519185f048bae0dd55a000','0');

UNLOCK TABLES;

/*Table structure for table `scan_log` */

DROP TABLE IF EXISTS `scan_log`;

CREATE TABLE `scan_log` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `mac` varchar(255) NOT NULL COMMENT '设备mac地址',
  `ring_num` int(10) DEFAULT NULL COMMENT '手环编号',
  `ring_signal` int(10) DEFAULT NULL COMMENT '手环信号',
  `station_mac` varchar(255) DEFAULT '0' COMMENT '基站mac',
  `location` varchar(255) DEFAULT NULL COMMENT '位置【section_grade_class_index】\r\n学段、年级、班级、序号\r\n中间用 "_" 隔开',
  `name` varchar(255) DEFAULT NULL COMMENT '教室名',
  `student_id` varchar(50) DEFAULT NULL COMMENT '学生ID',
  `student_name` varchar(100) DEFAULT NULL COMMENT '学生姓名',
  `type` int(10) DEFAULT NULL COMMENT '基站类型【0扫描数据，1计算发送数据】',
  `status` int(10) DEFAULT NULL COMMENT '设备状态[0:正常, 1:异常]',
  `last_update` bigint(20) DEFAULT NULL COMMENT '更新时间戳',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扫描信息表';

/*Data for the table `scan_log` */

LOCK TABLES `scan_log` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_class_section` */

DROP TABLE IF EXISTS `sync_class_section`;

CREATE TABLE `sync_class_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `limit_age` int(11) DEFAULT NULL,
  `section_year` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_class_section` */

LOCK TABLES `sync_class_section` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_department` */

DROP TABLE IF EXISTS `sync_department`;

CREATE TABLE `sync_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `master_id` varchar(255) DEFAULT NULL,
  `master_name` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_department` */

LOCK TABLES `sync_department` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_grade_class` */

DROP TABLE IF EXISTS `sync_grade_class`;

CREATE TABLE `sync_grade_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `xd` varchar(255) DEFAULT NULL,
  `nj` int(11) DEFAULT NULL,
  `bh` int(11) DEFAULT NULL,
  `bjlx` int(11) DEFAULT NULL,
  `xq` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `rxnd` bigint(255) DEFAULT NULL,
  `master_id` int(11) DEFAULT NULL,
  `master_name` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_grade_class` */

LOCK TABLES `sync_grade_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_org_school` */

DROP TABLE IF EXISTS `sync_org_school`;

CREATE TABLE `sync_org_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  `xz` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `bg_picture` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `short_flag` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_org_school` */

LOCK TABLES `sync_org_school` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_patriarch` */

DROP TABLE IF EXISTS `sync_patriarch`;

CREATE TABLE `sync_patriarch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `student_school_id` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `work` varchar(255) DEFAULT NULL,
  `work_at` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `sfjhr` int(11) DEFAULT NULL,
  `sfyqsh` int(11) DEFAULT NULL,
  `patriarch_flag` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_patriarch` */

LOCK TABLES `sync_patriarch` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_school_type` */

DROP TABLE IF EXISTS `sync_school_type`;

CREATE TABLE `sync_school_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_school_type` */

LOCK TABLES `sync_school_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_student` */

DROP TABLE IF EXISTS `sync_student`;

CREATE TABLE `sync_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `xsxm` varchar(255) DEFAULT NULL,
  `xszp` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `csrq` bigint(255) DEFAULT NULL,
  `rxrq` bigint(255) DEFAULT NULL,
  `xsxb` int(11) DEFAULT NULL,
  `xssg` varchar(255) DEFAULT NULL,
  `xd` varchar(255) DEFAULT NULL,
  `nj` int(11) DEFAULT NULL,
  `xh` varchar(255) DEFAULT NULL,
  `xjh` varchar(255) DEFAULT NULL,
  `qgxjh` varchar(255) DEFAULT NULL,
  `jyid` varchar(255) DEFAULT NULL,
  `syd` varchar(255) DEFAULT NULL,
  `yxzjlx` int(11) DEFAULT NULL,
  `yxzjh` varchar(255) DEFAULT NULL,
  `jbs` varchar(255) DEFAULT NULL,
  `sfsbt` int(11) DEFAULT NULL,
  `sbtxh` int(11) DEFAULT NULL,
  `xslb` int(11) DEFAULT NULL,
  `gb` varchar(255) DEFAULT NULL,
  `mz` varchar(255) DEFAULT NULL,
  `jg` varchar(255) DEFAULT NULL,
  `zzmm` int(11) DEFAULT NULL,
  `zslb` int(11) DEFAULT NULL,
  `lydq` varchar(255) DEFAULT NULL,
  `hkszd` varchar(255) DEFAULT NULL,
  `xjzd` varchar(255) DEFAULT NULL,
  `hkxz` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_student` */

LOCK TABLES `sync_student` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_class_room` */

DROP TABLE IF EXISTS `sync_teach_class_room`;

CREATE TABLE `sync_teach_class_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `room_type_name` varchar(255) DEFAULT NULL,
  `room_num` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `teach_building` varchar(255) DEFAULT NULL,
  `teach_building_num` int(11) DEFAULT NULL,
  `school_type` varchar(255) DEFAULT NULL,
  `school_type_name` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `available_seat` int(11) DEFAULT NULL,
  `exam_seat` int(11) DEFAULT NULL,
  `course_select` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_class_room` */

LOCK TABLES `sync_teach_class_room` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_course` */

DROP TABLE IF EXISTS `sync_teach_course`;

CREATE TABLE `sync_teach_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `course_type` varchar(255) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `pass_score` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_course` */

LOCK TABLES `sync_teach_course` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_course_node` */

DROP TABLE IF EXISTS `sync_teach_course_node`;

CREATE TABLE `sync_teach_course_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `course_node_init_id` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `cycle_year` varchar(255) DEFAULT NULL,
  `cycle_semester` int(11) DEFAULT NULL,
  `node` int(11) DEFAULT NULL,
  `node_name` varchar(255) DEFAULT NULL,
  `start_time` bigint(255) DEFAULT NULL,
  `end_time` bigint(255) DEFAULT NULL,
  `morning_afternoon` varchar(255) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `summer_winter_time` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_course_node` */

LOCK TABLES `sync_teach_course_node` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_course_node_init` */

DROP TABLE IF EXISTS `sync_teach_course_node_init`;

CREATE TABLE `sync_teach_course_node_init` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `cycle_year` varchar(255) DEFAULT NULL,
  `cycle_semester` int(11) DEFAULT NULL,
  `start_week` int(11) DEFAULT NULL,
  `end_week` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_course_node_init` */

LOCK TABLES `sync_teach_course_node_init` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_course_type` */

DROP TABLE IF EXISTS `sync_teach_course_type`;

CREATE TABLE `sync_teach_course_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_course_type` */

LOCK TABLES `sync_teach_course_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_cycle` */

DROP TABLE IF EXISTS `sync_teach_cycle`;

CREATE TABLE `sync_teach_cycle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `cycle_name` varchar(255) DEFAULT NULL,
  `cycle_year` varchar(255) DEFAULT NULL,
  `cycle_semester` int(11) DEFAULT NULL,
  `term_begin_time` bigint(255) DEFAULT NULL,
  `begin_date` bigint(255) DEFAULT NULL,
  `end_date` bigint(255) DEFAULT NULL,
  `week_count` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_cycle` */

LOCK TABLES `sync_teach_cycle` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_daily_hour` */

DROP TABLE IF EXISTS `sync_teach_daily_hour`;

CREATE TABLE `sync_teach_daily_hour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `grade_class_id` varchar(255) DEFAULT NULL,
  `skts` int(11) DEFAULT NULL,
  `swks` int(11) DEFAULT NULL,
  `xwks` int(11) DEFAULT NULL,
  `kjc` int(11) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `xn` varchar(255) DEFAULT NULL,
  `xq` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_daily_hour` */

LOCK TABLES `sync_teach_daily_hour` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_ref_class_room` */

DROP TABLE IF EXISTS `sync_teach_ref_class_room`;

CREATE TABLE `sync_teach_ref_class_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `grade_class` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `school_type_id` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_ref_class_room` */

LOCK TABLES `sync_teach_ref_class_room` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_ref_course_class` */

DROP TABLE IF EXISTS `sync_teach_ref_course_class`;

CREATE TABLE `sync_teach_ref_course_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `course_hour` int(11) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_ref_course_class` */

LOCK TABLES `sync_teach_ref_course_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_ref_room_cycle` */

DROP TABLE IF EXISTS `sync_teach_ref_room_cycle`;

CREATE TABLE `sync_teach_ref_room_cycle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `cycle_id` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_ref_room_cycle` */

LOCK TABLES `sync_teach_ref_room_cycle` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_room_type` */

DROP TABLE IF EXISTS `sync_teach_room_type`;

CREATE TABLE `sync_teach_room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_room_type` */

LOCK TABLES `sync_teach_room_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_standard_course` */

DROP TABLE IF EXISTS `sync_teach_standard_course`;

CREATE TABLE `sync_teach_standard_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `sys` int(11) DEFAULT NULL,
  `is_dictionary` int(11) DEFAULT NULL,
  `course_type_id` varchar(255) DEFAULT NULL,
  `course_type_name` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_standard_course` */

LOCK TABLES `sync_teach_standard_course` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teach_table` */

DROP TABLE IF EXISTS `sync_teach_table`;

CREATE TABLE `sync_teach_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `table_id` varchar(255) DEFAULT NULL,
  `class_room_id` varchar(255) DEFAULT NULL,
  `weekend` int(11) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teach_table` */

LOCK TABLES `sync_teach_table` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teacher` */

DROP TABLE IF EXISTS `sync_teacher`;

CREATE TABLE `sync_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `department_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `is_manage` int(11) DEFAULT NULL,
  `title_id` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `start_work` varchar(255) DEFAULT NULL,
  `head_url` varchar(255) DEFAULT NULL,
  `high_time` varchar(255) DEFAULT NULL,
  `zc` varchar(255) DEFAULT NULL,
  `sfzrjs` varchar(255) DEFAULT NULL,
  `jg` varchar(255) DEFAULT NULL,
  `zzmm` varchar(255) DEFAULT NULL,
  `rjxk` varchar(255) DEFAULT NULL,
  `xq` varchar(255) DEFAULT NULL,
  `zgxl` varchar(255) DEFAULT NULL,
  `lwxsj` varchar(255) DEFAULT NULL,
  `sfhq` varchar(255) DEFAULT NULL,
  `sfbzr` varchar(255) DEFAULT NULL,
  `wyyz` varchar(255) DEFAULT NULL,
  `zyjsgwfl` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teacher` */

LOCK TABLES `sync_teacher` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teacher_class` */

DROP TABLE IF EXISTS `sync_teacher_class`;

CREATE TABLE `sync_teacher_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_teacher_id` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sync_class_id` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_teacher_class` */

LOCK TABLES `sync_teacher_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_title` */

DROP TABLE IF EXISTS `sync_title`;

CREATE TABLE `sync_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `jb` varchar(255) DEFAULT NULL,
  `px` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_date` bigint(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_title` */

LOCK TABLES `sync_title` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_user` */

DROP TABLE IF EXISTS `sync_user`;

CREATE TABLE `sync_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ref_id` varchar(255) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_user` */

LOCK TABLES `sync_user` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_app` */

DROP TABLE IF EXISTS `sys_app`;

CREATE TABLE `sys_app` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '应用名',
  `icon_url` varchar(1000) DEFAULT NULL COMMENT '图标路径',
  `web_url` varchar(1000) DEFAULT NULL COMMENT '网页路径',
  `pic_url` varchar(1000) DEFAULT NULL COMMENT '介绍图片',
  `app_permission` int(5) DEFAULT NULL COMMENT '应用级别【1；区级应用 2 校级应用 3公共应用】',
  `app_status` int(10) DEFAULT NULL COMMENT '应用状态[0:审核中, 1:已上线, 2:下线, 3:其他异常]',
  `is_default` int(10) DEFAULT '0' COMMENT '是否是默认应用(0是默认应用，1不是默认应用)',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(5000) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `target_user` varchar(255) DEFAULT NULL COMMENT '目标用户（1：教师，2：学生，3：家长，4：其他）',
  `category` varchar(255) DEFAULT NULL COMMENT '应用类别（0.系统应用 1.教学教务；2.互动空间 3.校务管理）',
  `developers` varchar(255) DEFAULT NULL COMMENT '开发商',
  `sfczxmz` int(10) DEFAULT '0' COMMENT '是否存在项目中（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';

/*Data for the table `sys_app` */

LOCK TABLES `sys_app` WRITE;

insert  into `sys_app`(`id`,`name`,`icon_url`,`web_url`,`pic_url`,`app_permission`,`app_status`,`is_default`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`target_user`,`category`,`developers`,`sfczxmz`) values ('07b5ddc175e0b9c08714e7ae75e18f57','设备报修','app/detail/201708/1502855225920.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=94637c9e5acb420780f546e881095f8a','app/detail/201708/1502855239202.png,app/detail/201708/1502855239162.png,app/detail/201708/1502855239140.png,app/detail/201708/1502855239105.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855240319,NULL,NULL,'<p>在线报修信息管理系统具有在线提交报修信息、查看报修进程、报修评价等功能，它将有效的提高报修效率，让每个报修透明化，公开化。</p>\r\n<p>&nbsp;</p>',1,'1','3','教育软件有限公司 ',0),('1fca23c8f69bbdb6bf7afc9de13a0cc0','人事管理','app/detail/201609/1475142163669.png','renshi/renyuan/index','app/detail/201610/1476681933486.png,app/detail/201610/1476681933478.png,app/detail/201610/1476681933468.png,app/detail/201610/1476681933450.png,app/detail/201610/1476681933440.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1503308777405,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">人事管理系统的管理内容主要是对学校的教职工基本信息、教职工账号等信息进行切实的管理。同时对学校部门设置管理、职务设置管理等提供入口，满足教学人事管理的日常需求，让繁杂的教职工信息管理变得简单，并为其他系统应用提供人事数据。</span></p>',0,'1','0','教育云平台',1),('1fca23c8f69bbdb6bf7afc9de13a0cca','学籍管理','app/detail/201609/1475141395501.png','class/index','app/detail/201610/1476675269887.png,app/detail/201610/1476675269864.png,app/detail/201610/1476675269854.png,app/detail/201610/1476675269842.png,app/detail/201610/1476675269832.png,app/detail/201610/1476675269816.png,app/detail/201610/1476675269804.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1503308258190,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">学籍管理系统主要对学校学生信息的基础数据进行管理和维护，同时维护学校的部分基础信息，比如学校的学段设置、班级设置、其他基本设置等等。学籍管理系统作为学生基础数据的入口，为学生考勤、成绩分析等等系统提供了基础数据，实现了对整个平台的学生基础数据的统一管理，减少数据维护工作，减少沟通成本，提高了相应的教学教务的工作效率。</span></p>',0,'1','0','教育云平台',1),('1fca23c8f69bbdb6bf7afc9de13a0ccd','通知公告','app/detail/201609/1475142634153.png','notify/index','app/detail/201610/1476688169684.png,app/detail/201610/1476688169674.png,app/detail/201610/1476688169667.png,app/detail/201610/1476688169653.png',3,1,0,'1',1479190510494,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">通知公告主要面向教职工，对于校内的各类通知的发布，均可以通过通知公告应用来实现，并且对发布内容以及通告栏目有明确的管理人员和制度，对通知的发布做到严格控制。</span></p>',0,'1','0','教育云平台',1),('1fca23c8f69bbdb6bf7afc9de13a0cce','通讯录','app/detail/201609/1475139465580.png','contact/contact/index','app/detail/201609/1475139775314.png',3,1,0,'1',1479190409746,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">通讯录作为平台的基础功能，同步人事管理中的部门管理的教职工通讯信息，方便学校内部查询相关教职工信息，便于管理和使用。</span></p>',0,'1','0','教育云平台',1),('1fca23c8f69bbdb6bf7afc9de13a0ccf','人事管理','app/detail/201609/1475142163111.png','area/renyuan/index','',1,1,0,'1',1492135758103,NULL,NULL,'',0,'1','0','教育云平台',1),('264463c38c95bb643d859e4f5da66283','请假考勤管理','app/detail/201708/1502855160892.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=ab861a543a6b4c2baec00593ca99cfbc','app/detail/201708/1502855179336.png,app/detail/201708/1502855179310.png,app/detail/201708/1502855179279.png,app/detail/201708/1502855179256.png,app/detail/201708/1502855179228.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855181062,NULL,NULL,'<p>请假考勤系统旨在确定每个人的请假审核权责，明确各个不同层级职位所拥有的不同的审批职权范围。系统切实根据学校的需求规范请假流程，普通教职工只需提交请假条即可，便能轻易的对请假条的实际情况进行跟踪，是否被审批，审批到哪一层级，而组长、主任、校长则可根据自己的权限进行相应的审批，保证信息的及时流通。普通教师在请假之后可进行相应的销假操作，以实际销假时间为准作为请假记录。系统可根据简单设置自动核算请假时间，同时，请假考勤系统会按部门人员生成假条汇总，轻易查询相应的请假信息。系统自动生成考勤汇总表，轻松查看每个人的请假考勤情况，为工资核算等提供非常有效的依据。&nbsp;</p>',1,'1','3','教育软件有限公司 ',0),('42c5ca0441feb6bdfe5379417531bbf7','BetaTree','app/detail/201708/1503538383995.png','http://10.90.0.2:10004/ring/login/check','',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1503538404752,NULL,NULL,'',0,'1','0','ring',0),('5499b97c06adf875c22c7c1e1f6cdf72','教务管理','app/detail/201609/1475142163699.png','teach/task/cycle/index','',2,1,0,'1',1492156911727,NULL,NULL,'',0,'4,3,2,1','0','教育云平台',1),('6c3bfc09c81d1a38f458caa277bb6135','设备预约','app/detail/201708/1502855260317.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=ec1cf90f9619451eb98cac980c65ec0c','app/detail/201708/1502855275138.png,app/detail/201708/1502855275161.png,app/detail/201708/1502855275090.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855276709,NULL,NULL,'<p>设备预约系统解决了传统的设备预约的繁琐流程，实现在线预约；大大减少了管理者及使用者的工作量和时间。管理者只需在平台上录入设备信息，教师就可以轻松在线预约使用</p>\r\n<p>&nbsp;</p>',1,'1','3','教育软件有限公司 ',0),('78525fdb7a4bf8feb9ce6ff268494701','教师成长档案','app/detail/201708/1502855122754.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=10af297114774d80a295138e03fb1d11','app/detail/201708/1502855140401.png,app/detail/201708/1502855140371.png,app/detail/201708/1502855140349.png,app/detail/201708/1502855140303.png,app/detail/201708/1502855140253.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855144107,NULL,NULL,'<p>教师成长档案系统，通过对教师成长发展的记录、汇总和展示，为寻找教师专业发展目标与学校规划间的契合点提供了信息资料。以教师个人发展为立足点，建设促进教师间交流分享的全方位校园成长平台。系统汇总档案的方式是教师上传档案为主，采用社区模式，实现资源共享，同时也为学校评价教师，了解教师的综合实力提供一定的依据。学校也可以通过互相分享收集教师相应成果，激发教师的科研意识，全员一起进步。</p>',1,'1','3','教育软件有限公司 ',0),('936d39a4eece3f469e227682b9912e7a','值班排班','app/detail/201708/1502855307204.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=33feb89234384b88b5c7d337aed7ca6e','',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855312905,NULL,NULL,'<p>值班排班系统为学校提供了一整套的解决方案，用于小长假、寒暑假等非上课日期间学校的值班安排设置和审批、参与教师调班申请和审批、教师移动端打卡签到（需要移动端配合使用）、值班情况确认和导出等功能。值班安排遵循公平的原则，之前参与次数少的教师，会优先安排。</p>',1,'1','3','教育软件有限公司 ',0),('a7a0c7724b38c52fdc73767070ccf6ca','智慧班牌','app/detail/201611/1479190668111.png','classcard/index','app/detail/201708/1501637252165.jpg',2,1,0,'1',1501637257128,NULL,NULL,'',0,'4,3,2,1','0','教育云平台',1),('abd0a21726aa40da530f7a2de1536652','办公用品管理','app/detail/201708/1502854921302.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=1c197ba1d78542a48efb220264880cac','app/detail/201708/1502855019729.png,app/detail/201708/1502855019709.png,app/detail/201708/1502855019681.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855023125,NULL,NULL,'<p>办公用品管理是学校用于低消耗品的管理；资产借出后，无需归还入库；并且系统会生成出入库记录，对每个资产有一个详细的记录，便于管理。同时，办公用品管理会对物品设置警戒线，低于此警戒线，系统会提示管理员及时补充。</p>',1,'1','3','教育软件有限公司 ',0),('b5062042661d4a70a89e0249e7cf1b5d','多维度投票','app/detail/201708/1502855069724.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=c54b31711e6243f98d42fa57bedf038f','app/detail/201708/1502855083688.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855092862,NULL,NULL,'<p>教师多维度投票用于教师投票、评选活动；投票基本参数设置灵活实用，如投票数限制、结果可见性等；被评人及参评人可灵活选择；参评人可以面向教师、家长、学生，保证了投票维度的全面性。同时，在投票过程中，管理员可实时查看投票明细及报表，了解投票详情，并查看未投票人，及时对未投票人进行提醒。</p>',1,'3,2,1','3','教育软件有限公司 ',0),('d86b3f2ca6f25ffd772332be2e6d65df','学籍管理','app/detail/201609/1475142163222.png','area/class/stuinfo/index','app/detail/201704/1491459270213.jpg,app/detail/201704/1491459266897.jpg,app/detail/201704/1491459263571.jpg',1,1,0,'1',1492135779456,NULL,NULL,'',0,'1','0','教育云平台',1),('f07a49a8f9b0fe57ba944482560fefb1','人事综合考评','app/detail/201708/1502855197571.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=c35e2915cd3d4797868cd064a7c85484','',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855203731,NULL,NULL,'',1,'1','3','教育软件有限公司 ',0),('f82fefb55375ed0c48bfd630c4210e53','资产管理','app/detail/201708/1502855331187.png','http://iflytek.lezhiyun.cn/authentication/thirdpartyLoginByKey.do?forward=/school/app.do?id=app_03','app/detail/201708/1502855346040.png,app/detail/201708/1502855345994.png,app/detail/201708/1502855345955.png',2,1,0,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502855348007,NULL,NULL,'<p>资产管理系统专注于校园实物资产管理，实现固定资产信息录入、查询、报修、维修情况反馈和基本权限设置，包括资产信息管理、资产查询、资产领用、资产借用归还、资产调拨、资产报废、资产维修、资产盘点、资产核查、基础数据管理、统计报表、系统管理和权限管理等功能模块，涵盖了资产全生命周期管理的整个过程。</p>\r\n<p>采用固定资产条码管理方式，改变了固定资产清查数据的采集方式，解决固定资产实物清查的瓶颈问题，大大提高清查效率；增加了固定资产的形态方面的管理，有效解决学校资产的管理难题，使学校更轻松、更有效地管理固定资产，为决策提供依据，实现资源的合理配置。</p>\r\n<p>&nbsp;</p>',1,'1','3','教育软件有限公司 ',0);

UNLOCK TABLES;

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `param_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数类型',
  `param_key` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数key值',
  `param_value` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数值',
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标志（0：未删除，1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='系统配置';

/*Data for the table `sys_config` */

LOCK TABLES `sys_config` WRITE;

insert  into `sys_config`(`id`,`param_type`,`param_key`,`param_value`,`del_flag`) values ('1','bottomText','sa','<p>©2014-2016 Beijing Search Champion Technology Co.,Ltd. 北京谷壳儿科技有限公司</p><p>京ICP备16000182-3号|京公安网备11010502027075</p>',0),('2','defaultPassword','parent','000000',0),('3','defaultPassword','teacher ','000000',0),('4','defaultPassword','student','000000',0);

UNLOCK TABLES;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `sys_dict` */

LOCK TABLES `sys_dict` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `type` char(5) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(50) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

/*Data for the table `sys_log` */

LOCK TABLES `sys_log` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_mdict` */

DROP TABLE IF EXISTS `sys_mdict`;

CREATE TABLE `sys_mdict` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_mdict_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_mdict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多级字典表';

/*Data for the table `sys_mdict` */

LOCK TABLES `sys_mdict` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `parent_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '父级编号',
  `parent_ids` varchar(400) NOT NULL DEFAULT '' COMMENT '所有父级编号',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `href` varchar(1000) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(400) DEFAULT NULL COMMENT '图标',
  `is_show` int(10) NOT NULL DEFAULT '0' COMMENT '是否在菜单中显示',
  `permission` varchar(400) DEFAULT NULL COMMENT '权限标识',
  `belong` varchar(50) DEFAULT '0' COMMENT '所属应用ID 关联sys_app 的id',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_menu_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`parent_id`,`parent_ids`,`name`,`sort`,`href`,`target`,`icon`,`is_show`,`permission`,`belong`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('28059d1f5f36bfd464d8ec4967942100','28059d1f5f36bfd464d8ec4967942999','','机构管理',0,'/school/index',NULL,NULL,0,'school:index:view','0','3',2,NULL,NULL,NULL,0),('28059d1f5f36bfd464d8ec4967942110','28059d1f5f36bfd464d8ec4967942999','','角色管理',0,'/school/permissionMan',NULL,NULL,0,'school:role:view','0','3',2,NULL,NULL,NULL,0),('28059d1f5f36bfd464d8ec4967942111','0','','校级人事管理模块',0,'/renshi',NULL,NULL,0,'renShi:renYuan:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','2',20160808084312,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971192981,'<p>这个菜单能添加学生</p>',0),('28059d1f5f36bfd464d8ec4967942121','28059d1f5f36bfd464d8ec4967942999','','应用管理',0,'/app/index',NULL,NULL,0,'school:app:view','0','3',2,NULL,NULL,NULL,0),('28059d1f5f36bfd464d8ec4967942144','28059d1f5f36bfd464d8ec4967942333','','通知公告',0,'/notify/index',NULL,NULL,0,'notify:notify:view','1fca23c8f69bbdb6bf7afc9de13a0ccd','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971715295,'',0),('28059d1f5f36bfd464d8ec4967942155','28059d1f5f36bfd464d8ec4967942333','','栏目管理',0,'/notify/lanmu/manage',NULL,NULL,0,'notify:lanmu:view','1fca23c8f69bbdb6bf7afc9de13a0ccd','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971708311,'',0),('28059d1f5f36bfd464d8ec4967942166','28059d1f5f36bfd464d8ec4967942333','','发布',0,'/notify/add',NULL,NULL,0,'notify:notify:add','1fca23c8f69bbdb6bf7afc9de13a0ccd','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971701021,'',0),('28059d1f5f36bfd464d8ec4967942177','28059d1f5f36bfd464d8ec4967942333','','角色分配',0,'/notify/role/fenpei',NULL,NULL,0,'notify:role:view','1fca23c8f69bbdb6bf7afc9de13a0ccd','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971692301,'',0),('28059d1f5f36bfd464d8ec4967942191','28059d1f5f36bfd464d8ec4967942e11','','学生管理',0,'/class/index',NULL,NULL,0,'class:student:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971681789,'',0),('28059d1f5f36bfd464d8ec4967942201','28059d1f5f36bfd464d8ec4967942e11','','班级管理',0,'/class/banji/view',NULL,NULL,0,'class:banji:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971668927,'',0),('28059d1f5f36bfd464d8ec4967942221','28059d1f5f36bfd464d8ec4967942e11','','学校设置',0,'/class/schoolSetting/view',NULL,NULL,0,'class:schoolSetting:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971542863,'',0),('28059d1f5f36bfd464d8ec4967942222','28059d1f5f36bfd464d8ec4967942e11','','学段管理',0,'/class/xueDuan/view',NULL,NULL,0,'class:xueDuan:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971536760,'',0),('28059d1f5f36bfd464d8ec4967942231','28059d1f5f36bfd464d8ec4967942e11','','学生账号管理',0,'/class/stuAccount/view',NULL,NULL,0,'class:stuAccount:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971526823,'',0),('28059d1f5f36bfd464d8ec4967942241','28059d1f5f36bfd464d8ec4967942e11','','家长账号管理',0,'/class/parAccount/view',NULL,NULL,0,'class:parAccount:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971520747,'',0),('28059d1f5f36bfd464d8ec4967942251','28059d1f5f36bfd464d8ec4967942e11','','角色管理',0,'/class/roleManage/view',NULL,NULL,0,'class:role:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971514961,'',0),('28059d1f5f36bfd464d8ec4967942333','0','','通知公告模块',0,'/notify',NULL,NULL,0,'notify:notify:view','1fca23c8f69bbdb6bf7afc9de13a0ccd','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971486702,'',0),('28059d1f5f36bfd464d8ec4967942444','28059d1f5f36bfd464d8ec4967942111','','人员管理',0,'/renshi/rsindex',NULL,NULL,0,'renShi:renYuan:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','2',20160809074410,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971182437,'<p>这个菜单管理部门人员</p>',0),('28059d1f5f36bfd464d8ec4967942555','28059d1f5f36bfd464d8ec4967942111','','账号管理',0,'/renshi/rsaccount/index',NULL,NULL,0,'renshi:account:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971478800,'',0),('28059d1f5f36bfd464d8ec4967942666','28059d1f5f36bfd464d8ec4967942111','','部门管理',0,'/renshi/rsbumen/index',NULL,NULL,0,'renShi:bumen:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971471077,'',0),('28059d1f5f36bfd464d8ec4967942777','28059d1f5f36bfd464d8ec4967942111','','职务信息',0,'/renshi/rszhiwuindex',NULL,NULL,0,'renShi:zhiwu:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971463802,'',0),('28059d1f5f36bfd464d8ec4967942888','28059d1f5f36bfd464d8ec4967942111','','角色分配',0,'/renshi/rsrolefp/index',NULL,NULL,0,'renShi:role:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971631397,'',0),('28059d1f5f36bfd464d8ec4967942999','0','','机构管理模块',0,'/school',NULL,NULL,0,'school:index:view','1fca23c8f69bbdb6bf7afc9de13a0cc0','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971455112,'',0),('28059d1f5f36bfd464d8ec4967942e11','0','','校级学籍管理模块',0,'/class',NULL,NULL,0,'class:banji:view','1fca23c8f69bbdb6bf7afc9de13a0cca','3',2,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971435839,'',0),('28059d1f5f36bfd464d8ec4967942e22','0','','通讯录',0,'/contact/contact/index',NULL,NULL,0,'contact:contact:view','1fca23c8f69bbdb6bf7afc9de13a0cce','1',1477033340991,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971605622,'',0),('28059d1f5f36bfd464d8ec4967942e86','6364431286830059f26a6ed8ff45849b','','角色分配',0,'/teach/task/cycle/index',NULL,NULL,0,'teach:task:role','5499b97c06adf875c22c7c1e1f6cdf72','1',1497862460778,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971252183,'',0),('356bd1640d9cdf9cd75e467bc396efdb','6364431286830059f26a6ed8ff45849b','','综合管理',0,'/teach/task/ref/class/room/index',NULL,NULL,0,'teach:task:zonghe','5499b97c06adf875c22c7c1e1f6cdf72','1',1497862396642,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971261424,'',0),('3cfc77bb791b130cbbeb049eee55621a','a88235ee2abf3fa4418033c875d10345','','班级空间',0,'/classCard/introduction/index',NULL,NULL,0,'classCard:classspace:view','a7a0c7724b38c52fdc73767070ccf6ca','1',1501061812466,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971228374,'',0),('4657b2df16a8698e47be0e1d41879542','6364431286830059f26a6ed8ff45849b','','基础管理',0,'/teach/task/cycle/index',NULL,NULL,0,'teach:task:base','5499b97c06adf875c22c7c1e1f6cdf72','1',1497862335869,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971266695,'',0),('4cf4ea5120e7a67e2e23f510fd2c5a2b','0','','应用商店',0,'app/appstore/index',NULL,NULL,0,'app:appstore:view','1fca23c8f69bbdb6bf7afc9de13a0ccb','1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1503041089513,NULL,NULL,'',0),('54781213ae9ff5347cbee4848b8ede78','7e1c96eefcd3d5ef19668065e0a44c9e','','学生信息',0,'area/class/stuinfo/index',NULL,NULL,0,'class:area:stuinfo','d86b3f2ca6f25ffd772332be2e6d65df','5af78049d1878a0232333ec44324baaa',1497861618968,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971331854,'',0),('604ad67e7313d54db9bf1e989a88dd58','7e1c96eefcd3d5ef19668065e0a44c9e','','家长信息',0,'/area/class/parinfo/index',NULL,NULL,0,'class:area:parinfo','d86b3f2ca6f25ffd772332be2e6d65df','5af78049d1878a0232333ec44324baaa',1497861691538,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971316536,'',0),('6364431286830059f26a6ed8ff45849b','0','','教务管理模块',0,'/teach/task/cycle/index',NULL,NULL,0,'teach:task:view','5499b97c06adf875c22c7c1e1f6cdf72','1',1497862209001,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971288483,'',0),('79495b8fc425d6f76435ef0565936dbb','8a0894d3ba5972e80a358a9b81235936','','校级管理',0,'/area/school/person/index',NULL,NULL,0,'renShi:area:xiao','1fca23c8f69bbdb6bf7afc9de13a0ccf','1',1497861050693,NULL,NULL,'',0),('7e1c96eefcd3d5ef19668065e0a44c9e','0','','区级学籍管理',0,'/area/class',NULL,NULL,0,'class:area:stuinfo','d86b3f2ca6f25ffd772332be2e6d65df','5af78049d1878a0232333ec44324baaa',1497861520954,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971423619,'',0),('81468a3d8422b48ee3bef9098cb808d0','7e1c96eefcd3d5ef19668065e0a44c9e','','统计报表',0,'/area/class/birt/index',NULL,NULL,0,'class:area:birt','d86b3f2ca6f25ffd772332be2e6d65df','5af78049d1878a0232333ec44324baaa',1497861730624,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971307151,'',0),('8a0894d3ba5972e80a358a9b81235936','0','','区级人事管理',0,'/area',NULL,NULL,0,'renShi:area:view','1fca23c8f69bbdb6bf7afc9de13a0ccf','1',1497860774338,NULL,NULL,'',0),('9c7e431301ebd21169284d8e828a9c1f','8a0894d3ba5972e80a358a9b81235936','','角色分配',0,'/area/rolefp/index',NULL,NULL,0,'renShi:area:role','1fca23c8f69bbdb6bf7afc9de13a0ccf','1',1497861212859,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971353512,'',0),('9d984765fdd49214115cdc9cb364350a','a88235ee2abf3fa4418033c875d10345','','通知公告',0,'/classCard/notify/index',NULL,NULL,0,'classCard:notify:view','1fca23c8f69bbdb6bf7afc9de13a0ccd','1',1501061718481,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971237691,'',0),('a5378a7a373cf7f07461bf5022566805','8a0894d3ba5972e80a358a9b81235936','','区级管理',0,'/area/renyuan/index',NULL,NULL,0,'renShi:area:quji','1fca23c8f69bbdb6bf7afc9de13a0ccf','1',1497860968429,'1',1497861082832,'',0),('a88235ee2abf3fa4418033c875d10345','0','','智慧班牌',0,'/classCard',NULL,NULL,0,'classCard:index:view','a7a0c7724b38c52fdc73767070ccf6ca','1',1501061509167,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971654694,'',0),('b22e786ede60bbe8fc8ab07b9eb9505e','7e1c96eefcd3d5ef19668065e0a44c9e','','角色分配',0,'/area/class/rolefp/index',NULL,NULL,0,'class:area:role','d86b3f2ca6f25ffd772332be2e6d65df','5af78049d1878a0232333ec44324baaa',1497861769842,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971296385,'',0),('ba7dae25f2b12abf71fe949ba164f880','a88235ee2abf3fa4418033c875d10345','','分配角色',0,'/classCard/role/index',NULL,NULL,0,'classCard:role:view','a7a0c7724b38c52fdc73767070ccf6ca','1',1501061860071,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971217195,'',0),('c21452a46e7eb6914654d9de47356f89','8a0894d3ba5972e80a358a9b81235936','','统计报表',0,'/area/birt/index',NULL,NULL,0,'renShi:area:birt','1fca23c8f69bbdb6bf7afc9de13a0ccf','1',1497861145947,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971361781,'',0),('e79c741d3eb6417716935253214a9289','a88235ee2abf3fa4418033c875d10345','','设备管理',0,'/classCard/index',NULL,NULL,0,'classCard:equipment:view','a7a0c7724b38c52fdc73767070ccf6ca','1',1501061651309,'1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',1502971245454,'',0);

UNLOCK TABLES;

/*Table structure for table `sys_monitor` */

DROP TABLE IF EXISTS `sys_monitor`;

CREATE TABLE `sys_monitor` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `cpu` varchar(64) DEFAULT NULL COMMENT 'cpu使用率',
  `jvm` varchar(64) DEFAULT NULL COMMENT 'jvm使用率',
  `ram` varchar(64) DEFAULT NULL COMMENT '内存使用率',
  `toemail` varchar(64) DEFAULT NULL COMMENT '警告通知邮箱',
  `client_id` varchar(255) DEFAULT NULL COMMENT '开放平台传递应用数据初始化id',
  `open_url` varchar(255) DEFAULT NULL COMMENT '由哪个平台传递',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='系统监控';

/*Data for the table `sys_monitor` */

LOCK TABLES `sys_monitor` WRITE;

insert  into `sys_monitor`(`id`,`cpu`,`jvm`,`ram`,`toemail`,`client_id`,`open_url`) values ('1','99','99','99','xxxxxxx@qq.com',NULL,NULL),('cf694f1dbda243d285085046c59e0467',NULL,NULL,NULL,NULL,'kqScts','http://localhost:8088/open/');

UNLOCK TABLES;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `role_identify` varchar(255) DEFAULT NULL COMMENT '角色标识',
  `role_type` int(10) DEFAULT NULL COMMENT '角色类型',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT '0' COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_role_enname` (`enname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

LOCK TABLES `sys_role` WRITE;

insert  into `sys_role`(`id`,`name`,`enname`,`role_identify`,`role_type`,`useable`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1c0204e3e6519185f048bae0dd55a000','超级管理员','root','root',0,'1','1',1479379640842,'1',1478142092383,'<p>云平台系统后台管理员，权限最大</p>',0),('1c0204e3e6519185f048bae0dd55a233','校级人事管理员','teacher','xRenShiAdmin',3,'1','1',1478500421859,'1',1497862064367,'<p>renshi管理员</p>',0),('1c0204e3e6519185f048bae0dd55a255','校级学籍管理员','teacher','xXueJiAdmin',3,'1','1',1478501105380,'1',1497862078711,'',0),('1c0204e3e6519185f048bae0dd55a266','通知公告管理员','teacher','notifyAdmin',3,'1','1',1479379640842,'1',1502692544787,'',0),('1c0204e3e6519185f048bae0dd55a333','校级/区级管理员','teacher','schoolAdmin',3,'1','1',1479379640842,'1',1497861999203,'<p>校级管理员，拥有每个模块所有权限，创建机构时生成的帐号是这个角色分配</p>',0),('1c0204e3e6519185f048bae0dd55a444','普通学生','student','student',4,'1','1',1479379640842,'1',1472797318909,'学生角色',0),('1c0204e3e6519185f048bae0dd55a555','普通家长','patriarch','patriarch',5,'1','1',1479379640842,'1',1479379640842,'家长角色',0),('1c0204e3e6519185f048bae0dd55a777','普通教师','teacher','teacher',3,'1','1',1479379640842,'1',1479379640842,'教职工角色',0),('1c0204e3e6519185f048bae0dd55a8ba','教务管理员','teacher','teachTaskAdmin',3,'1','1',1494297023856,NULL,1479379640842,'',0),('6390a03083116fe48dc1ef96c11aee58','班牌管理员','cardAdmin','classCardAdmin',3,'1','1',1479379640842,NULL,1479379640842,'',0),('cc5353cce83d328cae1169b391f5d328','班主任','headTeacher','headTeacher',3,'1','1',1501066902950,'1',1502692795499,'<p>班主任（包含正副班主任）1</p>',0),('d0751018ca38f9bb1c9a13cb3fbb61f7','区级学籍管理员','teacher','qXueJiAdmin',3,'1','1',1491372011307,NULL,1479379640842,'',0),('ef3c113aa05ca8511ff69bfaa1f0dd95','区级人事管理员','teacher','qRenShiAdmin',3,'1','1',1490931921092,NULL,1479379640842,'',0);

UNLOCK TABLES;

/*Table structure for table `sys_theme` */

DROP TABLE IF EXISTS `sys_theme`;

CREATE TABLE `sys_theme` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `theme_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '主题ID',
  `bg_img` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '背景图片',
  `create_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `remark` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '说明',
  `del_flag` int(10) DEFAULT NULL COMMENT '删除标识【1 删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sys_theme` */

LOCK TABLES `sys_theme` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID',
  `username` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `ref_id` varchar(50) DEFAULT NULL COMMENT '用户引用ID',
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型[0:root, 1:教师, 2:学生, 3:家长]',
  `photo_url` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_flag` int(10) DEFAULT NULL COMMENT '是否可登录',
  `login_mark` varchar(64) DEFAULT NULL COMMENT '用于单点登录的随机字符串验证',
  `last_login_time` bigint(50) DEFAULT NULL COMMENT '上次登录时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`username`) USING BTREE,
  KEY `sys_user_update_date` (`update_date`) USING BTREE,
  KEY `sys_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

LOCK TABLES `sys_user` WRITE;

insert  into `sys_user`(`id`,`school_id`,`username`,`password`,`name`,`ref_id`,`user_type`,`photo_url`,`login_flag`,`login_mark`,`last_login_time`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee','0','root','OE43szSeXws9orNa6ooYsQ==','超级管理员','0',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

UNLOCK TABLES;

/*Table structure for table `teach_class_card` */

DROP TABLE IF EXISTS `teach_class_card`;

CREATE TABLE `teach_class_card` (
  `id` varchar(50) NOT NULL,
  `mac_id` varchar(50) DEFAULT NULL COMMENT '终端id（mac地址）',
  `equipment_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `teach_class_room_id` varchar(50) DEFAULT NULL COMMENT '所在位置',
  `class_id` varchar(50) DEFAULT NULL COMMENT '所属班级',
  `classroom` varchar(50) DEFAULT NULL COMMENT '教室/班级名称',
  `class_slogan` varchar(255) DEFAULT NULL COMMENT '教室/班级口号',
  `school_id` varchar(50) DEFAULT NULL COMMENT '学校id',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  `boot_day_of_week` varchar(50) DEFAULT NULL COMMENT '开机天数　星期一 星期二...',
  `start_boot_time_of_day` varchar(50) DEFAULT NULL COMMENT '开机开始时间　',
  `end_boot_time_of_day` varchar(50) DEFAULT NULL COMMENT '开机结束时间　',
  `is_synchro` int(10) DEFAULT '1' COMMENT '是否同步（0：同步 1：未同步）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_card` */

LOCK TABLES `teach_class_card` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_class_card_introduction` */

DROP TABLE IF EXISTS `teach_class_card_introduction`;

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
  `picture_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_card_introduction` */

LOCK TABLES `teach_class_card_introduction` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_class_card_mode` */

DROP TABLE IF EXISTS `teach_class_card_mode`;

CREATE TABLE `teach_class_card_mode` (
  `id` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `type` int(10) DEFAULT NULL COMMENT '模式类型',
  `time_start` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `time_end` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `content` text COMMENT '显示内容',
  `school_id` varchar(50) DEFAULT NULL COMMENT '学校id',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_card_mode` */

LOCK TABLES `teach_class_card_mode` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_class_card_notify` */

DROP TABLE IF EXISTS `teach_class_card_notify`;

CREATE TABLE `teach_class_card_notify` (
  `id` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `type` int(5) DEFAULT NULL COMMENT '通知类型 0校园通知 1 班级通知',
  `content` text COMMENT '内容',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_card_notify` */

LOCK TABLES `teach_class_card_notify` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_class_card_picture` */

DROP TABLE IF EXISTS `teach_class_card_picture`;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_card_picture` */

LOCK TABLES `teach_class_card_picture` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_class_room` */

DROP TABLE IF EXISTS `teach_class_room`;

CREATE TABLE `teach_class_room` (
  `id` varchar(50) NOT NULL COMMENT '教室id',
  `room_name` varchar(255) DEFAULT NULL COMMENT '教室名',
  `room_type` varchar(50) DEFAULT NULL COMMENT '教室类型 关联teach_task_config的 config_id',
  `room_type_name` varchar(100) DEFAULT NULL COMMENT '教室类型名  匹配room_type',
  `room_num` varchar(50) DEFAULT NULL COMMENT '房间号',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `teach_building` varchar(50) DEFAULT NULL COMMENT '所在教学楼名称',
  `teach_building_num` int(10) DEFAULT NULL COMMENT '所在教学楼楼号',
  `school_type` varchar(50) DEFAULT NULL COMMENT '所在校区（关联org_school_type id）',
  `school_type_name` varchar(100) DEFAULT NULL COMMENT '所在校区名称',
  `floor` varchar(10) DEFAULT NULL COMMENT '楼层',
  `count` int(20) DEFAULT NULL COMMENT '容纳人数',
  `available_seat` int(20) DEFAULT NULL COMMENT '有效座位数',
  `exam_seat` int(20) DEFAULT NULL COMMENT '考试座位数',
  `course_select` int(10) DEFAULT NULL COMMENT '是否用于选课[1 是  2 否]',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_class_room` */

LOCK TABLES `teach_class_room` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_course` */

DROP TABLE IF EXISTS `teach_course`;

CREATE TABLE `teach_course` (
  `id` varchar(50) NOT NULL COMMENT '课程id',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `english_name` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '周期id（关联teach_cycle的id）',
  `short_name` varchar(100) DEFAULT NULL COMMENT '课程简称',
  `course_type` varchar(50) DEFAULT NULL COMMENT '课程类型（关联teach_standard_course的id）',
  `room_type` varchar(50) DEFAULT NULL COMMENT '教室类型（关联teach_room_type的id）',
  `create_date` bigint(25) DEFAULT NULL COMMENT '创建时间',
  `score` double(10,2) DEFAULT NULL COMMENT '课程总分',
  `pass_score` double(10,2) DEFAULT NULL COMMENT '课程及格分',
  `del_flag` int(5) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_course` */

LOCK TABLES `teach_course` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_course_node` */

DROP TABLE IF EXISTS `teach_course_node`;

CREATE TABLE `teach_course_node` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `course_node_init_id` varchar(50) DEFAULT NULL COMMENT '关联teach_course_node的id',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '关联teach_cycle的id',
  `cycle_year` varchar(50) DEFAULT NULL COMMENT '学年',
  `cycle_semester` int(10) DEFAULT NULL COMMENT '学期 1第一学期  2第二学期',
  `node` int(10) DEFAULT NULL COMMENT '节次(一天中的第几节，0表示早自习，1第一节。。。。)',
  `node_name` varchar(50) DEFAULT NULL COMMENT '节点名称，例如早自习 第一节。。晚自习1，晚自习2',
  `start_time` bigint(50) DEFAULT NULL COMMENT '上课开始时间',
  `end_time` bigint(50) DEFAULT NULL COMMENT '上课结束时间',
  `morning_afternoon` varchar(50) DEFAULT NULL COMMENT '上午还是下午',
  `week` int(20) DEFAULT NULL COMMENT '周几',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `del_flag` int(10) DEFAULT NULL COMMENT '0存在  1删除',
  `summer_winter_time` varchar(100) DEFAULT NULL COMMENT '冬令时/夏令时时间段，例如9月1日-9月30日',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `teach_course_node` */

LOCK TABLES `teach_course_node` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_course_node_init` */

DROP TABLE IF EXISTS `teach_course_node_init`;

CREATE TABLE `teach_course_node_init` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `name` varchar(255) DEFAULT NULL COMMENT '时间表名称',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '教学周期id',
  `cycle_year` varchar(50) DEFAULT NULL COMMENT '学年',
  `cycle_semester` int(10) DEFAULT NULL COMMENT '学期 1表示第一学期 2表示第二学期',
  `start_week` int(20) DEFAULT NULL COMMENT '时间表适用开始周',
  `end_week` int(20) DEFAULT NULL COMMENT '时间表适用结束',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `del_flag` int(10) DEFAULT NULL COMMENT '0存在  1删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `teach_course_node_init` */

LOCK TABLES `teach_course_node_init` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_course_type` */

DROP TABLE IF EXISTS `teach_course_type`;

CREATE TABLE `teach_course_type` (
  `id` varchar(50) NOT NULL COMMENT '课程类型id',
  `name` varchar(100) DEFAULT NULL COMMENT '课程类型名字',
  `school_id` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `del_flag` int(5) DEFAULT '0',
  `remark` varchar(200) DEFAULT NULL,
  `english_name` varchar(100) DEFAULT NULL COMMENT '英文名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_course_type` */

LOCK TABLES `teach_course_type` WRITE;

insert  into `teach_course_type`(`id`,`name`,`school_id`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`remark`,`english_name`) values ('8630815e3d9511e6809626d7c75c7844','语文',NULL,NULL,1498011234223,NULL,NULL,0,'1','Chinese'),('86311c803d9511e6809626d7c75c7844','数学',NULL,NULL,1498011234223,NULL,NULL,0,'2','Mathematics'),('86318f783d9511e6809626d7c75c7844','英语',NULL,NULL,1498011234223,NULL,NULL,0,'3','English'),('8631ff0f3d9511e6809626d7c75c7844','物理',NULL,NULL,1498011234223,NULL,NULL,0,'4','Physics'),('86325dae3d9511e6809626d7c75c7844','化学',NULL,NULL,1498011234223,NULL,NULL,0,'5','Chemistry'),('8632bbb13d9511e6809626d7c75c7844','生物',NULL,NULL,1498011234223,NULL,NULL,0,'6','Biology'),('86331a4f3d9511e6809626d7c75c7844','历史',NULL,NULL,1498011234223,NULL,NULL,0,'7','History'),('8633714f3d9511e6809626d7c75c7844','地理',NULL,NULL,1498011234223,NULL,NULL,0,'8','Geography'),('8633d6423d9511e6809626d7c75c7844','思想政治',NULL,NULL,1498011234223,NULL,NULL,0,'9','Politics'),('863438ff3d9511e6809626d7c75c7844','信息技术',NULL,NULL,1498011234223,NULL,NULL,0,'10','Information Technolo'),('8634c1673d9511e6809626d7c75c7844','通用技术',NULL,NULL,1498011234223,NULL,NULL,0,'11','Generic Technology'),('86352d443d9511e6809626d7c75c7844','科学',NULL,NULL,1498011234223,NULL,NULL,0,'12','Science'),('86358cb33d9511e6809626d7c75c7844','体育',NULL,NULL,1498011234223,NULL,NULL,0,'13','P.E.'),('8635eadc3d9511e6809626d7c75c7844','美术',NULL,NULL,1498011234223,NULL,NULL,0,'14','Fine Arts'),('863648cb3d9511e6809626d7c75c7844','音乐',NULL,NULL,1498011234223,NULL,NULL,0,'15','Music'),('8636a5bd3d9511e6809626d7c75c7844','体育与健康',NULL,NULL,1498011234223,NULL,NULL,0,'16','Sports and Health '),('8637025f3d9511e6809626d7c75c7844','汉语',NULL,NULL,1498011234223,NULL,NULL,0,'17','Chinese'),('863763603d9511e6809626d7c75c7844','藏语',NULL,NULL,1498011234223,NULL,NULL,0,'18','Tibetan'),('8637bfa93d9511e6809626d7c75c7844','技术',NULL,NULL,1498011234223,NULL,NULL,0,'19','Technology'),('86381f8f3d9511e6809626d7c75c7844','阅读',NULL,NULL,1498011234223,NULL,NULL,0,'20','Reading'),('86387ee73d9511e6809626d7c75c7844','心理健康教育',NULL,NULL,1498011234223,NULL,NULL,0,'21','Psychologically Heal'),('863930043d9511e6809626d7c75c7844','品德与生活',NULL,NULL,1498011234223,NULL,NULL,0,'22','Morality and Life'),('86398cbd3d9511e6809626d7c75c7844','历史与社会',NULL,NULL,1498011234223,NULL,NULL,0,'23','History and Society'),('8639efbe3d9511e6809626d7c75c7844','品德与社会',NULL,NULL,1498011234223,NULL,NULL,0,'24','History and Society'),('863a4e383d9511e6809626d7c75c7844','思想品德',NULL,NULL,1498011234223,NULL,NULL,0,'25','Moral'),('863aad593d9511e6809626d7c75c7844','综合实践',NULL,NULL,1498011234223,NULL,NULL,0,'26','Compositive Practice'),('863b0d803d9511e6809626d7c75c7844','综合实践活动',NULL,NULL,1498011234223,NULL,NULL,0,'27','Integrated Practice '),('863b867a3d9511e6809626d7c75c7844','其他',NULL,NULL,1498011234223,NULL,NULL,0,'28','Other');

UNLOCK TABLES;

/*Table structure for table `teach_cycle` */

DROP TABLE IF EXISTS `teach_cycle`;

CREATE TABLE `teach_cycle` (
  `id` varchar(50) NOT NULL COMMENT '教学周期id',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `cycle_name` varchar(100) DEFAULT NULL COMMENT '教学周期名称',
  `cycle_year` varchar(50) DEFAULT NULL COMMENT '学年',
  `cycle_semester` int(5) DEFAULT NULL COMMENT '学期[1：第一学期 2：第二学期]',
  `term_begin_time` bigint(100) DEFAULT NULL COMMENT '开学时间',
  `begin_date` bigint(50) DEFAULT NULL COMMENT '开始周',
  `end_date` bigint(50) DEFAULT NULL COMMENT '结束周',
  `week_count` int(10) DEFAULT NULL COMMENT '周次',
  `del_flag` int(5) DEFAULT '0' COMMENT '逻辑删除标记【0 正常  1 删除】',
  `create_date` bigint(50) DEFAULT NULL,
  `update_date` bigint(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_cycle` */

LOCK TABLES `teach_cycle` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_daily_hour` */

DROP TABLE IF EXISTS `teach_daily_hour`;

CREATE TABLE `teach_daily_hour` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `grade_class_id` varchar(50) DEFAULT NULL COMMENT '关联org_grade_class的id',
  `skts` int(20) DEFAULT NULL COMMENT '一周的上课天数',
  `swks` int(20) DEFAULT NULL COMMENT '上午课时',
  `xwks` int(20) DEFAULT NULL COMMENT '下午课时',
  `kjc` int(20) DEFAULT NULL COMMENT '课间操',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '关联teach_cycle_id',
  `xn` varchar(100) DEFAULT NULL COMMENT '学年',
  `xq` varchar(100) DEFAULT NULL COMMENT '学期',
  `create_time` bigint(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(50) DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `del_flag` int(10) DEFAULT NULL COMMENT '删除标识【0存在  1删除】',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `teach_daily_hour` */

LOCK TABLES `teach_daily_hour` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_ref_class_room` */

DROP TABLE IF EXISTS `teach_ref_class_room`;

CREATE TABLE `teach_ref_class_room` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `room_id` varchar(50) NOT NULL COMMENT '关联teach_class_class_room',
  `grade_class` varchar(50) NOT NULL COMMENT '关联org_grade_class',
  `cycle_id` varchar(50) NOT NULL COMMENT '关联teach_cycle',
  `school_type_id` varchar(50) NOT NULL COMMENT '关联org_school_type',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_ref_class_room` */

LOCK TABLES `teach_ref_class_room` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_ref_classcard_notify` */

DROP TABLE IF EXISTS `teach_ref_classcard_notify`;

CREATE TABLE `teach_ref_classcard_notify` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `class_card_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `class_card_notify_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `school_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `update_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记【0 正常，1 删除】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `teach_ref_classcard_notify` */

LOCK TABLES `teach_ref_classcard_notify` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_ref_course_class` */

DROP TABLE IF EXISTS `teach_ref_course_class`;

CREATE TABLE `teach_ref_course_class` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `course_hour` int(20) DEFAULT NULL COMMENT '课时数',
  `course_id` varchar(50) DEFAULT NULL COMMENT '课程id(关联teach_course的id)',
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级id（关联org_grade_class的id）',
  `teacher_id` varchar(50) DEFAULT NULL COMMENT '教师id （关联user_teacher的id）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_ref_course_class` */

LOCK TABLES `teach_ref_course_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_ref_room_cycle` */

DROP TABLE IF EXISTS `teach_ref_room_cycle`;

CREATE TABLE `teach_ref_room_cycle` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `room_id` varchar(50) DEFAULT NULL COMMENT '关联teach_class_room的id',
  `cycle_id` varchar(50) DEFAULT NULL COMMENT '关联teach_cycle的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_ref_room_cycle` */

LOCK TABLES `teach_ref_room_cycle` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_room_type` */

DROP TABLE IF EXISTS `teach_room_type`;

CREATE TABLE `teach_room_type` (
  `id` varchar(50) NOT NULL COMMENT '教室类型id',
  `name` varchar(100) DEFAULT NULL COMMENT '教室类型名称',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `del_flag` int(5) DEFAULT '0' COMMENT '0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_room_type` */

LOCK TABLES `teach_room_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_standard_course` */

DROP TABLE IF EXISTS `teach_standard_course`;

CREATE TABLE `teach_standard_course` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '标准课程名称',
  `english_name` varchar(200) DEFAULT NULL COMMENT '标准课程英文名称名称',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `sys` int(10) DEFAULT NULL COMMENT '是否系统默认【0是，1否】',
  `is_dictionary` int(10) DEFAULT NULL COMMENT '是否字典学科【0为字典学科，1为学校自定义学科】',
  `course_type_id` varchar(50) DEFAULT NULL COMMENT '科目类型id(关联teach_course_type)，即科目字字典表',
  `course_type_name` varchar(200) DEFAULT NULL COMMENT '科目类型名称',
  `create_date` bigint(100) DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint(100) DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(10) DEFAULT NULL COMMENT '0正常  1删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `teach_standard_course` */

LOCK TABLES `teach_standard_course` WRITE;

UNLOCK TABLES;

/*Table structure for table `teach_table` */

DROP TABLE IF EXISTS `teach_table`;

CREATE TABLE `teach_table` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `class_id` varchar(255) DEFAULT NULL COMMENT '班级id',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  `teacher_id` varchar(255) DEFAULT NULL COMMENT '教师id',
  `table_id` varchar(255) DEFAULT NULL COMMENT '课节1,1周一的第一节',
  `class_room_id` varchar(255) DEFAULT NULL COMMENT '教室id',
  `weekend` int(11) DEFAULT NULL COMMENT '周',
  `school_id` varchar(50) DEFAULT NULL,
  `cycle_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_table` */

LOCK TABLES `teach_table` WRITE;

UNLOCK TABLES;

/*Table structure for table `user_patriarch` */

DROP TABLE IF EXISTS `user_patriarch`;

CREATE TABLE `user_patriarch` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `student_school_id` varchar(50) DEFAULT NULL COMMENT '学生所在机构id，匹配学生id',
  `student_id` varchar(50) DEFAULT NULL COMMENT '学生ID,关联user_student的主键id',
  `pinyin` varchar(50) DEFAULT NULL COMMENT '家长全拼',
  `account` varchar(50) DEFAULT NULL COMMENT '家长账号',
  `work` varchar(255) DEFAULT NULL COMMENT '职业,职务',
  `work_at` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `phone` varchar(100) DEFAULT NULL COMMENT '家长电话',
  `gender` int(10) DEFAULT NULL COMMENT '性别[1男，2女]',
  `sfjhr` int(10) DEFAULT NULL COMMENT '是否为监护人[1 是，2否]',
  `sfyqsh` int(10) DEFAULT NULL COMMENT '是否一起生活[1 是，2否]',
  `patriarch_flag` int(10) DEFAULT NULL COMMENT '家长标识[1父亲，2母亲 3其他]',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记[0：正常；1：删除]',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家长信息表';

/*Data for the table `user_patriarch` */

LOCK TABLES `user_patriarch` WRITE;

UNLOCK TABLES;

/*Table structure for table `user_student` */

DROP TABLE IF EXISTS `user_student`;

CREATE TABLE `user_student` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `school_id` varchar(50) NOT NULL COMMENT '结构ID[所属学校ID]',
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级ID',
  `account` varchar(100) DEFAULT NULL COMMENT '学生登录账号',
  `xsxm` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `xmpy` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `xszp` varchar(64) DEFAULT NULL COMMENT '学生照片地址',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `csrq` bigint(20) DEFAULT NULL COMMENT '出生日期',
  `rxrq` bigint(20) DEFAULT NULL COMMENT '入学时间',
  `xsxb` int(10) DEFAULT NULL COMMENT '性别【1男，2女】',
  `xssg` varchar(100) DEFAULT NULL COMMENT '身高',
  `xd` varchar(50) DEFAULT NULL COMMENT '学段',
  `nj` int(10) DEFAULT NULL COMMENT '年级编号',
  `xh` varchar(100) DEFAULT NULL COMMENT '学号',
  `xjh` varchar(100) DEFAULT NULL COMMENT '学籍号',
  `qgxjh` varchar(100) DEFAULT NULL COMMENT '全国学籍号',
  `jyid` varchar(100) DEFAULT NULL COMMENT '教育ID号',
  `syd` varchar(100) DEFAULT NULL COMMENT '生源地',
  `yxzjlx` int(10) DEFAULT NULL COMMENT '有效证件类型[1身份证，2护照]',
  `yxzjh` varchar(100) DEFAULT NULL COMMENT '有效证件号',
  `jbs` varchar(255) DEFAULT NULL COMMENT '疾病史',
  `sfsbt` int(10) DEFAULT NULL COMMENT '是否是双胞胎[0 否 1是]',
  `sbtxh` int(10) DEFAULT NULL COMMENT '双胞胎序号',
  `xslb` int(10) DEFAULT NULL COMMENT '学生类别 【0 普通学生  1 随班就读生  2 残障学生 3 其他】',
  `gb` varchar(100) DEFAULT NULL COMMENT '国别',
  `mz` varchar(100) DEFAULT NULL COMMENT '民族',
  `jg` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `zzmm` int(10) DEFAULT NULL COMMENT '政治面貌 【0 群众  1团员  2 党员】',
  `zslb` int(10) DEFAULT NULL COMMENT '招生类别  【0 普通入学 1 名族班 2 体育特招 3 外校转入 4 恢复入学资格  5 外籍 6 其他】',
  `lydq` varchar(200) DEFAULT NULL COMMENT '来源地区 [0 区县内  1 省市内  2 省市外]',
  `hkszd` varchar(200) DEFAULT NULL COMMENT '户口所在地',
  `xjzd` varchar(200) DEFAULT NULL COMMENT '现居住址',
  `hkxz` varchar(100) DEFAULT NULL COMMENT '户口性质 【0农村  1城镇  2其他】',
  `sfbshk` int(10) DEFAULT NULL COMMENT '是否按本市户口学生对待[1是 2否]',
  `status` int(10) DEFAULT NULL COMMENT '学生状态[0在籍在校，1在籍离校，2在校不在籍，3不在籍不在校]',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生管理';

/*Data for the table `user_student` */

LOCK TABLES `user_student` WRITE;

UNLOCK TABLES;

/*Table structure for table `user_teacher` */

DROP TABLE IF EXISTS `user_teacher`;

CREATE TABLE `user_teacher` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构ID',
  `department_id` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `name` varchar(255) DEFAULT NULL COMMENT '教师名',
  `quan_pin` varchar(255) DEFAULT NULL COMMENT '姓名全拼',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `identity` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `account` varchar(100) DEFAULT NULL COMMENT '教师登录账号',
  `is_manage` int(10) DEFAULT '0' COMMENT '是否是管理员[0不是，1是]',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `title_id` varchar(50) DEFAULT NULL COMMENT '职务ID-[职务管理]',
  `no` varchar(100) DEFAULT NULL COMMENT '职工编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `start_work` bigint(20) DEFAULT NULL COMMENT '开始工作时间',
  `head_url` varchar(400) DEFAULT NULL COMMENT '头像',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `high_time` bigint(20) DEFAULT NULL COMMENT '最高毕业时间',
  `high_job` varchar(100) DEFAULT NULL COMMENT '最高专业',
  `zc` varchar(50) DEFAULT NULL COMMENT '职称',
  `pzxx` varchar(100) DEFAULT NULL COMMENT '评职详细',
  `address` varchar(200) DEFAULT NULL COMMENT '家庭住址详细',
  `ggjsjb` varchar(10) DEFAULT NULL COMMENT '骨干教师级别',
  `htkssj` bigint(20) DEFAULT NULL COMMENT '合同开始时间',
  `htjssj` bigint(20) DEFAULT NULL COMMENT '合同结束时间',
  `cym` varchar(100) DEFAULT NULL COMMENT '曾用名',
  `jtyb` varchar(10) DEFAULT NULL COMMENT '家庭邮编',
  `sfzrjs` varchar(10) DEFAULT NULL COMMENT '是否专任教师',
  `salary` varchar(50) DEFAULT NULL COMMENT '薪资',
  `jg` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `zzmm` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `cjgzsj` bigint(20) DEFAULT NULL COMMENT '参加工作时间',
  `ysbysj` bigint(20) DEFAULT NULL COMMENT '原始毕业时间',
  `rjxk` varchar(20) DEFAULT NULL COMMENT '任教学科',
  `sf` varchar(20) DEFAULT NULL COMMENT '身份',
  `wysp` varchar(20) DEFAULT NULL COMMENT '外语水平',
  `zgxz` varchar(20) DEFAULT NULL COMMENT '最高学制',
  `xwsl` varchar(10) DEFAULT NULL COMMENT '学位数量',
  `rjxkjb` varchar(10) DEFAULT NULL COMMENT '任教学科级别',
  `xq` varchar(20) DEFAULT NULL COMMENT '校区',
  `gwflf` varchar(20) DEFAULT NULL COMMENT '岗位分类副',
  `zgxl` varchar(20) DEFAULT NULL COMMENT '最高学历',
  `zgbyxx` varchar(20) DEFAULT NULL COMMENT '最高毕业学校',
  `yzy` varchar(20) DEFAULT NULL COMMENT '原专业',
  `pzsj` bigint(20) DEFAULT NULL COMMENT '评职时间',
  `lwxsj` bigint(20) DEFAULT NULL COMMENT '来我校时间',
  `zzdh` varchar(20) DEFAULT NULL COMMENT '住宅电话',
  `gzgw` varchar(20) DEFAULT NULL COMMENT '工资岗位',
  `bgsdh` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `sfhq` varchar(10) DEFAULT NULL COMMENT '是否华侨',
  `sfbzr` varchar(10) DEFAULT NULL COMMENT '是否班主任【1正班主任 2 副班主任 3 不是班主任】',
  `wyyz` varchar(20) DEFAULT NULL COMMENT '外语语种',
  `yxz` varchar(10) DEFAULT NULL COMMENT '原学制',
  `zgxw` varchar(20) DEFAULT NULL COMMENT '最高学位',
  `zyjsgwfl` varchar(20) DEFAULT NULL COMMENT '专业技术岗位分类',
  `szjb` varchar(10) DEFAULT NULL COMMENT '实职级别',
  `gzgwf` varchar(20) DEFAULT NULL COMMENT '工资岗位',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表：教育局、学校职工';

/*Data for the table `user_teacher` */

LOCK TABLES `user_teacher` WRITE;

UNLOCK TABLES;

/* Trigger structure for table `org_class_section` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassSection_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassSection_i` AFTER INSERT ON `org_class_section` FOR EACH ROW BEGIN
 INSERT INTO `sync_class_section`(`sync_id`,`school_id`,`name`,`short_name`,`limit_age`,`section_year`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.short_name,new.limit_age,new.section_year,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_class_section` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassSection_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassSection_m` AFTER UPDATE ON `org_class_section` FOR EACH ROW BEGIN
DELETE FROM `sync_class_section` where `sync_id` = new.id;INSERT INTO `sync_class_section`(`sync_id`,`school_id`,`name`,`short_name`,`limit_age`,`section_year`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.short_name,new.limit_age,new.section_year,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_class_section` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassSection_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassSection_d` AFTER DELETE ON `org_class_section` FOR EACH ROW BEGIN
INSERT INTO `sync_class_section`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_department` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Department_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Department_i` AFTER INSERT ON `org_department` FOR EACH ROW BEGIN
 INSERT INTO `sync_department`(`sync_id`,`parent_id`,`school_id`,`no`,`name`,`short_name`,`master_id`,`master_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.school_id,new.no,new.name,new.short_name,new.master_id,new.master_name,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_department` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Department_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Department_m` AFTER UPDATE ON `org_department` FOR EACH ROW BEGIN
DELETE FROM `sync_department` where `sync_id` = new.id;INSERT INTO `sync_department`(`sync_id`,`parent_id`,`school_id`,`no`,`name`,`short_name`,`master_id`,`master_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.school_id,new.no,new.name,new.short_name,new.master_id,new.master_name,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_department` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Department_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Department_d` AFTER DELETE ON `org_department` FOR EACH ROW BEGIN
INSERT INTO `sync_department`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_grade_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `GradeClass_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `GradeClass_i` AFTER INSERT ON `org_grade_class` FOR EACH ROW BEGIN
 INSERT INTO `sync_grade_class`(`sync_id`,`school_id`,`name`,`xd`,`nj`,`bh`,`bjlx`,`xq`,`cycle_id`,`rxnd`,`master_id`,`master_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.xd,new.nj,new.bh,new.bjlx,new.xq,new.cycle_id,new.rxnd,new.master_id,new.master_name,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_grade_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `GradeClass_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `GradeClass_m` AFTER UPDATE ON `org_grade_class` FOR EACH ROW BEGIN
DELETE FROM `sync_grade_class` where `sync_id` = new.id;INSERT INTO `sync_grade_class`(`sync_id`,`school_id`,`name`,`xd`,`nj`,`bh`,`bjlx`,`xq`,`cycle_id`,`rxnd`,`master_id`,`master_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.xd,new.nj,new.bh,new.bjlx,new.xq,new.cycle_id,new.rxnd,new.master_id,new.master_name,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_grade_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `GradeClass_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `GradeClass_d` AFTER DELETE ON `org_grade_class` FOR EACH ROW BEGIN
INSERT INTO `sync_grade_class`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `School_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `School_i` AFTER INSERT ON `org_school` FOR EACH ROW BEGIN
 INSERT INTO `sync_org_school`(`sync_id`,`parent_id`,`name`,`ename`,`xz`,`type`,`grade`,`logo`,`bg_picture`,`address`,`phone`,`short_flag`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.name,new.ename,new.xz,new.type,new.grade,new.logo,new.bg_picture,new.address,new.phone,new.short_flag,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `School_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `School_m` AFTER UPDATE ON `org_school` FOR EACH ROW BEGIN
DELETE FROM `sync_org_school` where `sync_id` = new.id;INSERT INTO `sync_org_school`(`sync_id`,`parent_id`,`name`,`ename`,`xz`,`type`,`grade`,`logo`,`bg_picture`,`address`,`phone`,`short_flag`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.name,new.ename,new.xz,new.type,new.grade,new.logo,new.bg_picture,new.address,new.phone,new.short_flag,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `School_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `School_d` AFTER DELETE ON `org_school` FOR EACH ROW BEGIN
INSERT INTO `sync_org_school`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `SchoolType_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `SchoolType_i` AFTER INSERT ON `org_school_type` FOR EACH ROW BEGIN
 INSERT INTO `sync_school_type`(`sync_id`,`school_id`,`name`,`email`,`phone`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.email,new.phone,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `SchoolType_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `SchoolType_m` AFTER UPDATE ON `org_school_type` FOR EACH ROW BEGIN
DELETE FROM `sync_school_type` where `sync_id` = new.id;INSERT INTO `sync_school_type`(`sync_id`,`school_id`,`name`,`email`,`phone`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.email,new.phone,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `SchoolType_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `SchoolType_d` AFTER DELETE ON `org_school_type` FOR EACH ROW BEGIN
INSERT INTO `sync_school_type`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_title` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Title_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Title_i` AFTER INSERT ON `org_title` FOR EACH ROW BEGIN
 INSERT INTO `sync_title`(`sync_id`,`school_id`,`mc`,`jb`,`px`,`del_flag`,`update_date`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.mc,new.jb,new.px,new.del_flag,new.update_date,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_title` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Title_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Title_m` AFTER UPDATE ON `org_title` FOR EACH ROW BEGIN
DELETE FROM `sync_title` where `sync_id` = new.id;INSERT INTO `sync_title`(`sync_id`,`school_id`,`mc`,`jb`,`px`,`del_flag`,`update_date`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.mc,new.jb,new.px,new.del_flag,new.update_date,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_title` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Title_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Title_d` AFTER DELETE ON `org_title` FOR EACH ROW BEGIN
INSERT INTO `sync_title`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `ref_teacher_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeacherClass_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeacherClass_i` AFTER INSERT ON `ref_teacher_class` FOR EACH ROW BEGIN
 INSERT INTO `sync_teacher_class`(`sync_teacher_id`,`type`,`sync_class_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.teacher_id,new.type,new.class_id,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `ref_teacher_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeacherClass_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeacherClass_m` AFTER UPDATE ON `ref_teacher_class` FOR EACH ROW BEGIN
DELETE FROM `sync_teacher_class` where `sync_teacher_id` = new.teacher_id AND `sync_class_id`= new.class_id;INSERT INTO `sync_teacher_class`(`sync_teacher_id`,`type`,`sync_class_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.teacher_id,new.type,new.class_id,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `ref_teacher_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeacherClass_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeacherClass_d` AFTER DELETE ON `ref_teacher_class` FOR EACH ROW BEGIN
INSERT INTO `sync_teacher_class`(`sync_teacher_id`,`sync_class_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.teacher_id,old.class_id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `sys_user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `User_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `User_i` AFTER INSERT ON `sys_user` FOR EACH ROW BEGIN
 INSERT INTO `sync_user`(`sync_id`,`school_id`,`username`,`password`,`ref_id`,`user_type`,`photo_url`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.username,new.password,new.ref_id,new.user_type,new.photo_url,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `sys_user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `User_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `User_m` AFTER UPDATE ON `sys_user` FOR EACH ROW BEGIN
DELETE FROM `sync_user` where `sync_id` = new.id;INSERT INTO `sync_user`(`sync_id`,`school_id`,`username`,`password`,`ref_id`,`user_type`,`photo_url`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.username,new.password,new.ref_id,new.user_type,new.photo_url,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `sys_user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `User_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `User_d` AFTER DELETE ON `sys_user` FOR EACH ROW BEGIN
INSERT INTO `sync_user`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassRoom_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassRoom_i` AFTER INSERT ON `teach_class_room` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_class_room`(`sync_id`,`room_name`,`room_type`,`room_type_name`,`room_num`,`school_id`,`teach_building`,`teach_building_num`,`school_type`,`school_type_name`,`floor`,`count`,`available_seat`,`exam_seat`,`course_select`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_name,new.room_type,new.room_type_name,new.room_num,new.school_id,new.teach_building,new.teach_building_num,new.school_type,new.school_type_name,new.floor,new.count,new.available_seat,new.exam_seat,new.course_select,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassRoom_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassRoom_m` AFTER UPDATE ON `teach_class_room` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_class_room` where `sync_id` = new.id;INSERT INTO `sync_teach_class_room`(`sync_id`,`room_name`,`room_type`,`room_type_name`,`room_num`,`school_id`,`teach_building`,`teach_building_num`,`school_type`,`school_type_name`,`floor`,`count`,`available_seat`,`exam_seat`,`course_select`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_name,new.room_type,new.room_type_name,new.room_num,new.school_id,new.teach_building,new.teach_building_num,new.school_type,new.school_type_name,new.floor,new.count,new.available_seat,new.exam_seat,new.course_select,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassRoom_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassRoom_d` AFTER DELETE ON `teach_class_room` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_class_room`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Course_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Course_i` AFTER INSERT ON `teach_course` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_course`(`sync_id`,`school_id`,`name`,`english_name`,`cycle_id`,`short_name`,`course_type`,`room_type`,`score`,`pass_score`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.english_name,new.cycle_id,new.short_name,new.course_type,new.room_type,new.score,new.pass_score,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Course_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Course_m` AFTER UPDATE ON `teach_course` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_course` where `sync_id` = new.id;INSERT INTO `sync_teach_course`(`sync_id`,`school_id`,`name`,`english_name`,`cycle_id`,`short_name`,`course_type`,`room_type`,`score`,`pass_score`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.english_name,new.cycle_id,new.short_name,new.course_type,new.room_type,new.score,new.pass_score,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Course_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Course_d` AFTER DELETE ON `teach_course` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_course`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNode_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNode_i` AFTER INSERT ON `teach_course_node` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_course_node`(`sync_id`,`school_id`,`course_node_init_id`,`cycle_id`,`cycle_year`,`cycle_semester`,`node`,`node_name`,`start_time`,`end_time`,`morning_afternoon`,`week`,`del_flag`,`summer_winter_time`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.course_node_init_id,new.cycle_id,new.cycle_year,new.cycle_semester,new.node,new.node_name,new.start_time,new.end_time,new.morning_afternoon,new.week,new.del_flag,new.summer_winter_time,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNode_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNode_m` AFTER UPDATE ON `teach_course_node` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_course_node` where `sync_id` = new.id;INSERT INTO `sync_teach_course_node`(`sync_id`,`school_id`,`course_node_init_id`,`cycle_id`,`cycle_year`,`cycle_semester`,`node`,`node_name`,`start_time`,`end_time`,`morning_afternoon`,`week`,`del_flag`,`summer_winter_time`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.course_node_init_id,new.cycle_id,new.cycle_year,new.cycle_semester,new.node,new.node_name,new.start_time,new.end_time,new.morning_afternoon,new.week,new.del_flag,new.summer_winter_time,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNode_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNode_d` AFTER DELETE ON `teach_course_node` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_course_node`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node_init` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNodeInit_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNodeInit_i` AFTER INSERT ON `teach_course_node_init` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_course_node_init`(`sync_id`,`school_id`,`name`,`cycle_id`,`cycle_year`,`cycle_semester`,`start_week`,`end_week`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.cycle_id,new.cycle_year,new.cycle_semester,new.start_week,new.end_week,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node_init` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNodeInit_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNodeInit_m` AFTER UPDATE ON `teach_course_node_init` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_course_node_init` where `sync_id` = new.id;INSERT INTO `sync_teach_course_node_init`(`sync_id`,`school_id`,`name`,`cycle_id`,`cycle_year`,`cycle_semester`,`start_week`,`end_week`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.cycle_id,new.cycle_year,new.cycle_semester,new.start_week,new.end_week,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_node_init` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseNodeInit_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseNodeInit_d` AFTER DELETE ON `teach_course_node_init` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_course_node_init`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseType_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseType_i` AFTER INSERT ON `teach_course_type` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_course_type`(`sync_id`,`name`,`school_id`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.school_id,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseType_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseType_m` AFTER UPDATE ON `teach_course_type` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_course_type` where `sync_id` = new.id;INSERT INTO `sync_teach_course_type`(`sync_id`,`name`,`school_id`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.school_id,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_course_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseType_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseType_d` AFTER DELETE ON `teach_course_type` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_course_type`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachCycle_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachCycle_i` AFTER INSERT ON `teach_cycle` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_cycle`(`sync_id`,`school_id`,`cycle_name`,`cycle_year`,`cycle_semester`,`term_begin_time`,`begin_date`,`end_date`,`week_count`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.cycle_name,new.cycle_year,new.cycle_semester,new.term_begin_time,new.begin_date,new.end_date,new.week_count,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachCycle_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachCycle_m` AFTER UPDATE ON `teach_cycle` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_cycle` where `sync_id` = new.id;INSERT INTO `sync_teach_cycle`(`sync_id`,`school_id`,`cycle_name`,`cycle_year`,`cycle_semester`,`term_begin_time`,`begin_date`,`end_date`,`week_count`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.cycle_name,new.cycle_year,new.cycle_semester,new.term_begin_time,new.begin_date,new.end_date,new.week_count,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachCycle_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachCycle_d` AFTER DELETE ON `teach_cycle` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_cycle`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_daily_hour` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `DailyHour_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `DailyHour_i` AFTER INSERT ON `teach_daily_hour` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_daily_hour`(`sync_id`,`school_id`,`grade_class_id`,`skts`,`swks`,`xwks`,`kjc`,`cycle_id`,`xn`,`xq`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.grade_class_id,new.skts,new.swks,new.xwks,new.kjc,new.cycle_id,new.xn,new.xq,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_daily_hour` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `DailyHour_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `DailyHour_m` AFTER UPDATE ON `teach_daily_hour` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_daily_hour` where `sync_id` = new.id;INSERT INTO `sync_teach_daily_hour`(`sync_id`,`school_id`,`grade_class_id`,`skts`,`swks`,`xwks`,`kjc`,`cycle_id`,`xn`,`xq`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.grade_class_id,new.skts,new.swks,new.xwks,new.kjc,new.cycle_id,new.xn,new.xq,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_daily_hour` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `DailyHour_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `DailyHour_d` AFTER DELETE ON `teach_daily_hour` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_daily_hour`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefClassRoom_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefClassRoom_i` AFTER INSERT ON `teach_ref_class_room` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_ref_class_room`(`sync_id`,`room_id`,`grade_class`,`cycle_id`,`school_type_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_id,new.grade_class,new.cycle_id,new.school_type_id,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefClassRoom_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefClassRoom_m` AFTER UPDATE ON `teach_ref_class_room` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_ref_class_room` where `sync_id` = new.id;INSERT INTO `sync_teach_ref_class_room`(`sync_id`,`room_id`,`grade_class`,`cycle_id`,`school_type_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_id,new.grade_class,new.cycle_id,new.school_type_id,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_class_room` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefClassRoom_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefClassRoom_d` AFTER DELETE ON `teach_ref_class_room` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_ref_class_room`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_course_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseClass_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseClass_i` AFTER INSERT ON `teach_ref_course_class` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_ref_course_class`(`sync_id`,`course_hour`,`course_id`,`class_id`,`teacher_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.course_hour,new.course_id,new.class_id,new.teacher_id,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_course_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseClass_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseClass_m` AFTER UPDATE ON `teach_ref_course_class` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_ref_course_class` where `sync_id` = new.id;INSERT INTO `sync_teach_ref_course_class`(`sync_id`,`course_hour`,`course_id`,`class_id`,`teacher_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.course_hour,new.course_id,new.class_id,new.teacher_id,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_course_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `CourseClass_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `CourseClass_d` AFTER DELETE ON `teach_ref_course_class` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_ref_course_class`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_room_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefRoomCycle_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefRoomCycle_i` AFTER INSERT ON `teach_ref_room_cycle` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_ref_room_cycle`(`sync_id`,`room_id`,`cycle_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_id,new.cycle_id,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_room_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefRoomCycle_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefRoomCycle_m` AFTER UPDATE ON `teach_ref_room_cycle` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_ref_room_cycle` where `sync_id` = new.id;INSERT INTO `sync_teach_ref_room_cycle`(`sync_id`,`room_id`,`cycle_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.room_id,new.cycle_id,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_ref_room_cycle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RefRoomCycle_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RefRoomCycle_d` AFTER DELETE ON `teach_ref_room_cycle` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_ref_room_cycle`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_room_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RoomType_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RoomType_i` AFTER INSERT ON `teach_room_type` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_room_type`(`sync_id`,`name`,`school_id`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.school_id,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_room_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RoomType_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RoomType_m` AFTER UPDATE ON `teach_room_type` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_room_type` where `sync_id` = new.id;INSERT INTO `sync_teach_room_type`(`sync_id`,`name`,`school_id`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.school_id,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_room_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `RoomType_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `RoomType_d` AFTER DELETE ON `teach_room_type` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_room_type`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_standard_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StandardCourse_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StandardCourse_i` AFTER INSERT ON `teach_standard_course` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_standard_course`(`sync_id`,`name`,`english_name`,`school_id`,`sys`,`is_dictionary`,`course_type_id`,`course_type_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.english_name,new.school_id,new.sys,new.is_dictionary,new.course_type_id,new.course_type_name,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_standard_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StandardCourse_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StandardCourse_m` AFTER UPDATE ON `teach_standard_course` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_standard_course` where `sync_id` = new.id;INSERT INTO `sync_teach_standard_course`(`sync_id`,`name`,`english_name`,`school_id`,`sys`,`is_dictionary`,`course_type_id`,`course_type_name`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.english_name,new.school_id,new.sys,new.is_dictionary,new.course_type_id,new.course_type_name,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_standard_course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StandardCourse_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StandardCourse_d` AFTER DELETE ON `teach_standard_course` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_standard_course`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_table` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachTable_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachTable_i` AFTER INSERT ON `teach_table` FOR EACH ROW BEGIN
 INSERT INTO `sync_teach_table`(`sync_id`,`class_id`,`course_id`,`teacher_id`,`table_id`,`class_room_id`,`weekend`,`school_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.class_id,new.course_id,new.teacher_id,new.table_id,new.class_room_id,new.weekend,new.school_id,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_table` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachTable_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachTable_m` AFTER UPDATE ON `teach_table` FOR EACH ROW BEGIN
DELETE FROM `sync_teach_table` where `sync_id` = new.id;INSERT INTO `sync_teach_table`(`sync_id`,`class_id`,`course_id`,`teacher_id`,`table_id`,`class_room_id`,`weekend`,`school_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.class_id,new.course_id,new.teacher_id,new.table_id,new.class_room_id,new.weekend,new.school_id,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `teach_table` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TeachTable_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TeachTable_d` AFTER DELETE ON `teach_table` FOR EACH ROW BEGIN
INSERT INTO `sync_teach_table`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_patriarch` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Patriarch_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Patriarch_i` AFTER INSERT ON `user_patriarch` FOR EACH ROW BEGIN
 INSERT INTO `sync_patriarch`(`sync_id`,`name`,`student_school_id`,`student_id`,`account`,`work`,`work_at`,`phone`,`gender`,`sfjhr`,`sfyqsh`,`patriarch_flag`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.student_school_id,new.student_id,new.account,new.work,new.work_at,new.phone,new.gender,new.sfjhr,new.sfyqsh,new.patriarch_flag,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_patriarch` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Patriarch_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Patriarch_m` AFTER UPDATE ON `user_patriarch` FOR EACH ROW BEGIN
DELETE FROM `sync_patriarch` where `sync_id` = new.id;INSERT INTO `sync_patriarch`(`sync_id`,`name`,`student_school_id`,`student_id`,`account`,`work`,`work_at`,`phone`,`gender`,`sfjhr`,`sfyqsh`,`patriarch_flag`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.student_school_id,new.student_id,new.account,new.work,new.work_at,new.phone,new.gender,new.sfjhr,new.sfyqsh,new.patriarch_flag,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_patriarch` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Patriarch_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Patriarch_d` AFTER DELETE ON `user_patriarch` FOR EACH ROW BEGIN
INSERT INTO `sync_patriarch`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Student_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Student_i` AFTER INSERT ON `user_student` FOR EACH ROW BEGIN
 INSERT INTO `sync_student`(`sync_id`,`school_id`,`class_id`,`account`,`xsxm`,`xszp`,`phone`,`csrq`,`rxrq`,`xsxb`,`xssg`,`xd`,`nj`,`xh`,`xjh`,`qgxjh`,`jyid`,`syd`,`yxzjlx`,`yxzjh`,`jbs`,`sfsbt`,`sbtxh`,`xslb`,`gb`,`mz`,`jg`,`zzmm`,`zslb`,`lydq`,`hkszd`,`xjzd`,`hkxz`,`status`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.class_id,new.account,new.xsxm,new.xszp,new.phone,new.csrq,new.rxrq,new.xsxb,new.xssg,new.xd,new.nj,new.xh,new.xjh,new.qgxjh,new.jyid,new.syd,new.yxzjlx,new.yxzjh,new.jbs,new.sfsbt,new.sbtxh,new.xslb,new.gb,new.mz,new.jg,new.zzmm,new.zslb,new.lydq,new.hkszd,new.xjzd,new.hkxz,new.status,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Student_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Student_m` AFTER UPDATE ON `user_student` FOR EACH ROW BEGIN
DELETE FROM `sync_student` where `sync_id` = new.id;INSERT INTO `sync_student`(`sync_id`,`school_id`,`class_id`,`account`,`xsxm`,`xszp`,`phone`,`csrq`,`rxrq`,`xsxb`,`xssg`,`xd`,`nj`,`xh`,`xjh`,`qgxjh`,`jyid`,`syd`,`yxzjlx`,`yxzjh`,`jbs`,`sfsbt`,`sbtxh`,`xslb`,`gb`,`mz`,`jg`,`zzmm`,`zslb`,`lydq`,`hkszd`,`xjzd`,`hkxz`,`status`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.class_id,new.account,new.xsxm,new.xszp,new.phone,new.csrq,new.rxrq,new.xsxb,new.xssg,new.xd,new.nj,new.xh,new.xjh,new.qgxjh,new.jyid,new.syd,new.yxzjlx,new.yxzjh,new.jbs,new.sfsbt,new.sbtxh,new.xslb,new.gb,new.mz,new.jg,new.zzmm,new.zslb,new.lydq,new.hkszd,new.xjzd,new.hkxz,new.status,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Student_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Student_d` AFTER DELETE ON `user_student` FOR EACH ROW BEGIN
INSERT INTO `sync_student`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_teacher` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Teacher_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Teacher_i` AFTER INSERT ON `user_teacher` FOR EACH ROW BEGIN
 INSERT INTO `sync_teacher`(`sync_id`,`school_id`,`department_id`,`name`,`gender`,`identity`,`account`,`is_manage`,`title_id`,`no`,`phone`,`mobile`,`email`,`start_work`,`head_url`,`high_time`,`zc`,`sfzrjs`,`jg`,`zzmm`,`rjxk`,`xq`,`zgxl`,`lwxsj`,`sfhq`,`sfbzr`,`wyyz`,`zyjsgwfl`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.department_id,new.name,new.gender,new.identity,new.account,new.is_manage,new.title_id,new.no,new.phone,new.mobile,new.email,new.start_work,new.head_url,new.high_time,new.zc,new.sfzrjs,new.jg,new.zzmm,new.rjxk,new.xq,new.zgxl,new.lwxsj,new.sfhq,new.sfbzr,new.wyyz,new.zyjsgwfl,new.del_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_teacher` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Teacher_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Teacher_m` AFTER UPDATE ON `user_teacher` FOR EACH ROW BEGIN
DELETE FROM `sync_teacher` where `sync_id` = new.id;INSERT INTO `sync_teacher`(`sync_id`,`school_id`,`department_id`,`name`,`gender`,`identity`,`account`,`is_manage`,`title_id`,`no`,`phone`,`mobile`,`email`,`start_work`,`head_url`,`high_time`,`zc`,`sfzrjs`,`jg`,`zzmm`,`rjxk`,`xq`,`zgxl`,`lwxsj`,`sfhq`,`sfbzr`,`wyyz`,`zyjsgwfl`,`del_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.department_id,new.name,new.gender,new.identity,new.account,new.is_manage,new.title_id,new.no,new.phone,new.mobile,new.email,new.start_work,new.head_url,new.high_time,new.zc,new.sfzrjs,new.jg,new.zzmm,new.rjxk,new.xq,new.zgxl,new.lwxsj,new.sfhq,new.sfbzr,new.wyyz,new.zyjsgwfl,new.del_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_teacher` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Teacher_d` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Teacher_d` AFTER DELETE ON `user_teacher` FOR EACH ROW BEGIN
INSERT INTO `sync_teacher`(`sync_id`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (old.id,'DELETE','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/*Table structure for table `v_class_xd_xq` */

DROP TABLE IF EXISTS `v_class_xd_xq`;

/*!50001 DROP VIEW IF EXISTS `v_class_xd_xq` */;
/*!50001 DROP TABLE IF EXISTS `v_class_xd_xq` */;

/*!50001 CREATE TABLE  `v_class_xd_xq`(
 `classId` varchar(50) ,
 `schoolId` varchar(50) ,
 `className` varchar(255) ,
 `classShortName` varchar(255) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `bh` int(10) ,
 `bjlx` int(10) ,
 `xq` varchar(255) ,
 `rxnd` bigint(20) ,
 `master_id` int(11) ,
 `master_name` varchar(100) ,
 `schoolName` varchar(100) ,
 `email` varchar(200) ,
 `schoolPhone` varchar(10) ,
 `sectionName` varchar(255) ,
 `sectiongShortName` varchar(255) ,
 `limit_age` int(10) ,
 `section_year` int(10) 
)*/;

/*Table structure for table `v_depart_school` */

DROP TABLE IF EXISTS `v_depart_school`;

/*!50001 DROP VIEW IF EXISTS `v_depart_school` */;
/*!50001 DROP TABLE IF EXISTS `v_depart_school` */;

/*!50001 CREATE TABLE  `v_depart_school`(
 `departId` varchar(50) ,
 `departPar` varchar(50) ,
 `no` varchar(50) ,
 `departName` varchar(255) ,
 `master_id` varchar(50) ,
 `master_name` varchar(100) ,
 `schoolId` varchar(50) ,
 `schoolPar` varchar(50) ,
 `schoolName` varchar(100) ,
 `ename` varchar(255) ,
 `sort` int(10) ,
 `xz` varchar(255) ,
 `code` varchar(100) ,
 `type` int(10) ,
 `grade` int(10) ,
 `logo` varchar(255) ,
 `bg_picture` varchar(255) ,
 `home_text` varchar(255) ,
 `bottom_text` varchar(255) ,
 `address` varchar(255) ,
 `m_id` varchar(50) ,
 `master` varchar(100) ,
 `zip_code` varchar(100) ,
 `phone` varchar(200) ,
 `fax` varchar(200) ,
 `email` varchar(200) ,
 `patriarch_rule` int(10) ,
 `student_rule` int(10) ,
 `teacher_rule` int(10) ,
 `short_flag` varchar(100) ,
 `deploy_url` varchar(255) ,
 `userable` varchar(64) ,
 `primary_persion` varchar(64) ,
 `deputy_persion` varchar(64) ,
 `create_by` varchar(50) ,
 `create_date` bigint(20) ,
 `update_by` varchar(50) ,
 `update_date` bigint(20) ,
 `remarks` varchar(255) ,
 `del_flag` int(10) 
)*/;

/*Table structure for table `v_partiarch_student` */

DROP TABLE IF EXISTS `v_partiarch_student`;

/*!50001 DROP VIEW IF EXISTS `v_partiarch_student` */;
/*!50001 DROP TABLE IF EXISTS `v_partiarch_student` */;

/*!50001 CREATE TABLE  `v_partiarch_student`(
 `parentId` varchar(50) ,
 `parentName` varchar(100) ,
 `parAccount` varchar(50) ,
 `work` varchar(255) ,
 `work_at` varchar(255) ,
 `parentPhone` varchar(100) ,
 `parentGender` int(10) ,
 `sfjhr` int(10) ,
 `sfyqsh` int(10) ,
 `patriarch_flag` int(10) ,
 `delFlag` int(10) ,
 `studentId` varchar(50) ,
 `schoolId` varchar(50) ,
 `classId` varchar(50) ,
 `studentAccount` varchar(100) ,
 `xsxm` varchar(64) ,
 `xmpy` varchar(64) ,
 `xszp` varchar(64) ,
 `studentPhone` varchar(100) ,
 `csrq` bigint(20) ,
 `rxrq` bigint(20) ,
 `xsxb` int(10) ,
 `xssg` varchar(100) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `xh` varchar(100) ,
 `xjh` varchar(100) ,
 `qgxjh` varchar(100) ,
 `jyid` varchar(100) ,
 `syd` varchar(100) ,
 `yxzjlx` int(10) ,
 `yxzjh` varchar(100) ,
 `jbs` varchar(255) ,
 `sfsbt` int(10) ,
 `sbtxh` int(10) ,
 `xslb` int(10) ,
 `gb` varchar(100) ,
 `mz` varchar(100) ,
 `jg` varchar(100) ,
 `zzmm` int(10) ,
 `zslb` int(10) ,
 `lydq` varchar(200) ,
 `hkszd` varchar(200) ,
 `xjzd` varchar(200) ,
 `hkxz` varchar(100) ,
 `sfbshk` int(10) ,
 `status` int(10) 
)*/;

/*Table structure for table `v_partiarch_student_class_info` */

DROP TABLE IF EXISTS `v_partiarch_student_class_info`;

/*!50001 DROP VIEW IF EXISTS `v_partiarch_student_class_info` */;
/*!50001 DROP TABLE IF EXISTS `v_partiarch_student_class_info` */;

/*!50001 CREATE TABLE  `v_partiarch_student_class_info`(
 `parentId` varchar(50) ,
 `parentName` varchar(100) ,
 `parAccount` varchar(50) ,
 `work` varchar(255) ,
 `work_at` varchar(255) ,
 `parentPhone` varchar(100) ,
 `parentGender` int(10) ,
 `sfjhr` int(10) ,
 `sfyqsh` int(10) ,
 `patriarch_flag` int(10) ,
 `studentId` varchar(50) ,
 `schoolId` varchar(50) ,
 `school` varchar(100) ,
 `classId` varchar(50) ,
 `studentAccount` varchar(100) ,
 `xsxm` varchar(64) ,
 `xmpy` varchar(64) ,
 `xszp` varchar(64) ,
 `studengPhone` varchar(100) ,
 `csrq` bigint(20) ,
 `rxrq` bigint(20) ,
 `xsxb` int(10) ,
 `xssg` varchar(100) ,
 `xh` varchar(100) ,
 `xjh` varchar(100) ,
 `qgxjh` varchar(100) ,
 `jyid` varchar(100) ,
 `syd` varchar(100) ,
 `yxzjlx` int(10) ,
 `yxzjh` varchar(100) ,
 `jbs` varchar(255) ,
 `sfsbt` int(10) ,
 `sbtxh` int(10) ,
 `xslb` int(10) ,
 `gb` varchar(100) ,
 `mz` varchar(100) ,
 `jg` varchar(100) ,
 `zzmm` int(10) ,
 `zslb` int(10) ,
 `lydq` varchar(200) ,
 `hkszd` varchar(200) ,
 `xjzd` varchar(200) ,
 `hkxz` varchar(100) ,
 `sfbshk` int(10) ,
 `status` int(10) ,
 `delFlag` int(10) ,
 `className` varchar(255) ,
 `classShortName` varchar(255) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `bh` int(10) ,
 `bjlx` int(10) ,
 `xq` varchar(255) ,
 `rxnd` bigint(20) ,
 `master_id` int(11) ,
 `master_name` varchar(100) ,
 `schoolName` varchar(100) ,
 `email` varchar(200) ,
 `schoolPhone` varchar(10) ,
 `sectionName` varchar(255) ,
 `sectiongShortName` varchar(255) ,
 `limit_age` int(10) ,
 `section_year` int(10) 
)*/;

/*Table structure for table `v_ref_teacher_class_deputy` */

DROP TABLE IF EXISTS `v_ref_teacher_class_deputy`;

/*!50001 DROP VIEW IF EXISTS `v_ref_teacher_class_deputy` */;
/*!50001 DROP TABLE IF EXISTS `v_ref_teacher_class_deputy` */;

/*!50001 CREATE TABLE  `v_ref_teacher_class_deputy`(
 `cname` varchar(255) ,
 `cid` varchar(50) ,
 `bh` int(10) ,
 `GROUP_CONCAT(t.id)` text ,
 `tname` text ,
 `class_id` varchar(50) ,
 `teacher_id` varchar(50) ,
 `cycleId` varchar(50) ,
 `csId` varchar(50) ,
 `csname` varchar(255) 
)*/;

/*Table structure for table `v_ref_teacher_class_master` */

DROP TABLE IF EXISTS `v_ref_teacher_class_master`;

/*!50001 DROP VIEW IF EXISTS `v_ref_teacher_class_master` */;
/*!50001 DROP TABLE IF EXISTS `v_ref_teacher_class_master` */;

/*!50001 CREATE TABLE  `v_ref_teacher_class_master`(
 `cname` varchar(255) ,
 `cid` varchar(50) ,
 `bh` int(10) ,
 `id` varchar(50) ,
 `tname` varchar(255) ,
 `class_id` varchar(50) ,
 `teacher_id` varchar(50) ,
 `cycleId` varchar(50) ,
 `csId` varchar(50) ,
 `csname` varchar(255) 
)*/;

/*Table structure for table `v_section_class` */

DROP TABLE IF EXISTS `v_section_class`;

/*!50001 DROP VIEW IF EXISTS `v_section_class` */;
/*!50001 DROP TABLE IF EXISTS `v_section_class` */;

/*!50001 CREATE TABLE  `v_section_class`(
 `schoolId` varchar(50) ,
 `indexName` varchar(266) ,
 `classId` varchar(50) ,
 `className` varchar(255) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `xq` varchar(255) ,
 `name` varchar(255) ,
 `sectionYear` int(10) ,
 `sectionShortName` varchar(255) 
)*/;

/*Table structure for table `v_select_teach_table` */

DROP TABLE IF EXISTS `v_select_teach_table`;

/*!50001 DROP VIEW IF EXISTS `v_select_teach_table` */;
/*!50001 DROP TABLE IF EXISTS `v_select_teach_table` */;

/*!50001 CREATE TABLE  `v_select_teach_table`(
 `id` varchar(255) ,
 `sync_id` varchar(255) ,
 `class_id` varchar(255) ,
 `course_id` varchar(255) ,
 `teacher_id` varchar(255) ,
 `table_id` varchar(255) ,
 `class_room_id` varchar(255) ,
 `weekend` int(11) ,
 `school_id` varchar(50) ,
 `xd` varchar(50) ,
 `class_name` varchar(255) ,
 `class_short` varchar(255) ,
 `xn` varchar(50) ,
 `xq` int(5) ,
 `week_start` bigint(100) ,
 `kcmc` varchar(100) ,
 `cycle_id` varchar(50) ,
 `course_short` varchar(100) ,
 `start_time` bigint(50) ,
 `end_time` bigint(50) ,
 `node` bigint(21) ,
 `weekofday` bigint(21) 
)*/;

/*Table structure for table `v_student_class_school` */

DROP TABLE IF EXISTS `v_student_class_school`;

/*!50001 DROP VIEW IF EXISTS `v_student_class_school` */;
/*!50001 DROP TABLE IF EXISTS `v_student_class_school` */;

/*!50001 CREATE TABLE  `v_student_class_school`(
 `id` varchar(50) ,
 `school_id` varchar(50) ,
 `class_id` varchar(50) ,
 `student_account` varchar(100) ,
 `xsxm` varchar(64) ,
 `xmpy` varchar(64) ,
 `xszp` varchar(64) ,
 `phone` varchar(100) ,
 `csrq` bigint(20) ,
 `rxrq` bigint(20) ,
 `xsxb` int(10) ,
 `xssg` varchar(100) ,
 `xh` varchar(100) ,
 `xjh` varchar(100) ,
 `qgxjh` varchar(100) ,
 `jyid` varchar(100) ,
 `syd` varchar(100) ,
 `yxzjlx` int(10) ,
 `yxzjh` varchar(100) ,
 `jbs` varchar(255) ,
 `sfsbt` int(10) ,
 `sbtxh` int(10) ,
 `xslb` int(10) ,
 `gb` varchar(100) ,
 `mz` varchar(100) ,
 `jg` varchar(100) ,
 `zzmm` int(10) ,
 `zslb` int(10) ,
 `lydq` varchar(200) ,
 `hkszd` varchar(200) ,
 `xjzd` varchar(200) ,
 `hkxz` varchar(100) ,
 `sfbshk` int(10) ,
 `status` int(10) ,
 `del_flag` int(10) ,
 `remarks` varchar(255) ,
 `create_by` varchar(50) ,
 `create_date` bigint(20) ,
 `update_by` varchar(50) ,
 `update_date` bigint(20) ,
 `className` varchar(255) ,
 `indexName` varchar(266) ,
 `sectionName` varchar(255) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `xqId` varchar(50) ,
 `schoolTypeName` varchar(100) ,
 `sectionShortName` varchar(255) ,
 `schoolName` varchar(100) ,
 `schoolEname` varchar(255) 
)*/;

/*Table structure for table `v_student_classinfo` */

DROP TABLE IF EXISTS `v_student_classinfo`;

/*!50001 DROP VIEW IF EXISTS `v_student_classinfo` */;
/*!50001 DROP TABLE IF EXISTS `v_student_classinfo` */;

/*!50001 CREATE TABLE  `v_student_classinfo`(
 `id` varchar(50) ,
 `school_id` varchar(50) ,
 `class_id` varchar(50) ,
 `student_account` varchar(100) ,
 `xsxm` varchar(64) ,
 `xmpy` varchar(64) ,
 `xszp` varchar(64) ,
 `phone` varchar(100) ,
 `csrq` bigint(20) ,
 `rxrq` bigint(20) ,
 `xsxb` int(10) ,
 `xssg` varchar(100) ,
 `xh` varchar(100) ,
 `xjh` varchar(100) ,
 `qgxjh` varchar(100) ,
 `jyid` varchar(100) ,
 `syd` varchar(100) ,
 `yxzjlx` int(10) ,
 `yxzjh` varchar(100) ,
 `jbs` varchar(255) ,
 `sfsbt` int(10) ,
 `sbtxh` int(10) ,
 `xslb` int(10) ,
 `gb` varchar(100) ,
 `mz` varchar(100) ,
 `jg` varchar(100) ,
 `zzmm` int(10) ,
 `zslb` int(10) ,
 `lydq` varchar(200) ,
 `hkszd` varchar(200) ,
 `xjzd` varchar(200) ,
 `hkxz` varchar(100) ,
 `sfbshk` int(10) ,
 `status` int(10) ,
 `del_flag` int(10) ,
 `remarks` varchar(255) ,
 `create_by` varchar(50) ,
 `create_date` bigint(20) ,
 `update_by` varchar(50) ,
 `update_date` bigint(20) ,
 `className` varchar(255) ,
 `indexName` varchar(266) ,
 `sectionName` varchar(255) ,
 `xd` varchar(50) ,
 `nj` int(10) ,
 `sectionYear` int(10) ,
 `xqId` varchar(50) ,
 `schoolTypeName` varchar(100) ,
 `sectionShortName` varchar(255) 
)*/;

/*Table structure for table `v_teach_class_room` */

DROP TABLE IF EXISTS `v_teach_class_room`;

/*!50001 DROP VIEW IF EXISTS `v_teach_class_room` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_class_room` */;

/*!50001 CREATE TABLE  `v_teach_class_room`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `classroom_type_id` varchar(255) ,
 `classroom_type_name` varchar(255) ,
 `area_id` varchar(255) ,
 `area` varchar(255) ,
 `no` varchar(255) ,
 `layer` varchar(255) ,
 `number` varchar(255) ,
 `actnum` int(11) ,
 `testnum` int(11) ,
 `delFlag` int(11) ,
 `rnrs` int(11) ,
 `sfxk` int(11) ,
 `schoolId` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_course` */

DROP TABLE IF EXISTS `v_teach_course`;

/*!50001 DROP VIEW IF EXISTS `v_teach_course` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_course` */;

/*!50001 CREATE TABLE  `v_teach_course`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `school_id` varchar(255) ,
 `name` varchar(255) ,
 `cycle_id` varchar(255) ,
 `short_name` varchar(255) ,
 `course_standard_id` varchar(255) ,
 `room_type` varchar(255) ,
 `score` varchar(255) ,
 `pass_score` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_course_type` */

DROP TABLE IF EXISTS `v_teach_course_type`;

/*!50001 DROP VIEW IF EXISTS `v_teach_course_type` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_course_type` */;

/*!50001 CREATE TABLE  `v_teach_course_type`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `name` varchar(255) ,
 `school_id` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_cycle` */

DROP TABLE IF EXISTS `v_teach_cycle`;

/*!50001 DROP VIEW IF EXISTS `v_teach_cycle` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_cycle` */;

/*!50001 CREATE TABLE  `v_teach_cycle`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `name` varchar(255) ,
 `startWeek` bigint(255) ,
 `endWeek` bigint(255) ,
 `xn` varchar(255) ,
 `xq` int(11) ,
 `schoolId` varchar(255) ,
 `term_begin_time` bigint(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_daily_hour` */

DROP TABLE IF EXISTS `v_teach_daily_hour`;

/*!50001 DROP VIEW IF EXISTS `v_teach_daily_hour` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_daily_hour` */;

/*!50001 CREATE TABLE  `v_teach_daily_hour`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `school_id` varchar(255) ,
 `class_id` varchar(255) ,
 `skts` int(11) ,
 `swks` int(11) ,
 `xwks` int(11) ,
 `kjc` int(11) ,
 `period_id` varchar(255) ,
 `xn` varchar(255) ,
 `xq` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_room_type` */

DROP TABLE IF EXISTS `v_teach_room_type`;

/*!50001 DROP VIEW IF EXISTS `v_teach_room_type` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_room_type` */;

/*!50001 CREATE TABLE  `v_teach_room_type`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `classroom_type_name` varchar(255) ,
 `schoolId` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_standard_course` */

DROP TABLE IF EXISTS `v_teach_standard_course`;

/*!50001 DROP VIEW IF EXISTS `v_teach_standard_course` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_standard_course` */;

/*!50001 CREATE TABLE  `v_teach_standard_course`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `name` varchar(255) ,
 `english_name` varchar(255) ,
 `school_id` varchar(255) ,
 `sys` int(11) ,
 `is_dictionary` int(11) ,
 `course_code_id` varchar(255) ,
 `course_code_name` varchar(255) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_table` */

DROP TABLE IF EXISTS `v_teach_table`;

/*!50001 DROP VIEW IF EXISTS `v_teach_table` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_table` */;

/*!50001 CREATE TABLE  `v_teach_table`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `class_id` varchar(255) ,
 `course_id` varchar(255) ,
 `teacher_id` varchar(255) ,
 `table_id` varchar(255) ,
 `class_room_id` varchar(255) ,
 `weekend` int(11) ,
 `school_id` varchar(255) ,
 `xd` varchar(50) ,
 `class_name` varchar(255) ,
 `class_short` varchar(255) ,
 `xn` varchar(50) ,
 `xq` int(5) ,
 `week_start` bigint(100) ,
 `kcmc` varchar(100) ,
 `cycle_id` varchar(50) ,
 `course_short` varchar(100) ,
 `start_time` bigint(50) ,
 `end_time` bigint(50) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teach_teacher_course_manage` */

DROP TABLE IF EXISTS `v_teach_teacher_course_manage`;

/*!50001 DROP VIEW IF EXISTS `v_teach_teacher_course_manage` */;
/*!50001 DROP TABLE IF EXISTS `v_teach_teacher_course_manage` */;

/*!50001 CREATE TABLE  `v_teach_teacher_course_manage`(
 `id` int(11) ,
 `sync_id` varchar(255) ,
 `teacherId` varchar(255) ,
 `courseId` varchar(255) ,
 `xn` varchar(50) ,
 `xq` int(5) ,
 `courseTime` int(11) ,
 `classType` varchar(50) ,
 `periodId` varchar(50) ,
 `classId` varchar(255) ,
 `schoolId` varchar(50) ,
 `event` varchar(255) ,
 `source` varchar(255) ,
 `sync_date` datetime ,
 `sync_del_flag` varchar(255) 
)*/;

/*Table structure for table `v_teacher_school` */

DROP TABLE IF EXISTS `v_teacher_school`;

/*!50001 DROP VIEW IF EXISTS `v_teacher_school` */;
/*!50001 DROP TABLE IF EXISTS `v_teacher_school` */;

/*!50001 CREATE TABLE  `v_teacher_school`(
 `id` varchar(50) ,
 `schoolId` varchar(50) ,
 `departmentId` varchar(50) ,
 `departName` varchar(255) ,
 `name` varchar(255) ,
 `quanPin` varchar(255) ,
 `gender` int(11) ,
 `identity` varchar(50) ,
 `account` varchar(100) ,
 `isManage` int(10) ,
 `roleId` varchar(50) ,
 `titleId` varchar(50) ,
 `titleName` varchar(64) ,
 `no` varchar(100) ,
 `phone` varchar(20) ,
 `mobile` varchar(20) ,
 `email` varchar(255) ,
 `startWork` bigint(20) ,
 `headUrl` varchar(400) ,
 `createBy` varchar(50) ,
 `createDate` bigint(20) ,
 `updateBy` varchar(50) ,
 `updateDate` bigint(20) ,
 `remarks` varchar(255) ,
 `highTime` bigint(20) ,
 `highJob` varchar(100) ,
 `zc` varchar(50) ,
 `pzxx` varchar(100) ,
 `address` varchar(200) ,
 `ggjsjb` varchar(10) ,
 `htkssj` bigint(20) ,
 `htjssj` bigint(20) ,
 `cym` varchar(100) ,
 `jtyb` varchar(10) ,
 `sfzrjs` varchar(10) ,
 `salary` varchar(50) ,
 `jg` varchar(20) ,
 `zzmm` varchar(20) ,
 `cjgzsj` bigint(20) ,
 `ysbysj` bigint(20) ,
 `rjxk` varchar(20) ,
 `sf` varchar(20) ,
 `wysp` varchar(20) ,
 `zgxz` varchar(20) ,
 `xwsl` varchar(10) ,
 `rjxkjb` varchar(10) ,
 `xq` varchar(20) ,
 `gwflf` varchar(20) ,
 `zgxl` varchar(20) ,
 `zgbyxx` varchar(20) ,
 `yzy` varchar(20) ,
 `pzsj` bigint(20) ,
 `lwxsj` bigint(20) ,
 `zzdh` varchar(20) ,
 `gzgw` varchar(20) ,
 `bgsdh` varchar(20) ,
 `sfhq` varchar(10) ,
 `sfbzr` varchar(10) ,
 `wyyz` varchar(20) ,
 `yxz` varchar(10) ,
 `zgxw` varchar(20) ,
 `zyjsgwfl` varchar(20) ,
 `szjb` varchar(10) ,
 `gzgwf` varchar(20) ,
 `delFlag` int(10) ,
 `schoolParentId` varchar(50) ,
 `schoolName` varchar(100) ,
 `ename` varchar(255) ,
 `sort` int(10) ,
 `xz` varchar(255) ,
 `code` varchar(100) ,
 `type` int(10) ,
 `grade` int(10) ,
 `logo` varchar(255) ,
 `bg_picture` varchar(255) ,
 `homeText` varchar(255) ,
 `bottomText` varchar(255) ,
 `schoolAddress` varchar(255) ,
 `mId` varchar(50) ,
 `master` varchar(100) ,
 `zipCode` varchar(100) ,
 `patriarchRule` int(10) ,
 `studentRule` int(10) ,
 `teacherRule` int(10) ,
 `shortFlag` varchar(100) ,
 `deployUrl` varchar(255) ,
 `userable` varchar(64) ,
 `primaryPersion` varchar(64) ,
 `deputyPersion` varchar(64) 
)*/;

/*Table structure for table `v_title_school` */

DROP TABLE IF EXISTS `v_title_school`;

/*!50001 DROP VIEW IF EXISTS `v_title_school` */;
/*!50001 DROP TABLE IF EXISTS `v_title_school` */;

/*!50001 CREATE TABLE  `v_title_school`(
 `titleId` varchar(50) ,
 `titlleName` varchar(64) ,
 `titleSort` varchar(64) ,
 `schoolId` varchar(50) ,
 `schoolPar` varchar(50) ,
 `schoolName` varchar(100) ,
 `ename` varchar(255) ,
 `sort` int(10) ,
 `xz` varchar(255) ,
 `code` varchar(100) ,
 `type` int(10) ,
 `grade` int(10) ,
 `logo` varchar(255) ,
 `bg_picture` varchar(255) ,
 `home_text` varchar(255) ,
 `bottom_text` varchar(255) ,
 `address` varchar(255) ,
 `m_id` varchar(50) ,
 `master` varchar(100) ,
 `zip_code` varchar(100) ,
 `phone` varchar(200) ,
 `fax` varchar(200) ,
 `email` varchar(200) ,
 `patriarch_rule` int(10) ,
 `student_rule` int(10) ,
 `teacher_rule` int(10) ,
 `short_flag` varchar(100) ,
 `deploy_url` varchar(255) ,
 `userable` varchar(64) ,
 `primary_persion` varchar(64) ,
 `deputy_persion` varchar(64) ,
 `create_by` varchar(50) ,
 `create_date` bigint(20) ,
 `update_by` varchar(50) ,
 `update_date` bigint(20) ,
 `remarks` varchar(255) ,
 `del_flag` int(10) 
)*/;

/*View structure for view v_class_xd_xq */

/*!50001 DROP TABLE IF EXISTS `v_class_xd_xq` */;
/*!50001 DROP VIEW IF EXISTS `v_class_xd_xq` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_class_xd_xq` AS (select `c`.`id` AS `classId`,`c`.`school_id` AS `schoolId`,`c`.`name` AS `className`,`c`.`short_name` AS `classShortName`,`c`.`xd` AS `xd`,`c`.`nj` AS `nj`,`c`.`bh` AS `bh`,`c`.`bjlx` AS `bjlx`,`c`.`xq` AS `xq`,`c`.`rxnd` AS `rxnd`,`c`.`master_id` AS `master_id`,`c`.`master_name` AS `master_name`,`q`.`name` AS `schoolName`,`q`.`email` AS `email`,`q`.`phone` AS `schoolPhone`,`s`.`name` AS `sectionName`,`s`.`short_name` AS `sectiongShortName`,`s`.`limit_age` AS `limit_age`,`s`.`section_year` AS `section_year` from ((`org_grade_class` `c` left join `org_class_section` `s` on(((`c`.`xd` = `s`.`id`) and (`c`.`school_id` = `s`.`school_id`) and (`c`.`del_flag` = 0)))) left join `org_school_type` `q` on(((`c`.`xq` = `q`.`id`) and (`c`.`school_id` = `q`.`school_id`))))) */;

/*View structure for view v_depart_school */

/*!50001 DROP TABLE IF EXISTS `v_depart_school` */;
/*!50001 DROP VIEW IF EXISTS `v_depart_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_depart_school` AS (select `d`.`id` AS `departId`,`d`.`parent_id` AS `departPar`,`d`.`no` AS `no`,`d`.`name` AS `departName`,`d`.`master_id` AS `master_id`,`d`.`master_name` AS `master_name`,`s`.`id` AS `schoolId`,`s`.`parent_id` AS `schoolPar`,`s`.`name` AS `schoolName`,`s`.`ename` AS `ename`,`s`.`sort` AS `sort`,`s`.`xz` AS `xz`,`s`.`code` AS `code`,`s`.`type` AS `type`,`s`.`grade` AS `grade`,`s`.`logo` AS `logo`,`s`.`bg_picture` AS `bg_picture`,`s`.`home_text` AS `home_text`,`s`.`bottom_text` AS `bottom_text`,`s`.`address` AS `address`,`s`.`m_id` AS `m_id`,`s`.`master` AS `master`,`s`.`zip_code` AS `zip_code`,`s`.`phone` AS `phone`,`s`.`fax` AS `fax`,`s`.`email` AS `email`,`s`.`patriarch_rule` AS `patriarch_rule`,`s`.`student_rule` AS `student_rule`,`s`.`teacher_rule` AS `teacher_rule`,`s`.`short_flag` AS `short_flag`,`s`.`deploy_url` AS `deploy_url`,`s`.`userable` AS `userable`,`s`.`primary_persion` AS `primary_persion`,`s`.`deputy_persion` AS `deputy_persion`,`s`.`create_by` AS `create_by`,`s`.`create_date` AS `create_date`,`s`.`update_by` AS `update_by`,`s`.`update_date` AS `update_date`,`s`.`remarks` AS `remarks`,`s`.`del_flag` AS `del_flag` from (`org_department` `d` left join `org_school` `s` on(((`d`.`school_id` = `s`.`id`) and (`s`.`del_flag` = 0))))) */;

/*View structure for view v_partiarch_student */

/*!50001 DROP TABLE IF EXISTS `v_partiarch_student` */;
/*!50001 DROP VIEW IF EXISTS `v_partiarch_student` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_partiarch_student` AS (select `p`.`id` AS `parentId`,`p`.`name` AS `parentName`,`p`.`account` AS `parAccount`,`p`.`work` AS `work`,`p`.`work_at` AS `work_at`,`p`.`phone` AS `parentPhone`,`p`.`gender` AS `parentGender`,`p`.`sfjhr` AS `sfjhr`,`p`.`sfyqsh` AS `sfyqsh`,`p`.`patriarch_flag` AS `patriarch_flag`,`p`.`del_flag` AS `delFlag`,`s`.`id` AS `studentId`,`s`.`school_id` AS `schoolId`,`s`.`class_id` AS `classId`,`s`.`account` AS `studentAccount`,`s`.`xsxm` AS `xsxm`,`s`.`xmpy` AS `xmpy`,`s`.`xszp` AS `xszp`,`s`.`phone` AS `studentPhone`,`s`.`csrq` AS `csrq`,`s`.`rxrq` AS `rxrq`,`s`.`xsxb` AS `xsxb`,`s`.`xssg` AS `xssg`,`s`.`xd` AS `xd`,`s`.`nj` AS `nj`,`s`.`xh` AS `xh`,`s`.`xjh` AS `xjh`,`s`.`qgxjh` AS `qgxjh`,`s`.`jyid` AS `jyid`,`s`.`syd` AS `syd`,`s`.`yxzjlx` AS `yxzjlx`,`s`.`yxzjh` AS `yxzjh`,`s`.`jbs` AS `jbs`,`s`.`sfsbt` AS `sfsbt`,`s`.`sbtxh` AS `sbtxh`,`s`.`xslb` AS `xslb`,`s`.`gb` AS `gb`,`s`.`mz` AS `mz`,`s`.`jg` AS `jg`,`s`.`zzmm` AS `zzmm`,`s`.`zslb` AS `zslb`,`s`.`lydq` AS `lydq`,`s`.`hkszd` AS `hkszd`,`s`.`xjzd` AS `xjzd`,`s`.`hkxz` AS `hkxz`,`s`.`sfbshk` AS `sfbshk`,`s`.`status` AS `status` from (`user_patriarch` `p` left join `user_student` `s` on(((`p`.`student_id` = `s`.`id`) and (`p`.`student_school_id` = `s`.`school_id`) and (`p`.`del_flag` = 0))))) */;

/*View structure for view v_partiarch_student_class_info */

/*!50001 DROP TABLE IF EXISTS `v_partiarch_student_class_info` */;
/*!50001 DROP VIEW IF EXISTS `v_partiarch_student_class_info` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_partiarch_student_class_info` AS (select `v1`.`parentId` AS `parentId`,`v1`.`parentName` AS `parentName`,`v1`.`parAccount` AS `parAccount`,`v1`.`work` AS `work`,`v1`.`work_at` AS `work_at`,`v1`.`parentPhone` AS `parentPhone`,`v1`.`parentGender` AS `parentGender`,`v1`.`sfjhr` AS `sfjhr`,`v1`.`sfyqsh` AS `sfyqsh`,`v1`.`patriarch_flag` AS `patriarch_flag`,`v1`.`studentId` AS `studentId`,`v1`.`schoolId` AS `schoolId`,`s`.`name` AS `school`,`v1`.`classId` AS `classId`,`v1`.`studentAccount` AS `studentAccount`,`v1`.`xsxm` AS `xsxm`,`v1`.`xmpy` AS `xmpy`,`v1`.`xszp` AS `xszp`,`v1`.`studentPhone` AS `studengPhone`,`v1`.`csrq` AS `csrq`,`v1`.`rxrq` AS `rxrq`,`v1`.`xsxb` AS `xsxb`,`v1`.`xssg` AS `xssg`,`v1`.`xh` AS `xh`,`v1`.`xjh` AS `xjh`,`v1`.`qgxjh` AS `qgxjh`,`v1`.`jyid` AS `jyid`,`v1`.`syd` AS `syd`,`v1`.`yxzjlx` AS `yxzjlx`,`v1`.`yxzjh` AS `yxzjh`,`v1`.`jbs` AS `jbs`,`v1`.`sfsbt` AS `sfsbt`,`v1`.`sbtxh` AS `sbtxh`,`v1`.`xslb` AS `xslb`,`v1`.`gb` AS `gb`,`v1`.`mz` AS `mz`,`v1`.`jg` AS `jg`,`v1`.`zzmm` AS `zzmm`,`v1`.`zslb` AS `zslb`,`v1`.`lydq` AS `lydq`,`v1`.`hkszd` AS `hkszd`,`v1`.`xjzd` AS `xjzd`,`v1`.`hkxz` AS `hkxz`,`v1`.`sfbshk` AS `sfbshk`,`v1`.`status` AS `status`,`v1`.`delFlag` AS `delFlag`,`v2`.`className` AS `className`,`v2`.`classShortName` AS `classShortName`,`v2`.`xd` AS `xd`,`v2`.`nj` AS `nj`,`v2`.`bh` AS `bh`,`v2`.`bjlx` AS `bjlx`,`v2`.`xq` AS `xq`,`v2`.`rxnd` AS `rxnd`,`v2`.`master_id` AS `master_id`,`v2`.`master_name` AS `master_name`,`v2`.`schoolName` AS `schoolName`,`v2`.`email` AS `email`,`v2`.`schoolPhone` AS `schoolPhone`,`v2`.`sectionName` AS `sectionName`,`v2`.`sectiongShortName` AS `sectiongShortName`,`v2`.`limit_age` AS `limit_age`,`v2`.`section_year` AS `section_year` from ((`v_partiarch_student` `v1` left join `v_class_xd_xq` `v2` on(((`v1`.`classId` = `v2`.`classId`) and (`v1`.`schoolId` = `v2`.`schoolId`)))) left join `org_school` `s` on((`v1`.`schoolId` = `s`.`id`)))) */;

/*View structure for view v_ref_teacher_class_deputy */

/*!50001 DROP TABLE IF EXISTS `v_ref_teacher_class_deputy` */;
/*!50001 DROP VIEW IF EXISTS `v_ref_teacher_class_deputy` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_ref_teacher_class_deputy` AS (select `c`.`name` AS `cname`,`c`.`id` AS `cid`,`c`.`bh` AS `bh`,group_concat(`t`.`id` separator ',') AS `GROUP_CONCAT(t.id)`,group_concat(`t`.`name` separator ',') AS `tname`,`ref`.`class_id` AS `class_id`,`ref`.`teacher_id` AS `teacher_id`,`ref`.`cycle_id` AS `cycleId`,`cs`.`id` AS `csId`,`cs`.`name` AS `csname` from ((((`ref_teacher_class` `ref` left join `user_teacher` `t` on((`t`.`id` = `ref`.`teacher_id`))) left join `org_grade_class` `c` on((`c`.`id` = `ref`.`class_id`))) left join `org_class_section` `cs` on((`cs`.`id` = `c`.`xd`))) left join `teach_cycle` `cycle` on((`ref`.`cycle_id` = `cycle`.`id`))) where (`ref`.`type` = 2) group by `ref`.`class_id`,`ref`.`cycle_id` order by `c`.`bh`) */;

/*View structure for view v_ref_teacher_class_master */

/*!50001 DROP TABLE IF EXISTS `v_ref_teacher_class_master` */;
/*!50001 DROP VIEW IF EXISTS `v_ref_teacher_class_master` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_ref_teacher_class_master` AS (select `c`.`name` AS `cname`,`c`.`id` AS `cid`,`c`.`bh` AS `bh`,`c`.`id` AS `id`,`t`.`name` AS `tname`,`ref`.`class_id` AS `class_id`,`ref`.`teacher_id` AS `teacher_id`,`ref`.`cycle_id` AS `cycleId`,`cs`.`id` AS `csId`,`cs`.`name` AS `csname` from ((((`ref_teacher_class` `ref` left join `user_teacher` `t` on((`t`.`id` = `ref`.`teacher_id`))) left join `org_grade_class` `c` on((`ref`.`class_id` = `c`.`id`))) left join `org_class_section` `cs` on((`cs`.`id` = `c`.`xd`))) left join `teach_cycle` `cycle` on((`cycle`.`id` = `ref`.`cycle_id`))) where (`ref`.`type` = 1) order by `c`.`bh`) */;

/*View structure for view v_section_class */

/*!50001 DROP TABLE IF EXISTS `v_section_class` */;
/*!50001 DROP VIEW IF EXISTS `v_section_class` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_section_class` AS select `c`.`school_id` AS `schoolId`,concat(`s`.`name`,`c`.`nj`) AS `indexName`,`c`.`id` AS `classId`,`c`.`name` AS `className`,`c`.`xd` AS `xd`,`c`.`nj` AS `nj`,`c`.`xq` AS `xq`,`s`.`name` AS `name`,`s`.`section_year` AS `sectionYear`,`s`.`short_name` AS `sectionShortName` from (`org_grade_class` `c` left join `org_class_section` `s` on((`c`.`xd` = `s`.`id`))) where (`c`.`school_id` = `s`.`school_id`) order by `s`.`section_year` desc,`s`.`name`,`c`.`nj` */;

/*View structure for view v_select_teach_table */

/*!50001 DROP TABLE IF EXISTS `v_select_teach_table` */;
/*!50001 DROP VIEW IF EXISTS `v_select_teach_table` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_select_teach_table` AS (select `t_table`.`id` AS `id`,`t_table`.`id` AS `sync_id`,`t_table`.`class_id` AS `class_id`,`t_table`.`course_id` AS `course_id`,`t_table`.`teacher_id` AS `teacher_id`,`t_table`.`table_id` AS `table_id`,`t_table`.`class_room_id` AS `class_room_id`,`t_table`.`weekend` AS `weekend`,`t_table`.`school_id` AS `school_id`,`section`.`id` AS `xd`,`class`.`name` AS `class_name`,`class`.`short_name` AS `class_short`,`cycle`.`cycle_year` AS `xn`,`cycle`.`cycle_semester` AS `xq`,`cycle`.`term_begin_time` AS `week_start`,`course`.`name` AS `kcmc`,`course`.`cycle_id` AS `cycle_id`,`course`.`short_name` AS `course_short`,`node`.`start_time` AS `start_time`,`node`.`end_time` AS `end_time`,cast(substring_index(`t_table`.`table_id`,',',-(1)) as signed) AS `node`,cast(substring_index(`t_table`.`table_id`,',',1) as signed) AS `weekofday` from ((((((((`teach_table` `t_table` left join `org_grade_class` `class` on((`class`.`id` = `t_table`.`class_id`))) left join `org_class_section` `section` on((`section`.`id` = `class`.`xd`))) left join `user_teacher` `teacher` on((`t_table`.`teacher_id` = `teacher`.`id`))) left join `teach_course` `course` on((`t_table`.`course_id` = `course`.`id`))) left join `teach_cycle` `cycle` on((`course`.`cycle_id` = `cycle`.`id`))) left join `teach_course_node_init` `init` on(((`cycle`.`id` = `init`.`cycle_id`) and (`t_table`.`weekend` >= `init`.`start_week`) and (`t_table`.`weekend` <= `init`.`end_week`)))) left join `teach_course_node` `node` on(((`node`.`course_node_init_id` = `init`.`id`) and (right(`t_table`.`table_id`,1) = `node`.`node`)))) left join `teach_class_room` `room` on((`room`.`id` = `t_table`.`class_room_id`)))) */;

/*View structure for view v_student_class_school */

/*!50001 DROP TABLE IF EXISTS `v_student_class_school` */;
/*!50001 DROP VIEW IF EXISTS `v_student_class_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_student_class_school` AS (select `v`.`id` AS `id`,`v`.`school_id` AS `school_id`,`v`.`class_id` AS `class_id`,`v`.`student_account` AS `student_account`,`v`.`xsxm` AS `xsxm`,`v`.`xmpy` AS `xmpy`,`v`.`xszp` AS `xszp`,`v`.`phone` AS `phone`,`v`.`csrq` AS `csrq`,`v`.`rxrq` AS `rxrq`,`v`.`xsxb` AS `xsxb`,`v`.`xssg` AS `xssg`,`v`.`xh` AS `xh`,`v`.`xjh` AS `xjh`,`v`.`qgxjh` AS `qgxjh`,`v`.`jyid` AS `jyid`,`v`.`syd` AS `syd`,`v`.`yxzjlx` AS `yxzjlx`,`v`.`yxzjh` AS `yxzjh`,`v`.`jbs` AS `jbs`,`v`.`sfsbt` AS `sfsbt`,`v`.`sbtxh` AS `sbtxh`,`v`.`xslb` AS `xslb`,`v`.`gb` AS `gb`,`v`.`mz` AS `mz`,`v`.`jg` AS `jg`,`v`.`zzmm` AS `zzmm`,`v`.`zslb` AS `zslb`,`v`.`lydq` AS `lydq`,`v`.`hkszd` AS `hkszd`,`v`.`xjzd` AS `xjzd`,`v`.`hkxz` AS `hkxz`,`v`.`sfbshk` AS `sfbshk`,`v`.`status` AS `status`,`v`.`del_flag` AS `del_flag`,`v`.`remarks` AS `remarks`,`v`.`create_by` AS `create_by`,`v`.`create_date` AS `create_date`,`v`.`update_by` AS `update_by`,`v`.`update_date` AS `update_date`,`v`.`className` AS `className`,`v`.`indexName` AS `indexName`,`v`.`sectionName` AS `sectionName`,`v`.`xd` AS `xd`,`v`.`nj` AS `nj`,`v`.`xqId` AS `xqId`,`v`.`schoolTypeName` AS `schoolTypeName`,`v`.`sectionShortName` AS `sectionShortName`,`o`.`name` AS `schoolName`,`o`.`ename` AS `schoolEname` from (`v_student_classinfo` `v` left join `org_school` `o` on((`v`.`school_id` = `o`.`id`)))) */;

/*View structure for view v_student_classinfo */

/*!50001 DROP TABLE IF EXISTS `v_student_classinfo` */;
/*!50001 DROP VIEW IF EXISTS `v_student_classinfo` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_student_classinfo` AS (select `stu`.`id` AS `id`,`stu`.`school_id` AS `school_id`,`stu`.`class_id` AS `class_id`,`stu`.`account` AS `student_account`,`stu`.`xsxm` AS `xsxm`,`stu`.`xmpy` AS `xmpy`,`stu`.`xszp` AS `xszp`,`stu`.`phone` AS `phone`,`stu`.`csrq` AS `csrq`,`stu`.`rxrq` AS `rxrq`,`stu`.`xsxb` AS `xsxb`,`stu`.`xssg` AS `xssg`,`stu`.`xh` AS `xh`,`stu`.`xjh` AS `xjh`,`stu`.`qgxjh` AS `qgxjh`,`stu`.`jyid` AS `jyid`,`stu`.`syd` AS `syd`,`stu`.`yxzjlx` AS `yxzjlx`,`stu`.`yxzjh` AS `yxzjh`,`stu`.`jbs` AS `jbs`,`stu`.`sfsbt` AS `sfsbt`,`stu`.`sbtxh` AS `sbtxh`,`stu`.`xslb` AS `xslb`,`stu`.`gb` AS `gb`,`stu`.`mz` AS `mz`,`stu`.`jg` AS `jg`,`stu`.`zzmm` AS `zzmm`,`stu`.`zslb` AS `zslb`,`stu`.`lydq` AS `lydq`,`stu`.`hkszd` AS `hkszd`,`stu`.`xjzd` AS `xjzd`,`stu`.`hkxz` AS `hkxz`,`stu`.`sfbshk` AS `sfbshk`,`stu`.`status` AS `status`,`stu`.`del_flag` AS `del_flag`,`stu`.`remarks` AS `remarks`,`stu`.`create_by` AS `create_by`,`stu`.`create_date` AS `create_date`,`stu`.`update_by` AS `update_by`,`stu`.`update_date` AS `update_date`,`cla`.`className` AS `className`,`cla`.`indexName` AS `indexName`,`cla`.`name` AS `sectionName`,`cla`.`xd` AS `xd`,`cla`.`nj` AS `nj`,`cla`.`sectionYear` AS `sectionYear`,`t`.`id` AS `xqId`,`t`.`name` AS `schoolTypeName`,`cla`.`sectionShortName` AS `sectionShortName` from ((`user_student` `stu` join `v_section_class` `cla`) left join `org_school_type` `t` on((`cla`.`xq` = `t`.`id`))) where ((`stu`.`class_id` = `cla`.`classId`) and (`stu`.`school_id` = `cla`.`schoolId`))) */;

/*View structure for view v_teach_class_room */

/*!50001 DROP TABLE IF EXISTS `v_teach_class_room` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_class_room` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_class_room` AS (select `sync_teach_class_room`.`id` AS `id`,`sync_teach_class_room`.`sync_id` AS `sync_id`,`sync_teach_class_room`.`room_type` AS `classroom_type_id`,`sync_teach_class_room`.`room_type_name` AS `classroom_type_name`,`sync_teach_class_room`.`school_type` AS `area_id`,`sync_teach_class_room`.`school_type_name` AS `area`,`sync_teach_class_room`.`teach_building` AS `no`,`sync_teach_class_room`.`floor` AS `layer`,`sync_teach_class_room`.`room_num` AS `number`,`sync_teach_class_room`.`available_seat` AS `actnum`,`sync_teach_class_room`.`exam_seat` AS `testnum`,`sync_teach_class_room`.`del_flag` AS `delFlag`,`sync_teach_class_room`.`count` AS `rnrs`,`sync_teach_class_room`.`course_select` AS `sfxk`,`sync_teach_class_room`.`school_id` AS `schoolId`,`sync_teach_class_room`.`event` AS `event`,`sync_teach_class_room`.`source` AS `source`,`sync_teach_class_room`.`sync_date` AS `sync_date`,`sync_teach_class_room`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_class_room`) */;

/*View structure for view v_teach_course */

/*!50001 DROP TABLE IF EXISTS `v_teach_course` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_course` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_course` AS (select `sync_teach_course`.`id` AS `id`,`sync_teach_course`.`sync_id` AS `sync_id`,`sync_teach_course`.`school_id` AS `school_id`,`sync_teach_course`.`name` AS `name`,`sync_teach_course`.`cycle_id` AS `cycle_id`,`sync_teach_course`.`short_name` AS `short_name`,`sync_teach_course`.`course_type` AS `course_standard_id`,`sync_teach_course`.`room_type` AS `room_type`,`sync_teach_course`.`score` AS `score`,`sync_teach_course`.`pass_score` AS `pass_score`,`sync_teach_course`.`event` AS `event`,`sync_teach_course`.`source` AS `source`,`sync_teach_course`.`sync_date` AS `sync_date`,`sync_teach_course`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_course`) */;

/*View structure for view v_teach_course_type */

/*!50001 DROP TABLE IF EXISTS `v_teach_course_type` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_course_type` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_course_type` AS (select `sync_teach_course_type`.`id` AS `id`,`sync_teach_course_type`.`sync_id` AS `sync_id`,`sync_teach_course_type`.`name` AS `name`,`sync_teach_course_type`.`school_id` AS `school_id`,`sync_teach_course_type`.`event` AS `event`,`sync_teach_course_type`.`source` AS `source`,`sync_teach_course_type`.`sync_date` AS `sync_date`,`sync_teach_course_type`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_course_type`) */;

/*View structure for view v_teach_cycle */

/*!50001 DROP TABLE IF EXISTS `v_teach_cycle` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_cycle` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_cycle` AS (select `sync_teach_cycle`.`id` AS `id`,`sync_teach_cycle`.`sync_id` AS `sync_id`,`sync_teach_cycle`.`cycle_name` AS `name`,`sync_teach_cycle`.`begin_date` AS `startWeek`,`sync_teach_cycle`.`end_date` AS `endWeek`,`sync_teach_cycle`.`cycle_year` AS `xn`,`sync_teach_cycle`.`cycle_semester` AS `xq`,`sync_teach_cycle`.`school_id` AS `schoolId`,`sync_teach_cycle`.`term_begin_time` AS `term_begin_time`,`sync_teach_cycle`.`event` AS `event`,`sync_teach_cycle`.`source` AS `source`,`sync_teach_cycle`.`sync_date` AS `sync_date`,`sync_teach_cycle`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_cycle`) */;

/*View structure for view v_teach_daily_hour */

/*!50001 DROP TABLE IF EXISTS `v_teach_daily_hour` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_daily_hour` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_daily_hour` AS (select `sync_teach_daily_hour`.`id` AS `id`,`sync_teach_daily_hour`.`sync_id` AS `sync_id`,`sync_teach_daily_hour`.`school_id` AS `school_id`,`sync_teach_daily_hour`.`grade_class_id` AS `class_id`,`sync_teach_daily_hour`.`skts` AS `skts`,`sync_teach_daily_hour`.`swks` AS `swks`,`sync_teach_daily_hour`.`xwks` AS `xwks`,`sync_teach_daily_hour`.`kjc` AS `kjc`,`sync_teach_daily_hour`.`cycle_id` AS `period_id`,`sync_teach_daily_hour`.`xn` AS `xn`,`sync_teach_daily_hour`.`xq` AS `xq`,`sync_teach_daily_hour`.`event` AS `event`,`sync_teach_daily_hour`.`source` AS `source`,`sync_teach_daily_hour`.`sync_date` AS `sync_date`,`sync_teach_daily_hour`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_daily_hour`) */;

/*View structure for view v_teach_room_type */

/*!50001 DROP TABLE IF EXISTS `v_teach_room_type` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_room_type` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_room_type` AS (select `sync_teach_room_type`.`id` AS `id`,`sync_teach_room_type`.`sync_id` AS `sync_id`,`sync_teach_room_type`.`name` AS `classroom_type_name`,`sync_teach_room_type`.`school_id` AS `schoolId`,`sync_teach_room_type`.`event` AS `event`,`sync_teach_room_type`.`source` AS `source`,`sync_teach_room_type`.`sync_date` AS `sync_date`,`sync_teach_room_type`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_room_type`) */;

/*View structure for view v_teach_standard_course */

/*!50001 DROP TABLE IF EXISTS `v_teach_standard_course` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_standard_course` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_standard_course` AS (select `sync_teach_standard_course`.`id` AS `id`,`sync_teach_standard_course`.`sync_id` AS `sync_id`,`sync_teach_standard_course`.`name` AS `name`,`sync_teach_standard_course`.`english_name` AS `english_name`,`sync_teach_standard_course`.`school_id` AS `school_id`,`sync_teach_standard_course`.`sys` AS `sys`,`sync_teach_standard_course`.`is_dictionary` AS `is_dictionary`,`sync_teach_standard_course`.`course_type_id` AS `course_code_id`,`sync_teach_standard_course`.`course_type_name` AS `course_code_name`,`sync_teach_standard_course`.`event` AS `event`,`sync_teach_standard_course`.`source` AS `source`,`sync_teach_standard_course`.`sync_date` AS `sync_date`,`sync_teach_standard_course`.`sync_del_flag` AS `sync_del_flag` from `sync_teach_standard_course`) */;

/*View structure for view v_teach_table */

/*!50001 DROP TABLE IF EXISTS `v_teach_table` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_table` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_table` AS (select `t_table`.`id` AS `id`,`t_table`.`sync_id` AS `sync_id`,`t_table`.`class_id` AS `class_id`,`t_table`.`course_id` AS `course_id`,`t_table`.`teacher_id` AS `teacher_id`,`t_table`.`table_id` AS `table_id`,`t_table`.`class_room_id` AS `class_room_id`,`t_table`.`weekend` AS `weekend`,`t_table`.`school_id` AS `school_id`,`section`.`id` AS `xd`,`class`.`name` AS `class_name`,`class`.`short_name` AS `class_short`,`cycle`.`cycle_year` AS `xn`,`cycle`.`cycle_semester` AS `xq`,`cycle`.`term_begin_time` AS `week_start`,`course`.`name` AS `kcmc`,`course`.`cycle_id` AS `cycle_id`,`course`.`short_name` AS `course_short`,`node`.`start_time` AS `start_time`,`node`.`end_time` AS `end_time`,`t_table`.`event` AS `event`,`t_table`.`source` AS `source`,`t_table`.`sync_date` AS `sync_date`,`t_table`.`sync_del_flag` AS `sync_del_flag` from ((((((((`sync_teach_table` `t_table` left join `org_grade_class` `class` on((`class`.`id` = `t_table`.`class_id`))) left join `org_class_section` `section` on((`section`.`id` = `class`.`xd`))) left join `user_teacher` `teacher` on((`t_table`.`teacher_id` = `teacher`.`id`))) left join `teach_course` `course` on((`t_table`.`course_id` = `course`.`id`))) left join `teach_cycle` `cycle` on((`course`.`cycle_id` = `cycle`.`id`))) left join `teach_course_node_init` `init` on(((`cycle`.`id` = `init`.`cycle_id`) and (`t_table`.`weekend` >= `init`.`start_week`) and (`t_table`.`weekend` <= `init`.`end_week`)))) left join `teach_course_node` `node` on(((`node`.`course_node_init_id` = `init`.`id`) and (right(`t_table`.`table_id`,1) = `node`.`node`)))) left join `teach_class_room` `room` on((`room`.`id` = `t_table`.`class_room_id`)))) */;

/*View structure for view v_teach_teacher_course_manage */

/*!50001 DROP TABLE IF EXISTS `v_teach_teacher_course_manage` */;
/*!50001 DROP VIEW IF EXISTS `v_teach_teacher_course_manage` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teach_teacher_course_manage` AS (select `s`.`id` AS `id`,`s`.`sync_id` AS `sync_id`,`s`.`teacher_id` AS `teacherId`,`s`.`course_id` AS `courseId`,`cy`.`cycle_year` AS `xn`,`cy`.`cycle_semester` AS `xq`,`s`.`course_hour` AS `courseTime`,`c`.`room_type` AS `classType`,`c`.`cycle_id` AS `periodId`,`s`.`class_id` AS `classId`,`u`.`school_id` AS `schoolId`,`s`.`event` AS `event`,`s`.`source` AS `source`,`s`.`sync_date` AS `sync_date`,`s`.`sync_del_flag` AS `sync_del_flag` from (((`sync_teach_ref_course_class` `s` left join `teach_course` `c` on((`s`.`course_id` = `c`.`id`))) left join `teach_cycle` `cy` on((`c`.`cycle_id` = `cy`.`id`))) left join `user_teacher` `u` on((`s`.`teacher_id` = `u`.`id`)))) */;

/*View structure for view v_teacher_school */

/*!50001 DROP TABLE IF EXISTS `v_teacher_school` */;
/*!50001 DROP VIEW IF EXISTS `v_teacher_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teacher_school` AS (select `u`.`id` AS `id`,`u`.`school_id` AS `schoolId`,`u`.`department_id` AS `departmentId`,`d`.`name` AS `departName`,`u`.`name` AS `name`,`u`.`quan_pin` AS `quanPin`,`u`.`gender` AS `gender`,`u`.`identity` AS `identity`,`u`.`account` AS `account`,`u`.`is_manage` AS `isManage`,`u`.`role_id` AS `roleId`,`u`.`title_id` AS `titleId`,`t`.`mc` AS `titleName`,`u`.`no` AS `no`,`u`.`phone` AS `phone`,`u`.`mobile` AS `mobile`,`u`.`email` AS `email`,`u`.`start_work` AS `startWork`,`u`.`head_url` AS `headUrl`,`u`.`create_by` AS `createBy`,`u`.`create_date` AS `createDate`,`u`.`update_by` AS `updateBy`,`u`.`update_date` AS `updateDate`,`u`.`remarks` AS `remarks`,`u`.`high_time` AS `highTime`,`u`.`high_job` AS `highJob`,`u`.`zc` AS `zc`,`u`.`pzxx` AS `pzxx`,`u`.`address` AS `address`,`u`.`ggjsjb` AS `ggjsjb`,`u`.`htkssj` AS `htkssj`,`u`.`htjssj` AS `htjssj`,`u`.`cym` AS `cym`,`u`.`jtyb` AS `jtyb`,`u`.`sfzrjs` AS `sfzrjs`,`u`.`salary` AS `salary`,`u`.`jg` AS `jg`,`u`.`zzmm` AS `zzmm`,`u`.`cjgzsj` AS `cjgzsj`,`u`.`ysbysj` AS `ysbysj`,`u`.`rjxk` AS `rjxk`,`u`.`sf` AS `sf`,`u`.`wysp` AS `wysp`,`u`.`zgxz` AS `zgxz`,`u`.`xwsl` AS `xwsl`,`u`.`rjxkjb` AS `rjxkjb`,`u`.`xq` AS `xq`,`u`.`gwflf` AS `gwflf`,`u`.`zgxl` AS `zgxl`,`u`.`zgbyxx` AS `zgbyxx`,`u`.`yzy` AS `yzy`,`u`.`pzsj` AS `pzsj`,`u`.`lwxsj` AS `lwxsj`,`u`.`zzdh` AS `zzdh`,`u`.`gzgw` AS `gzgw`,`u`.`bgsdh` AS `bgsdh`,`u`.`sfhq` AS `sfhq`,`u`.`sfbzr` AS `sfbzr`,`u`.`wyyz` AS `wyyz`,`u`.`yxz` AS `yxz`,`u`.`zgxw` AS `zgxw`,`u`.`zyjsgwfl` AS `zyjsgwfl`,`u`.`szjb` AS `szjb`,`u`.`gzgwf` AS `gzgwf`,`u`.`del_flag` AS `delFlag`,`o`.`parent_id` AS `schoolParentId`,`o`.`name` AS `schoolName`,`o`.`ename` AS `ename`,`o`.`sort` AS `sort`,`o`.`xz` AS `xz`,`o`.`code` AS `code`,`o`.`type` AS `type`,`o`.`grade` AS `grade`,`o`.`logo` AS `logo`,`o`.`bg_picture` AS `bg_picture`,`o`.`home_text` AS `homeText`,`o`.`bottom_text` AS `bottomText`,`o`.`address` AS `schoolAddress`,`o`.`m_id` AS `mId`,`o`.`master` AS `master`,`o`.`zip_code` AS `zipCode`,`o`.`patriarch_rule` AS `patriarchRule`,`o`.`student_rule` AS `studentRule`,`o`.`teacher_rule` AS `teacherRule`,`o`.`short_flag` AS `shortFlag`,`o`.`deploy_url` AS `deployUrl`,`o`.`userable` AS `userable`,`o`.`primary_persion` AS `primaryPersion`,`o`.`deputy_persion` AS `deputyPersion` from (((`user_teacher` `u` left join `org_school` `o` on((`u`.`school_id` = `o`.`id`))) left join `org_department` `d` on(((`u`.`department_id` = `d`.`id`) and (`u`.`school_id` = `d`.`school_id`)))) left join `org_title` `t` on(((`u`.`title_id` = `t`.`id`) and (`u`.`school_id` = `t`.`school_id`)))) where (isnull(`u`.`is_manage`) or (`u`.`is_manage` = 0))) */;

/*View structure for view v_title_school */

/*!50001 DROP TABLE IF EXISTS `v_title_school` */;
/*!50001 DROP VIEW IF EXISTS `v_title_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_title_school` AS (select `t`.`id` AS `titleId`,`t`.`mc` AS `titlleName`,`t`.`px` AS `titleSort`,`s`.`id` AS `schoolId`,`s`.`parent_id` AS `schoolPar`,`s`.`name` AS `schoolName`,`s`.`ename` AS `ename`,`s`.`sort` AS `sort`,`s`.`xz` AS `xz`,`s`.`code` AS `code`,`s`.`type` AS `type`,`s`.`grade` AS `grade`,`s`.`logo` AS `logo`,`s`.`bg_picture` AS `bg_picture`,`s`.`home_text` AS `home_text`,`s`.`bottom_text` AS `bottom_text`,`s`.`address` AS `address`,`s`.`m_id` AS `m_id`,`s`.`master` AS `master`,`s`.`zip_code` AS `zip_code`,`s`.`phone` AS `phone`,`s`.`fax` AS `fax`,`s`.`email` AS `email`,`s`.`patriarch_rule` AS `patriarch_rule`,`s`.`student_rule` AS `student_rule`,`s`.`teacher_rule` AS `teacher_rule`,`s`.`short_flag` AS `short_flag`,`s`.`deploy_url` AS `deploy_url`,`s`.`userable` AS `userable`,`s`.`primary_persion` AS `primary_persion`,`s`.`deputy_persion` AS `deputy_persion`,`s`.`create_by` AS `create_by`,`s`.`create_date` AS `create_date`,`s`.`update_by` AS `update_by`,`s`.`update_date` AS `update_date`,`s`.`remarks` AS `remarks`,`s`.`del_flag` AS `del_flag` from (`org_title` `t` left join `org_school` `s` on(((`t`.`school_id` = `s`.`id`) and (`t`.`del_flag` = 0))))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
