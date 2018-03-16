<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>

<script type="text/javascript">
	$(function() {
		$("#roleIds-1").click(function(){
			var clazzValue = $("select[name='clazzIds']").val();
			if (clazzValue != null) {
				for (var i = 0; i<clazzValue.length; i++) {
					$("select[name='clazzIds']").find("option[value='"+clazzValue[i]+"']").attr("selected", false);
				}
			}
			$(".course").show();
			$(".clazz").hide();
			$("select[name='courseIds']").addClass("required");
			$("select[name='clazzIds']").removeClass("required");
			if ($("#roleIds-2").attr("checked")) {
				$("#roleIds-2").attr("checked",false);
			}
		});
		$("#roleIds-2").click(function(){
			var courseValue = $("select[name='courseIds']").val();
			if (courseValue != null) {
				for (var i = 0; i<courseValue.length; i++) {
					$("select[name='courseIds']").find("option[value='"+courseValue[i]+"']").attr("selected", false);
				}
			}
			$(".clazz").show();
			$(".course").hide();
			$("select[name='clazzIds']").addClass("required");
			$("select[name='courseIds']").removeClass("required");
			if ($("#roleIds-1").attr("checked")) {
				$("#roleIds-1").attr("checked",false);
			}
		});
		if ($("#roleIds-2").attr("checked")) {
			$(".clazz").show();
			$(".course").hide();
			$("select[name='clazzIds']").addClass("required");
			$("select[name='courseIds']").removeClass("required");
		}
		if ($("#roleIds-1").attr("checked")) {
			$(".course").show();
			$(".clazz").hide();
			$("select[name='courseIds']").addClass("required");
			$("select[name='clazzIds']").removeClass("required");
		}
		$("input[name=roleIds]").click(function() {
			if ($("#roleIds-1").attr("checked") || $("#roleIds-2").attr("checked")) {
				$("#setDiv").show();
			} else {
				$("#setDiv").hide();
			}
		});
		if ($("#roleIds-1").attr("checked") || $("#roleIds-2").attr("checked")) {
			$("#setDiv").show();
		} else {
			$("#setDiv").hide();
		}
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/Ajax.js"></script>

</head>
<body>
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<s:if test=" id == null "> 新建用户</s:if>
				<s:else> 修改用户</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="user_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1"></div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">岗位设置</td>
							<td><s:checkboxlist list="roleList" listKey="id" listValue="name" name="roleIds" /></td>
						</tr>
						<tr>
							<td width="100">所属部门</td>
							<td><s:select name="departmentId" cssClass="SelectStyle"
									list="departmentList" listKey="id" listValue="name"
									headerKey="" headerValue="==请选择部门==" /></td>
						</tr>
						<s:if test=" id == null ">
							<tr>
								<td>登录名</td>
								<td><s:textfield name="loginName" cssClass="InputStyle required {minlength:3, maxlength:15}" /> *</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td>登录名</td>
								<td><s:textfield name="loginName" cssClass="InputStyle required {minlength:3, maxlength:15}" readonly="true" /> *</td>
							</tr>
						</s:else>
						<tr>
							<td>姓名</td>
							<td><s:textfield name="name" cssClass="InputStyle required" /> *</td>
						</tr>
						<tr>
							<td>性别</td>
							<td><s:radio name="gender" list="%{    {'男', '女'}    }" /></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><s:textfield name="phoneNumber" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><s:textfield name="email" cssClass="InputStyle email" /></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="setDiv" style="display: none;">
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					相关设置
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td class="clazz">筛选：</td>
							<td class="clazz">
									<s:select name="grade" cssStyle="width :122;" cssClass="SelectStyle"
									list="gradeList" headerKey="" headerValue="==年级==" />
									<s:select name="major" cssStyle="width :122;" cssClass="SelectStyle"
									list="majorList" headerKey="" headerValue="==专业==" />
							</td>
						</tr>
						<tr>
							<td width="100" class="course">课程</td>
							<td class="course"><s:select name="courseIds" cssClass="SelectStyle"
									multiple="true" size="10" list="courseList" listKey="id"
									listValue="%{name}" />
									按住Ctrl键可以多选或取消选择
							</td>
							<td width="100" class="clazz">班级</td>
							<td class="clazz"><s:select name="clazzIds" cssClass="SelectStyle"
									multiple="true" size="10" list="clazzList" listKey="id"
									listValue="%{grade + '级 ' + major + '专业 ' + clazz+'班 '}" />
									按住Ctrl键可以多选或取消选择
							</td>
						</tr>
					</table>
				</div>
			</div>
			</div>
			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

</body>
</html>
