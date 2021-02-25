package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2020/6/22 13:48
 * @description: 申请表
 */
@Data
@Table(name = "API_APPLY")
public class Apply  extends BaseEntity {
    @Column(name = "APP_ID")
   private String appId;
    @Column(name = "SERVICE_ID")
   private String serviceId;
    @Column(name = "STATUS")
   private String status;
}