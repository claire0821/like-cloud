package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSkuSaleAttrValueParam;
import com.mdd.product.vo.PmsSkuSaleAttrValueListVo;
import com.mdd.product.vo.PmsSkuSaleAttrValueDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * sku销售属性&值服务接口类
 */
public interface IPmsSkuSaleAttrValueService {

    /**
     * sku销售属性&值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuSaleAttrValueVo>
     */
    PageResult<PmsSkuSaleAttrValueListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku销售属性&值详情
     *
     * @param id 主键ID
     * @return PmsSkuSaleAttrValue
     */
    PmsSkuSaleAttrValueDetailVo detail(Long id);

    /**
     * sku销售属性&值新增
     *
     * @param pmsSkuSaleAttrValueParam 参数
     */
    void add(PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam);

    /**
     * sku销售属性&值编辑
     *
     * @param pmsSkuSaleAttrValueParam 参数
     */
    void edit(PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam);

    /**
     * sku销售属性&值删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
