教育云开放平台智慧教育云平台  gkPlatform

数据库地址配置文件 db.properties

-----------------------------------------------------------r--------------------------------------------------------------------------------------------
数据库脚本文件
  基础sql:/readme/platform_no_test_data_20170330.sql  （包含平台运行基础数据，未新建测试学校）
  平台管理员账号：root/000000

  //gkPlatform\src\main\resources\db\migrations\V1.0.0__init_data.sql  （包含平台基础数据+测试学校demo）
  平台管理员账号：root/000000
  演示测试账号：admin@demo/000000

-----------------------------------------------------------r--------------------------------------------------------------------------------------------
部署在服务器上：
  1 ：修改web.xml里的，解开注释 （这些是错误页面，否则线上错误会跳到tomcat自带的404  ，500）
    <!-- <error-page>
          <error-code>404</error-code>
          <location>/WEB-INF/pages/404.jsp</location>
      </error-page>

      <error-page>
          <error-code>500</error-code>
          <location>/WEB-INF/pages/500.jsp</location>
      </error-page>-->

   2 : 修改 mybatis-config.xml文件里的 配置，取消sql语句打印输出，注释如下代码
      <settings>
            <setting name="logImpl" value="STDOUT_LOGGING" />
        </settings>

   3 ：在logback.xml的如下节点配置日志级别
     <!--  ALL	各级包括自定义级别
      DEBUG	指定细粒度信息事件是最有用的应用程序调试
      ERROR	错误事件可能仍然允许应用程序继续运行
      FATAL	指定非常严重的错误事件，这可能导致应用程序中止
      INFO	指定能够突出在粗粒度级别的应用程序运行情况的信息的消息
      OFF	这是最高等级，为了关闭日志记录
      TRACE	指定细粒度比DEBUG更低的信息事件
      WARN	指定具有潜在危害的情况-->
   <property name="log.root.level" value="INFO"/> <!-- 日志级别 -->

-----------------------------------------------------------r--------------------------------------------------------------------------------------------
mybatis-generator使用步骤：
    1 ：在mybatis-generator.xml后面添加 如下节点

            <table schema="db.properties里面配置的jdbc.schema" tableName="数据库表名" domainObjectName="实体类别名"
                       enableCountByExample="false"
                       enableUpdateByExample="true"
                       enableDeleteByExample="true"
                       enableSelectByExample="true"
                       selectByExampleQueryId="true">
                    <property name="useActualColumnNames" value="false"/>
                </table>
     2 : 菜单栏：   view --> Tool Windows --> Maven Projects --> 当前项目 Plugins --> mybatis-generator --> 双击即可生成 dao,entity,mapper,mapperxml等文件

------------------------------------------------------------r------------------------------------------------------------------------------------------

数据库增量更新步骤：
    1 : 在 db.properties配置的 jdbc.script 下
    新建文件  Version__sql_name.sql文件，拷贝需要执行的sql语句
    （sql文件命名规则：新建sql文件比已存在的sql文件版本要高。例如存在v1.0.0__sql1.sql,新建文件名得是 v1.0.1__sql2.sql）
    2 : 运行 DBMigrate.java main方法，脚本文件会执行到数据库。

------------------------------------------------------------r------------------------------------------------------------------------------------------

    1 : db.properties   数据库属性配置文件

            jdbc.schema  //schema
            jdbc.script   //数据库脚本文件路径 执行 DBMigrate.java main方法，可以将未同步的数据库脚本执行到指定 schema的数据库
            jdbc.driver  //数据库驱动
            jdbc.url   //数据库链接地址
            jdbc.username  //用户名
            jdbc.password  //密码
            drive.class.path  //数据库jar驱动地址，使用mybatis-generator需要链接数据库，此为驱动jar包路径
            target.project   //target
            model.package   // mybatis-generator 生成的entity类路径
            dao.package    // mybatis-generator 生成的dao类路径
            xml.mapper.package    // mybatis-generator 生成的mapper路径
            vfsroot.windows   //文件上传windows路径
            vfsroot.linux  //文件上传 服务器路径
            develop_path   //项目部署域名后缀

------------------------------------------------------------r------------------------------------------------------------------------------------------
   所有单表的增删改查，不包括批量操作，使用自动生成的类即可完成，不必自己写sql
   demo:

        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
                criteria. //等价于  where 1=1
                andSchoolIdEqualTo("1"). //等价于  and schoolId = 1
                andSchoolIdBetween("1","2"). //等价于  and schoolId between 1 and 2
                andSchoolIdIn(new ArrayList<String>()).  //等价于  and schoolId in (list)
                andSchoolIdIsNull().andSchoolIdLike("%12%")  //等价于  and schoolId like "%12%"

        Student student = new Student();

        studentMapper.deleteByExample(example);  // delete from student where 以上条件
        studentMapper.insert(student);  // insert  所有字段 into student
        studentMapper.insertSelective(student);  //insert  不为空字段 into student
        studentMapper.selectByExample(example);  //select * from student where 以上条件
        studentMapper.updateByExample(example);  //update student set 所有字段  where 以上条件
        studentMapper.updateByExampleSelective(student,example)  //update student set 不为空字段 where 以上条件
        studentMapper.updateByPrimaryKeySelective(student); //update 不为空字段 where 主键 = student.主键
        studentMapper.updateByPrimaryKey(student)  //update 所有字段  where 主键 = student.主键

-----------------------------------------------------------r--------------------------------------------------------------------------------------------
关于权限配置（shiro）
    application-shiro.xml为核心配置文件，cn.gukeer.common.shiro.ShiroAuthRealm为权限Realm类
权限涉及表：sys_user,sys_role,ref_user_role，sys_menu,ref_role_menu

    1 配置 sys_menu的 permission字段，必须遵循：***:***:***  的格式
    具体配置请参照：http://jinnianshilongnian.iteye.com/blog/2018936   http://jinnianshilongnian.iteye.com/blog/2029717
------------------------------------------------------------r------------------------------------------------------------------------------------------

        涉及到多表操作，需要自己写xml的时候， 请参考命名规范   A_****ExtensionMapper.xml  不可手动更改生成的xml中的代码！
------------------------------------------------------------r------------------------------------------------------------------------------------------
数据推送配置：syncData.properties

------------------------------------------------------------r------------------------------------------------------------------------------------------