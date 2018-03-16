<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>论坛</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				移动主题:${topic.title}
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">
		<s:form action="topic_move?oldForumId=%{forumId}">
			<s:hidden name="id"></s:hidden>
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1">
					<div width=85% style="float:left">
						<font class="MenuPoint"> &gt; </font>
						<s:a action="forum_list">论坛</s:a>
						<font class="MenuPoint"> &gt; </font>
						<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
						<font class="MenuPoint"> &gt; </font>
						<s:a action="topic_show?id=%{#topic.id}">${topic.title}</s:a>
						<font class="MenuPoint"> &gt;&gt; </font> 移动主题
					</div>
				</div>
				<div class="ItemBlockBorder">
					<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
						<tr>
							<td class="InputAreaBg" height="30"><div class="InputTitle">帖子主题</div></td>
							<td class="InputAreaBg"><div class="InputContent">${topic.title}</div></td>
						</tr>
						<tr>
							<td class="InputAreaBg" height="30"><div class="InputTitle">移动到</div></td>
							<td class="InputAreaBg">
								<div class="InputContent">
								<s:select name="forumId" list="forumList" listKey="id" listValue="name" cssStyle="width: 200" />
								</div>
							</td>
						</tr>
						<tr height="30">
							<td class="InputAreaBg" colspan="2" align="center">
								<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;" />
								<a href="javascript:history.go(-1);">
									<img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png" />
								</a>
							</td>
						</tr>
					</table>
				</div>
			</center>
		</s:form>
	</div>

</body>
</html>
