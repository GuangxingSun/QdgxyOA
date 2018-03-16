<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>作业管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>
            <s:if test=" id == null ">作业信息</s:if><s:else> 修改作业</s:else>
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="submitWork_%{id == null ? 'add' : 'edit'}" method="post" enctype="multipart/form-data">
    	<s:hidden name="homeworkId" value="%{#homework.id}" /><s:hidden name="id" />
        <div class="ItemBlock_Title1">
        	<font class="MenuPoint"> &gt; </font>
			<s:a action="course_list">我的课程</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="course_show?id=%{#homework.course.id}">${homework.course.name}</s:a>
			<font class="MenuPoint"> &gt; </font>${homework.name}
			<font class="MenuPoint"> &gt;&gt; </font>
			<s:if test=" id == null ">提交作业</s:if><s:else>修改作业</s:else>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<s:if test=" id != null ">
	                	<tr><td>提交时间：</td><td><s:date name="time" format="yyyy年MM月dd日 HH:mm" /></td></tr>
	                	<tr><td>文件名：</td><td>${fileName}</td></tr>
                	</s:if>
                    <tr>
						<td>请选择要提交的作用文件</td>
                        <td>
							<s:file name="upload" cssClass="InputStyle required {accept:'docx',messages: {required: '请选择要提交的作业文件', accept:'请选择以docx为后缀名的文件'}} " cssStyle="width:450px;" /> *
						</td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

</body>
</html>
