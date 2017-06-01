package model;
/*
 * 班级类 避免和class冲突
 * 班级Id  教师id 课程id　课程编号　上课时间　上课地点　班级标签　 
 */
public class Teachingclass {
	private int classId;
	private String teacherId = "";
	private String subId;
	private String classNum = "";
	private String classTime = "";
	private String classAdd = "";
	private String classTag = "";
	private String classTempTime = "";
	
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getClassAdd() {
		return classAdd;
	}
	public void setClassAdd(String classAdd) {
		this.classAdd = classAdd;
	}
	public String getClassTag() {
		return classTag;
	}
	public void setClassTag(String classTag) {
		this.classTag = classTag;
	}
	public String getClassTempTime() {
		return classTempTime;
	}
	public void setClassTempTime(String classTempTime) {
		this.classTempTime = classTempTime;
	}
	
}
