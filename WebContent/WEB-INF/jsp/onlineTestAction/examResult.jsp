<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看得分</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
<table width="1003" height="485" border="0" cellpadding="0" cellspacing="0" class="centerbg">
  <tr>
    <td width="149" height="485">&nbsp;</td>
    <td width="741" valign="top" class="rightbian">
    <s:form action="onlineTest_showAnswer?courseId=%{courseId}">
    
	<table width="60%" align="center" cellpadding="10" cellspacing="0" border="1">
      
      	<!-- 隐藏字段，隐藏内容为：之前选择的试题id -->
      	<input type="hidden" name="subjectIds" value="${testTitleId.testTitleIds}"/>
      <tr>
        <td height="35" colspan="2"></td>
      </tr>
      <tr>
        <td height="49" colspan="2"><div align="center"><span class="STYLE4"><strong>${course.name}</strong>考试情况表</span></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">考生姓名:${user.name}</div></td>
        <td><div align="center">考生得分:${result.testResult}分</div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center">
        <input type="submit" value="查看答案"/>
        </div></td>
      </tr>
    </table>
    </s:form>
    </td>
    <td width="113">&nbsp;</td>
  </tr>
</table>
</body>
</html>
