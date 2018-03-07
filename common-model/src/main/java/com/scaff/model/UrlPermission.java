package com.scaff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 1/8/18.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class UrlPermission implements Permission{

    private String path;

    private String method;

    private String application;

    @Override
    public PermissionType getType() {
        return PermissionType.URL;
    }
}
