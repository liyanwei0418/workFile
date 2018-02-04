<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>人员管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/xiaojiRenShiHead.jsp" %>

<main class="container">
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <div>
                <button onclick="openDialog('新增职务','${ctx}/renshi/title/add','500px','320px')">新增职务</button>
            </div>
            <ul id="tree1" class="ztree"></ul>
        </aside>
        <main class="col-xs-9" id="zw-manage">
            <c:if test="${gukeer:notEmptyString(currentTitle)}">
                <div class="stu-num-manage-menu">
                    <ul>
                        <li><a href="#" id="about_person" data="generated" class="active">相关人员</a></li>
                        <li><a href="#" id="zhiwu_info" data="not-generate">职务信息</a></li>
                    </ul>
                </div>
            </c:if>
            <section id="generated">
                <div class="search-box">
                    <p>
                        <c:if test="${gukeer:notEmptyString(currentTitle)}">
                            <%--<button onclick="openDialog('分配职务','${ctx}/renshi/title/teacher/add?titleId='+${currentTitle.id},'520px','352px');">添加</button>--%>
                            <button onclick="openDialog('分配职务','${ctx}/renshi/teacher/manyDepart/add?titleId=${currentTitle.id}','950px','750px');">
                                添加
                            </button>
                        </c:if>
                    </p>
                </div>
                <div>
                    <table class="normal" style="margin-top:20px;">
                        <thead>
                        <tr>
                            <th width="%">序号</th>
                            <th width="%">姓名</th>
                            <th width="%">性别</th>
                            <th width="%">职工编号</th>
                            <th width="18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                            <tr>
                                <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                                <td>${teacher.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${teacher.gender==1}">
                                            男
                                        </c:when>
                                        <c:when test="${teacher.gender==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${teacher.no}</td>
                                <td>
                                        <%--<span class="remove" onclick="alertTips(400,222,'移除职位','确定要移除${teacher.name}的${currentTitle.mc}职务吗?','deleteSure(${teacher.id})')">移除</span>--%>
                                    <span class="remove"
                                          onclick="alertTips(400,202,'移除职位','确定要移除${teacher.name}的职务吗?','deleteSure(\'${teacher.id}\')')">移除</span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="fenye">
                    <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                        <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                    </c:if>
                    <div class="fenY" id="fenY">
                    </div>
                </div>
            </section>
            <section id="not-generate">
                <div id="column-manage-menu" style="display: block;">
                    <div class="column-manage-operation">
                        <button class="column-manage-edit"
                                onclick="openDialog('编辑职务','${ctx}/renshi/title/add?titleId=${currentTitle.id}','480px','320px');">
                            编辑
                        </button>
                        <button class="column-manage-delete"
                                onclick="alertTips(400,202,'删除职务','确定要删除${title.mc}吗?','deleteTitle(\'${currentTitle.id}\')')">
                            删除
                        </button>
                    </div>
                    <table>
                        <tbody>
                        <tr>
                            <td>职务名称</td>
                            <td>${currentTitle.mc}</td>
                        </tr>
                        <tr>
                            <td>职务排序</td>
                            <td>${currentTitle.px}</td>
                        </tr>
                        <tr>
                            <td>职务描述</td>
                            <td>${currentTitle.remarks}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </section>

        </main>
    </div>

    <script>
        activeMenu("zwmenu", 0);

        /* 初始化分页 */
        $(function () {
            <c:if test="${pageInfo.pages!=0}">
            $(".fenY").createPage({
                pageCount:${pageInfo.pages},//总页数
                current:${pageInfo.pageNum},//当前页面
                backFn: function (p) {
                    var name = $("#searchHidden").val();
                    window.location.href = "${ctx}/renshi/zhiwu/index?pageNum=" + p + "&pageSize=10" + "&titleId=${currentTitle.id}";
                }
            });
            </c:if>

            var tap = "${tap}";
            if (tap == "2"){
                showWhichTap($("#zhiwu_info"));
            }else{
                showWhichTap($("#about_person"));
            }


            $(".gotoPage").click(function () {
                var pageNum = $(".go").val();
                if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                    layer.msg("请输入正确的页码")
                } else {
                    window.location.href = "${ctx}/renshi/zhiwu/index?pageNum=" + $(".go").val() + "&pageSize=10&titleId=${currentTitle.id}";
                }
            });

        })

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
        var zNodes1 = [
            {id: 0, pId: -2, name: "职务管理", open: true},
            <c:forEach items='${titleList}' var='title'>
            {id: "${title.id}", pId: "0", name: "${title.mc}", open: true},
            </c:forEach>

        ];
        function clickTree(event, treeId, treeNode, clickFlag) {
            var zTree = $.fn.zTree.getZTreeObj("tree1");
            zTree.expandNode(treeNode);
        }

        $.fn.zTree.init($("#tree1"), setting, zNodes1);
        /*z-tree*/

        $(".node_name").click(function () {
            window.location.href = "${ctx}/renshi/zhiwu/index?titleId=" + $(this).attr("menuId");
        });


        function setFontCss(treeId, treeNode) {
            if (treeNode.id == "${currentTitle.id}")
                return {
                    'padding-top': ' 0',
                    'background-color': '#def7f5',
                    'color': 'black',
                    'height': '25px',
                    'opacity': '.8',
                    'width': '175px'
                };
            else return {'font-weight': 'normal', color: 'black'};
        }

        //删除该教师职务
        function deleteSure(id) {
            closeAlertDiv();
            $.post
            ("${ctx}/renshi/title/teacher/delete", {
                id: id,
            }, function (retJson) {
            }, "json");
            setTimeout(function () {
                window.location.reload();
            }, 300)
        }

        function deleteTitle(id) {
            closeAlertDiv();
            $.post("${ctx}/renshi/title/delete", {
                id: id
            }, function (retJson) {
                layer.msg(retJson.msg, {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    window.location.href = '${ctx}/renshi/zhiwu/index?tap=2';
                });
            });
        }
        function showWhichTap(index) {

            $('.stu-num-manage-menu a').removeClass('active');
            $(index).addClass('active');
            var data = $(index).attr('data');
            if (data!= undefined){
                var sections = $('#' + data);
                $('#' + sections[0].id).show();
                $('#' + sections[0].id).siblings('section').hide();
            }
        }


    </script>
</main>
</body>
</html>