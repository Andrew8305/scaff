package com.scaff.oauth2.service;

import com.scaff.oauth2.dto.CustomClientDetails;
import com.scaff.oauth2.dto.CustomUserDetails;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xyl on 11/20/17.
 */
@Slf4j
public class CustomUserDetailService implements UserDetailsService {


    @Resource
    private CustomClientDetailService customClientDetailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails client = customClientDetailService.loadClientByClientId(username);
        if (client == null) {
            log.info("No client found for username: " + username);
            throw new AuthenticationCredentialsNotFoundException("Client not found for username: " + username);
        }
        boolean enabled = ((CustomClientDetails)client).isEnabled();

        UserDetails userDetails = new CustomUserDetails(client.getClientId(), client.getClientSecret(), // no need for client secret for pre auth scenario!
                enabled, client.getAuthorities());
        log.debug("User details populated from client data: " + userDetails.toString());

        return userDetails;
    }

}
