package cn.qdgxy.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.PageBean;
import cn.qdgxy.oa.domain.SubmitWork;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.util.CountWeek;
import cn.qdgxy.oa.util.QueryHelper;

// 这个@Transactional注解对子类中的方法也有效！
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz = null;

	public DaoSupportImpl() {
		// 通过反射获取T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				// 注意空格！
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)// 注意一定要使用setParameterList()方法！
				.list();
	}

	public List<T> findAll() {
		// 注意空格！
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            查询语句 + 参数列表
	 * @return
	 */
	public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {

		List<Object> parameters = queryHelper.getParameters();
		// 查询一页的数据列表
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<T> list = query.list(); // 查询
		if (!list.isEmpty()) {
			if (list.get(0).getClass().getSimpleName().equals("Homework")) { // 拦截作业管理分页功能,重新打包list
				list = sub(list);
			}
		}
		// 查询总记录数
		query = getSession().createQuery(queryHelper.getQueryCountHql()); // 注意空格！
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) query.uniqueResult(); // 查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}
	
	public List<T> sub(List<T> list) {
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean isStudent = (boolean) ActionContext.getContext().getSession().get("isStudent");
		if (isStudent) {
			List<Homework> homeworkList = (List<Homework>) list;
			int index = 0;
			for (Homework homework : homeworkList) {
				SubmitWork submitWork = (SubmitWork) getSession().createQuery(//
						"FROM SubmitWork s WHERE s.student = ? AND s.homework = ?")//
						.setParameter(0, user)//
						.setParameter(1, homework)//
						.uniqueResult();
				if (submitWork == null) {
					homework.setSub(false);
				} else {
					homework.setSub(true);
					homework.setScore(submitWork.getScore());
					homework.setSubmitWorkId(submitWork.getId());
				}
				if (homework.getStime() != null) {
					int day = new CountWeek().countWeek(homework.getStime(), new Date());
					homework.setDay(day);					
				}
				homeworkList.set(index++, homework);
			}
			list = (List<T>) homeworkList;
		}
		return list;
	}

}
