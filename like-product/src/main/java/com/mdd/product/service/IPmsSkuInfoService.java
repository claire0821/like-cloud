package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSkuInfoParam;
import com.mdd.product.vo.PmsSkuInfoListVo;
import com.mdd.product.vo.PmsSkuInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * sku信息服务接口类
 */
public interface IPmsSkuInfoService {

    /**
     * sku信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuInfoVo>
     */
    PageResult<PmsSkuInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * sku信息详情
     *
     * @param id 主键ID
     * @return PmsSkuInfo
     */
    PmsSkuInfoDetailVo detail(Long id);

    /**
     * sku信息新增
     *
     * @param pmsSkuInfoParam 参数
     */
    void add(PmsSkuInfoParam pmsSkuInfoParam);

    /**
     * sku信息编辑
     *
     * @param pmsSkuInfoParam 参数
     */
    void edit(PmsSkuInfoParam pmsSkuInfoParam);

    /**
     * sku信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
