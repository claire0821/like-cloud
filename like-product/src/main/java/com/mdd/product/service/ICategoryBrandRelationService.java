package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.CategoryBrandRelation;
import com.mdd.product.validate.CategoryBrandRelationParam;
import com.mdd.product.vo.CategoryBrandRelationListVo;
import com.mdd.product.vo.PmsCategoryBrandRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 品牌分类关联服务接口类
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelation> {

    /**
     * 品牌分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryBrandRelationVo>
     */
    PageResult<CategoryBrandRelationListVo> list(PageParam pageParam, Map<String, String> params);

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
     * @param categoryBrandRelationParam 参数
     */
    void add(CategoryBrandRelationParam categoryBrandRelationParam);

    /**
     * 品牌分类关联编辑
     *
     * @param categoryBrandRelationParam 参数
     */
    void edit(CategoryBrandRelationParam categoryBrandRelationParam);

    /**
     * 品牌分类关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void saveDetail(CategoryBrandRelation categoryBrandRelation);

    void updateCategory(Long catId, String name);
}
