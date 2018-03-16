<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>在线测评</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/onlineTestShow.css" />
<%@page import="javax.servlet.ServletRequest"%>
<!-- 弹出新的窗口进行测试！带滚动条  -->
<%
	String swfPath = (String) request.getAttribute("swfPath");
%>
<script language="javascript">
window.onload=testUI;
	function testUI(){
		var path = '${swfPath1.swfPath}';
		path=encodeURI(encodeURI(path));
		//window.open('printWindow.do?bm=j&selStr='+selStr+'');
		//window.open("clazz_lookStudentHomework.do?swfPath=${swfPath1.swfPath}", "查看作业"); 
		 window.open('clazz_lookStudentHomework.do?swfPath='+path+'', "查看作业",
			"fullscreen,scrollbars");
		 }
</script>

</head>
<body>
	
	<s:hidden name="swfPath"></s:hidden>
</body>
</html>
