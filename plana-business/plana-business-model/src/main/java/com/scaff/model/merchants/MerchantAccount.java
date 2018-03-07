package com.scaff.model.merchants;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商户账户信息表
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Data@Builder@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantAccount implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商户账户uuid
     */
	private String accountUuid;
    /**
     * 账户余额
     */
	private Long accountBalance;
	private Date createTime;
	private Date updateTime;


}
