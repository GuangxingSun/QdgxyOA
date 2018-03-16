package cn.qdgxy.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.SubmitWorkService;

@Service
public class SubmitWorkServiceImpl extends DaoSupportImpl<SubmitWork> implements SubmitWorkService {

	public void save(SubmitWork submitWork) {
		getSession().save(submitWork);
		Homework homework = submitWork.getHomework();
		homework.setHasSubmit(homework.getHasSubmit() + 1);
		
		if (homework.getSubmitCount() == homework.getHasSubmit()) {
			homework.setJ(1);
		} else {
			homework.setJ(0);
		}
		getSession().update(homework);
	}
	
	public List<String> findClazzName(User student, Homework homework) {
		List<String> clazzNameList = new ArrayList<String>();
		for (Clazz clazz1 : student.getClazzs()) {
			for (Clazz clazz2 : homework.getClazzs()) {
				if (clazz1.getId().equals(clazz2.getId())) {
					clazzNameList.add(clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班");
					break;
				}
			}
		}
		return clazzNameList;
	}

	@Override
	public int studentNotice(User student, Term term) {
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(DISTINCT h) FROM Homework h JOIN h.clazzs c JOIN c.students s WHERE h.thisTerm = ? AND s = ? AND h.id NOT IN(SELECT sw.homework.id FROM SubmitWork sw)")//
				.setParameter(0, term)//
				.setParameter(1, student)//
				.uniqueResult();
		return count.intValue();
	}
	
}
