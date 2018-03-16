package cn.qdgxy.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.TeacherCourse;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.TeacherCourseService;

@Service
public class TeacherCourseServiceImpl extends DaoSupportImpl<TeacherCourse> implements TeacherCourseService {

	@Override
	public List<Course> getCourses(User user, Term term) {
		//获取老师教授的课程信息 tcs
		Set<TeacherCourse> tcs = user.getTeacherCourses();
		List<Course> courseList = new ArrayList<Course>();
		
		//遍历本学期老师讲授的每个课程，并把课程信息放入courseList集合中
		for(TeacherCourse teacherCourse : tcs) {
			if (teacherCourse.getTermId().equals(term.getId())){
				Course course = (Course) getSession().createQuery("FROM Course c WHERE c.id = ?")//
						.setParameter(0, teacherCourse.getCourseId())//
						.uniqueResult();
				courseList.add(course);
			}
		}
		return courseList;
	}


}
