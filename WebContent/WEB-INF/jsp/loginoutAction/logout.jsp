<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>您已退出QdgxyOA系统</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link href="${pageContext.request.contextPath}/style/blue/logout.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table border=0 cellspacing=0 cellpadding=0 width=100% height=100%>
		<tr>
			<td align=center>
				<div id=Logout>
					<div id=AwokeMsg>
						<img id=LogoutImg src="${pageContext.request.contextPath}/style/blue/images/logout/logout.gif" border=0 />
						<img id=LogoutTitle src="${pageContext.request.contextPath}/style/blue/images/logout/logout1.jpg" border=0 />
					</div>
					<div id=LogoutOperate>
						<img src="${pageContext.request.contextPath}/style/blue/images/logout/logout2.gif" border=0 />

						<%-- 这个超链接不使用<s:a>标签！！ --%>
						<a href="${pageContext.request.contextPath}/loginout_loginUI.do">重新进入系统</a>

						<img src="${pageContext.request.contextPath}/style/blue/images/logout/logout3.gif" border=0 />
						<a href="javascript:window.open('', '_self'); window.close();">关闭当前窗口</a>
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
