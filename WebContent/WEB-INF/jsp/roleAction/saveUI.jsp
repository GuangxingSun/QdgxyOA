<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>岗位管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<s:if test=" id == null "> 新建岗位</s:if>
				<s:else> 修改岗位</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">

		<s:form action="role_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id" />

			<div class="ItemBlock_Title1" ></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">岗位名称</td>
							<td><s:textfield name="name" cssClass="InputStyle required" />*</td>
						</tr>
						<tr>
							<td>岗位说明</td>
							<td><s:textarea name="description" cssClass="TextareaStyle" /></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);">
					<img src="${pageContext.request.contextPath}/style/images/goBack.png" />
				</a>
			</div>
		</s:form>
	</div>

</body>
</html>
