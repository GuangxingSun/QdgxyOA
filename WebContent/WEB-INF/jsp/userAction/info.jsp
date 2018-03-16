<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>个人设置</title>
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
				个人信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="user_infoEdit" method="post" enctype="multipart/form-data">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="150">用户ID（登录名）</td>
							<td>${loginName}</td>
							<td rowspan="5" align="right">
								<img src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" />
							</td>
						</tr>
						<tr>
							<td>部门</td>
							<td><s:if test="%{#department == null}">无</s:if>${department.name}</td>
						</tr>
						<tr>
							<td>岗位</td>
							<td>${roleName}<s:iterator value="roleList">${name}&nbsp;</s:iterator></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>${name}</td>
						</tr>
						<tr>
							<td>姓别</td>
							<td>${gender}</td>
						</tr>
						<s:if test="%{ #session.isStudent == true }">
							<tr>
								<td>班级</td>
								<td>${clazzName}<s:iterator value="clazzList">${grade}级${major}专业${clazz}班<br></s:iterator></td>
							</tr>
						</s:if>
						<s:elseif test="%{ #session.isTeacher == true }">
							<tr>
								<td>课程</td>
								<td>${courseName}<s:iterator value="courseList">${name}<br></s:iterator></td>
							</tr>						
						</s:elseif>
						<tr>
							<td>联系电话</td>
							<td><s:textfield name="phoneNumber" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><s:textfield name="email" cssClass="InputStyle email" /></td>
						</tr>
						<tr>
							<td>头像</td>
							<td><s:file name="resource" cssClass="InputStyle" /></td>
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
					<s:if test="%{ #session.user.loginName != 'admin'}">
					<s:a action="user_intoInfoUI?id=%{id}">信息录入</s:a>
					</s:if>
			</div>
		</s:form>
	</div>
</body>
</html>
