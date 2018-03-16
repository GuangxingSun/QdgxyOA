package cn.qdgxy.oa.view.action;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.OnlineTestCase;
import cn.qdgxy.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class CourseAction extends BaseAction<Course> {

	private int i;
	private int j;
	private int z;
	private int orderBy;
	private boolean asc;
	private Long courseId;
	
	
	//根据不同的用户登录输出不同的课程信息
	public String list() throws Exception { 
		List<Course> courseList = null;
		if(i != 2){//当 处理有关  作业信息   和学生成绩  的时候
			if (getCurrentUser().isAdmin()) {
				if(i == 3){//管理员可以查看   可以查看学生成绩（i == 3 代表为 学生成绩）
					courseList = courseService.findThisTermCourse(thisTerm()); // TODO 管理员的作用总数有问题
				}else{
					return "noPrivilegeUI";
				}
			} else if (isTeacher()) {
				//获得当前学期老师讲授的课程信息
				courseList = teacherCourseService.getCourses(getCurrentUser(), thisTerm());
				//获得当前学期老师教授课程信息同时获得每个课程的作业总数信息
				courseList = courseService.countTeacherCourse(courseList, thisTerm(), getCurrentUser());
			} else if (isStudent()) {
				courseList = courseService.findStudentCourse(getCurrentUser(), thisTerm());
			} else {
				return "noPrivilegeUI";
			}
		}else{//当时处理有关   在线测试信息    时候
			if (getCurrentUser().isAdmin()) {
				//courseList = courseService.findThisTermCourse(thisTerm()); // TODO 管理员的作用总数有问题
				return "noPrivilegeUI";
			} else if (isTeacher()) {
				//获得当前学期老师讲授的课程信息（包括测试总次数）
				courseList = teacherCourseService.getCourses(getCurrentUser(), thisTerm());
			
			
			} else if (isStudent()) {
				//获得当前学期上课的不同课程（包括测试总次数、作业总数）
				courseList = courseService.findStudentTestCourse(getCurrentUser(), thisTerm());
			} else {
				return "noPrivilegeUI";
			}
		}
		ActionContext.getContext().put("courseList", courseList);
		return "list";
	}
	
	public String show() throws Exception {
		Course course = courseService.getById(model.getId());
		
		ActionContext.getContext().put("course", course);
		if (isTeacher()) {
			new QueryHelper(Homework.class, "h")//
					.addWhereCondition("h.thisTerm = ?", thisTerm())//
					.addWhereCondition("h.course = ?", course)//
					.addWhereCondition("h.teacher = ?", getCurrentUser())//
					.addWhereCondition((i == 1), "h.i = ?", 0)//
					.addWhereCondition((i == 2), "h.i = ?", 1)//
					.addWhereCondition((j == 1), "h.j = ?", 0)//
					.addWhereCondition((j == 2), "h.j = ?", 1)//
					.addOrderByProperty((orderBy == 0), "h.time", false)//
					.addOrderByProperty((orderBy == 1), "h.time", asc)//
					.addOrderByProperty((orderBy == 2), "h.stime", asc)//
					.preparePageBean(courseService, pageNum, pageSize);
		} else if (isStudent()) {
			new QueryHelper("SELECT DISTINCT h ", "FROM Homework h JOIN h.clazzs c JOIN c.students s")//
					.addWhereCondition("h.thisTerm = ?", thisTerm())//
					.addWhereCondition("s = ?", getCurrentUser())//
					.addWhereCondition("h.course = ?", course)//
					.addWhereCondition((z == 1), "h.id NOT IN(SELECT sw.homework.id FROM SubmitWork sw)", new Object[0])//
					.addWhereCondition((z == 2), "h.id IN(SELECT sw.homework.id FROM SubmitWork sw)", new Object[0])//
					.addOrderByProperty((orderBy == 0), "h.time", false)//
					.addOrderByProperty((orderBy == 1), "h.time", asc)//
					.addOrderByProperty((orderBy == 2), "h.stime", asc)//
					.preparePageBean(courseService, pageNum, pageSize);
		} else if (getCurrentUser().isAdmin()) {
			new QueryHelper(Homework.class, "h")//
					.addWhereCondition("h.thisTerm = ?", thisTerm())//
					.addWhereCondition("h.course = ?", course)//
					.addOrderByProperty((orderBy == 0), "h.time", false)//
					.addOrderByProperty((orderBy == 1), "h.time", asc)//
					.addOrderByProperty((orderBy == 2), "h.stime", asc)//
					.preparePageBean(courseService, pageNum, pageSize);
		} else {
			return "noPrivilegeUI";
		} 
		setCourseId(model.getId());
		System.out.println("courseId:"+courseId);
		return "show";
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
