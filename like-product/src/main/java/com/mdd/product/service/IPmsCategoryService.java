package com.mdd.product.service;

import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.PmsCategory;
import com.mdd.product.validate.PmsCategoryParam;
import com.mdd.product.vo.PmsCategoryListVo;
import com.mdd.product.vo.PmsCategoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类服务接口类
 */
public interface IPmsCategoryService {

    /**
     * 商品三级分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryVo>
     */
    PageResult<PmsCategoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 查询所有
     * @return List<PmsCategory>
     */
    List<PmsCategory> listWithTree();
    /**
     * 商品三级分类详情
     *
     * @param id 主键ID
     * @return PmsCategory
     */
    PmsCategoryDetailVo detail(Long id);

    /**
     * 商品三级分类新增
     *
     * @param pmsCategoryParam 参数
     */
    void add(PmsCategoryParam pmsCategoryParam);

    /**
     * 商品三级分类编辑
     *
     * @param pmsCategoryParam 参数
     */
    void edit(PmsCategoryParam pmsCategoryParam);

    /**
     * 商品三级分类删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
