package model;
/*
 * 选课和成绩类 
 * 选课Id 班级id 用户id 分数 选课班级
 */
public class Elective {
	private int electiveId;
	private String classId = "";
	private String userId = "";
	private String score = "";
	private String electiveTag	 = "";
	
	public int getElectiveId() {
		return electiveId;
	}
	public void setElectiveId(int electiveId) {
		this.electiveId = electiveId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getElectiveTag() {
		return electiveTag;
	}
	public void setElectiveTag(String electiveTag) {
		this.electiveTag = electiveTag;
	}
}
