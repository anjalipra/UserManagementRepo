package com.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.model.User;
 
public class UserPrinciple implements UserDetails {
  private static final long serialVersionUID = 1L;
 
  private Long id;
 
    private String name;
 
    private String username;
 
    private String email;
 
    @JsonIgnore
    private String password;
 
    private GrantedAuthority authority;
 
    public UserPrinciple(Long id, String name, 
              String username, String email, String password, 
              GrantedAuthority authority) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
 
    public static UserPrinciple build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().name());
		/*
		 * .stream().map(role -> new SimpleGrantedAuthority(role.getName().name())
		 * ).collect(Collectors.toList());
		 */
 
        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authority
        );
    }
 
    public Long getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
    	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(authority);
        return authorities;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
