<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>在线测评</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/onlineTestShow.css" />
	
<!-- 下面两个js文件 只限本页面使用 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/Ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/My97DatePicker/WdatePicker.js"></script>


<!-- 弹出新的窗口进行测试！带滚动条  -->
<script language="javascript">

window.onload=testUI;

function testUI(){
		var a ='${result.doTest}';	
		 if(a.length != 0){
			window.open("onlineTest_testUI.do?courseId=${course.id}", "正式答题","fullscreen,scrollbars");
		} 
	}
	 $(function() {
		var $load = $("#onload");

		/*控制登录面板居中*/
		$load.css("left", ($(window).width() - 403) / 2 + "px");
		$load.css("top", ($(window).height() - 259) / 2 + "px");
		$load.css("display", "none");
		
		$("#user .load").click(function() {				//打开登录面板时初始化验证码
			$load.css("display", "block");
			createCode($("#onload #checkcode"));
		});
		
		$("#onload .close").click(function() {			//点击叉关闭登陆界面
			$load.css("display", "none");	
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
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				【${course.name}】试题信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<s:form id="pageForm" action="onlineTest_makeTest?courseId=%{courseId}">
		
		<div id="MainArea">
			<div id="PageHead"></div>
			<center>
			
				<div class="ItemBlock_Title1" style="width: 98%;">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="course_list?i=2">测评信息</s:a>
					<font class="MenuPoint"> &gt; </font> ${course.name}课程
				</div>

				<div class="ForumPageTableBorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<!--表头-->
						<tr align="center" valign="middle">
							<td colspan="8" height="50"><span
								style="font-size: 14pt; font-family:宋体">请按照下方提示功能进行相应操作</span></td>
						</tr>
						</tr>
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="9"></td>
						</tr>
						<tr align="center" valign="middle">
							<td width="3" class="ForumPageTableTitleLeft"><img
								border="0" width="1" height="1"
								src="${pageContext.request.contextPath}/style/images/blank.gif" />
							</td>
							
								<td width="220" height="40" align="center"><span
									style="font-size: 13pt;font-weight:bold;font-family:宋体">
										<s:a action="onlineTest_goTest?courseId=%{courseId}">在线测试</s:a>
										<!--<div id="test" onclick="test1()">在线测试</div>-->
								</span></td>
							
								<td width="220" height="40" align="center"><span
									style="font-size: 10pt;font-weight:bold;font-family:宋体">
										<s:a action="onlineTest_addUI?courseId=%{courseId}">录入试题</s:a>
								</span></td>
								<td width="220" height="40" align="center"><span
									style="font-size: 10pt;font-weight:bold;font-family:宋体">
										<s:a action="onlineTest_controlUI?courseId=%{courseId}">管理试题</s:a>
								</span></td>
								<td width="220" height="40" align="center"><span
									style="font-size: 10pt;font-weight:bold;font-family:宋体">
										<s:a action="onlineTest_queryUI?courseId=%{courseId}">查询试题</s:a>
								</span></td>
								<td width="220" height="40"><span
									style="font-size: 10pt;font-weight:bold;font-family:宋体">


								<s:if test="%{ #session.isTeacher == true }"> 
										<div id="user" align="center">
											<a class="load">生成测试</a>
										</div> <%-- <s:a action="onlineTest_queryUI?courseId=%{courseId}">生成测试</s:a> --%>
								</s:if>

								</span></td>
						</tr>
						<tr height="1" class="ForumPageTableTitleLine">
							<td colspan="8"></td>
						</tr>
						<tr height=3>
							<td colspan=8></td>
						</tr>

						<!--主题列表结束-->

						<tr height="3">
							<td colspan="9"></td>
						</tr>

					 	<tr>
  			 				<td valign="middle" align="center" colspan="9">*小提示：本课程已经进行过 ${course.onlineTestCount}次测试！</td>
  				 		</tr>
  				 		<tr>
  				 			<td colspan="8" align="center">
  				 				<FONT color="red"><s:actionerror/></FONT>
							</td>
  				 		</tr>

						
					</table>
				</div>
			</center>
		</div>
		<!-- 不显示部分，当点击生成测试时才显示 -->
		<div id="onload">
			<div class="close">
				<img src="${pageContext.request.contextPath}/style/images/close.png"
					width="30" height="26" alt="关闭" />
			</div>
			<div id="topic">请选择本次测试的结束日期</div>
			<div class="wrap">

				<fileset>
				<td>
				<div class="font">请设定日期：</div>
				<s:textfield name="endTime" onfocus="WdatePicker({minDate:'%y-%M-#{%d+1}',firstDayOfWeek:1,readOnly:true,dateFmt:'yyyy年MM月dd日 '})" cssClass="InputStyle Wdate" /> （学生测试的截至时间）</td> 
				<br>
				<br>
				</fileset>
				<div>
					<input type="submit" id="endTime"  onclick="test()" value="确认"> 
				</div>
			</div>
		</div>
	</s:form>
	<script type="text/javascript">
		function test(){
			getElementById('endTime').find();
		}
	</script>
</body>
</html>
