package com.scaff.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by xyl on 11/20/17.
 */
@Data
public class OauthClientDetails {
    @Id
    @Column(name = "client_id", length=256)
    private String clientId;

    @Column(name = "resource_ids", length=256)
    private String resourceIds;

    @Column(name = "client_secret", length=256)
    private String clientSecret;

    @Column(name = "scope", length=256)
    private String scope;

    @Column(name = "authorized_grant_types", length=256)
    private String authorizedGrantTypes;

    @Column(name = "authorities", length=256)
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "enabled")
    private boolean enabled;

}
