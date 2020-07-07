package com.ironman.common.configuration;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lihaodong
 * @create 2020/7/7 1:05 下午
 */
@Component
@Slf4j
public class ElacticsearchTemplate {




    @Resource(name = "esHighClient")
    private RestHighLevelClient esHighClient;





    public Boolean createIndex(String indexName){
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        CreateIndexResponse createIndexResponse = null;
        try {
             createIndexResponse  = esHighClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            log.error("创建索引异常 {}", JSON.toJSONString(e.getMessage()));

        }
        return createIndexResponse.isAcknowledged();
    }









}
