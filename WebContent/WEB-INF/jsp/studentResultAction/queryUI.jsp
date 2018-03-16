<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>精确查询成绩页面</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#addSubjectForm table  td{
		font-size:12px;
	}
	-->
	</style>
	<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top">
    </td>
    <td valign="top">
    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				查询试题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
    </table>
    </td>
    <td width="16" valign="top"></td>
  </tr>
  <tr>
    <td valign="middle"">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="addSubjectForm" align="center"><!--录入试题表单-->
		
		<s:form action="studentResult_query?courseId=%{#course.id}">
			<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
				 	<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="course_list?i=3">我的课程</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="studentResult_controlList?courseId=%{courseId}">${course.name}课程</s:a>
					<font class="MenuPoint"> &gt; </font> 成绩查询
				</div>
					<td>试题题目:</td>
					<td><input name="studentNo" class="{required:true,digits:true,rangelength:[12,12],messages:{required:'请输入要查询的学生学号信息！',rangelength:'请输入由12位数字组成的学号信息！'}}" size="50" ></td>
				  </tr>
				  <tr>
				    <td colspan="2"><div align="center">
				      <input type="submit" name="Submit" value="查询">
				      <input type="reset" name="Submit2" value="重置">
			        </div></td>
			    </tr>
			     <tr>
  			 		<td valign="middle" align="center" colspan="4">*小提示：请输入学生正确学号进行查询！</td>
  				 	</tr>
			</table>
			</s:form>
			</div>
		</td>
        </tr>
      
    </table></td>
  </tr>
</table>
</body>
</html>
