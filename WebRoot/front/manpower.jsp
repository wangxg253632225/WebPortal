<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展有限公司-公司概况</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>
    <script src="<%=ctx%>/resource/js/front/manpower.js"></script>
</head>
<body>
<div class="main">
    <!-- 头部开始 -->
    <%@include file="header.jsp" %>
    <!-- 头部结束 --> 
		<!-- 公司简介开始 -->
    <div class="container">
        <img src="<%=ctx%>/resource/img/manpower.png" />
        <div class="s_general">
            <div class="s_general_cnt" id="manpowerHtml"></div>
        </div>
    </div>
    <!-- 公司简介结束 -->

		<!-- footer开始 -->
    <%@include file="footer.jsp" %>
    <!-- footer结束 -->
</div>
</body>
</html>