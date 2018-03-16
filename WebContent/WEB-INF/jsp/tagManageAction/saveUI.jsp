<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>标记管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript">
	
	$.validator.addMethod('letter', function(value, element) {
		if(value.length>1) {
			return false;
		}
		var chrnum = /^([a-zA-Z]+)$/; 
		return this.optional(element) || (chrnum.test(value)); 
	}, '必须是一个字母,大写或小写');
	
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
				<s:if test=" id == null "> 新建标记</s:if>
				<s:else> 修改标记</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="tagManage_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td>标记</td>
							<td><s:textfield name="tag" cssClass="InputStyle required letter" /> *</td>
						</tr>
						<tr>
							<td>处理</td>
							<td><s:textfield name="settle" cssClass="InputStyle required {range:[-100,100]}" /> *（正数为加分，负数为扣分）</td>
						</tr>
						<tr>
							<td>说明</td>
							<td><s:textarea name="description" cssClass="TextareaStyle required" /> *</td>
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
