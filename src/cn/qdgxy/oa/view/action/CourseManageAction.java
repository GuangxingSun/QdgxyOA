package cn.qdgxy.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Course;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class CourseManageAction extends BaseAction<Course> {

	public String list() throws Exception {
		List<Course> courseList = courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		return "list";
	}

	public String delete() throws Exception {
		courseService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		return "saveUI";
	}

	public String add() throws Exception {
		courseService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		Course course = courseService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(course);
		return "saveUI";
	}

	public String edit() throws Exception {
		Course course = courseService.getById(model.getId());
		course.setName(model.getName());
		course.setDescription(model.getDescription());
		courseService.update(course);
		return "toList";
	}
}
