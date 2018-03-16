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
				【${course.name}】课程信息
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
					<font class="MenuPoint"> &gt; </font> ${course.name}课程
				</div>

				<div class="ForumPageTableBorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<!--表头-->
						<tr align="center" valign="middle">
							<td colspan="8" height="50"><span
								style="font-size: 14pt; font-family:宋体">请按照下方提示功能进行相应操作</span></td>
						</tr>
						</tr>
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="9"></td>
						</tr>
						<tr align="center" valign="middle">
							<td width="3" class="ForumPageTableTitleLeft"><img
								border="0" width="1" height="1"
								src="${pageContext.request.contextPath}/style/images/blank.gif" />
							</td>
								<td width="220" height="40"><span
									style="font-size: 12pt;font-weight:bold;font-family:宋体">
										<s:a action="studentResult_one?courseId=%{courseId}">个人成绩</s:a>
								</span></td>
								<td width="220" height="40"><span
									style="font-size: 12pt;font-weight:bold;font-family:宋体">
										<s:a action="studentResult_allClazzUI?courseId=%{courseId}">班级成绩</s:a>
								</span></td>
								<td width="220" height="40"><span
									style="font-size: 12pt;font-weight:bold;font-family:宋体">
										<s:a action="studentResult_queryUI?courseId=%{courseId}">成绩查询</s:a>
								</span></td>
						</tr>
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
  			 				<td valign="middle" align="center" colspan="9">*小提示：本课程已经进行过 ${course.onlineTestCount}次测试！</td>
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
