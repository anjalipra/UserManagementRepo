package com.user.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.message.UserProfile;
import com.user.model.User;
import com.user.service.UserManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/users")
@Api(value="User Profile Management System", description="Operations pertaining to user profile in User Management System")
public class UserManagementController {
	
	private static final Logger logger = LogManager.getLogger(UserManagementController.class);

	@Autowired
	UserManagementService userManagementService;
	
	@ApiOperation(value = "Create a new user profile")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created user"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
        
    })
    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> createUser(@RequestBody UserProfile userProfile) {
		logger.info("Going to create the user.");
		return userManagementService.createUser(userProfile);
	}
	
    @ApiOperation(value = "Get the user profile based on the userId", response = User.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved user"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable(value = "userId") Long userId) {
    	logger.info("Going to fetch the user profile for userId : " + userId);
		return userManagementService.getUser(userId);
	}
	
    @ApiOperation(value = "Update the user profile")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated user"),
        @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/update/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateUser(@RequestBody UserProfile userProfile) {
    	logger.info("Going to update the user profile.");
    	return userManagementService.updateUser(userProfile);
	}
	
    
    @ApiOperation(value = "Delete the user profile based on a UserId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted user"),
        @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable(value = "userId") Long userId) {
    	logger.info("Going to delete the user profile.");
		userManagementService.deleteUser(userId);
	}
	
    @ApiOperation(value = "Assigning a role to a particualr user profile")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated user with the role"),
        @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/assignRole/{userId}/{role}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public String assignRole(@PathVariable(value = "userId") Long userId, @PathVariable(value = "role") String role) {
    	logger.info("Going to assign role to the user with userId : " + userId);
    	userManagementService.assignPermission(userId, role);
    	return "User assigned the " + role + " role.";
	}
	
    @ApiOperation(value = "Get the user profile of the current logged in user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully fetched user"),
        @ApiResponse(code = 401, message = "You are not authorized to get the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@RequestMapping(value = "/get/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
	public User getUser(Principal principal) {
    	logger.info("Going to get the user profile for the current user.");
		return userManagementService.getUser(principal.getName());	
	}
	
    @ApiOperation(value = "Delete the user profile of the current logged in user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted user"),
        @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
	public String deleteUser(Principal principal) {
    	logger.info("Going to delete the user profile for the current user.");
		userManagementService.deleteUser(principal.getName());
		return "User Deleted!";
	}
	
	/*
	 * @GetMapping("/api/test/user")
	 * 
	 * @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") public String
	 * userAccess() { return ">>> User Contents!"; }
	 * 
	 * @GetMapping("/api/test/pm")
	 * 
	 * @PreAuthorize("hasRole('PM') or hasRole('ADMIN')") public String
	 * projectManagementAccess() { return ">>> Board Management Project"; }
	 * 
	 * @GetMapping("/api/test/admin")
	 * 
	 * @PreAuthorize("hasRole('ADMIN')") public String adminAccess() { return
	 * ">>> Admin Contents"; }
	 */
}
