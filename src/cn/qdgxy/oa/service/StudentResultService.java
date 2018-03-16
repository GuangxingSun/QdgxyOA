package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;



public interface StudentResultService extends DaoSupport<Result>{

	//根据相应的信息查找学生提交的作业信息
	List<SubmitWork> findSubmitWork(User currentUser, Term thisTerm,
			Course course);

	//根据相应的信息查询学生在线测试的成绩信息
	List<Result> findOnlineTestRresultOne(User currentUser, Term thisTerm,
			Course course);

	//根据老师和课程以及本学期信息获得班级信息
	List<Clazz> findClazzs(User currentUser, Term thisTerm, Course course);

	//根据班级获取所有同学的信息
	List<User> findStudentByClazz(Clazz clazz);

}
