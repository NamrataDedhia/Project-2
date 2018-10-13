package com.proj.dao;

import com.proj.models.User;

public interface UserDAO {
	void registration(User user);
	boolean isEmailUnique(String email);
	User login(User user);//i/p - user with email and pwd
	                      //o/p -1 user obj with email,pwd,fname,lname,pnumber,role,online
	                      // or null value

	void updateUser(User user);
	User getUser(String email);
	}

