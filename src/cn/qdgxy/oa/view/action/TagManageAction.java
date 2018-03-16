package cn.qdgxy.oa.view.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Tag;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class TagManageAction extends BaseAction<Tag> {

	private Long teacherId;
	
	public String list() throws Exception {
		if (isTeacher()) {
			List<Tag> tagList = tagService.getTags(getCurrentUser());
			ActionContext.getContext().put("tagList", tagList);
			return "list";
		} else {
			return "noPrivilegeUI";
		}
	}

	public String delete() throws Exception {
		tagService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		return "saveUI";
	}

	public String add() throws Exception {
		if(tagService.findTag(model.getTag(), getCurrentUser())) {
			ServletActionContext.getResponse().getWriter().print(//
					"<script language=javascript>alert('此标记已被使用！');history.go(-1);</script>");
			return null;
		}
		model.setTeacher(getCurrentUser());
		tagService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		Tag tag = tagService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(tag);
		return "saveUI";
	}

	public String edit() throws Exception {
		Tag tag = tagService.getById(model.getId());
		tag.setTag(model.getTag());
		tag.setDescription(model.getDescription());
		tag.setSettle(model.getSettle());
		tagService.update(tag);
		return "toList";
	}
	
	public Long getTeacherId() {
		return teacherId;
	}
	
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
}
