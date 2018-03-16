package cn.qdgxy.oa.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.StudentResultService;
/**
 * 查询学生成绩
 * 
 * @author Guangxing
 *
 */

@SuppressWarnings("unchecked")
@Service
public class StudentResultServiceImpl extends DaoSupportImpl<Result> implements StudentResultService{

	
	//根据相应的信息查找学生提交的作业信息

	public List<SubmitWork> findSubmitWork(User currentUser, Term thisTerm,
			Course course) {
		
		return getSession().createQuery(//
				"SELECT w FROM SubmitWork w WHERE w.student = ? AND w.homework.id IN (SELECT h.id FROM Homework h WHERE h.thisTerm.id = ? AND h.course.id = ?)")//
				.setParameter(0, currentUser)//
				.setParameter(1, thisTerm.getId())//
				.setParameter(2, course.getId())
				.list();
	}

	//根据相应的信息查询学生在线测试的成绩信息
	public List<Result> findOnlineTestRresultOne(User currentUser,
			Term thisTerm, Course course) {
		return getSession().createQuery(//
				"FROM Result r WHERE r.student = ? AND r.course = ? AND r.term = ?")//
				.setParameter(0, currentUser)//
				.setParameter(1, course)//
				.setParameter(2, thisTerm)//
				.list();
	}
	
	//根据老师和课程以及本学期信息获得班级信息
	public List<Clazz> findClazzs(User currentUser, Term thisTerm, Course course) {
		// TODO 由于之前设计  结构不符当前需求 待解决
		getSession().createQuery(//
				" "
				);
		return null;
	}

	//根据班级获取所有同学的信息
	public List<User> findStudentByClazz(Clazz clazz) {
		return getSession().createQuery(//
				"SELECT u FROM User u,Clazz c WHERE u.id in elements(c.students) and c = ?")//
				.setParameter(0, clazz)//
				.list();
	}
}
