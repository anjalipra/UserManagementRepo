package com.user.service;

import org.springframework.http.ResponseEntity;

import com.user.message.UserProfile;
import com.user.model.User;

public interface UserManagementService {

	//admin
	public ResponseEntity<String> createUser(UserProfile userProfile);
	
	//admin
	public User getUser(Long userId);
	
	//admin
	public ResponseEntity<String> updateUser(UserProfile userProfile);
	
	//admin
	public ResponseEntity<String> deleteUser(Long userId);
	
	//admin
	public void assignPermission(Long userId, String role);
	
	//user
	public User getUser(String userName);
	
	//user
	public void deleteUser(String userName);
}
