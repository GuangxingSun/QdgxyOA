package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.OnlineTestCase;
import cn.qdgxy.oa.domain.Reply;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.TestLibrary;
import cn.qdgxy.oa.domain.TestTitleId;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.util.Page;
import cn.qdgxy.oa.util.PageResult;

public interface OnlineTestService extends DaoSupport<TestLibrary> {

	void save(TestLibrary testLibrary);

	boolean saveSubject(TestLibrary testLibrary);
	
	// 按分页信息查询试题
	PageResult querySubjectByPage(Page page,Course course);

	//模糊查询试题信息
	PageResult likeQueryBySubjectTitle(String subjectTitle, Page page, Course course);

	//根据试题删除指定试题
	<T>void deleteInfo(T info);

	//查看某个试题
	TestLibrary showSubjectParticular(Long id);

	//更新题目
	boolean updateSubject(TestLibrary testLibrary);

	//随记生成考试题目
	List<TestLibrary> randomFindSubject(int count,Course course);

	//保存一次测试信息
	void savaTestCase(OnlineTestCase onlineTestCase);

	//获得学生个数
	int getCountByCourse(Course course);

	//初始化学生的成绩
	void initStudentResult(Result result,Course course);

	//取出对应学生的成绩
	Result getResult(User student,Course course,Term term,int count);

	//更新信息
	<T>void updateInfo(T info);
	
	//保存信息
	<T>void saveInfo(T info);

	//获得考试信息
	OnlineTestCase getTestCase(Course course, Term thisTerm, int count);

	//进行评分
	int accountResult(List<Long> subjectIdList, List<String> subjectAnswerList);

	//根据信息获得第一次抽题的题目id
	TestTitleId findTestTitleId(Course course, User currentUser);

	//根据试题题目id批量获取试题
	List<TestLibrary> gainSubject(String testTitleIds);

	//根据课程查看题库相应题目的数量
	List<TestLibrary> getTestLibraryByCourse(Course course);

	//根据用户还有课程，获取指定试题临时表的字段
	TestTitleId getTestTitleIdBySC(User student, Course course);



}
