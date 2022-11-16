package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SkuFullReduction;
import com.mdd.coupon.validate.SkuFullReductionParam;
import com.mdd.coupon.vo.SkuFullReductionListVo;
import com.mdd.coupon.vo.SkuFullReductionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品满减信息服务接口类
 */
public interface ISkuFullReductionService extends IService<SkuFullReduction> {

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuFullReductionVo>
     */
    PageResult<SkuFullReductionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品满减信息详情
     *
     * @param id 主键ID
     * @return SkuFullReduction
     */
    SkuFullReductionDetailVo detail(Long id);

    /**
     * 商品满减信息新增
     *
     * @param skuFullReductionParam 参数
     */
    void add(SkuFullReductionParam skuFullReductionParam);

    /**
     * 商品满减信息编辑
     *
     * @param skuFullReductionParam 参数
     */
    void edit(SkuFullReductionParam skuFullReductionParam);

    /**
     * 商品满减信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void saveSkuReduction(SkuReductionTo reductionTo);
}
