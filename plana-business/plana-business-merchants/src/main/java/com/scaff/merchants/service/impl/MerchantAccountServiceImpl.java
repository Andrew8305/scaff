package com.scaff.merchants.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scaff.merchants.mapper.MerchantAccountMapper;
import com.scaff.merchants.service.IMerchantAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scaff.model.merchants.MerchantAccount;

import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 商户账户信息表 服务实现类
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@Service
public class MerchantAccountServiceImpl extends ServiceImpl<MerchantAccountMapper, MerchantAccount> implements IMerchantAccountService {

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    @Override
    public boolean updateById(MerchantAccount entity) {
        readWriteLock.writeLock().lock();
        try {
            return super.updateById(entity);
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public MerchantAccount selectOne(Wrapper<MerchantAccount> wrapper) {
        readWriteLock.readLock().lock();
        try {
            return super.selectOne(wrapper);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
