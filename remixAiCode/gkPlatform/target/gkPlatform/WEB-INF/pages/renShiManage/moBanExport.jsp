<%@ include file="../common/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <%--<script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>--%>
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

        .stuAndPar-msg {
            color: #54AB37;
            font-size: 14px;
            padding: 1px 9px;
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
            font-size: 13px;
        }

        form input[type='text'], form select {
            height: 28px !important;
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 class="stuAndPar-msg">教师信息</h3>
    <span style="margin-top:10px;">
            <input type="checkbox" id="allStu"/>
            <label for="allStu">全选</label>
        </span>
    <div>
        <form action="${ctx}/renshi/export" method="post">

            <input id="teachers" name="teachers" value="${teachers}" hidden>
            <input id="chooseSchoolId" name="chooseSchoolId" value="${chooseSchoolId}" hidden>
            <input id="teacherName" name="teacherName" value="${teacherName}" hidden>
            <span>
                <input type="checkbox" name="stu1" id="stu1" value="姓名" class="defultMsg" checked="checked" disabled/>
                <label for="stu1">姓名</label>
            </span>
            <span>
                <input type="checkbox" id="stu27" value="职工编号" class="stuMsg"/>
                <label for="stu27">职工编号</label>
            </span>
            <span>
                <input type="checkbox" id="stu2" value="手机号码" class="stuMsg"/>
                <label for="stu2">手机号码</label>
            </span>
            <span>
                <input type="checkbox" id="stu3" value="邮箱" class="stuMsg"/>
                <label for="stu3">邮箱</label>
            </span>
            <span>
                <input type="checkbox" id="stu4" value="身份证号码" class="stuMsg"/>
                <label for="stu4">身份证号码</label>
            </span>
            <span>
                <input type="checkbox" id="stu5" value="性别" class="stuMsg"/>
                <label for="stu5">性别</label>
            </span>
            <span>
                <input type="checkbox" id="stu6" value="开始工作时间" class="stuMsg"/>
                <label for="stu6">开始工作时间</label>
            </span>
            <%-- <span>
                   <input type="checkbox" id="stu7" value="职务" class="stuMsg"/>
                   <label for="stu7">职务</label>
               </span>--%>
            <span>
                <input type="checkbox" id="stu8" value="最高毕业时间" class="stuMsg"/>
                <label for="stu8">最高毕业时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu9" value="最高专业" class="stuMsg"/>
                <label for="stu9">最高专业</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu10" value="评职详细" class="stuMsg"/>
                <label for="stu10">评职详细</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu11" value="家庭住址详细" class="stuMsg"/>
                <label for="stu11">家庭住址详细</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu12" value="骨干教师级别" class="stuMsg"/>
                <label for="stu12">骨干教师级别</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu13" value="合同开始时间" class="stuMsg"/>
                <label for="stu13">合同开始时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu14" value="曾用名" class="stuMsg"/>
                <label for="stu14">曾用名</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu15" value="家庭邮编" class="stuMsg"/>
                <label for="stu15">家庭邮编</label>
            </span>
            <span>
                <input type="checkbox" id="stu16" value="是否专任教师" class="stuMsg"/>
                <label for="stu16">是否专任教师</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu17" value="身份" class="stuMsg"/>
                <label for="stu17">身份</label>
            </span>
            <span>
                <input type="checkbox" id="stu18" value="外语水平" class="stuMsg"/>
                <label for="stu18">外语水平</label>
            </span>
            <span>
                <input type="checkbox" id="stu19" value="最高学制" class="stuMsg"/>
                <label for="stu19">最高学制</label>
            </span>
            <span>
                <input type="checkbox" id="stu20" value="学位数量" class="stuMsg"/>
                <label for="stu20">学位数量</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu21" value="任教学科级别" class="stuMsg"/>
                <label for="stu21">任教学科级别</label>
            </span>
            <span>
                <input type="checkbox" id="stu22" value="校区" class="stuMsg"/>
                <label for="stu22">校区</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu23" value="薪资" class="stuMsg"/>
                <label for="stu23">薪资</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu24" value="岗位分类副" class="stuMsg"/>
                <label for="stu24">岗位分类副</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu25" value="籍贯" class="stuMsg"/>
                <label for="stu25">籍贯</label>
            </span>
            <span>
                <input type="checkbox" id="stu26" value="政治面貌" class="stuMsg"/>
                <label for="stu26">政治面貌</label>
            </span>
            <span>
                <input type="checkbox" id="stu28" value="原始毕业时间" class="stuMsg"/>
                <label for="stu28">原始毕业时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu29" value="最高学历" class="stuMsg"/>
                <label for="stu29">最高学历</label>
            </span>
            <span>
                <input type="checkbox" id="stu30" value="最高毕业学校" class="stuMsg"/>
                <label for="stu30">最高毕业学校</label>
            </span>
            <span>
                <input type="checkbox" id="stu31" value="原专业" class="stuMsg"/>
                <label for="stu31">原专业</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu32" value="评职时间" class="stuMsg"/>
                <label for="stu32">评职时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu33" value="来我校时间" class="stuMsg"/>
                <label for="stu33">来我校时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu34" value="住宅电话" class="stuMsg"/>
                <label for="stu34">住宅电话</label>
            </span>
            <span>
                <input type="checkbox" id="stu35" value="工资岗位" class="stuMsg"/>
                <label for="stu35">工资岗位</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu36" value="合同结束时间" class="stuMsg"/>
                <label for="stu36">合同结束时间</label>
            </span>
            <span>
                <input type="checkbox" id="stu37" value="办公室电话" class="stuMsg"/>
                <label for="stu37">办公室电话</label>
            </span>
            <span>
                <input type="checkbox" id="stu38" value="是否华侨" class="stuMsg"/>
                <label for="stu38">是否华侨</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu39" value="是否班主任" class="stuMsg"/>
                <label for="stu39">是否班主任</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu40" value="外语语种" class="stuMsg"/>
                <label for="stu40">外语语种</label>
            </span>
           <%-- <span>
                <input type="checkbox" id="stu41" value="是否班主任" class="stuMsg"/>
                <label for="stu41">是否班主任</label>
            </span>--%>
            <span>
                <input type="checkbox" id="stu42" value="原学制" class="stuMsg"/>
                <label for="stu42">原学制</label>
            </span>
            <span>
                <input type="checkbox" id="stu43" value="最高学位" class="stuMsg"/>
                <label for="stu43">最高学位</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu44" value="专业技术岗位分类" class="stuMsg"/>
                <label for="stu44">专业技术岗位分类</label>
            </span>
            <span>
                <input type="checkbox" id="stu45" value="任教学科" class="stuMsg"/>
                <label for="stu45">任教学科</label>
            </span>
            </shiro:hasRole>

            <span>
                <input type="checkbox" id="stu46" value="实职级别" class="stuMsg"/>
                <label for="stu46">实职级别</label>
            </span>

            <shiro:hasRole name="common">
            <span>
                <input type="checkbox" id="stu47" value="工资岗位副" class="stuMsg"/>
                <label for="stu47">工资岗位副</label>
            </span>
            </shiro:hasRole>
        </form>
    </div>
</div>

<script>
    function doSubmit() {

        var teachers = $("#teachers").val();

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


        var finalmsg = defultTemp;
        if (stuTemp != "") {
            finalmsg += ("," + stuTemp);
        }

        <%--window.location.href = "${ctx}/renshi/export?id=" + teachers + "&header=" + encodeURI(encodeURI(finalmsg));--%>
        <%--$.post("${ctx}/renshi/export", {--%>
        <%--id: teachers,--%>
        <%--header: finalmsg--%>
        <%--});--%>
        //window.location.href = "${ctx}/renshi/export?id=" + teachers + "&header=" + encodeURI(encodeURI(finalmsg));
        $("form").attr("action", "${ctx}/renshi/export?header=" + encodeURI(encodeURI(finalmsg)));
        $("form").submit();
        // window.parent.layer.closeAll();
    }


    var allStu = $('#allStu');
    var normalStu = $('.stuMsg');
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


</script>
</body>
</html>