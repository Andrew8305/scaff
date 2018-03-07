package com.scaff.services.service.impl;

import com.scaff.model.services.Services;
import com.scaff.services.mapper.ServicesMapper;
import com.scaff.services.service.IServicesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务信息表 服务实现类
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Service
public class ServicesServiceImpl extends ServiceImpl<ServicesMapper, Services> implements IServicesService {

}
