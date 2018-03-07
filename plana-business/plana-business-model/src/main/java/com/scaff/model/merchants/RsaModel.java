package com.scaff.model.merchants;

import org.mongodb.morphia.annotations.Embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/26/17.
 */
@Data@Builder@Embedded@NoArgsConstructor
@AllArgsConstructor
public class RsaModel {
    private String primaryKey;
    private String publicKey;
}
