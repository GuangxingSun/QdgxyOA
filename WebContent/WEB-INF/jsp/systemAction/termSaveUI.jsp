<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>学期设置</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<s:if test=" id == null "> 新建学期</s:if>
				<s:else> 修改学期</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="sys_%{id == null ? 'termAdd' : 'termEdit'}">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td>学年</td>
							<td><s:select name="schoolYear" cssClass="SelectStyle"
									list="schooleYearList"/> *</td>
						</tr>
						<tr>
							<td>学期</td>
							<<td><s:select name="term" cssClass="SelectStyle"
									list="{'第一学期','第二学期'}"/> *</td>
						</tr>
						<tr>
							<td>开学时间</td>
							<td><s:textfield name="time" onfocus="WdatePicker({firstDayOfWeek:1,readOnly:true,dateFmt:'yyyy年MM月dd日'})" cssClass="InputStyle Wdate required" /> *</td>
							 <!-- 时间回显有问题  -->
						</tr>
						<%-- <tr>
							<td>设为当前学期</td>
							<td><s:radio name="thisTerm" list="%{ #{'0':'否', '1':'是'} }" /></td>
						</tr> --%>
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
	<s:if test="%{ #session.user.loginName == 'admin' }">
	<div class="Description">
		说明：<br>
		1，必须设置好学期，并指定当前学期后，才能正常使用系统。
	</div>
	</s:if>
</body>
</html>