package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SkuSaleAttrValue;
import com.mdd.product.validate.SkuSaleAttrValueParam;
import com.mdd.product.vo.SkuSaleAttrValueListVo;
import com.mdd.product.vo.SkuSaleAttrValueDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * sku销售属性&值服务接口类
 */
public interface ISkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    /**
     * sku销售属性&值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuSaleAttrValueVo>
     */
    PageResult<SkuSaleAttrValueListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku销售属性&值详情
     *
     * @param id 主键ID
     * @return SkuSaleAttrValue
     */
    SkuSaleAttrValueDetailVo detail(Long id);

    /**
     * sku销售属性&值新增
     *
     * @param skuSaleAttrValueParam 参数
     */
    void add(SkuSaleAttrValueParam skuSaleAttrValueParam);

    /**
     * sku销售属性&值编辑
     *
     * @param skuSaleAttrValueParam 参数
     */
    void edit(SkuSaleAttrValueParam skuSaleAttrValueParam);

    /**
     * sku销售属性&值删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
