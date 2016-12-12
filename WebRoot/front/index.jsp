<%--
  Created by IntelliJ IDEA.
  User: lizy_java
  Date: 2016/12/5
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展首页</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>
    
    <script src="../resource/js/front/index.js"></script>
</head>
<body>
<div class="main">
    <!-- 头部开始 -->
    <%@include file="header.jsp" %>
    <!-- 头部结束 -->
    <!-- 首页正文开始 -->
    <div class="container clearfix">
        <!-- 新闻中心开始 -->
        <div class="news">
            <div class="title">
                <span>新闻中心</span>
                <img src="../resource/img/more.gif"/>
            </div>
            <div class="list" id="newsList"></div>
        </div>
        <!-- 新闻中心结束 -->
        <!-- 轮播图开始 -->
        <div class="caption">
            <div class="banner">
                <ul></ul>
                <ol></ol>
                <i class="left"></i><i class="right"></i>
            </div>
        </div>
        <!-- 轮播图结束 -->
        <!-- 通知公告开始 -->
        <div class="notice">
            <div class="title">
                <span>通知公告</span>
                <img src="../resource/img/more.gif"/>
            </div>
            <div class="list">
                <div class="line">
                    <div>
                        <a href="#" title="扶贫用实招 爱心解难题——城投公司工会组织员工为帮扶村的困难户捐款">扶贫用实招 爱心解难题——城投公司工会组织员工为帮扶村的困难户捐款 </a>
                    </div>
                    <span class="date">(11-25)</span>
                </div>
            </div>
        </div>
        <!-- 通知公告结束 -->
    </div>
    <!-- 首页正文结束 -->

    <!-- footer开始 -->
    <%@include file="footer.jsp" %>
    <!-- footer结束 -->
</div>
</body>
</html>
