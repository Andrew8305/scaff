package com.scaff.merchants.service;

import com.baomidou.mybatisplus.service.IService;
import com.scaff.model.merchants.Merchants;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商户表 服务类
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
public interface IMerchantsService extends IService<Merchants> {

}
