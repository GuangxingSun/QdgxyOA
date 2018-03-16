<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>学期设置</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<script type="text/javascript">

	$(function(){
		$(".setConfirm").click(function(){
			var bxq = $(this).parent().parent().find(".bxq");
			var schoolYear = $(this).parent().parent().find(".schoolYear").html();
			var term = $(this).parent().parent().find(".term").html();
			if (bxq.length == 1) {
				return false;
			}
			return confirm('你确定将' + schoolYear + term + '设为本学期吗！');
		});
		$(".delConfirm").click(function(){
			var bxq = $(this).parent().parent().find(".bxq");
			if (bxq.length == 1) {
				alert("无法删除本学期！");
				return false;
			}
		});
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
				学期设置
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="60">当前学期</td>
					<td width="100">学年</td>
					<td width="80">学期</td>
					<td width="120">开学时间</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer">

				<s:iterator value="termList">
					<tr class="TableDetail1 template">
						<td>
							<s:if test="thisTerm == 1">
								<img alt="本学期" class="bxq" src="${pageContext.request.contextPath}/style/images/selected.gif">
							</s:if>
						</td>
						<td class="schoolYear">${schoolYear}</td>
						<td class="term">${term}</td>
						<td><s:date name="time" format="yyyy年MM月dd日" /></td>
						<td>
							<s:a action="sys_termDelete?id=%{id}" cssClass="delConfirm">删除</s:a>
							<s:a action="sys_termEditUI?id=%{id}">修改</s:a>
							<s:a action="sys_setThisTerm?id=%{id}" cssClass="setConfirm">设为当前学期</s:a>
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="sys_termAddUI">
					<img src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
	</div>

</body>
</html>
