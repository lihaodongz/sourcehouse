package com.ironman.common.configuration;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;
import sun.awt.image.IntegerComponentRaster;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lihaodong
 * @create 2020/7/7 1:05 下午
 */
@Component
@Slf4j
public class ElacticsearchTemplate {


    @Resource(name = "esHighClient")
    private RestHighLevelClient esHighClient;


    /**
     * 创建索引
     * @param indexName
     * @return
     */
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


    /**
     * 创建索引，
     * @param jsonString
     */
    public String createDocument(String jsonString) throws IOException {
        IndexRequest indexRequest= new IndexRequest("posts");
        indexRequest.id("1");
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = esHighClient.index(indexRequest, RequestOptions.DEFAULT);
        return index.getId();
    }










}
