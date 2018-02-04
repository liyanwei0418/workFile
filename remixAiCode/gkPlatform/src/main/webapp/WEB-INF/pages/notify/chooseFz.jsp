<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link rel="stylesheet"  href="${ctxStaticNew}/css/z-tree-bootStrap.css"/>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/z-treeAll.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <style>

        body{
            font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif

        }
        div.container{

            font-size:14px;
            color:#101010;
            width:300px;
            margin:0 auto;
            margin-top:30px;
        }

    </style>

</head>
<body>
<div class="container" >

    <form action="${ctx}/school/permission/save" id="inputForm" method="post">
        <ul id="tree1"  class="ztree"></ul>
    </form>

</div>

<script type="text/javascript">
    var setting = {
        check: {
            enable: true,
            nocheckInherit: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onCheck:onCheck
        }
    };

    <%--<c:if test="${gukeer:listContainsString(currentRolePermission,menu.permission)}">
    checked:true,
    </c:if>--%>
    //ztree用于初始化的静态数据
    var zNodes = [
        <c:forEach items='${departmentList}' var='department'>
        {id:"${department.id}", pId:"${department.parentId}", name:"${department.name}", nocheck: true},
        </c:forEach>
    ];

    //过滤节点的机制 直接return node表示不做任何过滤
    function filter(node) {
        return node;
    }

    ///动态设置zTree的所有节点有checkbox
    function DynamicUpdateNodeCheck() {

    }

    ///页面加载后初始化zTree数据且默认展开所有节点
    $(document).ready(function () {
        $.fn.zTree.init($("#tree1"), setting, zNodes).expandAll(true);
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        //根据过滤机制获得zTree的所有节点
        var nodes = zTree.getNodesByFilter(filter);
        //遍历每一个节点然后动态更新nocheck属性值
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];
            node.nocheck = false; //表示显示checkbox
            zTree.updateNode(node);
        }
    });
    var depratmentIds="";
    var departmentNames="";
    function onCheck(e,treeId,treeNode){
         depratmentIds="";
         departmentNames="";
        menuString="";
        var treeObj=$.fn.zTree.getZTreeObj("tree1"),
                nodes=treeObj.getCheckedNodes(true)

        for(var i=0;i<nodes.length;i++){
                if(nodes[i].id==-1){

                }else{
                    depratmentIds+=nodes[i].id+ ",";
                    departmentNames+=nodes[i].name+ " , ";
                }

            // alert(nodes[i].id); //获取选中节点的值
        }

    }
    //-->
    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

        window.parent.chooseResult(depratmentIds,departmentNames);//调用父级frame的js方法将选择的值传递给父级页面
        window.parent.closeAlertDiv();
        return false;//防止顶层页面刷新
    }
</script>
</body>
</html>