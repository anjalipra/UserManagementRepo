package com.user.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.controller.UserManagementController;
import com.user.exception.UserNotFoundException;
import com.user.message.UserProfile;
import com.user.model.Role;
import com.user.model.RoleName;
import com.user.model.User;
import com.user.repository.RoleRepository;
import com.user.repository.UserRepository;
import com.user.security.jwt.JwtTokenProvider;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private static final Logger logger = LogManager.getLogger(UserManagementController.class);

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtTokenProvider jwtProvider;
	
    /**
     * 
     */
	public ResponseEntity<String> createUser(UserProfile userProfile) 
	{
		logger.info("Going to create the user.");
		 if(userRepository.existsByUsername(userProfile.getUsername())) {
			 logger.error("Username is already taken");
	            return new ResponseEntity<String>("Fail -> Username is already taken!",
	                    HttpStatus.BAD_REQUEST);
	        }
	 
	        if(userRepository.existsByEmail(userProfile.getEmail())) {
	        	 logger.error("Email is already in use");
	            return new ResponseEntity<String>("Fail -> Email is already in use!",
	                    HttpStatus.BAD_REQUEST);
	        }
	 
	        // Creating user's account
	        User user = new User(userProfile.getFirstName(), userProfile.getLastName(), userProfile.getDateOfBirth(), userProfile.getUsername(),
	        		userProfile.getEmail(), encoder.encode(userProfile.getPassword()), userProfile.getHomeAddress(), userProfile.getOfficeAddress());
	 
	        String role = userProfile.getRole();
	        user.setRole(getRole(role));
	        userRepository.save(user);
	 
	        logger.info("User registered successfully");
	        return ResponseEntity.ok().body("User registered successfully!");
		
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(Long userId) {
		logger.info("Going to get user with UserId : " + userId);
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent()) {
			logger.error("User not found");
			throw new UserNotFoundException("User not found with Id: " + userId);
		}
		return user.get();

	}
	
	public ResponseEntity<String> updateUser(UserProfile userProfile) {
		logger.info("Going to update the user");
		Iterable<User> users = userRepository.findAll();
		
		for(User user: users) {
			if(user.getUsername().equals(userProfile.getUsername())) {
				userRepository.delete(user);
				break;
			}
		}

		User user = new User(userProfile.getFirstName(), userProfile.getLastName(), userProfile.getDateOfBirth(),
				userProfile.getUsername(), userProfile.getEmail(), encoder.encode(userProfile.getPassword()),
				userProfile.getHomeAddress(), userProfile.getOfficeAddress());
		String role = userProfile.getRole();
		user.setRole(getRole(role));
		
		userRepository.save(user);
		logger.info("User updated successfully");
		return ResponseEntity.ok().body("User updated successfully!");
	}
	
	public ResponseEntity<String> deleteUser(Long userId) {
		logger.info("Going to delete the user with userId : " +  userId);
		if(userId == 1) {
			return new ResponseEntity<String>("Cannot delete super user.",
                    HttpStatus.BAD_REQUEST);
		}
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			logger.error("User not found");
			throw new UserNotFoundException("User not found with Id: " + userId);
		}

		userRepository.deleteById(userId);
		logger.info("User updated successfully!");
		return ResponseEntity.ok().body("User deleted successfully!");
	}
	
	public void assignPermission(Long userId, String role) {
		logger.info("Going to assign permission");
		Optional<User> user = userRepository.findById(userId);
		
		if (!user.isPresent()) {
			logger.error("User not found");
			throw new UserNotFoundException("User not found with Id: " + userId);
		}
		
		User updatedUser = user.get();
		updatedUser.setRole(getRole(role));
		
		userRepository.save(updatedUser);
	}
	
	/**
	 * 
	 */
	public User getUser(String userName) {
		Iterable<User> users = userRepository.findAll();
		
		for(User user: users) {
			if(user.getUsername().equals(userName))
				return user;
		}
		
		logger.error("User not found");
		throw new UserNotFoundException("User not found with Name: " + userName);
	}
	
	public void deleteUser(String userName) {
		boolean userPresent = false;
		Iterable<User> users = userRepository.findAll();

		for (User user : users) {
			if (user.getUsername().equals(userName)) {
				userRepository.delete(user);
				userPresent = true;
			}
		}
		
		if (!userPresent) {
			logger.error("User not found");
			throw new UserNotFoundException("User not found with Name: " + userName);
		}

	}
	
	/**
	 * This method maps the role name to role type post validation of the role.
	 * @param role
	 * @return
	 */
	private Role getRole(String role) {
        Role assignedRole = null;
        
		switch (role) {
		case "admin":
			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			assignedRole = adminRole;

			break;
		case "user":
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			assignedRole = userRole;
		}
        
        return assignedRole;
	}

}
