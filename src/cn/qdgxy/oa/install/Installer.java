package cn.qdgxy.oa.install;

import java.io.File;
import java.util.Scanner;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.qdgxy.oa.domain.Privilege;
import cn.qdgxy.oa.domain.Role;
import cn.qdgxy.oa.domain.UploadSrc;
import cn.qdgxy.oa.domain.User;

/**
 * 安装程序（初始化数据）
 * 
 * @author Guangxing
 * 
 */
@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	@SuppressWarnings("resource")
	public void install() throws Exception {
		Session session = sessionFactory.getCurrentSession();

		// =======================================================================
		// 1，超级管理员
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin")); // 密码要使用MD5摘要
		session.save(user); // 保存

		// =======================================================================
		// 2，权限数据
		Privilege menu, menu1, menu2, menu3, menu4, menu5,menu6,menu7;

		menu = new Privilege("系统管理", null, null,"1.gif");
		menu1 = new Privilege("岗位管理", "/role_list", menu, null);
		menu2 = new Privilege("部门管理", "/department_list", menu, null);
		menu3 = new Privilege("用户管理", "/user_filtrate", menu, null);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3); 

		session.save(new Privilege("岗位列表", "/role_list", menu1, null));
		session.save(new Privilege("岗位删除", "/role_delete", menu1, null));
		session.save(new Privilege("岗位添加", "/role_add", menu1, null));
		session.save(new Privilege("岗位修改", "/role_edit", menu1, null));
		session.save(new Privilege("权限设置", "/role_setPrivilege", menu1, null));

		session.save(new Privilege("部门列表", "/department_list", menu2, null));
		session.save(new Privilege("部门删除", "/department_delete", menu2, null));
		session.save(new Privilege("部门添加", "/department_add", menu2, null));
		session.save(new Privilege("部门修改", "/department_edit", menu2, null));

		session.save(new Privilege("用户列表", "/user_filtrate", menu3, null));
		session.save(new Privilege("用户删除", "/user_delete", menu3, null));
		session.save(new Privilege("用户添加", "/user_add", menu3, null));
		session.save(new Privilege("用户修改", "/user_edit", menu3, null));
		session.save(new Privilege("初始密码", "/user_initPassword", menu3, null));

		// ------

		menu = new Privilege("网上交流", null, null, "19.gif");
		menu1 = new Privilege("论坛管理", "/forumManage_list", menu, null);
		menu2 = new Privilege("在线论坛", "/forum_list", menu, null);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		session.save(new Privilege("板块管理", "/forumManage_list", menu1, null));
		session.save(new Privilege("移动主题", "/topic_move", menu1, null));
		session.save(new Privilege("修改主题", "/topic_edit", menu1, null));
		session.save(new Privilege("删除主题", "/topic_delete", menu1, null));
		session.save(new Privilege("修改回帖", "/reply_edit", menu1, null));
		session.save(new Privilege("删除回帖", "/reply_delete", menu1, null));

		// ------

		menu = new Privilege("作业管理", null, null, "28.gif");
		
		menu1 = new Privilege("在线测评", "/onlineTest_list", menu, null);
		menu2 = new Privilege("网上作业", "/course_list", menu, null);
		menu3 = new Privilege("作业统计", "/homework_statistics", menu, null);
		menu4 = new Privilege("课程管理", "/courseManage_list", menu, null);
		menu5 = new Privilege("班级管理", "/clazz_list", menu, null);
		menu6 = new Privilege("学生成绩", "/studentResult_list", menu, null);
		menu7 = new Privilege("标记管理", "/tagManage_list", menu, null);

		session.save(menu);
		session.save(menu1); 
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);
		session.save(menu7);
		
	
		session.save(new Privilege("录入试题", "/onlineTest_addUI", menu1, null));
		session.save(new Privilege("管理题目", "/onlineTest_controlUI", menu1, null));
		session.save(new Privilege("查询试题", "/onlineTest_queryUI", menu1, null));
		session.save(new Privilege("在线答题", "/onlineTest_goTest", menu1, null));
		session.save(new Privilege("生成测试", "/onlineTest_makeTest", menu1, null));
		
		
		session.save(new Privilege("作业列表", "/course_list", menu2, null));
		session.save(new Privilege("作业修改", "/homework_edit", menu2, null));
		session.save(new Privilege("作业删除", "/homework_delete", menu2, null));
		session.save(new Privilege("作业添加", "/homework_add", menu2, null));
		session.save(new Privilege("作业收取", "/homework_collect", menu2, null));
		session.save(new Privilege("作业下载", "/homework_download", menu2, null));
		session.save(new Privilege("作业提交", "/submitWork_add", menu2, null));
		session.save(new Privilege("删除提交", "/submitWork_delete", menu2, null));
		session.save(new Privilege("修改提交", "/submitWork_edit", menu2, null));
		
		session.save(new Privilege("个人成绩","/studentResult_one", menu6, null));
		session.save(new Privilege("班级成绩","/studentResult_all", menu6, null));
		session.save(new Privilege("成绩查询","/studentResult_query", menu6, null));

		// -------

		menu = new Privilege("个人设置", null, null, "42.gif");
		menu1 = new Privilege("个人信息", "/user_info", menu, null);
		menu2 = new Privilege("密码修改", "/user_toEditPassword", menu, null);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		
		session.save(new Privilege("信息列表", "/user_info", menu1, null));
		session.save(new Privilege("信息录入", "/user_intoInfo", menu1, null));
		
		// -------
		
		menu = new Privilege("系统设置", null, null, "45.gif");
		menu1 = new Privilege("学期设置", "/sys_setTerm", menu, null);
		menu2 = new Privilege("用户导入", "/lead_leadUserUI", menu, null);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		// =======================================================================
		// 3，岗位设置
		Role role1, role2;
		
		role1 = new Role("老师", "在校教职老师");
		role2 = new Role("学生", "在校读书学生");
		
		session.save(role1);
		session.save(role2);
		
		// =======================================================================
		// 4，上传路劲设置
		UploadSrc uploadSrc = new UploadSrc();
		Scanner sc=new Scanner(System.in);
		String src = "";
		String pd = "";
		while(true){
			System.out.print("请输入文件存放的路劲，用于存放用户上传的所有文件；\n注意：\n1，必须为英文路劲；\n2，一旦设置不能修改。\n请输入：");
			src = sc.nextLine();
			File file = new File(src);
			if(!file.exists()){
				while(true){
					System.out.print("你输入的文件夹不存在，是否创建？（N/Y）：");
					pd = sc.nextLine().toUpperCase();
					if("N".equals(pd)||"Y".equals(pd))
						break;
				}
				if("N".equals(pd))
					continue;
				else if("Y".equals(pd)){
					file.mkdirs();
					break;
				}
			}else
				break;
		}
		uploadSrc.setUploadSrc(src);
		session.save(uploadSrc);
		System.out.println("=======================================================================");
		System.out.println("文件存放的路劲为："+uploadSrc.getUploadSrc());
		System.out.println("=======================================================================");
		
		// =======================================================================
		// 5，设置学期的开始时间
		/*BeginTime beginTime = new BeginTime();
		Scanner scTime=new Scanner(System.in);
		System.out.print("请输入这学期的开始时间（如输入‘2015-01-01’表示为2015年1月1号）：");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		beginTime.setTime(sdf.parse(scTime.nextLine()+" "));
		session.save(beginTime);
		System.out.println("=======================================================================");
		System.out.println("这学期的开始时间为："+sdf.format(beginTime.getTime()));
		System.out.println("=======================================================================");*/
	}

	@Transactional
	public UploadSrc check() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (UploadSrc) session.createQuery("FROM UploadSrc").uniqueResult();
	}
	
	public static void main(String[] args) throws Exception {
		
		// 一定要从Spring容器中取出对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		if (installer.check() == null) {
			System.out.println("正在初始化数据...");
			// 执行安装
			installer.install();
			System.out.println("初始化数据完毕！");
		} else {
			System.out.println("系统已经初始化了，无需再次进行。");
		}

	}

}
