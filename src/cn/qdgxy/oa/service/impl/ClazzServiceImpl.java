package cn.qdgxy.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.ClazzService;

@Service
@SuppressWarnings("unchecked")
public class ClazzServiceImpl extends DaoSupportImpl<Clazz> implements ClazzService {

	public List<Integer> findGrade() {
		return getSession().createQuery(//
				"SELECT DISTINCT c.grade FROM Clazz c ORDER BY c.grade ASC")//
				.list();
	}

	public List<String> findMajor() {
		return getSession().createQuery(//
				"SELECT DISTINCT c.major FROM Clazz c")//
				.list();
	}

	public List<Clazz> findAll() {
		return getSession().createQuery(//
				"FROM Clazz c ORDER BY c.major, c.grade")//
				.list();
	}

	public List<Clazz> findClazz(Integer grade, String major) {
		return getSession().createQuery(//
				"FROM Clazz c WHERE c.grade = ? AND c.major = ?")//
				.setParameter(0, grade)//
				.setParameter(1, major)//
				.list();
	}

	public List<SubmitWork> findHasSubmitWork(Homework homework, Clazz clazz) {
		
		List<SubmitWork> submitWorkList = new ArrayList<SubmitWork>(homework.getSubmitWorks());
		List<SubmitWork> hasSubmitWorkList = new ArrayList<SubmitWork>();
		for (SubmitWork submitWork : submitWorkList) {
			for (Clazz userClazz : submitWork.getStudent().getClazzs()) {
				if (userClazz.getId().equals(clazz.getId())) {
					hasSubmitWorkList.add(submitWork);
				}
			}
		}
		/** 对List集合进行排序 */
		Collections.sort(hasSubmitWorkList, new Comparator<SubmitWork>() {

			public int compare(SubmitWork o1, SubmitWork o2) {
				return o1.getId().compareTo(o2.getId());
			}
			
		});
		return hasSubmitWorkList;
		
	}

	public List<User> findNotSubmitUser(Homework homework, Clazz clazz) {
		return getSession().createQuery(//
				"SELECT u FROM User u JOIN u.clazzs c WHERE c = ? AND u.id NOT IN" +
				"(SELECT s.student.id FROM SubmitWork s WHERE s.homework = ?) ORDER BY u.loginName ASC")//
				.setParameter(0, clazz)//
				.setParameter(1, homework)//
				.list();
	}
}
