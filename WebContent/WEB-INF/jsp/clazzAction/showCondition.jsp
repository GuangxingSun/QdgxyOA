<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>作业管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
<script type="text/javascript">
	$(function() {
		$("select[name='score']").change(function() {
			var score = $(this).val();
			var id = $(this).parent().find(".subId").html();
			$.post("${pageContext.request.contextPath}/submitWork_score.do", {'id':id, 'score':score});
		});
	});
</script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				${clazz.grade}级${clazz.major}专业${clazz.clazz}班的作业提交情况
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
	<div id="PageHead"></div>
	<div class="ItemBlock_Title1" style="width: 98%;">
		<font class="MenuPoint"> &gt; </font>
		<s:a action="course_list">我的课程</s:a>
		<font class="MenuPoint"> &gt; </font>
		<s:a action="course_show?id=%{#homework.course.id}">${homework.course.name}课</s:a>
		<font class="MenuPoint"> &gt; </font>${homework.name}
		<font class="MenuPoint"> &gt; </font> ${clazz.grade}级${clazz.major}专业${clazz.clazz}班
	</div>
	<br>
	
		<table cellspacing="0" cellpadding="0" class="TableStyle">
			<thead>
				<tr valign=middle>
					<td width="200" align="left">已交名单如下：</td>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
				</tr>
			</thead>

			<tbody id="TableData" class="dataContainer">
				<s:iterator value="hasSubmitWork" status="st">
				<s:if test="#st.index % 5 == 0"><tr></s:if>
            	<td><b>${student.name}</b>&nbsp;&nbsp;
            		<s:a action="clazz_downloadStudntHomework?id=%{#clazz.id}&submitWorkId=%{id}"
            						   title="下载这个学生的作用">下载</s:a>&nbsp;&nbsp;
            		<s:a action="clazz_toLookStudntHomework?id=%{#clazz.id}&submitWorkId=%{id}"
            						   title="下载这个学生的作用">在线查看</s:a>&nbsp;&nbsp;
            				   
            						   
            		评分：<s:select name="score" list="%{ #{'4':'优秀', '3':'中等', '2':'良好', '1':'不及格'} }" headerKey="0" headerValue="未检查" />&nbsp;&nbsp;&nbsp;|<a style="display: none;" class="subId">${id}</a>
            	</td>
            	<s:if test="#st.index % 5 == 4||#st.last"></tr></s:if>
				</s:iterator>
			</tbody>
		</table>
			
		</table>
		<br><br>
		<table cellspacing="0" cellpadding="0" class="TableStyle">
			<thead>
				<tr valign=middle>
					<td width="200" align="left">未交名单如下：</td>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
					<td width="200"/>
				</tr>
			</thead>

			<tbody id="TableData" class="dataContainer">
				<s:iterator value="notSubmitUser" status="st">
				<s:if test="#st.index % 8 == 0"><tr></s:if>
            	<td><b>${name}</b></td>
            	<s:if test="#st.index % 8 == 7||#st.last"></tr></s:if>
				</s:iterator>
			</tbody>
		</table>
		<br><br>
		<div style="text-align: center;">
			<a href="${pageContext.request.contextPath}/course_show.do?id=${homework.course.id}">
				<img src="${pageContext.request.contextPath}/style/images/goBack.png" />
			</a>
		</div>
		
	</div>

</body>
</html>
