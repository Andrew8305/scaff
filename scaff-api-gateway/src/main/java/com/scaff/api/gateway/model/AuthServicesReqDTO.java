package com.scaff.api.gateway.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/27/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class AuthServicesReqDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String serviceName;

    private String merchantUUID;


}
