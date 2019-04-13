<%--
  Created by IntelliJ IDEA.
  User: bigStone
  Date: 2019/4/13
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>主页</h1>
${reqeust.getContextPath()}
<%
    response.sendRedirect(request.getContextPath() + "/homeAction_index.action");
%>
