package com.example.demo.entities;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.UserEntity;

import  java.util.List;

public class MyUserDetails implements UserDetails {

    private String userName;

    private String password;

//    private String roles;

    private boolean active;

    private UserEntity myUser;

    private List<GrantedAuthority>authorities;


    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserEntity getMyUser() {
		return myUser;
	}

	public void setMyUser(UserEntity myUser) {
		this.myUser = myUser;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public MyUserDetails() {
    }

    public MyUserDetails(UserEntity user) {
        this.myUser=user;
        this.authorities=
                Arrays.stream(user.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

/*    public MyUserDetails(UserEntity user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active=user.isActive();
        this.authorities= Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Arrays.asList(new SimpleGrantedAuthority("Role_ADMIN"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return myUser.getPassword();
    }

    @Override
    public String getUsername() {
        return userName;
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
        return active;
    }

}