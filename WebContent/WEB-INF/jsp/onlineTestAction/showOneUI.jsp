<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看试题</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}

#addSubjectForm table  td {
	font-size: 12px;
}
-->
</style>
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top"></td>
			<td valign="top"><table width="100%" height="31" border="0"
					cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
					<div id="Title_bar">
						<div id="Title_bar_Head">
							<div id="Title_Head"></div>
							<div id="Title">
								<!--页面标题-->
								<img border="0" width="13" height="13"
									src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
								查看试题
							</div>
							<div id="Title_End"></div>
						</div>
					</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="showSubject" align="center"><!--显示试题-->
			<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
				    <td width="60">试题编号:</td>
				    <td>${id}</td>
			    </tr>
				  <tr>
					<td>试题题目:</td>
					<td>${subjectTitle}</td>
				  </tr>
				  <tr>
					<td>选项A:</td>
					<td>${optionA}</td>
				  </tr>
				   <tr>
					<td>选项B:</td>
					<td>${optionB}</td>
				  </tr>
				   <tr>
					<td>选项C:</td>
					<td>${optionC}</td>
				  </tr>
				   <tr>
					<td>选项D:</td>
					<td>${optionD}</td>
				  </tr>
				   <tr>
					<td>答案:</td>
					<td>${answer}</td>
				  </tr>
				  <tr>
					<td valign="top">答案解析:</td>
					<td valign="top">${parse}</td>
				  </tr>
			</table>
			</div>
		</td>
        </tr>
      
    </table>
					
				</td>
				<td background="images/mail_rightbg.gif">&nbsp;</td>
					</tr>
				</table>
</body>
</html>
