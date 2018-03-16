<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>作业管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				我的课程
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<center>
			<div class="ForumPageTableBorder" style="margin-top: 25px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<!--表头-->
					<tr align="center" valign="middle">
					<s:if test="%{ i == 0 }">
						<td colspan="3" class="ForumPageTableTitleLeft">课程名</td>
						<td width="250" class="ForumPageTableTitle">总作业数</td>
					</s:if>
						<s:else>
							<td colspan="3" class="ForumPageTableTitleLeft">请选择要统计的课程</td>
						</s:else>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="9"></td>
					</tr>
					<tr height="3">
						<td colspan="9"></td>
					</tr>

					<!--版面列表-->
					<tbody class="dataContainer">

						<s:iterator value="courseList">
							<tr height="78" align="center" class="template">
								<td width="3"></td>
								<td width="150" class="ForumPageTableDataLine">
									<img src="${pageContext.request.contextPath}/style/images/forumpage4.gif" />
								</td>
								<td class="ForumPageTableDataLine">
									<ul class="ForumPageTopicUl">
										<li class="ForumPageTopic">
										<s:if test="%{i == 0}">
											<s:a cssClass="ForumPageTopic" action="course_show?id=%{id}">${name}</s:a>
										</s:if>
										<s:else>
											<s:a cssClass="ForumPageTopic" action="homework_doStatistics?id=%{id}">${name}</s:a>
										</s:else>
										</li>
										<li class="ForumPageTopicMemo">${description}</li>
									</ul>
								</td>
								<s:if test="%{i == 0}">
								<td class="ForumPageTableDataLine"><b>${homeworkCount}</b></td>
								</s:if>
								<td width="3"></td>
							</tr>
						</s:iterator>

					</tbody>

				</table>
			</div>
		</center>
	</div>
</body>
</html>
