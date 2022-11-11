package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.Category;
import com.mdd.product.validate.CategoryParam;
import com.mdd.product.vo.CategoryListVo;
import com.mdd.product.vo.PmsCategoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类服务接口类
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 商品三级分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryVo>
     */
    PageResult<CategoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 查询所有
     * @return List<PmsCategory>
     */
    List<Category> listWithTree();
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
     * @param categoryParam 参数
     */
    void add(CategoryParam categoryParam);

    /**
     * 商品三级分类编辑
     *
     * @param categoryParam 参数
     */
    void edit(CategoryParam categoryParam);

    /**
     * 商品三级分类删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void removeMenuByIds(List<Long> asList);

    /**
     * 找到catelogId的完整路径；
     * [父/子/孙]
     * @param catelogId
     * @return
     */
     Long[] findCatelogPath(Long catelogId);

    void updateCascade(Category category);
}
