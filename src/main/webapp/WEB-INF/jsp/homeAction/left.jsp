<%@ page language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>导航菜单</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
    <link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <%--顶级菜单--%>
        <s:iterator value="#application.topPrivilegeList">
        <li class="level1">
            <div onClick="menuClick(this)" class="level1Style">
                <img src="style/images/MenuIcon/FUNC20001.gif" class="Icon" />${name}</div>
            <ul style="display: none;" class="MenuLevel2">
                <%--二级菜单--%>
                <s:iterator value="children">
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 个人考勤</div>
                </li>
                </s:iterator>
            </ul>
        </li>
        </s:iterator>
    </ul>
</div>
</body>
</html>
