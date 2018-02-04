<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师网盘</title>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/laydate.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <%--<link rel="stylesheet" href="demoStyle/demo.css" type="text/css">--%>
    <link rel="stylesheet" href="${ctxStaticNew}/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<input type="hidden" value="${repositoryId}" class="repositoryId">
<input type="hidden" value="${token}" class="token">
<input type="hidden" value="${flag}" class="flag">
<div>
    <ul id="folder" class="ztree"></ul>
</div>
</body>
<script>

    $(function () {
        $.fn.zTree.init($("#folder"), setting, zNodes);
    })
    var zTree;
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false,
            showIcon: true,
            txtSelectedEnable: true,
            fontCss: setFontCss
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onClick: clickTree
        }
    };

    var id = '';
    var zNodes = [
        {
            id: "/",
            pId: "/",
            name: "我的文件夹",
            open: true,
        },
        <c:forEach items='${directoryList}' var='oneDirectory'>
        <c:if test="${oneDirectory.parent_dir == '/'}">
        {
            id: "/${oneDirectory.name}",
            pId: "${oneDirectory.parent_dir}",
            name: "${oneDirectory.name}",
            open: false,
        },
        </c:if>
        <c:if test="${oneDirectory.parent_dir !='/'}">
        {
            id: "${oneDirectory.parent_dir}/${oneDirectory.name}",
            pId: "${oneDirectory.parent_dir}",
            name: "${oneDirectory.name}",
            open: false,
        },
        </c:if>
        </c:forEach>

    ];


    function clickTree(event, treeId, treeNode, clickFlag) {
        var zTree = $.fn.zTree.getZTreeObj("folder");
        zTree.expandNode(treeNode);
//        	alert("您点击了：treeId:" + treeId + "   name:" + treeNode.name
//         + treeNode.level + "   tid:" + treeNode.tId + "   parentTId:"
//         + treeNode.parentTId + "   children:" + treeNode.children);

    }


    /*z-tree*/

    <%--$(".node_name").click(function () {--%>

    <%--window.location.href = "${ctx}/notify/index?lanmu=" + $(this).attr("menuId");--%>
    <%--});--%>
    function setFontCss(treeId, treeNode) {
        if (treeNode.id == "${columId}")
            return {
                'padding-top': ' 0',
                'background-color': '#def7f5',
                'color': 'black',
                'height': '25px',
                'opacity': '.8',
                'width': '86%'
            };
        else return {'font-weight': 'normal', color: 'black'};
    }

    function doSubmit(directoryPath, arr) {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        var token = $(".token").val();
        var repositoryId = $(".repositoryId").val();
        var flag = $(".flag").val();
        var title = $(".curSelectedNode").attr("title");
//        alert(title + "=========开始")
        var hierarchy = "";
        var parent_dir = "/";

        var hierarchy = $(".curSelectedNode").parent("li").parent("ul").attr("class").split(" ")[0];
        if (hierarchy != undefined && hierarchy != "") {
            var numStr = hierarchy.substr(hierarchy.length - 1, hierarchy.length);
            var num = Number(numStr);

            var tempUl = $(".curSelectedNode").parent("li").parent("ul").siblings("a");
            var tempTitle = $(".curSelectedNode").parent("li").parent("ul").siblings("a").attr("title");
            if (num > 1) {
                for (var i = 0; i < num - 1; i++) {
                    tempUl = tempUl.parent("li").parent("ul").siblings("a");
                    console.log(tempUl);
                    tempTitle = tempUl.attr("title") + "/" + tempTitle;
                    console.log(tempTitle);
                    if (i == num - 2) {
                        parent_dir = parent_dir + tempTitle + "/" + title;
                    }
                    console.log(parent_dir);
                }
            } else {
                parent_dir = parent_dir + title;
                console.log(parent_dir);
            }
        }
//        alert(tempTitle);
//        alert(parent_dir);
        $.post("${ctx}/net/disk/move", {
            flag: flag,
            parent_dir: encodeURI(parent_dir),
            directoryPath: encodeURI(directoryPath),
            arr:JSON.stringify(arr),
        }, function (data) {
            if (data.code == 0) {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.layer.closeAll();

                layer.msg("移动成功");
                top.layer.close();
                var currentFolederName = parent.$("#hiddenCurrentfolederName").val();
                var totalfile = parent.$(".totalFileUrl").val();
                parent.partflush(totalfile, currentFolederName);

            }
        })
    }
</script>
</html>
