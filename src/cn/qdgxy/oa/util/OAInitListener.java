package cn.qdgxy.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.qdgxy.oa.domain.Privilege;
import cn.qdgxy.oa.domain.UploadSrc;
import cn.qdgxy.oa.service.PrivilegeService;
import cn.qdgxy.oa.service.UploadSrcService;

public class OAInitListener implements ServletContextListener {

	private Log log = LogFactory.getLog(OAInitListener.class);

	// 初始化
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		// 从Spring的容器中取出PrivilegeService的对象实例.
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application); // 获取Spring的监听器中创建的Spring容器对象
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		UploadSrcService uploadSrcService = (UploadSrcService) ac.getBean("uploadSrcServiceImpl");

		// 1，查询所有顶级的权限列表并放到application作用域中
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("====== 顶级的权限列表已经放到application作用域中了！ ======");

		// 2，查询出所有的权限的URL集合并放到application作用域中
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		
		
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		log.info("====== 权限的URL集合已经放到application作用域中了！ ======");
		
		// 3，获取文件上传的路径
		UploadSrc ul = uploadSrcService.findUl();
		if (ul != null) {
			application.setAttribute("mainSrc", ul.getUploadSrc());
			log.info("====== 文件上传的路劲已经放到application作用域中了！ ======");
		}
	}

	// 销毁
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
