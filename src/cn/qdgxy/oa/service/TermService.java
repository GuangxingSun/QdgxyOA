package cn.qdgxy.oa.service;

import java.util.Date;
import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Term;

public interface TermService extends DaoSupport<Term> {

	List<String> getSchoolYearBySystemTime();

	List<String> getSchoolYearByTermTime(Date time);

	void changeThisTerm(Term thisTerm);

}
