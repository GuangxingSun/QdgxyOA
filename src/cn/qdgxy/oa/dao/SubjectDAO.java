package cn.qdgxy.oa.dao;

import java.util.List;

import cn.qdgxy.oa.domain.TestLibrary;
import cn.qdgxy.oa.util.Page;

public interface SubjectDAO {
	// 根据试题题目查找试题
	public TestLibrary findSubjectByTitle(String testLibrary);

	// 分页查询试题
	public List<TestLibrary> findSubjectByPage(Page page);

	// 查询试题总量
	public int findSubjectCount();

	// 根据试题ID查找试题
	public TestLibrary findSubjectByID(int subjectID);

	// 更新方法，用来更新试题
	public void updateSubject(TestLibrary subject);

	// 根据试题ID删除试题
	public void deleteSubject(int subjectID);

	// 根据试题标题模糊查询试题
	public List<TestLibrary> likeQueryByTitle(String subjectTitle, Page page);

	public int findLinkQueryCount(String subjectTitle);// 查询模糊记录数

	public List<TestLibrary> randomFindSubject(int number);// 随时取出记录

}
