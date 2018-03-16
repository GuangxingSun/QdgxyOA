package cn.qdgxy.oa.view.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.SubmitWorkService;

@Controller
@SuppressWarnings("serial")
public class HomeAction extends ActionSupport {

	@Resource
	protected SubmitWorkService submitWorkService;
	
	public String index() throws Exception {
		return "index";
	}

	public String top() throws Exception {
		// 通知数据准备
		Map<String, Object> session = ActionContext.getContext().getSession();
		boolean isStudent = (boolean) session.get("isStudent");
		if (isStudent) {
			User student = (User) session.get("user");
			Term thisTerm = (Term) session.get("thisTerm");
			int count = submitWorkService.studentNotice(student, thisTerm);
			ActionContext.getContext().put("notice", count);
		}
		return "top";
	}

	public String bottom() throws Exception {
		return "bottom";
	}

	public String left() throws Exception {
		return "left";
	}

	public String right() throws Exception {
		return "right";
	}

}
