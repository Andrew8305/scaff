package com.scaff.merchants.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.common.web.result.JSONResult;
import com.scaff.merchants.service.IMerchantAccountService;
import com.scaff.merchants.service.IMerchantsService;
import com.scaff.merchants.service.IServiceFeignService;
import com.scaff.model.merchants.MerchantAccount;
import com.scaff.model.merchants.MerchantRsa;
import com.scaff.model.merchants.MerchantServicesConfig;
import com.scaff.model.merchants.Merchants;
import com.scaff.model.merchants.RsaModel;
import com.scaff.model.merchants.dto.GetMerchantResDTO;
import com.scaff.model.merchants.dto.UpdateBalanceReqDTO;
import com.scaff.model.services.Services;
import com.scaff.utils.json.FastJsonUtil;
import com.scaff.utils.RandomUtil;
import com.scaff.utils.exception.BaseException;
import com.scaff.utils.exception.WebBusinessCodeEnum;
import com.scaff.utils.encrypt.RSA;
import com.scaff.utils.encrypt.RsaKeys;
import org.mongodb.morphia.Datastore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

import javax.annotation.Resource;


/**
 * <p>
 * 商户表 前端控制器
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    @Resource
    private IMerchantsService iMerchantsService;

    @Resource
    private IMerchantAccountService iMerchantAccountService;

    @Resource
    private IServiceFeignService iServiceFeignService;

    @Resource
    private Datastore datastore;

    @PostMapping("/add")
    public Merchants addMerchants(){
        /*step 1:生成公私钥对*/
        RsaKeys rsaKeys = RSA.generateKeyPair();
        MerchantRsa merchantRsa = MerchantRsa.builder()
                .uuid(RandomUtil.generateUUIDWithTime())
                .rsaModel(RsaModel.builder()
                        .primaryKey(rsaKeys != null ? rsaKeys.getPrimaryKey() : null)
                        .publicKey(rsaKeys != null ? rsaKeys.getPublicKey() : null)
                        .build())
                .build();
        /*step 2:生成账户信息*/
        MerchantAccount merchantAccount = MerchantAccount.builder()
                .accountUuid(RandomUtil.generateUUIDWithTime())
                .accountBalance(0L)
                .build();
        /*step 3:生成服务信息*/
        //校验服务中心是否有此服务
        Services services = FastJsonUtil.parseObject(iServiceFeignService.getServicesByName("service-reg").getData(),Services.class);
        if (services == null){
            throw new BaseException(WebBusinessCodeEnum.SERVICE_NOT_EXIST);
        }
        MerchantServicesConfig merchantServicesConfig = MerchantServicesConfig.builder()
                .uuid(RandomUtil.generateUUIDWithTime())
                .authServices(Collections.singletonList("service-reg"))
                .build();
        /*step 4:生成商户信息*/
        Merchants merchants = Merchants.builder()
                .merchantUuid(RandomUtil.generateUUIDWithTime())
                .merchantName("吗冬梅")
                .merchantKeyId(merchantRsa.getUuid())
                .merchantAccountUuid(merchantAccount.getAccountUuid())
                .merchantServicesKey(merchantServicesConfig.getUuid())
                .build();

        /*step 5:数据存储*/
        datastore.save(merchantRsa);
        datastore.save(merchantServicesConfig);
        iMerchantAccountService.insert(merchantAccount);
        iMerchantsService.insert(merchants);
        return merchants;
    }

    @GetMapping("/services/{merchantUUID}")
    public JSONResult getMerchantServices(@PathVariable("merchantUUID") String merchantUUID){

        Merchants merchants = iMerchantsService.selectOne(Condition.create().eq("merchantUuid",merchantUUID));
        if (merchants == null){
            return JSONResult.error(WebBusinessCodeEnum.MERCHANTS_NOT_EXIST);
        }

        MerchantServicesConfig merchantServicesConfig = datastore.createQuery(MerchantServicesConfig.class)
                .field("uuid").equal(merchants.getMerchantServicesKey())
                .get();
        return JSONResult.ok(merchantServicesConfig.getAuthServices());
    }

    @GetMapping("/{merchantUUID}")
    public JSONResult getMerchant(@PathVariable("merchantUUID") String merchantUUID){

        Merchants merchants = iMerchantsService.selectOne(Condition.create().eq("merchantUuid",merchantUUID));

        MerchantServicesConfig merchantServicesConfig = datastore.createQuery(MerchantServicesConfig.class)
                .field("uuid").equal(merchants.getMerchantServicesKey())
                .get();

        MerchantAccount merchantAccount = iMerchantAccountService.selectOne(
                Condition.create().eq("accountUuid",merchants.getMerchantAccountUuid())
        );
        GetMerchantResDTO getMerchantResDTO = GetMerchantResDTO.builder()
                .merchants(merchants)
                .merchantServicesConfig(merchantServicesConfig)
                .merchantAccount(merchantAccount)
                .build();
        return JSONResult.ok(getMerchantResDTO);
    }

    @GetMapping("/primaryKey/{merchantUUID}")
    public JSONResult getMerchantPrimaryKey(@PathVariable("merchantUUID") String merchantUUID){
        Merchants merchants = iMerchantsService.selectOne(Condition.create().eq("merchantUuid",merchantUUID));

        return JSONResult.ok(
                datastore.createQuery(MerchantRsa.class)
                        .field("uuid")
                        .equal(merchants.getMerchantKeyId())
                        .get()
        );
    }

    @PostMapping("/updateBalance")
    public JSONResult updateBalance(@FastJson UpdateBalanceReqDTO dto){

        Merchants merchants = iMerchantsService.selectOne(Condition.create().eq("merchantUuid",dto.getMerchantUUID()));
        MerchantAccount merchantAccount = iMerchantAccountService.selectOne(
                Condition.create().eq("accountUuid",merchants.getMerchantAccountUuid())
        );
        merchantAccount.setAccountBalance(merchantAccount.getAccountBalance() + dto.getAmount());

        iMerchantAccountService.updateById(merchantAccount);

        return JSONResult.ok();
    }

}

