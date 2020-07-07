package com.ironman.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lihaodong
 * @create 2020/7/7 12:54 下午
 */

@ConfigurationProperties(prefix = "elasticsearch")
@Component
@Data
public class ElasticSearchProperty {


    /**
     * ip 地址
     */
    private String host;


    private List<Integer> port;

    /**
     * 协议
     */
    private String property;

}
