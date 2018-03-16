package cn.qdgxy.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.OnlineTestCase;
import cn.qdgxy.oa.domain.Result;
import cn.qdgxy.oa.domain.Term;
import cn.qdgxy.oa.domain.TestLibrary;
import cn.qdgxy.oa.domain.TestTitleId;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.OnlineTestService;
import cn.qdgxy.oa.util.Page;
import cn.qdgxy.oa.util.PageResult;
import cn.qdgxy.oa.util.PageUtil;

@Service
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class OnlineTestServiceImpl extends DaoSupportImpl<TestLibrary>
		implements OnlineTestService {
	
	//更新信息
	public <T>void updateInfo(T info) {
		getSession().update(info);
	}
	
	//保存信息
	public <T>void saveInfo(T info) {
		getSession().save(info);
	}
	
	// 删除信息
	public <T>void deleteInfo(T info) {
		getSession().delete(info);
	}

	
	// 查看某个试题
	public TestLibrary showSubjectParticular(Long id) {
		return findSubjectByID(id);
	}


	public void save(TestLibrary testLibrary) {
		// 1，设置属性并保存
		testLibrary.setPutTime(new Date()); // 当前时间
		getSession().save(testLibrary);

	}

	@Override
	public boolean saveSubject(TestLibrary testLibrary) {

		// 获取当前录入试题的题目
		String subjectTile = testLibrary.getSubjectTitle();
		if (findSubjectByTitle(subjectTile) == null) { // 如果该试题标题不存在，允许添加
			save(testLibrary);
			return true;
		} else {
			return false;
		}
	}

	// =============================
	// 管理试题
	// 根据管理试题列出试题列表
	public PageResult querySubjectByPage(Page page, Course course) {
		page = PageUtil.createPage(page.getEveryPage(),
				findSubjectCount(course), page.getCurrentPage());// 根据总记录数创建分页信息
		List<TestLibrary> list = findSubjectByPage(page, course);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	// 模糊查询列出试题列表
	public PageResult likeQueryBySubjectTitle(String subjectTitle, Page page,
			Course course) {
		page = PageUtil
				.createPage(page.getEveryPage(),
						findLinkQueryCount(subjectTitle, course),
						page.getCurrentPage());// 根据总记录数创建分页信息
		List<TestLibrary> list = likeQueryByTitle(subjectTitle, page, course);// 通过分页信息模糊查询试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	
	

	// =========================
	// 判断是否有重名的题目
	public TestLibrary findSubjectByTitle(String subjectTitle) {

		List list = getSession().createQuery(//
				"FROM TestLibrary t WHERE t.subjectTitle = ?")//
				.setString(0, subjectTitle)//
				.list(); // 查询结果保存到list中
		if (list.size() == 0) {
			System.out.println("返回空，没有查到相同题目的试题！");
			return null; // 返回null
		} else {
			return (TestLibrary) list.get(0); // 返回第一个试题
		}
	}

	// 查询试题的数量
	public int findSubjectCount(Course course) {
		return getSession().createQuery(//
				"FROM TestLibrary t WHERE t.course = ?")//
				.setParameter(0, course)//
				.list()// //查询结果保存到list中
				.size();
	}

	public List<TestLibrary> findSubjectByPage(Page page, Course course) {
		return getSession().createQuery(//
				"FROM TestLibrary t WHERE t.course = ?")//
				.setParameter(0, course)//
				.setMaxResults(page.getEveryPage())// 设置查询记录数
				.setFirstResult(page.getBeginIndex())// 设置查询记录起始位置
				.list();// 查询结果保存到list中
	}

	// 查询与标题题目相同的 数量
	public int findLinkQueryCount(String subjectTitle, Course course) {
		return getSession()
				.createQuery(
						"FROM TestLibrary t WHERE t.subjectTitle like ? AND t.course = ?")//
				.setParameter(0, "%" + subjectTitle + "%")//
				.setParameter(1, course)//
				.list()// 查询结果保存到list中
				.size();
	}

	public List<TestLibrary> likeQueryByTitle(String subjectTitle, Page page,
			Course course) {
		return getSession()
				.createQuery(
						"FROM TestLibrary t WHERE t.subjectTitle like ? AND t.course = ?")//
				.setParameter(0, "%" + subjectTitle + "%")//
				.setParameter(1, course)//
				.setMaxResults(page.getEveryPage())// 设置查询记录数
				.setFirstResult(page.getBeginIndex())// 查询结果保存到list中
				.list();
	}

	// 根据试题id查看试题
	private TestLibrary findSubjectByID(Long id) {
		return (TestLibrary) getSession()
				.createQuery("FROM TestLibrary t WHERE t.id=?")//
				.setLong(0, id)//
				.uniqueResult();

	}

	// 更新试题
	public boolean updateSubject(TestLibrary testLibrary) {

		System.out.println("输出试题的题目-----==---》："
				+ testLibrary.getSubjectTitle());
		// 获取当前录入试题的题目
		String subjectTile = testLibrary.getSubjectTitle();
		if (findSubjectByTitle(subjectTile) == null) { // 如果该试题标题不存在，允许添加
			System.out.println("--------------------更新成功!");
			testLibrary.setPutTime(new Date());
			update(testLibrary);
			return true;
		} else {
			return false;
		}
	}

	// 保存测试信息
	@Override
	public void savaTestCase(OnlineTestCase onlineTestCase) {
		getSession().save(onlineTestCase);

	}

	// 获得学生的个数
	public int getCountByCourse(Course course) {
		Long count = (Long) getSession()
				.createQuery(//
						"SELECT COUNT(DISTINCT s) FROM User s JOIN s.clazzs c JOIN c.courses e WHERE e = ?")//
				.setParameter(0, course)//
				.uniqueResult();
		return count.intValue();
	}
	
	
	//初始化学生的成绩
	public void initStudentResult(Result result, Course course) {
		//获取所有关联该课程的学生
		List<User> studentList = getSession().createQuery(//
				"SELECT s FROM User s JOIN s.clazzs c JOIN c.courses e WHERE e = ?")//
				.setParameter(0, course)//
				.list();
		//给每位同学都初始化同样的信息
		for(User students : studentList){
			result.setStudent(students);
			getSession().save(result);
			getSession().clear();
		}
	}
	//根据课程查看题库相应题目的数量
	public List<TestLibrary> getTestLibraryByCourse(Course course) {
		return getSession().createQuery(//
				"FROM TestLibrary t WHERE t.course = ?")//
				.setParameter(0, course)//
				.list();
	
	}

	
	
	

	// ------------------------------学生生成试题试卷---------------------------
	public List<TestLibrary> randomFindSubject(int count,Course course) {
		return getSession().createQuery("FROM TestLibrary t  WHERE t.course = ? order by rand()")//
				.setMaxResults(count)// 设置查询记录数
				.setParameter(0, course)//设置要查询试题所属的科目
				.list(); // 查询结果保存到list中
	}


	//初始化学生成绩表的部分信息
	public Result getResult(User student,Course course,Term term,int count){
		return (Result) getSession().createQuery(//
				"FROM Result r WHERE r.student = ? AND r.course = ? AND r.term = ? AND r.count = ?")//
				.setParameter(0, student)//
				.setParameter(1, course)//
				.setParameter(2, term)//
				.setParameter(3, count)//
				.uniqueResult();
		
		
	}

	//根据参数信息获得具体的考试信息
	public OnlineTestCase getTestCase(Course course, Term thisTerm, int count) {
		return (OnlineTestCase) getSession().createQuery(//
				"FROM OnlineTestCase o WHERE o.course = ? AND o.thisTerm = ? AND o.number = ?")//
				.setParameter(0, course)//
				.setParameter(1, thisTerm)//
				.setParameter(2, count)//
				.uniqueResult();
	}

	//计算出最终的成绩
	public int accountResult(List<Long> subjectIdList, List<String> subjectAnswer) {
		int score = 0;//总分
		for(int i = 0; i < subjectIdList.size(); i++){
			//获得试题的正确答案！ 
			String rightAnswer = findSubjectByID(subjectIdList.get(i)).getAnswer();
			if(rightAnswer.equals(subjectAnswer.get(i))){
				score += 5 ; //加五分
			}
		}
		return score;
	}

	//获取出抽题的id
	public TestTitleId findTestTitleId(Course course, User student) {
		return (TestTitleId) getSession().createQuery(//
				"FROM TestTitleId t WHERE t.course = ? AND t.student = ?")//
				.setParameter(0, course)//
				.setParameter(1, student)//
				.uniqueResult(); 
	}

	//根据试题题目id批量获取试题
	public List<TestLibrary> gainSubject(String testTitleIds) { 
		return getSession().createQuery(//
				"SELECT t FROM TestLibrary t WHERE t.id IN ("+testTitleIds+")")//
				.list();
	}

	//根据用户还有课程，获取指定试题临时表的字段
	public TestTitleId getTestTitleIdBySC(User student, Course course) {
		return (TestTitleId) getSession().createQuery(
				 "FROM TestTitleId t WHERE t.course = ? AND t.student = ?")//
				 .setParameter(0, course)//
				 .setParameter(1, student)//
				 .uniqueResult();
	}

	


	
	
}
