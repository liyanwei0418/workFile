/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17 : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `db_versions` */

DROP TABLE IF EXISTS `db_versions`;

CREATE TABLE `db_versions` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `db_versions_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `db_versions` */

LOCK TABLES `db_versions` WRITE;

insert  into `db_versions`(`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,'1.0.0','init data','SQL','V1.0.0__init_data.sql',755089867,'root','2017-03-16 16:15:39',26623,1),(2,'1.0.1','create table org class room','SQL','V1.0.1__create_table_org_class_room.sql',1062386986,'root','2017-03-16 16:19:31',322,1),(3,'1.0.2','add colum school type name','SQL','V1.0.2__add_colum_school_type_name.sql',-1072165638,'root','2017-03-17 10:31:28',567,1);

UNLOCK TABLES;

/*Table structure for table `iim_chat_history` */

DROP TABLE IF EXISTS `iim_chat_history`;

CREATE TABLE `iim_chat_history` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `userid1` varchar(50) DEFAULT NULL,
  `userid2` varchar(50) DEFAULT NULL,
  `msg` varchar(1024) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='聊天历史';

/*Data for the table `iim_chat_history` */

LOCK TABLES `iim_chat_history` WRITE;

UNLOCK TABLES;

/*Table structure for table `iim_mail` */

DROP TABLE IF EXISTS `iim_mail`;

CREATE TABLE `iim_mail` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `overview` varchar(128) DEFAULT NULL COMMENT '内容概要',
  `content` varchar(5096) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件';

/*Data for the table `iim_mail` */

LOCK TABLES `iim_mail` WRITE;

UNLOCK TABLES;

/*Table structure for table `iim_mail_box` */

DROP TABLE IF EXISTS `iim_mail_box`;

CREATE TABLE `iim_mail_box` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `readstatus` int(10) DEFAULT NULL COMMENT '状态 0 未读 1 已读',
  `senderId` varchar(50) DEFAULT NULL COMMENT '发件人',
  `receiverId` varchar(6400) DEFAULT NULL COMMENT '收件人',
  `sendtime` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `mailid` varchar(10) DEFAULT NULL COMMENT '邮件外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收件箱';

/*Data for the table `iim_mail_box` */

LOCK TABLES `iim_mail_box` WRITE;

UNLOCK TABLES;

/*Table structure for table `iim_mail_compose` */

DROP TABLE IF EXISTS `iim_mail_compose`;

CREATE TABLE `iim_mail_compose` (
  `id` varchar(50) NOT NULL,
  `status` int(10) DEFAULT NULL COMMENT '状态 0 草稿 1 已发送',
  `readstatus` int(10) DEFAULT NULL COMMENT '状态 0 未读 1 已读',
  `senderId` varchar(50) DEFAULT NULL COMMENT '发送者',
  `receiverId` varchar(6400) DEFAULT NULL COMMENT '接收者',
  `sendtime` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `mailId` varchar(50) DEFAULT NULL COMMENT '邮件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发件箱 草稿箱';

/*Data for the table `iim_mail_compose` */

LOCK TABLES `iim_mail_compose` WRITE;

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

/*Table structure for table `open_app` */

DROP TABLE IF EXISTS `open_app`;

CREATE TABLE `open_app` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `category` int(255) DEFAULT NULL COMMENT '0：教学教务 1互动空间',
  `target_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_free` int(11) DEFAULT '0' COMMENT '0：免费 1：不免费',
  `app_screenshot` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `app_abstruct` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `app_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `demo_account` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `app_secret` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用推送到云平台的非网络传输密钥',
  `del_flag` int(50) DEFAULT '0',
  `check_status` int(10) DEFAULT '0' COMMENT '审核状态',
  `app_abbreviation` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用简称',
  `create_date` bigint(20) DEFAULT '0' COMMENT '应用创建时间',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `client_Id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '授权登陆的客户端',
  `client_secret` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '授权登陆的密钥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `open_app` */

LOCK TABLES `open_app` WRITE;

UNLOCK TABLES;

/*Table structure for table `org_class_room` */

DROP TABLE IF EXISTS `org_class_room`;

CREATE TABLE `org_class_room` (
  `room_id` varchar(50) NOT NULL COMMENT '教室id',
  `room_name` varchar(255) DEFAULT NULL COMMENT '教室名',
  `room_type` varchar(50) DEFAULT NULL COMMENT '教室类型 关联teach_task_config的 config_id',
  `room_type_name` varchar(100) DEFAULT NULL COMMENT '教室类型名  匹配room_type',
  `school_id` varchar(50) DEFAULT NULL COMMENT '机构id',
  `school_type` varchar(50) DEFAULT NULL COMMENT '所在校区',
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
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_class_room` */

LOCK TABLES `org_class_room` WRITE;

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
  `grade` int(10) DEFAULT NULL COMMENT '机构等级',
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
  `app_id` varchar(50) NOT NULL COMMENT '应用ID',
  `school_id` varchar(50) NOT NULL COMMENT '学校、机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色，应用映射表';

/*Data for the table `ref_app_role` */

LOCK TABLES `ref_app_role` WRITE;

insert  into `ref_app_role`(`role_id`,`app_id`,`school_id`) values ('1','6','0'),('1','6','0'),('23','1','0'),('25','2','0'),('26','5','0');

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

insert  into `ref_role_menu`(`role_id`,`menu_id`,`school_id`) values ('1','0','0'),('1','1','0'),('1','4','0'),('1','5','0'),('1','6','0'),('1','7','0'),('1','8','0'),('1','26','0'),('1','9','0'),('1','10','0'),('1','11','0'),('1','12','0'),('1','13','0'),('1','14','0'),('1','15','0'),('1','16','0'),('1','17','0'),('1','18','0'),('1','19','0'),('1','20','0'),('1','21','0'),('1','22','0'),('1','23','0'),('1','24','0'),('1','25','0'),('3','0','0'),('3','1','0'),('3','4','0'),('3','5','0'),('3','6','0'),('3','7','0'),('3','8','0'),('3','26','0'),('3','13','0'),('3','14','0'),('3','15','0'),('3','16','0'),('3','17','0'),('3','18','0'),('3','19','0'),('3','20','0'),('3','21','0'),('3','22','0'),('3','23','0'),('3','24','0'),('3','25','0'),('2','0','0'),('2','1','0'),('2','4','0'),('2','5','0'),('2','6','0'),('2','7','0'),('2','8','0'),('2','26','0'),('2','13','0'),('2','14','0'),('2','15','0'),('2','16','0'),('2','17','0'),('2','18','0'),('2','19','0'),('2','20','0'),('2','21','0'),('2','22','0'),('2','23','0'),('2','24','0'),('2','25','0'),('7','0','0'),('7','26','0'),('7','13','0'),('7','14','0'),('23','0','0'),('23','1','0'),('23','4','0'),('23','5','0'),('23','6','0'),('23','7','0'),('25','0','0'),('25','18','0'),('25','19','0'),('25','20','0'),('25','21','0'),('25','22','0'),('25','23','0'),('25','24','0'),('24','0','0'),('24','13','0'),('24','14','0'),('24','15','0'),('24','16','0'),('24','17','0'),('26','0','0'),('26','13','0'),('26','14','0'),('26','15','0'),('26','16','0');

UNLOCK TABLES;

/*Table structure for table `ref_role_school` */

DROP TABLE IF EXISTS `ref_role_school`;

CREATE TABLE `ref_role_school` (
  `role_id` varchar(50) NOT NULL COMMENT '角色编号',
  `school_id` varchar(50) NOT NULL COMMENT '机构编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结构-角色映射（机构有哪些角色）';

/*Data for the table `ref_role_school` */

LOCK TABLES `ref_role_school` WRITE;

insert  into `ref_role_school`(`role_id`,`school_id`) values ('1','1'),('1','2'),('1','3'),('1','4'),('2','2'),('2','5');

UNLOCK TABLES;

/*Table structure for table `ref_school_app` */

DROP TABLE IF EXISTS `ref_school_app`;

CREATE TABLE `ref_school_app` (
  `app_id` varchar(50) NOT NULL COMMENT '应用ID',
  `school_id` varchar(50) NOT NULL COMMENT '机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构，应用映射表';

/*Data for the table `ref_school_app` */

LOCK TABLES `ref_school_app` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_teacher_class` */

DROP TABLE IF EXISTS `ref_teacher_class`;

CREATE TABLE `ref_teacher_class` (
  `teacher_id` varchar(50) NOT NULL COMMENT '老师ID',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0：教师， 1：班主任',
  `class_id` varchar(50) NOT NULL COMMENT '班级ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师-班级';

/*Data for the table `ref_teacher_class` */

LOCK TABLES `ref_teacher_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `ref_user_role` */

DROP TABLE IF EXISTS `ref_user_role`;

CREATE TABLE `ref_user_role` (
  `user_id` varchar(50) NOT NULL COMMENT '用户编号',
  `role_id` varchar(50) NOT NULL COMMENT '角色编号',
  `school_id` varchar(50) NOT NULL COMMENT '机构ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `ref_user_role` */

LOCK TABLES `ref_user_role` WRITE;

insert  into `ref_user_role`(`user_id`,`role_id`,`school_id`) values ('1','1','1');

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
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `short_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `limit_age` int(11) DEFAULT NULL,
  `section_year` int(11) DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_class_section` */

LOCK TABLES `sync_class_section` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_department` */

DROP TABLE IF EXISTS `sync_department`;

CREATE TABLE `sync_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `short_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `master_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `master_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_department` */

LOCK TABLES `sync_department` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_grade_class` */

DROP TABLE IF EXISTS `sync_grade_class`;

CREATE TABLE `sync_grade_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xd` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `nj` int(11) DEFAULT NULL,
  `bh` int(11) DEFAULT NULL,
  `bjlx` int(11) DEFAULT NULL,
  `xq` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `master_id` int(11) DEFAULT NULL,
  `master_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_grade_class` */

LOCK TABLES `sync_grade_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_org_school` */

DROP TABLE IF EXISTS `sync_org_school`;

CREATE TABLE `sync_org_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ename` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xz` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bg_picture` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `short_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_org_school` */

LOCK TABLES `sync_org_school` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_patriarch` */

DROP TABLE IF EXISTS `sync_patriarch`;

CREATE TABLE `sync_patriarch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `student_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `work` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `work_at` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `sfjhr` int(11) DEFAULT NULL,
  `sfyqsh` int(11) DEFAULT NULL,
  `patriarch_flag` int(11) DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_patriarch` */

LOCK TABLES `sync_patriarch` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_school_type` */

DROP TABLE IF EXISTS `sync_school_type`;

CREATE TABLE `sync_school_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_school_type` */

LOCK TABLES `sync_school_type` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_student` */

DROP TABLE IF EXISTS `sync_student`;

CREATE TABLE `sync_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `class_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xsxm` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xszp` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `csrq` bigint(255) DEFAULT NULL,
  `rxrq` bigint(255) DEFAULT NULL,
  `xsxb` int(11) DEFAULT NULL,
  `xssg` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xjh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `qgxjh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `jyid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `syd` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `yxzjlx` int(11) DEFAULT NULL,
  `yxzjh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `jbs` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sfsbt` int(11) DEFAULT NULL,
  `sbtxh` int(11) DEFAULT NULL,
  `xslb` int(11) DEFAULT NULL,
  `gb` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mz` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `jg` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zzmm` int(11) DEFAULT NULL,
  `zslb` int(11) DEFAULT NULL,
  `lydq` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hkszd` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xjzd` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hkxz` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_student` */

LOCK TABLES `sync_student` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teacher` */

DROP TABLE IF EXISTS `sync_teacher`;

CREATE TABLE `sync_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `identity` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_manage` int(11) DEFAULT NULL,
  `title_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `start_work` bigint(255) DEFAULT NULL,
  `head_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `high_time` bigint(255) DEFAULT NULL,
  `zc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sfzrjs` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `jg` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zzmm` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `rjxk` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `xq` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zgxl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `lwxsj` bigint(255) DEFAULT NULL,
  `sfhq` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sfbzr` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `wyyz` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zyjsgwfl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_teacher` */

LOCK TABLES `sync_teacher` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_teacher_class` */

DROP TABLE IF EXISTS `sync_teacher_class`;

CREATE TABLE `sync_teacher_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_teacher_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sync_class_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_teacher_class` */

LOCK TABLES `sync_teacher_class` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_title` */

DROP TABLE IF EXISTS `sync_title`;

CREATE TABLE `sync_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `jb` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `px` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_date` bigint(255) DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sync_title` */

LOCK TABLES `sync_title` WRITE;

UNLOCK TABLES;

/*Table structure for table `sync_user` */

DROP TABLE IF EXISTS `sync_user`;

CREATE TABLE `sync_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sync_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ref_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `photo_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `event` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sync_date` datetime DEFAULT NULL,
  `sync_del_flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `app_status` int(10) DEFAULT NULL COMMENT '应用状态[0:审核中, 1:已上线, 2:下线, 3:其他异常]',
  `is_default` int(10) DEFAULT '0' COMMENT '是否是默认应用',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(5000) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `target_user` varchar(255) DEFAULT NULL COMMENT '目标用户（1：教师，2：学生，3：家长，4：其他）',
  `category` varchar(255) DEFAULT NULL COMMENT '应用类别（1.教学教务；2.互动空间）',
  `developers` varchar(255) DEFAULT NULL COMMENT '开发商',
  `sfczxmz` int(10) DEFAULT '0' COMMENT '是否存在项目中（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';

/*Data for the table `sys_app` */

LOCK TABLES `sys_app` WRITE;

insert  into `sys_app`(`id`,`name`,`icon_url`,`web_url`,`pic_url`,`app_status`,`is_default`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`target_user`,`category`,`developers`,`sfczxmz`) values ('1','人事管理','app/detail/201609/1475142163669.png','renshi/renyuan/index','app/detail/201610/1476681933486.png,app/detail/201610/1476681933478.png,app/detail/201610/1476681933468.png,app/detail/201610/1476681933450.png,app/detail/201610/1476681933440.png',0,1,'1',1479190291413,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">人事管理系统的管理内容主要是对学校的教职工基本信息、教职工账号等信息进行切实的管理。同时对学校部门设置管理、职务设置管理等提供入口，满足教学人事管理的日常需求，让繁杂的教职工信息管理变得简单，并为其他系统应用提供人事数据。</span></p>',0,'1','1','教育云平台',1),('12','互动社区','app/detail/201611/1479190668287.png','http://115.28.175.51/cloud/index.php/forum/index/index','',0,0,'1',1479191517258,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"mso-spacerun: \'yes\'; font-family: 微软雅黑; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\"><span style=\"font-family: 微软雅黑;\">互动社区的本质是对信息的接收和传播，学校或者个人可以通过互动社区应用有针性面向一定的群体发布内容，具有范围广、参与人群广和开放性的特点，管理层级分明，规范有序，促进交流，有较强的互动性</span></span></p>',0,'3,2,1','2','教育云平台',1),('13','积分商城','app/detail/201611/1479191137475.png','','',0,0,'1',1479191478967,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; font-size: 11pt;\"><span style=\"font-family: 微软雅黑;\">积分是一种用户奖励计划，在积分商城内所有商品都参与积分返利活动。用户可根据获得的积分可以在商城购物中，直接作为现金抵用，用来购买商城内的商品。同时，用户对商品可进行站外分享。</span></span></p>',0,'3,2,1','2','教育云平台',1),('14','活动中心','app/detail/201611/1479191594544.png','','',0,1,'1',1479191625412,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">活动中心平台是通过平台发起活动，绝大部分在平台上进行的活动，也可以选择线下进行活动，是一个先要在平台上发布活动信息，募集活动人员，在平台或者线下上进行活动的流程。活动中心向所有人开放，解决了很多由于人力、物力和时间的约束难以进行的活动也得以通过平台展开，活动中心就是这样一个提供活动相关服务的平台。</span></span></p>',0,'3,2,1','2','教育云平台',1),('15','慕课直播','app/detail/201611/1479191651165.png','http://mooc.gukeer.cc/mooc/ssoLogin/auth','',0,0,'1',1479973096581,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; font-size: 11pt;\"><span style=\"font-family: 微软雅黑;\">在线直播平台通过教师在线授课，学生网上学习的方式。教师可以通过白板授课，在白板中进行知识点的注释和讲解，通过公共和私人两种方式和学生交流互动，教师可以实时查看学生参与状态，支持用户同一时间共享多个摄像头，支持上传</span>pdf、word等多种格式文件，实现桌面共享，学生可以在线回答问题，课后可以根据上课情况进行反馈，教师能够通过学生的课堂反馈，直接的了解到学生学习的真正所需，以便教师进行自我反思和调控教学。</span></p>',0,'2,1','1','教育云平台',1),('16','你问我答','app/detail/201611/1479191753232.png','','',0,0,'1',1479191817321,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"mso-spacerun: \'yes\'; font-family: 微软雅黑; font-size: 10.5000pt; mso-font-kerning: 0.0000pt;\"><span style=\"font-family: 微软雅黑;\">你问我答平台中，用户可以具有针对性地提出问题，通过其他用户解答的方式，来解决该问题，完成问答过程。同时，这些问题的答案又会进一步作为搜索结果，提供给其他有类似疑问的用户，达到分享知识的效果。你问我答的最大特点，就是让用户所拥有的隐性知识转化成显性知识</span></span><span style=\"mso-spacerun: \'yes\'; font-family: 微软雅黑; font-size: 10.5000pt; mso-font-kerning: 0.0000pt;\"><span style=\"font-family: 微软雅黑;\">，用户既是你问我答内容的使用者，同时又是你问我答的创造者。</span></span></p>',0,'3,2,1','2','教育云平台',1),('17','兴趣群组','app/detail/201611/1479191921557.png','','',0,0,'1',1479191942120,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; font-size: 11pt;\"><span style=\"font-family: 微软雅黑;\">群组分为公开和私有两种</span>,加入私有群组需要创建人审核，群成员可邀请好友加入群组。群组成员可以在群组内公开发帖，来实现动态信息内容的共享。群组有讨论、有分享、有成员关系、有人与人之间的沟通。成员是群组最重要的部分，通过互动维系成员联系。</span></p>',0,'3,2,1','2','教育云平台',1),('18','智能穿戴','app/detail/201611/1479191966396.png','','',0,0,'1',1487126257437,NULL,NULL,'<p>我也加个评论呗</p>',0,'1','1','教育云平台',1),('19','资讯广场','app/detail/201611/1479192004915.png','','',0,0,'1',1479192031007,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; font-size: 11pt;\"><span style=\"font-family: 微软雅黑;\">资讯广场以发布展示资讯内容为主，例如新闻类、文章类、动态等。可以自定义分类，展示全方位的内容信息，校内人员可通过资讯了解学校的动态。对于优质类的内容可进行推荐展示或分享。</span></span></p>',0,'3,2,1','2','教育云平台',1),('2','学籍管理','app/detail/201609/1475141395501.png','class/index','app/detail/201610/1476675269887.png,app/detail/201610/1476675269864.png,app/detail/201610/1476675269854.png,app/detail/201610/1476675269842.png,app/detail/201610/1476675269832.png,app/detail/201610/1476675269816.png,app/detail/201610/1476675269804.png',0,1,'1',1479190430102,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">学籍管理系统主要对学校学生信息的基础数据进行管理和维护，同时维护学校的部分基础信息，比如学校的学段设置、班级设置、其他基本设置等等。学籍管理系统作为学生基础数据的入口，为学生考勤、成绩分析等等系统提供了基础数据，实现了对整个平台的学生基础数据的统一管理，减少数据维护工作，减少沟通成本，提高了相应的教学教务的工作效率。</span></p>',0,'1','1','教育云平台',1),('20','班级圈','app/detail/201611/1479793232210.png','http://115.28.175.51/cloud/index.php/weibo/index/index','',0,0,'1',1487126235205,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">班级圈是一个<span style=\"color: #333333;\">个基于</span></span></span><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">用户</span></span><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">关系信息，</span></span><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">分享</span></span><span style=\"font-family: 微软雅黑; color: #333333; font-size: 10.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-family: 微软雅黑;\">、传播以及获取的平台，用户通过在社交圈分享个人的最新动态和每时每刻的所见所闻，并且能够通过相互关注的方式实现用户之间的交流和社交，具有实时性和快捷性的特点，深受用户喜爱。</span></span></p>',0,'3,2,1','2','教育云平台',1),('3','应用商店','app/detail/201609/1475142456413.png','app/appstore/index','app/detail/201610/1476683805438.png,app/detail/201610/1476683805416.png,app/detail/201610/1476683805407.png',0,0,'1',1479190601359,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">应用商店是一个开放的应用生态圈，可以利用全接口整合第三方应用，学校可以根据其个性化需求在应用商店中选择实际需要的应用，同时商店中的应用会持续更新，满足学校日后对应用的需求，促进学校的长远发展。</span></p>',0,'3,2,1','2','教育云平台',1),('4','通讯录','app/detail/201609/1475139465580.png','contact/contact/index','app/detail/201609/1475139775314.png',0,0,'1',1479190409746,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">通讯录作为平台的基础功能，同步人事管理中的部门管理的教职工通讯信息，方便学校内部查询相关教职工信息，便于管理和使用。</span></p>',0,'1','1','教育云平台',1),('5','通知公告','app/detail/201609/1475142634153.png','notify/index','app/detail/201610/1476688169684.png,app/detail/201610/1476688169674.png,app/detail/201610/1476688169667.png,app/detail/201610/1476688169653.png',0,0,'1',1479190510494,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"font-family: 微软雅黑; color: #333333; letter-spacing: 0pt; font-size: 10.5pt;\">通知公告主要面向教职工，对于校内的各类通知的发布，均可以通过通知公告应用来实现，并且对发布内容以及通告栏目有明确的管理人员和制度，对通知的发布做到严格控制。</span></p>',0,'1','1','教育云平台',1),('6','阳光分班','app/detail/201610/1476699968952.png','','app/detail/201610/1476688618304.png,app/detail/201610/1476688618299.png,app/detail/201610/1476688618294.png',0,0,'1',1479190639391,NULL,NULL,'<p class=\"MsoNormal\"><span style=\"mso-spacerun: \'yes\'; font-family: 微软雅黑; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\">阳光分班系统通过分班自动化、公开化的操作，实现均衡搭配，智能分班的理念。以分班数据的随机分配原则，为用户提供多种分班条件的配置，满足不同分班需求，真正实现阳光分班。</span></p>',0,'1','1','教育云平台',1);

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

insert  into `sys_config`(`id`,`param_type`,`param_key`,`param_value`,`del_flag`) values ('1','bottomText','s','<p>©2014-2016 Beijing Search Champion Technology Co.,Ltd. 北京教育云开放平台科技有限公司</p><p>京ICP备16000182-3号|京公安网备11010502027075</p>',0),('2','defaultPassword','parent','000000',0),('3','defaultPassword','teacher ','111111',0),('4','defaultPassword','student','000000',0);

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
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `create_by` varchar(10) DEFAULT NULL COMMENT '创建者',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
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
  `belong` int(11) DEFAULT '0' COMMENT '所属应用ID',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(10) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_menu_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`parent_id`,`parent_ids`,`name`,`sort`,`href`,`target`,`icon`,`is_show`,`permission`,`belong`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1','0','','人事管理模块',0,'/renshi',NULL,NULL,0,'renShi:renYuan:view',0,'2',20160808084312,'1',20160809021248,'<p>这个菜单能添加学生</p>',0),('10','9','','机构管理',0,'/school/index',NULL,NULL,0,'school:index:view',0,'3',2,NULL,NULL,NULL,0),('11','9','','角色管理',0,'/school/permissionMan',NULL,NULL,0,'school:role:view',0,'3',2,NULL,NULL,NULL,0),('12','9','','应用管理',0,'/app/index',NULL,NULL,0,'school:app:view',0,'3',2,NULL,NULL,NULL,0),('13','0','','通知公告模块',0,'/notify',NULL,NULL,0,'notify:notify:view',0,'3',2,NULL,NULL,NULL,0),('14','13','','通知公告',0,'/notify/index',NULL,NULL,0,'notify:notify:view',0,'3',2,NULL,NULL,NULL,0),('15','13','','栏目管理',0,'/notify/lanmu/manage',NULL,NULL,0,'notify:lanmu:view',0,'3',2,NULL,NULL,NULL,0),('16','13','','发布',0,'/notify/add',NULL,NULL,0,'notify:notify:add',0,'3',2,NULL,NULL,NULL,0),('17','13','','角色分配',0,'/notify/role/fenpei',NULL,NULL,0,'notify:role:view',0,'3',2,NULL,NULL,NULL,0),('18','0','','学籍管理模块',0,'/class',NULL,NULL,0,'class:banji:view',0,'3',2,NULL,NULL,NULL,0),('19','18','','学生管理',0,'/class/index',NULL,NULL,0,'class:student:view',0,'3',2,NULL,NULL,NULL,0),('20','18','','班级管理',0,'/class/banji/view',NULL,NULL,0,'class:banji:view',0,'3',2,NULL,NULL,NULL,0),('21','18','','学校设置',0,'/class/schoolSetting/view',NULL,NULL,0,'class:schoolSetting:view',0,'3',2,NULL,NULL,NULL,0),('22','18','','学段管理',0,'/class/xueDuan/view',NULL,NULL,0,'class:xueDuan:view',0,'3',2,NULL,NULL,NULL,0),('23','18','','学生账号管理',0,'/class/stuAccount/view',NULL,NULL,0,'class:stuAccount:view',0,'3',2,NULL,NULL,NULL,0),('24','18','','家长账号管理',0,'/class/parAccount/view',NULL,NULL,0,'class:parAccount:view',0,'3',2,NULL,NULL,NULL,0),('25','18','','角色管理',0,'/class/roleManage/view',NULL,NULL,0,'class:role:view',0,'3',2,NULL,NULL,NULL,0),('26','0','','通讯录',0,'/contact/contact/index',NULL,NULL,0,'contact:contact:view',0,'1',1477033340991,NULL,NULL,'',0),('4','1','','人员管理',0,'/renshi/rsindex',NULL,NULL,0,'renShi:renYuan:view',0,'2',20160809074410,NULL,NULL,'这个菜单管理部门人员',0),('5','1','','账号管理',0,'/renshi/rsaccount/index',NULL,NULL,0,'renshi:account:view',0,'3',2,NULL,NULL,NULL,0),('6','1','','部门管理',0,'/renshi/rsbumen/index',NULL,NULL,0,'renShi:bumen:view',0,'3',2,NULL,NULL,NULL,0),('7','1','','职务信息',0,'/renshi/rszhiwuindex',NULL,NULL,0,'renShi:zhiwu:view',0,'3',2,NULL,NULL,NULL,0),('8','1','','角色分配',0,'/renshi/rsrolefp/index',NULL,NULL,0,'renShi:role:view',0,'3',2,NULL,NULL,NULL,0),('9','0','','机构管理模块',0,'/school',NULL,NULL,0,'school:index:view',0,'3',2,NULL,NULL,NULL,0);

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

insert  into `sys_monitor`(`id`,`cpu`,`jvm`,`ram`,`toemail`,`client_id`,`open_url`) values ('1','99','99','99','xxxxxxx@qq.com',NULL,NULL);

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

insert  into `sys_role`(`id`,`name`,`enname`,`role_identify`,`role_type`,`useable`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1','超级管理员','root','root',0,'1','1',20160808083942,'1',1478142092383,'<p>云平台系统后台管理员，权限最大</p>',0),('2','区级管理员','admin','admin',1,'1','1',20160809021736,'1',20160809022413,'区级管理员',0),('23','人事管理员',NULL,'teacher',3,'1','1',1478500421859,NULL,NULL,'<p>renshi管理员</p>',0),('24','通知公告管理员',NULL,'teacher',3,'1','1',1478500515645,NULL,NULL,'<p>通知公告管理员</p>',1),('25','学籍管理员',NULL,'teacher',NULL,NULL,'1',1478501105380,NULL,0,'',0),('26','通知公告管理员',NULL,'teacher',NULL,NULL,'1',1479379640842,NULL,NULL,'<p>a</p>',0),('3','校级管理员','teacher','teacher',3,'1','1',20160809021736,'1',1473042933659,'校级管理员，拥有每个模块所有权限，创建机构时分配',0),('4','学生','student','student',4,'1','1',20160809021736,'1',1472797318909,'学生角色',0),('5','家长','patriarch','patriarch',5,'1','1',20160809021736,'1',0,'家长角色',0),('7','教师','schoolAdmin','teacher',3,'1','1',2,'1',1,'教职工角色',0);

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

insert  into `sys_user`(`id`,`school_id`,`username`,`password`,`name`,`ref_id`,`user_type`,`photo_url`,`login_flag`,`login_mark`,`last_login_time`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1','0','root','OE43szSeXws9orNa6ooYsQ==','超级管理员','0',0,'',1,NULL,NULL,'0',20130527080000,'1',20160615134945,'',0);

UNLOCK TABLES;

/*Table structure for table `teach_task_config` */

DROP TABLE IF EXISTS `teach_task_config`;

CREATE TABLE `teach_task_config` (
  `config_id` varchar(50) NOT NULL COMMENT '教务管理配置id',
  `school_id` varchar(50) NOT NULL COMMENT '机构id',
  `config_type` varchar(20) DEFAULT NULL COMMENT '教务管理配置类别',
  `config_value` varchar(100) DEFAULT NULL COMMENT '配置值',
  `create_date` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach_task_config` */

LOCK TABLES `teach_task_config` WRITE;

insert  into `teach_task_config`(`config_id`,`school_id`,`config_type`,`config_value`,`create_date`,`create_by`,`update_date`,`update_by`,`del_flag`,`remarks`) values ('1','37','room_type','普通教室',NULL,NULL,NULL,NULL,0,'教室类型'),('2','37','room_type','多媒体教室',NULL,NULL,NULL,NULL,0,'教室类型'),('3','37','room_type','报告厅',NULL,NULL,NULL,NULL,0,'教室类型'),('4','37','room_type','考试教室',NULL,NULL,NULL,NULL,0,'教室类型'),('5','37','course_type','必修课',NULL,NULL,NULL,NULL,0,'课程类型'),('6','37','course_type','选修课',NULL,NULL,NULL,NULL,0,'课程类型');

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
  `rxrq` bigint(20) DEFAULT NULL COMMENT '入学年度',
  `xsxb` int(10) DEFAULT NULL COMMENT '性别【0位置，1男，2女】',
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
  `xslb` int(10) DEFAULT NULL COMMENT '学生类别',
  `gb` varchar(100) DEFAULT NULL COMMENT '国别',
  `mz` varchar(100) DEFAULT NULL COMMENT '民族',
  `jg` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `zzmm` int(10) DEFAULT NULL COMMENT '政治面貌',
  `zslb` int(10) DEFAULT NULL COMMENT '招生类别',
  `lydq` varchar(200) DEFAULT NULL COMMENT '来源地区',
  `hkszd` varchar(200) DEFAULT NULL COMMENT '户口所在地',
  `xjzd` varchar(200) DEFAULT NULL COMMENT '现居住址',
  `hkxz` varchar(100) DEFAULT NULL COMMENT '户口性质',
  `sfbshk` int(10) DEFAULT NULL COMMENT '是否按本市户口学生对待[0是 1否]',
  `status` int(10) DEFAULT NULL COMMENT '学生状态[0在籍正常，1在籍不在校，2在校不在籍，3不在籍不在校]',
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
  `school_id` varchar(50) NOT NULL COMMENT '机构ID',
  `department_id` varchar(50) NOT NULL COMMENT '部门ID',
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
  `sfbzr` varchar(10) DEFAULT NULL COMMENT '是否班主任',
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
 INSERT INTO `sync_class_section`(`sync_id`,`school_id`,`name`,`short_name`,`limit_age`,`section_year`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.short_name,new.limit_age,new.section_year,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_class_section` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ClassSection_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ClassSection_m` AFTER UPDATE ON `org_class_section` FOR EACH ROW BEGIN
DELETE FROM `sync_class_section` where `sync_id` = new.id;INSERT INTO `sync_class_section`(`sync_id`,`school_id`,`name`,`short_name`,`limit_age`,`section_year`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.short_name,new.limit_age,new.section_year,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_department`(`sync_id`,`parent_id`,`school_id`,`name`,`short_name`,`master_id`,`master_name`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.school_id,new.name,new.short_name,new.master_id,new.master_name,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_department` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Department_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Department_m` AFTER UPDATE ON `org_department` FOR EACH ROW BEGIN
DELETE FROM `sync_department` where `sync_id` = new.id;INSERT INTO `sync_department`(`sync_id`,`parent_id`,`school_id`,`name`,`short_name`,`master_id`,`master_name`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.school_id,new.name,new.short_name,new.master_id,new.master_name,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_grade_class`(`sync_id`,`school_id`,`name`,`xd`,`nj`,`bh`,`bjlx`,`xq`,`master_id`,`master_name`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.xd,new.nj,new.bh,new.bjlx,new.xq,new.master_id,new.master_name,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_grade_class` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `GradeClass_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `GradeClass_m` AFTER UPDATE ON `org_grade_class` FOR EACH ROW BEGIN
DELETE FROM `sync_grade_class` where `sync_id` = new.id;INSERT INTO `sync_grade_class`(`sync_id`,`school_id`,`name`,`xd`,`nj`,`bh`,`bjlx`,`xq`,`master_id`,`master_name`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.xd,new.nj,new.bh,new.bjlx,new.xq,new.master_id,new.master_name,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_org_school`(`sync_id`,`parent_id`,`name`,`ename`,`xz`,`type`,`grade`,`logo`,`bg_picture`,`address`,`phone`,`short_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.name,new.ename,new.xz,new.type,new.grade,new.logo,new.bg_picture,new.address,new.phone,new.short_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `School_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `School_m` AFTER UPDATE ON `org_school` FOR EACH ROW BEGIN
DELETE FROM `sync_org_school` where `sync_id` = new.id;INSERT INTO `sync_org_school`(`sync_id`,`parent_id`,`name`,`ename`,`xz`,`type`,`grade`,`logo`,`bg_picture`,`address`,`phone`,`short_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.parent_id,new.name,new.ename,new.xz,new.type,new.grade,new.logo,new.bg_picture,new.address,new.phone,new.short_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_school_type`(`sync_id`,`school_id`,`name`,`email`,`phone`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.email,new.phone,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_school_type` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `SchoolType_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `SchoolType_m` AFTER UPDATE ON `org_school_type` FOR EACH ROW BEGIN
DELETE FROM `sync_school_type` where `sync_id` = new.id;INSERT INTO `sync_school_type`(`sync_id`,`school_id`,`name`,`email`,`phone`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.name,new.email,new.phone,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_title`(`sync_id`,`school_id`,`mc`,`jb`,`px`,`update_date`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.mc,new.jb,new.px,new.update_date,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `org_title` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Title_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Title_m` AFTER UPDATE ON `org_title` FOR EACH ROW BEGIN
DELETE FROM `sync_title` where `sync_id` = new.id;INSERT INTO `sync_title`(`sync_id`,`school_id`,`mc`,`jb`,`px`,`update_date`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.mc,new.jb,new.px,new.update_date,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_user`(`sync_id`,`school_id`,`username`,`password`,`ref_id`,`user_type`,`photo_url`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.username,new.password,new.ref_id,new.user_type,new.photo_url,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `sys_user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `User_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `User_m` AFTER UPDATE ON `sys_user` FOR EACH ROW BEGIN
DELETE FROM `sync_user` where `sync_id` = new.id;INSERT INTO `sync_user`(`sync_id`,`school_id`,`username`,`password`,`ref_id`,`user_type`,`photo_url`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.username,new.password,new.ref_id,new.user_type,new.photo_url,'MODIFY','0',UTC_TIMESTAMP(),'0');
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

/* Trigger structure for table `user_patriarch` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Patriarch_i` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Patriarch_i` AFTER INSERT ON `user_patriarch` FOR EACH ROW BEGIN
 INSERT INTO `sync_patriarch`(`sync_id`,`name`,`student_id`,`account`,`work`,`work_at`,`phone`,`gender`,`sfjhr`,`sfyqsh`,`patriarch_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.student_id,new.account,new.work,new.work_at,new.phone,new.gender,new.sfjhr,new.sfyqsh,new.patriarch_flag,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_patriarch` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Patriarch_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Patriarch_m` AFTER UPDATE ON `user_patriarch` FOR EACH ROW BEGIN
DELETE FROM `sync_patriarch` where `sync_id` = new.id;INSERT INTO `sync_patriarch`(`sync_id`,`name`,`student_id`,`account`,`work`,`work_at`,`phone`,`gender`,`sfjhr`,`sfyqsh`,`patriarch_flag`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.name,new.student_id,new.account,new.work,new.work_at,new.phone,new.gender,new.sfjhr,new.sfyqsh,new.patriarch_flag,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 INSERT INTO `sync_student`(`sync_id`,`school_id`,`class_id`,`account`,`xsxm`,`xszp`,`phone`,`csrq`,`rxrq`,`xsxb`,`xssg`,`xh`,`xjh`,`qgxjh`,`jyid`,`syd`,`yxzjlx`,`yxzjh`,`jbs`,`sfsbt`,`sbtxh`,`xslb`,`gb`,`mz`,`jg`,`zzmm`,`zslb`,`lydq`,`hkszd`,`xjzd`,`hkxz`,`status`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.class_id,new.account,new.xsxm,new.xszp,new.phone,new.csrq,new.rxrq,new.xsxb,new.xssg,new.xh,new.xjh,new.qgxjh,new.jyid,new.syd,new.yxzjlx,new.yxzjh,new.jbs,new.sfsbt,new.sbtxh,new.xslb,new.gb,new.mz,new.jg,new.zzmm,new.zslb,new.lydq,new.hkszd,new.xjzd,new.hkxz,new.status,'INSERT','0',UTC_TIMESTAMP(),'0');
END */$$


DELIMITER ;

/* Trigger structure for table `user_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Student_m` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Student_m` AFTER UPDATE ON `user_student` FOR EACH ROW BEGIN
DELETE FROM `sync_student` where `sync_id` = new.id;INSERT INTO `sync_student`(`sync_id`,`school_id`,`class_id`,`account`,`xsxm`,`xszp`,`phone`,`csrq`,`rxrq`,`xsxb`,`xssg`,`xh`,`xjh`,`qgxjh`,`jyid`,`syd`,`yxzjlx`,`yxzjh`,`jbs`,`sfsbt`,`sbtxh`,`xslb`,`gb`,`mz`,`jg`,`zzmm`,`zslb`,`lydq`,`hkszd`,`xjzd`,`hkxz`,`status`,`event`, `source`, `sync_date` ,`sync_del_flag`)
VALUES (new.id,new.school_id,new.class_id,new.account,new.xsxm,new.xszp,new.phone,new.csrq,new.rxrq,new.xsxb,new.xssg,new.xh,new.xjh,new.qgxjh,new.jyid,new.syd,new.yxzjlx,new.yxzjh,new.jbs,new.sfsbt,new.sbtxh,new.xslb,new.gb,new.mz,new.jg,new.zzmm,new.zslb,new.lydq,new.hkszd,new.xjzd,new.hkxz,new.status,'MODIFY','0',UTC_TIMESTAMP(),'0');
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
 `sectionShortName` varchar(255) 
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
 `xqId` varchar(50) ,
 `schoolTypeName` varchar(100) ,
 `sectionShortName` varchar(255) 
)*/;

/*Table structure for table `v_teacher_school` */

DROP TABLE IF EXISTS `v_teacher_school`;

/*!50001 DROP VIEW IF EXISTS `v_teacher_school` */;
/*!50001 DROP TABLE IF EXISTS `v_teacher_school` */;

/*!50001 CREATE TABLE  `v_teacher_school`(
 `id` varchar(50) ,
 `schoolId` varchar(50) ,
 `departmentId` varchar(50) ,
 `name` varchar(255) ,
 `quanPin` varchar(255) ,
 `gender` int(11) ,
 `identity` varchar(50) ,
 `account` varchar(100) ,
 `isManage` int(10) ,
 `roleId` varchar(50) ,
 `titleId` varchar(50) ,
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

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_partiarch_student_class_info` AS (select `v1`.`parentId` AS `parentId`,`v1`.`parentName` AS `parentName`,`v1`.`parAccount` AS `parAccount`,`v1`.`work` AS `work`,`v1`.`work_at` AS `work_at`,`v1`.`parentPhone` AS `parentPhone`,`v1`.`parentGender` AS `parentGender`,`v1`.`sfjhr` AS `sfjhr`,`v1`.`sfyqsh` AS `sfyqsh`,`v1`.`patriarch_flag` AS `patriarch_flag`,`v1`.`studentId` AS `studentId`,`v1`.`schoolId` AS `schoolId`,`v1`.`classId` AS `classId`,`v1`.`studentAccount` AS `studentAccount`,`v1`.`xsxm` AS `xsxm`,`v1`.`xmpy` AS `xmpy`,`v1`.`xszp` AS `xszp`,`v1`.`studentPhone` AS `studengPhone`,`v1`.`csrq` AS `csrq`,`v1`.`rxrq` AS `rxrq`,`v1`.`xsxb` AS `xsxb`,`v1`.`xssg` AS `xssg`,`v1`.`xh` AS `xh`,`v1`.`xjh` AS `xjh`,`v1`.`qgxjh` AS `qgxjh`,`v1`.`jyid` AS `jyid`,`v1`.`syd` AS `syd`,`v1`.`yxzjlx` AS `yxzjlx`,`v1`.`yxzjh` AS `yxzjh`,`v1`.`jbs` AS `jbs`,`v1`.`sfsbt` AS `sfsbt`,`v1`.`sbtxh` AS `sbtxh`,`v1`.`xslb` AS `xslb`,`v1`.`gb` AS `gb`,`v1`.`mz` AS `mz`,`v1`.`jg` AS `jg`,`v1`.`zzmm` AS `zzmm`,`v1`.`zslb` AS `zslb`,`v1`.`lydq` AS `lydq`,`v1`.`hkszd` AS `hkszd`,`v1`.`xjzd` AS `xjzd`,`v1`.`hkxz` AS `hkxz`,`v1`.`sfbshk` AS `sfbshk`,`v1`.`status` AS `status`,`v1`.`delFlag` AS `delFlag`,`v2`.`className` AS `className`,`v2`.`classShortName` AS `classShortName`,`v2`.`xd` AS `xd`,`v2`.`nj` AS `nj`,`v2`.`bh` AS `bh`,`v2`.`bjlx` AS `bjlx`,`v2`.`xq` AS `xq`,`v2`.`rxnd` AS `rxnd`,`v2`.`master_id` AS `master_id`,`v2`.`master_name` AS `master_name`,`v2`.`schoolName` AS `schoolName`,`v2`.`email` AS `email`,`v2`.`schoolPhone` AS `schoolPhone`,`v2`.`sectionName` AS `sectionName`,`v2`.`sectiongShortName` AS `sectiongShortName`,`v2`.`limit_age` AS `limit_age`,`v2`.`section_year` AS `section_year` from (`v_partiarch_student` `v1` left join `v_class_xd_xq` `v2` on(((`v1`.`classId` = `v2`.`classId`) and (`v1`.`schoolId` = `v2`.`schoolId`))))) */;

/*View structure for view v_section_class */

/*!50001 DROP TABLE IF EXISTS `v_section_class` */;
/*!50001 DROP VIEW IF EXISTS `v_section_class` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_section_class` AS select `c`.`school_id` AS `schoolId`,concat(`s`.`name`,`c`.`nj`) AS `indexName`,`c`.`id` AS `classId`,`c`.`name` AS `className`,`c`.`xd` AS `xd`,`c`.`nj` AS `nj`,`c`.`xq` AS `xq`,`s`.`name` AS `name`,`s`.`short_name` AS `sectionShortName` from (`org_grade_class` `c` left join `org_class_section` `s` on((`c`.`xd` = `s`.`id`))) where (`c`.`school_id` = `s`.`school_id`) */;

/*View structure for view v_student_classinfo */

/*!50001 DROP TABLE IF EXISTS `v_student_classinfo` */;
/*!50001 DROP VIEW IF EXISTS `v_student_classinfo` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_student_classinfo` AS (select `stu`.`id` AS `id`,`stu`.`school_id` AS `school_id`,`stu`.`class_id` AS `class_id`,`stu`.`account` AS `student_account`,`stu`.`xsxm` AS `xsxm`,`stu`.`xmpy` AS `xmpy`,`stu`.`xszp` AS `xszp`,`stu`.`phone` AS `phone`,`stu`.`csrq` AS `csrq`,`stu`.`rxrq` AS `rxrq`,`stu`.`xsxb` AS `xsxb`,`stu`.`xssg` AS `xssg`,`stu`.`xh` AS `xh`,`stu`.`xjh` AS `xjh`,`stu`.`qgxjh` AS `qgxjh`,`stu`.`jyid` AS `jyid`,`stu`.`syd` AS `syd`,`stu`.`yxzjlx` AS `yxzjlx`,`stu`.`yxzjh` AS `yxzjh`,`stu`.`jbs` AS `jbs`,`stu`.`sfsbt` AS `sfsbt`,`stu`.`sbtxh` AS `sbtxh`,`stu`.`xslb` AS `xslb`,`stu`.`gb` AS `gb`,`stu`.`mz` AS `mz`,`stu`.`jg` AS `jg`,`stu`.`zzmm` AS `zzmm`,`stu`.`zslb` AS `zslb`,`stu`.`lydq` AS `lydq`,`stu`.`hkszd` AS `hkszd`,`stu`.`xjzd` AS `xjzd`,`stu`.`hkxz` AS `hkxz`,`stu`.`sfbshk` AS `sfbshk`,`stu`.`status` AS `status`,`stu`.`del_flag` AS `del_flag`,`stu`.`remarks` AS `remarks`,`stu`.`create_by` AS `create_by`,`stu`.`create_date` AS `create_date`,`stu`.`update_by` AS `update_by`,`stu`.`update_date` AS `update_date`,`cla`.`className` AS `className`,`cla`.`indexName` AS `indexName`,`cla`.`name` AS `sectionName`,`cla`.`xd` AS `xd`,`cla`.`nj` AS `nj`,`t`.`id` AS `xqId`,`t`.`name` AS `schoolTypeName`,`cla`.`sectionShortName` AS `sectionShortName` from ((`user_student` `stu` join `v_section_class` `cla`) left join `org_school_type` `t` on((`cla`.`xq` = `t`.`id`))) where ((`stu`.`class_id` = `cla`.`classId`) and (`stu`.`school_id` = `cla`.`schoolId`))) */;

/*View structure for view v_teacher_school */

/*!50001 DROP TABLE IF EXISTS `v_teacher_school` */;
/*!50001 DROP VIEW IF EXISTS `v_teacher_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teacher_school` AS (select `u`.`id` AS `id`,`u`.`school_id` AS `schoolId`,`u`.`department_id` AS `departmentId`,`u`.`name` AS `name`,`u`.`quan_pin` AS `quanPin`,`u`.`gender` AS `gender`,`u`.`identity` AS `identity`,`u`.`account` AS `account`,`u`.`is_manage` AS `isManage`,`u`.`role_id` AS `roleId`,`u`.`title_id` AS `titleId`,`u`.`no` AS `no`,`u`.`phone` AS `phone`,`u`.`mobile` AS `mobile`,`u`.`email` AS `email`,`u`.`start_work` AS `startWork`,`u`.`head_url` AS `headUrl`,`u`.`create_by` AS `createBy`,`u`.`create_date` AS `createDate`,`u`.`update_by` AS `updateBy`,`u`.`update_date` AS `updateDate`,`u`.`remarks` AS `remarks`,`u`.`high_time` AS `highTime`,`u`.`high_job` AS `highJob`,`u`.`zc` AS `zc`,`u`.`pzxx` AS `pzxx`,`u`.`address` AS `address`,`u`.`ggjsjb` AS `ggjsjb`,`u`.`htkssj` AS `htkssj`,`u`.`htjssj` AS `htjssj`,`u`.`cym` AS `cym`,`u`.`jtyb` AS `jtyb`,`u`.`sfzrjs` AS `sfzrjs`,`u`.`salary` AS `salary`,`u`.`jg` AS `jg`,`u`.`zzmm` AS `zzmm`,`u`.`cjgzsj` AS `cjgzsj`,`u`.`ysbysj` AS `ysbysj`,`u`.`rjxk` AS `rjxk`,`u`.`sf` AS `sf`,`u`.`wysp` AS `wysp`,`u`.`zgxz` AS `zgxz`,`u`.`xwsl` AS `xwsl`,`u`.`rjxkjb` AS `rjxkjb`,`u`.`xq` AS `xq`,`u`.`gwflf` AS `gwflf`,`u`.`zgxl` AS `zgxl`,`u`.`zgbyxx` AS `zgbyxx`,`u`.`yzy` AS `yzy`,`u`.`pzsj` AS `pzsj`,`u`.`lwxsj` AS `lwxsj`,`u`.`zzdh` AS `zzdh`,`u`.`gzgw` AS `gzgw`,`u`.`bgsdh` AS `bgsdh`,`u`.`sfhq` AS `sfhq`,`u`.`sfbzr` AS `sfbzr`,`u`.`wyyz` AS `wyyz`,`u`.`yxz` AS `yxz`,`u`.`zgxw` AS `zgxw`,`u`.`zyjsgwfl` AS `zyjsgwfl`,`u`.`szjb` AS `szjb`,`u`.`gzgwf` AS `gzgwf`,`u`.`del_flag` AS `delFlag`,`o`.`parent_id` AS `schoolParentId`,`o`.`name` AS `schoolName`,`o`.`ename` AS `ename`,`o`.`sort` AS `sort`,`o`.`xz` AS `xz`,`o`.`code` AS `code`,`o`.`type` AS `type`,`o`.`grade` AS `grade`,`o`.`logo` AS `logo`,`o`.`bg_picture` AS `bg_picture`,`o`.`home_text` AS `homeText`,`o`.`bottom_text` AS `bottomText`,`o`.`address` AS `schoolAddress`,`o`.`m_id` AS `mId`,`o`.`master` AS `master`,`o`.`zip_code` AS `zipCode`,`o`.`patriarch_rule` AS `patriarchRule`,`o`.`student_rule` AS `studentRule`,`o`.`teacher_rule` AS `teacherRule`,`o`.`short_flag` AS `shortFlag`,`o`.`deploy_url` AS `deployUrl`,`o`.`userable` AS `userable`,`o`.`primary_persion` AS `primaryPersion`,`o`.`deputy_persion` AS `deputyPersion` from (`user_teacher` `u` left join `org_school` `o` on((`u`.`school_id` = `o`.`id`)))) */;

/*View structure for view v_title_school */

/*!50001 DROP TABLE IF EXISTS `v_title_school` */;
/*!50001 DROP VIEW IF EXISTS `v_title_school` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_title_school` AS (select `t`.`id` AS `titleId`,`t`.`mc` AS `titlleName`,`t`.`px` AS `titleSort`,`s`.`id` AS `schoolId`,`s`.`parent_id` AS `schoolPar`,`s`.`name` AS `schoolName`,`s`.`ename` AS `ename`,`s`.`sort` AS `sort`,`s`.`xz` AS `xz`,`s`.`code` AS `code`,`s`.`type` AS `type`,`s`.`grade` AS `grade`,`s`.`logo` AS `logo`,`s`.`bg_picture` AS `bg_picture`,`s`.`home_text` AS `home_text`,`s`.`bottom_text` AS `bottom_text`,`s`.`address` AS `address`,`s`.`m_id` AS `m_id`,`s`.`master` AS `master`,`s`.`zip_code` AS `zip_code`,`s`.`phone` AS `phone`,`s`.`fax` AS `fax`,`s`.`email` AS `email`,`s`.`patriarch_rule` AS `patriarch_rule`,`s`.`student_rule` AS `student_rule`,`s`.`teacher_rule` AS `teacher_rule`,`s`.`short_flag` AS `short_flag`,`s`.`deploy_url` AS `deploy_url`,`s`.`userable` AS `userable`,`s`.`primary_persion` AS `primary_persion`,`s`.`deputy_persion` AS `deputy_persion`,`s`.`create_by` AS `create_by`,`s`.`create_date` AS `create_date`,`s`.`update_by` AS `update_by`,`s`.`update_date` AS `update_date`,`s`.`remarks` AS `remarks`,`s`.`del_flag` AS `del_flag` from (`org_title` `t` left join `org_school` `s` on(((`t`.`school_id` = `s`.`id`) and (`t`.`del_flag` = 0))))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
