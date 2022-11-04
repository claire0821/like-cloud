package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsSkuFullReductionParam;
import com.mdd.coupon.vo.SmsSkuFullReductionListVo;
import com.mdd.coupon.vo.SmsSkuFullReductionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品满减信息服务接口类
 */
public interface ISmsSkuFullReductionService {

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSkuFullReductionVo>
     */
    PageResult<SmsSkuFullReductionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品满减信息详情
     *
     * @param id 主键ID
     * @return SmsSkuFullReduction
     */
    SmsSkuFullReductionDetailVo detail(Long id);

    /**
     * 商品满减信息新增
     *
     * @param smsSkuFullReductionParam 参数
     */
    void add(SmsSkuFullReductionParam smsSkuFullReductionParam);

    /**
     * 商品满减信息编辑
     *
     * @param smsSkuFullReductionParam 参数
     */
    void edit(SmsSkuFullReductionParam smsSkuFullReductionParam);

    /**
     * 商品满减信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
