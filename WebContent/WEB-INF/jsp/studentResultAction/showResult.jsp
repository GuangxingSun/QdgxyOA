<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>成绩信息</title>
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
					成绩列表
				</div>
				<div id="Title_End">
				</div>
		</div>
	</div>
	<div id="MainArea">
		<center>
		<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="studentResult_list?i=3">我的课程</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="studentResult_controlList?courseId=%{#course.id}">${course.name}课程</s:a>
					<font class="MenuPoint"> &gt; </font> 成绩查询
			</div>
		
			<div class="ForumPageTableBorder" style="margin-top: 25px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				
					<!--表头-->
						<tr align="center" valign="middle">
							<tr height="1" align="center" valign="middle">
							
										<td colspan="2" class="ForumPageTableTitleLeft">姓名</td>
										
									<s:if test="#subList.size<=#onlineList.size">
										<s:iterator value="onlineList" status="sta">
							
											<td colspan="1" class="ForumPageTableTitle">
											第${sta.index+ 1}次
											</td>
						 				</s:iterator>
									</s:if>
									<%-- 当作业数量多余测试数量时候 --%>
									<s:else>
										<s:iterator value="subList" status="sta">
											<td colspan="1" class="ForumPageTableTitle">
											第${sta.index+ 1}次
											</td>
						 				</s:iterator>
									</s:else>
									
										<td colspan="2" class="ForumPageTableTitle">平均成绩</td>
									
							</tr>						
						</tr>
						<tr height="1" width="100%" class="ForumPageTableTitleLine">
						</tr>
						<tr height="3" width="100%">
						</tr>

					<!--版面列表-->
					<tbody class="dataContainer">
						<tr height="4" align="center" valign="middle">
								<td rowspan="2" colspan="2" class="ForumPageTableTitleLeft">
									<s:if test="name == null">
										${user.name}
									</s:if>
									<s:else>
										${name}
									</s:else>


								</td>
								 <s:iterator value="onlineList">
									<td colspan="1" class="ForumPageTableTitle">
										${testResult}
									</td>
							 	</s:iterator>
							 	<td colspan="1" class="ForumPageTableTitle">
							 		${avg}
							 	</td>
						</tr>
						<tr>
							<s:iterator value="subList">
									<td colspan="1" class="ForumPageTableTitle">
									<s:if test="score == 0">
										未检查
									</s:if>
									<s:elseif test="score == 1">
										不合格
									</s:elseif>
									<s:elseif test="score == 2">
										良
									</s:elseif>
									<s:elseif test="score == 3">
										中等
									</s:elseif>
									<s:elseif test="score == 4">
										优
									</s:elseif>
									</td>
							 </s:iterator>
						</tr>
					</tbody>

				</table>
			</div>
			
		</center>
	</div>
</body>
</html>
