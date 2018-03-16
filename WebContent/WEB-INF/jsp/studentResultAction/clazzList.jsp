<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看学生成绩</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/onlineTestShow.css" />
	

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
				【${course.name}】试题信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

		<div id="MainArea">
			<div id="PageHead"></div>
			<center>
			
				<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="studentResult_list?i=3">我的课程</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="studentResult_controlList?courseId=%{courseId}">${course.name}课程</s:a>
					<font class="MenuPoint"> &gt; </font>班级成绩查询
				</div>

				<div class="ForumPageTableBorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<!--表头-->
						<tr align="center" valign="middle">
							<td colspan="8" height="50">
							<span style="font-size: 14pt; font-family:宋体">请直接点击您要查看成绩的班级！</span></td>
						</tr>
						</tr>
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="9"></td>
						</tr>
					
							<tr  height="3">
									
							</tr>
						
						<s:iterator value="clazzlist">
						<tr >
							<td align="center" width="3" class="ForumPageTableTitleLeft"><img
								border="0" width="1" height="1"
								src="${pageContext.request.contextPath}/style/images/blank.gif" />
										<label>
											<s:a action="studentResult_all?clazzId=%{id}&courseId=%{#course.id}" title="点击班级查看本班成绩详细情况">${grade}级${major}专业${clazz}班（${studentCount}人）</s:a>
										</label>
							</td>
						</tr>
						</s:iterator>
						
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="8"></td>
						</tr>
						<tr height=3>
							<td colspan=8></td>
						</tr>
						
						
	
						<!--主题列表结束-->

						<tr height="3">
							<td colspan="9"></td>
						</tr>
						
  				 		<tr>
  				 			<td colspan="8" align="center">
  				 				<FONT color="red"><s:actionerror/></FONT>
							</td>
  				 		</tr>

						
					</table>
				</div>
			</center>
		</div>
</body>
</html>
