package com.github.hollykunge.openapi.entity;

import com.github.hollykunge.openapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2021/3/3 16:07
 * @description:
 */
@Data
@Table(name = "API_ROLE")
public class Role  extends BaseEntity {
    @Column(name = "NAME")
    private String name;
}