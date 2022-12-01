package com.mdd.search.util;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.util.ObjectBuilder;
import com.mdd.common.es.SkuEsModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-29 11:55
 **/


@Component
@Slf4j
public class EsClient {
    public static final String SEARCH_SKU_PRICE = "skuPrice"; // skuPrice域字段名
    public static final String SEARCH_HAS_STOCK = "hasStock"; // hosStock域字段名
    public static final String SEARCH_SKU_TITLE = "skuTitle"; // skuTitle域字段名
    public static final String SEARCH_CATEGORY_ID = "categoryId"; // categoryId域字段名
    public static final String ES_CATEGORY_AGG = "categoryAgg"; // category聚合别名
    public static final String ES_CATEGORY_ID_AGG = "categoryIdAgg"; // categoryId聚合别名
    public static final String SEARCH_CATEGORY_NAME = "categoryName"; // categoryName域字段名
    public static final String ES_CATEGORY_NAME_AGG = "categoryNameAgg"; // categoryName聚合别名
    public static final String SEARCH_BRAND_ID = "brandId"; // brandId域数据字段名
    public static final String ES_BRAND_AGG = "brandAgg"; // brand聚合数据别名
    public static final String ES_BRAND_ID_AGG = "brandIdAgg"; // brandId聚合数据别名
    public static final String SEARCH_BRAND_NAME = "brandName"; // brandName域数据字段名
    public static final String ES_BRAND_NAME_AGG = "brandNameAgg"; // brandName聚合数据别名
    public static final String SEARCH_BRAND_IMG = "brandImg"; // brandImg域数据字段名
    public static final String ES_BRAND_IMG_AGG = "brandImgAgg"; // brandImg聚合数据别名
    public static final String SEARCH_ATTRS = "attrs"; // attrs域数据字段名
    public static final String ES_ATTRS_AGG = "attrsAgg"; // attrs聚合数据别名
    public static final String SEARCH_ATTR_ID = "attrId"; // attrId域数据字段名
    public static final String ES_ATTR_ID_AGG = "attrIdAgg"; // attrId聚合数据别名
    public static final String SEARCH_ATTR_NAME = "attrName"; // attrName域数据字段名
    public static final String ES_ATTR_NAME_AGG = "attrNameAgg"; // attrName聚合数据别名
    public static final String SEARCH_ATTR_VALUE = "attrValue"; // attrValue域数据字段名
    public static final String ES_ATTR_VALUE_AGG = "attrValueAgg"; // attrValue聚合数据别名

    public static final String SEARCH_KEYWORD = ".keyword";
    /**
     * 索引名称
     */
    public static final String PRODUCT_INDEX = "likemall_product";

    @Resource
    public ElasticsearchClient client;

    /**
     * 判断索引是否存在
     *
     * @return
     * @throws Exception
     */
    public Boolean isExistsIndex() throws Exception {
        BooleanResponse exists = client.indices().exists(o -> o.index(PRODUCT_INDEX));
        return exists.value();
    }

    /**
     * 创建索引并添加mapping字段映射
     *
     * @throws Exception
     */
    public boolean createIndex() throws Exception {
        log.info("创建ES索引开始");
        // 新的索引有三个字段，每个字段都有自己的property，这里依次创建
        Property keywordProperty = Property.of(o -> o.keyword(kBuilder -> kBuilder));
        //ik_smart,试过拼音分词器，想过不理想
        Property textProperty = Property.of(o -> o.text(tBuilder -> tBuilder.analyzer("ik_max_word").searchAnalyzer("ik_smart")));
        Property integerProperty = Property.of(o -> o.integer(iBuilder -> iBuilder));
        Property longProperty = Property.of(o -> o.long_(lBuilder -> lBuilder));
        Property dateProperty = Property.of(o -> o.date(dBuilder -> dBuilder.format("yyyy-MM-dd HH:mm:ss")));

        //图片 不建索引 不聚合排序
        Property keywordImgProperty = Property.of(o -> o.keyword(kBuilder -> kBuilder.index(false).docValues(false)));
        Property booleanProperty = Property.of(o -> o.boolean_(lBuilder -> lBuilder));

        //属性
        Map<String, Property> attrsDTO = new HashMap<>(16);
        attrsDTO.put(SEARCH_ATTR_ID, longProperty);
        attrsDTO.put(SEARCH_ATTR_NAME, keywordProperty);
        attrsDTO.put(SEARCH_ATTR_VALUE, keywordProperty);
        Property nestedProperty = Property.of(o -> o.nested(lBuilder -> lBuilder.properties(attrsDTO)));

        Map<String, Property> esDTO = new HashMap<>(16);
        esDTO.put("skuId", longProperty);
        esDTO.put("spuId", longProperty);
        esDTO.put(SEARCH_SKU_TITLE, textProperty);
        esDTO.put(SEARCH_SKU_PRICE, keywordProperty);
        esDTO.put("skuImg", keywordProperty);
        esDTO.put("saleCount", longProperty);
        esDTO.put(SEARCH_HAS_STOCK, booleanProperty);
        esDTO.put("hotScore", longProperty);
        esDTO.put(SEARCH_CATEGORY_ID, longProperty);
        esDTO.put(SEARCH_CATEGORY_NAME, keywordProperty);
        esDTO.put(SEARCH_BRAND_ID, longProperty);
        esDTO.put(SEARCH_BRAND_NAME, keywordProperty);
        esDTO.put(SEARCH_BRAND_IMG, keywordProperty);

        esDTO.put("attrs", nestedProperty);

        //设置分片和映射
        CreateIndexResponse createIndexResponse = client.indices()
                .create(c -> c.index(PRODUCT_INDEX)
                        .settings(s -> s.numberOfShards("1").numberOfReplicas("1"))
                        .mappings(m -> m.properties(esDTO)));
        log.info("创建ES索引结束,结果：{}", createIndexResponse.acknowledged());
        return createIndexResponse.acknowledged();
    }


    /**
     * 通过id 查询数据
     *
     * @param id id
     * @return EsDTO
     * @throws IOException
     */
    public SkuEsModel getById(Long id) throws IOException {
        GetResponse<SkuEsModel> response = client.get(g -> g
                        .index(PRODUCT_INDEX)
                        .id(id + ""),
                SkuEsModel.class
        );
        return response.source();
    }

    /**
     * 条件查询，不分页
     *
     * @param query
     * @return
     * @throws IOException
     */
    public List<SkuEsModel> getList(Query query) throws IOException {
        List<SkuEsModel> list = new ArrayList<>();
        SearchResponse<SkuEsModel> response = client.search(s -> s
                        .index(PRODUCT_INDEX)
                        .query(query),
                SkuEsModel.class
        );
        List<Hit<SkuEsModel>> hits = response.hits().hits();
        for (Hit<SkuEsModel> hit : hits) {
            list.add(hit.source());
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param query 查询参数
     * @param size  每页大小
     * @param from  第几页
     * @return
     * @throws IOException
     */
//    public EsResDTO getPage(Query query, int from, int size) {
//        //高亮
//        Highlight of = Highlight.of(h -> {
//                    h.fields("title", hf -> hf.preTags("<em>").postTags("</em>"));
//                    h.fields("content", hf -> hf.preTags("<em>").postTags("</em>"));
//                    return h;
//                }
//        );
//        List<SkuEsModel> list = new ArrayList<>();
//        SearchResponse<SkuEsModel> response = null;
//        Function<SearchRequest.Builder, ObjectBuilder<SearchRequest>> searchRequest = builder -> {
//            builder.index(HOUSE_INDEX)
//                    .query(query)
//                    .size(size)
//                    .from(from)
//                    .sort(so -> so.score(ss -> ss.order(SortOrder.Desc)))
//                    .sort(so -> so.field(v -> v.field("sendTime").order(SortOrder.Desc)))
//                    .highlight(of);
//            return builder;
//        };
//        try {
//            response = client.search(searchRequest, EsDTO.class);
//        } catch (Exception e) {
//            log.error("es 查询出错：{}", e.getMessage());
//            e.printStackTrace();
//        }
//        List<Hit<SkuEsModel>> hits = response.hits().hits();
//        for (Hit<SkuEsModel> hit : hits) {
//            SkuEsModel source = hit.source();
//            Map<String, List<String>> highlight = hit.highlight();
//            source.setHighlight(highlight);
//            list.add(source);
//        }
//        EsResDTO esResDTO = new EsResDTO();
//        esResDTO.setList(list);
//        esResDTO.setTotal(response.hits().total().value());
//        return esResDTO;
//    }


    /**
     * 单个保存(修改id 一样就会修改)
     *
     * @param esDTO
     * @return
     * @throws IOException
     */
    public String save(SkuEsModel esDTO) throws IOException {
        IndexRequest.Builder<SkuEsModel> indexReqBuilder = new IndexRequest.Builder<>();
        indexReqBuilder.index(PRODUCT_INDEX);
        indexReqBuilder.id(esDTO.getSkuId() + "");
        indexReqBuilder.document(esDTO);
        IndexResponse response = client.index(indexReqBuilder.build());
        return response.id();
    }

    /**
     * 批量添加
     *
     * @param list
     * @throws IOException
     */
    public boolean bulkSave(List<SkuEsModel> list) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (SkuEsModel o : list) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index(PRODUCT_INDEX)
                            .id(o.getSkuId() + "")
                            .document(o)
                    )
            );
        }
        return client.bulk(br.build()).errors();
    }

    /***
     * 通过id 删除
     * @param id
     * @throws IOException
     */
    public void removeById(Long id) throws IOException {
        DeleteRequest de = DeleteRequest.of(d -> d.index(PRODUCT_INDEX).id(id + ""));
        client.delete(de);
    }

    /***
     * 通过id 批量删除
     * @param ids
     * @throws IOException
     */
    public void removeById(List<Long> ids) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        // 将每一个product对象都放入builder中
        ids.stream()
                .forEach(id -> br
                        .operations(op -> op
                                .delete(d -> d
                                        .index(PRODUCT_INDEX)
                                        .id(id + ""))));
        client.bulk(br.build());
    }

    /**
     * 根据查询进行删除
     *
     * @param query
     * @throws IOException
     */
    public void delQuery(Query query) throws IOException {
        DeleteByQueryRequest de = DeleteByQueryRequest.of(d -> d.index(PRODUCT_INDEX).query(query));
        client.deleteByQuery(de);
    }
}

