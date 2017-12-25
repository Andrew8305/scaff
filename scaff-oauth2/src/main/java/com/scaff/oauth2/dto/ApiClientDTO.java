package com.scaff.oauth2.dto;

import com.scaff.enums.weberrorcode.ErrorCodes;

import org.hibernate.validator.constraints.NotBlank;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by xyl on 12/14/17.
 */
@Data
public class ApiClientDTO {

    @NotBlank(message = ErrorCodes.CLIENT_ID_IS_NULL_STR)
    @NotNull(message = ErrorCodes.CLIENT_ID_IS_NULL_STR)
    private String clientId;

    @NotBlank(message = ErrorCodes.CLIENT_SECRET_IS_NULL_STR)
    @NotNull(message = ErrorCodes.CLIENT_SECRET_IS_NULL_STR)
    private String clientSecret;

    private String status;

    private String purpose;

    @NotNull(message = ErrorCodes.ORGANIZAITON_ID_IS_NULL_STR)
    private UUID tenantId;

    private UUID userId;
}