package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

/**
 * @Author: CuiChengLong
 * @Date: 2023/6/12 16:40
 * @Description
 */
public class NacosConfigConstant {

    /**
     * Nacos dataId postfix for flow rule.
     */
    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rule";
    /**
     * Nacos dataId postfix for degrade rule.
     */
    public static final String DEGRADE_DATA_ID_POSTFIX = "-degrade-rule";
    /**
     * Nacos Group
     */
    public static final String GROUP_ID = "DEFAULT_GROUP";
    /**
     * Nacos file extension
     */
    public static final String FILE_POSTFIX = ".json";

}
