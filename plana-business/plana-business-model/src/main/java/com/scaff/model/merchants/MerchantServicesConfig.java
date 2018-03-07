package com.scaff.model.merchants;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/26/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
@Entity("merchant_services_config")
public class MerchantServicesConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String uuid;

    private List<String> authServices;
}
