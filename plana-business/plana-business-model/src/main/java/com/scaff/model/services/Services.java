package com.scaff.model.services;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.Builder;

/**
 * <p>
 * 服务信息表
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(serialize = false)
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 服务uuid
     */
	@TableField("serviceUuid")
	private String serviceUuid;
    /**
     * 服务名称
     */
	@TableField("serviceName")
	private String serviceName;
    /**
     * 服务单价
     */
	@TableField("servicePrice")
	private Long servicePrice;
	@TableField("createTime")
	private Date createTime;
	@TableField("updateTime")
	private Date updateTime;


}
