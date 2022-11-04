package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsAttrGroupParam;
import com.mdd.product.vo.PmsAttrGroupListVo;
import com.mdd.product.vo.PmsAttrGroupDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 属性分组服务接口类
 */
public interface IPmsAttrGroupService {

    /**
     * 属性分组列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrGroupVo>
     */
    PageResult<PmsAttrGroupListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 属性分组详情
     *
     * @param id 主键ID
     * @return PmsAttrGroup
     */
    PmsAttrGroupDetailVo detail(Long id);

    /**
     * 属性分组新增
     *
     * @param pmsAttrGroupParam 参数
     */
    void add(PmsAttrGroupParam pmsAttrGroupParam);

    /**
     * 属性分组编辑
     *
     * @param pmsAttrGroupParam 参数
     */
    void edit(PmsAttrGroupParam pmsAttrGroupParam);

    /**
     * 属性分组删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
