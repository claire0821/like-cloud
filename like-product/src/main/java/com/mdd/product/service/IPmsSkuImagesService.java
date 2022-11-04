package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSkuImagesParam;
import com.mdd.product.vo.PmsSkuImagesListVo;
import com.mdd.product.vo.PmsSkuImagesDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * sku图片服务接口类
 */
public interface IPmsSkuImagesService {

    /**
     * sku图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuImagesVo>
     */
    PageResult<PmsSkuImagesListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku图片详情
     *
     * @param id 主键ID
     * @return PmsSkuImages
     */
    PmsSkuImagesDetailVo detail(Long id);

    /**
     * sku图片新增
     *
     * @param pmsSkuImagesParam 参数
     */
    void add(PmsSkuImagesParam pmsSkuImagesParam);

    /**
     * sku图片编辑
     *
     * @param pmsSkuImagesParam 参数
     */
    void edit(PmsSkuImagesParam pmsSkuImagesParam);

    /**
     * sku图片删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
