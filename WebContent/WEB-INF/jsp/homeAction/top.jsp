<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>电子作业管理系统</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="style/blue/top.css" />
</head>

<body class="PageBody" style="margin: 0">
	
	<div id="Logo_img"><img border="0" src="style/images/top/d_banner_school.gif"></div>
	<div id="Head1">
		<div id="Logo">
			<font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">电子作业管理系统</font>
		</div>

		<div id="Head1Right">
			<div id="Head1Right_UserName">
				<img border="0" width="13" height="14"
					src="style/images/top/user.gif" /> 您好， <b>${user.name} <s:if
						test="#session.isTeacher == true">（老师）</s:if> <s:if
						test="#session.isStudent == true">（学生）</s:if>
				</b>
			</div>
			<!-- <div id="Head1Right_UserDept"></div>

			<div id="Head1Right_Time"></div> -->
		</div>

		<div id="Head1Right_SystemButton">
			<s:a action="loginout_logout" target="_parent">
				<img width="78" height="20" alt="退出系统"
					src="style/blue/images/top/logout.gif" />
			</s:a>
		</div>
	</div>

	<div id="Head2">
		<div id="Head2_FunctionList">
		<s:if test="#session.isStudent == true && #notice != 0">通知：你有${notice}份作业没交，请及时提交</s:if>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${thisTerm.schoolYear}${thisTerm.term}&nbsp第${thisWeak}周&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<a href="javascript: window.parent.right.location.reload(true);"
				style="color: #ffffff; text-decoration: none;"><img alt="刷新" src="style/blue/images/desktop/refresh.gif"></a>
		</div>
	</div>

</body>
</html>
