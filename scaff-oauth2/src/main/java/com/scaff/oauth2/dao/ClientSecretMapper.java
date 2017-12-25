package com.scaff.oauth2.dao;

import com.scaff.oauth2.model.ClientSecret;


public interface ClientSecretMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(ClientSecret record);

    int insertSelective(ClientSecret record);

    ClientSecret selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(ClientSecret record);

    int updateByPrimaryKey(ClientSecret record);
}