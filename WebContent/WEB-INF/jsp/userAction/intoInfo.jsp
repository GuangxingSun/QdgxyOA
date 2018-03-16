<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>信息录入</title>
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
				<s:if test=" #session.isStudent == true "> 请选择你所在的班级</s:if>
				<s:else> 请选择你在本学期所教的课程</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="user_intoInfo" method="post">
			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
					<s:if test=" #session.isStudent == true ">
						<tr>
							<td class="clazz">筛选：</td>
							<td class="clazz">
									<s:select name="grade" cssStyle="width :122;" cssClass="SelectStyle"
									list="gradeList" headerKey="" headerValue="==年级==" />
									<s:select name="major" cssStyle="width :122;" cssClass="SelectStyle"
									list="majorList" headerKey="" headerValue="==专业==" />
							</td>
						</tr>
					</s:if>
						<tr>
							<s:if test=" #session.isTeacher == true ">
							<td width="100" class="course">课程</td>
							<td class="course"><s:select name="courseIds" cssClass="SelectStyle"
									multiple="true" size="10" list="courseList" listKey="id"
									listValue="%{name}" />
									按住Ctrl键可以多选或取消选择
							</td>
							</s:if>
							<s:if test=" #session.isStudent == true ">
							<td width="100" class="clazz">班级</td>
							<td class="clazz"><s:select name="clazzIds" cssClass="SelectStyle"
									multiple="true" size="10" list="clazzList" listKey="id"
									listValue="%{grade + '级 ' + major + '专业 ' + clazz+'班 '}" />
									按住Ctrl键可以多选或取消选择
							</td>
							</s:if>
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
