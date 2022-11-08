package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsCouponParam;
import com.mdd.coupon.vo.SmsCouponListVo;
import com.mdd.coupon.vo.SmsCouponDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券信息服务接口类
 */
public interface ISmsCouponService {

    /**
     * 优惠券信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponVo>
     */
    PageResult<SmsCouponListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券信息详情
     *
     * @param id 主键ID
     * @return SmsCoupon
     */
    SmsCouponDetailVo detail(Long id);

    /**
     * 优惠券信息新增
     *
     * @param smsCouponParam 参数
     */
    void add(SmsCouponParam smsCouponParam);

    /**
     * 优惠券信息编辑
     *
     * @param smsCouponParam 参数
     */
    void edit(SmsCouponParam smsCouponParam);

    /**
     * 优惠券信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
