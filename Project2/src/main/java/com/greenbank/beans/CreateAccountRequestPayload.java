package com.greenbank.beans;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateAccountRequestPayload {

	UserInfo userInfo;
	String type;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	//custom methods
	
	public void generatePassword() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedPassword = RandomStringUtils.random(length, useLetters, useNumbers);
		this.userInfo.setPassword(generatedPassword);
	}
	
	public void generateUsername() {
		String generatedUsername = RandomStringUtils.randomAlphabetic(10);
		this.userInfo.setUsername(generatedUsername);
	}
	
	
	//pojo
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateAccountRequestPayload other = (CreateAccountRequestPayload) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OpenAccountRequestPayload [userInfo=" + userInfo + ", type=" + type + "]";
	}
	
}
