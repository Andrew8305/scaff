package com.scaff.oauth2.dto;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * Created by xyl on 11/20/17.
 */
public class CustomClientDetails extends BaseClientDetails {

    private static final long serialVersionUID = -7298772815246079578L;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public CustomClientDetails() {
        super();
    }

    public CustomClientDetails(ClientDetails prototype) {
        super(prototype);
    }

    public CustomClientDetails(String clientId, String resourceIds, String scopes, String grantTypes,
                               String authorities, String redirectUris) {
        super(clientId, resourceIds, scopes, grantTypes, authorities, redirectUris);
    }

    public CustomClientDetails(String clientId, String resourceIds, String scopes, String grantTypes,
                               String authorities) {
        super(clientId, resourceIds, scopes, grantTypes, authorities);
    }


}
