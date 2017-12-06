package com.scaff.oauth2.controller;

import com.scaff.oauth2.dto.CustomClientDetails;
import com.scaff.oauth2.dto.UserCreateUpdateDto;
import com.scaff.oauth2.dto.UserDeleteDto;
import com.scaff.oauth2.service.CustomClientDetailService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xyl on 11/20/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60; // 1 day

    private static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 7 * 24 * 60 *60; // 1 week

    @Autowired
    private CustomClientDetailService clientDetailService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserCreateUpdateDto userDto) {

        CustomClientDetails clientDetails = new CustomClientDetails();
        clientDetails.setClientId(userDto.getUsername()); // clientId = username
        clientDetails.setClientSecret(userDto.getPassword()); // secret = password; encoded in client detail service

        // if roles passed - set them as granted authorities
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            clientDetails.setAuthorities(populateAuthorities(userDto));
        }

        clientDetails.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        clientDetails.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        clientDetails.setScope(Arrays.asList("read", "write"));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
        clientDetails.setResourceIds(userDto.getResourceIds());
        clientDetails.setEnabled(userDto.getEnabled());

        // now store client with prepopulated data
        clientDetailService.addClientDetails(clientDetails);

        LOG.debug(userDto.getUsername() + " successfully stored!");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteDto userDto) {

        // delete user with given clientId = username
        clientDetailService.removeClientDetails(userDto.getUsername());
        LOG.debug(userDto.toString() + " successfully deleted!");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UserCreateUpdateDto userDto) {

        if (!StringUtils.isEmpty(userDto.getPassword())) {
            LOG.debug("Updating password for client: " + userDto.getUsername());
            clientDetailService.updateClientSecret(userDto.getUsername(), userDto.getPassword());
        }

        // if roles passed - set them as granted authorities
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            // find client, and if we passed new password or roles, change them
            BaseClientDetails clientDetails = (BaseClientDetails) (clientDetailService.loadClientByClientId(userDto.getUsername()));
            LOG.debug("Client: " + clientDetails.toString() + " found!");
            clientDetails.setAuthorities(populateAuthorities(userDto));
            LOG.debug("Updating roles for client: " + clientDetails.getClientId());
            clientDetailService.updateClientDetails(clientDetails); // roles update has different method
        }

        // now store client with prepopulated data
        LOG.debug(userDto.getUsername() + " successfully updated!");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/enable", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> enableUser(@RequestBody UserCreateUpdateDto userDto){
        BaseClientDetails clientDetails = (BaseClientDetails) (clientDetailService.loadClientByClientId(userDto.getUsername()));
        clientDetails.addAdditionalInformation("enabled", true);
        clientDetailService.updateClientDetails(clientDetails);
        LOG.debug(userDto.getUsername() + " successfully enabled!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Set<GrantedAuthority> populateAuthorities(UserCreateUpdateDto userDto) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : userDto.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}