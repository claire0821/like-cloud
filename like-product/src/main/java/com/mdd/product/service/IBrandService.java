package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.Brand;
import com.mdd.product.validate.BrandParam;
import com.mdd.product.vo.BrandListVo;
import com.mdd.product.vo.BrandDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 品牌服务接口类
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 品牌列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsBrandVo>
     */
    PageResult<BrandListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 品牌详情
     *
     * @param id 主键ID
     * @return PmsBrand
     */
    BrandDetailVo detail(Long id);

    /**
     * 品牌新增
     *
     * @param brandParam 参数
     */
    void add(BrandParam brandParam);

    /**
     * 品牌编辑
     *
     * @param brandParam 参数
     */
    void edit(BrandParam brandParam);

    /**
     * 品牌删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
