package cn.qdgxy.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.JsonClazz;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.SwfPath;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.util.DocConverter;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ClazzAction extends BaseAction<Clazz> {

	private JSONArray result;
	private Long homeworkId;
	private Long studentId;
	private String filename;
	private Long submitWorkId;
	private String swfPath;//用来传递在线查看的swf文件的路径

	public String list() throws Exception {
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		return "list";
	}

	public String delete() throws Exception {
		clazzService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		List<Integer> gradeList = clazzService.findGrade();
		ActionContext.getContext().put("gradeList", gradeList);
		List<String> majorList = clazzService.findMajor();
		ActionContext.getContext().put("majorList", majorList);
		return "saveUI";
	}

	public String add() throws Exception {
		clazzService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		Clazz clazz = clazzService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(clazz);
		return "saveUI";
	}

	public String edit() throws Exception {
		Clazz clazz = clazzService.getById(model.getId());
		clazz.setGrade(model.getGrade());
		clazz.setMajor(model.getMajor());
		clazz.setClazz(model.getClazz());
		clazzService.update(clazz);
		return "toList";
	}

	public String jsonClazz() throws Exception {
		List<Clazz> clazzList = clazzService.findClazz(model.getGrade(),
				model.getMajor());
		List<JsonClazz> jsonClazz = new ArrayList<JsonClazz>();
		for (Clazz clazz : clazzList) {
			JsonClazz newClazz = new JsonClazz();
			newClazz.setId(clazz.getId().intValue());
			newClazz.setGrade(clazz.getGrade());
			newClazz.setMajor(clazz.getMajor());
			newClazz.setClazz(clazz.getClazz());
			jsonClazz.add(newClazz);
		}
		result = JSONArray.fromObject(jsonClazz);
		return "showClazz";
	}

	public String homeworkCondition() throws Exception {
		Homework homework = homeworkService.getById(homeworkId);
		Clazz clazz = clazzService.getById(model.getId());
		ActionContext.getContext().put("homework", homework);
		ActionContext.getContext().put("clazz", clazz);
		List<SubmitWork> hasSubmitWork = clazzService.findHasSubmitWork(
				homework, clazz);
		List<User> notSubmitUser = clazzService.findNotSubmitUser(homework,
				clazz);
		ActionContext.getContext().put("hasSubmitWork", hasSubmitWork);
		ActionContext.getContext().put("notSubmitUser", notSubmitUser);
		return "showCondition";
	}

	// 老师查看当前同学提交的作业
	public String downloadStudntHomework() throws Exception {
		System.out.println("1111111111111111111111");

		System.out.println("fileName==========:" + filename);

		return "studntHomework";
	}

	// 老师在线查看同学提交的作业
	public String toLookStudntHomework() throws Exception {
		// 准备数据
		Clazz clazz = clazzService.getById(model.getId());// 获得所在的班级
		SubmitWork submitWork = submitWorkService.getById(submitWorkId);// 获得指定的提交作业信息
		System.out.println("需要查看的文件名---->：" + submitWork.getFileName());

		setFilename(URLEncoder.encode(submitWork.getFileName(), "UTF-8"));// 获得文件的名称并且通过set方法给struts配置文件
		// setFilename(submitWork.getFileName());//获得文件的名称并且通过set方法给struts配置文件
		System.out.println("解码后的文件名为："
				+ URLDecoder.decode(getFilename(), "UTF-8"));

		Homework homework = submitWork.getHomework();// 获得作业信息

		String saveDirectory = "D:/upload";// 设置老师如果在线查看作业时，作业格式转换的临时文件存放位置
		// 采用cos缺省的命名策略，重名后加1,2,3...如果不加dfp重名将覆盖
		DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		// 每个文件最大50m
		int maxPostSize = 20 * 1024 * 1024;
		HttpServletRequest request = ServletActionContext.getRequest();
		// response的编码为"UTF-8",同时采用缺省的文件名冲突解决策略,实现上传,如果不加dfp重名将覆盖
		// MultipartRequest multi = new MultipartRequest(request, saveDirectory,
		// maxPostSize,"UTF-8",dfp);
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\"
				+ homework.getCourse().getName() + "\\"
				+ homework.getTeacher().getName() + "\\" + homework.getName()
				+ "\\" + clazz.getGrade() + "级" + clazz.getMajor() + "专业"
				+ clazz.getClazz() + "班" + "\\" + submitWork.getFileName();
		System.out.println("打印出之前文件的存储路径：" + src);
		File file = new File(src);// 得到老师要查看的学生作业文件
		
		// 复制文件操作 把同学提交的作业复制到d:/upload目录下
		if (file != null) {
			String fileName = URLDecoder.decode(getFilename(), "UTF-8");// 暂时保存文件名
			/*int bytesum = 0;
			int byteread = 0;
			String newPath = saveDirectory + "/" + fileName;
			// 把同学提交的作业复制到事先选定的 存放临时文件的d:/upload 目录下
			InputStream inStream = new FileInputStream(file); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			inStream.close();*/
 
			// 获得上传文件的扩展名
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println("需要查看的文件名：" + fileName);
			// 设置保存文件的全路径（这里为 d:/upload/文件名）
			String lastFileName = saveDirectory + "\\" + fileName;
			// 获取需要转换的文件名,将路径名中的'\'替换为'/'
			String converfilename = saveDirectory.replaceAll("\\\\", "/") + "/"
				+ fileName;
			//String converfilename = saveDirectory+ "/"+fileName; 
			
			src = src.replaceAll("\\\\", "/") ;
			System.out.println("src:"+src);
			
			//通过正则表达式去掉文件名的中文
			String reg = "[\u4e00-\u9fa5]";
			Pattern pat = Pattern.compile(reg);  
	    	Matcher mat=pat.matcher(converfilename); 
	    	converfilename = mat.replaceAll("");
			
			
			System.out.println("打印输出文件转换临时文件存放目录：" + converfilename);
		
			 //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(src,converfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	     	 d.conver();
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         System.out.println("打印转换后的swf文件路径"+d.getswfPath());
	         //生成swf相对路径，以便传递给flexpaper播放器
	         swfPath = "/upload"+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
	         System.out.println("最终得到的swfpath路径："+swfPath);
	         SwfPath swfPath1 = new SwfPath(swfPath);
	         ActionContext.getContext().put("swfPath1", swfPath1);
	         setSwfPath(swfPath);
	         
		}
		return "toLookStudntHomework";
	}
	 
	public String lookStudentHomework() throws Exception{
		
		swfPath=java.net.URLDecoder.decode(getSwfPath(),"UTF-8");
		
		System.out.println("swfPath:"+swfPath);
		SwfPath swfPath1 = new SwfPath(swfPath);
        ActionContext.getContext().put("swfPath1", swfPath1);
		return "lookStudentHomework";
		
	}
	

	public InputStream getStudntHomework() throws Exception {
		Clazz clazz = clazzService.getById(model.getId());// 获得所在的班级
		SubmitWork submitWork = submitWorkService.getById(submitWorkId);// 获得指定的提交作业信息
		setFilename(submitWork.getFileName());// 获得文件的名称并且通过set方法给struts配置文件
		Homework homework = submitWork.getHomework();// 获得作业信息
		System.out.println("2222222222222222222222");
		String src = getMainSrc() + "\\" + thisTermSrc() + "\\"
				+ homework.getCourse().getName() + "\\"
				+ homework.getTeacher().getName() + "\\" + homework.getName()
				+ "\\" + clazz.getGrade() + "级" + clazz.getMajor() + "专业"
				+ clazz.getClazz() + "班" + "\\" + submitWork.getFileName();
		System.out.println("打印出之前文件的存储路径：" + src);

		InputStream inputStream = new FileInputStream(new File(src));
		System.out
				.println("FileName:______________" + submitWork.getFileName());
		return inputStream;
	}

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

	public Long getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Long homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getFilename() throws Exception {
		return new String(filename.getBytes(), "ISO-8859-1");
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getSubmitWorkId() {
		return submitWorkId;
	}

	public void setSubmitWorkId(Long submitWorkId) {
		this.submitWorkId = submitWorkId;
	}

	public String getContentType() {
		try {
			filename = URLDecoder.decode(filename, "UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("3333333333333333333333333");
		System.out.println("fileName_contentType:" + filename);
		System.out.println("根据文件名获取的文件类型为："
				+ ServletActionContext.getServletContext()
						.getMimeType(filename).toString());
		return ServletActionContext.getServletContext().getMimeType(filename);// 通过该文件名来获取其指定的文件类型
	}

	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}
}
