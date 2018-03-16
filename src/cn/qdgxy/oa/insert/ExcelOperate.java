package cn.qdgxy.oa.insert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.qdgxy.oa.domain.Role;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.UserService;

/**
 * Excel --->Mysql
 * 
 * @author Guangxing
 *
 */
@SuppressWarnings("serial")
public class ExcelOperate extends ActionSupport {

	private File src;
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	private final static String msgError = "<script>parent.callback('leadUserError')</script>";
	private final static String msgOK = "<script>parent.callback('leadUserOK')</script>";

	public String leadUserUI() throws Exception {
		return "leadUserUI";
	}

	public void leadUser() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		UserService userService = (UserService) ac.getBean("userServiceImpl");
		User user = new User();
		Role role = new Role();
		Set<Role> roles = new HashSet<Role>();
		String[][] result = getData(src, 1);
		if (result == null) {
			out.print(msgError);
			out.close();
			return;
		}
		int rowLength = result.length;
		String teacherName = null;
		try {
			teacherName = result[3][0];
		} catch (Exception e1) {
			e1.printStackTrace();
			out.print(msgError);
			out.close();
			return;
		}
		if (teacherName != null) {
			// 任课教师：[2007061]杨新艳
			String teacherId = teacherName.replaceAll(".*?(\\d+).*", "$1");
			teacherName = teacherName.replaceAll(".*?\\d+](.*)", "$1");
			user.setLoginName(teacherId);
			String md5 = DigestUtils.md5Hex(teacherId);
			user.setPassword(md5);
			user.setName(teacherName);
			user.setIsTorS(2);
			// 教师的roleId是1
			role.setId(1L);
			roles.add(role);
			user.setRoles(roles);
			userService.save(user);
		}
		for (int i = 5; i < rowLength; i++) {
			try {
				if (result[i][4].endsWith("班")) {
					user.setLoginName(result[i][1]);
					String md5 = DigestUtils.md5Hex(result[i][1]);
					user.setPassword(md5);
					user.setName(result[i][2]);
					user.setGender(result[i][3]);
					user.setIsTorS(1);
					// 学生的roleId是2
					role.setId(2L);
					roles.add(role);
					user.setRoles(roles);
					userService.save(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.print(msgError);
				out.close();
				return;
			}
		}
		out.print(msgOK);
		out.close();

	}

	/**
	 * 
	 * @param file
	 *            表格名称
	 * @param ignoreRows
	 *            表格开始的行
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String[][] getData(File file, int ignoreRows) {

		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = null;
		HSSFSheet st = null;
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = null;
		HSSFCell cell = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			fs = new POIFSFileSystem(in);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			st = wb.getSheetAt(sheetIndex);
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}
				if (hasValue) {
					result.add(values);
				}
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
		
	}

	/**
	 * 
	 * @param str
	 * @return
	 */

	public static String rightTrim(String str) {
		
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
		
	}

	public File getSrc() {
		return src;
	}

	public void setSrc(File src) {
		this.src = src;
	}

}
