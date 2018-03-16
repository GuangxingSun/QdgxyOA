package cn.qdgxy.oa.view.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Term;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SetTermAction extends BaseAction<Term> {
	
	public String setTerm () throws Exception {
		
		// 准备数据
		List<Term> termList = termService.findAll();
		ActionContext.getContext().put("termList", termList);
		return "termList";
		
	}
	
	public String termAddUI () throws Exception {
		
		// 跳到表单页面
		List<String> schooleYearList = termService.getSchoolYearBySystemTime();
		ActionContext.getContext().put("schooleYearList", schooleYearList);
		return "termSaveUI";
		
	}
	
	public String termAdd () throws Exception {
		termService.save(model);
		return "toTermList";
	}
	
	public String termEditUI () throws Exception {
		Term term = termService.getById(model.getId());
		List<String> schooleYearList = termService.getSchoolYearByTermTime(term.getTime());
		ActionContext.getContext().getValueStack().push(term);
		ActionContext.getContext().put("schooleYearList", schooleYearList);
		return "termSaveUI";
		
	}
	
	public String termEdit () throws Exception {
		Term term = termService.getById(model.getId());
		term.setSchoolYear(model.getSchoolYear());
		term.setTerm(model.getTerm());
		term.setTime(model.getTime());
		term.setThisTerm(model.getThisTerm());
		termService.update(term);
		return "toTermList";
		
	}
	
	public String setThisTerm() {
		Term thisTerm = termService.getById(model.getId());
		termService.changeThisTerm(thisTerm);
		ActionContext.getContext().getSession().put("thisTerm", thisTerm); // 把当前学期放到session
		ActionContext.getContext().getSession().put("thisWeak", userService.thisWeak(new Date())); // 把当前周次放到session
		return "toTermList";
	}
	
	public String termDelete() {
		termService.delete(model.getId());
		return "toTermList";
	}

}
