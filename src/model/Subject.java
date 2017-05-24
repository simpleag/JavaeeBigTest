package model;
/*
 * 课程id 课程名称 课程信息 课程标签
 */
public class Subject {
	private String subId = "";
	private String subName = "";
	private String subInfo = "";
	private String subTab = "";
	
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubInfo() {
		return subInfo;
	}
	public void setSubInfo(String subInfo) {
		this.subInfo = subInfo;
	}
	public String getSubTab() {
		return subTab;
	}
	public void setSubTab(String subTab) {
		this.subTab = subTab;
	}
}
