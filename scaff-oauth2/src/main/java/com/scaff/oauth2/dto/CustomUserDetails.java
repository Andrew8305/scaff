package com.scaff.oauth2.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import lombok.Data;

/**
 * Created by xyl on 11/20/17.
 */
@Data
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -4748400110652585649L;

    private String username;
    private String password;
    private boolean enabled;
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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

    public CustomUserDetails() {
        super();
    }

    public CustomUserDetails(String username, String password, boolean enabled, Collection<GrantedAuthority> authorities) {
        super();
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }


}
