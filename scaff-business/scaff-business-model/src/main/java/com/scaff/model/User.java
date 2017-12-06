package com.scaff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 11/10/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class User {
    private String username;
    private String age;
    private String money;
}
