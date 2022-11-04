package com.mdd.product.service;

import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsProductAttrValueParam;
import com.mdd.product.vo.PmsProductAttrValueListVo;
import com.mdd.product.vo.PmsProductAttrValueDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * spu属性值服务接口类
 */
public interface IPmsProductAttrValueService {

    /**
     * spu属性值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsProductAttrValueVo>
     */
    PageResult<PmsProductAttrValueListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu属性值详情
     *
     * @param id 主键ID
     * @return PmsProductAttrValue
     */
    PmsProductAttrValueDetailVo detail(Long id);

    /**
     * spu属性值新增
     *
     * @param pmsProductAttrValueParam 参数
     */
    void add(PmsProductAttrValueParam pmsProductAttrValueParam);

    /**
     * spu属性值编辑
     *
     * @param pmsProductAttrValueParam 参数
     */
    void edit(PmsProductAttrValueParam pmsProductAttrValueParam);

    /**
     * spu属性值删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
