<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				用户列表
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td>登录名</td>
					<td>姓名</td>
					<td>所属部门</td>
					<td>岗位</td>
					<td>备注</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer">

				<s:iterator value="recordList">
					<tr class="TableDetail1 template">
						<td>${loginName}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${department.name}&nbsp;</td>
						<td><s:iterator value="roles">${name}&nbsp;</s:iterator>&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>
							<%-- version 4 还是s:a标签，我们要修改<s:a>标签所对应的源码 --%>
							<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
							<s:a action="user_editUI?id=%{id}">修改</s:a>
							<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="user_addUI">
					<img src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
	</div>


	<%-- 分页信息 --%>
	<s:form id="pageForm" action="user_list" />
	<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>

</body>
</html>
