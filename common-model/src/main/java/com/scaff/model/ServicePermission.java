package com.scaff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 1/8/18.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class ServicePermission implements Permission{

    private String serviceName;

    @Override
    public PermissionType getType() {
        return PermissionType.SERVICE;
    }
}
