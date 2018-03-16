package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.User;

public interface ClazzService extends DaoSupport<Clazz> {

	List<Integer> findGrade();

	List<String> findMajor();

	List<Clazz> findClazz(Integer grade, String major);

	List<SubmitWork> findHasSubmitWork(Homework homework, Clazz clazz);

	List<User> findNotSubmitUser(Homework homework, Clazz clazz);


}
