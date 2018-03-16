package cn.qdgxy.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.bind.v2.TODO;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.util.Compression;
import cn.qdgxy.oa.util.doZip;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class HomeworkAction extends BaseAction<Homework> {

	private Long[] clazzIds;
	private Long courseId;
	private File upload;
	private String uploadFileName;
	private String filename;
	private String clazzIdString;

	public String addUI() throws Exception {
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		
		Course course = courseService.getById(courseId);
		ActionContext.getContext().put("course", course);
		
		List<Integer> gradeList = clazzService.findGrade();
		ActionContext.getContext().put("gradeList", gradeList);
		List<String> majorList = clazzService.findMajor();
		ActionContext.getContext().put("majorList", majorList);
		
		return "saveUI";
	}
	/**
	 * 添加作业
	 */
	public String add() throws Exception {
		
		// TODO 处理文件 当文件上传失败时，跳到input页面
		if (upload != null) {
			if (homeworkService.judge(model.getName())) {
				ServletActionContext.getResponse().getWriter().print(//
						"<script language=javascript>alert('你已添加本次作业！');history.go(-1);</script>");
				return null;
			}
			Course course = courseService.getById(courseId);
			/** 作业存储路劲为：根目录/学期/课程名/老师/作业名 */
			String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + course.getName() + "\\" +getCurrentUser().getName()+ "\\" + model.getName();
			File file = new File(new File(src), uploadFileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileUtils.copyFile(upload, file);
			List<Clazz> clazzList = clazzService.getByIds(clazzIds);
			model.setClazzs(new HashSet<Clazz>(clazzList));
			model.setCourse(course);
			model.setTeacher(getCurrentUser());
			model.setFileName(uploadFileName);
			model.setTime(new Date());
			model.setThisTerm(homeworkService.getThisTerm());
			model.setWeek(userService.thisWeak(model.getTime())); // 计算周次，这样更准确
			homeworkService.save(model);
			ActionContext.getContext().put("courseId", courseId);
		} else {
			ServletActionContext.getResponse().getWriter().print(//
					"<script language=javascript>alert('文件上传失败！');history.go(-1);</script>");
			return null;
		}
		return "toShow";
	}

	public String editUI() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		clazzIds = new Long[homework.getClazzs().size()];
		int index = 0;
		for (Clazz clazz : homework.getClazzs()) {
			clazzIds[index++] = clazz.getId();
		}
		ActionContext.getContext().getValueStack().push(homework);
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		
		Course course = homework.getCourse();
		ActionContext.getContext().put("course", course);
		
		List<Integer> gradeList = clazzService.findGrade();
		ActionContext.getContext().put("gradeList", gradeList);
		List<String> majorList = clazzService.findMajor();
		ActionContext.getContext().put("majorList", majorList);
		
		return "saveUI";
	}
	/**
	 * 修改作业
	 */
	public String edit() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		homework.setStime(model.getStime());
		List<Clazz> clazzList = clazzService.getByIds(clazzIds);
		homework.setClazzs(new HashSet<Clazz>(clazzList));
		homework.setSc(model.getSc());
		// 处理文件
		if (upload != null) {
			String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" +homework.getTeacher().getName() + "\\" + homework.getName();
			if (new File(src + "\\" + homework.getFileName()).delete()) { // 删除以前的文件
				homework.setFileName(uploadFileName);
				File file = new File(new File(src), uploadFileName);
				FileUtils.copyFile(upload, file);
			} else {
				ServletActionContext.getResponse().getWriter().print(//
						"<script language=javascript>alert('无法修改文件！');history.go(-1);</script>");
				return null;
			}
		}
		homeworkService.update(homework);
		ActionContext.getContext().put("courseId", homework.getCourse().getId());
		return "toShow";
	}
	/**
	 * 删除作业
	 */
	public String delete() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		Long courseId = homework.getCourse().getId();
		// 处理文件
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" +homework.getTeacher().getName() + "\\" + homework.getName();
		if (homeworkService.deleteDir(new File(src))) {
			homeworkService.delete(homework);
		} else {
			ServletActionContext.getResponse().getWriter().print(//
					"<script language=javascript>history.go(-1);alert('删除失败！');</script>");
			return null;
		}
		ActionContext.getContext().put("courseId", courseId);
		return "toShow";
	}
	
	public String download() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		//setFilename(homework.getFileName());
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" + homework.getTeacher().getName() + "\\" + homework.getName() + "\\" + homework.getFileName();
		File file = new File(src);
		if (!file.exists()) {
			ServletActionContext.getResponse().getWriter().print(//
					"<script language=javascript>alert('文件不在了！');history.go(-1);</script>");
			return null;
		}
		return "download";
	}
	/**
	 * 下载作业
	 */
	public InputStream getDownloadFile() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		setFilename(homework.getFileName());
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" + homework.getTeacher().getName() + "\\" + homework.getName() + "\\" + homework.getFileName();
		File file = new File(src);
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }
	
	public String collect() throws Exception {
		
		return "collect";
	}
	
	public InputStream getCollectFile() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		setFilename(homework.getName() + ".zip");
		List<Clazz> clazzList = clazzService.getByIds(ClazzIdStringToClazzIds());
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\" + homework.getCourse().getName() + "\\" + homework.getTeacher().getName() + "\\" + homework.getName();
		Compression zc = new Compression(src + "\\" + homework.getName() + ".zip");
		doZip bigan = new doZip(zc, clazzList, src); // TODO 最大支持6个班级同时下载
		bigan.zip();
		File file = new File(src + "\\" + homework.getName() + ".zip");
		InputStream inputStream = new FileInputStream(file);
		
		if (homework.getI() == 0) {
			homework.setI(1);
			homeworkService.update(homework);
		}
		
        return inputStream;
	}
	
	public String statistics() throws Exception {
		
		return "showCourses";
		
	}
	
	public String doStatistics() throws Exception {
		Course course = courseService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(course);
		return "doStatistics";
		
	}
	
	public void slider() throws Exception {
		Homework homework = homeworkService.getById(model.getId());
		homework.setSc(model.getSc());
		homeworkService.update(homework);
	}
	
	//根据不同的文件动态给出MIME文件类型
	public String getContentType(){
	    //在Tomcat Conf里的web.xml有对应的映射文件
	    return ServletActionContext.getServletContext().getMimeType(filename);
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	//返回一个文件名
	public String getFilename() throws Exception{
		return new String(filename.getBytes(),"ISO-8859-1");
	}
	
	public void setClazzIds(Long[] clazzIds) {
		this.clazzIds = clazzIds;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public Long[] getClazzIds() {
		return clazzIds;
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
	
	public String getClazzIdString() {
		return clazzIdString;
	}
	
	public void setClazzIdString(String clazzIdString) {
		this.clazzIdString = clazzIdString;
	}
	
	public Long[] ClazzIdStringToClazzIds() {
		String[] clazzIdString = this.clazzIdString.split(",");
		Long[] clazzIds = new Long[clazzIdString.length];
		for (int i = 0; i < clazzIdString.length; i++) {
			clazzIds[i] = Long.valueOf(clazzIdString[i]);
		}
		return clazzIds;
	}
	
}
