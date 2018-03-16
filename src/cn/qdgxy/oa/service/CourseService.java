package cn.qdgxy.oa.service;

import java.util.List;
import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;

public interface CourseService extends DaoSupport<Course> {

	List<Course> findStudentCourse(User student, Term thisTerm);

	List<Course> findThisTermCourse(Term thisTerm);

	List<Course> countTeacherCourse(List<Course> courseList, Term thisTerm, User teacher);

	List<Course> findStudentTestCourse(User currentUser, Term thisTerm);

	
}
