package com.scaff.model.merchants.dto;

import com.scaff.model.merchants.MerchantAccount;
import com.scaff.model.merchants.MerchantServicesConfig;
import com.scaff.model.merchants.Merchants;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/27/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class GetMerchantResDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Merchants merchants;

    private MerchantServicesConfig merchantServicesConfig;

    private MerchantAccount merchantAccount;
}
