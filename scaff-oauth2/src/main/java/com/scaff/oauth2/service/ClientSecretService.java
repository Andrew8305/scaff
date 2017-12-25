package com.scaff.oauth2.service;
import com.google.common.collect.Lists;

import com.scaff.oauth2.dao.ClientSecretMapper;
import com.scaff.enums.oauth.ClientSecretStatus;
import com.scaff.oauth2.dto.ApiClientDTO;
import com.scaff.oauth2.model.ClientSecret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by xyl on 12/14/17.
 */
@Service
public class ClientSecretService {
    @Autowired
    private ClientSecretMapper clientSecretMapper;

    public void createClientSecret(ApiClientDTO apiClient) {
       ClientSecret clientSecret =ClientSecret.builder()
                .clientId(apiClient.getClientId())
                .clientSecret(apiClient.getClientSecret())
                .tenantId(apiClient.getTenantId())
                .purpose(apiClient.getPurpose())
                .status(ClientSecretStatus.ACTIVE)
                .build();

        clientSecretMapper.insert(clientSecret);
    }

    public ApiClientDTO getClientSecretByClientId(String clientId) {
        ClientSecret clientSecret =ClientSecret.builder()
                .clientId(clientId)
                .build();
        List<ClientSecret> results = clientSecretMapper.selectByParams(new HashMap(){{
            put("clientId",clientSecret.getClientId());
        }});
        System.out.println(results.size());
        if (results.size() >= 1) {
            return convert(results.get(0));
        }
        return null;
    }

    public List<ApiClientDTO> getClientSecretByTenantId(UUID tenantId) {
       ClientSecret clientSecret = ClientSecret.builder()
                .tenantId(tenantId)
                .build();
        return Lists.transform(clientSecretMapper.selectByParams(new HashMap(){{
            put("clientId",clientSecret.getClientId());
        }}), this::convert);
    }

    public int updateClientSecret(ApiClientDTO apiClient) {
       ClientSecret clientSecret = ClientSecret.builder()
                .clientSecret(apiClient.getClientSecret())
                .purpose(apiClient.getPurpose())
                .status(ClientSecretStatus.valueOf(apiClient.getStatus()))
                .clientId(apiClient.getClientId())
                .tenantId(apiClient.getTenantId())
                .build();
        return clientSecretMapper.updateByParams(clientSecret);
    }

    public int updateClientSecretByTenantId(UUID tenantId, ClientSecretStatus status) {
        return clientSecretMapper.updateStatusByTenantId(tenantId, status);
    }

    public int updateClientSecretByClientId(String clientId, ApiClientDTO apiClient) {
        ClientSecretStatus status = ClientSecretStatus.valueOf(apiClient.getStatus());
        return clientSecretMapper.updateStatusByClientId(clientId, status);
    }

    private ApiClientDTO convert(com.scaff.oauth2.model.ClientSecret clientSecret) {
        ApiClientDTO apiClient = new ApiClientDTO();
        apiClient.setClientId(clientSecret.getClientId());
        apiClient.setClientSecret(clientSecret.getClientSecret());
/*        apiClient.setStatus(clientSecret.getStatus().toString());
        apiClient.setPurpose(clientSecret.getPurpose());
        apiClient.setUserId(clientSecret.getUserId());*/
        return apiClient;
    }

}
