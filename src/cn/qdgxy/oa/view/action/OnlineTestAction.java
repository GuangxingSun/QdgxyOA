package cn.qdgxy.oa.view.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.OnlineTestCase;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.TestLibrary;
import cn.qdgxy.oa.domain.TestTitleId;
import cn.qdgxy.oa.util.Page;
import cn.qdgxy.oa.util.PageResult;

/**
 * 在线测试Action
 * 
 * @author Guangxing
 * 
 */
@Controller
@Scope("prototype")
@SuppressWarnings(value = { "serial", "unchecked" })
public class OnlineTestAction extends BaseAction<TestLibrary> {
	private Long courseId;// 用于临时存放Course的id
	private Long subjectID;// 获取试题的ID
	private Date endTime; // 获取设置的测试的最截至时间
	
	
	private List<Long> subjectIdList;//学生考试的题目ID

	// 分页有关信息
	// 管理试题
	private String subjectTitle; // 试题标题
	private int currentPage; // 当前页
	
	//抽取试题id
	private String subjectIds;

	
	
	//临时数据
	List<TestLibrary> testLibraries = new ArrayList<TestLibrary>();
	
	// 根据不同的用户登录输出不同的课程信息
	public String list() throws Exception {
		return "list";
	}

	// 根据点击的课程信息显示相应的测试相关信息
	public String testList() throws Exception {
		// 获得当前课程
		Course course = courseService.getById(courseId);
		ActionContext.getContext().put("course", course);
		return "show";
	}

	// 录入试题页面
	public String addUI() throws Exception {
		// 获得要录入试题的课程
		Course course = courseService.getById(courseId);
		ActionContext.getContext().getValueStack().push(courseId);
		ActionContext.getContext().put("course", course);

		return "addUI";
	}

	// 录入试题
	public String add() throws Exception {
		// 准备数据（课程信息）
		Course course = courseService.getById(courseId);
		// 封装对象
		TestLibrary testLibrary = new TestLibrary();

		// 表单中的参数
		testLibrary.setSubjectTitle(model.getSubjectTitle());
		testLibrary.setOptionA(model.getOptionA());
		testLibrary.setOptionB(model.getOptionB());
		testLibrary.setOptionC(model.getOptionC());
		testLibrary.setOptionD(model.getOptionD());
		testLibrary.setAnswer(model.getAnswer());
		testLibrary.setParse(model.getParse());
		testLibrary.setCourse(course);

		// 调用业务方法
		if (onlineTestService.saveSubject(testLibrary)) {
			return "toShow";
		} else {// 如果输入的试题已经添加过

			return "toAddUI";
		}

	}

	// 用来传递courseId（实现当提示重复添加时候，清楚之前输入的信息）
	public String sendCourseId() throws Exception {
		// 准备回显数据（课程信息）
		Course course = courseService.getById(courseId);
		this.addActionError("该试题已经添加过了，请不要重复添加!");
		ActionContext.getContext().put("course", course);
		return "addUI";
	}

	// 管理试题页面
	public String controlUI() throws Exception {
		Course course = courseService.getById(courseId);

		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = onlineTestService.querySubjectByPage(page,
				course);

		List<TestLibrary> testLibraries = pageResult.getList();// 获得试题记录
		page = pageResult.getPage();// 获得分页信息

		ActionContext.getContext().put("testLibraries", testLibraries);
		ActionContext.getContext().put("course", course);
		ActionContext.getContext().getValueStack().push(page);
		return "controlUI";
	}

	// 查看某个试题
	public String showOneUI() throws Exception {
		TestLibrary testLibrary = onlineTestService.showSubjectParticular(model
				.getId());

		ActionContext.getContext().getValueStack().push(testLibrary);
		return "showOneUI";
	}

	// 更新试题,获得更新前的试题
	public String updateUI() throws Exception {
		TestLibrary testLibrary = onlineTestService.showSubjectParticular(model
				.getId());

		Course course = courseService.getById(courseId);

		ActionContext.getContext().put("course", course);
		ActionContext.getContext().getValueStack().push(testLibrary);
		return "updateUI";
	}

	// 更新试题
	public String update() throws Exception {
		Course course = courseService.getById(courseId);
		// 封装对象

		TestLibrary testLibrary = new TestLibrary();
		testLibrary.setId(model.getId());
		testLibrary.setSubjectTitle(model.getSubjectTitle());
		testLibrary.setOptionA(model.getOptionA());
		testLibrary.setOptionB(model.getOptionB());
		testLibrary.setOptionC(model.getOptionC());
		testLibrary.setOptionD(model.getOptionD());
		testLibrary.setAnswer(model.getAnswer());
		testLibrary.setParse(model.getParse());

		testLibrary.setCourse(course);

		ActionContext.getContext().getValueStack().push(course);

		// 调用业务方法
		if (onlineTestService.updateSubject(testLibrary)) {
			return "toControlUI";
		} else {// 如果输入的试题已经添加过
			addActionError("该试题已经添加过了，请不要重复更新添加!");
			return "toUpdateUI";
		}

	}

	// 删除某个试题
	public String delete() throws Exception {
		onlineTestService.deleteInfo(onlineTestService.getById(model.getId()));
		return "toControlUI";
	}

	// 查询试题页面
	public String queryUI() throws Exception {
		Course course = courseService.getById(courseId);
		ActionContext.getContext().put("course", course);
		return "queryUI";
	}

	// 查询试题
	public String query() throws Exception {
		
		System.out.println("课程ID为："+courseId);
		Course course = courseService.getById(courseId);
		String subjectTitle = model.getSubjectTitle();
		if(null ==  subjectTitle || subjectTitle.length() == 0){
				ServletActionContext 
				.getResponse()
				.getWriter()
				.print(//
				"<script language=javascript>alert('请输入课程题目信息！');history.go(-1);</script>");
		}else{
				Page page = new Page();
				page.setEveryPage(10);// 每页显示10条记录
				page.setCurrentPage(currentPage);// 设置当前页
				PageResult pageResult = onlineTestService.likeQueryBySubjectTitle(
						subjectTitle, page, course);
				if(pageResult == null){
					ServletActionContext 
					.getResponse()
					.getWriter()
					.print(//
					"<script language=javascript>alert('没有查询到您想要查询的信息！');history.go(-1);</script>");
					
				}else{
					System.out.println("查询试题数目：" + pageResult.getList().size());
					List<TestLibrary> testLibraries = pageResult.getList();// 获得试题记录
			
					List<TestLibrary> newTestLibrary = new ArrayList<TestLibrary>();// 新的记录
					// 给关键字标红
					for (TestLibrary testLibrary : testLibraries) {
						String newTitle = testLibrary.getSubjectTitle().replaceAll(
								subjectTitle,
								"<font color='red'>" + subjectTitle + "</font>");
						testLibrary.setSubjectTitle(newTitle);
						newTestLibrary.add(testLibrary);
					}
	
				page = pageResult.getPage();// 获得分页信息
				ActionContext.getContext().put("testLibraries", newTestLibrary);
				ActionContext.getContext().put("page", page);
				ActionContext.getContext().put("course", course);
				}
			}
		return "controlUI";
	}

	// 生成测试试题
	public String makeTest() throws Exception {

		// 准备数据
		Course course = courseService.getById(courseId);
		ActionContext.getContext().put("course", course);
		
		//获取本课程的试题个数
		List<TestLibrary> testLibraries = onlineTestService.getTestLibraryByCourse(course);
		if(endTime == null){
			this.addActionError("请设置测试的截至时间！");
		}else if(testLibraries.size() < 20){
			this.addActionError("该课程题库数目小于20，请添加题目后再进行测试！");
		}else{
			System.out.println("sum："+testLibraries.size());
		// 根据课程id获取绑定该课程的有关测试信息的个数
		int no = course.getOnlineTestCount();
		course.setOnlineTestCount(no + 1);
		onlineTestService.updateInfo(course);

		// 封装考试信息情况
		OnlineTestCase onlineTestCase = new OnlineTestCase();
		onlineTestCase.setStartTime(new Date());
		onlineTestCase.setEndTime(endTime);
		onlineTestCase.setNumber(no + 1);
		onlineTestCase.setCourse(course);
		onlineTestCase.setThisTerm(homeworkService.getThisTerm());
		// 获取关联本课程的学生个数
		int count = onlineTestService.getCountByCourse(course);
		System.out.println("同学的个数：" + count);

		onlineTestCase.setShouldSubmit(count);
		onlineTestCase.setActualSubmit(0);

		onlineTestService.savaTestCase(onlineTestCase);

		// 初始化相关学生的成绩，并且设置学生成绩信息为"未做"即 赋值 "0"
		Result result = new Result();
		result.setYesOrNo(0);
		result.setIsOrNot(0);
		result.setCourse(course);
		result.setTerm(homeworkService.getThisTerm());
		result.setCount(no + 1);

		// 把当前的信息初始化给选该课程的每位同学
		onlineTestService.initStudentResult(result, course);
		}
		return "show";
	}

	// --------------------------在线测试,生成试题,学生端----------------------------
	public String goTest() throws Exception {
		// 获得测试的课程
		Course course = courseService.getById(courseId);
		// 完成学生考试情况表的更新
		// 获得当前登录的学生getCurrentUser()
		// 根据当前 登录的学生、课程、本学期、第几次成绩进行查询出最终成绩
		Result result = onlineTestService.getResult(getCurrentUser(), course,
				homeworkService.getThisTerm(), course.getOnlineTestCount());
		ActionContext.getContext().put("course", course);
		if (result == null) {// 如果result为null 则说明该课程没有进行测试
			this.addActionError("该课程没有要进行的测试！");
		}else {
			//根据相应信息获得本次测试的基本型况信息
			OnlineTestCase onlineTestCase = onlineTestService.getTestCase(course,homeworkService.getThisTerm(),course.getOnlineTestCount());
			//判断本次测试是否已经结束
			if(!onlineTestCase.getEndTime().before(new Date())){
				// 如果是本次测试的第一次抽题测试
				if (result.getDoTest() == null ) {
					// 开始测试时间
					result.setDoTest(new Date());
					// 更新成绩表
					ActionContext.getContext().put("result", result);
					onlineTestService.updateInfo(result);
				}else if(result.getYesOrNo() == 0){//判断 学生是否已经提交答案
					ActionContext.getContext().put("result", result);
				}else{
					this.addActionError("您已经完成了本次测试！");
				}
			}else{
				this.addActionError("就近的一次测试已经结束！如果您还没有进行测试那么您的本次测试成绩为0！");
			}
		}
		return "show";
	}
	
	public String testUI() throws Exception{
		TestTitleId testTitleId = new TestTitleId();//抽题临时表
		//临时数据
		List<TestLibrary> testLibraries;
		// 获得测试的课程
		Course course = courseService.getById(courseId);
		Result result = onlineTestService.getResult(getCurrentUser(), course,
				homeworkService.getThisTerm(), course.getOnlineTestCount());
		
		//如果是第一次抽题,则保存已经抽到的题目id号
		if(result.getIsOrNot() == 0){
			result.setIsOrNot(1);
			onlineTestService.updateInfo(result);
			System.out.println("1111111111111");
			// 随机抽取题目 在绑定该课程的题目中抽取题目
			testLibraries = onlineTestService.randomFindSubject(
				20, course);// 获得试题记录 这设置为10道题
			System.out.println("抽出的题目个数为："+testLibraries.size());
			String titleIdString = null;
			for(int i = 0 ;i<testLibraries.size(); i++){
				if(titleIdString == null){
					titleIdString = testLibraries.get(i).getId().toString();
				}else{
					titleIdString=testLibraries.get(i).getId()+","+titleIdString;
				}
			}
			System.out.println("titleIdString:"+titleIdString);
			testTitleId.setTestTitleIds(titleIdString);
			testTitleId.setCourse(course);
			testTitleId.setStudent(getCurrentUser());
			onlineTestService.saveInfo(testTitleId);
			
			System.out.println("题目的id数组为："+titleIdString);
			ActionContext.getContext().put("testLibrary", testLibraries);
		}else{
			//获取第一次的抽题信息
			testTitleId = onlineTestService.findTestTitleId(course,getCurrentUser());
			testLibraries = onlineTestService.gainSubject(testTitleId.getTestTitleIds());
			ActionContext.getContext().put("testLibrary", testLibraries);
		}
		ActionContext.getContext().put("testLibrary", testLibraries);
		ActionContext.getContext().put("course", course);
		return "goTest";
	}

		//提交试卷后计算试题题目得分
		public String examTest() throws Exception{
			HttpServletRequest request = ServletActionContext.getRequest();
			List<String>subjectAnswerList = new ArrayList<String>();
			for(int i = 0 ; i<20 ; i++){
				subjectAnswerList.add(request.getParameter("option"+i));
			}
			System.out.println("学生做的第一个题目ID是:"+subjectIdList.get(0));
			//准备数据	1、 获得测试的课程2、最终成绩3、根据当前 登录的学生、课程、本学期、第几次成绩进行查询出最终成绩
			Course course = courseService.getById(courseId);
			System.out.println("测试课程为："+course.getName());
			
			System.out.println("答案第三个答案为："+subjectAnswerList.get(2));
			//接受同学答题的答案
			
			System.out.println("本课程的测试数目"+course.getOnlineTestCount());			
			System.out.println("学生一共做了:"+subjectAnswerList.size()+"道题");
			
			int score = onlineTestService.accountResult(subjectIdList, subjectAnswerList);
			Result result = onlineTestService.getResult(getCurrentUser(), course,
					homeworkService.getThisTerm(), course.getOnlineTestCount());
			
			//封装对象 1、设置学生已经提交试卷 2、最终考试结果
			result.setYesOrNo(1);
			result.setTestResult(score);
			result.setDownTest(new Date());
			//更新学生成绩
			onlineTestService.updateInfo(result);
			
			System.out.println("该学生获得的成绩为："+score);
			
			//删除绑定该学生和课程的试题临时表中的数据,先获取指定字段
			TestTitleId testTitleId = onlineTestService.getTestTitleIdBySC(getCurrentUser(),course);
			
			subjectIds = testTitleId.getTestTitleIds();
			
			ActionContext.getContext().put("course", course);
			ActionContext.getContext().put("result", result);
			ActionContext.getContext().put("testTitleId", testTitleId);
			
			//删除之前学生考试时抽取试题的临时数据
			onlineTestService.deleteInfo(testTitleId);
			
			return "examResult";
		}
		
		//查看试题解析
		public String showAnswer() throws Exception{
			Course course = courseService.getById(courseId);
			//获得考试得分
			Result result = onlineTestService.getResult(getCurrentUser(), course,
					homeworkService.getThisTerm(), course.getOnlineTestCount());
		
			List<TestLibrary> subjects = new ArrayList<TestLibrary>();//保存学生考过的题目
			String[] subjectIDs = subjectIds.split(",");
			List<String> sList = Arrays.asList(subjectIDs);
			
			for(String subjectID : sList) {
				TestLibrary subject = onlineTestService.showSubjectParticular(Long.parseLong(subjectID));//通过试题编号查找试题
				subjects.add(subject);
			}
			
			ActionContext.getContext().put("subjects", subjects);
			ActionContext.getContext().put("course", course);
			ActionContext.getContext().put("result", result);
			
			return "showAnswer";
		}
		
	
	// ====================
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Long subjectID) {
		this.subjectID = subjectID;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<Long> getSubjectIdList() {
		return subjectIdList;
	}

	public void setSubjectIdList(List<Long> subjectIdList) {
		this.subjectIdList = subjectIdList;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

}
