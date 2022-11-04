package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsCouponHistoryParam;
import com.mdd.coupon.vo.SmsCouponHistoryListVo;
import com.mdd.coupon.vo.SmsCouponHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券领取历史记录服务接口类
 */
public interface ISmsCouponHistoryService {

    /**
     * 优惠券领取历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponHistoryVo>
     */
    PageResult<SmsCouponHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键ID
     * @return SmsCouponHistory
     */
    SmsCouponHistoryDetailVo detail(Long id);

    /**
     * 优惠券领取历史记录新增
     *
     * @param smsCouponHistoryParam 参数
     */
    void add(SmsCouponHistoryParam smsCouponHistoryParam);

    /**
     * 优惠券领取历史记录编辑
     *
     * @param smsCouponHistoryParam 参数
     */
    void edit(SmsCouponHistoryParam smsCouponHistoryParam);

    /**
     * 优惠券领取历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
