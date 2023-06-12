package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: CuiChengLong
 * @Date: 2023/6/12 16:30
 * @Description nacos配置类
 */
@Configuration
@ConfigurationProperties(prefix = "nacos.server")
@Data
public class NacosConfigProperties {

    private String ip;

    private String port;

    private String namespace;

    private String groupId;

    private String username;

    private String password;

}
