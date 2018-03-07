package com.scaff.model.merchants.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 12/29/17.
 */
@Data@Builder@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceReqDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotNull
    private String merchantUUID;

    @NotNull
    private long amount;
}
