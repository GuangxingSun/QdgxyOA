package cn.qdgxy.oa.service.impl;

import java.io.File;
import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.service.HomeworkService;

@Service
public class HomeworkServiceImpl extends DaoSupportImpl<Homework> implements HomeworkService {

	public void save(Homework homework) {
		
		getSession().save(homework);
		homework.setSubmitCount(countStudent(homework));
		
		Course course = homework.getCourse();
		course.setHomeworkCount(course.getHomeworkCount() + 1);
		getSession().update(course);
	}

	/**
	 * 计算本次作业的应交作业数量
	 * @param homework
	 * @return
	 */
	public int countStudent(Homework homework) {
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(DISTINCT s) FROM User s JOIN s.clazzs c JOIN c.homeworks h WHERE h = ?")//
				.setParameter(0, homework)//
				.uniqueResult();
		return count.intValue();
	}
	
	/**
	 * 删除作业的跟文件夹及里面所有的文件
	 */
	public boolean deleteDir(File file) {
		if (file.isDirectory()) {
			String[] children = file.list(); // 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return file.delete(); // 目录此时为空，可以删除
	}

	public boolean judge(String name) {
		int i = getSession().createQuery(//
				"FROM Homework h WHERE h.name = ?")//
				.setParameter(0, name)//
				.list()//
				.size();
		if (i == 0) {
			return false;
		}
		return true;
	}

	public void delete(Homework homework) {
		getSession().delete(homework);
		Course course = homework.getCourse();
		course.setHomeworkCount(course.getHomeworkCount() - 1);
		getSession().update(course);
	}

	public void update(Homework homework) {
		homework.setHasSubmit(homework.getHasSubmit() - 1);
		getSession().update(homework);
		homework.setSubmitCount(countStudent(homework));
		homework.setHasSubmit(countSubmit(homework));
		if (homework.getSubmitCount() == homework.getHasSubmit()) {
			homework.setJ(1);
		} else {
			homework.setJ(0);
		}
	}
	
	public int countSubmit(Homework homework) {		
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(s) FROM SubmitWork s WHERE s.homework = ?")//
				.setParameter(0, homework)//
				.uniqueResult();
		return count.intValue();
	}

	@Override
	public Term getThisTerm() {
		return (Term) getSession().createQuery("FROM Term t WHERE t.thisTerm = ?").setParameter(0, 1).uniqueResult();
	}

}
