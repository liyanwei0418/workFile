<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'microsoft YaHei';
        }

        .clearfix:after {
            content: '';
            display: block;
            clear: both;
        }

        .container {
            width: 700px;
            height: 550px;
            margin: 0 auto;
            border: 1px solid #ddd;
        }

        aside, section {
            height: 100%;
        }

        aside {
            border-right: 1px solid #ddd;
            float: left;
            padding: 28px 20px;
            width: 50%;
        }

        section {
            float: right;
        }

        aside > header {
            overflow: hidden;
            position: relative;
        }

        aside > header i {
            cursor: pointer;
            display: none;
            position: absolute;
            width: 11px;
            height: 10px;
            right: 43px;
            top: 11px;
            background: url(${ctxStaticNew}/images/icon-c-2.png) no-repeat center center;
        }

        aside > header > span {
            font-size: 14px;
            color: #525252;
            line-height: 30px;
        }

        aside > header > input[type=text] {
            padding-left: 5px;
            padding-right: 23px;
            float: right;
            height: 30px;
            width: 222px;
            border: 1px solid #999;
            border-right: none;
        }

        aside > header > button {
            cursor: pointer;
            float: right;
            border: 1px solid #54ab37;
            width: 37px;
            height: 30px;
            background: #54ab37;
            border-top-right-radius: 3px;
            border-bottom-right-radius: 3px;
        }

        aside > header > button > span {
            display: inline-block;
            width: 17px;
            height: 28px;
            background: url(${ctxStaticNew}/images/icon-r.png) no-repeat center center;
        }

        aside > div {
            max-height: 450px;
            overflow-y: scroll;
            background: #f8f8f8;
            margin-top: 25px;
        }

        aside > div > table {
            text-align: center;
            font-size: 14px;
            width: 100%;
            border-collapse: collapse
        }

        aside > div > table th {
            color: #333;
            font-weight: 600;
            padding: 20px 0;
            border-bottom: 1px solid #aaa;
        }

        aside > div > table td {
            color: #525252;
            padding: 10px 0;
        }

        section {
            float: left;
            padding: 28px 20px;
            width: 50%;
        }

        section > header {
            padding-bottom: 15px;
            color: #525252;
            font-size: 14px;
            overflow: hidden;
            border-bottom: 1px solid #ddd;
        }

        section > header span {
            float: right;
            color: #fa2250;
            font-size: 12px;
            cursor: pointer;
            padding-left: 21px;
            background: url(${ctxStaticNew}/images/icon-d.png) no-repeat left center;
        }

        section > div {
            max-height: 450px;
            overflow-y: scroll;
        }

        section > div > ul {
            list-style: none;
            padding: 0;
            overflow: hidden;
        }

        section > div > ul > li {
            padding-right: 15px;
            cursor: pointer;
            margin-right: 15px;
            float: left;
            font-size: 14px;
            color: #333;
            margin-bottom: 15px;
            background: url(${ctxStaticNew}/images/icon-c-3.png) no-repeat right center;
        }

        section > div > ul > li:hover {
            background: url(${ctxStaticNew}/images/icon-c-1.png) no-repeat right center;
        }
    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
</head>
<body>
<%--${departId}--%>
<main class="container clearfix">
    <aside>
        <header>
            <span>姓名</span>
            <button onclick="queryUser()"><span></span></button>
            <input type="text" class="addName"/>
            <i class="clearName"></i>
        </header>
        <div>
            <table id="personTable">
                <tbody>
                <%--<tr>
                    <th width="13%"><input class="allCheck" type="checkbox"/></th>
                    <th width="20%">姓名</th>
                    <th width="67%">教职工编号</th>
                </tr>--%>
                <%-- <tr>
                     <td><input class="contentCheck" id="1" type="checkbox"/></td>
                     <td>王思彤1</td>
                     <td>HZS-456789</td>
                 </tr>--%>
                </tbody>
            </table>
        </div>
    </aside>
    <section>
        <input hidden name="departmentId" value="${departId}"/>
        <header>
            已选人员
            <span class="clearAll" onclick="clearAll()">全部清空</span>
        </header>
        <div>
            <ul class="clearItem">
            </ul>
            <%--<input type="button" value="获取数据" onclick="getChecked()"/>--%>
        </div>
    </section>
</main>
<script>

    //判断数组是否包含某元素
    Array.prototype.contains = function (needle) {
        for (i in this) {
            if (this[i] == needle) return true;
        }
        return false;
    }

    $(function () {
        getData("");
    });

    function queryUser() {
        var name = $(".addName").val();
        getData(name);
    }
    //左侧选择到右边
    $('body').on("change", "input[type=checkbox]", function () {
        moveToRight(this);
    })


    function moveToRight(obj) {
        var id = $(obj).attr("id");
        var name = $(obj).parent().next().html();
        var className = $(obj).attr("class");

        //若当前项已经添加过，去重
        var aa = $("li[name=" + id + "]").remove();


        var str = "<li onclick='clearSelf(this)' name=" + id + ">" + name + "</li>"

        if ("allCheck" != className) {
            if (obj.checked == true) {
                $(".clearItem").append(str);
            } else {
                clearSelf("li[name=" + id + "]");
            }
        } else {
            //全选
            $(".clearItem").html("");
            $('.contentCheck').each(function (i, ele) {
                moveToRight(ele);
            })
        }

    }

    //end  左右选择


    $('.addName').focus(function () {
        $('.clearName').show();
    })

    $('.clearName').click(function () {
        $(this).hide();
        $(this).siblings('input').val('');
        getData("");
    })


    $('body').on("click", "table th input", function () {
        var tds = $('table td input[type=checkbox]');
        var me = this;
        $(tds).each(function (i, e) {
            if (me.checked == true) {
                e.checked = true;
            } else {
                e.checked = false;
            }
        })
    })

    function clearAll() {
        $('.clearItem').children().remove();
        $('input[type=checkbox]').attr("checked", false);
    }

    function clearSelf(obj) {
        $(obj).remove();
        var id = $(obj).attr("name");
        $("#" + id).attr("checked", false);
    }


    var inner = "";
    function getData(name) {
        inner = '<tr><th width="13%"><input class="allCheck" type="checkbox"/></th><th width="20%">姓名</th> <th width="67%">教职工编号</th></tr>';
        $.ajax({
            type: "POST",
            url: "${ctx}/renshi/getAllTeacher",
            data: {
                name: name,
                type: 1
            },
            dataType: "json",
            success: function (data) {
                var arr = getCheckedArray();
                for (var i = 0; i < data.length; i++) {
                    var username = data[i].name;
                    var userid = data[i].id;
                    var userno = data[i].no;

                    if (typeof (userno) == "undefined") {
                        userno = "";
                    }
                    if (arr.contains(username)) {
                        inner += '<tr><td><input class="contentCheck" id="' + userid + '" checked type="checkbox"/></td><td>' + username + '</td><td>' + userno + '</td></tr>';
                    } else {
                        inner += '<tr><td><input class="contentCheck" id="' + userid + '" type="checkbox"/></td><td>' + username + '</td><td>' + userno + '</td></tr>';
                    }
                }
                $("#personTable tbody").html(inner);
            },
            error: function (e) {
                layer.msg("暂无相关数据");
                inner = '<tr><th width="13%"><input class="allCheck" type="checkbox"/></th><th width="20%">姓名</th> <th width="67%">教职工编号</th></tr>';
                $("#personTable tbody").html(inner);
            }
        });
    }

    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        layer.closeAll();
        layer.msg('添加中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
        var teacherId = getChecked();
        $.post("${ctx}/renshi/bumen/teacher/save", {
            departmentId: $("input[name='departmentId']").val(),
            teacherId: teacherId
        }, function (retJson) {
            layer.msg(retJson.msg, {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                if (retJson.code == -1){
                    return false;
                }else {
                    window.parent.location.reload(true);
                } 
            });
        });


    }

    function getChecked() {
        var checkedData = $(".clearItem li");
        var res = "";
        for (var i = 0; i < checkedData.length; i++) {
            if (res == "") {
                res = $(checkedData[i]).attr("name") + ",";
            } else {
                res += $(checkedData[i]).attr("name") + ",";
            }
        }
        return res;
    }

    function getCheckedArray() {
        var checkedData = $(".clearItem li");
        var arr = Array();
        for (var i = 0; i < checkedData.length; i++) {
            arr[arr.length] = $(checkedData[i]).html();
        }
        return arr;
    }

</script>
</body>
</html>