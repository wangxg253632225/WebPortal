<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <img src="<%=ctx%>/resource/img/top.png" width="1000"/>
    <div class="nav">
        <ul>
            <li>
                <a href="<%=ctx%>">
                    <div class="left">
                        <span class="first">首页</span>
                        <span class="last">Home</span>
                    </div>
                </a>
                <div class="divider"></div>
            </li>
            <li>
                <a href="<%=ctx%>/general">
                    <div class="left">
                        <span class="first">企业概况</span>
                        <span class="last">GeneralPicture</span>
                    </div>
                </a>
                <div class="divider"></div>
            </li>
            <li>
                <a href="<%=ctx%>/category?type=business">
                    <div class="left">
                        <span class="first">公司业务</span>
                        <span class="last">Financing</span>
                    </div>
                </a>
                <div class="divider"></div>
            </li>
            <li>
                <a href="<%=ctx%>/category?type=news">
                    <div class="left">
                        <span class="first">新闻中心</span>
                        <span class="last">News</span>
                    </div>
                    <div class="divider"></div>
                </a>
            </li>
            <li>
                <a href="<%=ctx%>/manpower">
                    <div class="left">
                        <span class="first">人力资源</span>
                        <span class="last">Job</span>
                    </div>
                </a>
                <div class="divider"></div>
            </li>
            <li>
                <div class="left">
                    <span class="first">联系我们</span>
                    <span class="last">Call me</span>
                </div>
                <%--<div class="divider"></div>--%>
            </li>
            <%--<li>--%>
                <%--<div class="left">--%>
                    <%--<span class="first">内 部论坛</span>--%>
                    <%--<span class="last">BBS</span>--%>
                <%--</div>--%>
            <%--</li>--%>
        </ul>
    </div>
</div>