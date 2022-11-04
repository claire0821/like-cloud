package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsAttrAttrgroupRelationParam;
import com.mdd.product.vo.PmsAttrAttrgroupRelationListVo;
import com.mdd.product.vo.PmsAttrAttrgroupRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 属性&属性分组关联服务接口类
 */
public interface IPmsAttrAttrgroupRelationService {

    /**
     * 属性&属性分组关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrAttrgroupRelationVo>
     */
    PageResult<PmsAttrAttrgroupRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 属性&属性分组关联详情
     *
     * @param id 主键ID
     * @return PmsAttrAttrgroupRelation
     */
    PmsAttrAttrgroupRelationDetailVo detail(Long id);

    /**
     * 属性&属性分组关联新增
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     */
    void add(PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam);

    /**
     * 属性&属性分组关联编辑
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     */
    void edit(PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam);

    /**
     * 属性&属性分组关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
