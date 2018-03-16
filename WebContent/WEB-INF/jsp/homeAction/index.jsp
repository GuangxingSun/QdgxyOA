<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>首页</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
	<frame src="home_top.do" name="TopMenu" scrolling="no" noresize />
	<frameset cols="180,*" id="resize">
		<frame noresize name="menu" src="home_left.do" scrolling="auto" />
		<s:if test="%{ #session.user.loginName == 'admin' && #session.thisTerm == null }">
			<frame noresize name="right" src="sys_termAddUI.do" scrolling="auto" />		
		</s:if>
		<s:else>
			<frame noresize name="right" src="home_right.do" scrolling="auto" />		
		</s:else>
	</frameset>
	<frame noresize name="status_bar" scrolling="no" src="home_bottom.do" />
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>

