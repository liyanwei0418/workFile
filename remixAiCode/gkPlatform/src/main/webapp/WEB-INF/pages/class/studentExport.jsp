<%@ include file="../common/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <style>
        .container {
            width: 820px;
            margin: 0 auto;
            font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
        }

        .container > span {
            margin: 20px 0;
        }

        span {
            display: inline-block;

        }

        .stuAndPar-msg, .stuAndPar-msg {
            color: #54AB37;
            font-size: 14px;
            padding: 1px 6px;
            border-left: 2px solid #54AB37;
        }

        label {
            margin-left: 8px;
            cursor: pointer;
            font-size: 13px;
        }

        .container > div span {
            width: 24%;
            margin-bottom: 20px;
        }
    </style>
</head>
<form method="post" action="${ctx}/class/export">
    <div class="container">
        <input id="students" name="students" value="${students}" hidden>
        <h3 class="stuAndPar-msg">学生信息</h3>
        <span>
            <input type="checkbox" id="allStu"/>
            <label for="allStu">全选</label>
        </span>
        <div>
            <span>
                <input type="checkbox" id="stu1" value="学段" class="defultMsg" checked="checked" disabled/>
                <label for="stu1">学段</label>
            </span>
            <span>
                <input type="checkbox" id="stu27" value="校区" class="defultMsg" checked="checked" disabled/>
                <label for="stu27">校区</label>
            </span>
            <span>
                <input type="checkbox" id="stu2" value="年级" class="defultMsg" checked="checked" disabled/>
                <label for="stu2">年级</label>
            </span>
            <span>
                <input type="checkbox" id="stu3" value="班级" class="defultMsg" checked="checked" disabled/>
                <label for="stu3">班级</label>
            </span>
            <span>
                <input type="checkbox" id="stu4" value="姓名" class="defultMsg" checked="checked" disabled/>
                <label for="stu4">姓名</label>
            </span>
           <%-- <span>
                <input type="checkbox" id="stu5" value="学生学籍号" class="defultMsg" checked="checked" disabled/>
                <label for="stu5">学生学籍号</label>
            </span>--%>
            <span>
                <input type="checkbox" id="stu6" value="性别" class="stuMsg"/>
                <label for="stu6">性别</label>
            </span>
            <span>
                <input type="checkbox" id="stu7" value="有效证件类型" class="stuMsg"/>
                <label for="stu7">有效证件类型</label>
            </span>
            <span>
                <input type="checkbox" id="stu8" value="有效证件号" class="stuMsg"/>
                <label for="stu8">有效证件号</label>
            </span>
            <span>
                <input type="checkbox" id="stu9" value="学籍号" class="stuMsg"/>
                <label for="stu9">学籍号</label>
            </span>
            <span>
                <input type="checkbox" id="stu10" value="教育Id号" class="stuMsg"/>
                <label for="stu10">教育Id号</label>
            </span>
          <%--  <span>
                <input type="checkbox" id="stu11" value="全国学籍号" class="stuMsg"/>
                <label for="stu11">全国学籍号</label>
            </span>--%>
            <span>
                <input type="checkbox" id="stu12" value="拼音" class="stuMsg"/>
                <label for="stu12">拼音</label>
            </span>
            <span>
                <input type="checkbox" id="stu13" value="入学年度" class="stuMsg"/>
                <label for="stu13">入学年度</label>
            </span>
            <span>
                <input type="checkbox" id="stu14" value="学生类别" class="stuMsg"/>
                <label for="stu14">学生类别</label>
            </span>
            <span>
                <input type="checkbox" id="stu15" value="国别" class="stuMsg"/>
                <label for="stu15">国别</label>
            </span>
            <span>
                <input type="checkbox" id="stu16" value="民族" class="stuMsg"/>
                <label for="stu16">民族</label>
            </span>
            <span>
                <input type="checkbox" id="stu17" value="籍贯" class="stuMsg"/>
                <label for="stu17">籍贯</label>
            </span>
            <span>
                <input type="checkbox" id="stu18" value="政治面貌" class="stuMsg"/>
                <label for="stu18">政治面貌</label>
            </span>
            <span>
                <input type="checkbox" id="stu19" value="在校状态" class="stuMsg"/>
                <label for="stu19">在校状态</label>
            </span>
            <span>
                <input type="checkbox" id="stu20" value="现居住地" class="stuMsg"/>
                <label for="stu20">现居住地</label>
            </span>
            <span>
                <input type="checkbox" id="stu21" value="户口性质" class="stuMsg"/>
                <label for="stu21">户口性质</label>
            </span>
            <span>
                <input type="checkbox" id="stu22" value="来源地区" class="stuMsg"/>
                <label for="stu22">来源地区</label>
            </span>
            <span>
                <input type="checkbox" id="stu23" value="户口所在地" class="stuMsg"/>
                <label for="stu23">户口所在地</label>
            </span>
            <span>
                <input type="checkbox" id="stu24" value="是否按照本地户口对待" class="stuMsg"/>
                <label for="stu24">是否按照本地户口对待</label>
            </span>
            <span>
                <input type="checkbox" id="stu25" value="出生日期" class="stuMsg"/>
                <label for="stu25">出生日期</label>
            </span>
            <span>
                <input type="checkbox" id="stu26" value="招生类别" class="stuMsg"/>
                <label for="stu26">招生类别</label>
            </span>
        </div>
        <h3 class="stuAndPar-msg">
            学生家长信息
        </h3>
        <span style="margin-top: 0px;margin-bottom: 0px;padding-left:8px;font-size:14px;">父亲</span>
        <br/>
        <span>
                <input type="checkbox" id="allFa"/>
                <label for="allFa">全选</label>
            </span>
        <div>

        <span>
                <input type="checkbox" id="par2" value="父亲姓名" class="faMsg"/>
                <label for="par2">姓名</label>
            </span>
            <span>
                <input type="checkbox" id="par3" value="父亲联系电话" class="faMsg"/>
                <label for="par3">联系电话</label>
            </span>
            <span>
                <input type="checkbox" id="par4" value="父亲职务或职业" class="faMsg"/>
                <label for="par4">职务或职业</label>
            </span>
            <span>
                <input type="checkbox" id="par5" value="父亲工作单位" class="faMsg"/>
                <label for="par5">工作单位</label>
            </span>
        </div>
        <span style="margin-top: 0px;margin-bottom: 0px;padding-left:8px;font-size:14px;">母亲</span>
        <br/>
        <span>
                <input type="checkbox" id="allMa"/>
                <label for="allMa">全选</label>
            </span>

        <div>

        <span>
                <input type="checkbox" id="par7" value="母亲姓名" class="maMsg"/>
                <label for="par7">姓名</label>
            </span>
            <span>
                <input type="checkbox" id="par8" value="母亲联系电话" class="maMsg"/>
                <label for="par8">联系电话</label>
            </span>
            <span>
                <input type="checkbox" id="par9" value="母亲职务或职业" class="maMsg"/>
                <label for="par9">职务或职业</label>
            </span>
            <span>
                <input type="checkbox" id="par10" value="母亲工作单位" class="maMsg"/>
                <label for="par10">工作单位</label>
            </span>
        </div>
    </div>
</form>
<script>
    function doSubmit() {

        var students = $("#students").val();

        var defultTemp = "";
        $('input:checkbox[class=defultMsg]:checked').each(function (i) {
            if (0 == i) {
                defultTemp = $(this).val();
            } else {
                defultTemp += ("," + $(this).val());
            }
        });

        var stuTemp = "";
        $('input:checkbox[class=stuMsg]:checked').each(function (i) {
            if (0 == i) {
                stuTemp = $(this).val();
            } else {
                stuTemp += ("," + $(this).val());
            }
        });

        var faTemp = "";
        $('input:checkbox[class=faMsg]:checked').each(function (i) {
            if (0 == i) {
                faTemp = $(this).val();
            } else {
                faTemp += ("," + $(this).val());
            }
        });

        var maTemp = "";
        $('input:checkbox[class=maMsg]:checked').each(function (i) {
            if (0 == i) {
                maTemp = $(this).val();
            } else {
                maTemp += ("," + $(this).val());
            }
        });

        var finalmsg = defultTemp;
        if (stuTemp != "") {
            finalmsg += ("," + stuTemp);
        }
        if (faTemp != "") {
            finalmsg += ("," + faTemp);
        }
        if (maTemp != "") {
            finalmsg += ("," + maTemp);
        }
        <%--window.location.href = "${ctx}/class/export?students=" + students + "&header=" + encodeURI(encodeURI(finalmsg));--%>
        $("form").attr("action", "${ctx}/class/export?header=" + encodeURI(encodeURI(finalmsg)));
        $("form").submit();
    }


    var allStu = $('#allStu');
    var normalStu = $('.stuMsg');
    console.log(allStu.checked);
    $(allStu).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalStu.length; i++) {
                normalStu[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalStu.length; i++) {
                normalStu[i].checked = false;
            }
        }
    });

    var allFa = $('#allFa');
    var normalFa = $('.faMsg');
    console.log(allFa.checked);
    $(allFa).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalFa.length; i++) {
                normalFa[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalFa.length; i++) {
                normalFa[i].checked = false;
            }
        }
    })

    var allMa = $('#allMa');
    var normalMa = $('.maMsg');
    console.log(allMa.checked);
    $(allMa).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalMa.length; i++) {
                normalMa[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalMa.length; i++) {
                normalMa[i].checked = false;
            }
        }
    });

</script>
</body>
</html>