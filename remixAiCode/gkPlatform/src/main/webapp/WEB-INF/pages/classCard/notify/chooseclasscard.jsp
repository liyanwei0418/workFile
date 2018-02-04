<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    form {
        font-size: 13px;
        color: #525252;
    }
    ul{
        border: 1px solid #ddd;
        border-bottom: 0;
        padding-left: 0;
        margin: 0 !important;
        width: 600px;
        margin-left: 80px !important;
        margin-top: 30px !important;
    }
    ul span{
        display: inline-block;
    }
    ul p{
        border-bottom: 1px solid #ddd;
        margin: auto !important;
        padding: 10px 10px;
    }
    input[type='checkbox']{
        position: relative;
        top:2px;
    }
    #xx_all, #cz_all, #gz_all{
        background: #eee;
    }
    .s-class{
        padding: 0 15px 0 0;
    }
    .s-class i{
        font-style: normal;
    }
    .xx, .cz, .gz{
        background: rgba(191, 191, 191, .1);
    }
</style>
<body>
<div>
    <form action="${ctx}/teach/task/course/class/add?courseId=${courseId}" method="post" class="courseClass">
        <ul id="ClassCardUl">
            <%--<p style="font-size: 15px;background: #eee"><input type="checkbox">全选</p>--%>
            <p id="xx_all" class="" style="display: none;">小学<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' class="xx_check_all"><span class="school-type">全选</span></p>
            <p id="xx_1" class="xx" style="display: none;">一年级 <input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_1' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>

            <p id="xx_2" class="xx" style="display: none;">二年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_2' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>

            <p id="xx_3" class="xx" style="display: none;">三年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_3' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>

            <p id="xx_4" class="xx" style="display: none;">四年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_4' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>

            <p id="xx_5" class="xx" style="display: none;">五年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_5' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>

            <p id="xx_6" class="xx" style="display: none;">六年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='xx_6' class="nj_xx_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="xx_check" style="display: none;"></p>


            <p id="cz_all" class="" style="display: none;">初中<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' class="cz_check_all"><span class="school-type">全选</span></p>
            <p id="cz_1" class="cz" style="display: none;">一年级<input type="checkbox" ${disabled} value=""style='margin-left: 5px;' data-flg='cz_1' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>

            <p id="cz_2" class="cz" style="display: none;">二年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='cz_2' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>

            <p id="cz_3" class="cz" style="display: none;">三年级<input type="checkbox" ${disabled} value=""  style='margin-left: 5px;' data-flg='cz_3' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>

            <p id="cz_4" class="cz" style="display: none;">四年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='cz_4' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>

            <p id="cz_5" class="cz" style="display: none;">五年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='cz_5' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>

            <p id="cz_6" class="cz" style="display: none;">六年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='cz_6' class="nj_cz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="cz_check" style="display: none;"></p>


            <p id="gz_all" class="" style="display: none;">高中<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' class="gz_check_all nj_all_check"><span class="school-type">全选</span></p>
            <p id="gz_1" class="gz" style="display: none;">一年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='gz_1' class="nj_gz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="gz_check" style="display: none;"></p>

            <p id="gz_2" class="gz" style="display: none;">二年级<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' data-flg='gz_2' class="nj_gz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="gz_check" style="display: none;"></p>

            <p id="gz_3" class="gz" style="display: none;">三年级<input type="checkbox" ${disabled}value="" style='margin-left: 5px;' data-flg='gz_3' class="nj_gz_check nj_all_check"><span class="school-type">全选</span></p>
            <p class="gz_check" style="display: none;"></p>

            <p id="qt_all" class="" style="display: none;">其他<input type="checkbox" ${disabled} value="" style='margin-left: 5px;' class="qt_check_all"><span class="school-type">全选</span></p>
            <p class="qt_check" style="display: none;"></p>
        </ul>
    </form>
</div>
</body>
<script>

    //回显选中设备
    function isChecked(id,checkedIds) {
        var _checkedStr=checkedIds.split(',');
        for(var i=0;i<_checkedStr.length;i++){
            if(id==_checkedStr[i])  return true;
        }
        return false;
    }
   $(function () {
       var _returnData=JSON.parse('${returnData}');
       //console.log(JSON.parse(_returnData)['小学']);
       for(var p in _returnData){//遍历json对象的每个key/value对,p为key
           if(p=='小学'){
               var nj=_returnData[p];
               var xdFlag="xx";
               $('#xx_all').show();
                    for(var q in nj){
                        var njFlag=q;
                        $('.xx').eq(q-1).show();
                        $('.xx_check').eq(q-1).show();
                        var bj=nj[q];
                        for(var r in bj){
                            var html="<span class='s-class'><input data-equipmentName='"+bj[r].equipmentName+"'";
                            var $checkedIds='${checkedIds}';
                            console.log($checkedIds);
                            if(isChecked(bj[r].id,$checkedIds)){
                                html+='checked';
                            }
                            html+=" name='check_equipment' type='checkbox' value='"+bj[r].id+"' class='"+xdFlag+'_'+njFlag+" "+xdFlag+'_all_flag'+"'>" +
                                "<i class='school-type'>"+bj[r].equipmentName+"</i></span>";

                            $('.xx_check').eq(q-1).append(html)
                        }
                    }
           }
           if(p=='初中'){
               var nj=_returnData[p];
               var xdFlag="cz";
               $('#cz_all').show();
               for(var q in nj){
                   var njFlag=q;
                   $('.cz').eq(q-1).show();
                   $('.cz_check').eq(q-1).show();
                   var bj=nj[q];
                   for(var r in bj){
                       var html="<input style='margin-left: 30px;' data-equipmentName='"+bj[r].equipmentName+"'";
                       var $checkedIds='${checkedIds}';
                       if(isChecked(bj[r].id,$checkedIds)){
                           html+='checked';
                       }
                       html+=" name='check_equipment' type='checkbox' value='"+bj[r].id+"' class='"+xdFlag+'_'+njFlag+" "+xdFlag+'_all_flag'+"'>" +
                           "<span class='school-type'>"+bj[r].equipmentName+"</span>";
                       $('.cz_check').eq(q-1).append(html)
                   }
               }
           }
           if(p=='高中'){
               var nj=_returnData[p];
               var xdFlag="gz";
               $('#gz_all').show();
               for(var q in nj){
                   var njFlag=q;
                   $('.gz').eq(q-1).show();
                   $('.gz_check').eq(q-1).show();
                   var bj=nj[q];
                   for(var r in bj){
                       var html="<input style='margin-left: 30px;' data-equipmentName='"+bj[r].equipmentName+"'";
                       var $checkedIds='${checkedIds}';
                       if(isChecked(bj[r].id,$checkedIds)){
                           html+='checked';
                       }
                       html+=" name='check_equipment' type='checkbox' value='"+bj[r].id+"' class='"+xdFlag+'_'+njFlag+" "+xdFlag+'_all_flag'+"'>" +
                           "<span class='school-type'>"+bj[r].equipmentName+"</span>";
                       $('.gz_check').eq(q-1).append(html)
                   }
               }
           }

           if(p=="null"){
               var nj=_returnData[p];
               var xdFlag="qt";
               $('#qt_all').show();
               for(var q in nj){
                   var njFlag=q;
                   //$('.qt').eq(q-1).show();
                   $('.qt_check').show();
                   var bj=nj[q];
                   for(var r in bj){
                       var html="<input style='margin-left: 30px;' data-equipmentName='"+bj[r].equipmentName+"'";
                       var $checkedIds='${checkedIds}';
                       if(isChecked(bj[r].id,$checkedIds)){
                           html+='checked';
                       }
                       html+=" name='check_equipment' type='checkbox' value='"+bj[r].id+"' class='"+xdFlag+'_'+njFlag+" "+xdFlag+'_all_flag'+"'>" +
                           "<span class='school-type'>"+bj[r].equipmentName+"</span>";
                       $('.qt_check').append(html)
                   }
               }
           }
       }
   })

//学段全选
    $(".xx_check_all").on("click", function () {
        if (this.checked == true) {
            $(".xx_all_flag").prop("checked", true);
            $(".nj_xx_check").prop("checked", true);
        } else {
            $(".xx_all_flag").prop("checked", false);
            $(".nj_xx_check").prop("checked", false);
        }
    });


    $(".cz_check_all").on("click", function () {
        if (this.checked == true) {
            $(".cz_all_flag").prop("checked", true);
            $(".nj_cz_check").prop("checked", true);
        } else {
            $(".cz_all_flag").prop("checked", false);
            $(".nj_cz_check").prop("checked", false);
        }
    });

   $(".gz_check_all").on("click", function () {
       if (this.checked == true) {
           $(".gz_all_flag").prop("checked", true);
           $(".nj_gz_check").prop("checked", true);
       } else {
           $(".gz_all_flag").prop("checked", false);
           $(".nj_gz_check").prop("checked", false);
       }
   });
    $(".qt_check_all").on("click", function () {
        if (this.checked == true) {
            $(".qt_all_flag").prop("checked", true);
        } else {
            $(".qt_all_flag").prop("checked", false);
        }
    });


    //年级全选
   $(".nj_all_check").on('click',function () {
       var $checkedNj="."+$(this).data('flg');
       if(this.checked==true){
           $($checkedNj).prop("checked", true);
       }else{
           $($checkedNj).prop("checked", false);
       }
   })

   var checkedIds = "";
   var unCheckedIds = "";
   var checkedName="";
   function getChecked() {
       $('input[name="check_equipment"]').each(function (i) {
           if ($(this).attr("checked")) {
               checkedIds += $(this).val()+",";
               checkedName += $(this).data('equipmentname')+",";
           } else {
               unCheckedIds += $(this).val()+",";
           }
       });
   }
   function doSubmit(){ //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
       getChecked();
       window.parent.chooseResult(checkedIds,unCheckedIds,checkedName);//调用父级frame的js方法将选择的值传递给父级页面
       window.parent.closeAlertDiv();
       return false;//防止顶层页面刷新
   }
</script>
</html>
