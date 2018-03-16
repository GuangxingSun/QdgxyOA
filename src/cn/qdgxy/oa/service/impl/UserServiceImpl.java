package cn.qdgxy.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Role;
import cn.qdgxy.oa.domain.TeacherCourse;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.UserService;
import cn.qdgxy.oa.util.CountWeek;

@Service
@SuppressWarnings("unchecked")
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	public void save(User user, Long[] courseIds) {
		// 默认密码是1234
		String md5 = DigestUtils.md5Hex("1234"); // 密码要使用MD5摘要
		user.setPassword(md5);

		// 保存到数据库
		getSession().save(user);
		
		if (isTeacher(user)) {
			user.setIsTorS(2);  // 设置用户为老师
		} else if (isStudent(user)) {
			user.setIsTorS(1);  // 设置为学生
			for (Clazz clazz : user.getClazzs()) {
				clazz.setStudentCount(clazz.getStudentCount() + 1);  // 学生所在班级人数加一
				getSession().update(clazz);
			}
		}
		
		Set<TeacherCourse> teacherCourses = new HashSet<TeacherCourse>();
		for (Long courseId : courseIds) {
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setCourseId(courseId);
			teacherCourse.setTeacher(user);
			teacherCourse.setTermId(thisTerm().getId());
			getSession().save(teacherCourse);
			teacherCourses.add(teacherCourse);
		}
		user.setTeacherCourses(teacherCourses);
		
		
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5)// 密码要使用MD5摘要
				.uniqueResult();
	}

	public boolean judge(String loginName) {
		List<User> list = getSession().createQuery(//
				"FROM User u WHERE u.loginName=?")//
				.setParameter(0, loginName)//
				.list();
		if (list.size() == 0) {
			return true;
		}
		return false;
	}
	// TODO 触发器实现
	public void update(User user, User oldUser, Long[] courseIds) {
		getSession().update(user);
		
		// 修改课程 ，先删除，后添加
		Set<TeacherCourse> tcSet = user.getTeacherCourses();
		for (TeacherCourse teacherCourse : tcSet) {
			getSession().delete(teacherCourse);
		}
		Set<TeacherCourse> teacherCourses = new HashSet<TeacherCourse>();
		if (courseIds != null) {
			for (Long courseId : courseIds) {
				TeacherCourse teacherCourse = new TeacherCourse();
				teacherCourse.setCourseId(courseId);
				teacherCourse.setTeacher(user);
				teacherCourse.setTermId(thisTerm().getId());
				getSession().save(teacherCourse);
				teacherCourses.add(teacherCourse);
			}
			user.setTeacherCourses(teacherCourses);			
		}
		
		//////////////////////////////////////
		if (isTeacher(user)) {
			user.setIsTorS(2);
			if (isStudent(oldUser)) {
				for (Clazz clazz : oldUser.getClazzs()) {
					clazz.setStudentCount(clazz.getStudentCount() - 1);
					getSession().update(clazz);
				}
			}
		} else if (isStudent(user)) {
			user.setIsTorS(1);
			if (isTeacher(oldUser)) {
				for (Clazz clazz : user.getClazzs()) {
					clazz.setStudentCount(clazz.getStudentCount() + 1);
					getSession().update(clazz);
				}
			} else if (isStudent(oldUser)) {
				List<Long> clazzIdList = new ArrayList<Long>();
				for (Clazz oldClazz : oldUser.getClazzs()) {
					for (Clazz clazz : user.getClazzs()) {
						if (oldClazz.getId().equals(clazz.getId())) {
							clazzIdList.add(clazz.getId());
							break;
						}
					}
				}
				for (Clazz oldClazz : oldUser.getClazzs()) {
					boolean idR = false;
					for (Long clazzId : clazzIdList) {
						if (clazzId.equals(oldClazz.getId())) {
							idR = true;
							break;
						}
					}
					if (!idR) {
						oldClazz.setStudentCount(oldClazz.getStudentCount() - 1);
						getSession().update(oldClazz);
					}
				}
				for (Clazz clazz : user.getClazzs()) {
					boolean idR = false;
					for (Long clazzId : clazzIdList) {
						if (clazzId.equals(clazz.getId())) {
							idR = true;
							break;
						}
					}
					if (!idR) {
						clazz.setStudentCount(clazz.getStudentCount() + 1);
						getSession().update(clazz);
					}
				}
			}
			
		}
	}
	
	public void delete(User user) {
		if (isStudent(user)) {
			for (Clazz clazz : user.getClazzs()) {
				clazz.setStudentCount(clazz.getStudentCount() - 1);
				getSession().update(clazz);
			}
		}
		getSession().delete(user);
		
	}

	public boolean isTeacher(User user) {
		boolean isTeacher = false;
		for (Role role : user.getRoles()) {
			isTeacher = role.getName().trim().equals("老师");
			if (isTeacher) {
				break;
			}
		}
		return isTeacher;
	}

	public boolean isStudent(User user) {
		boolean isStudent = false;
		for (Role role : user.getRoles()) {
			isStudent = role.getName().trim().equals("学生");
			if (isStudent) {
				break;
			}
		}
		return isStudent;
	}

	@Override
	public Term thisTerm() {
		return (Term) getSession().createQuery("FROM Term t WHERE t.thisTerm = ?").setParameter(0, 1).uniqueResult();
	}

	@Override
	public int thisWeak(Date time) {
		Term beginTime = (Term) getSession().createQuery("FROM Term t WHERE t.thisTerm = ?").setParameter(0, 1).uniqueResult();
		if (beginTime == null) {
			return 0;
		}
		CountWeek countWeek = new CountWeek();
		return countWeek.countWeek(time, beginTime.getTime())/7 + 1; // 计算现在的周次
	}

	/**
	 * 通过登录名，来筛选用户
	 */
	@Override
	public User getByLoginName(String loginName) {
		if (loginName == null) {
			return null;
		}
		return (User) getSession().createQuery(//
				// 注意空格！
				"FROM User WHERE loginName =?")//
				.setParameter(0, loginName)//
				.uniqueResult();
	}
	
}
