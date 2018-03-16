package cn.qdgxy.oa.service;

import java.io.File;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.Term;

public interface HomeworkService extends DaoSupport<Homework>{

	boolean deleteDir(File file);

	int countStudent(Homework homework);

	boolean judge(String name);

	void delete(Homework homework);

	void update(Homework homework);

	Term getThisTerm();
	
	

}
