package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.CouponSpuRelation;
import com.mdd.coupon.validate.CouponSpuRelationParam;
import com.mdd.coupon.vo.CouponSpuRelationListVo;
import com.mdd.coupon.vo.CouponSpuRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券与产品关联服务接口类
 */
public interface ICouponSpuRelationService extends IService<CouponSpuRelation> {

    /**
     * 优惠券与产品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponSpuRelationVo>
     */
    PageResult<CouponSpuRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键ID
     * @return CouponSpuRelation
     */
    CouponSpuRelationDetailVo detail(Long id);

    /**
     * 优惠券与产品关联新增
     *
     * @param couponSpuRelationParam 参数
     */
    void add(CouponSpuRelationParam couponSpuRelationParam);

    /**
     * 优惠券与产品关联编辑
     *
     * @param couponSpuRelationParam 参数
     */
    void edit(CouponSpuRelationParam couponSpuRelationParam);

    /**
     * 优惠券与产品关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
