<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>班级管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				上课班级列表
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="200">年级</td>
					<td width="200">专业</td>
					<td width="200">班级</td>
					<td width="200">学生人数</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer">

				<s:iterator value="clazzList">
					<tr class="TableDetail1 template">
						<td>${grade}&nbsp;</td>
						<td>${major}&nbsp;</td>
						<td>${clazz}&nbsp;</td>
						<td>${studentCount}&nbsp;</td>
						<td>
							<s:a action="clazz_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
							<s:a action="clazz_editUI?id=%{id}">修改</s:a>
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="clazz_addUI">
					<img src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
		
	</div>
</body>
</html>
