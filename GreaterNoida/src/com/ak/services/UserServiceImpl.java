package com.ak.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.beans.UserBean;
import com.ak.daos.UserDao;
import com.ak.modals.General;
import com.ak.modals.UserDepartments;
import com.ak.modals.Users;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	@Override
	public String authUser(String userId,String password)
	{
		return userDao.authUser(userId,password);
	}

	@Override
	public UserBean getUserBean(String userId)
	{
		return userDao.getUserBean(userId);
	}

	@Override
	public boolean isUserIdExists(String userId)
	{
		return userDao.isUserIdExists(userId);
	}

	@Override
	public void insertOrUpdateUser(Users user)
	{
		userDao.insertOrUpdateUser(user);		
	}

	@Override
	public void insertOrUpdateUserDepartments(ArrayList<UserDepartments> userDepartments)
	{
		userDao.insertOrUpdateUserDepartments(userDepartments);		
	}
/*=================================================================*/
	@Override
	public Users getUserDetails(String userId)
	{
		return userDao.getUserDetails(userId);
	}

	/* ================================================================== */
	@Override
	public void removeUser(String userId)
	{
		userDao.removeUser(userId);		
	}

	@Override
	public ArrayList<General> getAllRecords(String department)
	{
		return userDao.getAllRecords(department);
	}	
}