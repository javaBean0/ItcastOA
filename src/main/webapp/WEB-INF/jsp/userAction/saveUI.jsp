<%--
  Created by IntelliJ IDEA.
  User: bigStone
  Date: 2019/4/10
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户信息</title>
    <%@include file="../public/common.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="userAction_%{id == null ? 'add' : 'edit'}.action">
        <s:hidden name="id"></s:hidden>
        <s:hidden name="password"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                            <s:select name="departmentId" list="#departmentList" listValue="name" listKey="id"
                                      headerValue="请选择部门" headerKey="" cssClass="SelectStyle">
                            </s:select>


                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><s:textfield type="text" name="loginName" cssClass="InputStyle required"/> *
                            （登录名要唯一）
                        </td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield type="text" name="name" cssClass="InputStyle required"/> *</td>
                    </tr>
                    <tr><td>性别</td>
                        <td><s:radio name="gender" list="#{'男':'男','女':'女'}"></s:radio></td>
                    </tr>
                    <tr><td>联系电话</td>
                        <td><s:textfield type="text" name="phoneNumber" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield type="text" name="email" cssClass="InputStyle email"/></td>
                    </tr>
                    <tr><td>备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">岗位</td>
                        <td>
                            <s:select name="roleIds" list="roleList"
                                      multiple="true" size="10" cssClass="SelectStyle"
                                      listKey="id" listValue="name"></s:select>

                            按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
    说明：<br />
    1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
    2，新建用户后，密码被初始化为"1234"。<br />
    3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
    4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
    5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
    6，修改用户信息时，登录名不可修改。
</div>

</body>
</html>
