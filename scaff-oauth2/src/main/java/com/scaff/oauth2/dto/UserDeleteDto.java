package com.scaff.oauth2.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Created by xyl on 11/20/17.
 */
@Data
public class UserDeleteDto {
    @NotEmpty
    private String username;
}
