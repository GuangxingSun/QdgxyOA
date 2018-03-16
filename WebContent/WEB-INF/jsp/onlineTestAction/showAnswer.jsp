<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>查看试题解析</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
<link href="${pageContext.request.contextPath}/style/blue/css2.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
.STYLE3 {font-size: 20px}
.STYLE4 {font-size: 22px; font-weight: bold; }
.STYLE5 {color: #FF0000}
-->
</style>
<link href="images/skin.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function close1() {
		window.close();
	}
</script> 
</head>
<body>
	<table width="100%" height="485" border="0" cellpadding="0"
		cellspacing="0" >
		<tr>
			<td width="741" valign="top">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="10">
					<tr>
						<td><div align="center" class="STYLE3">考试时间：60 分钟</div></td>
						<td><div align="center" class="STYLE3">考生：${user.name}</div></td>
						<td><div align="center" class="STYLE3">总分 ：100 分</div></td>
						<td><div align="center" class="STYLE3">得分：${result.testResult}</div></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" bgcolor="#999999" class="STYLE4">选择题(每小题5分，共20个)</td>
					</tr>
					<!--题目开始-->
					<s:iterator value="#request.subjects" var="subject" status="sta">
						<tr>
							<td colspan="4"><strong>第<span class="STYLE5">${sta.index
										+ 1}</span>题&nbsp;${subject.subjectTitle}
							</strong></td>
						</tr>
						<tr>
							<td colspan="4"><strong>A．</strong>${subject.optionA}</td>
						</tr>
						<tr>
							<td colspan="4"><strong>B．</strong>${subject.optionB}</td>
						</tr>
						<tr>
							<td colspan="4"><strong>C．</strong>${subject.optionC}</td>
						</tr>
						<tr>
							<td colspan="4"><strong>D．</strong>${subject.optionD}</td>
						</tr>
						<tr>
							<td height="32" colspan="4" bgcolor="#CCCCCC">
							<strong>【正确答案】：<span class="STYLE5">${subject.answer}</span></strong><br/>
							<strong>【参考解析】：${subject.parse}</strong>
						</tr>
						<tr></tr>
						</s:iterator>  
	  				 <!--题目结束-->
	   				
  				
	   
	   
	   
    </table>
			</td>
  </tr>
</table>
				<tr></tr>
				<tr></tr>
				<tr>
				<tr>
					<td colspan="4"><div align="center">
							<input type="submit" onclick="close1()" value="关  闭  窗  口">
					</div></td>
 				<tr></tr>
				<tr></tr>


</body>
</html>
