<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>作业管理</title>
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
	function onRadio(id) {
		alert(id);
	}
	$(function() {
		if ($("select[name=orderBy]").val() == '0') {
			$("select[name=asc]").attr("disabled", "disabled");
		}
		
		
		
		
		$(".submit").click(function(){
			if ($(this).parent().parent().find(".day").html() < 0) {
				alert("过了提交作业的时间，无法提交！");
				return false;
			}
		});
		
		
		
		
		$(".collect").click(function(){
			var hasSubmit = $(this).parent().parent().find(".hasSubmit").html();
			hasSubmit = hasSubmit.replace(/[^0-9]/ig, "");
			if (hasSubmit == 0) {
				alert("没有学生提交作业！");
				return false;
			}
			/////////
			var clazzIdString = "";
			$(this).parent().parent().find("input[name='clazzIds']").each(function() {
				 if($(this).attr("checked")){
					 clazzIdString += $(this).val()+",";
	             }
			});
			if (clazzIdString == "") {
				alert("请选择本次作业你要收取的班级");
				return false;
			}
			var href = $(this).attr("href");
			var index = href.indexOf("&");
			if (index != -1) {
				href = href.substring(0, index);
			}
			href += "&clazzIdString=" + clazzIdString;
			$(this).attr("href", href);
			/////////
			var submitCount = $(this).parent().parent().find(".submitCount").html();
			submitCount = submitCount.replace(/[^0-9]/ig, "");
			if (hasSubmit < submitCount) {
				return confirm("还有" + (submitCount - hasSubmit) + "份作业未交，确定要下载吗？");
			}
			
		});
		$(".slider").click(
            function () {
                var id = $(this).find(".sid").html();
            	var l = $(this).css("left");
            	if (l == '0px') {
	                $.post("${pageContext.request.contextPath}/homework_slider.do", {'id':id, 'sc':true});
	               	$(this).css("left","40px");               
            	} else {
            		$.post("${pageContext.request.contextPath}/homework_slider.do", {'id':id, 'sc':false});
            		$(this).css("left","0");
            	}
            }
	    );
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
				【${course.name}】中的作业列表
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<s:form id="pageForm" action="course_show?id=%{id}">
		<div id="MainArea">
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="course_list">我的课程</s:a>
					<font class="MenuPoint"> &gt; </font> ${course.name}课
					<s:if test="%{ #session.user.loginName != 'admin' }">
						<span style="margin-left:30px;">
							<s:a action="homework_addUI?courseId=%{#course.id}">
								<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/addNew.PNG" />
							</s:a>
						</span>
					</s:if>
				</div>

				<div class="ForumPageTableBorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<!--表头-->
						<tr align="center" valign="middle">
							<td width="3" class="ForumPageTableTitleLeft">
								<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
							</td>
							<td class="ForumPageTableTitle" width="150">作业名</td>
							<td class="ForumPageTableTitle" width="265">作业信息</td>
							<s:if test="%{ #session.isStudent != true }">
								<td class="ForumPageTableTitle" width="220">布置对象</td>
							</s:if>
							<td class="ForumPageTableTitle">作业提交情况</td>
							<td class="ForumPageTableTitle" width="300">相关操作</td>
							<s:if test="%{ #session.isTeacher == true }">
								<td class="ForumPageTableTitle" width="100">公开成绩</td>
							</s:if>
							<td width="3" class="ForumPageTableTitleRight">
								<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
							</td>
						</tr>
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="8"></td>
						</tr>
						<tr height=3>
							<td colspan=8></td>
						</tr>

						<!--主题列表-->
						<tbody class="dataContainer">


						<s:hidden name="courseId"/>
						
							<s:iterator value="recordList" id="homework">
								<tr height="35" id="d0" class="template">
									<td></td>
									<td class="Topic" style="text-align: center;">${name}</td>
									<td class="ForumTopicPageDataLine">
										<ul class="ForumPageTopicUl">
											<li class="Author">布置人： ${teacher.name}</li>
											<li class="CreateTime">布置时间： <s:date name="time" format="yyyy年MM月dd日 HH:mm" />&nbsp;第${week}周</li>
											<s:if test=" stime != null ">
												<li class="CreateTime">收取时间： <s:date name="stime" format="yyyy年MM月dd日 " />
													<s:if test="#session.isStudent == true">
														<s:if test="day >= 0">&nbsp;&nbsp;距今 <a style="color: red;">${day}</a>天</s:if>
														<a class="day" style="display: none;">${day}</a>
													</s:if>
												</li>
											</s:if>
										</ul>
									</td>
									<s:if test="%{ #session.isStudent != true }">
										<td class="ForumTopicPageDataLine">
											<ul class="ForumPageTopicUl">
												<s:iterator value="clazzs">
												<li class="Author"><input type="checkbox" name="clazzIds" value="${id}">
												<label><s:a action="clazz_homeworkCondition?id=%{id}&homeworkId=%{#homework.id}" title="点击班级查看本班作业的具体提交情况">${grade}级${major}专业${clazz}班（${studentCount}人）</s:a></label></input></li>
												</s:iterator>
											</ul>
										</td>
										<td class="ForumTopicPageDataLine">
											<ul class="ForumPageTopicUl">
												<li class="CreateTime submitCount">应交： ${submitCount}份</li>
												<li class="CreateTime hasSubmit">已交： <s:property value="%{ hasSubmit }" />份</li>
												<li class="CreateTime">未交： <s:property value="%{ submitCount - hasSubmit }" />份</li>
											</ul>
										</td>
									</s:if>
									<s:else>
										<td class="ForumTopicPageDataLine">
											<ul class="ForumPageTopicUl">
												<li class="CreateTime">状态： <s:if test="%{sub == true}">已&nbsp;交</s:if><s:else><a style="color: red;">未&nbsp; 交</a></s:else></li>
												<s:if test="%{sub == true && sc == true}">
													<li class="CreateTime">
														评分： <s:if test="%{score == 0}">未检查</s:if>
														<s:elseif test="%{score == 1}">不合格</s:elseif>
														<s:elseif test="%{score == 2}">良&nbsp;好</s:elseif>
														<s:elseif test="%{score == 3}">中&nbsp;等</s:elseif>
														<s:elseif test="%{score == 4}">优&nbsp;秀</s:elseif>
													</li>
												</s:if>
											</ul>
										</td>
									</s:else>
									<td style="background-color: #FFF; border-bottom: 1px solid #E3E3E3; text-align: center;">
										<s:a action="homework_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
										<s:a action="homework_editUI?id=%{id}">修改</s:a>
										<s:a action="homework_collect?id=%{id}" cssClass="collect" title="收取被选中的班级的作业">作业收取</s:a>
										<s:if test="%{ #session.user.loginName != 'admin' }">
											<s:a action="homework_download?id=%{id}">作业下载</s:a>
											<s:if test="%{sub == false}">
												<s:a action="submitWork_addUI?homeworkId=%{id}" cssClass="submit" >作业提交</s:a>
											</s:if>
											<s:else>
												<s:a action="submitWork_delete?id=%{submitWorkId}&courseId=%{courseId}" cssClass="submit" >删除提交</s:a>
												<s:a action="submitWork_editUI?id=%{submitWorkId}" cssClass="submit" >作业修改</s:a>
											</s:else>
										</s:if>
									</td>
									<s:if test="%{ #session.isTeacher == true }">
										<td style="background-color: #FFF; border-bottom: 1px solid #E3E3E3; text-align: center;">
											<div class="box">
										        <div class="slider" id="slider${id}"><span class="sid" style="display: none;">${id}</span><a style="display: none;">${sc}</a></div>
										        <span class="m">是</span>
										        <span class="w">否</span>
										    </div>
											<script type="text/javascript">
												var sc = $("#slider${id} a").html();
												if (sc == 'true') {
													$("#slider${id}").css("left","40px");
												} else {
													$("#slider${id}").css("left","0");
												}
											</script>
										</td>
									</s:if>
									<td></td>
								</tr>
							</s:iterator>

						</tbody>
						<!--主题列表结束-->

						<tr height="3">
							<td colspan="9"></td>
						</tr>

					</table>

					<!--其他操作-->
					<div id="TableTail">
						<div id="TableTail_inside">
							<table border="0" cellspacing="0" cellpadding="0" height="100%"
								align="left">
								<tr valign=bottom>
									<td></td>
									<td>
										<%-- 使用自定义标签，以便于回显数据 --%>
										<s:if test="%{ #session.isTeacher == true }">
											<s:select name="i" list="%{  #{0:'按是否收取过作业过滤', 1:'未收取过的作业', 2:'已收取过的作用'}  }" />
											<s:select name="j" list="%{  #{0:'按是否交齐过滤', 1:'未交齐的作业', 2:'已交齐的作业'}  }" />
										</s:if>
										<s:elseif test="%{ #session.isStudent == true }">
											<s:select name="z" list="%{  #{0:'按是否收提交作业过滤', 1:'未提交的作业', 2:'已提交的作业'}  }" />
										</s:elseif> <s:select name="orderBy" onchange="onSortByChange(this.value)" list="%{ #{0:'按时间排序', 1:'按作业布置的时间排序', 2:'按作业提交的时间排序'} }" />
										<s:select name="asc" list="%{ #{false:'降序', true:'升序'} }" />
										<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE" />
									</td>
								</tr>
							</table>
						</div>
					</div>

				</div>
			</center>
		</div>
	</s:form>

	<!--分页信息-->
	<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>

</body>
</html>
