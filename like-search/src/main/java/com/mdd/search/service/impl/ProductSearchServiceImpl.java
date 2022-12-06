package com.mdd.search.service.impl;

import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.util.ObjectBuilder;
import co.elastic.clients.elasticsearch._types.aggregations.NestedAggregate;
import com.mdd.common.es.SkuEsModel;
import com.mdd.common.utils.StringUtil;
import com.mdd.search.service.IProductSearchService;
import com.mdd.search.util.EsClient;
import com.mdd.search.vo.ProductRelatedInfo;
import com.mdd.search.vo.ProductResultVo;
import com.mdd.search.vo.ProductSearchVo;
import com.mdd.search.vo.ScreenOptions;
import jakarta.json.stream.JsonGenerator;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdd.search.util.EsClient.PRODUCT_INDEX;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:41
 **/
@Service
public class ProductSearchServiceImpl implements IProductSearchService {

    @Autowired
    EsClient esClient;

    @Override
    public ProductResultVo getProductRelated(ProductSearchVo productSearchVo) throws IOException {

        //构建查询语句
        SearchRequest.Builder builder = buildSearchRequest(productSearchVo);

        final SearchRequest build = builder.build();

        SearchResponse<SkuEsModel> response = null;
        try {
            response = esClient.client.search(build, SkuEsModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final ProductResultVo productResultVo = buildSearchResult(response,productSearchVo);

        System.out.println("test: " + printEsBySearchRequest(build));

        //TODO 分页
        return productResultVo;
    }

    //构建检索请求
    private SearchRequest.Builder buildSearchRequest(ProductSearchVo productSearchVo) {
        //构建查询语句
        SearchRequest.Builder builder = new SearchRequest.Builder();
        //指定查询索引库
        builder.index(PRODUCT_INDEX);

        //region 1 query-bool 查询
        BoolQuery boolQuery = BoolQuery.of(b -> {
            //1.1 bool-must skuTitle模糊匹配
            if (StringUtils.hasLength(productSearchVo.getKeyword())) {
                b.must(must -> must
                        .match(m -> m
                                .field(EsClient.SEARCH_SKU_TITLE)
                                .query(FieldValue.of(productSearchVo.getKeyword()))
                        )
                );
            }

            //1.1.2 bool-filter-term categoryId分类查询
            if (productSearchVo.getCategory() != null && productSearchVo.getCategory() > 0) {
                b.filter(f -> f
                        .term(t -> t
                                .field(EsClient.SEARCH_CATEGORY_ID)
                                .value(FieldValue.of(productSearchVo.getCategory()))
                        )
                );
            }
            //1.1.3 bool-filter-terms brandId品牌查询
            if (productSearchVo.getBrands() != null && productSearchVo.getBrands().size() > 0) {
                List<FieldValue> fieldValues = productSearchVo.getBrands().stream().map(FieldValue::of).collect(Collectors.toList());
                TermsQueryField termsQueryField = TermsQueryField.of(t -> t.value(fieldValues));
                TermsQuery termsQuery = TermsQuery.of(t -> t.field(EsClient.SEARCH_BRAND_ID).terms(termsQueryField));
                b.filter(f -> f.terms(termsQuery));
            }

            //1.1.4 bool-filter-nested attrs属性查询
            if(productSearchVo.getAttrs() != null && productSearchVo.getAttrs().size() > 0) {
                for(ScreenOptions attr : productSearchVo.getAttrs()) {
                    final List<ScreenOptions.Value> values = attr.getValues();
                    final List<String> strings = values.stream().map(v -> v.getValue()).collect(Collectors.toList());
                    if(strings != null && strings.size() > 0) {
//                        TermQuery termQuery = TermQuery.of(t -> t
//                                .field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_ID)
//                                .value(FieldValue.of(attr.getId()))
//                        );
//
//                        List<FieldValue> fieldValues = strings.stream().map(FieldValue::of).collect(Collectors.toList());
//                        TermsQueryField termsQueryField = TermsQueryField.of(t -> t.value(fieldValues));
//                        TermsQuery termsQuery = TermsQuery.of(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_VALUE).terms(termsQueryField));
//
//                        BoolQuery nestedBoolQuery = BoolQuery.of(t -> t
//                        .must(m -> m.term(termQuery)).must(must -> must.terms(termsQuery)));
//                        NestedQuery nestedQuery = NestedQuery.of(n ->
//                                n.path(EsClient.SEARCH_ATTRS)
//                                .scoreMode(ChildScoreMode.None)//不参与评分
//                                .query(nestedBoolQuery._toQuery())
//                        );

                        Query attrIdQuery = TermQuery.of(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_ID).value(FieldValue.of(attr.getId())))._toQuery();
                        FieldValue[] attrFieldValues = new FieldValue[attr.getValues().size()];
//                        final List<String> strings = values.stream().map(v -> v.getValue()).collect(Collectors.toList());
                        for (int i = 0; i < strings.size(); i++) {
                            attrFieldValues[i] = FieldValue.of(strings.get(i));
                        }

                        TermsQueryField termsQueryField = new TermsQueryField.Builder()
                                .value(Arrays.asList(attrFieldValues))
                                .build();
                        Query attrValueQuery = TermsQuery.of(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_VALUE).terms(termsQueryField))._toQuery();
                        Query nestAttrQuery = NestedQuery.of(t -> t.path(EsClient.SEARCH_ATTRS)
                                .query(q -> q.bool(bool -> bool
                                        .must(attrIdQuery)
                                        .must(attrValueQuery)
                                )))._toQuery();

                        b.filter(nestAttrQuery);
                    }
                }
            }
            //1.1.5 bool-filter-term hasStock库存查询
            if (productSearchVo.getHasStock() != null) {
                b.filter(f -> f
                        .term(t -> t
                                .field(EsClient.SEARCH_HAS_STOCK)
                                .value(FieldValue.of(productSearchVo.getHasStock() == 1 ? true : false))
                        )
                );
            }
            //1.1.6 bool-filter-range skuPrice价格区间查询
            //只有最低价
            if(productSearchVo.getMinPrice() != null && productSearchVo.getMaxPrice() == null) {
                b.filter(f -> f
                        .range(t -> t
                                .field(EsClient.SEARCH_SKU_PRICE)
                                .gte(JsonData.of(productSearchVo.getMinPrice()))
                        )
                );
            } else if(productSearchVo.getMinPrice() == null && productSearchVo.getMaxPrice() != null){ //只有最高价
                b.filter(f -> f
                        .range(t -> t
                                .field(EsClient.SEARCH_SKU_PRICE)
                                .lte(JsonData.of(productSearchVo.getMaxPrice()))
                        )
                );

            } else if(productSearchVo.getMinPrice() != null && productSearchVo.getMaxPrice() != null) {
                b.filter(f -> f
                        .range(t -> t
                                .field(EsClient.SEARCH_SKU_PRICE)
                                .gte(JsonData.of(productSearchVo.getMinPrice()))
                                .lte(JsonData.of(productSearchVo.getMaxPrice()))
                        )
                );
            }

            return b;
        });
        builder.query(boolQuery._toQuery());
        //endregion

        //region 2 sort 排序 分页 高亮
        //2.1 sort排序
        if(!StringUtils.isEmpty(productSearchVo.getSort())) {
            builder.sort(s -> s.field(f -> f
                            .field(productSearchVo.getSort())
                            .order(productSearchVo.getOrder().equals("desc") ? SortOrder.Desc : SortOrder.Asc)));
        }
        //2.2 from size分页
        builder.from((productSearchVo.getPageNumber() - 1) * productSearchVo.getPageSize());
        builder.size(productSearchVo.getPageSize());
        //2.3 highlight高亮
        if(!StringUtil.isEmpty(productSearchVo.getKeyword())) {
            String pre_tag = "<font color='red'>";
            String post_tag = "</font>";
            Highlight highlight = new Highlight.Builder().fields(EsClient.SEARCH_SKU_TITLE, new HighlightField.Builder().preTags(pre_tag).postTags(post_tag).build()).build();
            builder.highlight(highlight);
        }
        //endregion

        //region 3 aggs聚合数据
        // 分类聚合
        Aggregation categoryNameAgg = Aggregation.of(a ->
                a.terms(t ->t
                        .field(EsClient.SEARCH_CATEGORY_NAME)
                        .size(1)));

        Aggregation categoryAgg = Aggregation.of(a ->
                a.terms(t ->t
                        .field(EsClient.SEARCH_CATEGORY_ID)
                        .size(Integer.MAX_VALUE))
                        .aggregations(EsClient.ES_CATEGORY_NAME_AGG,categoryNameAgg));
        // 品牌聚合
        Aggregation brandNameAgg = Aggregation.of(a ->
                a.terms(t ->t
                        .field(EsClient.SEARCH_BRAND_NAME)
                        .size(1)));

        Aggregation brandImgAgg = Aggregation.of(a ->
                a.terms(t ->t
                        .field(EsClient.SEARCH_BRAND_IMG)
                        .size(1)));

        Aggregation brandAgg = Aggregation.of(a ->
                a.terms(t ->t
                        .field(EsClient.SEARCH_BRAND_ID)
                        .size(Integer.MAX_VALUE))
                        .aggregations(EsClient.ES_BRAND_NAME_AGG,brandNameAgg)
                        .aggregations(EsClient.ES_BRAND_IMG_AGG,brandImgAgg));
        // 属性聚合
//        NestedAggregation.Builder attrAgg = AggregationBuilders.nested().name(EsClient.ES_ATTRS_AGG).path(EsClient.SEARCH_ATTRS);

//        Aggregation attrNameAgg = Aggregation.of(a ->
//                a.terms(t ->t
//                        .field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_NAME)
//                        .size(1)));
//        Aggregation attrValueAgg = Aggregation.of(a ->
//                a.terms(t ->t
//                        .field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_VALUE)
//                        .size(1)));
//
//
//        Aggregation attrIdAgg = Aggregation.of(a ->
//                a.terms(t ->t
//                        .field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_ID)
//                        .size(Integer.MAX_VALUE))
//                        .aggregations(EsClient.ES_ATTR_NAME_AGG,attrNameAgg)
//                        .aggregations(EsClient.ES_ATTR_VALUE_AGG,attrValueAgg));
//
//        NestedAggregation attrAgg = NestedAggregation.of(n -> n.path(EsClient.ES_ATTRS_AGG));


        builder.aggregations(EsClient.ES_ATTRS_AGG, a ->
                        a.nested(n -> n.path(EsClient.SEARCH_ATTRS))
                                .aggregations(EsClient.ES_ATTR_ID_AGG, attrId -> attrId
                                        .terms(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_ID))
                                        .aggregations(EsClient.ES_ATTR_NAME_AGG, attrValue -> attrValue.terms(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_NAME).size(1)))
                                        .aggregations(EsClient.ES_ATTR_VALUE_AGG, attrValue -> attrValue.terms(t -> t.field(EsClient.SEARCH_ATTRS + "." + EsClient.SEARCH_ATTR_VALUE).size(50)))));

        builder.aggregations(EsClient.ES_CATEGORY_AGG,categoryAgg);
        builder.aggregations(EsClient.ES_BRAND_AGG,brandAgg);
//        builder.aggregations(EsClient.ES_ATTRS_AGG, attrAgg._toAggregation());
        //endregion

        return builder;
    }

    private ProductResultVo buildSearchResult(SearchResponse response,ProductSearchVo productSearchVo) {
        ProductResultVo productResultVo = new ProductResultVo();

        //region1 返回所有查询到的商品
        final HitsMetadata hits = response.hits();
        final List<Hit<SkuEsModel>> list = hits.hits();
        if(list != null && list.size() > 0) {
            List<SkuEsModel> skuEsModelList = new ArrayList<>();
            for (Hit<SkuEsModel> o : list) {
                final SkuEsModel source = o.source();

                //判断是否按关键字检索，若是就显示高亮，否则不显示
                if(!StringUtil.isEmpty(productSearchVo.getKeyword())) {
                    //拿到高亮信息显示标题
                    final Map<String, List<String>> highlight = o.highlight();
                    final String s = highlight.get(EsClient.SEARCH_SKU_TITLE).get(0);
                    source.setSkuTitle(s);
                }
                skuEsModelList.add(source);
            }
            productResultVo.setProducts(skuEsModelList);
        }
        //endregion

        Map<String, Aggregate> aggregations = response.aggregations();
        //region2 当前商品涉及到的所有属性信息
        final NestedAggregate attrsAgg = aggregations.get(EsClient.ES_ATTRS_AGG).nested();
        List<LongTermsBucket> attrIdAgg = attrsAgg.aggregations().get(EsClient.ES_ATTR_ID_AGG).lterms().buckets().array();
        List<ScreenOptions> screenOptionsList = new ArrayList<>();
        for (LongTermsBucket attr_id_agg : attrIdAgg) {
            ScreenOptions attrVo = new ScreenOptions();
            attrVo.setId(Long.valueOf(attr_id_agg.key()));
            List<StringTermsBucket> attrNameAgg = attr_id_agg.aggregations().get(EsClient.ES_ATTR_NAME_AGG).sterms().buckets().array();
            attrVo.setName(attrNameAgg.get(0).key());
            List<StringTermsBucket> attrValueAgg = attr_id_agg.aggregations().get(EsClient.ES_ATTR_VALUE_AGG).sterms().buckets().array();
            List<ScreenOptions.Value> attrValues = new ArrayList<>();
            for (StringTermsBucket attr_value_agg : attrValueAgg) {
                ScreenOptions.Value value = new ScreenOptions.Value();
                value.setSelected(false);
                value.setValue(attr_value_agg.key());
                attrValues.add(value);
            }
            attrVo.setValues(attrValues);
            screenOptionsList.add(attrVo);
        }
        productResultVo.setAttrs(screenOptionsList);
        //endregion

        //region3 当前商品涉及到的所有品牌信息
        List<LongTermsBucket> brandAgg = aggregations.get(EsClient.ES_BRAND_AGG).lterms().buckets().array();
        List<ScreenOptions> brandVos = new ArrayList<>();
        for (LongTermsBucket brand_agg : brandAgg){
            ScreenOptions brandVo = new ScreenOptions();
            brandVo.setId(Long.valueOf(brand_agg.key()));
            List<StringTermsBucket> brandNameAgg = brand_agg.aggregations().get(EsClient.ES_BRAND_NAME_AGG).sterms().buckets().array();
            brandVo.setName(brandNameAgg.get(0).key());
            List<StringTermsBucket> brandImgAgg = brand_agg.aggregations().get(EsClient.ES_BRAND_IMG_AGG).sterms().buckets().array();
            brandVo.setImg(brandImgAgg.get(0).key());
            brandVos.add(brandVo);
        }
        productResultVo.setBrands(brandVos);
        //endregion
        //region4 当前商品涉及到的所有分类信息
        List<LongTermsBucket> categoryAgg = aggregations.get(EsClient.ES_CATEGORY_AGG).lterms().buckets().array();
        List<ScreenOptions> catalogVos = new ArrayList<>();
        for (LongTermsBucket catalog_agg : categoryAgg){
            ScreenOptions catalogVo = new ScreenOptions();
            catalogVo.setId(Long.valueOf(catalog_agg.key()));
            List<StringTermsBucket> categoryNameAgg = catalog_agg.aggregations().get(EsClient.ES_CATEGORY_NAME_AGG).sterms().buckets().array();
            catalogVo.setName(categoryNameAgg.get(0).key());
            catalogVos.add(catalogVo);
        }
        productResultVo.setCategories(catalogVos);
        //endregion

        //region5 分页信息
        //5.1 页码
        //5.2 总记录数
        Long total = hits.total().value();
        productResultVo.setTotal(total);
        //5.2 总页码
        final Integer totalPages = Math.toIntExact(total % productSearchVo.getPageSize() == 0 ?
                total / productSearchVo.getPageSize() : total / productSearchVo.getPageSize() + 1);
        productResultVo.setTotalPages(totalPages);
        //endregion

        return productResultVo;
    }
    /**
     * 打印ES8执行语句（序列化）。
     * @param searchRequest
     * @return
     */
    public String printEsBySearchRequest(SearchRequest searchRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JsonpMapper mapper=new JacksonJsonpMapper();
        JsonGenerator generator =mapper.jsonProvider().createGenerator(byteArrayOutputStream);
        mapper.serialize(searchRequest, generator);
        generator.close();
        return byteArrayOutputStream.toString();
    }


}
