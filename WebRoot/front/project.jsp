<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展有限公司-公司项目</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>

    <script src="<%=ctx%>/resource/js/front/project.js"></script>
</head>
<body>
<div class="main">
    <!-- 头部开始 -->
    <%@include file="header.jsp" %>
    <!-- 头部结束 --> 
		<!-- 公司简介开始 -->
    <div class="container">
        <div class="project_top" id="projectTop">
            <%--<div class="project_li" style="background-image: url('<%=ctx%>/resource/img/ll04.jpg');"><a href="#">资源推介</a></div>--%>
        </div>
        <div class="project_general">
            <div class="project_general_cnt" id="projetHtml"></div>
        </div>
    </div>
    <!-- 公司简介结束 -->

		<!-- footer开始 -->
    <%@include file="footer.jsp" %>
    <!-- footer结束 -->
</div>
</body>
</html>