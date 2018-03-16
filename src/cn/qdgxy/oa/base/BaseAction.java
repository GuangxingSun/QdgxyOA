package cn.qdgxy.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.ClazzService;
import cn.qdgxy.oa.service.CourseService;
import cn.qdgxy.oa.service.DepartmentService;
import cn.qdgxy.oa.service.ForumService;
import cn.qdgxy.oa.service.HomeworkService;
import cn.qdgxy.oa.service.OnlineTestService;
import cn.qdgxy.oa.service.PrivilegeService;
import cn.qdgxy.oa.service.ReplyService;
import cn.qdgxy.oa.service.RoleService;
import cn.qdgxy.oa.service.StudentResultService;
import cn.qdgxy.oa.service.SubmitWorkService;
import cn.qdgxy.oa.service.TagService;
import cn.qdgxy.oa.service.TeacherCourseService;
import cn.qdgxy.oa.service.TermService;
import cn.qdgxy.oa.service.TopicService;
import cn.qdgxy.oa.service.UploadSrcService;
import cn.qdgxy.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// ===================== 声明Service ====================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected ClazzService clazzService;
	@Resource
	protected HomeworkService homeworkService;
	@Resource
	protected UploadSrcService uploadSrcService;
	@Resource
	protected CourseService courseService;
	@Resource
	protected SubmitWorkService submitWorkService;
	@Resource
	protected TagService tagService;
	@Resource
	protected TermService termService;
	@Resource
	protected TeacherCourseService teacherCourseService;
	@Resource
	protected OnlineTestService onlineTestService;
	@Resource
	protected StudentResultService studentResultService;
	
	// ===================== 对ModelDriven的支持 ====================

	protected T model;

	public BaseAction() {
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		try {
			// 通过反射获取T的真是类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// ========================= 对分页的支持 =========================

	protected int pageNum = 1; // 当前页，默认为第1页
	protected int pageSize = 10; // 设置每页默认显示10条数据

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// ========================== 工具方法 ==========================

	/**
	 * 获取当前登录的用户
	 */
	public User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 * 获取客户的IP地址
	 * 
	 * @return
	 */
	public String getRequestIP() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	
	public String getMainSrc() {
		return (String) ActionContext.getContext().getApplication().get("mainSrc");
	}

	public boolean isTeacher() {
		return (Boolean) ActionContext.getContext().getSession().get("isTeacher");
	}
	
	public boolean isStudent() {
		return (Boolean) ActionContext.getContext().getSession().get("isStudent");
	}
	
	public Term thisTerm () {
		return (Term) ActionContext.getContext().getSession().get("thisTerm");
	}
	
	/**
	 * 根据Term类换成文字路劲
	 */
	public String thisTermSrc () {
		Term term = thisTerm();
		return term.getSchoolYear() + term.getTerm(); 
	}
	
}
