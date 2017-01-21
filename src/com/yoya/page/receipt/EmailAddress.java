package com.yoya.page.receipt;

public class EmailAddress {
	
	private String real_name;
	private String mobile_number;
	private String province;
	private String city;
	private String address;
	private String email_code;
	private String postscript;
	public EmailAddress(){
		//
	}

	public EmailAddress(String real_name, String mobile_number, String province, String city, String address,
			String email_code, String postscript) {
		this.real_name = real_name;
		this.mobile_number = mobile_number;
		this.province = province;
		this.city = city;
		this.address = address;
		this.email_code = email_code;
		this.postscript = postscript;
	}

	public String getReal_name() {
		return real_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail_code() {
		return email_code;
	}

	public String getPostscript() {
		return postscript;
	}
	
	
	
	
	
	
}
