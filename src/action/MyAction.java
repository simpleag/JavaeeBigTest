package action;

import dao.CommonDao;
/*
 * 测试mvc架构的测试方式
 */
public class MyAction {
	private String defaultResult;
	private CommonDao commonDao;
	
	public String getDefaultResult() {
		return defaultResult;
	}
	public void setDefaultResult(String defaultResult) {
		this.defaultResult = defaultResult;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
