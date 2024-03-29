package com.github.hollykunge.openapi.entity;
import com.github.hollykunge.openapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: zhuqz
 * @date: 2020/6/22 13:53
 * @description: 服务表
 */
@Data
@Table(name = "API_SERVICE")
public class Service   extends BaseEntity {
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "REQUEST_TYPE")
    private String requestType;
    @Column(name = "REQUEST_URL")
    private String requestUrl;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "BLACK_IP")
    private String blackIp;
    @Column(name = "WHITE_IP")
    private String whiteIp;
    @Column(name = "MARK")
    private String mark;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "ENCODE")
    private String encode;
    @Column(name = "PARAM")
    private String param;
    @Column(name = "ATTCH_FILE")
    private String attchFile;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Column(name = "NAME")
    private String name;
;
}