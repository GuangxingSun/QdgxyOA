package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.TeacherCourse;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;

public interface TeacherCourseService extends DaoSupport<TeacherCourse> {

	List<Course> getCourses(User user, Term term);

}
