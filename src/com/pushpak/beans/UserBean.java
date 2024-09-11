package com.pushpak.beans;

import java.io.InputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBean implements Serializable {

	public UserBean() {
	}
                                                                                                                 //changes below
	public UserBean(String userName, Long mobileNo, String emailId, String address, int pinCode, String password,InputStream profile_photo) {
		super();
		this.name = userName;
		this.mobile = mobileNo;
		this.email = emailId;
		this.address = address;
		this.pinCode = pinCode;
		this.password = password;
		
		//changes stream
		this.profile_photo = profile_photo;
	}

	public InputStream getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(InputStream profile_photo) {
		this.profile_photo = profile_photo;
	}

	private String name;
	private Long mobile;
	private String email;
	private String address;
	private int pinCode;
	private String password;

	private InputStream profile_photo;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
