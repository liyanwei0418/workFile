<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head >
    <meta charset="utf-8">
    <title>通知公告</title>
    <style>
        .layui-layer.layui-layer-iframe.layer-anim {
            top: 6% !important;
        }
        .not-read{
            width: 80%;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .ztree li span{
            max-width: 200px;
            word-break: break-all;
            word-wrap: break-word;
            white-space: normal;
            line-height: 22px !important;
        }
    </style>
</head>
<body>

<%@ include file="../common/sonHead/notifyHead.jsp" %>

<main class="container">
    <!--通知公告-->
    <div id="inform-notice">
        <aside class="col-xs-3">
            <div class="tree-menu">
                <div class="widget-main padding-8">
                    <ul id="tree1"  class="ztree"></ul>
                </div>
            </div>
        </aside>
        <main class="col-xs-9">
            <div class="search-box">
                <shiro:hasPermission name="notify:role:view">
                <button onclick="openDialog('发布公告','${ctx}/notify/add?columId=${columId}','1100px','80%');"   class="notice-publish">发布</button>
                </shiro:hasPermission>
                <button class="notice-search summitButton" id="search"></button>
                <input type="text" class="searchInput " placeholder="搜索标题" value="${title}"  name="searchTitle"/>
            </div>
            <ul class="listUl" style="height:auto; display: none;">
                <c:forEach items="${resultList}" var="notify">
                    <li onclick="window.location.href='${ctx}/notify/details/${notify.notifyId}'">
                        <p
                                <c:choose>
                                    <c:when test="${notify.readFlag==1}"><%--1是已读，0未读--%>
                                        class="read"
                                    </c:when>
                                    <c:otherwise>
                                        class="not-read"
                                    </c:otherwise>
                                </c:choose>
                                >
                            <c:choose>
                                <c:when test="${gukeer:notEmptyString(notify.columName)}">
                                    【${notify.columName}】
                                </c:when>
                                <c:otherwise>
                                    【其他】
                                </c:otherwise>
                            </c:choose>${notify.title}</p>
                        <div>
                           <%-- <span>${notify.name}</span>--%>
                            <span>${gukeer:intervalNowTimeToView(notify.publishTime)}</span>
                        </div>
                    </li>
                </c:forEach>
                <p class="resultP" style="display: none; color: #666;">为您找到0条搜索结果</p>
            </ul>
            <div class="fenye">
                <div class="fenY" id="fenY">
                </div>
            </div>
        </main>
    </div>
</main>


<script type="text/javascript">
    activeMenu("tzmenu",0);

    /* 初始化分页 */
    $(function() {

        /* 提示搜索结果为空   strat  */
        var dataStr = "${resultList}";
        var checkStr= dataStr.substr(dataStr.indexOf('{') + 1, dataStr.indexOf('}') - 1);
        var checkArry = checkStr.split(",");
        var flag = checkArry[6].substr(checkArry[6].indexOf('=='));
        if(flag == '1') $('.listUl').show();
        if(flag == '0') $('.resultP').show();
        /* 提示搜索结果为空   end  */

        <c:if test="${pageInfo.pages!=0}">
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/notify/index?pageNum="+p+"&pageSize=10";
            }
        });
        </c:if>
        $(".gotoPage").click(function (){
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}){
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/notify/index?pageNum="+$(".go").val()+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var lanmu=$("select[name='lanmu']").val();
            var title=$("input[name='searchTitle']").val() ;
            var beginDate=$("#beginDate").val() ;
            var endDate=$("#endDate").val() ;
            window.location.href="${ctx}/notify/index?title="+encodeURI(encodeURI(title));
        });

    });

    /*z_tree*/
    var zTree;
    var demoIframe;

    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false,
            fontCss: setFontCss
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onClick : clickTree
        }
    };

    var zNodes1 =[
        {id:9999, pId:-1, name:"通知公告", open:true},

        <c:forEach items='${notifyColumnList}' var='column'>
        {id:"${column.id}", pId:"9999", name:"${column.name}", open:false},
        </c:forEach>

    ];


    function clickTree(event, treeId, treeNode, clickFlag) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        zTree.expandNode(treeNode);
        /*	alert("您点击了：treeId:" + treeId + "   name:" + treeNode.name + "level:"
         + treeNode.level + "   tid:" + treeNode.tId + "   parentTId:"
         + treeNode.parentTId + "   children:" + treeNode.children);*/

    }

    $.fn.zTree.init($("#tree1"), setting, zNodes1);
    /*z-tree*/

    $(".node_name").click(function () {

        window.location.href="${ctx}/notify/index?lanmu="+$(this).attr("menuId");
    });
    function setFontCss(treeId, treeNode) {
        if (treeNode.id == "${columId}")
            return {
                'padding-top':' 0','background-color': '#def7f5','color': 'black','height': '25px','opacity': '.8','width': '86%'
            };
        else return {'font-weight': 'normal', color: 'black'};
    }
</script>
</body>


</html>