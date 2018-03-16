<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
<script type="text/javascript">
	function onSortByChange(selectedValue) {
		if (selectedValue == 0) {
			$("select[name=asc]").attr("disabled", "disabled");
		} else {
			$("select[name=asc]").removeAttr("disabled");
		}
	}
	$(function() {
		if ($("select[name=orderBy]").val() == '0') {
			$("select[name=asc]").attr("disabled", "disabled");
		}
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				查看用户
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<s:form id="pageForm" action="user_filtrate_loginName">
		<div id="MainArea">
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1" style="width: 98%;">
				</div>
				<div class="ForumPageTableBorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="1" class="ForumPageTableTitleLine">
								<td colspan="8"></td>
							</tr>

						<!--主题列表-->
						<tbody class="dataContainer" datakey="topicList">
							<tr height="35" id="d0" class="template">
								<td width="70"></td>
								<td width="130">
									<s:a action="user_list">列出全部用户</s:a>
								</td>
								<td colspan="1">按照学号精确查找：
									<s:textfield name="loginName" />
									<input type="submit"/>
								</td>
								<td colspan="1"></td>
							</tr>
							<tr height="5" id="d0" class="template">
								<td colspan="1"></td>
							</tr>
							
							
							<%-- <tr height="15" class="template">
								<td width="70"></td>
								<td width="150">按照部门岗位条件筛选：</td>
								<td>
										使用自定义标签，以便于回显数据
										<s:select name="viewType" list="%{  #{0:'全部主题', 1:'全部精华贴'}  }" />
										<s:select name="orderBy" onchange="onSortByChange(this.value)" list="%{ #{0:'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)', 1:'只按最后更新时间排序', 2:'只按主题发表时间排序', 3:'只按回复数量排序'} }" />
										<s:select name="asc" list="%{ #{false:'降序', true:'升序'} }" />
										<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE" />
									</td>
							</tr> --%>
							
							<tr height="10"></tr>
						</tbody>
						<!--主题列表结束-->

						<tr height="3">
						
							<td colspan="8"></td>
						</tr>

					</table>

					<%-- <!--其他操作-->
					<div id="TableTail">
						<div id="TableTail_inside">
							<table border="0" cellspacing="0" cellpadding="0" height="100%"
								align="left">
								<tr valign=bottom>
									<td></td>
									<td>
										使用自定义标签，以便于回显数据
										<s:select name="viewType" list="%{  #{0:'全部主题', 1:'全部精华贴'}  }" />
										<s:select name="orderBy" onchange="onSortByChange(this.value)" list="%{ #{0:'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)', 1:'只按最后更新时间排序', 2:'只按主题发表时间排序', 3:'只按回复数量排序'} }" />
										<s:select name="asc" list="%{ #{false:'降序', true:'升序'} }" />
										<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE" />
									</td>
								</tr>
							</table>
						</div>
					</div> --%>

				</div>
			</center>
		</div>
	</s:form>

	<!--分页信息-->
	<%-- <%@ include file="/WEB-INF/jsp/public/pageView.jspf"%> --%>

</body>
</html>
