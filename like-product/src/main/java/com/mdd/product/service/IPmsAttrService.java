package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsAttrParam;
import com.mdd.product.vo.PmsAttrListVo;
import com.mdd.product.vo.PmsAttrDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品属性服务接口类
 */
public interface IPmsAttrService {

    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrVo>
     */
    PageResult<PmsAttrListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品属性详情
     *
     * @param id 主键ID
     * @return PmsAttr
     */
    PmsAttrDetailVo detail(Long id);

    /**
     * 商品属性新增
     *
     * @param pmsAttrParam 参数
     */
    void add(PmsAttrParam pmsAttrParam);

    /**
     * 商品属性编辑
     *
     * @param pmsAttrParam 参数
     */
    void edit(PmsAttrParam pmsAttrParam);

    /**
     * 商品属性删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
