package com.ironman.common.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lihaodong
 * @create 2020/7/7 12:56 下午
 */
@Component
public class EsClient {


    @Autowired
    private ElasticSearchProperty elasticSearchProperty;


    @Bean(name = "esHighClient")
    private RestHighLevelClient getClient(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(elasticSearchProperty.getHost(), elasticSearchProperty.getPort().get(0), elasticSearchProperty.getProperty()),
                new HttpHost(elasticSearchProperty.getHost(), elasticSearchProperty.getPort().get(1), elasticSearchProperty.getProperty()));

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }


}
