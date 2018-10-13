package com.proj.dao;

import java.util.List;

import com.proj.models.Friend;
import com.proj.models.User;

public interface FriendDAO {
	List<User> getSuggestedUsers(String email);

	void addFriendRequest(Friend friend);

	List<Friend> getPendingRequests(String email);

	void acceptFriendRequest(Friend friend);

	void deleteFriendRequest(Friend friend);

	List<User> listOfFriends(String email);
	}




