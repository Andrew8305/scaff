package com.scaff.model.merchants;



import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/26/17.
 */
@Data@Builder@NoArgsConstructor
@AllArgsConstructor
@Entity("merchant_rsa")
public class MerchantRsa {
    @Property("id")
    @Id
    private String uuid;
    @Embedded
    private RsaModel rsaModel;
}
