package com.greenbank.data;

import com.greenbank.beans.UserInfo;


public interface UserInfoDAO {
	public int addUser(UserInfo user);
	
	public UserInfo getUser(String username, String password);
	
	public UserInfo getUser(UserInfo u);
	
	public UserInfo getUserById(UserInfo u);
	
	public void deleteUser(UserInfo user);
	
	public void updateUser(UserInfo user);
}
