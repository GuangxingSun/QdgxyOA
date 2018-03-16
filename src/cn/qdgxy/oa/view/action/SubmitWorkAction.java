package cn.qdgxy.oa.view.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.SubmitWork;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SubmitWorkAction extends BaseAction<SubmitWork> {

	private Long homeworkId;
	private File upload;
	private String uploadFileName;
	private Long courseId;
	
	
	public String addUI() throws Exception {
		Homework homework = homeworkService.getById(homeworkId);
		ActionContext.getContext().put("homework", homework);
		return "saveUI";
	}

	public String add() throws Exception {
		Homework homework = homeworkService.getById(homeworkId);
		int index = uploadFileName.lastIndexOf(".");
		setUploadFileName(getCurrentUser().getLoginName() + getCurrentUser().getName() + uploadFileName.substring(index));
		if (upload != null) {
			List<String> clazzNameList = submitWorkService.findClazzName(getCurrentUser(), homework);
			for (int i = 0; i < clazzNameList.size(); i++) {
				String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" +homework.getTeacher().getName() + "\\" + homework.getName()+ "\\" + clazzNameList.get(i);
				File file = new File(new File(src), uploadFileName);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				FileUtils.copyFile(upload, file);
			}
			model.setHomework(homework);
			model.setStudent(getCurrentUser());
			model.setTime(new Date());
			model.setFileName(uploadFileName);
			submitWorkService.save(model);
		} else {
			ServletActionContext.getResponse().getWriter().print(//
					"<script language=javascript>alert('文件上传失败！');history.go(-1);</script>");
			return null;
		}
		ActionContext.getContext().put("courseId", homework.getCourse().getId());
		return "toShow";
	}

	public String editUI() throws Exception {
		SubmitWork submitWork = submitWorkService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(submitWork);
		ActionContext.getContext().put("homework", submitWork.getHomework());
		return "saveUI";
	}

	public String edit() throws Exception {
		SubmitWork submitWork = submitWorkService.getById(model.getId());
		Homework homework = submitWork.getHomework();
		int index = uploadFileName.lastIndexOf(".");
		setUploadFileName(getCurrentUser().getLoginName() + getCurrentUser().getName() + uploadFileName.substring(index));
		List<String> clazzNameList = submitWorkService.findClazzName(getCurrentUser(), homework);
		for (int i = 0; i < clazzNameList.size(); i++) {
			String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" +homework.getTeacher().getName() + "\\" + homework.getName()+ "\\" + clazzNameList.get(i);
			if (new File(src + "\\" + submitWork.getFileName()).delete()) {  // 先删除以前的文件
				File file = new File(new File(src), uploadFileName);
				FileUtils.copyFile(upload, file);  // 然后在保存
			} else {
				ServletActionContext.getResponse().getWriter().print(//
						"<script language=javascript>alert('无法修改文件！');history.go(-1);</script>");
				return null;
			}
		}
		submitWork.setTime(new Date());
		submitWork.setFileName(uploadFileName);
		submitWork.setScore(0);
		submitWorkService.update(submitWork);
		ActionContext.getContext().put("courseId", homework.getCourse().getId());
		return "toShow";
	}
	
	public String delete () throws Exception {
		SubmitWork submitWork = submitWorkService.getById(model.getId());
		Homework homework = submitWork.getHomework();
		List<String> clazzNameList = submitWorkService.findClazzName(getCurrentUser(), homework);
		for (int i = 0; i < clazzNameList.size(); i++) {
			String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" +homework.getTeacher().getName() + "\\" + homework.getName()+ "\\" + clazzNameList.get(i);
			new File(src + "\\" + submitWork.getFileName()).delete();  // 先删除以前的文件
		}
		submitWorkService.delete(model.getId());
		homeworkService.update(homework); 
		 
		System.out.println("删除时候传递的courseId："+courseId);
		
		return "toShow";
	}
	
	public void score() throws Exception {
		SubmitWork submitWork = submitWorkService.getById(model.getId());
		submitWork.setScore(model.getScore());
		submitWorkService.update(submitWork);
	}
	
	public Long getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Long homeworkId) {
		this.homeworkId = homeworkId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
}
