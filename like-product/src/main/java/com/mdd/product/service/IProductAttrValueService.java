package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.ProductAttrValue;
import com.mdd.product.validate.ProductAttrValueParam;
import com.mdd.product.vo.ProductAttrValueListVo;
import com.mdd.product.vo.ProductAttrValueDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * spu属性值服务接口类
 */
public interface IProductAttrValueService extends IService<ProductAttrValue> {

    /**
     * spu属性值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ProductAttrValueVo>
     */
    PageResult<ProductAttrValueListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu属性值详情
     *
     * @param id 主键ID
     * @return ProductAttrValue
     */
    ProductAttrValueDetailVo detail(Long id);

    /**
     * spu属性值新增
     *
     * @param productAttrValueParam 参数
     */
    void add(ProductAttrValueParam productAttrValueParam);

    /**
     * spu属性值编辑
     *
     * @param productAttrValueParam 参数
     */
    void edit(ProductAttrValueParam productAttrValueParam);

    /**
     * spu属性值删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void saveProductAttr(List<ProductAttrValue> collect);

    List<ProductAttrValue> attrlistforspu(Long spuId);

    void updateSpuAttr(List<ProductAttrValue> entities);

    List<ProductAttrValue> baseAttrListforspu(Long spuId);
}
