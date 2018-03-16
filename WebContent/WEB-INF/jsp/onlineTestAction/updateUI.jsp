<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>更新试题</title>
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
    <td valign="top"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				更新试题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
    </table></td>
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
		
		<s:form action="onlineTest_update" >
				<table border="0" cellspacing="10" cellpadding="0">
					<tr>
						<td colspan="2"><s:actionmessage/></td>
					</tr>
				 <tr>
				 <!-- 隐藏试题ID -->
				 <s:hidden name="id"/>
				 <!-- 隐藏课程ID -->
				 <s:hidden name="courseId"/>
					<td>试题题目:</td>
					<td><s:textfield name="subjectTitle" size="80"/></td>
				</tr>
				<tr>
					<td>选项A:</td>
					<td><s:textfield name="optionA" size="20"/></td>
				</tr>
				<tr>
					<td>选项B:</td>
					<td><s:textfield name="optionB" size="20" /></td>
				</tr>
				<tr>
					<td>选项C:</td>
					<td><s:textfield name="optionC" size="20"/></td>
				 </tr>
				 <tr>
					<td>选项D:</td>
					<td><s:textfield name="optionD" size="20"/></td>
				 </tr>
				 <tr>
					<td>答案:</td>
					<td>
						<s:radio name="answer" list="%{#{'A':'A','B':'B','C':'C','D':'D'}}"/>
					</td>
				  </tr>
				  <tr>
					<td valign="top">试题解析:</td>
					<td>
						<s:textarea name="parse" cols="76" rows="10"/>
					</td>
				  </tr>
				  <tr>
  			 		<td valign="middle" align="center" colspan="2">*小提示：本次更新目标课程为：【${course.name}】</td>
  				 	</tr>
				  <tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="submit" value="录入">				  	  
				  	  <input type="reset" value="重置">
			  	  </div>
				</td>
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
