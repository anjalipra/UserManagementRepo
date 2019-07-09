package com.user.message;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
@ApiModel(description = "All details required for the user profile management")
public class UserProfile {

    @NotBlank
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "User firstname")
    private String firstName;
    
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "User lastname")
    private String lastName;
    
    @ApiModelProperty(notes = "User date of birth")
    private Date dateOfBirth;
    
    @NotBlank
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "Username")
    private String username;
 
    @NotBlank
    @Size(max = 60)
    @Email
    @ApiModelProperty(notes = "User email address")
    private String email;
    
    @ApiModelProperty(notes = "User role")
    private String role;
    
    @ApiModelProperty(notes = "User home address")
	private String homeAddress;
	
    @ApiModelProperty(notes = "User office address")
	private String officeAddress;
    
    @NotBlank
    @Size(min = 6, max = 40)
    @ApiModelProperty(notes = "Password")
    private String password;
 
    public String getName() {
        return firstName;
    }
 
    public void setName(String name) {
        this.firstName = name;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
      return this.role;
    }
    
    public void setRole(String role) {
      this.role = role;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
}