<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
 <title>作业管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/Ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("input[type=image]").click(function(){
			var clazzs = $("select[name='clazzIds'] option:selected").text();
			if ($("input[name='name']").val() == "" || $("#rep").val() == "" || clazzs == "") {
				return true;
			}
			if ($("#datepicker").val() == "学生提交作业的最晚时间") {
				$("#datepicker").val("");
			}
			var i = clazzs.split("班").length - 1;
			var text = "";
			for (var j = 0; j < i; j++) {
				var n = clazzs.indexOf("班") + 1;
				if (j == i - 1) {
					text = text + clazzs.substring(0, n);
				} else {
					text = text + clazzs.substring(0, n) + "\n";
				}
				clazzs = clazzs.substr(n + 1);
			}
			var name = $("input[name='name']").val();
			
			return confirm("你确定将'" + name + "'作业布置给：\n" + text + "\n吗？");
		});
		
	});
</script>
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
				<s:if test=" id == null "> 新建作业</s:if>
				<s:else> 修改作业</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="homework_%{id == null ? 'add' : 'edit'}" method="post" enctype="multipart/form-data">
			<s:hidden name="id" /><s:hidden name="courseId" />

			<div class="ItemBlock_Title1" >
				<font class="MenuPoint"> &gt; </font>
				<s:a action="course_list">我的课程</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="course_show?id=%{#course.id}">${course.name}课</s:a>
				<font class="MenuPoint"> &gt;&gt; </font>
				<s:if test=" id == null ">布置作业</s:if>
				<s:else>修改作业</s:else>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<s:if test=" id == null ">
							<tr>
								<td>作业名</td>
								<td><s:textfield name="name" cssClass="InputStyle required" /> *</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td>作业名</td>
								<td><s:textfield name="name" cssClass="InputStyle required" readonly="true" /> *</td>
							</tr>
						</s:else>
						<tr>
							<td>收取时间</td>
							<td><s:textfield name="stime" onfocus="WdatePicker({minDate:'%y-%M-%d',firstDayOfWeek:1,readOnly:true,dateFmt:'yyyy年MM月dd日'})" cssClass="InputStyle Wdate" /> （学生提交作业的最晚时间）</td>
						</tr>
						<tr>
							<td>作业文件</td>
							<td>
								<s:textfield name="fileName" id="rep" cssClass="InputStyle" readonly="true" cssStyle="vertical-align:middle; width: 300px;" />
								<img alt="选择文件" src="${pageContext.request.contextPath}/style/images/buttonFile.png" style="vertical-align:middle" >
								<s:if test=" id == null ">
									<s:file name="upload" id="uploadStyle" cssClass="InputStyle required" onchange="rep.value=upload.value;" /> *
								</s:if>
								<s:else>
									<s:file name="upload" id="uploadStyle" cssClass="InputStyle" onchange="rep.value=upload.value;" /> *
								</s:else>
							</td>
						</tr>
						<tr>
							<td>公开成绩</td>
							<td>
								<s:if test="id == null">
								<a title="是否让学生看到作业的评分" style="text-decoration:none; color:#333;">
									<s:radio name="sc" list="%{ #{'true':'是', 'false':'否'} }" value="true" /> （是否让学生看到作业的评分情况）</a>
								</s:if>
								<s:else>
								<a title="是否让学生看到作业的评分" style="text-decoration:none; color:#333;">
									<s:radio name="sc" list="%{ #{'true':'是', 'false':'否'} }" /></a>
								</s:else>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					班级设置
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
							<td width="100">班级</td>
							<td><s:select name="clazzIds" cssClass="SelectStyle required"
									multiple="true" size="10" list="clazzList" listKey="id"
									listValue="%{grade + '级 ' + major + '专业 ' + clazz+'班 '}" />
									按住Ctrl键可以多选或取消选择
							</td>
						</tr>
					</table>
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