package com.github.hollykunge.openapi.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails {

    protected Integer id;
    private String username;
    private String password;
    private boolean lockedFlag;
    private String token;
    private String accessToken;
    private String refreshToken;

    public MyUserDetails(String username, String password, boolean lockedFlag,Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.lockedFlag = lockedFlag;
        this.authorities = roles;
    }

    public MyUserDetails(String username, String password,String accessToken,String refreshToken, boolean lockedFlag,Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.lockedFlag = lockedFlag;
        this.authorities = roles;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return  !lockedFlag;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}