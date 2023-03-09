package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.common.vo.ProductDetaliSpuVo;
import com.mdd.product.entity.SpuInfo;
import com.mdd.product.validate.SpuInfoParam;
import com.mdd.product.vo.ProductDetaliVo;
import com.mdd.product.vo.SpuInfoListVo;
import com.mdd.product.vo.SpuInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息服务接口类
 */
public interface ISpuInfoService extends IService<SpuInfo> {

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuInfoVo>
     */
    PageResult<SpuInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu信息详情
     *
     * @param id 主键ID
     * @return SpuInfo
     */
    SpuInfoDetailVo detail(Long id);

    /**
     * spu信息新增
     *
     * @param spuInfoParam 参数
     */
    void add(SpuInfoParam spuInfoParam);

    /**
     * spu信息编辑
     *
     * @param spuInfoParam 参数
     */
    void edit(SpuInfoParam spuInfoParam);

    /**
     * spu信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfo spuInfo);

    void up(Long spuId);

    ProductDetaliVo productDetail(Long spuId, Long skuId);

    ProductDetaliSpuVo getDetialBySkuId(Long skuId);

    PageResult<SpuInfoDetailVo> list(PageParam pageParam, String spuName, Long catalogId);
}
