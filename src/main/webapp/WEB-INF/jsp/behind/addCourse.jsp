<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">

    <!--表示使用IE最新的渲染模式进行解析-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加或修改课程</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!-- 如果IE版本小于9，加载以下js,解决低版本兼容问题 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--
    	引入网络的jquery  ,如果想换成自己的，导入即可
    	网站优化：建议将你网站的css\js等代码，放置在互联网公共平台上维护，比如：七牛
    -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <style type="text/css">
        th {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function showName(obj, int) {


            //想获取下拉列表选中的值
            var chooseName = $(obj).text();
            // 将获取的值显示在输入框内
            $("#subjectName").val(chooseName);
            //想给隐藏的文本赋值
            $("#subjectId").val(id);

        }
    </script>
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
                <li><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/course/list">课程管理</a></li>
            </ul>
            <p class="navbar-text navbar-right">
                <span>${sessionScope.USERNAME}</span>
                <i class="glyphicon glyphicon-log-in" aria-hidden="true"></i>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/admin/exit" class="navbar-link">
                    退出
                </a>
            </p>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
    <div class="container">
        <%-- <c:if test="empty ${video.id}"> --%>
        <c:if test="${empty course.id}">
            <h2>添加课程信息</h2>
        </c:if>

        <c:if test="${not empty course.id}">
            <h2>修改课程信息</h2>
        </c:if>
    </div>
</div>


<div class="container" style="margin-top: 20px;">

    <form class="form-horizontal" id="formSimple">

        <c:if test="${not empty course.id}">
            <input type="hidden" name="id" value="${course.id}">
        </c:if>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">课程名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="courseTitle" value="${course.courseTitle}"
                       placeholder="课程名称">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">所属专业</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">下拉菜单<span class="caret"></span></button>
                        <ul id="selectCourse" class="dropdown-menu">
                            <c:forEach items="${subjectList}" var="subject">
                                <li value="${subject.id}"><a href="#"
                                                             onclick="showName(this,${subject.id})">${subject.subjectName}</a>
                                </li>
                            </c:forEach>

                        </ul>
                    </div><!-- /btn-group -->
                    <c:if test="${empty course.subjectId}">
                        <input type="hidden" class="form-control" id="subjectId" name="Subject.id" value="0">
                    </c:if>
                    <c:if test="${not empty course.subjectId}">
                        <input type="hidden" class="form-control" id="subjectId" name="Subject.id"
                               value="${course.subjectId}">
                    </c:if>
                    <input type="text" class="form-control" disabled id="subjectName" aria-label="..."
                           value="${course.subjectName}">
                </div><!-- /input-group -->
            </div>
        </div>

        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">课程详情</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="courseDesc" rows="3">${course.courseDesc}</textarea>
            </div>
        </div>

    </form>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" onclick="commitForm()" class="btn btn-default">保存</button>
        </div>
    </div>
</div>

<script>
    function commitForm() {
        $.post("${pageContext.request.contextPath}/course/saveOrUpdate", $("#formSimple").serialize(), function (data) {
            if (data == 'success') {
                alert("保存成功！");
                location.href = "${pageContext.request.contextPath}/course/list";
            }
        });
    }
</script>

</body>
