package action;

import dao.CustomersDAO;
/*
 * 测试mvc架构的测试方式
 */
public class MyAction {
	private String defaultResult;
	private CustomersDAO customersDAO;
	public String getDefaultResult() {
		return defaultResult;
	}
	public void setDefaultResult(String defaultResult) {
		this.defaultResult = defaultResult;
	}
	public CustomersDAO getCustomersDAO() {
		return customersDAO;
	}
	public void setCustomersDAO(CustomersDAO customersDAO) {
		this.customersDAO = customersDAO;
	}
}
