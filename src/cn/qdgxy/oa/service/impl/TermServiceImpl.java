package cn.qdgxy.oa.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.service.TermService;

@Service
public class TermServiceImpl extends DaoSupportImpl<Term> implements TermService {

	@Override
	public List<String> getSchoolYearBySystemTime() {
		
		return getSchoolYear(new Date());
	}

	@Override
	public List<String> getSchoolYearByTermTime(Date time) {
		
		return getSchoolYear(time);
	}
	
	public List<String> getSchoolYear(Date time) {
		List<String> schooleYearList = new ArrayList<String>();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR); // 获取当前系统时间的年
		schooleYearList.add(0, (year-1) + "-" + year + "学年");
		schooleYearList.add(1, year + "-" + (year+1) + "学年");
		return schooleYearList;
	}

	@Override
	public void changeThisTerm(Term thisTerm) {
		
		Term term = (Term) getSession().createQuery("FROM Term t WHERE t.thisTerm = ?").setParameter(0, 1).uniqueResult();
		if (term != null) {
			term.setThisTerm(0);
			getSession().update(term);			
		}
		
		thisTerm.setThisTerm(1);
		getSession().update(thisTerm);
		
	}

}
