package com.mdd.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-28 15:41
 **/

@Configuration
public class ElasticSearchConfig {

    //在es中的索引
    public static final String PRODUCT_INDEX = "likemall_product";

    private final Integer PAGE_SIZE = 30;

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public ElasticsearchClient esRestClient(){
        RestClient restClient = RestClient.builder(
                new HttpHost("47.93.35.244", 9200)).build();

    // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

    // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);
        return  client;
    }
}
