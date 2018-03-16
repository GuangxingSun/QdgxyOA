<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>标记管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				标记列表
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="200">标记</td>
					<td width="300">处理</td>
					<td width="300">说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer">

				<s:iterator value="tagList">
					<tr class="TableDetail1 template">
						<td>${tag}&nbsp;</td>
						<td>
							<s:if test="%{settle>0}">加${settle}分&nbsp;</s:if>
							<s:elseif test="%{settle<0}">扣${-settle}分&nbsp;</s:elseif>
							<s:else>&nbsp;</s:else>
						</td>
						<td>${description}&nbsp;</td>
						<td>
							<s:a action="tagManage_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
							<s:a action="tagManage_editUI?id=%{id}">修改</s:a>
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="tagManage_addUI">
					<img src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
		
	</div>
</body>
</html>
