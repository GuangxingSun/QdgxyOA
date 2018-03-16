<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加试题</title>
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
				录入试题
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
		
		
		<s:form action="onlineTest_add">
			<!-- 隐藏 被添加试题的课程的ID-->
			<s:hidden name="courseId"></s:hidden>
			
			<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="course_list?i=2">测评信息</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="onlineTest_addUI?courseId=%{courseId}">添加试题</s:a>
					<font class="MenuPoint"> &gt; </font> ${course.name}课程
				</div>
			
			<table border="0" cellspacing="10" cellpadding="0">
				<tr>
					<td colspan="2"><FONT color="red">
					<s:actionerror/></FONT></td>
				</tr>
				<tr>
					<td>试题题目:</td>
					<td><s:textfield name="subjectTitle" cssClass="required" size="80"/></td>
				</tr>
				<tr>
					<td>选项A:</td>
					<td><s:textfield name="optionA"  cssClass="required" size="20"/></td>
				</tr>
				<tr>
					<td>选项B:</td>
					<td><s:textfield name="optionB" cssClass="required"  size="20" /></td>
				</tr>
				<tr>
					<td>选项C:</td>
					<td><s:textfield name="optionC" cssClass="required" size="20" /></td>
				 </tr>
				 <tr>
					<td>选项D:</td>
					<td><s:textfield name="optionD" cssClass="required" size="20" /></td>
				 </tr>
				 <tr>
					<td>答案:</td>
					<td>
						<s:radio name="answer" cssClass="required" list="%{#{'A':'A','B':'B','C':'C','D':'D'}}" value="'A'"/>
					</td>
				  </tr>
				  <tr>
					<td valign="top">试题解析:</td>
					<td>
						<s:textarea name="parse" cols="76" rows="10"/>
					</td>
				  </tr>
				  <tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="submit" value="录入">				  	  
				  	  <input type="reset" value="重置">
			  	  </div>
				</td>
				  </tr>
				  
				   <tr>
  			 		<td valign="middle" align="center" colspan="2">*小提示：本次添加试题属于课程：【${course.name}】</td>
  				 	</tr>
			</table>
			</s:form>	
			</div>
		</td>
        </tr>
      
    </table></td>
    <td background="images/mail_rightbg.gif">&nbsp;</td>
  </tr>
</table>
</body>
</html>
