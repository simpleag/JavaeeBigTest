package model;

public class Customers {
	private String CustomerID = "";
	private String CompanyName = "";
	private String ContactName = "";
	private String ContactTitle = "";
	private String Address = "";
	private String City = "";
	private String Region = "";
	private String PostalCode = "";
	private String Country = "";
	private String Phone = "";
	private String Fax = "";
	public Customers(){
		this.ContactName = "c";
		this.ContactTitle = "ct";
		this.Address = "a";
		this.City = "c";
		this.Region = "r";
		this.PostalCode = "p";
		this.Country = "g";
		this.Phone = "po";
		this.Fax = "f";
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String contactName) {
		ContactName = contactName;
	}
	public String getContactTitle() {
		return ContactTitle;
	}
	public void setContactTitle(String contactTitle) {
		ContactTitle = contactTitle;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	
	
}
