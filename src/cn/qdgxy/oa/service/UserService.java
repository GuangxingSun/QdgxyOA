package cn.qdgxy.oa.service;

import java.util.Date;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 验证用户名与密码，如果正确就返回这个用户，否则返回null
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

	/**
	 * 注册时验证登入名是否重复，重复返回flase
	 * 
	 * @param loginName
	 *            登入名
	 * @return
	 */
	boolean judge(String loginName);

	boolean isTeacher(User user);
	
	boolean isStudent(User user);

	void delete(User user);

	void update(User user, User oldUser, Long[] courseIds);
	
	

	Term thisTerm();

	int thisWeak(Date time);

	void save(User model, Long[] courseIds);

	/**
	 * 根据登录名来查询指定的用户
	 * @param loginName
	 * @return
	 */
	User getByLoginName(String loginName);

}
