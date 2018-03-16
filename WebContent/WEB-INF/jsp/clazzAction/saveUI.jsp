<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>班级管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript">
	$(function() {
		$("#grade a").click(function() {
			var text1 = $("#grade a").eq($("#grade a").index(this)).html();
			$("input[name='grade']").val(delHtmlTag(text1));
		});
		$("#major a").click(function() {
			var text2 = $("#major a").eq($("#major a").index(this)).html();
			$("input[name='major']").val(delHtmlTag(text2));
		});
	});
	function delHtmlTag (str) {
		return str.replace(/<[^>]+>/g,""); //去掉所有的html标记
	}
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<s:if test=" id == null "> 新建班级</s:if>
				<s:else> 修改班级</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="clazz_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td></td>
							<td id="grade">
								<s:iterator value="gradeList">
									<a><s:property /></a>&nbsp;
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td>年级</td>
							<td><s:textfield name="grade" cssClass="InputStyle required digits {min:2012}" /> *（例如2012级写“2012”）</td>
						</tr>
						<tr>
							<td></td>
							<td id="major">
								<s:iterator value="majorList">
									<a><s:property /></a>&nbsp;
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td>专业</td>
							<td><s:textfield name="major" cssClass="InputStyle required" /> *（例如软件工程专业写“软件工程”）</td>
						</tr>
						<tr>
							<td>班级</td>
							<td><s:textfield name="clazz" cssClass="InputStyle required" /> *（例如一班写“1”，java方向写“java方向”）</td>
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

	<div class="Description">
		说明：<br>
		1，班级为上课班级。
	</div>

</body>
</html>
