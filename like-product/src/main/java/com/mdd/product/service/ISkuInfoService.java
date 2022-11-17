package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.validate.SkuInfoParam;
import com.mdd.product.vo.SkuInfoListVo;
import com.mdd.product.vo.SkuInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * sku信息服务接口类
 */
public interface ISkuInfoService extends IService<SkuInfo> {

    /**
     * sku信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuInfoVo>
     */
    PageResult<SkuInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku信息详情
     *
     * @param id 主键ID
     * @return SkuInfo
     */
    SkuInfoDetailVo detail(Long id);

    /**
     * sku信息新增
     *
     * @param skuInfoParam 参数
     */
    void add(SkuInfoParam skuInfoParam);

    /**
     * sku信息编辑
     *
     * @param skuInfoParam 参数
     */
    void edit(SkuInfoParam skuInfoParam);

    /**
     * sku信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    List<SkuInfo> getSkusBySpuId(Long spuId);
}
