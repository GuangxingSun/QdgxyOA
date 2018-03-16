<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>修改密码</title>
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
				修改密码
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="user_editPassword">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr height="50">
							<td width="150">
								<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
								请输入原密码
							</td>
							<td><input type="password" name="oldPassword" class="InputStyle required" />*</td>
						</tr>
						<tr height="25">
							<td width="150">
								<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
								请填写新密码
							</td>
							<td><input type="password" name="password" id="password1" class="InputStyle required" />*</td>
						</tr>
						<tr height="25">
							<td width="150">
								<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
								再次输入新密码
							</td>
							<td><input type="password" name="password2" class="InputStyle required {equalTo : '#password1'}" />*</td>
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
