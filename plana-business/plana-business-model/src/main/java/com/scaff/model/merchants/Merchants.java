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
 * 商户表
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Data@Builder@NoArgsConstructor@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Merchants implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商户唯一uuid
     */
	private String merchantUuid;
    /**
     * 商户账户id
     */
	private String merchantAccountUuid;
    /**
     * 商户名称
     */
	private String merchantName;
    /**
     * 商户公私钥id
     */
	private String merchantKeyId;
    /**
     * 商户服务配置id
     */
	private String merchantServicesKey;
	private Date createTime;
	private Date updateTime;


}
