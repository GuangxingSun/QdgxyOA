<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>论坛</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<%@ include file="/WEB-INF/jsp/public/kindeditor.jspf"%>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<s:if test=" id == null ">发表新主题</s:if>
				<s:else>修改主题</s:else>
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">

		<s:form action="topic_%{id == null ? 'add' : 'edit'}?choose=1" cssStyle="margin: 0; padding: 0;">
			<s:hidden name="forumId" />
			<s:hidden name="id" />
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1">
					<div width=85% style="float:left">
						<font class="MenuPoint"> &gt; </font>
						<s:a action="forum_list">论坛</s:a>
						<font class="MenuPoint"> &gt; </font>
						<s:if test="id == null">
							<s:a action="forum_show?id=%{#forum.id}">${forum.name}</s:a>
						</s:if>
						<s:else>
							<s:a action="forum_show?id=%{forum.id}">${forum.name}</s:a>
							<font class="MenuPoint"> &gt; </font>
							<s:a action="topic_show?id=%{id}">${title}</s:a>
						</s:else>
						<font class="MenuPoint"> &gt;&gt; </font>
						<s:if test=" id == null ">发表新主题</s:if>
						<s:else>修改主题</s:else>（内容至少输入四个字，否则无法提交）
					</div>
				</div>
				<div class="ItemBlockBorder">
					<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
						<tr>
							<td class="InputAreaBg" height="30" width="50px"><div class="InputTitle">标题</div></td>
							<td class="InputAreaBg">
								<div class="InputContent">
									<s:textfield name="title" cssClass="InputStyle required" cssStyle="width:100%" />
								</div>
							</td>
						</tr>
						<tr height="240">
							<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
							<td class="InputAreaBg">
								<div class="InputContent">
									<s:textarea name="content" cssStyle="width:935px;height:240px" />
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
