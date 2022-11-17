package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SkuImages;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.validate.SkuImagesParam;
import com.mdd.product.vo.SkuImagesListVo;
import com.mdd.product.vo.SkuImagesDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * sku图片服务接口类
 */
public interface ISkuImagesService extends IService<SkuImages> {

    /**
     * sku图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuImagesVo>
     */
    PageResult<SkuImagesListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku图片详情
     *
     * @param id 主键ID
     * @return SkuImages
     */
    SkuImagesDetailVo detail(Long id);

    /**
     * sku图片新增
     *
     * @param skuImagesParam 参数
     */
    void add(SkuImagesParam skuImagesParam);

    /**
     * sku图片编辑
     *
     * @param skuImagesParam 参数
     */
    void edit(SkuImagesParam skuImagesParam);

    /**
     * sku图片删除
     *
     * @param id 主键ID
     */
    void del(Long id);
}
