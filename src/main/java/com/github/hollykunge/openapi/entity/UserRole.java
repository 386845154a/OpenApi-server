package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/3 16:09
 * @description:
 */
@Data
@Table(name = "API_USER_ROLE")
public class UserRole extends BaseEntity {
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "ROLE_ID")
    private String roleId;
}