package com.scaff.api.gateway.filter.planna;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.scaff.api.gateway.model.AuthServicesReqDTO;
import com.scaff.api.gateway.model.MerchantRsa;
import com.scaff.api.gateway.service.IAuthCenterService;
import com.scaff.api.gateway.service.IMerchantsService;
import com.scaff.common.web.result.JSONResult;
import com.scaff.tools.configcenter.ConfigCenter;
import com.scaff.utils.HttpUtils;
import com.scaff.utils.encrypt.AES;
import com.scaff.utils.exception.WebBusinessCodeEnum;
import com.scaff.enums.zuulfilter.ZuulFilterType;
import com.scaff.utils.encrypt.RSA;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * Created by xyl on 12/27/17.
 */
@Slf4j
public class DecryptRequestFilter extends ZuulFilter {

    private final static String MERCHANT_ID = "merchant_id";
    private final static String SERVICE_NAME = "service_name";
    private final static String DATA = "data";
    private final static String KEY = "key";
    private final static String METHOD = "method";

    @Resource
    private IAuthCenterService iAuthCenterService;

    @Resource
    private IMerchantsService iMerchantsService;

    @Resource
    ConfigCenter configCenter;

    @Override
    public String filterType() {
        return ZuulFilterType.PRE.getValue();
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String url = requestContext.getRequest().getRequestURI().substring(1);
        if (url.contains("/")){
            url = url.substring(0,url.indexOf("/"));
        }
        return configCenter.getConfig("authServices").contains(url);
    }

    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        JSONObject requestData = HttpUtils.getModelFromRequest(requestContext.getRequest(),JSONObject.class);
        String merchantId = requestData.getString(MERCHANT_ID);
        String serviceName = requestData.getString(SERVICE_NAME);
        /*step 1: 授权*/
        JSONResult result = iAuthCenterService.authService(
                AuthServicesReqDTO.builder()
                        .merchantUUID(merchantId)
                        .serviceName(serviceName)
                        .build()
        );
        log.info("授权结果 ：{}",result.buildJsonString());
        if (!result.isSuccess()){
            handlerFailureResult(requestContext,result.buildJsonString(),ContentType.APPLICATION_JSON.toString());
            return null;
        }
        if (StringUtils.isEmpty(requestData.getString(DATA)) || StringUtils.isEmpty(requestData.getString(KEY))){
            return null;
        }
        /*step 2:解密*/
        JSONResult rsaResult =  iMerchantsService.getMerchantPrimaryKey(merchantId);
        if (!rsaResult.isSuccess()){
            handlerFailureResult(requestContext,rsaResult.buildJsonString(),ContentType.APPLICATION_JSON.toString());
            return null;
        }
        MerchantRsa merchantRsa = rsaResult.get(MerchantRsa.class);
        String key;
        byte[] reqBodyBytes;
        try {
            key = RSA.decrypt(requestData.getString(KEY),merchantRsa.getRsaModel().getPrimaryKey());
            reqBodyBytes = AES.AES_Decrypt(key,requestData.getString(DATA)).getBytes();
        } catch (Exception e) {
            handlerFailureResult(requestContext,JSONResult.error(WebBusinessCodeEnum.DECRYPT_DATA_FAILED).buildJsonString(),ContentType.APPLICATION_JSON.toString());
            return null;
        }

        requestContext.setRequest(new HttpServletRequestWrapper(requestContext.getRequest()){
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(reqBodyBytes);
            }

            @Override
            public int getContentLength() {
                return reqBodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return reqBodyBytes.length;
            }

            @Override
            public String getMethod() {
                return requestData.getString(METHOD) != null ? requestData.getString(METHOD) : super.getMethod();
            }
        });
        return null;
    }

    private void handlerFailureResult(RequestContext requestContext, String responseBody, String contentType){
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseBody(responseBody);
        requestContext.getResponse().setContentType(contentType);
    }
}
