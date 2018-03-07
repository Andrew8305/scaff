package com.scaff.merchants.service.impl;

import com.scaff.merchants.mapper.MerchantsMapper;
import com.scaff.merchants.service.IMerchantsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scaff.model.merchants.Merchants;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 商户表 服务实现类
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Service
public class MerchantsServiceImpl extends ServiceImpl<MerchantsMapper, Merchants> implements IMerchantsService {

}
