package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.config.NacosConfigConstant;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.config.NacosConfigProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: CuiChengLong
 * @Date: 2023/6/12 16:51
 * @Description 降级规则推送Nacos类
 */
@Component("degradeRuleNacosPublisher")
@Slf4j
public class DegradeRuleNacosPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Resource
    private ConfigService configService;

    @Resource
    private Converter<List<DegradeRuleEntity>, String> converter;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigConstant.DEGRADE_DATA_ID_POSTFIX + NacosConfigConstant.FILE_POSTFIX,
                nacosConfigProperties.getGroupId(), converter.convert(rules), String.valueOf(ConfigType.JSON));
        log.info("推送降级规则信息至Nacos, AppName:{}, Rules:{}", app, rules);
    }
}
