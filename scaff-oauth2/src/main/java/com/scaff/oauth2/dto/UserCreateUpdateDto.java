package com.scaff.oauth2.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Created by xyl on 11/20/17.
 */
@Data
public class UserCreateUpdateDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password; // in plain text!
    private List<String> resourceIds;
    private Boolean enabled;
    private List<String> roles;
}
