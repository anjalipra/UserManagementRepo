package com.user.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
@ApiModel(description = "All details required for signing in. ")
public class LoginForm {
    @NotBlank
    @Size(min=3, max = 60)
    @ApiModelProperty(notes = "Username")
    private String username;
 
    @NotBlank
    @Size(min = 3, max = 40)
    @ApiModelProperty(notes = "Password")
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
