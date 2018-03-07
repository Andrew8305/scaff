package com.scaff.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 1/8/18.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class Role {
    private String roleId;
    private String roleType;
    private List<String> holdPermission;
    private int height;
}
