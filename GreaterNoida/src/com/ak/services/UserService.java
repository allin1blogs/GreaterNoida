package com.ak.services;

import java.util.ArrayList;

import com.ak.beans.UserBean;
import com.ak.modals.General;
import com.ak.modals.UserDepartments;
import com.ak.modals.Users;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface UserService
{
	public String authUser(String userId,String password);
	public UserBean getUserBean(String userId);
	public boolean isUserIdExists(String userId);
	public void insertOrUpdateUser(Users user);
	public void insertOrUpdateUserDepartments(ArrayList<UserDepartments> userDepartments);
	public Users getUserDetails(String userId);
	public void removeUser(String userId);
	public ArrayList<General> getAllRecords(String department);
}
