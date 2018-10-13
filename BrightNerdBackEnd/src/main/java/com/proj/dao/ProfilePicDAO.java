package com.proj.dao;

import com.proj.models.ProfilePic;

public interface ProfilePicDAO {
	void uploadProfilePicture(ProfilePic profilePic);
	ProfilePic  getProfilePicture(String email);
	}
