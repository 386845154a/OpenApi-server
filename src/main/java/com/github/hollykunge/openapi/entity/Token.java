package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/23 11:17
 * @description:
 */
@Data
@Table(name = "API_TOKEN")
public class Token extends BaseEntity {
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "TOKEN")
    private String token;
}