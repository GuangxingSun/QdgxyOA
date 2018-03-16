package cn.qdgxy.oa.util;

import java.util.List;

import cn.qdgxy.oa.domain.Clazz;

public class doZip {
	Compression zc;
	List<Clazz> clazzList;
	String src;
	
	public doZip(Compression zc, List<Clazz> clazzList, String src) {
		this.zc = zc;
		this.clazzList = clazzList;
		this.src = src;
	}

	public void zip() {
		String str1,str2,str3,str4,str5,str6;
		Clazz clazz1,clazz2,clazz3,clazz4,clazz5,clazz6;
		int count = clazzList.size();
		if (count == 1) {
			clazz1 = clazzList.get(0);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			zc.compress(str1);
			return;
		} else if (count == 2) {
			clazz1 = clazzList.get(0);
			clazz2 = clazzList.get(1);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			str2 = src + "\\" + clazz2.getGrade() + "级" + clazz2.getMajor() + "专业" + clazz2.getClazz() + "班";
			zc.compress(str1, str2);
		} else if (count == 3) {
			clazz1 = clazzList.get(0);
			clazz2 = clazzList.get(1);
			clazz3 = clazzList.get(2);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			str2 = src + "\\" + clazz2.getGrade() + "级" + clazz2.getMajor() + "专业" + clazz2.getClazz() + "班";
			str3 = src + "\\" + clazz3.getGrade() + "级" + clazz3.getMajor() + "专业" + clazz3.getClazz() + "班";
			zc.compress(str1, str2, str3);
		} else if (count == 4) {
			clazz1 = clazzList.get(0);
			clazz2 = clazzList.get(1);
			clazz3 = clazzList.get(2);
			clazz4 = clazzList.get(3);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			str2 = src + "\\" + clazz2.getGrade() + "级" + clazz2.getMajor() + "专业" + clazz2.getClazz() + "班";
			str3 = src + "\\" + clazz3.getGrade() + "级" + clazz3.getMajor() + "专业" + clazz3.getClazz() + "班";
			str4 = src + "\\" + clazz4.getGrade() + "级" + clazz4.getMajor() + "专业" + clazz4.getClazz() + "班";
			zc.compress(str1, str2, str3, str4);
		} else if (count == 5) {
			clazz1 = clazzList.get(0);
			clazz2 = clazzList.get(1);
			clazz3 = clazzList.get(2);
			clazz4 = clazzList.get(3);
			clazz5 = clazzList.get(4);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			str2 = src + "\\" + clazz2.getGrade() + "级" + clazz2.getMajor() + "专业" + clazz2.getClazz() + "班";
			str3 = src + "\\" + clazz3.getGrade() + "级" + clazz3.getMajor() + "专业" + clazz3.getClazz() + "班";
			str4 = src + "\\" + clazz4.getGrade() + "级" + clazz4.getMajor() + "专业" + clazz4.getClazz() + "班";
			str5 = src + "\\" + clazz5.getGrade() + "级" + clazz5.getMajor() + "专业" + clazz5.getClazz() + "班";
			zc.compress(str1, str2, str3, str4, str5);
		} else if (count == 6) {
			clazz1 = clazzList.get(0);
			clazz2 = clazzList.get(1);
			clazz3 = clazzList.get(2);
			clazz4 = clazzList.get(3);
			clazz5 = clazzList.get(4);
			clazz6 = clazzList.get(5);
			str1 = src + "\\" + clazz1.getGrade() + "级" + clazz1.getMajor() + "专业" + clazz1.getClazz() + "班";
			str2 = src + "\\" + clazz2.getGrade() + "级" + clazz2.getMajor() + "专业" + clazz2.getClazz() + "班";
			str3 = src + "\\" + clazz3.getGrade() + "级" + clazz3.getMajor() + "专业" + clazz3.getClazz() + "班";
			str4 = src + "\\" + clazz4.getGrade() + "级" + clazz4.getMajor() + "专业" + clazz4.getClazz() + "班";
			str5 = src + "\\" + clazz5.getGrade() + "级" + clazz5.getMajor() + "专业" + clazz5.getClazz() + "班";
			str6 = src + "\\" + clazz6.getGrade() + "级" + clazz6.getMajor() + "专业" + clazz6.getClazz() + "班";
			zc.compress(str1, str2, str3, str4, str5, str6);
		}
	}
}
