<%@ include file="../common/common.jsp" %>
<%@ include file="../common/headerMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>个人空间-学生</title>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/main.js"></script>
</head>

<body>
<div class="longHeader">
</div>
<div class="neiNian1">
    <div class="neiNian2">
        <div class="headerMenu">
            <div class="menuLeft">
                <table class="menuLeftTable" cellpadding=13px;>
                    <tr class="tableChange">

                        <td>
                            <img src="${ctxStatic}/image/logo.png">
                        </td>
                        <td style="width: 100px;text-align: center;background: #21A68B;border-bottom: 4px #FBB963 solid;">
                            <a class="doudong"> 首页 </a>
                        </td>
                    </tr>
                </table>
                <div class="menuRight">
                    <img id="pengyqShow" src="${ctxStatic}/image/rightDiv.png">
                </div>
            </div>

        </div>
    </div>
</div>
<div class="mainContain">
    <div class="forBeauty">
        <!--个人信息模块-->
        <div class="mainClass" id="studentInfo">
            <div class="neiNian3">
                上次登录时间:<label>2012年4月23日</label>
            </div>
            <div class="neiNian4">
                <img src="${ctxStatic}/image/headPic.png">
            </div>
            <div class="neiNian5">
                <p class="neiNian6">赵慧子&nbsp;<input type="button" value="学生" style="background-color:#FB6873 ;"
                                                    class="neiNian7"/>
                <p class="neiNian8">哈尔滨市第三中学</p>
                </p>
            </div>
            <br>
            <div class="neiNian9">
                <a class="special">43</a> 关注 &nbsp;| <a class="special">63</a> 粉丝 | &nbsp;<a class="special">79</a> 动态
            </div>

            <div style="position:absolute;width: 280px;height:255px;border-top: 1px #d2d2ca solid;margin-top: 28px;">
                <ul style="list-style: none;padding-left: 18px;padding-right: 18px;margin: 0;">
                    <li style="line-height: 45px;font-size: 14px;color: #333333;height: 18%"><span
                            class="devideVer"></span>&nbsp;我的班级
                    </li>
                    <li style="line-height: 27px;font-size: 14px;color: #565656;height: 18%">
                        &nbsp;&nbsp;小学三年级四班<a style="line-height: 27px;float: right;font-size: 12px;color: #999999;">班级编号：8374JIUY</a>
                    </li>
                    <li style="font-size: 14px;color: #333333;height: 23%;">
                        <span class="devideVer"></span>&nbsp;我的家长
                        <a style="line-height: 24px;float: right;font-size: 12px;color: #1ab394;">添加我的家长</a>
                        <br><a style="line-height: 37px;font-size: 12px;color: #999999;">家长准入码：00102340627</a>
                    </li>
                    <li style="height: 35%;">
						<span style="display: inline-block;width: 40%;">
							<img src="${ctxStatic}/image/stu/zhaoshuk.png"/><p>赵树坤</p>
						</span>
                        <span style="display: inline-block;width: 40%;">
							<img src="${ctxStatic}/image/stu/liuyu.png"/><p>&nbsp;刘玉</p>
						</span>
                    </li>
                </ul>
            </div>
            <div style="margin-top:300px;position:absolute;width:280px;height: 244px;border-top: 1px #d2d2ca solid;">
                <ul class="stuPerUl" style="list-style: none;padding-left: 18px;padding-right: 18px;margin: 0;">
                    <li>
						<span>
							作业完成情况
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #565656;">267份&nbsp;&nbsp;</a> 
							<a style="color: #FFAF25;">合格率   89%</a>
						</span>
                    </li>

                    <li>
						<span>
							考试情况
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #565656;">29次&nbsp;&nbsp;</a> 
							<a style="color: #FFAF25;">优秀率   89%</a>
						</span>
                    </li>

                    <li>
						<span>
							学习微课程
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #565656;">36次&nbsp;&nbsp;</a> 
							<a style="color: #FFAF25;">6/24</a>
						</span>
                    </li>

                    <li>
						<span>
							下载资源
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #565656;">19次&nbsp;&nbsp;</a> 
							<a style="color: #FFAF25;">8/16</a>
						</span>
                    </li>

                    <li>
						<span>
							参与讨论
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #565656;">87次&nbsp;&nbsp;</a> 
							<a style="color: #FFAF25;">9/35</a>
						</span>
                    </li>

                    <li>
						<span>
							我的学习豆
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a style="color: #1AB394;">充值&nbsp;&nbsp;</a> 
							<a style="color: #565656;">320个</a>
						</span>
                    </li>

                    <li>
						<span>
							我的经验值
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							<a class="xiaoJi" style="color: #ffffff">&nbsp;36次&nbsp;</a> 
							<a style="color: #FFAF25;">235/500</a>
						</span>
                    </li>
                </ul>
            </div>
        </div>

        <!--今日课程模块-->
        <div class="withLine" id="myCourseStu">
            <div class="withLine1">
                <div class="withLineLeft">今日课程</div>
            </div>
            <div class="todayCourse" style="height: 355px;">
                <ul style="">
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							8:00
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">语文课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							9:00
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">数学课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							10:00
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">实践课</a>
						</span>
                    </li>

                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							11:00
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">数学课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							12:00
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">午休</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							14:30
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">英语课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							15:30
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">体育课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							16:30
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">语文课</a>
						</span>
                    </li>
                    <li>
						<span style="text-align: right;line-height: 34px;float: left;width: 45px;height: 100%;padding-right: 30px;">
							17:30
						</span>

                        <span>
							<img align="absmiddle" style="height: 100%;"
                                 src="${ctxStatic}/image/todayCourseLine.png"/>
							<a style="padding-left: 30px;">手工课</a>
						</span>
                    </li>
                </ul>
            </div>

        </div>

        <!--我的直播课程模块-->
        <div class="withLine" id="myXsZhibo">
            <div class="withLine1">
                <div class="withLineLeft ">我的直播课程</div>
                <div class="withLineRight aForUnderLine neiNian39">进入小豆云课堂
                    <img src="${ctxStatic}/image/dayuhao.png">
                </div>
            </div>
            <div><img class="neiNian40" src="${ctxStatic}/image/course.png"></div>
            <p class="neiNian41Stu">
                <a class="neiNian42">初一暑期课程</a>
                <br><a class="neiNian43">上次授课时间:3-24</a>
            </p>
            <input type="button" class="neiNian44Stu" value="进入直播"/>
        </div>

        <!--通知模块-->
        <div class="withLine" id="publicStu">
            <div class="withLine1">
                <div class="withLineLeft">通知</div>
                <div class="withLineRight aForUnderLine neiNian39">进入通知
                    <img src="${ctxStatic}/image/dayuhao.png">
                </div>
            </div>
            <div class="buttonGroup">
                <input type="button" class="hoverButton" value="课程"/>
                <input type="button" class="hoverButton" value="作业"/>
                <input type="button" class="hoverButton" value="直播课"/>
                <input type="button" class="hoverButton" value="班级通知"/>
                <input type="button" class="hoverButton" value="学校通知"/>
                <input type="button" class="hoverButton" value="应用通知"/>
                <input type="button" class="hoverButton" value="群组通知"/>
                <input type="button" class="hoverButton" value="其他"/>
            </div>
            <div class="withLine2 publicLi neiNian10Stu">
                <ul>
                    <li><p class="green publicText"><a class="normalCss">王启明老师布置了一项主题为“写作练笔”的作业</a><a
                            class="publicTime">5分钟前</a></p></li>
                    <li><p class="orange publicText"><a class="normalCss">王启明老师布置了一项熟读“夸父驻日”的作业</a><a
                            class="publicTime">1个小时前</a></p></li>
                    <li><p class="blue publicText"><a class="normalCss">王启明老师布置了一项练习“女娲补天”课后生词五遍的作业</a><a
                            class="publicTime">2014-1</a></p></li>
                    <li><p class="green publicText"><a class="normalCss">王启明老师布置了一项“8.3”课后习题的作业</a><a
                            class="publicTime">2011-1</a></p></li>
                    <li><p class="green publicText"><a class="normalCss">王启明老师布置了一项预习下节课的作业</a><a class="publicTime">2011-1</a>
                    </p></li>
                    <li><p class="green publicText"><a class="normalCss">王启明老师布置了一项抄写unit6前5段的作业</a><a
                            class="publicTime">2011-1</a></p></li>
                    <li><p class="green publicText"><a class="normalCss">王启明老师布置了一项熟读“女娲补天”的作业</a><a class="publicTime">2011-1</a>
                    </p></li>
                    <li><p class="green publicText"><a class="normalCss">刘月老师布置了一项抄写第六单元生词3遍的作业</a><a
                            class="publicTime">2011-1</a></p></li>
                    <li><p class="orange publicText"><a class="normalCss">李时珍布置了一项参观动物园观后感的作业</a><a class="publicTime">2011-5</a>
                    </p></li>
                    <li><p class="blue publicText"><a class="normalCss">王香香老师布置了一项完成”8.2“课后习题的作业</a><a
                            class="publicTime">1993-6</a></p></li>

                </ul>
            </div>

            <p class="seeMore neiNian11">
                查看更多
            </p>
        </div>


        <!--天气模块-->
        <div class="withLine" id="weatherStu">
            <div class="withLine1">
                <div class="withLineLeft ">今天</div>
                <div class="withLineRight neiNian12">2016年5月23日 星期一</div>
            </div>
            <div class="neiNian13"><img src="${ctxStatic}/image/weather.png"
                                        class="neiNian14"></div>
            <p class="neiNian15">第五教学周</p>
            <p class="neiNian16">北京，晴转多云 空气质量好，适合外出</p>
            <p class="neiNian17">25℃ <br> <a class="neiNian18">26℃/21℃</a></p>
            <div style="bottom: 0;position: absolute;width: 100%;height:34%;background: #fff9df;">
                <ul class="stuPerUl" style="margin: 0;margin-right: 13px;margin-top:10px;">
                    <li style="height: 32px;">
						<span id="">
							距离期中考试5月11日
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							还有<a style="color: #FFA500;">6</a>天
						</span>
                    </li>
                    <li style="height: 32px;">
						<span id="">
							距离运动会5月11日
						</span>
                        <span style="height: 100%;float: right;margin-right: 0;">
							还有<a style="color: #FFA500;">6</a>天
						</span>
                    </li>
                </ul>
            </div>
        </div>

        <!--我的小伙伴模块 -->
        <div class="withLine" id="whatToDoStu">
            <div class="withLine1">
                <div class="withLineLeft ">我的小伙伴</div>
                <div class="withLineRight"></div>
            </div>
            <div class="withLine2 myFriend ">
                <ul style="margin-top: 23px;">
                    <li>
                        <div class="daibanDiv">
                            <img class="headheadPicPic"
                                 src="${ctxStatic}/image/stu/wangmengt.png">
                            <div class="neiNian22Stu">
                                <a class="aForUnderLine neiNian23">王梦瑶</a>
                                <br><a class="neiNian24">小学三年级四班学生</a>
                            </div>

                        </div>
                    </li>
                    <li>
                        <div class="daibanDiv">
                            <img class="headheadPicPic"
                                 src="${ctxStatic}/image/stu/luqi.png">
                            <div class="neiNian22Stu">
                                <a class="aForUnderLine neiNian23">陆奇</a>
                                <br><a class="neiNian24">小学三年级四班学生</a>
                            </div>

                        </div>
                    </li>

                    <li>
                        <div class="daibanDiv">
                            <img class="headheadPicPic"
                                 src="${ctxStatic}/image/stu/lisi.png">
                            <div class="neiNian22Stu">
                                <a class="aForUnderLine neiNian23">李斯</a>
                                <br><a class="neiNian24">小学三年级一班学生</a>
                            </div>

                        </div>
                    </li>

                    <li>
                        <div class="daibanDiv">
                            <img class="headheadPicPic"
                                 src="${ctxStatic}/image/stu/zhanghao.png">
                            <div class="neiNian22Stu">
                                <a class="aForUnderLine neiNian23">张浩博</a>
                                <br><a class="neiNian24">小学三年级四班学生</a>
                            </div>

                        </div>
                    </li>

                    <li>
                        <div class="daibanDiv">
                            <img class="headheadPicPic"
                                 src="${ctxStatic}/image/stu/wangy.png">
                            <div class="neiNian22Stu">
                                <a class="aForUnderLine neiNian23">王颖</a>
                                <br><a class="neiNian24">小学三年级三班学生</a>
                            </div>

                        </div>
                    </li>

                </ul>
            </div>
            <p class="seeMore neiNian25">
                查看更多
            </p>
        </div>


        <!-- 我的群组模块-->
        <div class="withLine" id="myCourseQz">
            <div class="withLine1">
                <div class="withLineLeft">我的群组</div>
                <div class="withLineRight aForUnderLine neiNian36">
                    创建
                </div>
            </div>
            <div class=" withLine2 mycourseLiStu">
                <ul>
                    <li>
                        <div class="neiNian34">
                            <div class="aForUnderLine">三年级四班
                            </div>
                        </div>
                    </li>

                    <li>
                        <div class="neiNian34">
                            <div class="aForUnderLine">开心小伙伴
                            </div>
                        </div>
                    </li>

                    <li>
                        <div class="neiNian34">
                            <div class="aForUnderLine">书法兴趣组
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

        </div>

        <!--我的应用模块-->
        <div class="withLine" id="myAppStu">
            <div class="withLine1">
                <div class="withLineLeft" id="myAppEnter"><a class="neiNian19" href="app_store.html">我的应用</a></div>
                <div class="withLineRight aForUnderLine neiNian39">进入应用商店
                    <img src="${ctxStatic}/image/dayuhao.png">
                </div>
            </div>
            <table class="myAppTable" width="95%" height="75%">
                <tr class="myAppTr">
                    <td width="33%" class="myAppTdStu">
                        <img src="${ctxStatic}/image/stu/xskq.png">
                        <div class="neiNian20">
                            <a class="appTitle">学生考勤</a><br>
                            <a class="appType">学生成长</a><br>
                            <a><img src="${ctxStatic}/image/star.png"></a>
                        </div>
                    </td>
                    <td width="33%" class="myAppTdStu">
                        <img src="${ctxStatic}/image/stu/czda.png">
                        <div class="neiNian20">
                            <a class="appTitle">成长档案</a><br>
                            <a class="appType">学生成长</a><br>
                            <a><img src="${ctxStatic}/image/star.png"></a>
                        </div>
                    </td>
                    <td width="33%" class="myAppTdStu">
                        <img src="${ctxStatic}/image/stu/rcbx.png">
                        <div class="neiNian20">
                            <a class="appTitle">日常表现</a><br>
                            <a class="appType">学生成长</a><br>
                            <a><img src="${ctxStatic}/image/star.png"></a>
                        </div>
                    </td>

                </tr>
                <tr class="myAppTr">

                    <td class="myAppTdStu">
                        <img src="${ctxStatic}/image/stu/xhj.png">
                        <div class="neiNian20">
                            <a class="appTitle">小画家</a><br>
                            <a class="appType">学生成长</a><br>
                            <a><img src="${ctxStatic}/image/star.png"></a>
                        </div>
                    </td>
                    <td class="myAppTdStu">
                        <img src="${ctxStatic}/image/stu/bjsh.png">
                        <div class="neiNian20">
                            <a class="appTitle">班级生活</a><br>
                            <a class="appType">学生成长</a><br>
                            <a><img src="${ctxStatic}/image/star.png"></a>
                        </div>
                    </td>

                </tr>

            </table>
        </div>


    </div>

</div>


<script type="text/javascript">
    window.onload = function () {
        $("#personMessage").hover(function () {
            $("#test").show();
            $("#thisOrange").attr("style", "background:orange;");
        }, function () {
            $("#thisOrange").attr("style", "background:none;");
            $("#test").hide();
        });
    }
</script>

</body>
</html>