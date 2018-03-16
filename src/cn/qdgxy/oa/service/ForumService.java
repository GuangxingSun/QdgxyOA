package cn.qdgxy.oa.service;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	/**
	 * 上移，最上面的不能上移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的不能下移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveDown(Long id);

}
