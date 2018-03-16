package cn.qdgxy.oa.view.action;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.StudentResultCase;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.util.QueryHelper;

/**
 * 
 * 查询学生成绩所用方法
 * 
 * @author Guangxing
 * 
 */
@SuppressWarnings({"serial"})
@Component
@Scope("prototype")
public class StudentResultAction extends BaseAction<Result> {
	private Long courseId;// 接受传过来课程的ID
	private double avg;// 用来暂时存储学生测试的平均成绩
	private String studentNo;// 用来获取jsp页面要查询的同学的学号

	private Long clazzId;
	
	// 根据不同用户登录列出不同的课程的成绩信息
	public String list() throws Exception {
		return "list";
	}

	// 查看学生成绩的具体操作列表
	public String controlList() throws Exception {
		// 获得要录入试题的课程
		Course course = courseService.getById(courseId);
		System.out.println("courseId:" + courseId);

		ActionContext.getContext().put("course", course);
		return "controlList";
	}

	public String one() throws Exception {
		System.out.println("查询个人成绩获取的courseId:" + courseId);
		Course course = courseService.getById(courseId);

		// 准备数据1：学生作业信息
		// 准备数据2：学生测试信息
		if (isStudent()) {// 如果是学生操作
			System.out.println("这是学生操作！");
			// 学生作业信息数据
			List<SubmitWork> subList = null;
			subList = studentResultService.findSubmitWork(getCurrentUser(),
					thisTerm(), course);
			System.out.println("查询出学生作业信息的记录数为：" + subList.size());

			// 学生测试信息数据
			List<Result> onlineList = null;
			onlineList = studentResultService.findOnlineTestRresultOne(
					getCurrentUser(), thisTerm(), course);
			System.out.println("查询出学生在线测试的记录数：" + onlineList.size());

			// 准备平均成绩
			if (onlineList.size() > 0) {
				int count = 0;
				for (Result result : onlineList) {
					count += result.getTestResult();
				}
				avg = (long) (count / onlineList.size());
				System.out.println("学生测试的平均成绩：" + avg);
			}

			ActionContext.getContext().put("subList", subList);
			ActionContext.getContext().put("onlineList", onlineList);
			ActionContext.getContext().put("course", course);

		} else {// 如果是老师或者管理员查看
			return "noDataUI";
		}
		return "showResult";
	}

	// 根据学号查询成绩到查询页面
	public String queryUI() throws Exception {
		Course course = courseService.getById(courseId);
		ActionContext.getContext().put("course", course);
		return "queryUI";
	}

	// 根据输入的学号进行查询操作
	public String query() throws Exception{
		Course course = courseService.getById(courseId);
		if(null == studentNo || studentNo.length() == 0 || "".equals(studentNo)){
			return null;
		}else{
			//当确实输入了学生学号信息
			//获得要查询的学生
			User student = userService.getByLoginName(studentNo);
			if(null == student){//当没有这个这个学生的时候
				ServletActionContext 
				.getResponse()
				.getWriter()
				.print(//
				"<script language=javascript>alert('对不起！没有查询到您查询的用户！！');history.go(-1);</script>");
				return null;
			}else{
				
				
				//学生作业信息数据
				List<SubmitWork> subList = null;
				subList = studentResultService.findSubmitWork(student,thisTerm(),course);
				System.out.println("查询出学生作业信息的记录数为："+subList.size());
				
				//学生测试信息数据
				List<Result> onlineList = null;
				onlineList = studentResultService.findOnlineTestRresultOne(student,thisTerm(),course);
				System.out.println("查询出学生在线测试的记录数："+onlineList.size());
				// 准备平均成绩
				if (onlineList.size() > 0) {
					int count = 0;
					for (Result result : onlineList) {
						count += result.getTestResult();
					}
					avg = (long) (count / onlineList.size());
					System.out.println("学生测试的平均成绩：" + avg);
				}
				
				ActionContext.getContext().put("subList", subList);
				ActionContext.getContext().put("onlineList", onlineList);
				ActionContext.getContext().put("course", course);
				ActionContext.getContext().getValueStack().push(student);
			}
		}
		return "showResult";
	}
	
	
	//查询某个班级的成绩
	public String allClazzUI() throws Exception{
		//要查询的课程
		Course course = courseService.getById(courseId);
			//根据老师和课程以及本学期信息获得班级信息
			//List<Clazz>clazzlist = StudentResultService.findClazzs(getCurrentUser(),thisTerm(),course);
			
			//获取跟本课程相关的班级
			Set<Clazz> clazzs = course.getClazzs();
			
			List<Clazz> clazzlist = new ArrayList<Clazz>(clazzs);
			ActionContext.getContext().put("clazzlist", clazzlist);
			ActionContext.getContext().put("course", course);
			
		return "allClazzUI";
	}
	
	//直接根据班级信息获取班里同学的成绩信息
	public String all() throws Exception{
		//获取指定班级和课程
		Clazz clazz = clazzService.getById(clazzId);
		Course course = courseService.getById(courseId);
		
		//根据班级找到班里同学,并且进行分页
		List<User> studentList = new QueryHelper("SELECT u ", " FROM User u,Clazz c ")//
			.addWhereCondition("u.id in elements(c.students) and c = ?", clazz)//
			.preparePageBean2(studentResultService, pageNum,
				pageSize);
		if(studentList.size()>0){//当这个班级有同学时候
			//封装学生成绩相关信息
			List<StudentResultCase> studentResultList = new ArrayList<StudentResultCase>();
		
			for(User student: studentList){
				StudentResultCase studentResultCase = new StudentResultCase();
				//学生作业信息数据
				List<SubmitWork> subList = null;
				subList = studentResultService.findSubmitWork(student,thisTerm(),course);
				System.out.println("查询出学生作业信息的记录数为："+subList.size());
				studentResultCase.setSubmitWorklist(subList);
				//学生测试信息数据
				List<Result> onlineList = null;
				onlineList = studentResultService.findOnlineTestRresultOne(student,thisTerm(),course);
				System.out.println("查询出学生在线测试的记录数："+ onlineList.size());
				studentResultCase.setResultlist(onlineList);
				
				studentResultCase.setStudent(student);
				
				// 准备平均成绩
				if (onlineList.size() > 0) {
					int count = 0;
					for (Result result : onlineList) {
						count += result.getTestResult();
					}
					avg = ((double)count / course.getOnlineTestCount());
					DecimalFormat df = new DecimalFormat("0.00");
					avg = new Double(df.format(avg).toString());
					System.out.println("学生测试的平均成绩：" + avg   );
					System.out.println("学生测试的总分：" + count   );
					System.out.println("学生测试的次数：" + course.getOnlineTestCount()   );
					studentResultCase.setAvg(avg);
				}
				studentResultList.add(studentResultCase);
			}
			ActionContext.getContext().put("course", course);
			ActionContext.getContext().put("clazz", clazz);
			ActionContext.getContext().put("studentResultList",studentResultList);
			
		}
		return "showResultAllClazz";
	}
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Long getClazzId() {
		return clazzId;
	}

	public void setClazzId(Long clazzId) {
		this.clazzId = clazzId;
	}

}
