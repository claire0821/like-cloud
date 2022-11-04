package com.mdd.product.service;

import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsCategoryBrandRelationParam;
import com.mdd.product.vo.PmsCategoryBrandRelationListVo;
import com.mdd.product.vo.PmsCategoryBrandRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 品牌分类关联服务接口类
 */
public interface IPmsCategoryBrandRelationService {

    /**
     * 品牌分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryBrandRelationVo>
     */
    PageResult<PmsCategoryBrandRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 品牌分类关联详情
     *
     * @param id 主键ID
     * @return PmsCategoryBrandRelation
     */
    PmsCategoryBrandRelationDetailVo detail(Long id);

    /**
     * 品牌分类关联新增
     *
     * @param pmsCategoryBrandRelationParam 参数
     */
    void add(PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam);

    /**
     * 品牌分类关联编辑
     *
     * @param pmsCategoryBrandRelationParam 参数
     */
    void edit(PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam);

    /**
     * 品牌分类关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
