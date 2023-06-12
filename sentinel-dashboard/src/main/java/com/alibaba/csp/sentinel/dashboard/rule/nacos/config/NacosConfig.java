package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @Author: CuiChengLong
 * @Date: 2023/6/12 17:38
 * @Description NacosConfig
 */
@Configuration
@Slf4j
public class NacosConfig {

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    /**
     * List<DegradeRuleEntity> -> String
     * @return String
     */
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return source -> JSON.toJSONString(source.stream()
                .map(DegradeRuleEntity::toRule).collect(Collectors.toList()), true);
    }

    /**
     * String -> List<DegradeRuleEntity>
     * @return List<DegradeRuleEntity>
     */
    @Bean
    public Converter<String,List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return source -> JSON.parseArray(source, DegradeRuleEntity.class);
    }

    /**
     * List<GatewayFlowRuleEntity> -> String
     * @return String
     */
    @Bean
    public Converter<List<GatewayFlowRuleEntity>, String> gatewayFlowRuleEntityEncoder() {
        return source -> JSON.toJSONString(source.stream()
                .map(GatewayFlowRuleEntity::toGatewayFlowRule).collect(Collectors.toList()), true);
    }

    /**
     * String -> List<GatewayFlowRuleEntity>
     * @return List<GatewayFlowRuleEntity>
     */
    @Bean
    public Converter<String,List<GatewayFlowRuleEntity>> gatewayFlowRuleEntityDecoder() {
        return source -> JSON.parseArray(source, GatewayFlowRuleEntity.class);
    }

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();

        properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getIp() + ":" + nacosConfigProperties.getPort());
        properties.put(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosConfigProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosConfigProperties.getPassword());
        log.info("SERVER_ADDR:{}, NAMESPACE:{},USERNAME:{},PASSWORD:{}",
                nacosConfigProperties.getIp() + ":" + nacosConfigProperties.getPort(),
                nacosConfigProperties.getNamespace(),
                nacosConfigProperties.getUsername(),
                nacosConfigProperties.getPassword());
        return ConfigFactory.createConfigService(properties);
    }

}
