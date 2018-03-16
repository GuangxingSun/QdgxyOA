<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>试题管理页面</title>
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
	#manageSubject table  td{
		font-size:12px;
	}
	-->
	</style>
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
				管理试题
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
			<div id="manageSubject" align="center"><!--管理试题-->
		
		
		<table width="95%" cellspacing="10">
			<!-- 隐藏 被添加试题的课程的ID-->
			<s:hidden name="courseId"/>
				<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="course_list?i=2">测评信息</s:a>
					<font class="MenuPoint"> &gt; </font>
					
					<s:a action="onlineTest_testList?courseId=%{courseId}">${course.name}课程</s:a>
					<font class="MenuPoint"> &gt; </font> 管理试题
				</div>
			
				  <tr align="center">
					<td>试题编号</td>
					<td>试题标题</td>
					<td>正确答案</td>
					<td>更新时间</td>
					<td>查看试题</td>
					<td>更新试题</td>
					<td>删除试题</td>
				  </tr>
				  <s:iterator value="testLibraries">
				  	 <tr align="center">
							<td>${id}</td>
							<td>${subjectTitle}</td>
							<td>${answer}</td>
							<td>${putTime}</td>
							<td><s:a action="onlineTest_showOneUI?id=%{id}">查看</s:a></td>
							<td><s:a action="onlineTest_updateUI?id=%{id}&courseId=%{courseId}">更新</s:a></td>
							<td><s:a action="onlineTest_delete?id=%{id}&courseId=%{courseId}">删除</s:a></td>
					  	</tr>
				  </s:iterator>	
				  <tr>
				  	<td colspan="6" align="center">
				  		共${totalCount}条纪录，当前第${currentPage}/${totalPage}页，每页${everyPage}条纪录
				  	<s:if test="hasPrePage">
                		<s:a action="onlineTest_controlUI?currentPage=1&courseId=%{courseId}">首页</s:a> | 
                		<s:a action="onlineTest_controlUI?currentPage=%{currentPage - 1}&courseId=%{courseId}">上一页</s:a> | 
               		</s:if>
               		<s:else>
               		首页 | 上一页 | 
               		</s:else>
               		<s:if test="hasNextPage">
                		<s:a action="onlineTest_controlUI?currentPage=%{currentPage + 1}&courseId=%{courseId}">下一页</s:a> | 
                		<s:a action="onlineTest_controlUI?currentPage=%{totalPage}&courseId=%{courseId}">尾页</s:a>
               		</s:if>
               		<s:else>
               		下一页 | 尾页
               		</s:else>
				  	</td>
				  </tr>	  
				  
				  <tr></tr>
				  
				   <tr>
  			 		<td valign="middle" align="center" colspan="8">*小提示：本次操作试题属于课程：【${course.name}】</td>
  				 	</tr>
			</table>		
			</div>
		</td>
        </tr>
      
    </table></td>
    <td background="images/mail_rightbg.gif">&nbsp;</td>
  </tr>
</table>
</body>
</html>
