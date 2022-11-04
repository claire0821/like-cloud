package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsBrandParam;
import com.mdd.product.vo.PmsBrandListVo;
import com.mdd.product.vo.PmsBrandDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 品牌服务接口类
 */
public interface IPmsBrandService {

    /**
     * 品牌列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsBrandVo>
     */
    PageResult<PmsBrandListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 品牌详情
     *
     * @param id 主键ID
     * @return PmsBrand
     */
    PmsBrandDetailVo detail(Long id);

    /**
     * 品牌新增
     *
     * @param pmsBrandParam 参数
     */
    void add(PmsBrandParam pmsBrandParam);

    /**
     * 品牌编辑
     *
     * @param pmsBrandParam 参数
     */
    void edit(PmsBrandParam pmsBrandParam);

    /**
     * 品牌删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
