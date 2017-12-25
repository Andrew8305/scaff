package com.scaff.oauth2.controller;

import com.scaff.common.web.result.JSONResult;
import com.scaff.oauth2.dto.ApiClientDTO;
import com.scaff.oauth2.service.ClientSecretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


/**
 * Created by xyl on 12/14/17.
 */
@RestController("/")
public class ClientSecretResourceController {
    @Autowired
    private ClientSecretService clientSecretService;

    @PostMapping("/clients")
    public JSONResult createClient(ApiClientDTO apiClientDTO) {
        clientSecretService.createClientSecret(apiClientDTO);
        return JSONResult.ok();
    }

    @GetMapping("/clients/{clientId}")
    public JSONResult getClient(@PathParam("clientId") String clientId) {
        ApiClientDTO apiClientDTO = clientSecretService.getClientSecretByClientId(clientId);
        return JSONResult.ok(apiClientDTO);
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/clients/{clientId}")
    public JSONResult updateClient(@PathParam("clientId") String clientId, ApiClientDTO apiClientDTO) {
        clientSecretService.updateClientSecret(apiClientDTO);
        return Response.ok().build();
    }
}
