<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>课程管理</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/confirm.js"></script>
    <script type="text/javascript">
        function showAddPage() {
            location.href = "${pageContext.request.contextPath}/course/addCourse";
        }

        function deleteCourseById(Obj, id, name) {

            Confirm.show('温馨提示：', '确定要删除' + name + '么？', {
                'Delete': {
                    'primary': true,
                    'callback': function () {
                        var param = {"id": id};
                        $.post("${pageContext.request.contextPath}/course/deleteCourseById", param, function (data) {
                            if (data == 'success') {
                                Confirm.show('温馨提示：', '删除成功');
                                $(Obj).parent().parent().remove();
                            } else {
                                Confirm.show('温馨提示：', '操作失败');
                            }
                        });
                    }
                }
            });
        }

        function showName(obj, id, type) {
            var name = $(obj).text();
            if (type == 1) {
                $("#subjectName").html(name + "<span class='caret'></span>");
                $("#subjectId").val(id);
            }
        }
    </script>
    <style type="text/css">
        th {
            text-align: center;
        }
    </style>
</head>

<body>
<nav class="navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand" href="${pageContext.request.contextPath}/video/list">视频管理系统</a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-9">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
                <li><a href="${pageContext.request.contextPath}/course/list">课程管理</a></li>


            </ul>
            <p class="navbar-text navbar-right">
                <span>${sessionScope.userName}</span> <i class="glyphicon glyphicon-log-in"
                                                         aria-hidden="true"></i>&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/admin/exit"
                    class="navbar-link">退出</a>
            </p>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
    <div class="container">
        <h2>课程管理</h2>
    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-2">
            <button onclick="showAddPage()" type="button"
                    class="btn btn-info dropdown-toggle" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                添加
            </button>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-6">
            <!-- 查询相关组件 -->
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/course/list" method="post" >

                <input type="text" name="courseTitle" class="form-control" placeholder="课程名">
                <div class="btn-group">
                    <button type="button" id="subjectName"
                            class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        --请选择专业--<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach items="${subject}" var="subject">
                            <li value='${subject.id}'>
                                <a href="#" onclick="showName(this,'${subject.id}',1)">
                                        ${subject.subjectName}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <input type="hidden" name="subjectId" id="subjectId" value="0"/>
                </div>

                <button type="submit" class="btn btn-info dropdown-toggle">查询</button>
            </form>

        </div>

    </div>
</div>

<div class="container" style="margin-top: 20px;">
    <table class="table table-bordered table-hover"
           style="text-align: center;table-layout:fixed;">
        <thead>
        <tr class="active">
            <th>序号</th>
            <th>课程名</th>
            <th>专业</th>
            <th style="width:60%;">课程详情</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="course" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${course.courseTitle}</td>
                <td>${course.subjectName}</td>
                <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${course.courseDesc}</td>
                <td><a href="${pageContext.request.contextPath}/course/edit/${course.id}">
                    <span class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
                <td><a href="#" onclick="return deleteCourseById(this,'${course.id}','${course.courseTitle}')">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <div class="navbar-right">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="${pageContext.request.contextPath}/course/list?page=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" >
                    <li><a href="${pageContext.request.contextPath}/course/list?page=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
                </c:forEach>


                <li>
                    <a href="${pageContext.request.contextPath}/course/list?page=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<%--<div class="container">--%>
<%--    <div class="navbar-right" style="padding-right: 17px">--%>
<%--        <p:page url="${pageContext.request.contextPath}/course/courseList"></p:page>--%>
<%--    </div>--%>
<%--</div>--%>

</body>

</html>