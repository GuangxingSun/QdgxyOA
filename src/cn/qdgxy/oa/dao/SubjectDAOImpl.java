package cn.qdgxy.oa.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.TestLibrary;
import cn.qdgxy.oa.util.Page;

@Transactional
@SuppressWarnings("rawtypes")
public class SubjectDAOImpl extends DaoSupportImpl<TestLibrary> implements
		SubjectDAO {
	@Resource
	private SessionFactory sessionFactory;

	public TestLibrary findSubjectByTitle(String subjectTitle) {
		List list = getSession().createQuery(//
				"FROM TestLibrary t WHERE t.subjectTitle = ?")//
				.setString(0, subjectTitle)//
				.list(); // 查询结果保存到list中
		if (list.size() == 0) {
			return null; // 返回null
		} else {
			return (TestLibrary) list.get(0); // 返回第一个试题
		}
	}

	@Override
	public List<TestLibrary> findSubjectByPage(Page page) {
		return null;
	}

	@Override
	public int findSubjectCount() {
		return 0;
	}

	@Override
	public TestLibrary findSubjectByID(int subjectID) {
		return null;
	}

	@Override
	public void updateSubject(TestLibrary subject) {

	}

	@Override
	public void deleteSubject(int subjectID) {

	}

	@Override
	public List<TestLibrary> likeQueryByTitle(String subjectTitle, Page page) {
		return null;
	}

	@Override
	public int findLinkQueryCount(String subjectTitle) {
		return 0;
	}

	@Override
	public List<TestLibrary> randomFindSubject(int number) {
		return null;
	}

}
