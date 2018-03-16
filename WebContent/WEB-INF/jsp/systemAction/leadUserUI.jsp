<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户导入</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript">
	function callback(data){
		if(data == 'leadUserError')
			$(".msg").html("excel文件有误！请修改后重试。");
		if(data == 'leadUserOK')
			$(".msg").html("用户导入成功，可点击用户管理查看。");
    }
</script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>
            	从excel导入用户
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
        <s:form action="lead_leadUser" method="post" enctype="multipart/form-data" target="hidden_frame">
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td>请选择excel文件</td>
                        <td>
							<s:file name="src" cssClass="InputStyle required" cssStyle="width:450px;" /> *
						</td>
                    </tr>
					<tr><td><p class="msg"></p></td></tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" />
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
        </s:form>
</div>

<iframe name='hidden_frame' style='display:none'></iframe>
</body>
</html>
