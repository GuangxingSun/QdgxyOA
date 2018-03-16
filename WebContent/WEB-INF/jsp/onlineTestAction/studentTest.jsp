<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>正在测试</title>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/style/blue/css2.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
.STYLE3 {
	font-size: 18px;
}

.STYLE4 {
	font-size: 18px;
	font-weight: bold;
}

.STYLE5 {
	color: #FF0000
}
-->
</style>
<script type="text/javascript">
 var ksTime; //定义考试时间以分钟计算
 ksTime = 60;//设置时间 这里设置为0.1代表是6秒,测试用
 if(readCookie("ss")==""){
  setCookie("ss",new Date(),ksTime/60);
 }
 function sT(){
  var tti = new Date();
  var lt  = parseInt((tti-new Date(readCookie("ss")))/1000)
  if((ksTime*60-lt)<0){
   setCookie("ss",new Date(),0);
   alert("考试时间到!\n即将提交试卷!");
   document.forms[0].submit();
  }else{
  	lm = Math.floor(lt / 60);
	ls = lt % 60;
	allY = ksTime*60-lt;
	ym = Math.floor(allY / 60);
	ys = allY % 60;
   document.getElementById("tTime").innerHTML = "考试已经开始了" + lm + "分" + ls + "秒" + ",剩余"  + ym + "分" + ys + "秒";
   var ttt = setTimeout("sT()",1000);
  }
 }
 function readCookie(name) { 
  var cookieValue = ""; 
  var search = name + "="; 
  if(document.cookie.length > 0) { 
   offset = document.cookie.indexOf(search); 
   if (offset != -1) { 
    offset += search.length; 
    end = document.cookie.indexOf(";", offset); 
    if (end == -1) 
     end = document.cookie.length; 
    cookieValue = document.cookie.substring(offset, end) 
   } 
  } 
  return cookieValue; 
 }  
 function setCookie(name, value, hours) { 
  var expire = ""; 
  if(hours != null) { 
   expire = new Date((new Date()).getTime() + hours * 3600000); 
   expire = "; expires=" + expire.toGMTString(); 
  } 
  document.cookie = name + "=" + value + expire; 
 }
 </script>

</head>
<body onload="sT()">
	<table width="100%" height="485" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="741" valign="top" class="rightbian">
			<s:form action="onlineTest_examTest?courseId=%{courseId}">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="10">
						<tr>
							<td><div align="center" class="STYLE3">考试时间：60 分钟</div></td>
							<td><div align="center" class="STYLE3">考生：${user.name}</div></td>
							<td><div align="center" class="STYLE3">总分 ：100 分</div></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td ><div align="center" id="tTime"></div></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3" bgcolor="#999999" class="STYLE4">选择题(每小题5分，共20个)</td>
						</tr>

						<!--题目开始-->

						<s:iterator value="#testLibrary" var="subject" status="sta">
							<tr>
								<input type="hidden" name="subjectIdList" value="${id}" />
								<td colspan="3"><strong>第<span class="STYLE5">${sta.index
											+ 1}</span>题&nbsp;${subject.subjectTitle}
								</strong></td>
							</tr>
							<tr>
								<td colspan="3"><strong>A．</strong>${subject.optionA}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>B．</strong>${subject.optionB}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>C．</strong>${subject.optionC}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>D．</strong>${subject.optionD}</td>
							</tr>
							<tr>
								<td height="32" colspan="3" bgcolor="#CCCCCC">选择答案：
									<s:radio name="option%{#sta.index}" id="option%{#sta.index}" list="#{'A':'A','B':'B','C':'C','D':'D'}"  value="'A'"/>
								</td>
							</tr>
						</s:iterator>
						<!--题目结束-->
						<tr>
							<td colspan="3"><div align="center">
									<input  value="提交答卷 " type="submit"  />
								</div></td>
						</tr>
					</table>
				</s:form>
			</td>
		</tr>
	</table>
</body>
</html>
