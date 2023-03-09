package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.Coupon;
import com.mdd.coupon.validate.CouponParam;
import com.mdd.coupon.vo.CouponListVo;
import com.mdd.coupon.vo.CouponDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券信息服务接口类
 */
public interface ICouponService extends IService<Coupon> {

    /**
     * 优惠券信息列表
     *
     * @param params 搜索参数
     * @return PageResult<CouponVo>
     */
    PageResult<CouponListVo> list(Map<String, Object> params);

    /**
     * 优惠券信息详情
     *
     * @param code 优惠码
     * @return Coupon
     */
    CouponDetailVo detail(String code);

    /**
     * 优惠券信息新增
     *
     * @param couponParam 参数
     */
    void add(CouponParam couponParam);

    /**
     * 优惠券信息编辑
     *
     * @param couponParam 参数
     */
    void edit(CouponParam couponParam);

    /**
     * 优惠券信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
