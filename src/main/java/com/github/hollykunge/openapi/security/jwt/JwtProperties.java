package com.github.hollykunge.openapi.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="jwt")
@Data
public class JwtProperties {
    /*private String secret;
    private Long expiration;
    private String tokenHead;
    private String headerName ="Authorization";*/
    private String secret="this is a secret";
    private String tokenHead = "Bearer ";
    private String headerName ="Authorization";
    private Integer accessExpiration = 60 * 60;
    private Integer rolesExpiration =60*5;
    private Integer refreshExpiration =60 * 60 * 24 * 7;
}
