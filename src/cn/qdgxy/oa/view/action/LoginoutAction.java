package cn.qdgxy.oa.view.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.User;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class LoginoutAction extends BaseAction<User> {

	/** 登录页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登录 */
	public String login() throws Exception {
		// 验证用户名与密码，如果正确就返回这个用户，否则返回null
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());

		// 如果登录名或密码不正确，就转回到登录页面并提示错误消息
		if (user == null) {
			addFieldError("login", "登录名或密码不正确！");
			return "loginUI";
		}
		// 如果登录名与密码都正确，就登录用户
		else {
			ActionContext.getContext().getSession().put("user", user);
			ActionContext.getContext().getSession().put("isTeacher", userService.isTeacher(user));
			ActionContext.getContext().getSession().put("isStudent", userService.isStudent(user));
			ActionContext.getContext().getSession().put("thisTerm", userService.thisTerm()); // 把当前学期放到session
			ActionContext.getContext().getSession().put("thisWeak", userService.thisWeak(new Date())); // 把当前周次放到session
			return "toHome";
		}
	}

	/** 注销 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

}
