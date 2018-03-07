package com.scaff.api.gateway.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/26/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class RsaModel {
    private String primaryKey;
    private String publicKey;
}
