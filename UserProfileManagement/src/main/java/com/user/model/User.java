package com.user.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
@ApiModel(description = "All details about the User Profile for mapping to the database.")
public class User{
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  	@ApiModelProperty(notes = "The database generated User ID")
    private Long id;
 
    @NotBlank
    @Size(min=3, max = 50)
    @ApiModelProperty(notes = "User first name")
    private String firstName;
 
    @ApiModelProperty(notes = "User last name")
    private String lastName;
    
    @ApiModelProperty(notes = "User date of birth")
    private Date dateOfBirth;
    
    @NotBlank
    @Size(min=3, max = 50)
    @ApiModelProperty(notes = "Username")
    private String username;
 
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    @ApiModelProperty(notes = "User email address")
    private String email;
    
    @ApiModelProperty(notes = "User home address")
	private String homeAddress;
	
    @ApiModelProperty(notes = "User office address")
	private String officeAddress;
 
    @NotBlank
    @Size(min=6, max = 100)
    @ApiModelProperty(notes = "Password")
    private String password;
 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Role role = new Role();
 
    
    public User() {}
 
    public User(String firstName, String lastName, Date dateOfBirth, String username, String email, String password, 
    		String homeAddress, String officeAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.email = email;
        this.password = password;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getName() {
        return firstName;
    }
 
    public void setName(String name) {
        this.firstName = name;
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
 
    public Role getRole() {
        return role;
    }
 
    public void setRole(Role role) {
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