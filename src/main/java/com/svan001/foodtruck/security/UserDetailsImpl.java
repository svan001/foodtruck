package com.svan001.foodtruck.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;

    // We always default this values for now
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private Collection<? extends GrantedAuthority> authorities = null;

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
