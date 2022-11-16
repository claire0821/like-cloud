package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.CouponHistory;
import com.mdd.coupon.validate.CouponHistoryParam;
import com.mdd.coupon.vo.CouponHistoryListVo;
import com.mdd.coupon.vo.CouponHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券领取历史记录服务接口类
 */
public interface ICouponHistoryService extends IService<CouponHistory> {

    /**
     * 优惠券领取历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponHistoryVo>
     */
    PageResult<CouponHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键ID
     * @return CouponHistory
     */
    CouponHistoryDetailVo detail(Long id);

    /**
     * 优惠券领取历史记录新增
     *
     * @param couponHistoryParam 参数
     */
    void add(CouponHistoryParam couponHistoryParam);

    /**
     * 优惠券领取历史记录编辑
     *
     * @param couponHistoryParam 参数
     */
    void edit(CouponHistoryParam couponHistoryParam);

    /**
     * 优惠券领取历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
