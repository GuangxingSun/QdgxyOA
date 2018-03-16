package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;

public interface SubmitWorkService extends DaoSupport<SubmitWork> {

	List<String> findClazzName(User student, Homework homework);
	
	int studentNotice (User student, Term term);
}
