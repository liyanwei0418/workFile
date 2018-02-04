<%@ include file="../../common/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title></title>
    <style>
        body {
            font-size: 13px;
            font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
        }

        .container {
            width: 400px;
            margin-top: 20px;
        }
        td{
            font-size: 13px;
            text-align: right;
            padding: 8px 0;
        }
        input[type="text"], select {
            width: 150px;
            height: 28px;
            line-height: 28px;
            margin-left: 12px;
            padding-left: 10px;
            outline: none;
            border: 1px solid #dadada;
            border-radius: 2px;
            color: #333;
        }

        td span, label, .time {
            display: inline-block;
            width: 110px;
            text-align: right;
        }

        a {
            color: #B7B8B8;
            /*margin-top: 10px;*/
        }

        .file-box {
            margin-top: 10px;
        }

        input[type='file']{
            margin-left: 12px;
        }


    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="">
        var isSubmit = false;

//        var form = new FormData($('#inputForm')[0]);
//        form.append("xdId",xdId);
//        form.append("nj",nj);
//        form.append("banji",classId);

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var inputsAdaptWeek = $("input[name='adaptCycleWeekInput']:checked");
            var weekArr = "";
            inputsAdaptWeek.each(function (i) {
                if (i == 0) {
                    weekArr = $(this).val();
                } else {
                    weekArr += "," + $(this).val();
                }
            })

            if (weekArr == "") {
                layer.msg("请选择适用教学周");
                return;
            }

            var xdId = $(".xd").find("option:selected").val();
            var nj = $(".nj").find("option:selected").val();
            var classId= $(".banji").find("option:selected").val();

            window.parent.layer.msg('导入中', {icon: 16, shade: 0.5,time:1000000000});//当生成完成这个对话框才被关掉
            if (!isSubmit) {
                $.ajax({
                    url: '${url}'+"?xdId="+xdId+"&nj="+nj+"&banji="+classId+"&cycleId=${cycleId}&weekArr="+weekArr,
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#inputForm')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    window.parent.importCallBack(res);
                }).fail(function (res) {
//                    alert(res.msg);
                    layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 500)
                });
            }else {
                window.parent.layer.closeAll();
                window.parent.layer.msg("表单提交异常,请稍后再试!");
            }
            isSubmit = true;
            return false;
        }


    </script>
</head>
<body>
<div class="container">
    <div>
        <table class="ttTable">
            <tr>
                <td><span>学段:</span>
                    <select name="xd" class="xd">
                        <option>请选择学段</option>
                        <c:forEach items="${classSections}" var="section">
                            <option value="${section.id}">${section.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td><span>年级:</span>
                    <select name="nj" class="nj">
                        <option value="">请选择年级</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td><span>班级:</span>
                    <select name="banji" class="banji">
                        <option value="">请选择班级</option>
                    </select>
                </td>
            </tr>
        </table>
        <span class="adaptweekSpan time" style="vertical-align: top;">适用周期:</span>
            <div style="display: inline-block; width: 240px;">
                <c:forEach items="${integerList}" var="week">
                    <span style="width: 55px;display: inline-block;">
                        <input type="checkbox" class="adaptCycleWeekInput" name="adaptCycleWeekInput" value="${week}" style="margin: 0 5px 0 14px; position: relative;">
                        <span class="adaptCycleWeekSpan">${week}</span>
                    </span>
                </c:forEach>
            </div>
    </div>
    <!--目标样式的文件选择框-->
    <form id="inputForm" method="post" enctype="multipart/form-data">
        <div class="file-box">
            <label>请选择导入文件:</label>
            <input type="file" name="file"/>
        </div>
    </form>
</div>

<script>

    var _param = {};

    function determineLocationParam() {
        _param["xdId"] = $(".xd").find("option:selected").val();
        _param["nj"] = $(".nj").find("option:selected").val();
        _param["banji"] = $(".banji").find("option:selected").val();
    }

    $(function () {
        var cycleId = '${cycleId}';
        $(".xd").change(function () {
            $(".nj").empty();
            $(".banji").empty();
            $(".nj").append("<option value=''>请选择年级</option>");
            $(".banji").append("<option value= ''>请选择班级</option>");
            _param["flag"] = "xd";
            determineLocationParam();
            cascade_location(_param, true)
        });
        $(".nj").change(function () {
            $(".banji").empty();
            $(".banji").append("<option value= ''>请选择班级</option>");
            _param["flag"] = "nj";
            determineLocationParam();
            cascade_location(_param, true)
        });
    })

    //位置级联方法
    function cascade_location(param, isclick) {
        $.ajax({
            url: "${ctx}/teach/task/table/import/pop/select/change",
            type: "post",
            data: {
                'mydata': JSON.stringify(param)
            },
            success:function(data){
                console.log(data);
                if(data.code=1){
                    var flg =data.data.flag;
                    var _gradeClasses=data.data.gradeClasses;
                    if(flg == 'xd'){
                        for(var i=0; i<_gradeClasses.length;i++){
                            var html="<option value='"+_gradeClasses[i].nj+"'";
                            if(isclick==false){
                                if(_gradeClasses[i].nj =='${gradeClassExtention.nj}'){
                                    html+="selected";
                                }
                            }
                            html+=">"+_gradeClasses[i].nj +"</option>";
                            $('.nj').append(html);
                        }
                    }else if(flg=='nj'){
                        for(var i=0; i<_gradeClasses.length;i++){
                            var html ="<option value='"+_gradeClasses[i].id+"'";
                            var tmp='${gradeClassExtention.id}'
                            if(isclick==false){
                                if(_gradeClasses[i].id =='${gradeClassExtention.id}'){
                                    html+="selected";
                                }
                            }
                            html+=">"+_gradeClasses[i].name +"</option>";
                            $('.banji').append(html);
//                          "<option value='"+_gradeClasses[i].id+"'>"+_gradeClasses[i].name +"</option>"
                        }
                    }
                }
            },
            error: function (e) {
                layer.msg("暂无相关数据");
            }
        })
    }
</script>
</body>
</html>