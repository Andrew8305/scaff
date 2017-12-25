package com.scaff.oauth2.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * Created by xyl on 12/14/17.
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8388417013613884411L;

    private Timestamp createTime;

    private Timestamp updateTime;

    private int createBy;

    private int updateBy;

}
