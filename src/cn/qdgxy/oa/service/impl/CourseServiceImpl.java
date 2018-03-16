package cn.qdgxy.oa.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.CourseService;

@Service
@SuppressWarnings("unchecked")
public class CourseServiceImpl extends DaoSupportImpl<Course> implements CourseService {

	/** 查找老师每个课程的作业总数及所有的课程 */
	/** 给老师讲授的每个课程信息添加,作业总数信息*/
	public List<Course> countTeacherCourse(List<Course> courseList, Term thisTerm, User teacher) {
		int courseIndex = 0;
		for (Course course : courseList) {
			int homeworkCount = 0;
			for (Homework homework : course.getHomeworks()) {
				if (homework.getThisTerm().getId().equals(thisTerm.getId())) { // 只查询本学期的作业
					if (homework.getTeacher().getId().equals(teacher.getId())) {  // 查询每个课程的作业的数量
						homeworkCount++;
					}
				}
			}
			course.setHomeworkCount(homeworkCount);
			courseList.set(courseIndex++, course);
		}
		return courseList;
	}

	/** 查找学生每个课程的作业总数及所有的课程 */
	public List<Course> findStudentCourse(User student, Term thisTerm) {
		List<Course> courseList = getSession().createQuery(//  查询学生有作业的课程
				"SELECT DISTINCT h.course FROM Homework h JOIN h.clazzs c JOIN c.students s WHERE s = ? AND h.thisTerm = ?")//
				.setParameter(0, student)//
				.setParameter(1, thisTerm)//
				.list();
		int courseIndex = 0;
		for (Course course : courseList) {
			Long homeworkCount = (Long) getSession().createQuery(// 查询每个课程的作业的数量
					"SELECT COUNT(DISTINCT h) FROM Homework h JOIN h.clazzs c JOIN c.students s WHERE s = ? AND h.course = ? AND h.thisTerm = ?")//
					.setParameter(0, student)//
					.setParameter(1, course)//
					.setParameter(2, thisTerm)//
					.uniqueResult();
			course.setHomeworkCount(homeworkCount.intValue());
			courseList.set(courseIndex++, course);
		}
		return courseList;
	}
	
	
	/** 查找学生每个课程的所有的课程 和相应   测试总次数 */
	public List<Course> findStudentTestCourse(User student, Term thisTerm) {
		//查询学生本学期 所上的课程
		 List<Course> courseList = getSession().createQuery(//  查询学生有作业的课程
				//"SELECT c FROM Course c join c.students s  AND c.terms = ?")//
				"SELECT c FROM Course c JOIN c.clazzs a JOIN a.students s JOIN c.terms t WHERE s = ? AND t = ?")//
				.setParameter(0, student)//
				.setParameter(1, thisTerm)//
				.list();
		 return courseList;
	}
	
	
	
	@Override
	public List<Course> findThisTermCourse(Term thisTerm) {
		
		List<Course> courseList = getSession().createQuery("SELECT DISTINCT h.course FROM Homework h WHERE h.thisTerm = ?")//
					.setParameter(0, thisTerm)//
					.list();
		
		int courseIndex = 0;
		for (Course course : courseList) {
			Long homeworkCount = (Long) getSession().createQuery(// 查询每个课程的作业的数量
					"SELECT COUNT(DISTINCT h) FROM Homework h WHERE h.course = ? AND h.thisTerm = ?")//
					.setParameter(0, course)//
					.setParameter(1, thisTerm)//
					.uniqueResult();
			course.setHomeworkCount(homeworkCount.intValue());
			courseList.set(courseIndex++, course);
		}
		return courseList;
	}

	

}
