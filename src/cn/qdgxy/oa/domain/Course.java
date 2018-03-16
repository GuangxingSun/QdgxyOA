package cn.qdgxy.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 课程
 * 
 * @author GuangXing
 * 
 */
@SuppressWarnings("serial")
public class Course implements java.io.Serializable {

	private Long id;
	private String name;
	private String description;
	private int onlineTestCount;//测试数目
	
	private Set<Homework> homeworks = new HashSet<Homework>();
	private Set<Result> results = new HashSet<Result>();
	private Set<TestLibrary> testLibraries = new HashSet<TestLibrary>();
	private Set<Term> terms = new HashSet<Term>();
	private Set<Clazz> clazzs = new HashSet<Clazz>();
	private Set<OnlineTestCase> onlineTestCases = new HashSet<OnlineTestCase>();
	
	private Set<TestTitleId> testTitleIds = new HashSet<TestTitleId>();
	// 瞬时态
	private int homeworkCount;  //作业数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public int getHomeworkCount() {
		return homeworkCount;
	}

	public void setHomeworkCount(int homeworkCount) {
		this.homeworkCount = homeworkCount;
	}

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public Set<TestLibrary> getTestLibraries() {
		return testLibraries;
	}

	public void setTestLibraries(Set<TestLibrary> testLibraries) {
		this.testLibraries = testLibraries;
	}

	public int getOnlineTestCount() {
		return onlineTestCount;
	}

	public void setOnlineTestCount(int onlineTestCount) {
		this.onlineTestCount = onlineTestCount;
	}

	public Set<Term> getTerms() {
		return terms;
	}

	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}

	public Set<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public Set<OnlineTestCase> getOnlineTestCases() {
		return onlineTestCases;
	}

	public void setOnlineTestCases(Set<OnlineTestCase> onlineTestCases) {
		this.onlineTestCases = onlineTestCases;
	}

	public Set<TestTitleId> getTestTitleIds() {
		return testTitleIds;
	}

	public void setTestTitleIds(Set<TestTitleId> testTitleIds) {
		this.testTitleIds = testTitleIds;
	}


}
